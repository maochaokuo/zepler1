package javas;

import java.util.EventListener;
import java.util.EventObject;
import javax.swing.event.EventListenerList;

public class RaiseEvent {
}
// Roughly analogous to .NET EventArgs
class ClickEvent extends EventObject {
    public ClickEvent(Object source) {
        super(source);
    }
}

// Roughly analogous to .NET delegate
interface ClickListener extends EventListener {
    void clicked(ClickEvent e);
}

class Button {
    // Two methods and field roughly analogous to .NET event with explicit add and remove accessors
    // Listener list is typically reused between several events

    private EventListenerList listenerList = new EventListenerList();

    void addClickListener(ClickListener l) {
        listenerList.add(ClickListener.class, l);
    }

    void removeClickListener(ClickListener l) {
        listenerList.remove(ClickListener.class, l);
    }

    // Roughly analogous to .net OnEvent protected virtual method pattern -
    // call this method to raise the event
    protected void fireClicked(ClickEvent e) {
        ClickListener[] ls = listenerList.getListeners(ClickListener.class);
        for (ClickListener l : ls) {
            l.clicked(e);
        }
    }
}