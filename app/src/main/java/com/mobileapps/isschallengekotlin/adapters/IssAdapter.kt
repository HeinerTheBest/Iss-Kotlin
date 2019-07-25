package com.mobileapps.isschallengekotlin.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobileapps.isschallengekotlin.R
import com.mobileapps.isschallengekotlin.models.Response
import kotlinx.android.synthetic.main.item_response.view.*
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

class IssAdapter(val responses : List<Response?>) : RecyclerView.Adapter<IssAdapter.ViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_response,parent,false))

    override fun getItemCount() = responses.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.setValues(responses[position])

    class ViewHolder( itemView: View ) : RecyclerView.ViewHolder(itemView)
    {
        fun setValues (response : Response?)
        {
           itemView.tvDuration.text = "Duration: " + response?.duration.toString() + " Seconds"
           itemView.tvDate.text     = "Time: "+ convertToDate(response?.risetime.toString())
        }

        private fun convertToDate(string: String): String
        {
            val ts = Timestamp(string.toLong())
            val date = Date(ts.time)
            return date.toString()
        }
    }
}


