package ru.arcanite.friendsposts.friends

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.arcanite.friendsposts.R

class FriendsActivity : AppCompatActivity() {

    private var mFriendsViewModel: FriendsViewModel? = null
    private val users: List<User> = ArrayList(
        listOf(
            User("Kirill", "yandex.ru", "site1"),
            User("Irina", "mail.ru", "site2"),
        )
    )

    val adapter: Adapter = Adapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friends)

        val recyclerView: RecyclerView = findViewById(R.id.friends_list)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(baseContext)
        adapter.setData(users)
//        val observer: Observer<List<User>> = object : Observer<List<User>> {
//            override fun onChanged(users: List<User>) {
//
//            }
//        }

        //  mFriendsViewModel = ViewModelProvider(this).get(FriendsViewModel::class.java)
        //  mFriendsViewModel?.getUsers()?.observe(this, observer)


    }
}