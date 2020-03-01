package javas.kotlin

import java.awt.BorderLayout
import java.awt.Component
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.util.*
import javax.swing.JComboBox
import javax.swing.JFrame
import javax.swing.JList
import javax.swing.plaf.basic.BasicComboBoxRenderer

class JComboBoxKeyValue2 : JFrame(), ActionListener {
    override fun actionPerformed(e: ActionEvent) {
        val comboBox = e.source as JComboBox<*>
        val item =
            comboBox.selectedItem as Item
        println(item.id.toString() + " : " + item.description)
    }

    internal inner class ItemRenderer : BasicComboBoxRenderer() {
        override fun getListCellRendererComponent(
            list: JList<*>?, value: Any, index: Int,
            isSelected: Boolean, cellHasFocus: Boolean
        ): Component {
            super.getListCellRendererComponent(
                list, value, index,
                isSelected, cellHasFocus
            )
            if (value != null) {
                val item = value as Item
                text = item.description.toUpperCase()
            }
            if (index == -1) {
                val item = value as Item
                text = "" + item.id
            }
            return this
        }
    }

    internal inner class Item(val id: Int, val description: String) {

        override fun toString(): String {
            return description
        }

    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val frame: JFrame = JComboBoxKeyValue2()
            frame.defaultCloseOperation = EXIT_ON_CLOSE
            frame.pack()
            frame.isVisible = true
        }
    }

    init {
        val model/*: Vector<*>*/ = Vector<Any?>()
        model.addElement(Item(1, "car"))
        model.addElement(Item(2, "plane"))
        model.addElement(Item(3, "train"))
        model.addElement(Item(4, "boat"))
        model.addElement(Item(5, "boat aadf asfsdf a asd asd"))
        //var comboBox: JComboBox<*>
        //  Easiest approach is to just override toString() method
//  of the Item class
        var comboBox = JComboBox<Any?>(model)
        comboBox.addActionListener(this)
        contentPane.add(comboBox, BorderLayout.NORTH)
        //  Most flexible approach is to create a custom render
//  to diplay the Item data
        comboBox = JComboBox<Any?>(model)
        comboBox.setRenderer(ItemRenderer())
        comboBox.addActionListener(this)
        contentPane.add(comboBox, BorderLayout.SOUTH)
    }
}