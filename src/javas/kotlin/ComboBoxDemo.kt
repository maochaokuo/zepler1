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

import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.Font
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.*

/*
 * ComboBoxDemo.java uses these additional files:
 *   images/Bird.gif
 *   images/Cat.gif
 *   images/Dog.gif
 *   images/Rabbit.gif
 *   images/Pig.gif
 */
class ComboBoxDemo : JPanel(BorderLayout()), ActionListener {
    var picture: JLabel
    /** Listens to the combo box.  */
    override fun actionPerformed(e: ActionEvent) {
        val cb = e.source as JComboBox<*>
        val petName = cb.selectedItem as String
        updateLabel(petName)
    }

    protected fun updateLabel(name: String?) {
        val icon = createImageIcon("images/$name.gif")
        picture.icon = icon
        picture.toolTipText = "A drawing of a " + name!!.toLowerCase()
        if (icon != null) {
            picture.text = null
        } else {
            picture.text = "Image not found"
        }
    }

    companion object {
        /** Returns an ImageIcon, or null if the path was invalid.  */
        protected fun createImageIcon(path: String): ImageIcon? {
            val imgURL = ComboBoxDemo::class.java.getResource(path)
            return if (imgURL != null) {
                ImageIcon(imgURL)
            } else {
                System.err.println("Couldn't find file: $path")
                null
            }
        }

        /**
         * Create the GUI and show it.  For thread safety,
         * this method should be invoked from the
         * event-dispatching thread.
         */
        private fun createAndShowGUI() { //Create and set up the window.
            val frame = JFrame("ComboBoxDemo")
            frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
            //Create and set up the content pane.
            val newContentPane: JComponent = ComboBoxDemo()
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
        val petStrings = arrayOf<String?>("Bird", "Cat", "Dog", "Rabbit", "Pig")
        //Create the combo box, select the item at index 4.
//Indices start at 0, so 4 specifies the pig.
        val petList: JComboBox<*>
        petList = JComboBox<Any?>(petStrings)
        petList.selectedIndex = 4
        petList.addActionListener(this)
        //Set up the picture.
        picture = JLabel()
        picture.font = picture.font.deriveFont(Font.ITALIC)
        picture.horizontalAlignment = JLabel.CENTER
        updateLabel(petStrings[petList.selectedIndex])
        picture.border = BorderFactory.createEmptyBorder(10, 0, 0, 0)
        //The preferred size is hard-coded to be the width of the
//widest image and the height of the tallest image + the border.
//A real program would compute this.
        picture.preferredSize = Dimension(177, 122 + 10)
        //Lay out the demo.
        add(petList, BorderLayout.PAGE_START)
        add(picture, BorderLayout.PAGE_END)
        border = BorderFactory.createEmptyBorder(20, 20, 20, 20)
    }
}