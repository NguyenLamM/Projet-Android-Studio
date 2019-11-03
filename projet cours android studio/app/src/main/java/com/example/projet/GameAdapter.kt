package com.example.projet

import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_layout.view.*



class GameViewHolder(val view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view)

class GamesAdapter(private val gameList: Array<Jeux>, val click: (game: Jeux, isOdd: Boolean) -> Unit) :
    androidx.recyclerview.widget.RecyclerView.Adapter<GameViewHolder>() {

    override fun getItemCount(): Int {
        return gameList.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.row_layout, parent, false)
        return GameViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val game: Jeux = gameList[position]
        val isOdd = position % 2 == 0

        holder.view.jeux_name.text = game.name
        holder.view.jeux_descriptions.text = game.description
        Picasso.get().load(game.img).resize(500, 600).into(holder.view.jeux_img)

        holder.view.button_details.setOnClickListener { click(game, isOdd) }
    }
}