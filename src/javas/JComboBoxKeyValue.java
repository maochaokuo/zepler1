package javas;

import java.awt.Component;
/*from  ww w . j  av  a2 s. c o  m*/
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

public class JComboBoxKeyValue {

    public JComboBoxKeyValue() {
        JComboBox comboBox = new JComboBox();
        comboBox.addItem(new Item(1, "-"));
        comboBox.addItem(new Item(2, "X"));
        comboBox.addItem(new Item(3, "Y"));
        comboBox.setMaximumRowCount(3);
        comboBox.setPrototypeDisplayValue(" None of the above ");
        comboBox.addActionListener(e -> {
            JComboBox c = (JComboBox) e.getSource();
            Item item = (Item) c.getSelectedItem();
            System.out.println(item.getId() + " : " + item.getDescription());
        });
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(comboBox);
        frame.pack();
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        new JComboBoxKeyValue();
    }
}
class ItemRenderer extends BasicComboBoxRenderer {
    @Override
    public Component getListCellRendererComponent(JList list, Object value,
                                                  int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected,
                cellHasFocus);
        if (value != null) {
            Item item = (Item) value;
            setText(item.getDescription().toUpperCase());
        }
        if (index == -1) {
            Item item = (Item) value;
            setText("" + item.getId());
        }
        return this;
    }
}
class Item {

    private int id;
    private String description;

    public Item(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }
}
