package menuFunctions.base

import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.FocusEvent
import java.awt.event.FocusListener

interface IButtonClick {

}
interface IComboBoxChanged {

}
interface ITextBoxLostFocus {

}
interface ITableSelectionChanged {

}
class menuSwingBase: ActionListener, FocusListener
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

}
