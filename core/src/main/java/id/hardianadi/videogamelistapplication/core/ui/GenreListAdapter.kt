package id.hardianadi.videogamelistapplication.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.hardianadi.videogamelistapplication.core.R
import id.hardianadi.videogamelistapplication.core.domain.model.Genre
import kotlinx.android.synthetic.main.item_platform_item.view.*

/**
 * @author hardiansyah (hardiansyah.adi@gmail.com)
 * @date 29/09/2020
 */
class GenreListAdapter : RecyclerView.Adapter<GenreListAdapter.GenreViewHolder>() {

    private var listData = ArrayList<Genre>()
    fun setData(newListData: List<Genre>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder =
        GenreViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_platform_item, parent, false)
        )

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listData.size

    inner class GenreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView.rootView) {
        fun bind(data: Genre) {
            with(itemView) {
                tvPlatformName.text = data.genreName
            }
        }
    }

}