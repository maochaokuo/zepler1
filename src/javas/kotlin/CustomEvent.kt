package javas.kotlin

import java.util.*
import javax.swing.event.EventListenerList

internal class MyEvent(source: Any?) : EventObject(source)
internal interface MyEventListener : EventListener {
    fun myEventOccurred(evt: MyEvent?)
}

internal class MyClass {
    protected var listenerList = EventListenerList()
    fun addMyEventListener(listener: MyEventListener?) {
        listenerList.add(MyEventListener::class.java, listener)
    }

    fun removeMyEventListener(listener: MyEventListener?) {
        listenerList.remove(MyEventListener::class.java, listener)
    }

    fun fireMyEvent(evt: MyEvent?) {
        val listeners = listenerList.listenerList
        var i = 0
        while (i < listeners.size) {
            if (listeners[i] === MyEventListener::class.java) {
                (listeners[i + 1] as MyEventListener).myEventOccurred(evt)
            }
            i = i + 2
        }
    }
}

object CustomEvent {
    @Throws(Exception::class)
    @JvmStatic
    fun main(argv: Array<String>) {
        val c = MyClass()
        c.addMyEventListener(object : MyEventListener {
            override fun myEventOccurred(evt: MyEvent?) {
                println("fired")
            }
        })
    }
}