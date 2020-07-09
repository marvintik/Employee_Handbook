package marvint.GUI.Department;

import marvint.GUI.MainForm;
import marvint.GUI.Otdel.AddOtdel;
import marvint.GUI.Otdel.DetailOtdel;
import marvint.GUI.Otdel.EditOtdel;
import marvint.domain.Department;
import marvint.domain.Otdel;
import marvint.сontroller.DepartmentController;
import marvint.сontroller.OtdelController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

@Component
public class DepartmentForm {
    @Autowired
    private DepartmentController departmentController;

    @Autowired
    private OtdelController otdelController;

    @Autowired
    private AddDepartment addDepartment;

    @Autowired
    private EditDepartmentt editDepartmentt;

    @Autowired
    private AddOtdel addOtdel;

    @Autowired
    private EditOtdel editOtdel;

    @Autowired
    private MainForm mainForm;

    @Autowired
    private DetailDepartment detailDepartment;

    @Autowired
    private DetailOtdel detailOtdel;

    private JInternalFrame frame;
    private JPanel panel1;
    private JTable table;
    private JTree tree;
    private DefaultTreeModel treeModel;
    private DefaultMutableTreeNode root;
    private DefaultMutableTreeNode departments;

    public JButton addButton() {
        JButton buttonAdd = new JButton();
        buttonAdd.setText("Добавить департамент");
        buttonAdd.setLocation(50, 550);
        buttonAdd.setSize(100, 50);
        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addDepartment.initFrame();
            }
        });
        return buttonAdd;
    }

    public JButton editButton() {
        JButton buttonEdit = new JButton();
        buttonEdit.setText("Изменить департамент");
        buttonEdit.setLocation(50, 550);
        buttonEdit.setSize(100, 50);
        buttonEdit.addActionListener(e -> {
            editDepartmentt.defaultEdit();
            editDepartmentt.initFrame();
        });
        return buttonEdit;
    }

    public JButton addOtdelButton() {
        JButton buttonAdd = new JButton();
        buttonAdd.setText("Добавить отдел");
        buttonAdd.setLocation(50, 550);
        buttonAdd.setSize(100, 50);
        buttonAdd.addActionListener(e -> addOtdel.initFrame());
        return buttonAdd;
    }

    public JButton editOtdelButton() {
        JButton buttonEdit = new JButton();
        buttonEdit.setText("Изменить отдел");
        buttonEdit.setLocation(50, 550);
        buttonEdit.setSize(100, 50);
        buttonEdit.addActionListener(e -> {
            editOtdel.defaultEdit();
            editOtdel.initFrame();
        });
        return buttonEdit;
    }


    public JTree createJTree() {
        treeModel = treeModelS();
        tree = new JTree(treeModel);
        tree.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                            tree.getLastSelectedPathComponent();
                    tree.getSelectionPath();
                    if (node == null) return;
                    Object nodeInfo = node.getUserObject();
                    if (!node.getAllowsChildren()) {
                        var parent = (DefaultMutableTreeNode) node.getParent();
                        var depTitle = parent.getUserObject().toString();
                        Department department = departmentController.getDepartmentByTitle(depTitle);
                        Otdel otdel = otdelController.getOtdelByTitleAndDepantment(nodeInfo.toString(), department);
                        detailOtdel.detailOtdel(otdel);
                        detailOtdel.init();
                    } else if (!tree.isExpanded(tree.getSelectionPath())) {
                        Department department = departmentController.getDepartmentByTitle(nodeInfo.toString());
                        detailDepartment.detailDepartment(department);
                        detailDepartment.init();
                    }
                }
            }
        });
        return tree;
    }

    public DefaultTreeModel treeModelS() {
        String roots = "Список департаментов и подразделений";
        root = new DefaultMutableTreeNode(roots, true);
        List<Department> list = departmentController.getDepartmentList();
        for (int i = 0; i < list.size(); i++) {
            Department department = list.get(i);
            departments = new DefaultMutableTreeNode(department.getTitle(), true);
            List<Otdel> otdelist = department.getOtdel();
            for (int j = 0; j < otdelist.size(); j++) {
                Otdel otdel = otdelist.get(j);
                DefaultMutableTreeNode otd = new DefaultMutableTreeNode(otdel.getTitle(), false);
                departments.add(otd);
            }
            root.add(departments);
        }
        treeModel = new DefaultTreeModel(root, true);
        return treeModel;
    }

    public void editTree() {
        treeModel = treeModelS();
        treeModel.reload();
        tree.setModel(treeModel);
        tree.updateUI();
    }

    public JInternalFrame initFrame() {
        frame = new JInternalFrame("Все подрахделения", true, true, true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());
        tree = createJTree();
        JScrollPane sp = new JScrollPane(tree);
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel1.add(sp, BorderLayout.CENTER);
        JButton buttonAdd = addButton();
        JButton editButton = editButton();
        JButton editOtdelButton = editOtdelButton();
        JButton addOtdelButton = addOtdelButton();
        JPanel buttons = new JPanel(new GridLayout(2, 3));
        buttons.add(buttonAdd);
        buttons.add(editButton);
        buttons.add(addOtdelButton);
        buttons.add(editOtdelButton);
        panel1.add(buttons, BorderLayout.SOUTH);
        frame.getContentPane().add(panel1);
        frame.pack();
        frame.setVisible(true);
        return frame;
    }
}
