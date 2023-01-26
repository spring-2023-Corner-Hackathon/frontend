package com.example.photobook

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.photobook.databinding.ActivityDetailSettingBinding
import java.util.*

class detailSetting : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_detail_setting)

        val binding = ActivityDetailSettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var bookTitle = binding.photoBookTitle.text  // 책 제목(필수 선택)
        var bookMemo = binding.photoBookMemo.text    // 책 내용
        var bookCategory :String                     // 카테고리
        var bounds : Boolean

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

        binding.startDate.setOnClickListener {
            var start_calendar = Calendar.getInstance()
            var year = start_calendar.get(Calendar.YEAR)
            var month = start_calendar.get(Calendar.MONTH)
            var day = start_calendar.get(Calendar.DAY_OF_MONTH)

            var listener = DatePickerDialog.OnDateSetListener { _, i, i2, i3 ->
                binding.startDate.text = "${i}.${i2+1}.${i3}"
            }
            var picker = DatePickerDialog(this, listener, year, month, day)
            picker.show()
        }
        binding.endDate.setOnClickListener {
            var end_calendar = Calendar.getInstance()
            var year = end_calendar.get(Calendar.YEAR)
            var month = end_calendar.get(Calendar.MONTH)
            var day = end_calendar.get(Calendar.DAY_OF_MONTH)

            var listener = DatePickerDialog.OnDateSetListener { _, i, i2, i3 ->
                binding.endDate.text = "${i}.${i2+1}.${i3}"
            }
            var picker = DatePickerDialog(this, listener, year, month, day)
            picker.show()
        }

        binding.publicRg.setOnCheckedChangeListener { radioGroup, checkedId ->
            when(checkedId) {
                R.id.rb_everyone -> {
                    bounds = true // 전체 공개일 떄 true
                }
                R.id.rb_onlyMe-> {
                    bounds = false // 나만 보기일 때 false
                }
            }
        }

        binding.coverBack.setOnClickListener {
            val intent = Intent(this, ChooseImages::class.java)
            startActivity(intent)
        }

        binding.coverNext.setOnClickListener {
            if(TextUtils.isEmpty(binding.photoBookTitle.text)) {
                Toast.makeText(this, "제목은 필수 사항입니다.", Toast.LENGTH_SHORT).show()
            }
            if(!binding.rbEveryone.isChecked&&!binding.rbOnlyMe.isChecked) {
                Toast.makeText(this, "공개 범위를 선택해주세요.", Toast.LENGTH_SHORT).show()
            }
            else {
                //val intent = Intent(this, ChooseImages::class.java)
                startActivity(intent)
            }
        }


    }

}