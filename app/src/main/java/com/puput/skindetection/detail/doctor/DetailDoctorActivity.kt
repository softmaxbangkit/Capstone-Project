package com.puput.skindetection.detail.doctor

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.puput.skindetection.home.HomeActivity
import com.puput.skindetection.databinding.ActivityDetailDoctorBinding
import com.puput.skindetection.model.DoctorModel

class DetailDoctorActivity : AppCompatActivity(){

    private lateinit var binding: ActivityDetailDoctorBinding

    companion object{
        const val EXTRA_DATA = "extra_data"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailDoctorBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val docName = intent.getParcelableExtra<DoctorModel>(EXTRA_DATA) as DoctorModel

        binding.nameDoctor.text = docName.name
        binding.aboutDoctor.text = docName.information
        binding.specialistDoctor.text = docName.occupation

        Glide.with(this)
            .load(docName.img)
            .apply(RequestOptions().override(200, 200))
            .into(binding.imgDoctor)

        binding.back.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }

        binding.buttonAppointment1.setOnClickListener{

            val url = "https://api.whatsapp.com/send?phone=6282218996891&text=Hello%20Doctor,%20I%20want%20to%20consult%20about%20my%20skin%20problem";

            val openWhatsappIntent = Intent(Intent.ACTION_VIEW)
            openWhatsappIntent.data = Uri.parse(url)
            startActivity(openWhatsappIntent)
        }
    }

}