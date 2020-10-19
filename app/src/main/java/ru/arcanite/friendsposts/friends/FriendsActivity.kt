package ru.arcanite.friendsposts.friends

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.arcanite.friendsposts.ListFragment
import ru.arcanite.friendsposts.R
import ru.arcanite.friendsposts.User

class FriendsActivity : AppCompatActivity() {

    private var users: List<User> = ArrayList()
    private val adapter: Adapter = Adapter()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friends)

        users = intent.getSerializableExtra(ListFragment.EXTRA_USERS) as List<User>
        for (u in users) {
            Log.d(javaClass.simpleName, u.toString())
        }
        val recyclerView: RecyclerView = findViewById(R.id.friends_list)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(baseContext)
        adapter.setData(users)
        adapter.attachListener(object : OnItemClickListener {
            override fun onClick(user: User) {
                Toast.makeText(baseContext, user.getName(), Toast.LENGTH_SHORT).show()
                val url = user.getWebsite()
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://${url}"))
                startActivity(intent)
            }
        })
    }
}