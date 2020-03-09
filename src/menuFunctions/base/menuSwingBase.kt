package menuFunctions.base

import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.FocusEvent
import java.awt.event.FocusListener
import javax.swing.event.ListSelectionEvent
import javax.swing.event.ListSelectionListener

// a few buttons, event need to know which button clicked
interface IButtonClick {

}
// a few combobox, event need to know which combobox changed
interface IComboBoxChanged {

}
// a few textbox, event need to know which textbox lost fucus
interface ITextBoxLostFocus {

}
// one single table, event need to know which row(s) selected
interface ITableSelectionChanged {

}
public class menuSwingBase: ActionListener
    , FocusListener, ListSelectionListener
{
    override fun actionPerformed(e: ActionEvent?) {
        TODO("Not yet implemented")
    }
    override fun focusLost(e: FocusEvent?) {
        TODO("Not yet implemented")
    }
    override fun focusGained(e: FocusEvent?) {
        TODO("Not yet implemented")
    }
    override fun valueChanged(e: ListSelectionEvent?) {
        TODO("Not yet implemented")
    }
}
