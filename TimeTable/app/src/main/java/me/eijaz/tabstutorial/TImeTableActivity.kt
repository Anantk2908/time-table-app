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
        arrTT.add(TimeTable("Monday",R.drawable.monday))
        arrTT.add(TimeTable("Tuesday",R.drawable.tuesday))
        arrTT.add(TimeTable("Wednesday",R.drawable.wednesday))
        arrTT.add(TimeTable("Thursday",R.drawable.thursday))
        arrTT.add(TimeTable("Friday",R.drawable.friday))

        listView.adapter = CustomAdapter(applicationContext, arrTT)

    }
}