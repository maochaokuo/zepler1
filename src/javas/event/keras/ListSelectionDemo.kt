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
import java.awt.GridLayout
import javax.swing.*
import javax.swing.event.ListSelectionEvent
import javax.swing.event.ListSelectionListener

/*
 * ListSelectionDemo.java requires no other files.
 */   class ListSelectionDemo : JPanel(BorderLayout()) {
    var output: JTextArea
    var list: JList<*>
    var table: JTable? = null
    var newline = "\n"
    var listSelectionModel: ListSelectionModel

    internal inner class SharedListSelectionHandler : ListSelectionListener {
        override fun valueChanged(e: ListSelectionEvent) {
            val lsm = e.source as ListSelectionModel
            val firstIndex = e.firstIndex
            val lastIndex = e.lastIndex
            val isAdjusting = e.valueIsAdjusting
            output.append(
                "Event for indexes "
                        + firstIndex + " - " + lastIndex
                        + "; isAdjusting is " + isAdjusting
                        + "; selected indexes:"
            )
            //            output.append(table.getSelectedRows().length.toString());
//            String line = "";
//            if (table.getSelectedRows().length > 0) {
//                int[] selectedrows = table.getSelectedRows();
//                for (int i = 0; i < selectedrows.length; i++)
//                {
//                    line += (table.getValueAt(selectedrows[i], 0).toString());
//                }
//            }
//            output.append(line);
            if (lsm.isSelectionEmpty) {
                output.append(" <none>")
            } else { // Find out which indexes are selected.
                val minIndex = lsm.minSelectionIndex
                val maxIndex = lsm.maxSelectionIndex
                for (i in minIndex..maxIndex) {
                    if (lsm.isSelectedIndex(i)) {
                        output.append(" $i")
                    }
                }
            }
            output.append(newline)
            output.caretPosition = output.document.length
        }
    }

    companion object {
        /**
         * Create the GUI and show it.  For thread safety,
         * this method should be invoked from the
         * event-dispatching thread.
         */
        private fun createAndShowGUI() { //Create and set up the window.
            val frame = JFrame("ListSelectionDemo")
            frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
            //Create and set up the content pane.
            val demo = ListSelectionDemo()
            demo.isOpaque = true
            frame.contentPane = demo
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
        val listData = arrayOf<String?>(
            "one", "two", "three", "four",
            "five", "six", "seven"
        )
        val columnNames = arrayOf("French", "Spanish", "Italian")
        list = JList<Any?>(listData)
        listSelectionModel = list.selectionModel
        listSelectionModel.addListSelectionListener(
            SharedListSelectionHandler()
        )
        val listPane = JScrollPane(list)
        val controlPane = JPanel()
        val modes = arrayOf<String?>(
            "SINGLE_SELECTION",
            "SINGLE_INTERVAL_SELECTION",
            "MULTIPLE_INTERVAL_SELECTION"
        )
        //Build output area.
        output = JTextArea(1, 10)
        output.isEditable = false
        val outputPane = JScrollPane(
            output,
            ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED
        )
        val comboBox: JComboBox<*> = JComboBox<Any?>(modes)
        comboBox.selectedIndex = 2
        comboBox.addActionListener {
            val newMode = comboBox.selectedItem as String
            if (newMode == "SINGLE_SELECTION") {
                listSelectionModel.selectionMode = ListSelectionModel.SINGLE_SELECTION
            } else if (newMode == "SINGLE_INTERVAL_SELECTION") {
                listSelectionModel.selectionMode = ListSelectionModel.SINGLE_INTERVAL_SELECTION
            } else {
                listSelectionModel.selectionMode = ListSelectionModel.MULTIPLE_INTERVAL_SELECTION
            }
            output.append(
                "----------"
                        + "Mode: " + newMode
                        + "----------" + newline
            )
        }
        controlPane.add(JLabel("Selection mode:"))
        controlPane.add(comboBox)
        //Do the layout.
        val splitPane = JSplitPane(JSplitPane.VERTICAL_SPLIT)
        add(splitPane, BorderLayout.CENTER)
        val topHalf = JPanel()
        topHalf.layout = BoxLayout(topHalf, BoxLayout.LINE_AXIS)
        val listContainer = JPanel(GridLayout(1, 1))
        listContainer.border = BorderFactory.createTitledBorder(
            "List"
        )
        listContainer.add(listPane)
        topHalf.border = BorderFactory.createEmptyBorder(5, 5, 0, 5)
        topHalf.add(listContainer)
        //topHalf.add(tableContainer);
        topHalf.minimumSize = Dimension(100, 50)
        topHalf.preferredSize = Dimension(100, 110)
        splitPane.add(topHalf)
        val bottomHalf = JPanel(BorderLayout())
        bottomHalf.add(controlPane, BorderLayout.PAGE_START)
        bottomHalf.add(outputPane, BorderLayout.CENTER)
        //XXX: next line needed if bottomHalf is a scroll pane:
//bottomHalf.setMinimumSize(new Dimension(400, 50));
        bottomHalf.preferredSize = Dimension(450, 135)
        splitPane.add(bottomHalf)
    }
}