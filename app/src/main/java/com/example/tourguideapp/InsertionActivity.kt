package com.example.tourguideapp

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase



private lateinit var etEmpName: EditText
private lateinit var etEmpAge: EditText
private lateinit var etEmpSalary: EditText
private lateinit var btnSaveData: Button

private lateinit var dbRef: DatabaseReference

class InsertionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insertion)

        etEmpName=findViewById(R.id.etEmpName)
        etEmpAge=findViewById(R.id.etEmpAge)
        etEmpSalary=findViewById(R.id.etEmpSalary)
        btnSaveData=findViewById(R.id.btnSave)

        dbRef= FirebaseDatabase.getInstance().getReference("Users")
        btnSaveData.setOnClickListener {
            saveEmployeeData()
            val intent = Intent(this, Profile::class.java)
            finish()
            startActivity(intent)
        }
    }
    private fun saveEmployeeData(){
        val empName= etEmpName.text.toString()
        val empAge= etEmpAge.text.toString()
        val empSalary= etEmpSalary.text.toString()
        var mp = MediaPlayer.create(this,R.raw.save)

        if(empName.isEmpty()){
            etEmpName.error="Please enter PLace name"
        }
        if(empAge.isEmpty()){
            etEmpAge.error="Please enter Date"
        }
        if(empSalary.isEmpty()){
            etEmpSalary.error="Please enter Bugget"
        }
        val empId= dbRef.push().key!!
        val employee=Users(empId,empName,empAge,empSalary)
        dbRef.child(empId).setValue(employee).addOnSuccessListener{
            Toast.makeText(this,"Data Inserted successfully",Toast.LENGTH_LONG).show()
            etEmpName.text.clear()
            etEmpSalary.text.clear()
            etEmpAge.text.clear()
            mp.start()
        }.addOnFailureListener {
            //Toast.makeText(this,"Error ${error.message}",Toast.LENGTH_LONG).show()
        }

    }
}