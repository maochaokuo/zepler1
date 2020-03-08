package javas.kotlin

import java.util.*
import javax.swing.event.EventListenerList

class RaiseEvent  // Roughly analogous to .NET EventArgs
internal class ClickEvent(source: Any?) :
    EventObject(source)  // Roughly analogous to .NET delegate

internal interface ClickListener : EventListener {
    fun clicked(e: ClickEvent?)
}

internal class Button {
    // Two methods and field roughly analogous to .NET event with explicit add and remove accessors
    // Listener list is typically reused between several events
    private val listenerList = EventListenerList()
    fun addClickListener(l: ClickListener?) {
        listenerList.add(ClickListener::class.java, l)
    }

    fun removeClickListener(l: ClickListener?) {
        listenerList.remove(ClickListener::class.java, l)
    }

    // Roughly analogous to .net OnEvent protected virtual method pattern -
    // call this method to raise the event
    protected fun fireClicked(e: ClickEvent?) {
        val ls =
            listenerList.getListeners(ClickListener::class.java)
        for (l in ls) {
            l.clicked(e)
        }
        throw NotImplementedError("not implemented yet!") // just an example
    }
}