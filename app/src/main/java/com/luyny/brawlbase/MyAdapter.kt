package com.luyny.brawlbase

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.luyny.brawlbase.fragments.BrawlerDetailsFragment
import com.squareup.picasso.Picasso

class MyAdapter(private val brawlerList: List<Brawler>, private val supportFragmentManager: FragmentManager) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    class MyViewHolder(val viewHolder: CardView) : RecyclerView.ViewHolder(viewHolder)

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val cardView = LayoutInflater.from(parent.context)
            .inflate(R.layout.brawler_list_item, parent, false) as CardView
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

        textBrawlerName.text = brawler.name
        textBrawlerTrophies.text = brawler.trophies.toString()
        imageBrawlerPortrait.setImageResource(imgId)
//        Picasso.get().load(imgId).into(imageBrawlerPortrait)

        holder.viewHolder.setOnClickListener {
//            Toast.makeText(context,brawler.name, Toast.LENGTH_SHORT).show()

            supportFragmentManager.beginTransaction()
                .replace(R.id.frame_main, BrawlerDetailsFragment(brawler))
                .commit()

        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = brawlerList.size
}
