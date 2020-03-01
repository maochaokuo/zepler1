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
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import javax.swing.*

/*
* KeyEventDemo
*/   class KeyEventDemo(name: String?) : JFrame(name), KeyListener, ActionListener {
    var displayArea: JTextArea? = null
    var typingArea: JTextField? = null
    private fun addComponentsToPane() {
        val button = JButton("Clear")
        button.addActionListener(this)
        typingArea = JTextField(20)
        typingArea!!.addKeyListener(this)
        //Uncomment this if you wish to turn off focus
//traversal.  The focus subsystem consumes
//focus traversal keys, such as Tab and Shift Tab.
//If you uncomment the following line of code, this
//disables focus traversal and the Tab events will
//become available to the key event listener.
//typingArea.setFocusTraversalKeysEnabled(false);
        displayArea = JTextArea()
        displayArea!!.isEditable = false
        val scrollPane = JScrollPane(displayArea)
        scrollPane.preferredSize = Dimension(375, 125)
        contentPane.add(typingArea, BorderLayout.PAGE_START)
        contentPane.add(scrollPane, BorderLayout.CENTER)
        contentPane.add(button, BorderLayout.PAGE_END)
    }

    /** Handle the key typed event from the text field.  */
    override fun keyTyped(e: KeyEvent) {
        displayInfo(e, "KEY TYPED: ")
    }

    /** Handle the key pressed event from the text field.  */
    override fun keyPressed(e: KeyEvent) {
        displayInfo(e, "KEY PRESSED: ")
    }

    /** Handle the key released event from the text field.  */
    override fun keyReleased(e: KeyEvent) {
        displayInfo(e, "KEY RELEASED: ")
    }

    /** Handle the button click.  */
    override fun actionPerformed(e: ActionEvent) { //Clear the text components.
        displayArea!!.text = ""
        typingArea!!.text = ""
        //Return the focus to the typing area.
        typingArea!!.requestFocusInWindow()
    }

    /*
     * We have to jump through some hoops to avoid
     * trying to print non-printing characters
     * such as Shift.  (Not only do they not print,
     * but if you put them in a String, the characters
     * afterward won't show up in the text area.)
     */
    private fun displayInfo(
        e: KeyEvent,
        keyStatus: String
    ) { //You should only rely on the key char if the event
//is a key typed event.
        val id = e.id
        val keyString: String
        keyString = if (id == KeyEvent.KEY_TYPED) {
            val c = e.keyChar
            "key character = '$c'"
        } else {
            val keyCode = e.keyCode
            ("key code = " + keyCode
                    + " ("
                    + KeyEvent.getKeyText(keyCode)
                    + ")")
        }
        val modifiersEx = e.modifiersEx
        var modString = "extended modifiers = $modifiersEx"
        val tmpString = KeyEvent.getModifiersExText(modifiersEx)
        modString += if (tmpString.length > 0) {
            " ($tmpString)"
        } else {
            " (no extended modifiers)"
        }
        var actionString = "action key? "
        actionString += if (e.isActionKey) {
            "YES"
        } else {
            "NO"
        }
        var locationString = "key location: "
        val location = e.keyLocation
        locationString += if (location == KeyEvent.KEY_LOCATION_STANDARD) {
            "standard"
        } else if (location == KeyEvent.KEY_LOCATION_LEFT) {
            "left"
        } else if (location == KeyEvent.KEY_LOCATION_RIGHT) {
            "right"
        } else if (location == KeyEvent.KEY_LOCATION_NUMPAD) {
            "numpad"
        } else { // (location == KeyEvent.KEY_LOCATION_UNKNOWN)
            "unknown"
        }
        displayArea!!.append(
            keyStatus + newline
                    + "    " + keyString + newline
                    + "    " + modString + newline
                    + "    " + actionString + newline
                    + "    " + locationString + newline
        )
        displayArea!!.caretPosition = displayArea!!.document.length
    }

    companion object {
        val newline = System.getProperty("line.separator")
        @JvmStatic
        fun main(args: Array<String>) { /* Use an appropriate Look and Feel */
            try { //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
//UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
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
            //Schedule a job for event dispatch thread:
//creating and showing this application's GUI.
            SwingUtilities.invokeLater { createAndShowGUI() }
        }

        /**
         * Create the GUI and show it.  For thread safety,
         * this method should be invoked from the
         * event-dispatching thread.
         */
        private fun createAndShowGUI() { //Create and set up the window.
            val frame = KeyEventDemo("KeyEventDemo")
            frame.defaultCloseOperation = EXIT_ON_CLOSE
            //Set up the content pane.
            frame.addComponentsToPane()
            //Display the window.
            frame.pack()
            frame.isVisible = true
        }
    }
}