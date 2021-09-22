package com.example.reforiterestapidemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    var post : List<PostModel> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_layout , parent , false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        holder.postId.text = post.get(position).id
        holder.postTitle.text = post.get(position).title
    }

    override fun getItemCount(): Int {
        return post.size
    }
    fun submitList(blogList: List<PostModel>){
        post = blogList
    }
    inner class ViewHolder(itemViw : View): RecyclerView.ViewHolder(itemViw){
        var postId: TextView
        var postTitle: TextView
        init {
            postId = itemViw.findViewById(R.id.post_id)
            postTitle = itemViw.findViewById(R.id.post_title)
            /* itemViw.setOnClickListener{
                 val position : Int = adapterPosition
                 Toast.makeText(itemViw.context , "Yow click on ${title[position]}" , Toast.LENGTH_LONG).show()
             }*/
        }

    }
}