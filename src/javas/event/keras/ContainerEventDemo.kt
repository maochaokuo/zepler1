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
import java.awt.GridLayout
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.ContainerEvent
import java.awt.event.ContainerListener
import java.util.*
import javax.swing.*

/*
 * ContainerEventDemo.java requires no other files.
 */   class ContainerEventDemo : JPanel(GridBagLayout()), ContainerListener, ActionListener {
    var display: JTextArea
    var buttonPanel: JPanel
    var addButton: JButton
    var removeButton: JButton
    var clearButton: JButton
    var buttonList: Vector<JButton>
    override fun componentAdded(e: ContainerEvent) {
        displayMessage(" added to ", e)
    }

    override fun componentRemoved(e: ContainerEvent) {
        displayMessage(" removed from ", e)
    }

    fun displayMessage(action: String, e: ContainerEvent) {
        display.append(
            (e.child as JButton).text
                    + " was"
                    + action
                    + e.container.javaClass.name
                    + newline
        )
        display.caretPosition = display.document.length
    }

    /*
     * This could have been implemented as two or three
     * classes or objects, for clarity.
     */
    override fun actionPerformed(e: ActionEvent) {
        val command = e.actionCommand
        if (ADD == command) {
            val newButton = JButton(
                "JButton #"
                        + (buttonList.size + 1)
            )
            buttonList.addElement(newButton)
            buttonPanel.add(newButton)
            buttonPanel.revalidate() //Make the button show up.
        } else if (REMOVE == command) {
            val lastIndex = buttonList.size - 1
            try {
                val nixedButton = buttonList.elementAt(lastIndex)
                buttonPanel.remove(nixedButton)
                buttonList.removeElementAt(lastIndex)
                buttonPanel.revalidate() //Make the button disappear.
                buttonPanel.repaint() //Make the button disappear.
            } catch (exc: ArrayIndexOutOfBoundsException) {
            }
        } else if (CLEAR == command) {
            display.text = ""
        }
    }

    companion object {
        const val ADD = "add"
        const val REMOVE = "remove"
        const val CLEAR = "clear"
        const val newline = "\n"
        /**
         * Create the GUI and show it.  For thread safety,
         * this method should be invoked from the
         * event-dispatching thread.
         */
        private fun createAndShowGUI() { //Create and set up the window.
            val frame = JFrame("ContainerEventDemo")
            frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
            //Create and set up the content pane.
            val newContentPane: JComponent = ContainerEventDemo()
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
        //Initialize an empty list of buttons.
        buttonList = Vector(10, 10)
        //Create all the components.
        addButton = JButton("Add a button")
        addButton.actionCommand = ADD
        addButton.addActionListener(this)
        removeButton = JButton("Remove a button")
        removeButton.actionCommand = REMOVE
        removeButton.addActionListener(this)
        buttonPanel = JPanel(GridLayout(1, 1))
        buttonPanel.preferredSize = Dimension(200, 75)
        buttonPanel.addContainerListener(this)
        display = JTextArea()
        display.isEditable = false
        val scrollPane = JScrollPane(display)
        scrollPane.preferredSize = Dimension(200, 75)
        clearButton = JButton("Clear text area")
        clearButton.actionCommand = CLEAR
        clearButton.addActionListener(this)
        c.fill = GridBagConstraints.BOTH //Fill entire cell.
        c.weighty = 1.0 //Button area and message area have equal height.
        c.gridwidth = GridBagConstraints.REMAINDER //end of row
        gridbag.setConstraints(scrollPane, c)
        add(scrollPane)
        c.weighty = 0.0
        gridbag.setConstraints(clearButton, c)
        add(clearButton)
        c.weightx = 1.0 //Add/remove buttons have equal width.
        c.gridwidth = 1 //NOT end of row
        gridbag.setConstraints(addButton, c)
        add(addButton)
        c.gridwidth = GridBagConstraints.REMAINDER //end of row
        gridbag.setConstraints(removeButton, c)
        add(removeButton)
        c.weighty = 1.0 //Button area and message area have equal height.
        gridbag.setConstraints(buttonPanel, c)
        add(buttonPanel)
        preferredSize = Dimension(400, 400)
        border = BorderFactory.createEmptyBorder(20, 20, 20, 20)
    }
}