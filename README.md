# zepler1

## IntelliJ tips
1. Ctrl+Shift+Alt+K may convert Java file to Kotlin file
2. in Kotlin file, copy/paste a section of Java code, will be prompted whether to convert to Kotlin program or not (certainly, Yes)
3. Ctrl-Shift-Alt-S, may specify JDK version. Must use JDK 8,
   or JDK 1.8. If it shows no JDK but actually installed, press
   add button and look for JDK in your installed directory
4. Ctrl-Shift-I see quick definition   

## to do

### 2020/3/3
1. each menu item clicked, will replace content pane
    with new form
    1. define a base class
    2. get JFrame
    3. clear content pane
    4. load data for the current form
    5. render UIs and prepare callbacks

### 2020/3/1
1. JTextField does not have id or name. If we need to 
    use its collection, need to use Map
```dtd
var fields: MutableMap<String?, JTextField?>
val field = JTextField(15)
fields[fieldName] = field
```
2. Prepare for JComboBox key value example
3. JComboBox selection change event example
4. actionPerformed print too much text

### 2020/2/26
1. it looks flowlayout cannot coexist with scrollpanel, so
    1. single table add/update use flowlayout
    2. query conditions, buttons, table result, use all 
    scrollpanels
2. study combobox, with options from external source
    (like database)    

## change history

### 2020/3/2
1. probably ok to proceed real development

### 2020/3/1
1. finally we got layout solution
    1. use wraplayout for multiple controlls, including
        input controlls and buttons
    2. then put wraplayout into scrollpane
    3. for JTable, put directly into scrollpane
    4. then add all scrollpane in JFrame content pane
2. JTable multiple continuous row selection in 
    JTableSelectDemo.java    

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