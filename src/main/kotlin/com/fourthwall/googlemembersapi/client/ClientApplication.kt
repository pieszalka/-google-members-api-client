package com.fourthwall.googlemembersapi.client

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class ClientApplication : SpringBootServletInitializer()

fun main(args: Array<String>) {
	runApplication<ClientApplication>(*args)
}
