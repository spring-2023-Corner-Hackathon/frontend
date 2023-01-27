package com.example.photobook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.photobook.databinding.ActivityMakeBinding

class MakeActivity : AppCompatActivity() {
    var coverResult = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_make)


        val binding = ActivityMakeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.checkbox1.setOnCheckedChangeListener { button, isChecked ->
            if(isChecked) {
                binding.checkbox2.isChecked = false
                binding.checkbox3.isChecked = false
                binding.checkbox4.isChecked = false
                coverResult = 1
            }
        }
        binding.checkbox2.setOnCheckedChangeListener { button, isChecked ->
            if(isChecked) {
                binding.checkbox1.isChecked = false
                binding.checkbox3.isChecked = false
                binding.checkbox4.isChecked = false
                coverResult = 2
            }
        }
        binding.checkbox3.setOnCheckedChangeListener { button, isChecked ->
            if(isChecked) {
                binding.checkbox2.isChecked = false
                binding.checkbox1.isChecked = false
                binding.checkbox4.isChecked = false
                coverResult = 3
            }
        }
        binding.checkbox4.setOnCheckedChangeListener { button, isChecked ->
            if(isChecked) {
                binding.checkbox2.isChecked = false
                binding.checkbox3.isChecked = false
                binding.checkbox1.isChecked = false
                coverResult = 4
            }
        }
        binding.coverNext.setOnClickListener {
            if(!binding.checkbox1.isChecked && !binding.checkbox2.isChecked &&
                !binding.checkbox3.isChecked && !binding.checkbox4.isChecked) {
                Toast.makeText(this, "표지를 선택해주세요", Toast.LENGTH_SHORT).show()
            }
            else {
                val intent = Intent(this, MusicCategory::class.java)
                startActivity(intent)
            }

        }
        binding.coverBack.setOnClickListener {
            val homeFragment: HomeFragment = HomeFragment()
            val manager: FragmentManager = supportFragmentManager
            val transaction: FragmentTransaction = manager.beginTransaction()

            transaction.replace(R.id.mainFrameLayout, homeFragment).commit()
        }
    }
}