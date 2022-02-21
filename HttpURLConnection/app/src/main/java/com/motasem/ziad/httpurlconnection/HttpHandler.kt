package com.motasem.ziad.httpurlconnection

import android.util.Log
import java.io.*
import java.net.HttpURLConnection
import java.net.URL

class HttpHandler {

    companion object {
        fun makeServiceCall(reqUrl: String?): String {
            lateinit var response: String
            try {
                val url = URL(reqUrl)
                val conn: HttpURLConnection = url.openConnection() as HttpURLConnection
                conn.requestMethod = "GET"
                val input: InputStream = BufferedInputStream(conn.inputStream)
                response = convertStreamToString(input)
                val statusCode = conn.responseCode
                Log.e("mzn", statusCode.toString())
            } catch (e: Exception) {
                Log.e("mzn", "Exception: " + e.message)
            }
            return response
        }

        private fun convertStreamToString(input: InputStream): String {
            val reader = BufferedReader(InputStreamReader(input))
            val sb = StringBuilder()
            var line: String?
            try {
                //also { line = it } as  line = reader.readLine()
                while (reader.readLine().also { line = it } != null) {
                    sb.append(line).append('\n')
                }
            } catch (e: IOException) {
                e.printStackTrace()
            } finally {
                try {
                    input.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
            return sb.toString()
        }
    }


}
