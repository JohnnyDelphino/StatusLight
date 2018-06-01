import org.firmata4j.Pin
import org.firmata4j.firmata.FirmataDevice

fun main(args: Array<String>){
    val connection = Connection(4,3,2, "/dev/cu.usbmodem14131")
    connection.setColor(255,0,0,true)
    connection.close()

}

class Connection(val redPin: Int, val greenPin: Int, val bluePin: Int, val port: String){

    lateinit var device:FirmataDevice
    lateinit var red:Pin
    lateinit var green:Pin
    lateinit var blue:Pin

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
    }

    fun close(){
        device.stop()
    }

    fun setColor(red: Int, green: Int, blue: Int, commonAnnode: Boolean){
        var r = red
        var g = green
        var b = blue
        if (commonAnnode){
            r = 255 - red
            g = 255 - green;
            b = 255 - blue;
        }
        if(device.isReady){
            this.red.value = r.toLong()
            this.red.value = g.toLong()
            this.blue.value = b.toLong()
        }
    }
}