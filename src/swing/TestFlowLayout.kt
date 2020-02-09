package swing

import java.awt.BorderLayout
import java.awt.Color
import java.awt.EventQueue
import java.awt.FlowLayout
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextField

class TestFlowLayout {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            TestFlowLayout()
        }
    }

    init {
        EventQueue.invokeLater {
            val master = JPanel(FlowLayout(FlowLayout.LEFT))
            val left = JPanel()
            left.background = Color.RED
            left.add(JLabel("Lefty1"))
            left.add(JTextField("Lefty2"))
            left.add(JLabel("Lefty3"))
            val right = JPanel()
            right.background = Color.BLUE
            right.add(JLabel("Righty1"))
            right.add(JTextField("Righty2"))
            right.add(JLabel("Righty3"))
            val other1 = JPanel()
            other1.background = Color.CYAN
            other1.add(JLabel("other1a"))
            other1.add(JTextField("other1b"))
            other1.add(JLabel("other1c"))
            val other2 = JPanel()
            other2.background = Color.DARK_GRAY
            other2.add(JLabel("other2a"))
            other2.add(JTextField("other2b"))
            other2.add(JLabel("other2c"))
            val other3 = JPanel()
            other3.background = Color.GRAY
            other3.add(JLabel("other3a"))
            other3.add(JTextField("other3b"))
            other3.add(JLabel("other3c"))
            master.add(left)
            master.add(right)
            master.add(other1)
            master.add(other2)
            master.add(other3)
            val frame = JFrame()
            frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
            frame.layout = BorderLayout()
            frame.add(master)
            frame.pack()
            frame.setLocationRelativeTo(null)
            frame.isVisible = true
        }
    }
}