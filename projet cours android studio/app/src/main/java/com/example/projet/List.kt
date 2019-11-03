package com.example.projet

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView

public class List(val context: Context, val list: ArrayList<Jeux>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view: View = LayoutInflater.from(context).inflate(R.layout.row_layout, parent, false )

        val jeuxId = view.findViewById(R.id.jeux_id) as AppCompatTextView
        val jeuxName = view.findViewById(R.id.jeux_name) as AppCompatTextView
        val jeuxDescriptions = view.findViewById(R.id.jeux_descriptions) as AppCompatTextView
        val jeuxLink = view.findViewById(R.id.jeux_link) as AppCompatTextView
        //val jeuxImage = view.findViewById(R.id.jeux_img) as AppCompatImageView

        jeuxId.text = list[position].id.toString()
        jeuxName.text = list[position].name
        jeuxDescriptions.text = list[position].description
        jeuxLink.text = list[position].link

        return view

    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return list.size
    }


}