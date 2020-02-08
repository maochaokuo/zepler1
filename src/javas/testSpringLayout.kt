package javas

import javax.swing.*

object testSpringLayout {
    @JvmStatic
    fun main(args: Array<String>) {
        println("Java Hello World")
        val labels = arrayOf("Name: ", "Fax: ", "Email: ", "Address: ")
        val numPairs = labels.size
        //Create and populate the panel.
        val p = JPanel(SpringLayout())
        for (i in 0 until numPairs) {
            val l = JLabel(labels[i], JLabel.TRAILING)
            p.add(l)
            val textField = JTextField(10)
            l.labelFor = textField
            p.add(textField)
        }
        //Lay out the panel.
        SpringUtilities.makeCompactGrid(
            p,
            numPairs, 2,  //rows, cols
            6, 6,  //initX, initY
            6, 6
        ) //xPad, yPad
        val f = JFrame() //creating instance of JFrame
        f.add(p) //using no layout managers
        f.setSize(400, 500) //400 width and 500 height
        f.layout = null //using no layout managers
        f.isVisible = true //making the frame visible
    }
}