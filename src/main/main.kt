package main

fun main(args: Array<String>) {
    val statusLight = StatusLight(5, 4, 0, "",false)
    val navigationBar = NavigationBar(statusLight)
}

//TODO: handle program exit to close statusLight-Connection in a good way