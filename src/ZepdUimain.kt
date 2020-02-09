package zepler.com

import javax.swing.*

class ZepdUimain {
    val frm= JFrame("ZepdUI")

    init {
        frm.defaultCloseOperation=JFrame.EXIT_ON_CLOSE
        frm.setSize(450, 260)
        frm.isVisible=true
        frm.jMenuBar = createMenuBar()
    }
    fun createMenuBar():JMenuBar {
        val menuBar: JMenuBar
        var menu: JMenu
        //var submenu: JMenu
        val menuItem: JMenuItem
//        var rbMenuItem: JRadioButtonMenuItem
//        var cbMenuItem: JCheckBoxMenuItem

        menuBar= JMenuBar()
        menu= JMenu("database management")
        menuItem = JMenuItem("Tables")
        menuItem.addActionListener {
            //val i = 32 / 3
            val f = JFrame()
            f.setSize(300, 200)
            f.isVisible = true
        }
        menu.add(menuItem)
        menuBar.add(menu)

        menu= JMenu("Generic Query Data")
        menuBar.add(menu)

        menu=JMenu("Elementary Programming Data")
        menuBar.add(menu)
        return menuBar
    }
}
