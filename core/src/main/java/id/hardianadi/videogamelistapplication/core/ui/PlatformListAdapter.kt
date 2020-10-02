package id.hardianadi.videogamelistapplication.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.hardianadi.videogamelistapplication.core.R
import id.hardianadi.videogamelistapplication.core.domain.model.Platform
import kotlinx.android.synthetic.main.item_platform_item.view.*

/**
 * @author hardiansyah (hardiansyah.adi@gmail.com)
 * @date 29/09/2020
 */
class PlatformListAdapter : RecyclerView.Adapter<PlatformListAdapter.PlatformViewHolder>() {

    private var listData = ArrayList<Platform>()
    fun setData(newListData: List<Platform>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlatformViewHolder =
        PlatformViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_platform_item, parent, false)
        )

    override fun onBindViewHolder(holder: PlatformViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listData.size

    inner class PlatformViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView.rootView) {
        fun bind(data: Platform) {
            with(itemView) {
                tvPlatformName.text = data.platformName
            }
        }
    }

}