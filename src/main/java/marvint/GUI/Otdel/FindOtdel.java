package marvint.GUI.Otdel;

import lombok.SneakyThrows;
import marvint.GUI.Otdel.DetailOtdel;
import marvint.GUI.Otdel.FindOtdel;
import marvint.GUI.MainForm;
import marvint.domain.Employee;
import marvint.domain.Otdel;
import marvint.domain.OtdelFilter;
import marvint.domain.Otdel;
import marvint.сontroller.OtdelController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class FindOtdel {
    @Autowired
    private OtdelController otdelController;
    @Autowired
    private DetailOtdel detailOtdel;
    @Autowired
    private MainForm mainForm;
    private JInternalFrame frame;
    private JPanel panel1;
    private JTable table;
    JTextField id;
    JTextField title;
    JTextField adress;
    JTextField department;


    public class OtdelTableModel implements TableModel {
        private List<Otdel> otdelList;
        private Set<TableModelListener> listeners = new HashSet<>();

        public OtdelTableModel(List<Otdel> otdelList) {
            this.otdelList = otdelList;
        }

        @Override
        public int getRowCount() {
            return otdelList.size();
        }

        @Override
        public int getColumnCount() {
            return 4;
        }

        @Override
        public String getColumnName(int columnIndex) {
            switch (columnIndex) {
                case 0:
                    return "ID";
                case 1:
                    return "Название";
                case 2:
                    return "Адрес";
                case 3:
                    return "Департамент";

            }
            return "";
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            var mockOtdel = new Otdel();
            switch (columnIndex) {
                case 0:
                    return Long.class;
                case 1:
                case 2:
                case 3:
                    return String.class;
            }
            return null;
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Otdel otdel = otdelList.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return otdel.getId();
                case 1:
                    return otdel.getTitle();
                case 2:
                    return otdel.getAddress();
                case 3:
                    return otdel.getDepartment().getTitle();
            }
            return null;
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        }

        @Override
        public void addTableModelListener(TableModelListener l) {
            listeners.add(l);
        }

        @Override
        public void removeTableModelListener(TableModelListener l) {
            listeners.remove(l);
        }
    }


    public JTable displayTable() {
        List<Otdel> otdelList = otdelController.getOtdelList();
        TableModel model = new FindOtdel.OtdelTableModel(otdelList);
        return new JTable(model);
    }

    public void updateOtdel(){
        List<Otdel> otdelList = otdelController.getOtdelList();
        TableModel model = new FindOtdel.OtdelTableModel(otdelList);
        table.setModel(model);
        table.revalidate();
    }

    public JButton findButton(){
        JButton buttonAdd = new JButton();
        buttonAdd.setText("Поиск");
        buttonAdd.setLocation(50,550);
        buttonAdd.setSize(100,50);
        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OtdelFilter otdelFilter = new OtdelFilter();
                if (!id.getText().equals("")) { otdelFilter.setId(Long.parseLong(id.getText()));}
                otdelFilter.setTitle(title.getText());
                otdelFilter.setAddress(adress.getText());
                otdelFilter.setDepartment(department.getText());
                List<Otdel> otdelList= otdelController.getOtdelsByFilter(otdelFilter);
                TableModel model = new FindOtdel.OtdelTableModel(otdelList);
                table.setModel(model);
                table.revalidate();
            }
        });
        return buttonAdd;
    }

    public JPanel findPanel() {
        JPanel panelFind = new JPanel();
        panelFind.setLayout(new GridLayout(2,4));
        panelFind.add(new JLabel("id"));
        id = new JTextField();
        panelFind.add(id);
        panelFind.add(new JLabel("Название"));
        title = new JTextField();
        panelFind.add(title);
        panelFind.add(new JLabel("Адрес"));
        adress = new JTextField();
        panelFind.add(adress);
        panelFind.add(new JLabel("Департмент"));
        department = new JTextField();
        panelFind.add(department);
        return panelFind;
    }

    public void initFrame() {
        frame = new JInternalFrame("Поиск отдела", true, true, true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());
        panel1.add(findPanel(), BorderLayout.NORTH);
        table = displayTable();
        table.addMouseListener(setMouseAddaptTable());
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        JScrollPane sp = new JScrollPane(table);
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel1.add(sp, BorderLayout.CENTER);
        JButton buttonFind = findButton();
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttons.add(buttonFind);
        panel1.add(buttons, BorderLayout.SOUTH);
        frame.getContentPane().add(panel1);
        frame.pack();
        mainForm.pane.add(frame);
        frame.setVisible(true);
    }

    public MouseAdapter setMouseAddaptTable() {
        MouseAdapter mouseAdapter= new MouseAdapter() {
            @SneakyThrows
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    var value = table.getValueAt(table.getSelectedRow(),0);
                    Otdel otdel = otdelController.getOtdelById(Long.parseLong(value.toString()));
                    detailOtdel.detailOtdel(otdel);
                    detailOtdel.init();
                }}
        };
        return mouseAdapter;
    }
}
