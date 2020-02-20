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
import java.awt.event.FocusEvent
import java.awt.event.FocusListener
import java.util.*
import javax.swing.*

/*
 * FocusEventDemo.java
 *
 */   class FocusEventDemo(name: String?) : JFrame(name), FocusListener {
    var display: JTextArea? = null
    fun addComponentsToPane(pane: Container) {
        val gridbag = GridBagLayout()
        pane.layout = gridbag
        val c = GridBagConstraints()
        c.fill = GridBagConstraints.HORIZONTAL
        c.weightx = 1.0 //Make column as wide as possible.
        val textField = JTextField("A TextField")
        textField.margin = Insets(0, 2, 0, 2)
        textField.addFocusListener(this)
        gridbag.setConstraints(textField, c)
        add(textField)
        c.weightx = 0.1 //Widen every other column a bit, when possible.
        c.fill = GridBagConstraints.NONE
        val label = JLabel("A Label")
        label.border = BorderFactory.createEmptyBorder(0, 5, 0, 5)
        label.addFocusListener(this)
        gridbag.setConstraints(label, c)
        add(label)
        val comboPrefix = "ComboBox Item #"
        val numItems = 15
        val vector = Vector<String>(numItems)
        for (i in 0 until numItems) {
            vector.addElement(comboPrefix + i)
        }
        val comboBox: JComboBox<*> = JComboBox<Any?>(vector)
        comboBox.addFocusListener(this)
        gridbag.setConstraints(comboBox, c)
        add(comboBox)
        c.gridwidth = GridBagConstraints.REMAINDER
        val button = JButton("A Button")
        button.addFocusListener(this)
        gridbag.setConstraints(button, c)
        add(button)
        c.weightx = 0.0
        c.weighty = 0.1
        c.fill = GridBagConstraints.BOTH
        val listPrefix = "List Item #"
        val listVector = Vector<String?>(numItems)
        for (i in 0 until numItems) {
            listVector.addElement(listPrefix + i)
        }
        val list: JList<*> = JList<Any?>(listVector)
        list.selectedIndex = 1 //It's easier to see the focus change
        //if an item is selected.
        list.addFocusListener(this)
        val listScrollPane = JScrollPane(list)
        gridbag.setConstraints(listScrollPane, c)
        add(listScrollPane)
        c.weighty = 1.0 //Make this row as tall as possible.
        c.gridheight = GridBagConstraints.REMAINDER
        //Set up the area that reports focus-gained and focus-lost events.
        display = JTextArea()
        display!!.isEditable = false
        //The setRequestFocusEnabled method prevents a
//component from being clickable, but it can still
//get the focus through the keyboard - this ensures
//user accessibility.
        display!!.isRequestFocusEnabled = false
        display!!.addFocusListener(this)
        val displayScrollPane = JScrollPane(display)
        gridbag.setConstraints(displayScrollPane, c)
        add(displayScrollPane)
        preferredSize = Dimension(450, 450)
        (pane as JPanel).border = BorderFactory.createEmptyBorder(20, 20, 20, 20)
    }

    override fun focusGained(e: FocusEvent) {
        displayMessage("Focus gained", e)
    }

    override fun focusLost(e: FocusEvent) {
        displayMessage("Focus lost", e)
    }

    fun displayMessage(prefix: String, e: FocusEvent) {
        display!!.append(
            prefix
                    + (if (e.isTemporary) " (temporary):" else ":")
                    + e.component.javaClass.name
                    + "; Opposite component: "
                    + (if (e.oppositeComponent != null) e.oppositeComponent.javaClass.name else "null")
                    + newline
        )
        display!!.caretPosition = display!!.document.length
    }

    companion object {
        const val newline = "\n"
        /**
         * Create the GUI and show it.  For thread safety,
         * this method should be invoked from the
         * event dispatch thread.
         */
        private fun createAndShowGUI() { //Create and set up the window.
            val frame = FocusEventDemo("FocusEventDemo")
            frame.defaultCloseOperation = EXIT_ON_CLOSE
            //Set up the content pane.
            frame.addComponentsToPane(frame.contentPane)
            //Display the window.
            frame.pack()
            frame.isVisible = true
        }

        @JvmStatic
        fun main(args: Array<String>) { /* Use an appropriate Look and Feel */
            try { //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
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
    }
}