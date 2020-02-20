package zepler.com

import java.awt.BorderLayout
import java.awt.FlowLayout
import javax.swing.*

class ZepdUimain {
    val frm = JFrame("ZepdUI")

    init {
        frm.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frm.setSize(450, 260)
        frm.isVisible = true
        frm.jMenuBar = createMenuBar()
    }
    fun createMenuBar():JMenuBar {
        val menuBar: JMenuBar
        var menu: JMenu
        //var submenu: JMenu
        val menuItem: JMenuItem
//        var rbMenuItem: JRadioButtonMenuItem
//        var cbMenuItem: JCheckBoxMenuItem

        menuBar = JMenuBar()
        menu = JMenu("database")
        menu.accessibleContext.accessibleDescription="DB"
        menuBar.add(menu)
        menuItem = JMenuItem("Tables")
        menuItem.accessibleContext.accessibleDescription="tb"
        menuItem.addActionListener {
            //val i = 32 / 3
//            val f = JFrame()
//            f.setSize(300, 200)
//            f.isVisible = true
            val master=JPanel(FlowLayout(FlowLayout.LEFT))
            val set1=JPanel()
            set1.add(JLabel("label1"))
            set1.add(JTextField("text1"))
            set1.add(JLabel("msg1"))
            val set2=JPanel()
            set2.add(JLabel("label2"))
            set2.add(JTextField("text2"))
            set2.add(JLabel("msg2"))
            val set3=JPanel()
            set3.add(JLabel("label3"))
            set3.add(JTextField("text3"))
            set3.add(JLabel("msg3"))
            master.add(set1)
            master.add(set2)
            master.add(set3)
            frm.layout= BorderLayout()
            frm.add(master)
            frm.pack()
        }
        menu.add(menuItem)

        menu= JMenu("GQD")
        menuBar.add(menu)

        menu=JMenu("EPD")
        menuBar.add(menu)
        return menuBar
    }
}
