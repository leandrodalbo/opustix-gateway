package com.events.gateway

import org.springframework.cloud.gateway.filter.GlobalFilter
import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import org.springframework.web.server.ServerWebExchange


@Configuration
class JwtHeaderFilterConfig {

    @Bean
    @Order(Ordered.LOWEST_PRECEDENCE)
    fun jwtHeaderFilter(): GlobalFilter {
        return GlobalFilter { exchange: ServerWebExchange, chain: GatewayFilterChain ->

            exchange.getPrincipal<JwtAuthenticationToken>()
                .flatMap { principal ->

                    val name = extractName(principal)
                    val familyName = extractFamilyName(principal)
                    val email = extractEmail(principal)
                    val roles = extractRoles(principal)?.joinToString(",") ?: ""


                    val mutatedRequest = exchange.request.mutate()
                        .header("X-Name", name)
                        .header("X-FamilyName", familyName)
                        .header("X-Email", email)
                        .header("X-Roles", roles)
                        .build()

                    chain.filter(exchange.mutate().request(mutatedRequest).build())
                }
                .switchIfEmpty(chain.filter(exchange))
        }
    }

    private fun extractRoles(jwt: JwtAuthenticationToken) =
        (jwt.token.getClaim("realm_access") as? Map<*, *>)?.get("roles") as? List<*>

    private fun extractEmail(jwt: JwtAuthenticationToken) = jwt.token.getClaim("email") ?: ""
    private fun extractName(jwt: JwtAuthenticationToken) = jwt.token.getClaim("name") ?: ""
    private fun extractFamilyName(jwt: JwtAuthenticationToken) = jwt.token.getClaim("family_name") ?: ""


}
