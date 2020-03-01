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
import java.awt.Insets
import javax.swing.*
import javax.swing.event.TreeExpansionEvent
import javax.swing.event.TreeExpansionListener
import javax.swing.tree.DefaultMutableTreeNode
import javax.swing.tree.TreeNode

/*
 * TreeExpandEventDemo.java requires no other files.
 */   class TreeExpandEventDemo : JPanel(GridBagLayout()) {
    var demoArea: DemoArea
    var textArea: JTextArea
    fun saySomething(eventDescription: String, e: TreeExpansionEvent) {
        textArea.append(
            eventDescription + "; "
                    + "path = " + e.path
                    + newline
        )
    }

    inner class DemoArea : JScrollPane(), TreeExpansionListener {
        var minSize = Dimension(100, 100)
        var tree: JTree
        private fun createNodes(): TreeNode {
            val root: DefaultMutableTreeNode
            var grandparent: DefaultMutableTreeNode
            var parent: DefaultMutableTreeNode
            var child: DefaultMutableTreeNode
            root = DefaultMutableTreeNode("San Francisco")
            grandparent = DefaultMutableTreeNode("Potrero Hill")
            root.add(grandparent)
            //
            parent = DefaultMutableTreeNode("Restaurants")
            grandparent.add(parent)
            child = DefaultMutableTreeNode("Thai Barbeque")
            parent.add(child)
            child = DefaultMutableTreeNode("Goat Hill Pizza")
            parent.add(child)
            //
            parent = DefaultMutableTreeNode("Grocery Stores")
            grandparent.add(parent)
            child = DefaultMutableTreeNode("Good Life Grocery")
            parent.add(child)
            child = DefaultMutableTreeNode("Safeway")
            parent.add(child)
            grandparent = DefaultMutableTreeNode("Noe Valley")
            root.add(grandparent)
            //
            parent = DefaultMutableTreeNode("Restaurants")
            grandparent.add(parent)
            child = DefaultMutableTreeNode("Hamano Sushi")
            parent.add(child)
            child = DefaultMutableTreeNode("Hahn's Hibachi")
            parent.add(child)
            //
            parent = DefaultMutableTreeNode("Grocery Stores")
            grandparent.add(parent)
            child = DefaultMutableTreeNode("Real Foods")
            parent.add(child)
            child = DefaultMutableTreeNode("Bell Market")
            parent.add(child)
            return root
        }

        override fun getMinimumSize(): Dimension {
            return minSize
        }

        override fun getPreferredSize(): Dimension {
            return minSize
        }

        // Required by TreeExpansionListener interface.
        override fun treeExpanded(e: TreeExpansionEvent) {
            saySomething("Tree-expanded event detected", e)
        }

        // Required by TreeExpansionListener interface.
        override fun treeCollapsed(e: TreeExpansionEvent) {
            saySomething("Tree-collapsed event detected", e)
        }

        init {
            val rootNode = createNodes()
            tree = JTree(rootNode)
            tree.addTreeExpansionListener(this)
            setViewportView(tree)
        }
    }

    companion object {
        const val newline = "\n"
        /**
         * Create the GUI and show it.  For thread safety,
         * this method should be invoked from the
         * event-dispatching thread.
         */
        private fun createAndShowGUI() { //Create and set up the window.
            val frame = JFrame("TreeExpandEventDemo")
            frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
            //Create and set up the content pane.
            val newContentPane: JComponent = TreeExpandEventDemo()
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
        c.fill = GridBagConstraints.BOTH
        c.gridwidth = GridBagConstraints.REMAINDER
        c.weightx = 1.0
        c.weighty = 1.0
        c.insets = Insets(1, 1, 1, 1)
        demoArea = DemoArea()
        gridbag.setConstraints(demoArea, c)
        add(demoArea)
        c.insets = Insets(0, 0, 0, 0)
        textArea = JTextArea()
        textArea.isEditable = false
        val scrollPane = JScrollPane(textArea)
        scrollPane.verticalScrollBarPolicy = JScrollPane.VERTICAL_SCROLLBAR_ALWAYS
        scrollPane.preferredSize = Dimension(200, 75)
        gridbag.setConstraints(scrollPane, c)
        add(scrollPane)
        preferredSize = Dimension(450, 450)
        border = BorderFactory.createEmptyBorder(20, 20, 20, 20)
    }
}