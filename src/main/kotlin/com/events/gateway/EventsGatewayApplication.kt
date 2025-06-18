package com.events.gateway

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class EventsGatewayApplication

fun main(args: Array<String>) {
    runApplication<EventsGatewayApplication>(*args)
}
