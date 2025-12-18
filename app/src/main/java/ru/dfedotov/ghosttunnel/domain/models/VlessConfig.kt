package ru.dfedotov.ghosttunnel.domain.models

import android.net.Uri

data class VlessConfig(
    val uuid: String,
    val address: String,
    val port: Int,
    val sni: String? = null,
    val pbk: String? = null,
    val sid: String? = null,
    val fp: String? = "chrome",
    val remark: String = "GhostServer"
) {
    companion object {
        fun parseFromUrl(url: String): VlessConfig? {
            return try {
                val uri = Uri.parse(url)
                if (uri.scheme != "vless") return null

                VlessConfig(
                    uuid = uri.userInfo ?: "",
                    address = uri.host ?: "",
                    port = uri.port,
                    sni = uri.getQueryParameter("sni"),
                    pbk = uri.getQueryParameter("pbk"),
                    sid = uri.getQueryParameter("sid"),
                    fp = uri.getQueryParameter("fp") ?: "chrome",
                    remark = uri.fragment ?: "Imported"
                )
            } catch (e: Exception) {
                null
            }
        }
    }
}