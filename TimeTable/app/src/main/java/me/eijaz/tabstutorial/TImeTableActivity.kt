package me.eijaz.tabstutorial

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ListView

class TImeTableActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_first)

        var listView = findViewById(R.id.listView) as ListView
        var arrTT: ArrayList<TimeTable> = ArrayList()
        arrTT.add(TimeTable("Monday",R.drawable.Monday))
        arrTT.add(TimeTable("Tuesday",R.drawable.Tuesday))
        arrTT.add(TimeTable("Wednesday",R.drawable.Wednesday))
        arrTT.add(TimeTable("Thursday",R.drawable.Thursday))
        arrTT.add(TimeTable("Friday",R.drawable.Friday))

        listView.adapter = CustomAdapter(applicationContext, arrTT)

    }
}