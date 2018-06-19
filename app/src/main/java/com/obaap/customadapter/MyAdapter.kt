package com.obaap.customadapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.ThumbnailUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import java.io.File
import java.util.zip.Inflater

class MyAdapter:BaseAdapter {

    var inflater:LayoutInflater?=null
    var file:File?=null
    var files:Array<File>?=null
    var path:String?=null
    var context:Context?=null
    constructor(mainActivity: MainActivity) {
        context=mainActivity
        inflater= LayoutInflater.from(context)
        path="/storage/emulated/0/WhatsApp/Media/WhatsApp Images/"
        file= File(path)
        if(!file!!.exists()){
            file= File(path)
            path="/storage/sdcard0/WhatsApp/Media/WhatsApp Images/"
        }
        files=file!!.listFiles()
    }
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var v=inflater!!.inflate(R.layout.custom_adpt,null)
       var iview:ImageView=v.findViewById(R.id.iview)
        var indi_file=files!!.get(p0)
        var bmp=BitmapFactory.decodeFile(indi_file.path)
        var t_bmp=ThumbnailUtils.extractThumbnail(bmp,50,50)
        iview.setImageBitmap(t_bmp)
        var tview=v.findViewById<TextView>(R.id.tview)
        tview.setText(files!!.get(p0).name)
        var del=v.findViewById<ImageView>(R.id.dview)
        del.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {
                Toast.makeText(context,"Clicked"+indi_file.name,Toast.LENGTH_SHORT).show()
                indi_file.delete()
                files=file!!.listFiles()
                this@MyAdapter.notifyDataSetChanged()
            }
        })
        return v

    }

    override fun getItem(p0: Int): Any {
        return 0
     }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return files!!.size
    }


}