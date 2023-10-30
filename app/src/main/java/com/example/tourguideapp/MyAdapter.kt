package com.example.tourguideapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.ToggleButton
import androidx.core.view.isVisible


class MyAdapter(var mCtx:Context, var resources:Int, var items:List<Model>):ArrayAdapter<Model>(mCtx,resources,items){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater:LayoutInflater = LayoutInflater.from(mCtx)
        val view:View = layoutInflater.inflate(resources, null)
        val imageView:ImageView = view.findViewById(R.id.image)
        val titleTextView:TextView = view.findViewById(R.id.textView1)
        val descriptionTextView:TextView = view.findViewById(R.id.textView2)
        var button:ToggleButton = view.findViewById(R.id.btn)
        var delete:Button = view.findViewById(R.id.delete)
        var boolean = false

        button.setOnClickListener {
            boolean = !boolean
            val mItem:Model = items[position]
            descriptionTextView.text = mItem.description
            descriptionTextView.isVisible = boolean
        }

        delete.setOnClickListener{
            this.remove(items[position])
            notifyDataSetChanged()

        }


        val mItem:Model = items[position]
        imageView.setImageDrawable(mCtx.resources.getDrawable(mItem.img))
        titleTextView.text = mItem.title


        return view
    }
}