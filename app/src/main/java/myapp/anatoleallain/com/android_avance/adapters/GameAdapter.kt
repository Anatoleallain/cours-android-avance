package myapp.anatoleallain.com.android_avance.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_list.view.*
import myapp.anatoleallain.com.android_avance.Game
import myapp.anatoleallain.com.android_avance.R


/**
 * Created by allai on 31/10/2019.
 */
class GameAdapter(private val gameList: ArrayList<Game>, var mListener: (Int) -> Unit) :
        RecyclerView.Adapter<GameAdapter.GameViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
        GameViewHolder {
            val viewHolder = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_list, parent, false)
            return GameViewHolder(viewHolder, mListener)
        }

        override fun getItemCount(): Int = gameList.size

        override fun onBindViewHolder(holder: GameViewHolder, position: Int) = holder.bind(gameList[position])

        class GameViewHolder(itemView: View, var mListener: (Int) -> Unit) :
                RecyclerView.ViewHolder(itemView) {
            private val textView: TextView =
                    itemView.findViewById(R.id.textViewA)


            fun bind(game: Game) {
                textView.text = game.name
                Picasso.get().load(game.imgUrl).into(itemView.imageViewA)

                itemView.setOnClickListener {
                    mListener(game.id)
                }
            }


        }
    }



