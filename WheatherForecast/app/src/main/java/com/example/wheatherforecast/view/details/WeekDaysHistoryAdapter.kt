package com.example.wheatherforecast.view.details

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.wheatherforecast.R
import com.example.wheatherforecast.databinding.ItemWeekDayBinding
import com.example.wheatherforecast.model.details.WheatherDaysDataModel
import com.example.wheatherforecast.viewmodel.details.WeekDaysHistoryItemViewModel


class WeekDaysHistoryAdapter(
    var daysListModel: ArrayList<WheatherDaysDataModel>,
    var context: Context
) :
    RecyclerView.Adapter<WeekDaysHistoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_week_day, parent, false
        )
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setViewModel(
            WeekDaysHistoryItemViewModel(
                context,
                daysListModel[position]
            )
        )
    }

    override fun getItemCount(): Int {
        return daysListModel.size
    }

    override fun onViewAttachedToWindow(holder: ViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.bind()
    }

    override fun onViewDetachedFromWindow(holder: ViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.unbind()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding: ItemWeekDayBinding? = null

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

        fun setViewModel(viewModel: WeekDaysHistoryItemViewModel) {
            if (binding != null) {
                binding!!.viewModel = viewModel
            }
        }
    }

}