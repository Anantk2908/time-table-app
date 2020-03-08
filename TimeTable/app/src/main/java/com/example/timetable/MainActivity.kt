package com.example.timetable

import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Toast
import android.support.v7.app.AppCompatActivity import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction


class MainActivity : AppCompatActivity() {
    private val Tag = "Home"
    var isOpen= false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mFragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = mFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame, TabFragment2(), Tag).commit()

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