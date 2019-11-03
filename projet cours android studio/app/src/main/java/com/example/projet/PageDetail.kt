package com.example.projet


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_page_detail.view.*
import kotlinx.android.synthetic.main.row_layout.view.*
import android.content.Intent
import android.net.Uri
import android.webkit.WebViewClient
import android.content.SharedPreferences





class PageDetail : Fragment() {
    var pair: Boolean = true

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v: View = inflater.inflate(R.layout.fragment_page_detail, container, false)

        //val link_detail = arguments!!.getString("url")

        v.titre_detail.text = arguments!!.getString("name")
        v.description_detail.text = arguments!!.getString("description")
       // v.image_detail.= arguments!!.getString("image")
        v.link_detail.text= arguments!!.getString("url")

        val url = arguments!!.getString("url")

        // Navigateur
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)

        v.link_detail.setOnClickListener {
            if(pair) {
                startActivity(i)
                pair = false
            }
            else {
                val intent = Intent(this.context, Webactivity::class.java)
                intent.putExtra("url", url)
                startActivity(intent)
                pair = true
            }
        }

        Picasso.get().load(arguments!!.getString("image")).resize(500, 600).into(v.image_detail)

        return v
    }
}