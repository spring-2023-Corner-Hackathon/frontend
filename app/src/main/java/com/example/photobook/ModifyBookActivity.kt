package com.example.photobook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.photobook.databinding.ActivityModifyBookBinding

class ModifyBookActivity : AppCompatActivity() {
    lateinit var binding: ActivityModifyBookBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_modify_book)
        binding = ActivityModifyBookBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var bookCategory :String

        binding.categorySpinner.adapter = ArrayAdapter.createFromResource(this, R.array.categoryItem,
            android.R.layout.simple_spinner_item)

        binding.categorySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (position) {
                    // 여행
                    0 -> { bookCategory = "travle" }
                    // 일상
                    1 -> { bookCategory = "daily" }
                    // 기념일
                    2 -> { bookCategory = "Anniversary" }
                    //생일
                    3 -> { bookCategory = "birthday" }
                    // 인생네컷 모음
                    4-> { bookCategory = "lifefourcuts" }
                    // 기타
                    5 -> { bookCategory = "etc" }
                    //일치하는게 없는 경우
                    else -> {
                        bookCategory = "nothing"
                    }
                }
            }

        }
    }

}