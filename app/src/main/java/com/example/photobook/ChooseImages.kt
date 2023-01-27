package com.example.photobook

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import com.example.photobook.databinding.ActivityChooseImagesBinding

class ChooseImages : AppCompatActivity() {

    var maxPhoto: Int = 0
    private lateinit var launcher: ActivityResultLauncher<Intent>
    var uriList = arrayListOf<Uri>()
    // lateinit var recyclerView: RecyclerView  // 이미지를 보여줄 리사이클러뷰
    val adapter = MultiImageAdapter(uriList,this) // 리사이클러뷰에 적용시킬 어댑터
    var editPoint: Int =0

    companion object {
        const val REVIEW_MIN_LENGTH = 10
        // 갤러리 권한 요청
        const val REQ_GALLERY = 1

        // API 호출시 Parameter key값
        const val PARAM_KEY_IMAGE = "image"
        const val PARAM_KEY__PRODUCT_ID = "product_id"
        const val PARAM_KEY_REVIEW = "review_content"
        const val PARAM_KEY_RATING = "rating"
    }

    private val imageResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        uriList.clear()
        if(it.resultCode == Activity.RESULT_OK) {
            val clipData = it?.data?.clipData
            val clipDataSize = clipData?.itemCount
            if (clipData == null) {
                val selectedImageUri = it?.data?.data!!
                // 얻어온 이미지 uri로 작업 진행
                uriList.add(selectedImageUri)
            }
            else {
                val count = clipData!!.itemCount
                if (count > maxPhoto) {
                    Toast.makeText(getApplicationContext(),"사진은 "+maxPhoto+"장까지 선택 가능합니다.", Toast.LENGTH_SHORT).show()
                    return@registerForActivityResult
                }
                for(i in 0 until clipDataSize!!) {
                    // 선택한 사진 수 만큼 반복
                    val selectedImageUri = clipData.getItemAt(i).uri
                    uriList.add(selectedImageUri)
                    // 얻어온 이미지 uri 로 작업 진행
                    Log.d("test", uriList.get(i).toString())
                }
//                clipData.let {
//                    clipData-> for(i in 0 until clipDataSize!!) {
//                        // 선택한 사진 수 만큼 반복
//                        val selectedImageUri = clipData.getItemAt(i).uri
//                        uriList.add(selectedImageUri)
//                    // 얻어온 이미지 uri 로 작업 진행
//                    Log.d("test", uriList.get(i).toString())
//                    }
//                }
            }
            adapter.notifyDataSetChanged()
        }
    }

    private fun selectGallery() {

        val intent = Intent()
        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        intent.action = Intent.ACTION_PICK
        imageResult.launch(intent)

//        val maxNumPhotos = 10
//        val intent = Intent(MediaStore.ACTION_PICK_IMAGES)
//        intent.putExtra(MediaStore.EXTRA_PICK_IMAGES_MAX, maxNumPhotos)
//        imageResult.launch(intent)

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_choose_images)
        val binding = ActivityChooseImagesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        editPoint = intent.getIntExtra("editPoint", 0)
        maxPhoto = editPoint+1

        binding.maxPhotoTV.text = "선택할 수 있는 사진 수 : " + maxPhoto

        var recyclerView=binding.recyclerGridImageView
        recyclerView.layoutManager = GridLayoutManager(getApplicationContext(), 3)
        recyclerView.adapter = adapter

        binding.getImagesButton.setOnClickListener {
            selectGallery()
        }

        binding.coverNext.setOnClickListener {
            if(uriList.size == 0) {
                Toast.makeText(this, "사진을 한 장 이상 선택해주세요.", Toast.LENGTH_SHORT).show()
            }
            else {
                val intent = Intent(this, detailSetting::class.java)
                startActivity(intent)
            }

        }
        binding.coverBack.setOnClickListener {
            val intent = Intent(this, ChooseMusic::class.java)
            startActivity(intent)
        }

    }
/*
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode != Activity.RESULT_OK) {
            return
        }
        when (resultCode) {
            2000 -> {
                data?:return
                val uri = data.data as Uri
            }
            else -> {
                Toast.makeText(this, "사진 못가져와", Toast.LENGTH_SHORT).show()
            }
        }
    }

*/

}