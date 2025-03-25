package kg.musabaev.analytic_tests

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class PurchaseController {

    private val log = LoggerFactory.getLogger(PurchaseController::class.java)

    @Value("\${MP_GA4_API_KEY}")
    private lateinit var mpGa4ApiKey: String

    @GetMapping("/purchase-flow")
    fun purchaseFlowPage(model: Model): String {
        log.info("PurchaseFlow")
        model.addAttribute("mpGa4ApiKey", mpGa4ApiKey.substring(7))
        return "purchase-flow"
    }
}