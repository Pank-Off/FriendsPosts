package ru.arcanite.friendsposts

import java.io.Serializable

class User(
    id: String?,
    private var name: String?,
    private var email: String?,
    private var website: String?
) : Serializable {
    private var titleList: ArrayList<String?> = ArrayList()
    private var bodyList: ArrayList<String?> = ArrayList()
    private var userId: String? = id
    private var isExpanded: Boolean = false

    fun setExpanded(expanded: Boolean) {
        isExpanded = expanded
    }

    fun isExpanded(): Boolean {
        return isExpanded
    }

    fun setPosts(title: String?, posts: String?) {
        titleList.add(title)
        bodyList.add(posts)
    }

    fun getTitleList() = titleList
    fun getBodyList() = bodyList

    fun getName(): String? = name

    fun getEmail(): String? = email

    fun getWebsite(): String? = website

    override fun toString(): String {
        return "User(name=$name, email=$email, website=$website, titleList=$titleList, bodyList=$bodyList, userId=$userId, isExpanded=$isExpanded)"
    }
}