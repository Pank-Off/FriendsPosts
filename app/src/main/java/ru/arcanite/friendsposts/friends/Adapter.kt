package ru.arcanite.friendsposts.friends

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.arcanite.friendsposts.R
import ru.arcanite.friendsposts.network.UserApi

class Adapter : RecyclerView.Adapter<Adapter.FriendsViewHolder>() {
    private var mData: List<UserApi.UserPlain> = ArrayList()
    private var listener: OnItemClickListener? = null

    fun attachListener(onItemClickListener: OnItemClickListener) {
        listener = onItemClickListener
    }

    fun setData(data: List<UserApi.UserPlain>) {
        mData = data
        notifyDataSetChanged()
    }

    class FriendsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mName: TextView = itemView.findViewById(R.id.name)
        val mEmail: TextView = itemView.findViewById(R.id.email)
        val expandableLayout: FrameLayout = itemView.findViewById(R.id.expandableLayout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendsViewHolder {
        return FriendsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: FriendsViewHolder, position: Int) {
        val user: UserApi.UserPlain = mData[position]
        holder.mEmail.text = user.getEmail()
        holder.mName.text = user.getName()
        holder.itemView.setOnClickListener {
            user.setExpanded(!user.isExpanded())
            notifyItemChanged(position)
        }
        val isExpanded = user.isExpanded()
        holder.expandableLayout.visibility = if (isExpanded) View.VISIBLE else View.GONE
        //   holder.itemView.setOnClickListener { listener?.onClick(user, holder.itemView) }
    }

    override fun getItemCount(): Int = mData.size

}