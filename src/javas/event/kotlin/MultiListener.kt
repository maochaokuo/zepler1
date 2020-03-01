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

import java.awt.*
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.*

/*
 * Swing version
 */   class MultiListener : JPanel(GridBagLayout()), ActionListener {
    var topTextArea: JTextArea
    var bottomTextArea: JTextArea
    var button1: JButton
    var button2: JButton
    override fun actionPerformed(e: ActionEvent) {
        topTextArea.append(e.actionCommand + newline)
        topTextArea.caretPosition = topTextArea.document.length
    }

    companion object {
        const val newline = "\n"
        /**
         * Create the GUI and show it.  For thread safety,
         * this method should be invoked from the
         * event-dispatching thread.
         */
        private fun createAndShowGUI() { //Create and set up the window.
            val frame = JFrame("MultiListener")
            frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
            //Create and set up the content pane.
            val newContentPane: JComponent = MultiListener()
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
        val gridbag = layout as GridBagLayout
        val c = GridBagConstraints()
        var l: JLabel? //= null
        c.fill = GridBagConstraints.BOTH
        c.gridwidth = GridBagConstraints.REMAINDER
        l = JLabel("What MultiListener hears:")
        gridbag.setConstraints(l, c)
        add(l)
        c.weighty = 1.0
        topTextArea = JTextArea()
        topTextArea.isEditable = false
        val topScrollPane = JScrollPane(topTextArea)
        val preferredSize = Dimension(200, 75)
        topScrollPane.preferredSize = preferredSize
        gridbag.setConstraints(topScrollPane, c)
        add(topScrollPane)
        c.weightx = 0.0
        c.weighty = 0.0
        l = JLabel("What Eavesdropper hears:")
        gridbag.setConstraints(l, c)
        add(l)
        c.weighty = 1.0
        bottomTextArea = JTextArea()
        bottomTextArea.isEditable = false
        val bottomScrollPane = JScrollPane(bottomTextArea)
        bottomScrollPane.preferredSize = preferredSize
        gridbag.setConstraints(bottomScrollPane, c)
        add(bottomScrollPane)
        c.weightx = 1.0
        c.weighty = 0.0
        c.gridwidth = 1
        c.insets = Insets(10, 10, 0, 10)
        button1 = JButton("Blah blah blah")
        gridbag.setConstraints(button1, c)
        add(button1)
        c.gridwidth = GridBagConstraints.REMAINDER
        button2 = JButton("You don't say!")
        gridbag.setConstraints(button2, c)
        add(button2)
        button1.addActionListener(this)
        button2.addActionListener(this)
        button2.addActionListener(Eavesdropper(bottomTextArea))
        setPreferredSize(Dimension(450, 450))
        border = BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(
                1, 1, 2, 2, Color.black
            ),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        )
    }
}

internal class Eavesdropper(var myTextArea: JTextArea) : ActionListener {
    override fun actionPerformed(e: ActionEvent) {
        myTextArea.append(
            e.actionCommand
                    + MultiListener.newline
        )
        myTextArea.caretPosition = myTextArea.document.length
    }

}