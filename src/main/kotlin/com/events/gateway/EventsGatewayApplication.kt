package com.events.gateway

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class EventsGatewayApplication

fun main(args: Array<String>) {
    runApplication<EventsGatewayApplication>(*args)
}
