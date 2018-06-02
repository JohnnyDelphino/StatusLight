package main

import org.firmata4j.Pin
import org.firmata4j.firmata.FirmataDevice
import org.firmata4j.transport.NetworkTransport
import java.net.Inet4Address
import java.net.InetAddress

class StatusLight(val redPin: Int, val greenPin: Int, val bluePin: Int, val port: String, val rgbCommonAnode: Boolean) : NavigationBar.Callback {

    lateinit var device: FirmataDevice
    lateinit var red: Pin
    lateinit var green: Pin
    lateinit var blue: Pin

    init {
        val networkTransport = NetworkTransport(InetAddress.getByName("192.168.178.81"),3030)
        device = FirmataDevice(networkTransport)
        device.start()
        device.ensureInitializationIsDone()
        red = device.getPin(redPin)
        red.mode = Pin.Mode.OUTPUT
        green = device.getPin(greenPin)
        green.mode = Pin.Mode.OUTPUT
        blue = device.getPin(bluePin)
        blue.mode = Pin.Mode.OUTPUT
        setColor(0,0,0,rgbCommonAnode)
    }

    fun stop(){
        device.stop()
    }

    override fun onMenutItemSelected(index: Int) {
        when(index){
            0 -> setColor(255,0,0,rgbCommonAnode)     // red
            1 -> setColor(255,255,0,rgbCommonAnode)  // yellow
            2 -> setColor(0,255,0,rgbCommonAnode)     // green
            3 -> setColor(0,0,0,rgbCommonAnode)       // off
            4 -> stop()
            else -> {
                System.out.println("MenuItem not relevant so far")
            }
        }
    }

    fun setColor(red: Long, green: Long, blue: Long, commonAnnode: Boolean){
        if(device.isReady){
            System.out.println("StatusLight is ready, send ColorChange-Request RGB(${red}|${green}|${blue})")
            this.red.value = if (commonAnnode) (255 - red) else red
            this.green.value = if(commonAnnode) (255 - green) else green
            this.blue.value = if(commonAnnode) (255 - blue) else blue
        }
    }


}