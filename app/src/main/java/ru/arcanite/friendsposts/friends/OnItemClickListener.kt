package ru.arcanite.friendsposts.friends

import android.view.View
import ru.arcanite.friendsposts.network.UserApi

interface OnItemClickListener {
    fun onClick(user: UserApi.UserPlain, itemView: View)
}