package ru.arcanite.friendsposts.friends

import java.io.Serializable

class User : Serializable {
    private var name: String? = null
    private var email: String? = null
    private var website: String? = null

    constructor(name: String?, email: String?, webSite: String?) {
        this.name = name
        this.email = email
        this.website = website
    }

    override fun toString(): String {
        return "UserPlain(name=$name, email=$email, website=$website)"
    }


    fun getName(): String? {
        return name
    }

    fun getEmail(): String? {
        return email
    }

    fun getWebsite(): String? {
        return website
    }
}