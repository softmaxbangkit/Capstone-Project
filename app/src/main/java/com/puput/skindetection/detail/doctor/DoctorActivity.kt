package com.puput.skindetection.detail.doctor

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import com.google.firebase.firestore.*
import com.puput.skindetection.databinding.ActivityDoctorBinding
import com.puput.skindetection.model.DoctorModel
import java.util.*
import kotlin.collections.ArrayList

class DoctorActivity : AppCompatActivity() {


    private lateinit var db: FirebaseFirestore
    private lateinit var binding: ActivityDoctorBinding
    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState:Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivityDoctorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = FirebaseFirestore.getInstance()
        fetchData()

    }


    private fun fetchData() {
        db.collection("doctors")
            .get()
            .addOnSuccessListener {
                val listDoctor : ArrayList<DoctorModel> = ArrayList()
                listDoctor.clear()

                for(document in it){
                    listDoctor.add((DoctorModel(
                        document.data.get("img") as String,
                        document.data.get("information") as String,
                        document.data.get("name") as String,
                        document.data.get("occupation") as String)
                            ))
                }

                val doctorAdapter = DoctorAdapter(listDoctor)

                binding.rvUser.apply{
                    binding.progressBar.visibility = View.VISIBLE
                    layoutManager = LinearLayoutManager(context)
                    adapter = doctorAdapter

                    doctorAdapter.setOnItemsClickCallback(object :
                        DoctorAdapter.OnItemClickCallback {
                        override fun onItemClicked(doctor: DoctorModel) {
                            val intent = Intent(this@DoctorActivity, DetailDoctorActivity::class.java)
                            intent.putExtra(DetailDoctorActivity.EXTRA_DATA, doctor)
                            startActivity(intent)
                        }
                    })
                }
                binding.progressBar.visibility = View.INVISIBLE
            }
            .addOnFailureListener{
                Log.v("Error","Failed Fetching Data")
            }
    }
}