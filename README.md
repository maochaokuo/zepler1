# zepler1

## IntelliJ tips
1. Ctrl+Shift+Alt+K may convert Java file to Kotlin file
2. in Kotlin file, copy/paste a section of Java code, will be prompted whether to convert to Kotlin program or not (certainly, Yes)
3. Ctrl-Shift-Alt-S, may specify JDK version. Must use JDK 8,
   or JDK 1.8. If it shows no JDK but actually installed, press
   add button and look for JDK in your installed directory

## change history

### 2020/2/13
1. add ListSelectionDemo.java
2. add TableListSelectionDemo.java
3. JTable selected content
```dtd
getValueAt(int row, int column)
getSelectedRow()
getSelectedColumn()
-- or --
int column = 0;
int row = table.getSelectedRow();
String value = table.getModel().getValueAt(row, column).toString();
```
4. JTable multiple selection
```dtd
you can allow multiple selection by jTable.setRowSelectionAllowed(true);
jTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
and you can get the values by

if (jTable.getSelectedRows() > -1) {
    int[] selectedrows = jTable.getSelectedRows();
    for (int i = 0; i < selectedrows.length; i++)
    {
         System.out.println(jTable.getValueAt(selectedrows[i], 0).toString());
    }
}
```

### 2020/2/12
1. update changing computer issues

### 2020/2/11
1. add JTable samples

### 2020/2/9
1. sqlite utility (basic insert/update/delete/select)
2. my initial desired control pair/set layout, flowlayout for panels
3. my initial menu

### 2020/2/6
1. initial commit
2. add swing example
3. add sqlite connect example, add db and driver, 
    connect successfully