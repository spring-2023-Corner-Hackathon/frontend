package com.example.photobook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.photobook.databinding.ActivityMakeBinding

class MakeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_make)
        var coverResult = 0

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
            val intent = Intent(this, MusicCategory::class.java)
            startActivity(intent)
        }
        binding.coverBack.setOnClickListener {
            val homeFragment: HomeFragment = HomeFragment()
            val manager: FragmentManager = supportFragmentManager
            val transaction: FragmentTransaction = manager.beginTransaction()

            transaction.replace(R.id.mainFrameLayout, homeFragment).commit()
        }
    }
}