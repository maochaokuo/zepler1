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

import java.awt.Frame
import java.awt.event.*
import java.beans.PropertyChangeEvent
import java.beans.PropertyChangeListener
import javax.swing.*

//property change stuff
/* 1.4 example used by DialogDemo.java. */
class CustomDialog : JDialog, ActionListener, PropertyChangeListener {
    /**
     * Returns null if the typed string was invalid;
     * otherwise, returns the string as the user entered it.
     */
    var validatedText: String? = null
        private set
    private var textField: JTextField? = null
    private var dd: DialogDemo? = null
    private var magicWord: String? = null
    private var optionPane: JOptionPane? = null
    private val btnString1 = "Enter"
    private val btnString2 = "Cancel"

    //constructor(frame: JFrame?, geisel: String?, dialogDemo: DialogDemo?) {}

    /** Creates the reusable dialog.  */
    constructor(aFrame: Frame?, aWord: String, parent: DialogDemo?) : super(
        aFrame,
        true
    ) {
        dd = parent
        magicWord = aWord.toUpperCase()
        title = "Quiz"
        textField = JTextField(10)
        //Create an array of the text and components to be displayed.
        val msgString1 = "What was Dr. SEUSS's real last name?"
        val msgString2 = ("(The answer is \"" + magicWord
                + "\".)")
        val array = arrayOf<Any>(msgString1, msgString2, textField!!)
        //Create an array specifying the number of dialog buttons
//and their text.
        val options = arrayOf<Any>(btnString1, btnString2)
        //Create the JOptionPane.
        optionPane = JOptionPane(
            array,
            JOptionPane.QUESTION_MESSAGE,
            JOptionPane.YES_NO_OPTION,
            null,
            options,
            options[0]
        )
        //Make this dialog display it.
        contentPane = optionPane
        //Handle window closing correctly.
        defaultCloseOperation = WindowConstants.DO_NOTHING_ON_CLOSE
        addWindowListener(object : WindowAdapter() {
            override fun windowClosing(we: WindowEvent) { /*
                 * Instead of directly closing the window,
                 * we're going to change the JOptionPane's
                 * value property.
                 */
                optionPane!!.value = JOptionPane.CLOSED_OPTION
            }
        })
        //Ensure the text field always gets the first focus.
        addComponentListener(object : ComponentAdapter() {
            override fun componentShown(ce: ComponentEvent) {
                textField!!.requestFocusInWindow()
            }
        })
        //Register an event handler that puts the text into the option pane.
        textField!!.addActionListener(this)
        //Register an event handler that reacts to option pane state changes.
        optionPane!!.addPropertyChangeListener(this)
    }

    /** This method handles events for the text field.  */
    override fun actionPerformed(e: ActionEvent) {
        optionPane!!.value = btnString1
    }

    /** This method reacts to state changes in the option pane.  */
    override fun propertyChange(e: PropertyChangeEvent) {
        val prop = e.propertyName
        if (isVisible
            && e.source === optionPane
            && (JOptionPane.VALUE_PROPERTY == prop || JOptionPane.INPUT_VALUE_PROPERTY == prop)
        ) {
            val value = optionPane!!.value
            if (value === JOptionPane.UNINITIALIZED_VALUE) { //ignore reset
                return
            }
            //Reset the JOptionPane's value.
//If you don't do this, then if the user
//presses the same button next time, no
//property change event will be fired.
            optionPane!!.value = JOptionPane.UNINITIALIZED_VALUE
            if (btnString1 == value) {
                validatedText = textField!!.text
                val ucText = validatedText!!.toUpperCase()
                if (magicWord == ucText) { //we're done; clear and dismiss the dialog
                    clearAndHide()
                } else { //text was invalid
                    textField!!.selectAll()
                    JOptionPane.showMessageDialog(
                        this@CustomDialog,
                        "Sorry, \"" + validatedText + "\" "
                                + "isn't a valid response.\n"
                                + "Please enter "
                                + magicWord + ".",
                        "Try again",
                        JOptionPane.ERROR_MESSAGE
                    )
                    validatedText = null
                    textField!!.requestFocusInWindow()
                }
            } else { //user closed dialog or clicked cancel
                dd!!.setLabel(
                    "It's OK.  "
                            + "We won't force you to type "
                            + magicWord + "."
                )
                validatedText = null
                clearAndHide()
            }
        }
    }

    /** This method clears the dialog and hides it.  */
    fun clearAndHide() {
        textField!!.text = null
        isVisible = false
    }
}