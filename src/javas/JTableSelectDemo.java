package javas;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class JTableSelectDemo {

    public static void main(String[] a) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JTable table;

        String[] columnTitles = { "A", "B", "C", "D" };
        Object[][] rowData = { { "11", "12", "13", "14" }, { "21", "22", "23", "24" }
                , { "31", "32", "33", "34" }, { "41", "42", "43", "44" }
                , { "51", "52", "53", "54" }, { "61", "62", "63", "64" }
                , { "71", "72", "73", "74" }, { "81", "82", "83", "84" }
        };

        table = new JTable(rowData, columnTitles);

//        table.setCellSelectionEnabled(true);
        table.setCellSelectionEnabled(false);
        table.setRowSelectionAllowed(true);
        ListSelectionModel cellSelectionModel = table.getSelectionModel();
//        cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
//        cellSelectionModel.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                String selectedData = null;

                int[] selectedRow = table.getSelectedRows();
                int[] selectedColumns = table.getSelectedColumns();

                for (int i = 0; i < selectedRow.length; i++) {
                    for (int j = 0; j < selectedColumns.length; j++) {
                        selectedData = (String) table.getValueAt(selectedRow[i], selectedColumns[j]);
                    }
                }
                Date date = Calendar.getInstance().getTime();
                DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
                String strDate = dateFormat.format(date);

                System.out.println(strDate+" Selected: " + selectedData);
            }

        });

        frame.add(new JScrollPane(table));

        frame.setSize(300, 200);
        frame.setVisible(true);
    }

}