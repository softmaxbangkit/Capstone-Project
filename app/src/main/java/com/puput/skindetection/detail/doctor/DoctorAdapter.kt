package com.puput.skindetection.detail.doctor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.puput.skindetection.databinding.ItemDoctorBinding
import com.puput.skindetection.model.DoctorModel


class DoctorAdapter(private val doctorList : ArrayList<DoctorModel>) : RecyclerView.Adapter<DoctorAdapter.DoctorViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(doctor: DoctorModel)

    }

    inner class DoctorViewHolder(private val binding: ItemDoctorBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(doctor: DoctorModel){
            with(binding){
                tvItemName.text = doctor.name

                Glide.with(itemView.context)
                    .load(doctor.img)
                    .apply(RequestOptions().override(100,100))
                    .into(tvAvatar)
                itemView.setOnClickListener{
                    onItemClickCallback.onItemClicked(doctor)
                }
            }
        }

    }

    fun setOnItemsClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorViewHolder {
        val itemView = ItemDoctorBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return DoctorViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DoctorViewHolder, position: Int) {
        val doctor = doctorList[position]
        holder.bind(doctor)
    }

    override fun getItemCount(): Int {
        return doctorList.size
    }

    fun setDoctor(doctor: List<DoctorModel>?) {
        if (doctor == null) return
        this.doctorList.clear()
        this.doctorList.addAll(doctor)
    }

}