package me.eijaz.tabstutorial


import android.content.Context
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.support.v4.app.Fragment
import android.support.v7.widget.DialogTitle
import android.util.EventLogTags
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter


class SecondFragment : Fragment() {

    var listNotes = ArrayList<Note>()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_second, container, false)

        LoadQuery("%")
    }

    private fun LoadQuery(title: String) {
        val dbManager = DbManager(this)
        val projections = arrayOf("ID", "Title", "Description" )
        val selectionArgs = arrayOf(title)
        val cursor = dbManager.Query(projections,"Title like ?", selectionArgs,"Title")
        listNotes.clear()
        if(cursor.moveToFirst()){

            do {
                val ID = cursor.getInt(cursor.getColumnIndex("ID"))
                val Title = cursor.getString(cursor.getColumnIndex("Title"))
                val Description = cursor.getString(cursor.getColumnIndex("Description"))

                listNotes.add(Note(ID, Title, Description))
            }while (cursor.moveToNext())


            }
        }

        var myNotesAdapter = MyNotesAdapter(this, listNotes)
    }

     inner class MyNotesAdapter() : BaseAdapter {

         var listNotesArray = ArrayList<Note>()
         var context:Context?= null

         constructor(context: Context,listNotesArray: ArrayList<Note>) : super() {
             this.listNotesArray = listNotesArray
             this.context = context
         }


        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

            var myView = layoutInflater.inflate()

        }

        override fun getItem(p0: Int): Any {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun getItemId(p0: Int): Long {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun getCount(): Int {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {

        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<MyNotesAdapter> {
            override fun createFromParcel(parcel: Parcel): MyNotesAdapter {
                return MyNotesAdapter(parcel)
            }

            override fun newArray(size: Int): Array<MyNotesAdapter?> {
                return arrayOfNulls(size)
            }
        }
    }

}
