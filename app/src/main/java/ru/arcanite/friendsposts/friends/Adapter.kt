package ru.arcanite.friendsposts.friends

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.arcanite.friendsposts.R
import ru.arcanite.friendsposts.User

class Adapter : RecyclerView.Adapter<Adapter.FriendsViewHolder>() {

    private var mUsersData: List<User> = ArrayList()
    private var listener: OnItemClickListener? = null

    fun attachListener(onItemClickListener: OnItemClickListener) {
        listener = onItemClickListener
    }

    fun setData(usersData: List<User>) {
        mUsersData = usersData
        notifyDataSetChanged()
    }

    class FriendsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val postsAdapter: PostsAdapter = PostsAdapter()
        private val mName: TextView = itemView.findViewById(R.id.name)
        private val mEmail: TextView = itemView.findViewById(R.id.email)
        private val recyclerView: RecyclerView = itemView.findViewById(R.id.posts_list)
        val expandableLayout: LinearLayout = itemView.findViewById(R.id.expandableLayout)

        init {
            recyclerView.adapter = postsAdapter
            recyclerView.layoutManager = LinearLayoutManager(itemView.context)
        }

        fun onBind(user: User) {
            mEmail.text = user.getEmail()
            mName.text = user.getName()
            Log.d(javaClass.simpleName, user.toString())
            postsAdapter.setData(user.getBodyList(), user.getTitleList())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendsViewHolder {
        return FriendsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: FriendsViewHolder, position: Int) {
        val user: User = mUsersData[position]
        holder.onBind(user)
        holder.itemView.setOnClickListener {
            listener?.onClick(user)
            notifyItemChanged(position)
        }
        val isExpanded = user.isExpanded()
        holder.expandableLayout.visibility = if (isExpanded) View.VISIBLE else View.GONE
    }

    override fun getItemCount(): Int = mUsersData.size
}