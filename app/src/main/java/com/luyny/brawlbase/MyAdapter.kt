package com.luyny.brawlbase

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val brawlerList: List<Brawler>) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    class MyViewHolder(val viewHolder: CardView) : RecyclerView.ViewHolder(viewHolder)

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // create a new view
        val cardView = LayoutInflater.from(parent.context)
            .inflate(R.layout.brawler_list_item, parent, false) as CardView
        // set the view's size, margins, paddings and layout parameters
        //...
        return MyViewHolder(cardView)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val imageBrawlerPortrait =
            holder.viewHolder.findViewById<ImageView>(R.id.image_brawler_portrait)
        val textBrawlerName = holder.viewHolder.findViewById<TextView>(R.id.text_brawler_name)
        val textBrawlerTrophies =
            holder.viewHolder.findViewById<TextView>(R.id.text_brawler_trophies)

        val brawler = brawlerList[position]
        val id = brawler.id!!
        val context = imageBrawlerPortrait.context
        val imgId = context.resources.getIdentifier("b$id", "drawable", context.packageName)
        imageBrawlerPortrait.setImageResource(imgId)
        textBrawlerName.text = brawler.name
        textBrawlerTrophies.text = brawler.trophies.toString()

        holder.viewHolder.setOnClickListener {
            Toast.makeText(context,brawler.name, Toast.LENGTH_SHORT).show()
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = brawlerList.size
}
