package com.fourthwall.googlemembersapi.client.domain.repository

import org.springframework.stereotype.Repository

@Repository
class MembersSubscriptionsRepository {

    private val subscriptions: MutableList<Subscription> = mutableListOf()

    fun saveSubscription(subscription: Subscription): Unit {
        subscriptions.add(subscription)
    }

    fun getUserSubscriptions(userName: String): List<Subscription> {
        return subscriptions.filter { it.userName == userName }
    }

}

data class Subscription(val userName: String, val userEmail: String, val subscribedChannel: String)