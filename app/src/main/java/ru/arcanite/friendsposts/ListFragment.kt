package ru.arcanite.friendsposts

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.arcanite.friendsposts.friends.FriendsActivity
import java.io.Serializable

class ListFragment : Fragment() {

    companion object {
        const val EXTRA = "LIST_EXTRA"
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

        val clickButton: Button = view.findViewById(R.id.click_btn)
        mListViewModel?.getProgress()?.observe(viewLifecycleOwner,
            { requestState ->

                when (requestState) {
                    ListViewModel.RequestState.FAILED -> {
                        Toast.makeText(context, "Request error", Toast.LENGTH_SHORT).show()
                        clickButton.isEnabled = true
                    }
                    ListViewModel.RequestState.IN_PROGRESS -> {
                        Toast.makeText(context, "Request in progress", Toast.LENGTH_SHORT).show()
                        clickButton.isEnabled = false
                    }
                    ListViewModel.RequestState.SUCCESS -> {
                        Toast.makeText(context, "Request success", Toast.LENGTH_SHORT).show()
                        clickButton.isEnabled = true
                    }
                }
            }
        )

        mListViewModel?.getUserList()?.observe(viewLifecycleOwner, { users ->
            val intent = Intent(activity, FriendsActivity::class.java)
            intent.putExtra(EXTRA, users as Serializable)
            startActivity(intent)
        })

        clickButton.setOnClickListener {
            mListViewModel?.getRequest()
        }
    }
}

