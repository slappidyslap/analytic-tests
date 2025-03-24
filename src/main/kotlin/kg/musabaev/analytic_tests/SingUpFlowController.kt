package kg.musabaev.analytic_tests

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class SingUpFlowController {
    private val log = LoggerFactory.getLogger(SingUpFlowController::class.java)

    @GetMapping("/sing-up-flow")
    fun signUpFlowPage(): String {
        log.info("Requesting SingUpFlow")
        return "sign-up-flow"
    }
}