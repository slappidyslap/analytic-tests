package kg.musabaev.analytic_tests

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.web.client.RestClient

// Measurement Protocol Google Analytics 4 Service
class MpGa4Service {

    @Value("\${MP_GA4_API_KEY}")
    private lateinit var mpGa4ApiKey: String
    @Value("\${GA4_CONTAINER_ID}")
    private lateinit var ga4ContainerId: String

    private lateinit var httpSessionService: HttpSessionService

    private val restClient = RestClient.create("www.google-analytics.com/mp/collect?api_secret=$mpGa4ApiKey&measurement_id=$ga4ContainerId")
    private val log = LoggerFactory.getLogger(MpGa4Service::class.java)

    fun sendPurchaseEvent(rawGa4ClientId: String) {
        val clientId = rawGa4ClientId.substring(7)
        val eventParams = httpSessionService.getSessionByClientId(clientId)
        val timestampMicros = System.currentTimeMillis() * 1000
        val nonPersonalizedAds = false
        val cs: String = eventParams["utm_source"]!!
        val cm: String = eventParams["utm_medium"]!!
        val cn: String = eventParams["utm_campaign"]!!
        val ck: String = eventParams["utm_keyword"]!!

        val payload = MpGa4JsonPayload(
            clientId,
            clientId,
            timestampMicros,
            nonPersonalizedAds,
            listOf(
                MpGa4JsonPayload.Event("purchase_dwh", eventParams)
            ),
            cs,
            cm,
            cn,
            ck)

        val res = restClient
            .post()
            .contentType(MediaType.APPLICATION_JSON)
            .body(payload)
            .retrieve()
            .toEntity(String::class.java)

        log.info(res.toString())
        log.info(res.body)
    }

}