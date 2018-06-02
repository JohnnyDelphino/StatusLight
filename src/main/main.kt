package main

fun main(args: Array<String>) {
    val statusLight = StatusLight(9, 10, 11, "/dev/cu.usbmodem14141")
    val navigationBar = NavigationBar(statusLight)
}

//TODO: handle program exit to close statusLight-Connection in a good way