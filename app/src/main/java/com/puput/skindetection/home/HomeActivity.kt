package com.puput.skindetection.home

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.firestore.FirebaseFirestore
import com.puput.skindetection.detail.doctor.DoctorActivity
import com.puput.skindetection.R
import com.puput.skindetection.databinding.ActivityHomeBinding
import com.puput.skindetection.detail.diagnosis.DetailDiagnosisActivity
import com.puput.skindetection.login.LoginActivity

class HomeActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityHomeBinding
    private lateinit var db: FirebaseFirestore

    companion object{
        const val EXTRA_DATA = "extra_data"

    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()

        val currentUser = auth.currentUser

        binding.username.text = currentUser?.displayName + "!"
        db = FirebaseFirestore.getInstance()
        binding.btnDiganosis.setOnClickListener(this)
        binding.profile.setOnClickListener(this)
        binding.btnAppointment.setOnClickListener(this)

        loadProfile()


    }

    @SuppressLint("SetTextI18n")
    private fun loadProfile() {
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.btn_diganosis ->{
                startActivity(Intent(this, DetailDiagnosisActivity::class.java))
            }
            R.id.profile -> {
                auth.signOut()
                Intent(this@HomeActivity, LoginActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
            R.id.btn_appointment ->{
                val moveIntent = Intent(this@HomeActivity, DoctorActivity::class.java)
                startActivity(moveIntent)
            }
        }
    }

}