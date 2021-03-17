package com.example.newproject

import android.app.Dialog
import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var progressDialog: ProgressDialog
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db = Firebase.firestore
        progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Uploading..!")
        progressDialog.setCancelable(false)
        //---------------------
        btnSend.setOnClickListener {
            if (
                etName.text.isNotEmpty()
                && etMobile.text.isNotEmpty()
                && etAddress.text.isNotEmpty()
            ){
                addContact(etName.text.toString(),etMobile.text.toString(),etAddress.text.toString())
            }
        }


    }
    fun addContact(name: String, mobile: String, address: String){
        progressDialog.show()
        db.collection("contacts").add(
            Contact(name,mobile,address)
        ).addOnSuccessListener { 
            progressDialog.dismiss()
            Toast.makeText(this,"Added successfully",Toast.LENGTH_LONG).show()
        }.addOnFailureListener {
            progressDialog.dismiss()
            Toast.makeText(this,"Something went Wrong",Toast.LENGTH_LONG).show()
        }
    }
}