package com.example.timetable

import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.app.FragmentManager
import android.app.Fragment
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.FragmentManager
//import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.timetable.ui.main.SectionsPagerAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    var isOpen= false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)

        val fabOpen = AnimationUtils.loadAnimation(this, R.anim.fab_open)
        val fabClose = AnimationUtils.loadAnimation(this,R.anim.fab_close)
        val fabRClockwise = AnimationUtils.loadAnimation(this,R.anim.rotate_clockwise)
        val fabRAntiClockwise = AnimationUtils.loadAnimation(this,R.anim.rotate_anticlockwise)


        fab.setOnClickListener {

            if(isOpen) {
                ds.startAnimation(fabClose)
                eee.startAnimation(fabClose)
                c_prog.startAnimation(fabClose)
                lin_al.startAnimation(fabClose)
                d_math.startAnimation(fabClose)
                cul.startAnimation(fabClose)
                fab.startAnimation(fabRClockwise)


                isOpen = false
            }

            else{
                ds.startAnimation(fabOpen)
                eee.startAnimation(fabOpen)
                c_prog.startAnimation(fabOpen)
                lin_al.startAnimation(fabOpen)
                d_math.startAnimation(fabOpen)
                cul.startAnimation(fabOpen)
                fab.startAnimation(fabRAntiClockwise)

                ds.isClickable
                eee.isClickable
                c_prog.isClickable
                lin_al.isClickable
                d_math.isClickable
                cul.isClickable

                isOpen = true
            }

            ds.setOnClickListener {
                Toast.makeText(this,"You clicked on DS",Toast.LENGTH_LONG).show()
            }

            eee.setOnClickListener {
                Toast.makeText(this,"You clicked on EEE",Toast.LENGTH_LONG).show()
            }
            c_prog.setOnClickListener {
                Toast.makeText(this,"You clicked on C Programming",Toast.LENGTH_LONG).show()
            }
            lin_al.setOnClickListener {
                Toast.makeText(this,"You clicked on LinAl",Toast.LENGTH_LONG).show()
            }
            d_math.setOnClickListener {
                Toast.makeText(this,"You clicked on Discrete Maths",Toast.LENGTH_LONG).show()
            }
            cul.setOnClickListener {
                Toast.makeText(this,"You clicked on CulEdu",Toast.LENGTH_LONG).show()
            }

        }
    }
    inner class SectionPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm){
        override fun getItem(position:Int): Fragment? {
            when(position) {
                0 -> {
                    return HomeActivity()
                }


                1 -> {
                    return NotesActivity()
                }



            }

            return null

        }

        override fun getCount(): Int {
            return 2
        }

        override fun getPageTitle(position: Int): CharSequence? {
            when (position) {
                0 -> {return "Time Table"}
                1 -> {return "Notes "}

                else -> return null
            }
        }
    }
}