package menuFunctions.base

import javas.kotlin.Item
import java.awt.event.FocusEvent
import java.awt.event.FocusListener
import javax.swing.*

public class inputFieldBase(
    name: String,
    containingFrame: JFrame,
    title: String,
    options: List<Item>? = null,
    maxRowCount: Int = 3) : FocusListener
{
    protected val _name : String = name
    protected val _containingFrame : JFrame = containingFrame
    protected val _title : String = title
    protected val _options : List<Item>? = options
    protected val pane : JPanel = JPanel()
    protected val _lblMessage : JLabel = JLabel()

    public var Value : String = ""
        get() = field
        set(value) { field = value }
    public var Message : String = ""
        get() = field
        set(value) { field = value}

    init {
        pane.add(JLabel(_title))
        if (options==null) {
            val txt : JTextField = JTextField(Value)
            txt.putClientProperty("ID_KEY", _name)
            txt.addFocusListener(this)
            pane.add(txt)
        }
        else{
            val cmb=JComboBox<Any?>()
            cmb.putClientProperty("ID_KEY", _name)
            options.forEach{
                cmb.addItem(it)
            }
            cmb.maximumRowCount=maxRowCount
            cmb.addFocusListener(this)
            pane.add(cmb)
        }
        _lblMessage.text = Message
        pane.add(_lblMessage)
    }

    override fun focusLost(e: FocusEvent?) {
        TODO("Not yet implemented")
    }
    override fun focusGained(e: FocusEvent?) {
        TODO("Not yet implemented")
    }
}