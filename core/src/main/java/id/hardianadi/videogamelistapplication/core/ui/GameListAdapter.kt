package id.hardianadi.videogamelistapplication.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import id.hardianadi.videogamelistapplication.core.R
import id.hardianadi.videogamelistapplication.core.domain.model.Game
import id.hardianadi.videogamelistapplication.core.util.UtilFunction
import kotlinx.android.synthetic.main.item_game_list.view.*
import java.util.*

/**
 * @author hardiansyah (hardiansyah.adi@gmail.com)
 * @date 28/09/2020
 */
class GameListAdapter : RecyclerView.Adapter<GameListAdapter.GameViewHolder>() {

    private var listData = ArrayList<Game>()
    var onItemClick: ((Game) -> Unit)? = null

    fun setData(newListData: List<Game>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        GameViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_game_list, parent, false)
        )

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class GameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(data: Game) {
            with(itemView) {
                tvItemName.text = data.name
                tvItemReleased.text = UtilFunction.stringDateFormat(data.released)

                Glide.with(this.context)
                    .load(data.backgroundImage)
                    .apply(
                        RequestOptions().override(350, 550)
                    )
                    .into(imgItemBackground)
            }
        }

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}