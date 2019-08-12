package com.duybui.doapp.ui.home

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.duybui.doapp.R
import com.duybui.doapp.data.model.Event
import kotlinx.android.synthetic.main.item_home.view.*

class HomeAdapter(val activity: Activity) : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    private var eventList = arrayListOf<Event>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val itemView = activity.layoutInflater.inflate(R.layout.item_home, parent, false)
        return HomeViewHolder(itemView)
    }

    fun setEventList(events: List<Event>) {
        eventList.clear()
        eventList.addAll(events)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
       return eventList.size
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val event = eventList.get(position)
        holder.tvEventName.text = event.title
        holder.tvEventType.text = event.description
        holder.tvEventTime.text = "All day"
    }

    class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvEventName = itemView.tvEventName
        val tvEventType = itemView.tvEventType
        val tvEventTime = itemView.tvEventTime

    }
}