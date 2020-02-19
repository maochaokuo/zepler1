package menuFunctions.database

import menuFunctions.ActionBase
import javax.swing.*

public open class BaseFrame(frm: JFrame,
         override val onActionListenerListener: OnActionListener)
    : ActionBase(frm, onActionListenerListener) {

}

public class tables(frm: JFrame, onActionListenerListener: OnActionListener)
    : BaseFrame(frm, onActionListenerListener){

}
