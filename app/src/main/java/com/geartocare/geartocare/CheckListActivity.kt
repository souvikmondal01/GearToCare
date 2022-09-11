package com.geartocare.geartocare

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.geartocare.geartocare.databinding.ActivityCheckListBinding
import com.google.firebase.database.*
import kotlin.collections.ArrayList

class CheckListActivity : AppCompatActivity(), ItemClicked {
    private lateinit var binding: ActivityCheckListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fab.setOnClickListener {
            val addDialog = LayoutInflater.from(this).inflate(R.layout.add_dialogbox, null)
            val addDialogBuilder = AlertDialog.Builder(this).setView(addDialog).setTitle("").show()

            val addBtn: Button = addDialog.findViewById(R.id.btn_add_item)
            val addEt: EditText = addDialog.findViewById(R.id.et_add_item)
            addBtn.setOnClickListener {
                val text = addEt.text.toString()
                val db = FirebaseDatabase.getInstance()
                val dbRef = db.getReference("check_list")
                val id: String = dbRef.push().key.toString()
                val data = CheckList(text, id)
                dbRef.child(id).setValue(data)
                addDialogBuilder.dismiss()
            }
        }

        val db = FirebaseDatabase.getInstance()
        val dataArray = ArrayList<CheckList>()
        val dbRef = db.getReference("check_list")
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                dataArray.clear()
                if (snapshot.exists()) {
                    for (i in snapshot.children) {
                        val dataSet = i.getValue(CheckList::class.java)
                        dataArray.add(dataSet!!)
                    }
                    binding.recyclerView.layoutManager = LinearLayoutManager(this@CheckListActivity)
                    val adapter = CheckListAdapter(dataArray, this@CheckListActivity)
                    binding.recyclerView.adapter =adapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }

    override fun onItemClicked(item: String, id: String) {

        val title = TextView(this)
        title.text = "Delete?"
        title.setPadding(10, 20, 10, 20)
        title.gravity = Gravity.CENTER
        title.textSize = 20F
        val builder = LayoutInflater.from(this).inflate(R.layout.delete_dialogbox, null)
        val addDialogBuilder =
            AlertDialog.Builder(this).setView(builder).setCustomTitle(title).show()

        val deleteBtn: Button = builder.findViewById(R.id.btn_delete_item)
        deleteBtn.setOnClickListener {
            val db = FirebaseDatabase.getInstance()
            val dbRef = db.getReference("check_list")
            dbRef.child(id).removeValue()
            addDialogBuilder.dismiss()
        }

    }

}
