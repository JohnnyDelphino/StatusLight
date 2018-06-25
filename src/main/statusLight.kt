package main

import org.firmata4j.Pin
import org.firmata4j.firmata.FirmataDevice
import java.io.IOException

class StatusLight(val redPin: Int, val greenPin: Int, val bluePin: Int, val port: String) : NavigationBar.Callback {

    lateinit var device: FirmataDevice
    lateinit var red: Pin
    lateinit var green: Pin
    lateinit var blue: Pin

    init {
        device = FirmataDevice(port)
        try {
            device.start()
            device.ensureInitializationIsDone()
            red = device.getPin(redPin)
            red.mode = Pin.Mode.PWM     // DFRobot Beetle (maybe Arduino Leonardo) needs PWM-Mode for RGB LED
            green = device.getPin(greenPin)
            green.mode = Pin.Mode.PWM
            blue = device.getPin(bluePin)
            blue.mode = Pin.Mode.PWM
            setColor(0,0,0,true)
            registerShutdownHandler()
        } catch (e: IOException){
            System.out.println("Unable to Start firmata Device on Port ${port}\nERROR: ${e.localizedMessage}")
        } catch (e: InterruptedException){
            System.out.println("Problem initializing firmata Device\nERROR: ${e.localizedMessage}")
        }
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


    fun registerShutdownHandler(){
        Runtime.getRuntime().addShutdownHook(Thread() { run{
            if (device.isReady){
                System.out.println("Shutting Down Firmata Device")
                device.stop()
            }
        } })
    }
}