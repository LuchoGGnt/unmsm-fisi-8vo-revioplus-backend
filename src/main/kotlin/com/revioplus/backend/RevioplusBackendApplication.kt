package com.revioplus.backend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RevioplusBackendApplication

fun main(args: Array<String>) {
	runApplication<RevioplusBackendApplication>(*args)
}