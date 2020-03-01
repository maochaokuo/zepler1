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
package javas.kotlin

import java.awt.Color
import java.awt.Component
import java.awt.Dimension
import java.awt.GridLayout
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.text.SimpleDateFormat
import java.util.*
import javax.swing.*

/* ComboBoxDemo2.java requires no other files. */
class ComboBoxDemo2 : JPanel(), ActionListener {
    var result: JLabel
    var currentPattern: String?
    override fun actionPerformed(e: ActionEvent) {
        val cb = e.source as JComboBox<*>
        val newSelection = cb.selectedItem as String
        currentPattern = newSelection
        reformat()
    }

    /** Formats and displays today's date.  */
    fun reformat() {
        val today = Date()
        val formatter = SimpleDateFormat(currentPattern)
        try {
            val dateString = formatter.format(today)
            result.foreground = Color.black
            result.text = dateString
        } catch (iae: IllegalArgumentException) {
            result.foreground = Color.red
            result.text = "Error: " + iae.message
        }
    }

    companion object {
        var frame: JFrame? = null
        /**
         * Create the GUI and show it.  For thread safety,
         * this method should be invoked from the
         * event-dispatching thread.
         */
        private fun createAndShowGUI() { //Create and set up the window.
            val frame = JFrame("ComboBoxDemo2")
            frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
            //Create and set up the content pane.
            val newContentPane: JComponent = ComboBoxDemo2()
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
        layout = BoxLayout(this, BoxLayout.PAGE_AXIS)
        val patternExamples = arrayOf<String?>(
            "dd MMMMM yyyy",
            "dd.MM.yy",
            "MM/dd/yy",
            "yyyy.MM.dd G 'at' hh:mm:ss z",
            "EEE, MMM d, ''yy",
            "h:mm a",
            "H:mm:ss:SSS",
            "K:mm a,z",
            "yyyy.MMMMM.dd GGG hh:mm aaa"
        )
        currentPattern = patternExamples[0]
        //Set up the UI for selecting a pattern.
        val patternLabel1 = JLabel("Enter the pattern string or")
        val patternLabel2 = JLabel("select one from the list:")
        val patternList: JComboBox<*> = JComboBox<Any?>(patternExamples)
        patternList.isEditable = true
        patternList.addActionListener(this)
        //Create the UI for displaying result.
        val resultLabel = JLabel(
            "Current Date/Time",
            JLabel.LEADING
        ) //== LEFT
        result = JLabel(" ")
        result.foreground = Color.black
        result.border = BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.black),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        )
        //Lay out everything.
        val patternPanel = JPanel()
        patternPanel.layout = BoxLayout(
            patternPanel,
            BoxLayout.PAGE_AXIS
        )
        patternPanel.add(patternLabel1)
        patternPanel.add(patternLabel2)
        patternList.alignmentX = Component.LEFT_ALIGNMENT
        patternPanel.add(patternList)
        val resultPanel = JPanel(GridLayout(0, 1))
        resultPanel.add(resultLabel)
        resultPanel.add(result)
        patternPanel.alignmentX = Component.LEFT_ALIGNMENT
        resultPanel.alignmentX = Component.LEFT_ALIGNMENT
        add(patternPanel)
        add(Box.createRigidArea(Dimension(0, 10)))
        add(resultPanel)
        border = BorderFactory.createEmptyBorder(10, 10, 10, 10)
        reformat()
    } //constructor
}