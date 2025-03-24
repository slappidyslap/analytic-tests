package kg.musabaev.analytic_tests

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HomeController {
    private val log = LoggerFactory.getLogger(HomeController::class.java)

    @GetMapping("/")
    fun homePage(): String {
        return "home"
    }
}