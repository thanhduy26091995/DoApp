package com.duybui.doapp.ui.lists

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.duybui.doapp.R
import com.duybui.doapp.data.model.Event
import com.duybui.doapp.data.model.EventStatus
import kotlinx.android.synthetic.main.item_home.view.tvEventName
import kotlinx.android.synthetic.main.item_list.view.*

class ListAdapter(val activity: Activity) : RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    private var eventList = arrayListOf<Event>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val itemView = activity.layoutInflater.inflate(R.layout.item_list, parent, false)
        return ListViewHolder(itemView)
    }

    fun setEventList(events: List<Event>) {
        eventList.clear()
        eventList.addAll(events)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return eventList.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        var event = eventList.get(position)
        holder.tvEventName.text = event.title
        holder.ckEvent.isChecked = event.eventStatus == EventStatus.COMPLETED
        holder.ckEvent.setOnCheckedChangeListener { buttonView, isChecked ->
            if (buttonView.isPressed){
                if (isChecked) {
                    event.eventStatus = EventStatus.COMPLETED
                } else {
                    event.eventStatus = EventStatus.OVERDUE
                }
                notifyItemChanged(position)
            }
        }
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvEventName = itemView.tvEventName
        val ckEvent = itemView.ckEvent
    }
}