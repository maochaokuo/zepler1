package zepler.com

import javas.kotlin.WrapLayout
import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.FocusEvent
import java.awt.event.FocusListener
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import javax.swing.*
import javax.swing.BoxLayout
import javax.swing.JOptionPane.showMessageDialog


class ZepdUimain: ActionListener, FocusListener
{
    val frm = JFrame("ZepdUI")

    init {
        frm.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frm.setSize(450, 260)
        frm.preferredSize = Dimension(450, 260)
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
            //val master = JPanel(WrapLayout())//FlowLayout(FlowLayout.LEFT))

            val set1=JPanel()
            set1.add(JLabel("label1"))
            val txt1:JTextField=JTextField("text1")
            txt1.addFocusListener(this)
            set1.add(txt1)// JTextField("text1"))
            set1.add(JLabel("msg1"))
            val set2=JPanel()
            set2.add(JLabel("label2"))
            val txt2:JTextField=JTextField("text2")
            txt2.addFocusListener(this)
            set2.add(txt2)//JTextField("text2"))
            set2.add(JLabel("msg2"))
            val set3=JPanel()
            set3.add(JLabel("label3"))
            val txt3:JTextField=JTextField("text3")
            txt3.addFocusListener(this)
            set3.add(txt3)//JTextField("text3"))
            set3.add(JLabel("msg3"))
            val set4=JPanel()
            set4.add(JLabel("label4"))
            val txt4:JTextField=JTextField("text4")
            txt4.addFocusListener(this)
            set4.add(txt4)//JTextField("text4"))
            set4.add(JLabel("msg4"))
            val set5=JPanel()
            set5.add(JLabel("label5"))
            val txt5:JTextField=JTextField("text5")
            txt5.addFocusListener(this)
            set5.add(txt5)//JTextField("text5"))
            set5.add(JLabel("msg5"))
//            val cmb1
//            val txt1:JTextField=JTextField("text1")
//            txt1.addFocusListener(this)
//            set1.add(txt1)// JTextField("text1"))
//            set1.add(JLabel("msg1"))

            //Map<String, JTextField> fields
            //todo JTextField does not have id or name
            // if we need to use its collection, need to
            // use Map
//            var fields: MutableMap<String?, JTextField?>
//            val field = JTextField(15)
//            fields[fieldName] = field

            val master0=JPanel(WrapLayout())
            master0.add(set1)
            master0.add(set2)
            master0.add(set3)
            master0.add(set4)
            master0.add(set5)
            val master = JScrollPane(master0)

            val master2pre = JPanel( WrapLayout())// FlowLayout(FlowLayout.LEFT))
            for (i in 0..15) {
                val btn:JButton=JButton("JButton"+i.toString())
                btn.addActionListener(this)
                master2pre.add(btn)
            }
            val master2 = JScrollPane(master2pre)
//            val master2 = JPanel()//scrollPane2)
//            master2.add(scrollPane2)
//            master2.verticalScrollBarPolicy = JScrollPane.VERTICAL_SCROLLBAR_ALWAYS
//            master2.horizontalScrollBarPolicy = JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS
//            master2.preferredSize = Dimension(400, 300)

            //val master3 = JPanel(WrapLayout())//FlowLayout(FlowLayout.LEFT))//BorderLayout())

            val table = JTable(30, 30)
            table.autoResizeMode = JTable.AUTO_RESIZE_OFF
            table.rowSelectionAllowed = true
            val cellSelectionModel = table.selectionModel
            cellSelectionModel.selectionMode = ListSelectionModel.SINGLE_INTERVAL_SELECTION
            cellSelectionModel.addListSelectionListener {
                var selectedData: String? = null
                val selectedRow = table.selectedRows
                val selectedColumns = table.selectedColumns
                for (i in selectedRow.indices) {
                    for (j in selectedColumns.indices) {
                        println("selectedRow[i]="+selectedRow[i].toString())
                        selectedData = table.getValueAt(selectedRow[i], selectedColumns[j]) as String?
                    }
                }
                val date = Calendar.getInstance().time
                val dateFormat: DateFormat = SimpleDateFormat("yyyy-mm-dd hh:mm:ss")
                val strDate = dateFormat.format(date)
                println("$strDate Selected: $selectedData")
            }
            //val scrollPane3 = JScrollPane(table)
            val master3 = JScrollPane(table)
            //master3.add(scrollPane3)//, BorderLayout.CENTER)
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

    override fun actionPerformed(e: ActionEvent) {
//        if(e.getSource() == button1)
//            println("e.getSource().toString()");
        showMessageDialog(null,"actionPerformed: "+ e.source);
    }
    override fun focusGained(e: FocusEvent?) {
        //showMessageDialog(null,"focusGained: "+ e!!.source.);
    }
    override fun focusLost(e: FocusEvent?) {
        if (e?.component is JTextField && e.oppositeComponent is JTextField) {
            val c: JTextField = e?.component as JTextField
            val co: JTextField = e.oppositeComponent as JTextField
            showMessageDialog(null, "focusLost: " + c.getText() + " " + co.getText());
        }
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