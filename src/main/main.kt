package main

fun main(args: Array<String>) {
    val statusLight = StatusLight(5, 4, 0, "http://192.168.178.81:3030")
    val navigationBar = NavigationBar(statusLight)
}

//TODO: handle program exit to close statusLight-Connection in a good way