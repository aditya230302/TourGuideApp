package com.example.tourguideapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tourguideapp.Users
import com.google.firebase.database.*

class FetchingActivity : AppCompatActivity() {

    private lateinit var empRecyclerView: RecyclerView
    private lateinit var tvLoadingData:TextView
    private lateinit var back:ImageButton
    private lateinit var empList: ArrayList<Users>
    private lateinit var dbRef:DatabaseReference

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetching)

        empRecyclerView=findViewById(R.id.rvEmp)
        empRecyclerView.layoutManager=LinearLayoutManager(this@FetchingActivity)
        empRecyclerView.setHasFixedSize(true)
        tvLoadingData=findViewById(R.id.tvLoadingData)
        back = findViewById(R.id.back)

        back.setOnClickListener{
            val intent = Intent(this, Profile::class.java)
            startActivity(intent)
        }


        empList= arrayListOf<Users>()
        getEmployeesData()

    }
    private  fun getEmployeesData(){
        empRecyclerView.visibility= View.GONE
        tvLoadingData.visibility=View.VISIBLE
        dbRef=FirebaseDatabase.getInstance().getReference("Users")
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                empList.clear()
                if(snapshot.exists()){
                    for(empSnap in snapshot.children){
                        val empData=empSnap.getValue(Users::class.java)
                        empList.add(empData!!)
                    }
                    val mAdapter=EmpAdapter(empList)
                    empRecyclerView.adapter=mAdapter
                    mAdapter.setOnItemClickListener(object :EmpAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {
                            val intent=Intent(this@FetchingActivity,EmployeeDetailsActivity::class.java)
                            intent.putExtra("empId",empList[position].empId)
                            intent.putExtra("empName",empList[position].empName)
                            intent.putExtra("empAge",empList[position].empAge)
                            intent.putExtra("empSalary",empList[position].empSalary)
                            startActivity(intent)
                        }
                    })
                    empRecyclerView.visibility=View.VISIBLE
                    tvLoadingData.visibility=View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}