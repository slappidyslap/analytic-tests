package kg.musabaev.analytic_tests

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class PurchaseController {

    private val log = LoggerFactory.getLogger(PurchaseController::class.java)

    @Value("\${MP_GA4_API_KEY}")
    private lateinit var mpGa4ApiKey: String

    @GetMapping("/purchase-via-application-flow")
    fun getPurchaseViaApplicationPageByStep(@Param("step") step: Int, model: Model): String {
        log.info("purchase-via-application-flow?step=$step")
        val nextStepLabelText = when (step) {
            1 -> "Анкета"
            2 -> "Конкретный психолог"
            3 -> "Регистрация"
            4 -> "Покупка"
            else -> throw RuntimeException("There is now such step")
        }
        val ga4EventName = when (step) {
            1 -> "application_completed"
            2 -> "select_item"
            3 -> "sign_up"
            4 -> "purchase"
            else -> throw RuntimeException("There is now such step")
        }

        model.apply {
            addAttribute("step", step)
            addAttribute("flow", "Анкета")
            addAttribute("curFlowLabelText", "анкету")
            addAttribute("mpGa4ApiKey", mpGa4ApiKey.substring(7))
            addAttribute("curStepUri", "/purchase-via-application-flow?step=$step")
            addAttribute("nextStepUri", "/purchase-via-application-flow?step=${step + 1}")
            addAttribute("nextStepLabelText", nextStepLabelText)
            addAttribute("ga4EventName", ga4EventName)
        }

        return "purchase-flow"
    }

    @GetMapping("/purchase-via-catalog-flow")
    fun getPurchaseViaCatalogPageByStep(@Param("step") step: Int, model: Model): String {
        log.info("purchase-via-catalog-flow?step=$step")
        val nextStepLabelText = when (step) {
            1 -> "Конкретный продукт"
            2 -> "Регистрация"
            3 -> "Покупка"
            else -> throw RuntimeException("There is now such step")
        }
        val ga4EventName = when (step) {
            1 -> "select_item"
            2 -> "sign_up"
            3 -> "purchase"
            else -> throw RuntimeException("There is now such step")
        }

        model.apply {
            addAttribute("step", step)
            addAttribute("flow", "Каталог")
            addAttribute("curFlowLabelText", "каталог")
            addAttribute("mpGa4ApiKey", mpGa4ApiKey.substring(7))
            addAttribute("curStepUri", "/purchase-via-catalog-flow?step=$step")
            addAttribute("nextStepUri", "/purchase-via-catalog-flow?step=${step + 1}")
            addAttribute("nextStepLabelText", nextStepLabelText)
            addAttribute("ga4EventName", ga4EventName)
        }

        return "purchase-flow"
    }

    @GetMapping("/purchase-completed")
    fun purchaseCompletedPage(): String {
        log.info("purchase-completed")
        return "purchase-completed"
    }
}