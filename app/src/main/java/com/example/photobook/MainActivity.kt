package com.example.photobook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.photobook.databinding.ActivityMainBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager


private const val TAG_CALENDER = "calender_fragment"
private const val TAG_HOME = "home_fragment"
private const val TAG_MY_PAGE = "my_page_fragment"

class MainActivity : AppCompatActivity() {
    lateinit var mainbinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainbinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainbinding.root)


        //navigationbar
        setFragment(TAG_HOME,HomeFragment())

        mainbinding.navigationView.setOnItemSelectedListener {  item ->
            when(item.itemId) {
                R.id.calendarFragment -> setFragment(TAG_CALENDER, CalendarFragment())
                R.id.homeFragment -> setFragment(TAG_HOME, HomeFragment())
                R.id.myPageFragment -> setFragment(TAG_MY_PAGE, MyPageFragment())
            }
            true
        }
    }

    private fun setFragment(tag: String, fragment: Fragment) {
        val manager: FragmentManager = supportFragmentManager
        val fragTransaction = manager.beginTransaction()

        if(manager.findFragmentByTag(tag) == null){
            fragTransaction.add(R.id.mainFrameLayout, fragment, tag)
        }

        val calender = manager.findFragmentByTag(TAG_CALENDER)
        val home = manager.findFragmentByTag(TAG_HOME)
        val myPage = manager.findFragmentByTag(TAG_MY_PAGE)

        if(calender != null){
            fragTransaction.hide(calender)
        }

        if(home != null){
            fragTransaction.hide(home)
        }

        if(myPage != null){
            fragTransaction.hide(myPage)
        }

        if(tag == TAG_HOME){
            if(home != null){
                fragTransaction.show(home)
            }
        }
        else if(tag == TAG_CALENDER){
            if(calender != null){
                fragTransaction.show(calender)
            }
        }
        else if(tag == TAG_MY_PAGE){
            if(myPage != null){
                fragTransaction.show(myPage)
            }
        }

        fragTransaction.commitAllowingStateLoss()
    }

}