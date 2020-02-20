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

import java.awt.Dimension
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.*
import javax.swing.event.DocumentEvent
import javax.swing.event.DocumentListener
import javax.swing.text.Document

/*
 * DocumentEventDemo.java requires no other files.
 */   class DocumentEventDemo : JPanel(GridBagLayout()), ActionListener {
    var textField: JTextField
    var textArea: JTextArea
    var displayArea: JTextArea

    internal inner class MyDocumentListener : DocumentListener {
        val newline = "\n"
        override fun insertUpdate(e: DocumentEvent) {
            updateLog(e, "inserted into")
        }

        override fun removeUpdate(e: DocumentEvent) {
            updateLog(e, "removed from")
        }

        override fun changedUpdate(e: DocumentEvent) { //Plain text components don't fire these events.
        }

        fun updateLog(e: DocumentEvent, action: String) {
            val doc = e.document as Document
            val changeLength = e.length
            displayArea.append(
                changeLength.toString() + " character"
                        + (if (changeLength == 1) " " else "s ")
                        + action + " " + doc.getProperty("name") + "."
                        + newline
                        + "  Text length = " + doc.length + newline
            )
            displayArea.caretPosition = displayArea.document.length
        }
    }

    internal inner class MyTextActionListener : ActionListener {
        /** Handle the text field Return.  */
        override fun actionPerformed(e: ActionEvent) {
            val selStart = textArea.selectionStart
            val selEnd = textArea.selectionEnd
            textArea.replaceRange(
                textField.text,
                selStart, selEnd
            )
            textField.selectAll()
        }
    }

    /** Handle button click.  */
    override fun actionPerformed(e: ActionEvent) {
        displayArea.text = ""
        textField.requestFocus()
    }

    companion object {
        /**
         * Create the GUI and show it.  For thread safety,
         * this method should be invoked from the
         * event-dispatching thread.
         */
        private fun createAndShowGUI() { //Create and set up the window.
            val frame = JFrame("DocumentEventDemo")
            frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
            //Create and set up the content pane.
            val newContentPane: JComponent = DocumentEventDemo()
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
        val button = JButton("Clear")
        button.addActionListener(this)
        textField = JTextField(20)
        textField.addActionListener(MyTextActionListener())
        textField.document.addDocumentListener(MyDocumentListener())
        textField.document.putProperty("name", "Text Field")
        textArea = JTextArea()
        textArea.document.addDocumentListener(MyDocumentListener())
        textArea.document.putProperty("name", "Text Area")
        val scrollPane = JScrollPane(textArea)
        scrollPane.preferredSize = Dimension(200, 75)
        displayArea = JTextArea()
        displayArea.isEditable = false
        val displayScrollPane = JScrollPane(displayArea)
        displayScrollPane.preferredSize = Dimension(200, 75)
        c.gridx = 0
        c.gridy = 0
        c.weightx = 1.0
        c.fill = GridBagConstraints.HORIZONTAL
        gridbag.setConstraints(textField, c)
        add(textField)
        c.gridx = 0
        c.gridy = 1
        c.weightx = 0.0
        c.gridheight = 2
        c.fill = GridBagConstraints.BOTH
        gridbag.setConstraints(scrollPane, c)
        add(scrollPane)
        c.gridx = 1
        c.gridy = 0
        c.weightx = 1.0
        c.weighty = 1.0
        gridbag.setConstraints(displayScrollPane, c)
        add(displayScrollPane)
        c.gridx = 1
        c.gridy = 2
        c.weightx = 0.0
        c.gridheight = 1
        c.weighty = 0.0
        c.fill = GridBagConstraints.HORIZONTAL
        gridbag.setConstraints(button, c)
        add(button)
        preferredSize = Dimension(450, 250)
        border = BorderFactory.createEmptyBorder(20, 20, 20, 20)
    }
}