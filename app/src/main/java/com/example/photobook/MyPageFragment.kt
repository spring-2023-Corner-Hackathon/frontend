package com.example.photobook

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.app.Activity
import android.app.Activity.RESULT_OK
import android.app.appsearch.SetSchemaRequest.READ_EXTERNAL_STORAGE
import android.content.ContentResolver
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.provider.CalendarContract.Attendees.query
import android.provider.CalendarContract.Instances.query
import android.provider.ContactsContract
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.example.photobook.databinding.FragmentHomeBinding
import com.example.photobook.databinding.FragmentMyPageBinding
import kotlin.math.log

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MyPageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MyPageFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var binding: FragmentMyPageBinding

    var Gallary = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding = FragmentMyPageBinding.inflate(inflater, container, false)

        fun calculateInSampleSize(fileUri: Uri, reqWidth: Int, reqHeight:Int) : Int{
            val options = BitmapFactory.Options()
            options.inJustDecodeBounds = true
            try{
                var inputStream = requireActivity().contentResolver.openInputStream(fileUri)

                BitmapFactory.decodeStream(inputStream, null, options)
                inputStream!!.close()
                inputStream = null
            }
            catch (e:Exception){
                e.printStackTrace()
            }
            val (height: Int, width: Int) = options.run { outHeight to outWidth }
            var inSampleSize = 1
            if(height > reqHeight || width > reqWidth){
                val halfHeight: Int = height / 2
                val halfWidth: Int = width / 2

                while (halfHeight / inSampleSize >= reqHeight && halfWidth / inSampleSize >= reqWidth){
                    inSampleSize *= 2
                }
            }
            return inSampleSize
        }

        val requestGalleryLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){
                try{
                    val calRatio = calculateInSampleSize(
                        it.data!!.data!!, 150, 150
                    )
                    val option = BitmapFactory.Options()
                    var inputStream = requireActivity().contentResolver.openInputStream(it.data!!.data!!)
                    val bitmap = BitmapFactory.decodeStream(inputStream, null, option)
                    inputStream!!.close()
                    inputStream = null

                    bitmap?.let {
                        binding.propileImg.setImageBitmap(bitmap)
                    } ?: let{
                        Log.d("mobileApp", "bitmap null")
                    }
                }
                catch (e:Exception){
                    e.printStackTrace()
                }
            }

        //프로필 사진 변경
        binding.propileImg.setOnClickListener{
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.type = "image/*"
            requestGalleryLauncher.launch(intent)
        }
        //닉네임 변경
        binding.nickname.setOnClickListener {
            //닉네임 변경
        }

        //비밀번호 재설정
        binding.ChangePass.setOnClickListener {
            val intent = Intent(getActivity(), passwordActivity::class.java)
            startActivity(intent)
        }
        //로그인하기
        binding.goLogin.setOnClickListener {
            //만약 로그아웃 -> 로그인
            val intent = Intent(getActivity(), LoginActivity::class.java)
            startActivity(intent)

            //만약 로그인 -> 로그아웃
        }

        //탈퇴하기
        binding.Deltet.setOnClickListener {
            //탈퇴하기
        }

        return binding.root

    }

    private fun navigatePhotos() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivityForResult(intent,2000)

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MyPageFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MyPageFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}