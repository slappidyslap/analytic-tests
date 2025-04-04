package kg.musabaev.analytic_tests

class HttpSessionService {

    private val sessions: MutableMap<String, Map<String, String>> = mutableMapOf()

    fun saveSession(rawGa4ClientId: String, allUrlParams: Map<String, String>) {
        val clientId = rawGa4ClientId.substring(7)
        sessions[clientId] = allUrlParams
    }

    fun getSessionByClientId(clientId: String) = sessions[clientId]!!
}