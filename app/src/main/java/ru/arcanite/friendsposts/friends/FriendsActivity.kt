package ru.arcanite.friendsposts.friends

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.arcanite.friendsposts.ListFragment
import ru.arcanite.friendsposts.R
import ru.arcanite.friendsposts.network.UserApi

class FriendsActivity : AppCompatActivity() {

    private var mFriendsViewModel: FriendsViewModel? = null
    private var users: List<UserApi.UserPlain> = ArrayList()

    val adapter: Adapter = Adapter()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friends)
        users = intent.getSerializableExtra(ListFragment.EXTRA) as List<UserApi.UserPlain>
        val recyclerView: RecyclerView = findViewById(R.id.friends_list)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(baseContext)
        adapter.setData(users)
        adapter.attachListener(object : OnItemClickListener {
            override fun onClick(user: UserApi.UserPlain, itemView: View) {
                Toast.makeText(baseContext, user.getName(), Toast.LENGTH_SHORT).show()
            }
        })
//        val observer: Observer<List<User>> = object : Observer<List<User>> {
//            override fun onChanged(users: List<User>) {
//
//            }
//        }

        //  mFriendsViewModel = ViewModelProvider(this).get(FriendsViewModel::class.java)
        //  mFriendsViewModel?.getUsers()?.observe(this, observer)


    }
}