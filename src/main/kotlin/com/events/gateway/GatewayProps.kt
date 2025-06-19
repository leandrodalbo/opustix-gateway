package com.events.gateway

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "events-gateway")
class GatewayProps(val allowedOrigins: List<String>)