package com.obaap.customadapter

import android.Manifest
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.widget.ListView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    var lview:ListView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lview= this.findViewById(R.id.lview)
        var status=ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE)
        if(status==PackageManager.PERMISSION_GRANTED){
            lview!!.adapter=MyAdapter(this)
        }else{
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),10)
        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
            lview!!.adapter=MyAdapter(this)
        }else{
            Toast.makeText(this,"Permission Required",Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
