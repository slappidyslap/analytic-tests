package kg.musabaev.analytic_tests

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class PurchaseController {
    private val log = LoggerFactory.getLogger(PurchaseController::class.java)

    @GetMapping("/purchase-flow")
    fun purchaseFlowPage(): String {
        log.info("PurchaseFlow")
        return "purchase-flow"
    }
}