package com.events.gateway

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain

@EnableWebFluxSecurity
@Configuration
class SecurityConfig {

    @Bean
    fun springSecurityFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        return http

            .csrf { it.disable() }
            .authorizeExchange {
                it
                    .pathMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                    .pathMatchers("/api/public/**", "/auth/**").permitAll()
                    .anyExchange().authenticated()
            }
            .oauth2ResourceServer { resourceServer ->
                resourceServer.jwt { jwtSpec ->
                    jwtSpec
                }
            }
            .build()
    }
}