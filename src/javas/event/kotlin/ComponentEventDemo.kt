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
import java.awt.event.ComponentEvent
import java.awt.event.ComponentListener
import java.awt.event.ItemEvent
import java.awt.event.ItemListener
import javax.swing.*

/*
 * ComponentEventDemo.java requires no other files.
 */   class ComponentEventDemo : JPanel(BorderLayout()), ComponentListener, ItemListener {
    var display: JTextArea
    var label: JLabel
    var newline = "\n"
    override fun itemStateChanged(e: ItemEvent) {
        if (e.stateChange == ItemEvent.SELECTED) {
            label.isVisible = true
            //Need to revalidate and repaint, or else the label
//will probably be drawn in the wrong place.
            label.revalidate()
            label.repaint()
        } else {
            label.isVisible = false
        }
    }

    protected fun displayMessage(message: String) { //If the text area is not yet realized, and
//we tell it to draw text, it could cause
//a text/AWT tree deadlock. Our solution is
//to ensure that the text area is realized
//before attempting to draw text.
// if (display.isShowing()) {
        display.append(message + newline)
        display.caretPosition = display.document.length
        //}
    }

    override fun componentHidden(e: ComponentEvent) {
        displayMessage(e.component.javaClass.name + " --- Hidden")
    }

    override fun componentMoved(e: ComponentEvent) {
        displayMessage(e.component.javaClass.name + " --- Moved")
    }

    override fun componentResized(e: ComponentEvent) {
        displayMessage(e.component.javaClass.name + " --- Resized ")
    }

    override fun componentShown(e: ComponentEvent) {
        displayMessage(e.component.javaClass.name + " --- Shown")
    }

    companion object {
        var frame: JFrame? = null
        /**
         * Create the GUI and show it.  For thread safety,
         * this method should be invoked from the
         * event-dispatching thread.
         */
        private fun createAndShowGUI() { //Create and set up the window.
            frame = JFrame("ComponentEventDemo")
            frame!!.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
            //Create and set up the content pane.
            val newContentPane: JComponent = ComponentEventDemo()
            newContentPane.isOpaque = true //content panes must be opaque
            frame!!.contentPane = newContentPane
            //Display the window.
            frame!!.pack()
            frame!!.isVisible = true
        }

        @JvmStatic
        fun main(args: Array<String>) { //Schedule a job for the event-dispatching thread:
//creating and showing this application's GUI.
            SwingUtilities.invokeLater { createAndShowGUI() }
        }
    }

    init {
        display = JTextArea()
        display.isEditable = false
        val scrollPane = JScrollPane(display)
        scrollPane.preferredSize = Dimension(350, 200)
        val panel = JPanel(BorderLayout())
        label = JLabel("This is a label", JLabel.CENTER)
        label.addComponentListener(this)
        panel.add(label, BorderLayout.CENTER)
        val checkbox = JCheckBox("Label visible", true)
        checkbox.addItemListener(this)
        checkbox.addComponentListener(this)
        panel.add(checkbox, BorderLayout.PAGE_END)
        panel.addComponentListener(this)
        add(scrollPane, BorderLayout.CENTER)
        add(panel, BorderLayout.PAGE_END)
        frame!!.addComponentListener(this)
    }
}