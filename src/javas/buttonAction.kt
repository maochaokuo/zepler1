package javas

import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.JButton
import javax.swing.JFrame

class buttonAction : JFrame(), ActionListener {
    private val button1: JButton
    override fun actionPerformed(e: ActionEvent) { //        if(e.getSource() == button1)
//            println("e.getSource().toString()");
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val calc = buttonAction()
            calc.isVisible = true
        }
    }

    init {
        this.setSize(100, 100)
        this.isVisible = true
        button1 = JButton("1")
        button1.addActionListener(this)
        this.add(button1)
    }
}