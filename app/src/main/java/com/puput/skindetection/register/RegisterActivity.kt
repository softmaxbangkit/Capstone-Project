package com.puput.skindetection.register

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.puput.skindetection.home.HomeActivity
import com.puput.skindetection.login.LoginActivity
import com.puput.skindetection.R
import com.puput.skindetection.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var auth: FirebaseAuth

    private lateinit var binding: ActivityRegisterBinding

    companion object {
        var TAG = RegisterActivity::class.java.simpleName
    }

    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()

        db = Firebase.firestore
        binding.tvLogin.setOnClickListener(this)
        binding.btnSignup.setOnClickListener {
            signUp()

        }
    }

    private fun signUp() {
        val username = binding.etUsername.text.toString().trim()
        val email = binding.etEmail.text.toString().trim()
        val password = binding.etPassword.text.toString()

        if (username.isEmpty()){
            binding.etUsername.error = "Username harus diisi"
            return
        }
        if (email.isEmpty()){
            binding.etEmail.error = "Email harus diisi"
            return
        }

        if (password.isEmpty() || password.length < 8){
            binding.etPassword.error = "Password harus lebih dari 8 karakter"
            return
        }



        db.collection("users").get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(TAG, "addOnSuccessListener ${document.data["username"]}")
                    if (document.data["username"] == username) {
                        Log.d(TAG, "addOnSuccessListener ${document.data["username"]}")
                        binding.etUsername.error = "Username already exist"
                        Toast.makeText(this, "Username already exist", Toast.LENGTH_SHORT)
                            .show()
                        return@addOnSuccessListener
                    }
                }
                register(email, password, username)
            }
            .addOnFailureListener {e ->
                Toast.makeText(this, e.message, Toast.LENGTH_SHORT ).show()
            }
    }

    private fun register(email: String, password: String, username: String ){
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    val profileUpdates = UserProfileChangeRequest.Builder()
                        .setDisplayName(username)
                        .build()
                    user?.updateProfile(profileUpdates)
                    val userdata = hashMapOf(
                        "username" to username,
                        "email" to email
                    )
                    db.collection("users")
                        .add(userdata)

                    Intent(this@RegisterActivity, HomeActivity::class.java).also {
                        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(it)
                        finish()
                    }

                } else {
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.tv_login -> startActivity(Intent(this, LoginActivity::class.java))
        }
    }
    override fun onStart() {
        super.onStart()
        if(auth.currentUser != null){
            Intent(this@RegisterActivity, LoginActivity::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(it)
            }
        }

    }
}