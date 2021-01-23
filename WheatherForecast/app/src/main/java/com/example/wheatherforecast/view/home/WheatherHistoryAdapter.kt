package com.example.wheatherforecast.view.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.wheatherforecast.R
import com.example.wheatherforecast.databinding.ItemWheatherHistoryBinding
import com.example.wheatherforecast.model.home.PlaceResultModel
import com.example.wheatherforecast.viewmodel.home.WheatherHistoryItemViewModel


class WheatherHistoryAdapter(
    private var wheatherHistoryListener: WheatherHistoryListener,
    var historyItemList: ArrayList<PlaceResultModel>,
    var context: Context
) :
    RecyclerView.Adapter<WheatherHistoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_wheather_history, parent, false
        )
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setViewModel(
            WheatherHistoryItemViewModel(
                context,
                wheatherHistoryListener,
                historyItemList[position]
            )
        )
    }

    override fun getItemCount(): Int {
        return historyItemList.size
    }

    override fun onViewAttachedToWindow(holder: ViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.bind()
    }

    override fun onViewDetachedFromWindow(holder: ViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.unbind()
    }

    fun addData(dataItem: PlaceResultModel) {
        historyItemList.add(0, dataItem)
        notifyDataSetChanged()
    }

    fun updateData(list: ArrayList<PlaceResultModel>) {
        historyItemList = list
        notifyDataSetChanged()
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding: ItemWheatherHistoryBinding? = null

        init {
            bind()
        }

        fun bind() {
            if (binding == null) {
                binding = DataBindingUtil.bind(itemView)
                binding!!.executePendingBindings()
            }
        }

        fun unbind() {
            if (binding != null) {
                binding!!.unbind()
            }
        }

        fun setViewModel(viewModel: WheatherHistoryItemViewModel) {
            if (binding != null) {
                binding!!.viewModel = viewModel
            }
        }
    }

}