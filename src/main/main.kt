package main

fun main(args: Array<String>) {
    val statusLight = StatusLight(6, 5, 3, "/dev/cu.usbmodem14131")
    val navigationBar = NavigationBar(statusLight)
}

//TODO: handle program exit to close statusLight-Connection in a good way