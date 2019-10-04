package com.example.terasystemhrisv4.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.terasystemhrisv4.R
import com.example.terasystemhrisv4.util.inflate
import com.example.terasystemhrisv4.model.Logs
import com.example.terasystemhrisv4.ui.LogsViewHolder

class RecyclerAdapter(private val logs: ArrayList<Logs>) : RecyclerView.Adapter<RecyclerView.ViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogsViewHolder {
        val inflatedView = parent.inflate(R.layout.recyclerview_item_row, false)
        return LogsViewHolder(inflatedView)
    }

    override fun getItemCount() = logs.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemLogs = logs[position]
        val logsViewHolder = holder as LogsViewHolder
        logsViewHolder.bindLogs(itemLogs)
    }

}