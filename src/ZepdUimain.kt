package zepler.com

import java.awt.BorderLayout
import java.awt.FlowLayout
import javax.swing.*
import javax.swing.BoxLayout


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
            //val master=JScrollPanel(FlowLayout(FlowLayout.LEFT))
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
            val set4=JPanel()
            set4.add(JLabel("label4"))
            set4.add(JTextField("text4"))
            set4.add(JLabel("msg4"))
            val set5=JPanel()
            set5.add(JLabel("label5"))
            set5.add(JTextField("text5"))
            set5.add(JLabel("msg5"))
            master.add(set1)
            master.add(set2)
            master.add(set3)
            master.add(set4)
            master.add(set5)

            val master2=JPanel(FlowLayout(FlowLayout.LEFT))
            for (i in 0..5) {
                master2.add(JButton("JButton"))
            }

            val master3=JPanel(FlowLayout(FlowLayout.LEFT))//BorderLayout())

            val table = JTable(20, 20)
            table.autoResizeMode=JTable.AUTO_RESIZE_OFF

            val pane = JScrollPane(table)
            master3.add(pane)//, BorderLayout.CENTER)
            //master3.add(table)

//            val js = JScrollPane(
//                master,
//                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
//                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS
//            )
//            js.preferredSize = Dimension(400, 300)
            val container = JPanel()
            container.layout = BoxLayout(container, BoxLayout.Y_AXIS)

//            val panel1 = JPanel()
//            val panel2 = JPanel()

            container.add(master)
            container.add(master2)
            container.add(master3)

            frm.layout= BorderLayout()
            frm.add(container)
//            frm.add(master)
//            frm.add(master2)
            //frm.add(js)
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

//class zepler {
fun main(args: Array<String>) {
    println("Hello World!")
    val i=33/2
    println(i)
    println("hello $i")
    //main2()
    //connect()
    SwingUtilities.invokeLater{
        ZepdUimain()}
}