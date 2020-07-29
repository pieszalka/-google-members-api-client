package com.fourthwall.googlemembersapi.client.domain.repository

import arrow.core.extensions.list.foldable.exists
import org.springframework.stereotype.Repository

@Repository
class ChannelMembershipsRepository {

    private val memberships: HashMap<String, List<String>> = HashMap()

    fun saveMemberships(creatorChannelId: String, membershipsChannels: List<String>): Unit {
        memberships.put(creatorChannelId, membershipsChannels)
    }

    fun getSubscribedChannels(subscriberChannels: List<String>): List<String> {
        return memberships.filter { (_: String, membershipsChannels: List<String>) ->
            subscriberChannels.exists { membership -> membershipsChannels.contains(membership) }
        }.keys.toList()
    }

    fun getAllMemberships(): HashMap<String, List<String>> {
        return memberships
    }
}