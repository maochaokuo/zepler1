package javas.kotlin

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import javax.swing.JFrame
import javax.swing.JScrollPane
import javax.swing.JTable
import javax.swing.ListSelectionModel

object JTableSelectDemo {
    @JvmStatic
    fun main(a: Array<String>) {
        val frame = JFrame()
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        val table: JTable
        val columnTitles = arrayOf("A", "B", "C", "D")
        val rowData = arrayOf(
            arrayOf<Any>("11", "12", "13", "14"),
            arrayOf<Any>("21", "22", "23", "24"),
            arrayOf<Any>("31", "32", "33", "34"),
            arrayOf<Any>("41", "42", "43", "44"),
            arrayOf<Any>("51", "52", "53", "54"),
            arrayOf<Any>("61", "62", "63", "64"),
            arrayOf<Any>("71", "72", "73", "74"),
            arrayOf<Any>("81", "82", "83", "84")
        )
        table = JTable(rowData, columnTitles)
        //        table.setCellSelectionEnabled(true);
        table.cellSelectionEnabled = false
        table.rowSelectionAllowed = true
        val cellSelectionModel = table.selectionModel
        //        cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        cellSelectionModel.selectionMode = ListSelectionModel.SINGLE_INTERVAL_SELECTION
        //        cellSelectionModel.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        cellSelectionModel.addListSelectionListener {
            var selectedData: String? = null
            val selectedRow = table.selectedRows
            val selectedColumns = table.selectedColumns
            for (i in selectedRow.indices) {
                for (j in selectedColumns.indices) {
                    selectedData = table.getValueAt(selectedRow[i], selectedColumns[j]) as String
                }
            }
            val date = Calendar.getInstance().time
            val dateFormat: DateFormat = SimpleDateFormat("yyyy-mm-dd hh:mm:ss")
            val strDate = dateFormat.format(date)
            println("$strDate Selected: $selectedData")
        }
        frame.add(JScrollPane(table))
        frame.setSize(300, 200)
        frame.isVisible = true
    }
}