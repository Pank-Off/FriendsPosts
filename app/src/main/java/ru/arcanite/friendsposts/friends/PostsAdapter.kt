package ru.arcanite.friendsposts.friends

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.arcanite.friendsposts.R

class PostsAdapter : RecyclerView.Adapter<PostsAdapter.PostsViewHolder>() {

    private var mBodyList: ArrayList<String?> = ArrayList()
    private var mTitleList: ArrayList<String?> = ArrayList()

    fun setData(bodyList: ArrayList<String?>, titleList: ArrayList<String?>) {
        mBodyList = bodyList
        mTitleList = titleList
        notifyDataSetChanged()
    }

    class PostsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mTitle: TextView = itemView.findViewById(R.id.title)
        val mBody: TextView = itemView.findViewById(R.id.body)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        return PostsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        holder.mTitle.text = mTitleList[position]
        holder.mBody.text = mBodyList[position]
    }

    override fun getItemCount(): Int = mTitleList.size
}