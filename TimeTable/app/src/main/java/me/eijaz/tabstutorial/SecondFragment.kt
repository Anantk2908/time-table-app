package me.eijaz.tabstutorial


import AddNoteActivity
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.SearchView
import android.text.ClipboardManager
import android.view.*
import android.widget.BaseAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_second.*
import kotlinx.android.synthetic.main.row.view.*

class SecondFragment : Fragment() {

    var listNotes = ArrayList<Note>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_second, container, false)

        LoadQuery("%")
    }

    override fun onResume() {
        super.onResume()
        LoadQuery("%")
    }

    private fun LoadQuery(title: String) {
        var dbManager = DbManager(this.context)
        val projections = arrayOf("ID", "Title", "Description")
        val selectionArgs = arrayOf(title)
        val cursor = dbManager?.Query(projections, "Title like ?", selectionArgs, "Title")
        listNotes.clear()
        if (cursor != null) {
            if (cursor.moveToFirst()) {

                do {
                    val ID = cursor.getInt(cursor.getColumnIndex("ID"))
                    val Title = cursor.getString(cursor.getColumnIndex("Title"))
                    val Description = cursor.getString(cursor.getColumnIndex("Description"))

                    listNotes.add(Note(ID, Title, Description))

                } while (cursor.moveToNext())
            }
        }

        //adapter
        var myNotesAdapter = this.context?.let { MyNotesAdapter(it, listNotes) }
        //set adapter
        notesLv.adapter = myNotesAdapter

        //get total number of tasks from ListView
        val total = notesLv.count
        //actionbar
        val mActionBar = supportActionBar
        if (mActionBar != null) {
            //set to actionbar as subtitle of actionbar
            mActionBar.subtitle = "You have $total note(s) in list..."
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.main_menu, menu)

        //searchView
        val sv: SearchView = menu!!.findItem(R.id.app_bar_search).actionView as SearchView

        val sm = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        sv.setSearchableInfo(sm.getSearchableInfo(componentName))
        sv.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                LoadQuery("%$query%")
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                LoadQuery("%$newText%")
                return false
            }
        });

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item != null) {
            when (item.itemId) {
                R.id.addNote -> {
                    startActivity(Intent(this.context , AddNoteActivity::class.java))
                }
                R.id.action_settings -> {
                    Toast.makeText(this.context, "Settings", Toast.LENGTH_SHORT).show()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    inner class MyNotesAdapter : BaseAdapter {
        var listNotesAdapter = ArrayList<Note>()
        var context: Context? = null

        constructor(context: Context, listNotesAdapter: ArrayList<Note>) : super() {
            this.listNotesAdapter = listNotesAdapter
            this.context = context
        }


        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            //inflate layout row.xml
            var myView = layoutInflater.inflate(R.layout.row, null)
            val myNote = listNotesAdapter[position]
            myView.titleTv.text = myNote.nodeName
            myView.descTv.text = myNote.nodeDes
            //delete button click
            myView.deleteBtn.setOnClickListener {
                var dbManager = DbManager(this.context)
                val selectionArgs = arrayOf(myNote.nodeID.toString())
                dbManager.delete("ID=?", selectionArgs)
                LoadQuery("%")
            }
            //edit//update button click
            myView.editBtn.setOnClickListener {
                GoToUpdateFun(myNote)
            }
            //copy btn click
            myView.copyBtn.setOnClickListener {
                //get title
                val title = myView.titleTv.text.toString()
                //get description
                val desc = myView.descTv.text.toString()
                //concatinate
                val s = title + "\n" + desc
                val cb = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                cb.text = s // add to clipboard
                Toast.makeText(this.context, "Copied...", Toast.LENGTH_SHORT ).show()
            }
            //share btn click
            myView.shareBtn.setOnClickListener {
                //get title
                val title = myView.titleTv.text.toString()
                //get description
                val desc = myView.descTv.text.toString()
                //concatenate
                val s = title + "\n" + desc
                //share intent
                val shareIntent = Intent()
                shareIntent.action = Intent.ACTION_SEND
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_TEXT, s)
                startActivity(Intent.createChooser(shareIntent, s))
            }

            return myView
        }

        override fun getItem(position: Int): Any {
            return listNotesAdapter[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return listNotesAdapter.size
        }

    }

    private fun GoToUpdateFun(myNote: Note) {
        var intent = Intent(this.context, AddNoteActivity::class.java)
        intent.putExtra("ID", myNote.nodeID) //put id
        intent.putExtra("name", myNote.nodeName) //ut name
        intent.putExtra("des", myNote.nodeDes) //put description
        startActivity(intent) //start activity
    }
}




