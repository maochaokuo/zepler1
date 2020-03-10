package javas.kotlin

import java.awt.Component
import java.awt.event.ActionEvent
import javax.swing.JComboBox
import javax.swing.JFrame
import javax.swing.JList
import javax.swing.plaf.basic.BasicComboBoxRenderer

/*from  ww w . j  av  a2 s. c o  m*/   class JComboBoxKeyValue {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            JComboBoxKeyValue()
        }
    }

    init {
        val comboBox = JComboBox<Any?>()
        comboBox.addItem(Item(1, "-"))
        comboBox.addItem(Item(2, "X"))
        comboBox.addItem(Item(3, "Y"))
        comboBox.maximumRowCount = 3
        comboBox.setPrototypeDisplayValue(" None of the above ")
        comboBox.addActionListener { e: ActionEvent ->
            val c = e.source as JComboBox<*>
            val item = c.selectedItem as Item
            println(item.id.toString() + " : " + item.description)
        }
        val frame = JFrame()
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.add(comboBox)
        frame.pack()
        frame.isVisible = true
    }
}

internal class ItemRenderer : BasicComboBoxRenderer() {
    override fun getListCellRendererComponent(
        list: JList<*>?, value: Any,
        index: Int, isSelected: Boolean, cellHasFocus: Boolean
    ): Component {
        super.getListCellRendererComponent(
            list, value, index, isSelected,
            cellHasFocus
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

class Item(val id: Int, val description: String) {

    override fun toString(): String {
        return description
    }

}