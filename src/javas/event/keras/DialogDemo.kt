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
import java.awt.Font
import java.awt.GridLayout
import java.awt.event.ActionListener
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import javax.swing.*

//Property change stuff
/*
 * DialogDemo.java requires these files:
 *   CustomDialog.java
 *   images/middle.gif
 */
class DialogDemo(var frame: JFrame) : JPanel(BorderLayout()) {
    var label: JLabel
    var icon = createImageIcon("images/middle.gif")
    var simpleDialogDesc = "Some simple message dialogs"
    var iconDesc = "A JOptionPane has its choice of icons"
    var moreDialogDesc = "Some more dialogs"
    var customDialog: CustomDialog
    /** Sets the text displayed at the bottom of the frame.  */
    fun setLabel(newText: String?) {
        label.text = newText
    }

    /** Creates the panel shown by the first tab.  */
    private fun createSimpleDialogBox(): JPanel {
        val numButtons = 4
        val radioButtons = arrayOfNulls<JRadioButton>(numButtons)
        val group = ButtonGroup()
        val showItButton: JButton? //= null
        val defaultMessageCommand = "default"
        val yesNoCommand = "yesno"
        val yeahNahCommand = "yeahnah"
        val yncCommand = "ync"
        radioButtons[0] = JRadioButton("OK (in the L&F's words)")
        radioButtons[0]!!.actionCommand = defaultMessageCommand
        radioButtons[1] = JRadioButton("Yes/No (in the L&F's words)")
        radioButtons[1]!!.actionCommand = yesNoCommand
        radioButtons[2] = JRadioButton(
            "Yes/No "
                    + "(in the programmer's words)"
        )
        radioButtons[2]!!.actionCommand = yeahNahCommand
        radioButtons[3] = JRadioButton(
            "Yes/No/Cancel "
                    + "(in the programmer's words)"
        )
        radioButtons[3]!!.actionCommand = yncCommand
        for (i in 0 until numButtons) {
            group.add(radioButtons[i])
        }
        radioButtons[0]!!.isSelected = true
        showItButton = JButton("Show it!")
        showItButton.addActionListener(ActionListener {
            val command = group.selection.actionCommand
            //ok dialog
            if (command === defaultMessageCommand) {
                JOptionPane.showMessageDialog(
                    frame,
                    "Eggs aren't supposed to be green."
                )
                //yes/no dialog
            } else if (command === yesNoCommand) {
                val n = JOptionPane.showConfirmDialog(
                    frame, "Would you like green eggs and ham?",
                    "An Inane Question",
                    JOptionPane.YES_NO_OPTION
                )
                if (n == JOptionPane.YES_OPTION) {
                    setLabel("Ewww!")
                } else if (n == JOptionPane.NO_OPTION) {
                    setLabel("Me neither!")
                } else {
                    setLabel("Come on -- tell me!")
                }
                //yes/no (not in those words)
            } else if (command === yeahNahCommand) {
                val options = arrayOf<Any>("Yes, please", "No way!")
                val n = JOptionPane.showOptionDialog(
                    frame,
                    "Would you like green eggs and ham?",
                    "A Silly Question",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]
                )
                if (n == JOptionPane.YES_OPTION) {
                    setLabel("You're kidding!")
                } else if (n == JOptionPane.NO_OPTION) {
                    setLabel("I don't like them, either.")
                } else {
                    setLabel("Come on -- 'fess up!")
                }
                //yes/no/cancel (not in those words)
            } else if (command === yncCommand) {
                val options = arrayOf<Any>(
                    "Yes, please",
                    "No, thanks",
                    "No eggs, no ham!"
                )
                val n = JOptionPane.showOptionDialog(
                    frame, "Would you like some green eggs to go "
                            + "with that ham?",
                    "A Silly Question",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[2]
                )
                if (n == JOptionPane.YES_OPTION) {
                    setLabel("Here you go: green eggs and ham!")
                } else if (n == JOptionPane.NO_OPTION) {
                    setLabel("OK, just the ham, then.")
                } else if (n == JOptionPane.CANCEL_OPTION) {
                    setLabel("Well, I'm certainly not going to eat them!")
                } else {
                    setLabel("Please tell me what you want!")
                }
            }
            return@ActionListener
        })
        return createPane(
            "$simpleDialogDesc:",
            radioButtons,
            showItButton
        )
    }

    /**
     * Used by createSimpleDialogBox and createFeatureDialogBox
     * to create a pane containing a description, a single column
     * of radio buttons, and the Show it! button.
     */
    private fun createPane(
        description: String,
        radioButtons: Array<JRadioButton?>,
        showButton: JButton
    ): JPanel {
        val numChoices = radioButtons.size
        val box = JPanel()
        val label = JLabel(description)
        box.layout = BoxLayout(box, BoxLayout.PAGE_AXIS)
        box.add(label)
        for (i in 0 until numChoices) {
            box.add(radioButtons[i])
        }
        val pane = JPanel(BorderLayout())
        pane.add(box, BorderLayout.PAGE_START)
        pane.add(showButton, BorderLayout.PAGE_END)
        return pane
    }

    /**
     * Like createPane, but creates a pane with 2 columns of radio
     * buttons.  The number of buttons passed in *must* be even.
     */
    private fun create2ColPane(
        description: String,
        radioButtons: Array<JRadioButton?>,
        showButton: JButton
    ): JPanel {
        val label = JLabel(description)
        val numPerColumn = radioButtons.size / 2
        val grid = JPanel(GridLayout(0, 2))
        for (i in 0 until numPerColumn) {
            grid.add(radioButtons[i])
            grid.add(radioButtons[i + numPerColumn])
        }
        val box = JPanel()
        box.layout = BoxLayout(box, BoxLayout.PAGE_AXIS)
        box.add(label)
        grid.alignmentX = 0.0f
        box.add(grid)
        val pane = JPanel(BorderLayout())
        pane.add(box, BorderLayout.PAGE_START)
        pane.add(showButton, BorderLayout.PAGE_END)
        return pane
    }

    /*
     * Creates the panel shown by the 3rd tab.
     * These dialogs are implemented using showMessageDialog, but
     * you can specify the icon (using similar code) for any other
     * kind of dialog, as well.
     */
    private fun createIconDialogBox(): JPanel {
        val showItButton: JButton? //= null
        val numButtons = 6
        val radioButtons = arrayOfNulls<JRadioButton>(numButtons)
        val group = ButtonGroup()
        val plainCommand = "plain"
        val infoCommand = "info"
        val questionCommand = "question"
        val errorCommand = "error"
        val warningCommand = "warning"
        val customCommand = "custom"
        radioButtons[0] = JRadioButton("Plain (no icon)")
        radioButtons[0]!!.actionCommand = plainCommand
        radioButtons[1] = JRadioButton("Information icon")
        radioButtons[1]!!.actionCommand = infoCommand
        radioButtons[2] = JRadioButton("Question icon")
        radioButtons[2]!!.actionCommand = questionCommand
        radioButtons[3] = JRadioButton("Error icon")
        radioButtons[3]!!.actionCommand = errorCommand
        radioButtons[4] = JRadioButton("Warning icon")
        radioButtons[4]!!.actionCommand = warningCommand
        radioButtons[5] = JRadioButton("Custom icon")
        radioButtons[5]!!.actionCommand = customCommand
        for (i in 0 until numButtons) {
            group.add(radioButtons[i])
        }
        radioButtons[0]!!.isSelected = true
        showItButton = JButton("Show it!")
        showItButton.addActionListener(ActionListener {
            val command = group.selection.actionCommand
            //no icon
            if (command === plainCommand) {
                JOptionPane.showMessageDialog(
                    frame,
                    "Eggs aren't supposed to be green.",
                    "A plain message",
                    JOptionPane.PLAIN_MESSAGE
                )
                //information icon
            } else if (command === infoCommand) {
                JOptionPane.showMessageDialog(
                    frame,
                    "Eggs aren't supposed to be green.",
                    "Inane informational dialog",
                    JOptionPane.INFORMATION_MESSAGE
                )
                //XXX: It doesn't make sense to make a question with
//XXX: only one button.
//XXX: See "Yes/No (but not in those words)" for a better solution.
//question icon
            } else if (command === questionCommand) {
                JOptionPane.showMessageDialog(
                    frame,
                    "You shouldn't use a message dialog "
                            + "(like this)\n"
                            + "for a question, OK?",
                    "Inane question",
                    JOptionPane.QUESTION_MESSAGE
                )
                //error icon
            } else if (command === errorCommand) {
                JOptionPane.showMessageDialog(
                    frame,
                    "Eggs aren't supposed to be green.",
                    "Inane error",
                    JOptionPane.ERROR_MESSAGE
                )
                //warning icon
            } else if (command === warningCommand) {
                JOptionPane.showMessageDialog(
                    frame,
                    "Eggs aren't supposed to be green.",
                    "Inane warning",
                    JOptionPane.WARNING_MESSAGE
                )
                //custom icon
            } else if (command === customCommand) {
                JOptionPane.showMessageDialog(
                    frame,
                    "Eggs aren't supposed to be green.",
                    "Inane custom dialog",
                    JOptionPane.INFORMATION_MESSAGE,
                    icon
                )
            }
        })
        return create2ColPane(
            "$iconDesc:",
            radioButtons,
            showItButton
        )
    }

    /** Creates the panel shown by the second tab.  */
    private fun createFeatureDialogBox(): JPanel {
        val numButtons = 5
        val radioButtons = arrayOfNulls<JRadioButton>(numButtons)
        val group = ButtonGroup()
        val showItButton: JButton? //= null
        val pickOneCommand = "pickone"
        val textEnteredCommand = "textfield"
        val nonAutoCommand = "nonautooption"
        val customOptionCommand = "customoption"
        val nonModalCommand = "nonmodal"
        radioButtons[0] = JRadioButton("Pick one of several choices")
        radioButtons[0]!!.actionCommand = pickOneCommand
        radioButtons[1] = JRadioButton("Enter some text")
        radioButtons[1]!!.actionCommand = textEnteredCommand
        radioButtons[2] = JRadioButton("Non-auto-closing dialog")
        radioButtons[2]!!.actionCommand = nonAutoCommand
        radioButtons[3] = JRadioButton(
            "Input-validating dialog "
                    + "(with custom message area)"
        )
        radioButtons[3]!!.actionCommand = customOptionCommand
        radioButtons[4] = JRadioButton("Non-modal dialog")
        radioButtons[4]!!.actionCommand = nonModalCommand
        for (i in 0 until numButtons) {
            group.add(radioButtons[i])
        }
        radioButtons[0]!!.isSelected = true
        showItButton = JButton("Show it!")
        showItButton.addActionListener(ActionListener {
            val command = group.selection.actionCommand
            //pick one of many
            if (command === pickOneCommand) {
                val possibilities = arrayOf<Any>("ham", "spam", "yam")
                val s = JOptionPane.showInputDialog(
                    frame, "Complete the sentence:\n"
                            + "\"Green eggs and...\"",
                    "Customized Dialog",
                    JOptionPane.PLAIN_MESSAGE,
                    icon,
                    possibilities,
                    "ham"
                ) as String
                //If a string was returned, say so.
                if ( s.length > 0) {
                    setLabel("Green eggs and... $s!")
                    return@ActionListener
                }
                //If you're here, the return value was null/empty.
                setLabel("Come on, finish the sentence!")
                //text input
            } else if (command === textEnteredCommand) {
                val s = JOptionPane.showInputDialog(
                    frame, "Complete the sentence:\n"
                            + "\"Green eggs and...\"",
                    "Customized Dialog",
                    JOptionPane.PLAIN_MESSAGE,
                    icon,
                    null,
                    "ham"
                ) as String
                //If a string was returned, say so.
                if ( s.length > 0) {
                    setLabel("Green eggs and... $s!")
                    return@ActionListener
                }
                //If you're here, the return value was null/empty.
                setLabel("Come on, finish the sentence!")
                //non-auto-closing dialog
            } else if (command === nonAutoCommand) {
                val optionPane = JOptionPane(
                    "The only way to close this dialog is by\n"
                            + "pressing one of the following buttons.\n"
                            + "Do you understand?",
                    JOptionPane.QUESTION_MESSAGE,
                    JOptionPane.YES_NO_OPTION
                )
                //You can't use pane.createDialog() because that
//method sets up the JDialog with a property change
//listener that automatically closes the window
//when a button is clicked.
                val dialog = JDialog(
                    frame,
                    "Click a button",
                    true
                )
                dialog.contentPane = optionPane
                dialog.defaultCloseOperation = JDialog.DO_NOTHING_ON_CLOSE
                dialog.addWindowListener(object : WindowAdapter() {
                    override fun windowClosing(we: WindowEvent) {
                        setLabel("Thwarted user attempt to close window.")
                    }
                })
                optionPane.addPropertyChangeListener { e ->
                    val prop = e.propertyName
                    if (dialog.isVisible
                        && e.source === optionPane
                        && JOptionPane.VALUE_PROPERTY == prop
                    ) { //If you were going to check something
//before closing the window, you'd do
//it here.
                        dialog.isVisible = false
                    }
                }
                dialog.pack()
                dialog.setLocationRelativeTo(frame)
                dialog.isVisible = true
                val value = (optionPane.value as Int).toInt()
                if (value == JOptionPane.YES_OPTION) {
                    setLabel("Good.")
                } else if (value == JOptionPane.NO_OPTION) {
                    setLabel(
                        "Try using the window decorations "
                                + "to close the non-auto-closing dialog. "
                                + "You can't!"
                    )
                } else {
                    setLabel("Window unavoidably closed (ESC?).")
                }
                //non-auto-closing dialog with custom message area
//NOTE: if you don't intend to check the input,
//then just use showInputDialog instead.
            } else if (command === customOptionCommand) {
                customDialog.setLocationRelativeTo(frame)
                customDialog.isVisible = true
                val s = customDialog.validatedText
                if (s != null) { //The text is valid.
                    setLabel(
                        "Congratulations!  "
                                + "You entered \""
                                + s
                                + "\"."
                    )
                }
                //non-modal dialog
            } else if (command === nonModalCommand) { //Create the dialog.
                val dialog = JDialog(
                    frame,
                    "A Non-Modal Dialog"
                )
                //Add contents to it. It must have a close button,
//since some L&Fs (notably Java/Metal) don't provide one
//in the window decorations for dialogs.
                val label = JLabel(
                    "<html><p align=center>"
                            + "This is a non-modal dialog.<br>"
                            + "You can have one or more of these up<br>"
                            + "and still use the main window."
                )
                label.horizontalAlignment = JLabel.CENTER
                //val font = label.font
                label.font = label.font.deriveFont(
                    Font.PLAIN,
                    14.0f
                )
                val closeButton = JButton("Close")
                closeButton.addActionListener {
                    dialog.isVisible = false
                    dialog.dispose()
                }
                val closePanel = JPanel()
                closePanel.layout = BoxLayout(
                    closePanel,
                    BoxLayout.LINE_AXIS
                )
                closePanel.add(Box.createHorizontalGlue())
                closePanel.add(closeButton)
                closePanel.border = BorderFactory.createEmptyBorder(0, 0, 5, 5)
                val contentPane = JPanel(BorderLayout())
                contentPane.add(label, BorderLayout.CENTER)
                contentPane.add(closePanel, BorderLayout.PAGE_END)
                contentPane.isOpaque = true
                dialog.contentPane = contentPane
                //Show it.
                dialog.size = Dimension(300, 150)
                dialog.setLocationRelativeTo(frame)
                dialog.isVisible = true
            }
        })
        return createPane(
            "$moreDialogDesc:",
            radioButtons,
            showItButton
        )
    }

    companion object {
        /** Returns an ImageIcon, or null if the path was invalid.  */
        protected fun createImageIcon(path: String): ImageIcon? {
            val imgURL = DialogDemo::class.java.getResource(path)
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
            val frame = JFrame("DialogDemo")
            frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
            //Create and set up the content pane.
            val newContentPane = DialogDemo(frame)
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

    /** Creates the GUI shown inside the frame's content pane.  */
    init {
        customDialog = CustomDialog(frame, "geisel", this)
        customDialog.pack()
        //Create the components.
        val frequentPanel = createSimpleDialogBox()
        val featurePanel = createFeatureDialogBox()
        val iconPanel = createIconDialogBox()
        label = JLabel(
            "Click the \"Show it!\" button"
                    + " to bring up the selected dialog.",
            JLabel.CENTER
        )
        //Lay them out.
        val padding = BorderFactory.createEmptyBorder(20, 20, 5, 20)
        frequentPanel.border = padding
        featurePanel.border = padding
        iconPanel.border = padding
        val tabbedPane = JTabbedPane()
        tabbedPane.addTab(
            "Simple Modal Dialogs", null,
            frequentPanel,
            simpleDialogDesc
        ) //tooltip text
        tabbedPane.addTab(
            "More Dialogs", null,
            featurePanel,
            moreDialogDesc
        ) //tooltip text
        tabbedPane.addTab(
            "Dialog Icons", null,
            iconPanel,
            iconDesc
        ) //tooltip text
        add(tabbedPane, BorderLayout.CENTER)
        add(label, BorderLayout.PAGE_END)
        label.border = BorderFactory.createEmptyBorder(10, 10, 10, 10)
    }
}