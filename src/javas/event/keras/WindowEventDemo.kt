/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package javas.event.keras

import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.Frame
import java.awt.event.*
import java.lang.Boolean
import javax.swing.*

/*
 * WindowEventDemo
 */   class WindowEventDemo(name: String?) : JFrame(name), WindowListener, WindowFocusListener,
    WindowStateListener {
    var display: JTextArea? = null
    private fun addComponentsToPane() {
        display = JTextArea()
        display!!.isEditable = false
        val scrollPane = JScrollPane(display)
        scrollPane.preferredSize = Dimension(500, 450)
        contentPane.add(scrollPane, BorderLayout.CENTER)
        addWindowListener(this)
        addWindowFocusListener(this)
        addWindowStateListener(this)
        checkWM()
    }

    //Some window managers don't support all window states.
    fun checkWM() {
        val tk = frame.toolkit
        if (!tk.isFrameStateSupported(Frame.ICONIFIED)) {
            displayMessage(
                "Your window manager doesn't support ICONIFIED."
            )
        } else displayMessage(
            "Your window manager supports ICONIFIED."
        )
        if (!tk.isFrameStateSupported(Frame.MAXIMIZED_VERT)) {
            displayMessage(
                "Your window manager doesn't support MAXIMIZED_VERT."
            )
        } else displayMessage(
            "Your window manager supports MAXIMIZED_VERT."
        )
        if (!tk.isFrameStateSupported(Frame.MAXIMIZED_HORIZ)) {
            displayMessage(
                "Your window manager doesn't support MAXIMIZED_HORIZ."
            )
        } else displayMessage(
            "Your window manager supports MAXIMIZED_HORIZ."
        )
        if (!tk.isFrameStateSupported(Frame.MAXIMIZED_BOTH)) {
            displayMessage(
                "Your window manager doesn't support MAXIMIZED_BOTH."
            )
        } else {
            displayMessage(
                "Your window manager supports MAXIMIZED_BOTH."
            )
        }
    }

    override fun windowClosing(e: WindowEvent) {
        displayMessage("WindowListener method called: windowClosing.")
        //A pause so user can see the message before
//the window actually closes.
        val task: ActionListener = object : ActionListener {
            var alreadyDisposed = false
            override fun actionPerformed(e: ActionEvent) {
                if (frame.isDisplayable) {
                    alreadyDisposed = true
                    frame.dispose()
                }
            }
        }
        val timer = Timer(500, task) //fire every half second
        timer.initialDelay = 2000 //first delay 2 seconds
        timer.isRepeats = false
        timer.start()
    }

    override fun windowClosed(e: WindowEvent) { //This will only be seen on standard output.
        displayMessage("WindowListener method called: windowClosed.")
    }

    override fun windowOpened(e: WindowEvent) {
        displayMessage("WindowListener method called: windowOpened.")
    }

    override fun windowIconified(e: WindowEvent) {
        displayMessage("WindowListener method called: windowIconified.")
    }

    override fun windowDeiconified(e: WindowEvent) {
        displayMessage("WindowListener method called: windowDeiconified.")
    }

    override fun windowActivated(e: WindowEvent) {
        displayMessage("WindowListener method called: windowActivated.")
    }

    override fun windowDeactivated(e: WindowEvent) {
        displayMessage("WindowListener method called: windowDeactivated.")
    }

    override fun windowGainedFocus(e: WindowEvent) {
        displayMessage("WindowFocusListener method called: windowGainedFocus.")
    }

    override fun windowLostFocus(e: WindowEvent) {
        displayMessage("WindowFocusListener method called: windowLostFocus.")
    }

    override fun windowStateChanged(e: WindowEvent) {
        displayStateMessage(
            "WindowStateListener method called: windowStateChanged.", e
        )
    }

    private fun displayMessage(msg: String) {
        display!!.append(msg + newline)
        println(msg)
    }

    private fun displayStateMessage(prefix: String, e: WindowEvent) {
        val state = e.newState
        val oldState = e.oldState
        val msg = (prefix
                + newline + space
                + "New state: "
                + convertStateToString(state)
                + newline + space
                + "Old state: "
                + convertStateToString(oldState))
        displayMessage(msg)
    }

    private fun convertStateToString(state: Int): String {
        if (state == Frame.NORMAL) {
            return "NORMAL"
        }
        var strState = " "
        if (state and Frame.ICONIFIED != 0) {
            strState += "ICONIFIED"
        }
        //MAXIMIZED_BOTH is a concatenation of two bits, so
//we need to test for an exact match.
        if (state and Frame.MAXIMIZED_BOTH == Frame.MAXIMIZED_BOTH) {
            strState += "MAXIMIZED_BOTH"
        } else {
            if (state and Frame.MAXIMIZED_VERT != 0) {
                strState += "MAXIMIZED_VERT"
            }
            if (state and Frame.MAXIMIZED_HORIZ != 0) {
                strState += "MAXIMIZED_HORIZ"
            }
        }
        if (" " == strState) {
            strState = "UNKNOWN"
        }
        return strState.trim { it <= ' ' }
    }

    companion object {
        val newline = System.getProperty("line.separator")
        const val space = "    "
        var frame = WindowEventDemo("WindowEventDemo")
        @JvmStatic
        fun main(args: Array<String>) { /* Use an appropriate Look and Feel */
            try { //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
//UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
                UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel")
            } catch (ex: UnsupportedLookAndFeelException) {
                ex.printStackTrace()
            } catch (ex: IllegalAccessException) {
                ex.printStackTrace()
            } catch (ex: InstantiationException) {
                ex.printStackTrace()
            } catch (ex: ClassNotFoundException) {
                ex.printStackTrace()
            }
            /* Turn off metal's use of bold fonts */UIManager.put("swing.boldMetal", Boolean.FALSE)
            //Schedule a job for the event dispatch thread:
//creating and showing this application's GUI.
            SwingUtilities.invokeLater { createAndShowGUI() }
        }

        /**
         * Create the GUI and show it.  For thread safety,
         * this method should be invoked from the
         * event-dispatching thread.
         */
        private fun createAndShowGUI() { //Create and set up the window.
            frame.defaultCloseOperation = DO_NOTHING_ON_CLOSE
            //Set up the content pane.
            frame.addComponentsToPane()
            //Display the window.
            frame.pack()
            frame.isVisible = true
        }
    }
}