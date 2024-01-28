package com.aspire.daybreaktodo_app.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.aspire.daybreaktodo_app.databinding.FragmentHomeBinding
import com.aspire.daybreaktodo_app.utils.TaskAdapter
import com.aspire.daybreaktodo_app.utils.TaskModel
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class HomeFragment : Fragment(), AddTaskDialogFragment.DialogSaveBtnClickListener,
    TaskAdapter.TaskClickListener {
    private lateinit var auth : FirebaseAuth
    private lateinit var dbRef : DatabaseReference
    private lateinit var navController : NavController
    private lateinit var binding: FragmentHomeBinding
    private var addTaskDialogFragment: AddTaskDialogFragment? = null
    private lateinit var adapter : TaskAdapter
    private lateinit var list : MutableList<TaskModel>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init(view)
        getDataFromFirebase()
        registerEvents()
    }

    private fun registerEvents() {
        binding.fabBtn.setOnClickListener {
            if(addTaskDialogFragment != null)
                childFragmentManager.beginTransaction().remove(addTaskDialogFragment!!).commit()
            addTaskDialogFragment = AddTaskDialogFragment()
            addTaskDialogFragment!!.setListener(this)
            addTaskDialogFragment!!.show(
                childFragmentManager,
                AddTaskDialogFragment.TAG
            )
        }
    }

    private fun init(view: View) {
        navController = Navigation.findNavController(view)
        auth = FirebaseAuth.getInstance()
        dbRef = FirebaseDatabase.getInstance().reference
            .child("Tasks").child(auth.currentUser?.uid.toString())
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager  = LinearLayoutManager(context)
        list = mutableListOf()
        adapter = TaskAdapter(list)
        adapter.setListener(this)
        binding.recyclerView.adapter = adapter
    }

    private fun getDataFromFirebase(){
        dbRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                list.clear()
                for(taskSnapshot in snapshot.children){
                    val  task = taskSnapshot.key?.let {
                        TaskModel(it,taskSnapshot.value.toString())
                    }
                    if(task != null){
                        list.add(task)
                    }
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context,error.message,Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onSaveTask(task : String, taskNameTIET : TextInputEditText) {
        dbRef.push().setValue(task).addOnCompleteListener{
            if(it.isSuccessful){
                Toast.makeText(context,"Saved",Toast.LENGTH_SHORT).show()

            }else{
                Toast.makeText(context,it.exception?.message,Toast.LENGTH_SHORT).show()

            }
            taskNameTIET.text = null
            addTaskDialogFragment!!.dismiss()
        }
    }

    override fun onUpdateTask(taskModel: TaskModel, taskNameTIET: TextInputEditText) {
        val map = HashMap<String,Any>()
        map[taskModel.taskId] = taskModel.task
        dbRef.updateChildren(map).addOnCompleteListener {
            if(it.isSuccessful){
                Toast.makeText(context,"Updated",Toast.LENGTH_SHORT).show()

            }else{
                Toast.makeText(context,it.exception?.message,Toast.LENGTH_SHORT).show()
            }
            taskNameTIET.text = null
            addTaskDialogFragment!!.dismiss()
        }
    }

    override fun onDeleteTask(taskModel: TaskModel) {
        dbRef.child(taskModel.taskId).removeValue().addOnCompleteListener {
            if(it.isSuccessful){
                Toast.makeText(context,"Deleted",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(context,it.exception?.message,Toast.LENGTH_SHORT).show()
            }

        }
    }

    override fun onEditTask(taskModel: TaskModel) {
        if(addTaskDialogFragment != null)
            childFragmentManager.beginTransaction().remove(addTaskDialogFragment!!).commit()

        addTaskDialogFragment = AddTaskDialogFragment.newInstance(taskModel.taskId,taskModel.task)
        addTaskDialogFragment!!.setListener(this)
        addTaskDialogFragment!!.show(childFragmentManager, AddTaskDialogFragment.TAG)
    }


}