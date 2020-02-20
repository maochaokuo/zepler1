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
import javax.swing.event.ListDataEvent
import javax.swing.event.ListDataListener
import javax.swing.event.ListSelectionEvent
import javax.swing.event.ListSelectionListener

/*
 * ListDataEventDemo.java requires the Java Look and Feel Graphics
 * Repository (jlfgr-1_0.jar).  You can download this file from
 * http://java.sun.com/developer/techDocs/hi/repository/
 * Put it in the class path using one of the following commands
 * (assuming jlfgr-1_0.jar is in a subdirectory named jars):
 *
 *   java -cp .;jars/jlfgr-1_0.jar ListDataEventDemo [Microsoft Windows]
 *   java -cp .:jars/jlfgr-1_0.jar ListDataEventDemo [UNIX]
 */   class ListDataEventDemo : JPanel(BorderLayout()), ListSelectionListener {
    private val list: JList<*>
    private val listModel: DefaultListModel<*>
    private val addButton: JButton
    private val deleteButton: JButton
    private var upButton: JButton? = null
    private var downButton: JButton? = null
    private val nameField: JTextField
    private val log: JTextArea

    internal inner class MyListDataListener : ListDataListener {
        override fun contentsChanged(e: ListDataEvent) {
            log.append(
                "contentsChanged: " + e.index0 +
                        ", " + e.index1 + newline
            )
            log.caretPosition = log.document.length
        }

        override fun intervalAdded(e: ListDataEvent) {
            log.append(
                "intervalAdded: " + e.index0 +
                        ", " + e.index1 + newline
            )
            log.caretPosition = log.document.length
        }

        override fun intervalRemoved(e: ListDataEvent) {
            log.append(
                "intervalRemoved: " + e.index0 +
                        ", " + e.index1 + newline
            )
            log.caretPosition = log.document.length
        }
    }

    internal inner class DeleteButtonListener : ActionListener {
        override fun actionPerformed(e: ActionEvent) { /*
             * This method can be called only if
             * there's a valid selection,
             * so go ahead and remove whatever's selected.
             */
            val lsm = list.selectionModel
            var firstSelected = lsm.minSelectionIndex
            val lastSelected = lsm.maxSelectionIndex
            listModel.removeRange(firstSelected, lastSelected)
            val size = listModel.size()
            if (size == 0) { //List is empty: disable delete, up, and down buttons.
                deleteButton.isEnabled = false
                upButton!!.isEnabled = false
                downButton!!.isEnabled = false
            } else { //Adjust the selection.
                if (firstSelected == listModel.size) { //Removed item in last position.
                    firstSelected--
                }
                list.setSelectedIndex(firstSelected)
            }
        }
    }

    /** A listener shared by the text field and add button.  */
    internal inner class AddButtonListener : ActionListener {
        override fun actionPerformed(e: ActionEvent) {
            if (nameField.text == "") { //User didn't type in a name...
                Toolkit.getDefaultToolkit().beep()
                return
            }
            var nameFieldtext=nameField.text
            val index = list.selectedIndex
            val size = listModel.size
            //If no selection or if item in last position is selected,
//add the new one to end of list, and select new one.
            if (index == -1 || index + 1 == size) {
                listModel.addElement(nameFieldtext)
                list.setSelectedIndex(size)
                //Otherwise insert the new one after the current selection,
//and select new one.
            } else {
                listModel.insertElementAt(nameFieldtext, index + 1)
                list.setSelectedIndex(index + 1)
            }
        }
    }

    //Listen for clicks on the up and down arrow buttons.
    internal inner class UpDownListener : ActionListener {
        override fun actionPerformed(e: ActionEvent) { //This method can be called only when
//there's a valid selection,
//so go ahead and move the list item.
            val moveMe = list.selectedIndex
            if (e.actionCommand == upString) { //UP ARROW BUTTON
                if (moveMe != 0) { //not already at top
                    swap(moveMe, moveMe - 1)
                    list.selectedIndex = moveMe - 1
                    list.ensureIndexIsVisible(moveMe - 1)
                }
            } else { //DOWN ARROW BUTTON
                if (moveMe != listModel.size - 1) { //not already at bottom
                    swap(moveMe, moveMe + 1)
                    list.selectedIndex = moveMe + 1
                    list.ensureIndexIsVisible(moveMe + 1)
                }
            }
        }
    }

    //Swap two elements in the list.
    private fun swap(a: Int, b: Int) {
        val aObject = listModel.getElementAt(a)
        val bObject = listModel.getElementAt(b)
        listModel.set(a, bObject)
        listModel.set(b, aObject)
    }

    //Listener method for list selection changes.
    override fun valueChanged(e: ListSelectionEvent) {
        if (e.valueIsAdjusting == false) {
            if (list.selectedIndex == -1) { //No selection: disable delete, up, and down buttons.
                deleteButton.isEnabled = false
                upButton!!.isEnabled = false
                downButton!!.isEnabled = false
                nameField.text = ""
            } else if (list.selectedIndices.size > 1) { //Multiple selection: disable up and down buttons.
                deleteButton.isEnabled = true
                upButton!!.isEnabled = false
                downButton!!.isEnabled = false
            } else { //Single selection: permit all operations.
                deleteButton.isEnabled = true
                upButton!!.isEnabled = true
                downButton!!.isEnabled = true
                nameField.text = list.selectedValue.toString()
            }
        }
    }

    companion object {
        private const val addString = "Add"
        private const val deleteString = "Delete"
        private const val upString = "Move up"
        private const val downString = "Move down"
        private const val newline = "\n"
        /** Returns an ImageIcon, or null if the path was invalid.  */
        protected fun createImageIcon(imageName: String): ImageIcon? {
            val imgLocation = ("toolbarButtonGraphics/navigation/"
                    + imageName
                    + ".gif")
            val imageURL = ListDataEventDemo::class.java.getResource(imgLocation)
            return if (imageURL == null) {
                System.err.println(
                    "Resource not found: "
                            + imgLocation
                )
                null
            } else {
                ImageIcon(imageURL)
            }
        }

        /**
         * Create the GUI and show it.  For thread safety,
         * this method should be invoked from the
         * event-dispatching thread.
         */
        private fun createAndShowGUI() { //Create and set up the window.
            val frame = JFrame("ListDataEventDemo")
            frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
            //Create and set up the content pane.
            val newContentPane: JComponent = ListDataEventDemo()
            newContentPane.isOpaque = true //content panes must be opaque
            frame.contentPane = newContentPane
            //Don't let the content pane get too small.
//(Works if the Java look and feel provides
//the window decorations.)
            newContentPane.minimumSize = Dimension(
                newContentPane.preferredSize.width,
                100
            )
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
        //Create and populate the list model.
        listModel = DefaultListModel<Any?>()
        listModel.addElement("Whistler, Canada")
        listModel.addElement("Jackson Hole, Wyoming")
        listModel.addElement("Squaw Valley, California")
        listModel.addElement("Telluride, Colorado")
        listModel.addElement("Taos, New Mexico")
        listModel.addElement("Snowbird, Utah")
        listModel.addElement("Chamonix, France")
        listModel.addElement("Banff, Canada")
        listModel.addElement("Arapahoe Basin, Colorado")
        listModel.addElement("Kirkwood, California")
        listModel.addElement("Sun Valley, Idaho")
        listModel.addListDataListener(MyListDataListener())
        //Create the list and put it in a scroll pane.
        list = JList<Any?>(listModel)
        list.selectionMode = ListSelectionModel.SINGLE_INTERVAL_SELECTION
        list.selectedIndex = 0
        list.addListSelectionListener(this)
        val listScrollPane = JScrollPane(list)
        //Create the list-modifying buttons.
        addButton = JButton(addString)
        addButton.actionCommand = addString
        addButton.addActionListener(AddButtonListener())
        deleteButton = JButton(deleteString)
        deleteButton.actionCommand = deleteString
        deleteButton.addActionListener(
            DeleteButtonListener()
        )
        var icon = createImageIcon("Up16")
        if (icon != null) {
            upButton = JButton(icon)
            upButton!!.setMargin(Insets(0, 0, 0, 0))
        } else {
            upButton = JButton("Move up")
        }
        upButton!!.setToolTipText("Move the currently selected list item higher.")
        upButton!!.setActionCommand(upString)
        upButton!!.addActionListener(UpDownListener())
        icon = createImageIcon("Down16")
        if (icon != null) {
            downButton = JButton(icon)
            downButton!!.setMargin(Insets(0, 0, 0, 0))
        } else {
            downButton = JButton("Move down")
        }
        downButton!!.setToolTipText("Move the currently selected list item lower.")
        downButton!!.setActionCommand(downString)
        downButton!!.addActionListener(UpDownListener())
        val upDownPanel = JPanel(GridLayout(2, 1))
        upDownPanel.add(upButton)
        upDownPanel.add(downButton)
        //Create the text field for entering new names.
        nameField = JTextField(15)
        nameField.addActionListener(AddButtonListener())
        val name = listModel.getElementAt(list.selectedIndex)
            .toString()
        nameField.text = name
        //Create a control panel, using the default FlowLayout.
        val buttonPane = JPanel()
        buttonPane.add(nameField)
        buttonPane.add(addButton)
        buttonPane.add(deleteButton)
        buttonPane.add(upDownPanel)
        //Create the log for reporting list data events.
        log = JTextArea(10, 20)
        val logScrollPane = JScrollPane(log)
        //Create a split pane for the log and the list.
        val splitPane = JSplitPane(
            JSplitPane.VERTICAL_SPLIT,
            listScrollPane, logScrollPane
        )
        splitPane.resizeWeight = 0.5
        //Put everything together.
        add(buttonPane, BorderLayout.PAGE_START)
        add(splitPane, BorderLayout.CENTER)
    }
}