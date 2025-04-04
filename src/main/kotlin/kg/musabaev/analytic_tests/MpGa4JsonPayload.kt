package kg.musabaev.analytic_tests

data class MpGa4JsonPayload(
    val clientId: String,
    val userId: String,
    val timestampMicros: Long,
    val nonPersonalizedAds: Boolean,
    val events: List<Event>,
    val cs: String,
    val cm: String,
    val cn: String,
    val ck: String
) {
    data class Event(
        val name: String,
        val params: Map<String, Any> // params как Map
    )
}
