package me.eijaz.tabstutorial

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TableRow
import android.widget.TextView

class CustomAdapter(var context: Context, var timeTable: ArrayList<TimeTable>): BaseAdapter() {

    private class ViewHolder(row: View?){
        var txtName: TextView
        var ivImage: ImageView

        init {
            this.txtName = row?.findViewById(R.id.txtName) as TextView
            this.ivImage= row?.findViewById(R.id.ivPokemon) as ImageView
        }
    }
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

        var view: View?
        var viewHolder: ViewHolder

        if (p1 == null){
            var layout = LayoutInflater.from(context)
            view = layout.inflate(R.layout.timetable_list,p2,false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else{
            view = p1
            viewHolder = view.tag as ViewHolder
        }

        var timeTable: TimeTable = getItem(p0) as TimeTable
        viewHolder.txtName.text = timeTable.name
        viewHolder.ivImage.setImageResource(timeTable.image)
        return view as View
    }

    override fun getItem(p0: Int): Any {
        return timeTable.get(p0)
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return timeTable.count()
    }
}