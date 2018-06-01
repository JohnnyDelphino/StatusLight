package main

import dorkbox.systemTray.MenuItem
import dorkbox.systemTray.SystemTray
import dorkbox.util.CacheUtil
import java.awt.event.ActionListener

class NavigationBar(val callback: Callback){

    // original light bulp icon made by Freepik from www.flaticon.com, modified by Christian Schaefer
    val lightOffImageUrl = NavigationBar::class.java.getResource("resources/light_off.png")
    val lightRedImageUrl = NavigationBar::class.java.getResource("resources/light_red.png")
    val lightYellowImageUrl = NavigationBar::class.java.getResource("resources/light_yellow.png")
    val lightGreenImageUrl = NavigationBar::class.java.getResource("resources/light_green.png")

    init {
        SystemTray.DEBUG = true // for test apps, we always want to run in debug mode
        CacheUtil.clear() // for test apps, make sure the cache is always reset. You should never do this in production.

        // SystemTray.SWING_UI = CustomSwingUI()
        var systemTray = SystemTray.get()
        systemTray?.setTooltip("Easily switch your main.StatusLight color")
        systemTray?.setImage(lightOffImageUrl)
        systemTray?.status = "StatusLight turned off"

        systemTray.menu.add(MenuItem("RED", ActionListener {
            callback.onMenutItemSelected(0)
            systemTray?.setImage(lightRedImageUrl)
            systemTray?.status = "StatusLight signals RED"
        })).setShortcut('r')
        systemTray.menu.add(MenuItem("YELLOW", ActionListener {
            callback.onMenutItemSelected(1)
            systemTray?.setImage(lightYellowImageUrl)
            systemTray?.status = "StatusLight signals YELLOW"
        })).setShortcut('y')
        systemTray.menu.add(MenuItem("GREEN", ActionListener {
            callback.onMenutItemSelected(2)
            systemTray?.setImage(lightGreenImageUrl)
            systemTray?.status = "StatusLight signals GREEN"
        })).setShortcut('g')
        systemTray.menu.add(MenuItem("OFF", ActionListener {
            callback.onMenutItemSelected(3)
            systemTray?.setImage(lightOffImageUrl)
            systemTray?.status = "StatusLight turned off"
        }))
        systemTray.menu.add(MenuItem("Quit", ActionListener {
            callback.onMenutItemSelected(4)
            System.exit(0)
        })).setShortcut('q')
    }

    interface Callback{
        fun onMenutItemSelected(index: Int);
    }
}
