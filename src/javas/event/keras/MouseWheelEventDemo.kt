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
import java.awt.event.MouseWheelEvent
import java.awt.event.MouseWheelListener
import javax.swing.*

/*
* MouseWheelEventDemo.java
*/   class MouseWheelEventDemo : JPanel(BorderLayout()), MouseWheelListener {
    var textArea: JTextArea
    var scrollPane: JScrollPane
    //Append to the text area and make sure the new text is visible.
    fun eventOutput(eventDescription: String, e: MouseWheelEvent) {
        textArea.append(
            e.component.javaClass.name
                    + ": "
                    + eventDescription
        )
        textArea.caretPosition = textArea.document.length
    }

    override fun mouseWheelMoved(e: MouseWheelEvent) {
        var message: String
        val notches = e.wheelRotation
        message = if (notches < 0) {
            ("Mouse wheel moved UP "
                    + -notches + " notch(es)" + NEWLINE)
        } else {
            ("Mouse wheel moved DOWN "
                    + notches + " notch(es)" + NEWLINE)
        }
        if (e.scrollType == MouseWheelEvent.WHEEL_UNIT_SCROLL) {
            message += "    Scroll type: WHEEL_UNIT_SCROLL$NEWLINE"
            message += ("    Scroll amount: " + e.scrollAmount
                    + " unit increments per notch" + NEWLINE)
            message += ("    Units to scroll: " + e.unitsToScroll
                    + " unit increments" + NEWLINE)
            message += ("    Vertical unit increment: "
                    + scrollPane.verticalScrollBar.getUnitIncrement(1)
                    + " pixels" + NEWLINE)
        } else { //scroll type == MouseWheelEvent.WHEEL_BLOCK_SCROLL
            message += "    Scroll type: WHEEL_BLOCK_SCROLL$NEWLINE"
            message += ("    Vertical block increment: "
                    + scrollPane.verticalScrollBar.getBlockIncrement(1)
                    + " pixels" + NEWLINE)
        }
        eventOutput(message, e)
    }

    companion object {
        val NEWLINE = System.getProperty("line.separator")
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
            /* Turn off metal's use of bold fonts */UIManager.put("swing.boldMetal", false)
            //Schedule a job for the event dispatch thread:
//creating and showing this application's GUI.
            SwingUtilities.invokeLater { createAndShowGUI() }
        }

        /**
         * Create the GUI and show it.  For thread safety,
         * this method should be invoked from the
         * event dispatch thread.
         */
        private fun createAndShowGUI() { //Create and set up the window.
            val frame = JFrame("MouseWheelEventDemo")
            frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
            //Create and set up the content pane.
            val newContentPane: JComponent = MouseWheelEventDemo()
            newContentPane.isOpaque = true //content panes must be opaque
            frame.contentPane = newContentPane
            //Display the window.
            frame.pack()
            frame.isVisible = true
        }
    }

    init {
        textArea = JTextArea()
        textArea.isEditable = false
        scrollPane = JScrollPane(textArea)
        scrollPane.verticalScrollBarPolicy = JScrollPane.VERTICAL_SCROLLBAR_ALWAYS
        scrollPane.preferredSize = Dimension(400, 250)
        add(scrollPane, BorderLayout.CENTER)
        textArea.append(
            "This text area displays information "
                    + "about its mouse wheel events."
                    + NEWLINE
        )
        //Register for mouse-wheel events on the text area.
        textArea.addMouseWheelListener(this)
        preferredSize = Dimension(450, 350)
        border = BorderFactory.createEmptyBorder(20, 20, 20, 20)
    }
}