package ru.arcanite.friendsposts

import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.arcanite.friendsposts.friends.FriendsActivity
import java.io.Serializable

class ListFragment : Fragment() {

    companion object {
        const val EXTRA_USERS = "LIST_EXTRA_USERS"
    }

    private var mListViewModel: ListViewModel? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mListViewModel = activity?.let { ViewModelProvider(it) }?.get(ListViewModel::class.java)

        val intent = Intent(activity, FriendsActivity::class.java)
        val clickButton: Button = view.findViewById(R.id.click_btn)
        val progressBar: ProgressBar = view.findViewById(R.id.progress_bar)
        mListViewModel?.getProgress()?.observe(viewLifecycleOwner,
            { requestState ->
                when (requestState) {
                    ListViewModel.RequestState.FAILED -> {
                        Toast.makeText(context, "Request error", Toast.LENGTH_SHORT).show()
                        clickButton.text = getString(R.string.refresh)
                        progressBar.visibility = ProgressBar.INVISIBLE
                        clickButton.isEnabled = true
                    }
                    ListViewModel.RequestState.IN_PROGRESS -> {
                        Toast.makeText(context, "Request in progress", Toast.LENGTH_SHORT).show()
                        progressBar.visibility = ProgressBar.VISIBLE
                        clickButton.isEnabled = false
                    }
                    ListViewModel.RequestState.SUCCESS -> {
                        Toast.makeText(context, "Request success", Toast.LENGTH_SHORT).show()
                        mListViewModel?.getUser()?.observe(viewLifecycleOwner, { users ->
                            intent.putExtra(EXTRA_USERS, users as Serializable)
                        })
                        startActivity(intent)
                        progressBar.visibility = ProgressBar.INVISIBLE
                        clickButton.text = getString(R.string.click)
                        clickButton.isEnabled = true
                    }
                }
            }
        )
        clickButton.setOnClickListener {
            mListViewModel?.getRequest()
        }
    }
}

