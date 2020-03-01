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

import java.awt.Color
import java.awt.Dimension
import java.awt.GridLayout
import java.awt.event.MouseEvent
import java.awt.event.MouseMotionListener
import javax.swing.*

/*
 * MouseMotionEventDemo.java
 *
 */   class MouseMotionEventDemo : JPanel(GridLayout(0, 1)), MouseMotionListener {
    var blankArea: BlankArea
    var textArea: JTextArea
    fun eventOutput(eventDescription: String, e: MouseEvent) {
        textArea.append(
            eventDescription
                    + " (" + e.x + "," + e.y + ")"
                    + " detected on "
                    + e.component.javaClass.name
                    + NEWLINE
        )
        textArea.caretPosition = textArea.document.length
    }

    override fun mouseMoved(e: MouseEvent) {
        eventOutput("Mouse moved", e)
    }

    override fun mouseDragged(e: MouseEvent) {
        eventOutput("Mouse dragged", e)
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
         * event-dispatching thread.
         */
        private fun createAndShowGUI() { //Create and set up the window.
            val frame = JFrame("MouseMotionEventDemo")
            frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
            //Create and set up the content pane.
            val newContentPane: JComponent = MouseMotionEventDemo()
            newContentPane.isOpaque = true //content panes must be opaque
            frame.contentPane = newContentPane
            //Display the window.
            frame.pack()
            frame.isVisible = true
        }
    }

    init {
        blankArea = BlankArea(Color.YELLOW)
        add(blankArea)
        textArea = JTextArea()
        textArea.isEditable = false
        val scrollPane = JScrollPane(
            textArea,
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
        )
        scrollPane.preferredSize = Dimension(200, 75)
        add(scrollPane)
        //Register for mouse events on blankArea and panel.
        blankArea.addMouseMotionListener(this)
        addMouseMotionListener(this)
        preferredSize = Dimension(450, 450)
        border = BorderFactory.createEmptyBorder(20, 20, 20, 20)
    }
}