package ru.arcanite.friendsposts

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            Log.d(javaClass.simpleName,"savedInstanceState==null - lifeCycle")
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, StartScreenFragment(), "List")
                .commit()
        }
    }
}