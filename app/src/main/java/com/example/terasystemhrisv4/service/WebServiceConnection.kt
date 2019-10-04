package com.example.terasystemhrisv4.service

import android.annotation.SuppressLint
import android.os.AsyncTask
import com.example.terasystemhrisv4.interfaces.NetworkRequestInterface
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

@SuppressLint("StaticFieldLeak")
class WebServiceConnection(private var networkRequestInterface: NetworkRequestInterface) : AsyncTask<String, Int, String?>() {

    //val requestParam: HashMap<String, String>
    override fun onPreExecute() {
        super.onPreExecute()
        networkRequestInterface.beforeNetworkCall()
    }

    override fun doInBackground(vararg parts: String): String? {

        var result = ""
        val stringURL: String = parts[0]
        val reqParam: String = parts[1]
        val mURL = URL(stringURL)

        try {

            with(mURL.openConnection() as HttpURLConnection) {
                requestMethod = "POST"
                var inputLine: String?
                this.connectTimeout = 3000
                this.readTimeout = 3000
                    val wr = OutputStreamWriter(outputStream)
                    wr.write(reqParam)
                    wr.flush()

                    BufferedReader(InputStreamReader(inputStream)).use {
                        val response = StringBuffer()

                        inputLine = it.readLine()
                        while (inputLine != null) {
                            response.append(inputLine)
                            inputLine = it.readLine()

                        }
                        result = response.toString()
                        it.close()
                    }
                this.disconnect()
            }
        }
        catch (ex: java.net.ConnectException)
        {
            result = "Could not connect to the server"
        }
        catch (ex: java.net.SocketTimeoutException) {
            result = "Connection Timed Out"
        }
        catch (ex: Exception)
        {
            result = ex.message.toString()
        }

        return result
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        networkRequestInterface.afterNetworkCall(result)
    }

}