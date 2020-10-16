package ru.arcanite.friendsposts.friends

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.arcanite.friendsposts.R

class Adapter : RecyclerView.Adapter<Adapter.FriendsViewHolder>() {
    private var mData: List<User> = ArrayList()

    fun setData(data: List<User>) {
        mData = data
        notifyDataSetChanged()
    }

    class FriendsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mName: TextView = itemView.findViewById(R.id.name)
        val mEmail: TextView = itemView.findViewById(R.id.email)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendsViewHolder {
        return FriendsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: FriendsViewHolder, position: Int) {
        val user: User = mData[position]
        holder.mEmail.text = user.getEmail()
        holder.mName.text = user.getName()
    }

    override fun getItemCount(): Int = mData.size

}