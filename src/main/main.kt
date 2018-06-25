package main

import java.io.FileReader
import java.util.*

fun main(args: Array<String>) {


    FileReader("config.properties").use {
        val properties = Properties()
        properties.load(it)
        val serialPort = properties.getProperty("serialPort");
        val statusLight = StatusLight(9, 10, 11, serialPort)
        val navigationBar = NavigationBar(statusLight)
    }
}

//TODO: handle program exit to close statusLight-Connection in a good way