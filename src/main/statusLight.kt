package main

import org.firmata4j.Pin
import org.firmata4j.firmata.FirmataDevice

class StatusLight(val redPin: Int, val greenPin: Int, val bluePin: Int, val port: String) : NavigationBar.Callback {

    lateinit var device: FirmataDevice
    lateinit var red: Pin
    lateinit var green: Pin
    lateinit var blue: Pin

    init {
        device = FirmataDevice(port)
        device.start()
        device.ensureInitializationIsDone()
        red = device.getPin(redPin)
        red.mode = Pin.Mode.OUTPUT
        green = device.getPin(greenPin)
        green.mode = Pin.Mode.OUTPUT
        blue = device.getPin(bluePin)
        blue.mode = Pin.Mode.OUTPUT
        setColor(0,0,0,true)
    }

    fun stop(){
        device.stop()
    }

    override fun onMenutItemSelected(index: Int) {
        when(index){
            0 -> setColor(255,0,0,true)     // red
            1 -> setColor(255,255,0,true)  // yellow
            2 -> setColor(0,255,0,true)     // green
            3 -> setColor(0,0,0,true)       // off
            4 -> stop()
            else -> {
                System.out.println("MenuItem not relevant so far")
            }
        }
    }

    fun setColor(red: Long, green: Long, blue: Long, commonAnnode: Boolean){
        if(device.isReady){
            System.out.println("main.StatusLight is ready, send ColorChange-Request RGB(${red}|${green}|${blue})")
            this.red.value = if (commonAnnode) (255 - red) else red
            this.green.value = if(commonAnnode) (255 - green) else green
            this.blue.value = if(commonAnnode) (255 - blue) else blue
        }
    }


}