package swing

import javax.swing.ImageIcon
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JScrollPane

//class layoutEx1 {
////}

class ScrollPaneDemo : JFrame("ScrollPane Demo") {
//    companion object {
//        @JvmStatic
//        fun main(args: Array<String>) {
//            ScrollPaneDemo()
//        }
//    }

    init {
        val img = ImageIcon("child.png")
        val png = JScrollPane(JLabel(img))
        contentPane.add(png)
        setSize(300, 250)
        isVisible = true
    }
}

fun main(){
    ScrollPaneDemo()
}