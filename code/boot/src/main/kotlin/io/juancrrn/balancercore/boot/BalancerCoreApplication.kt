package io.juancrrn.balancercore.boot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["io.juancrrn"])
class BalancerCoreApplication

fun main(args: Array<String>) {
    runApplication<BalancerCoreApplication>(*args)
}
