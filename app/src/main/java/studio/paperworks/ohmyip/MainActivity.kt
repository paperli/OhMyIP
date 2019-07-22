package studio.paperworks.ohmyip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.net.Inet4Address
import java.net.InetAddress
import java.net.NetworkInterface
import java.net.SocketException
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ip = getIpAddressString()
        messageTextView.text = ip
    }

    fun getIpAddressString(): String {
        try {
            val enNetI: Enumeration<NetworkInterface> = NetworkInterface.getNetworkInterfaces()
            for (netI in enNetI) {
                val enumIpAddr: Enumeration<InetAddress> = netI.inetAddresses
                for (inetAddress in enumIpAddr) {
                    if (inetAddress is Inet4Address && !inetAddress.isLoopbackAddress) {
                        return inetAddress.hostAddress
                    }
                }
            }
        } catch (e: SocketException) {
            e.printStackTrace()
        }
        return ""
    }
}
