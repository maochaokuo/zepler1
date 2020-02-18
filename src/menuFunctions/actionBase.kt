package menuFunctions

import javax.swing.JFrame

//class actionBase( frm: JFrame) {
//    val _frm=frm
//    //private OnActionListener onActionListenerListener
//    abstract fun displayJob(description: String)
//
//}
open class ActionBase(frm: JFrame,
                 open val onActionListenerListener: OnActionListener) {

    interface OnActionListener {
        fun onAction()
    }

    fun doAction() {
        println("action before")
        onActionListenerListener.onAction()
        println("action after")
    }

}
