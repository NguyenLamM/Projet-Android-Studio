package com.example.projet

import android.graphics.pdf.PdfDocument
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_page_list.view.*
import okhttp3.*
import java.io.IOException

class PageList : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v: View = inflater.inflate(R.layout.fragment_page_list, container, false)

        v.jeux_list.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        getGames({ games ->
            this.activity?.runOnUiThread {
                v.jeux_list.adapter = GamesAdapter(games) { game, isOdd ->

                    val bundle = Bundle()
                    bundle.putString("name", game.name)
                    bundle.putString("description", game.description)
                    bundle.putString("image", game.img)
                    bundle.putString("url", game.link)

                    val frag = PageDetail()
                    frag.arguments = bundle

                    this.activity!!.supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frameContainer, frag)
                        .addToBackStack(null)
                        .commit()

                }
            }
        }, {

        })
        return v;
    }

    fun getGames(success: (gameList: Array<Jeux>) -> Unit, failure: () -> Unit) {

        val request = Request.Builder().url("https://my-json-server.typicode.com/bgdom/cours-android/games").build()
        OkHttpClient().newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                when (response.code) {
                    200 -> success(Gson().fromJson(response.body?.string(), Array<Jeux>::class.java))
                    else -> failure()
                }
            }
            override fun onFailure(call: Call, e: IOException) {
                failure()
            }
        })
    }
}
