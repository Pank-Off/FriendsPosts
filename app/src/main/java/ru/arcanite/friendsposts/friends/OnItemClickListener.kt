package ru.arcanite.friendsposts.friends

import ru.arcanite.friendsposts.User

interface OnItemClickListener {
    fun onClick(user: User)
}