package com.github.hfantin.authserver.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
class UserController {
    @RequestMapping("/user")
    fun user(user: Principal) = user
}