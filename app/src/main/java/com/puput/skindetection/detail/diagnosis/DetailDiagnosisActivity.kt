package com.puput.skindetection.detail.diagnosis

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.canhub.cropper.CropImage
import com.canhub.cropper.CropImageView
import com.puput.skindetection.DummyDiagnosis
import com.puput.skindetection.home.HomeActivity
import com.puput.skindetection.R
import com.puput.skindetection.databinding.ActivityDetailDiagnosisBinding
import com.puput.skindetection.ml.Inceptionresnetv2150x150Dynamic
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer


class DetailDiagnosisActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailDiagnosisBinding
    lateinit var bitmap: Bitmap
    lateinit var uri: Uri
    private var listDiagnosis = DummyDiagnosis.generateDiagnosis()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailDiagnosisBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fileName = "label_index.txt"
        val inputString = application.assets.open(fileName).bufferedReader().use {
            it.readText()
        }
        val plasticLabel = inputString.split("\n").toMutableList()


        binding.bottomNavigationView.background = null

        if (ContextCompat.checkSelfPermission(
                        this,
                        Manifest.permission.CAMERA
                ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.CAMERA),
                    CODE_CAMERA
            )
        }


        binding.fabCamera.setOnClickListener {
            startCropActivity()
        }

        binding.buttonPredict.setOnClickListener {
            val resized: Bitmap = Bitmap.createScaledBitmap(bitmap, 150, 150, true)
            val model = Inceptionresnetv2150x150Dynamic.newInstance(this)

            val inputFeature0 =
                TensorBuffer.createFixedSize(intArrayOf(1, 150, 150, 3), DataType.FLOAT32)
            val tensorImage = TensorImage(DataType.FLOAT32)
            tensorImage.load(resized)
            val byteBuffer = tensorImage.buffer
            inputFeature0.loadBuffer(byteBuffer)

            val outputs = model.process(inputFeature0)
            val outputFeature0 = outputs.outputFeature0AsTensorBuffer

            val max = getMax(outputFeature0.floatArray)

            model.close()

            for (i in outputFeature0.floatArray.indices) {
              Log.d("main","${plasticLabel[i]}  = ${outputFeature0.floatArray[i]}")
            }
            Log.d("main", "hasil yang tertinggi ke ${plasticLabel[max]}")

            var temp:Float= 0.0F
            var sortingArrayFloat = arrayListOf<Float>()
            for (i in outputFeature0.floatArray.indices){
                sortingArrayFloat.add(outputFeature0.floatArray[i])
            }


            Log.d("main", "array not sorder$sortingArrayFloat")

            for (i in sortingArrayFloat.indices) {
                for(j in i+1 until sortingArrayFloat.size){
                    if (sortingArrayFloat[i]<sortingArrayFloat[j]){
                        temp = sortingArrayFloat[i]
                        val tempLabel = plasticLabel[i]
                        sortingArrayFloat[i] = sortingArrayFloat[j]
                        sortingArrayFloat[j] = temp
                        plasticLabel[i] = plasticLabel[j]
                        plasticLabel[j] = tempLabel
                    }
                }

            }
            Log.d("main", "arrray sorted $sortingArrayFloat")
            Log.d("main", plasticLabel.toString())




            binding.progressBar.visibility = View.VISIBLE

            binding.predictProbability.visibility = View.VISIBLE
            binding.predict1.text = plasticLabel[0] + sortingArrayFloat[0]
            binding.predict2.text = plasticLabel[1] + sortingArrayFloat[1]
            binding.predict3.text = plasticLabel[2] + sortingArrayFloat[2]

            binding.result.visibility = View.VISIBLE
            binding.nameDiagnosis.text = plasticLabel[0] + sortingArrayFloat[0]


            binding.desc.visibility = View.VISIBLE
            binding.descriptionDiagnosis.text = listDiagnosis[max].description

            binding.progressBar.visibility = View.INVISIBLE

        }

        binding.back.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            val result: CropImage.ActivityResult? = CropImage.getActivityResult(data)
            if (resultCode == RESULT_OK) {
                val cropUri = result?.uriContent
                bitmap =
                    MediaStore.Images.Media.getBitmap(this.contentResolver, cropUri)
                binding.ivDetailImage.setImageURI(cropUri)

                binding.buttonAppointment.setOnClickListener{

                    val shareIntent: Intent = Intent().apply {
                        action = Intent.ACTION_SEND;
                        putExtra(Intent.EXTRA_STREAM,cropUri)
//                        putExtra(Intent.EXTRA_TEXT, "This is my prediction of skin disease.")
////                        putExtra(Intent.EXTRA_TEXT,listDiagnosis[getMax()].description)
////                        putExtra(Intent.EXTRA_TEXT,plasticLabel[max])
//                        type = "text/plain"
                        type = "image/jpeg"
                        setPackage("com.whatsapp");
                    }
                    startActivity(Intent.createChooser(shareIntent, resources.getText(R.string.send_to)))

                }

            } else {
                Log.d("image", "error")
            }
        }

    }

    companion object {
        const val CODE_CAMERA = 42
    }

    private fun getMax(arr: FloatArray): Int {
        var index = 0
        var min = 0.0


        for (i in 0 until arr.count() - 1) {
            if (arr[i] > min) {
                index = i
                min = arr[i].toDouble()
            }
        }

        return index
    }

    private fun startCropActivity() {
        CropImage.activity().setAspectRatio(8, 8).setGuidelines(CropImageView.Guidelines.ON)
            .start(this)
    }

}