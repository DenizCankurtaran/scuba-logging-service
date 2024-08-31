package com.example.scuba.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "test")
data class ConfigProperties(
    var hello: String = "",
)