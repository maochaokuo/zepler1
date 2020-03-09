package javas.kotlin

class Test(private val onActionListenerListener:
           OnActionListener
) {

    interface OnActionListener {
        fun onAction()
    }

    fun doAction() {
        onActionListenerListener.onAction()
    }

}

internal class Test2 {
    init {
        val test = Test(object : Test.OnActionListener {
            override fun onAction() {
                // do something
            }
        })
        test.doAction()
    }
}
fun main(args: Array<String>) {
    val test = Test3 {
        // do something
        println("actually do something")
    }
    test.doAction()
}

class Test3(private val onActionListenerListener: () -> Unit) {
    fun doAction() {
        onActionListenerListener()
    }
}
