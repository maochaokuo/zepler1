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
import java.awt.Toolkit
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.*

/*
 * Beeper.java requires no other files.
 */   class Beeper : JPanel(BorderLayout()), ActionListener {
    var button: JButton
    override fun actionPerformed(e: ActionEvent) {
        Toolkit.getDefaultToolkit().beep()
    }

    companion object {
        /**
         * Create the GUI and show it.  For thread safety,
         * this method should be invoked from the
         * event-dispatching thread.
         */
        private fun createAndShowGUI() { //Create and set up the window.
            val frame = JFrame("Beeper")
            frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
            //Create and set up the content pane.
            val newContentPane: JComponent = Beeper()
            newContentPane.isOpaque = true //content panes must be opaque
            frame.contentPane = newContentPane
            //Display the window.
            frame.pack()
            frame.isVisible = true
        }

        @JvmStatic
        fun main(args: Array<String>) { //Schedule a job for the event-dispatching thread:
//creating and showing this application's GUI.
            SwingUtilities.invokeLater { createAndShowGUI() }
        }
    }

    init {
        button = JButton("Click Me")
        button.preferredSize = Dimension(200, 80)
        add(button, BorderLayout.CENTER)
        button.addActionListener(this)
    }
}