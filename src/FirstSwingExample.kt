import javax.swing.JButton
import javax.swing.JFrame

object FirstSwingExample {
    fun main() {
        val f = JFrame() //creating instance of JFrame
        val b = JButton("click") //creating instance of JButton
        b.setBounds(130, 100, 100, 40) //x axis, y axis, width, height
        f.add(b) //adding button in JFrame
        f.setSize(400, 500) //400 width and 500 height
        f.layout = null //using no layout managers
        f.isVisible = true //making the frame visible
    }
}