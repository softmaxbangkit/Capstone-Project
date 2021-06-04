package com.puput.skindetection.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.puput.skindetection.home.HomeActivity
import com.puput.skindetection.R
import com.puput.skindetection.databinding.ActivityLoginBinding
import com.puput.skindetection.register.RegisterActivity


class LoginActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        var TAG = LoginActivity::class.java.simpleName
    }

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.tvRegister.setOnClickListener(this)
        binding.btnLogin.setOnClickListener {
            signIn()
        }

        binding.tvRegister.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun signIn() {

        val email = binding.etEmail.text.toString().trim()
        val password = binding.etPassword.text.toString()

        if (email.isEmpty()){
            binding.etEmail.error = "Email harus diisi"
            return
        }

        if (password.isEmpty() || password.length < 8){
            binding.etPassword.error = "Password harus lebih dari 8 karakter"
            return
        }

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    val name = user?.email
                    val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                    intent.putExtra("EXTRA_NAME", name)
                    startActivity(intent)
                    finish()
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
          R.id.tv_register -> startActivity(Intent(this, RegisterActivity::class.java))
      }
    }

    override fun onStart() {
        super.onStart()
        if (auth.currentUser != null){
            Intent(this@LoginActivity, HomeActivity::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(it)
                finish()
            }
        }
    }
}