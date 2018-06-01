import dorkbox.systemTray.MenuItem
import dorkbox.systemTray.SystemTray
import dorkbox.util.CacheUtil
import java.awt.event.ActionListener

class NavigationBar(){

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
        systemTray?.setTooltip("Test Tooltip")
        systemTray?.setImage(lightOffImageUrl)
        systemTray?.status = "No Status"
        systemTray.menu.add(MenuItem("RED", ActionListener { systemTray?.setImage(lightRedImageUrl) }))
        systemTray.menu.add(MenuItem("YELLOW", ActionListener { systemTray?.setImage(lightYellowImageUrl) }))
        systemTray.menu.add(MenuItem("GREEN", ActionListener { systemTray?.setImage(lightGreenImageUrl) }))
        systemTray.menu.add(MenuItem("Quit", ActionListener { System.exit(0) })).setShortcut('q')
    }
}
