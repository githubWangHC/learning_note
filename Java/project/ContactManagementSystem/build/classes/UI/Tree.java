/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Data.Setmodel;
import static UI.MainFrame.mainshow;
import static UI.MyMenu.detele;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

/**
 *
 * @author Lau Dukang(laudukang@gmail.com)
 */
public class Tree extends JTree {

    //  private JScrollPane jspz;//= new JScrollPane(jtz);;
    public DefaultTreeModel dtm;
    public static String treegroupname = "默认分组";
    public static DefaultMutableTreeNode treeNode;
    public static String parent = new String();
    JPopupMenu menu = new JPopupMenu();
    JMenuItem addnew = new JMenuItem("新建联系人");
    JMenuItem edit = new JMenuItem("修改联系人");
    JMenuItem deleteone = new JMenuItem("删除联系人");
    JMenuItem deletegroup = new JMenuItem("删除组");
    JMenuItem deleteall = new JMenuItem("清空数据");

    public Tree() {

        DefaultMutableTreeNode root = new DefaultMutableTreeNode("联系人   (" + Data.file.getsum() + ")");

        dtm = new DefaultTreeModel(root);
        String[] s = Data.file.getgrouplist();
        int[] crewnumber = Data.file.getcrewnumber();
        String[][] crew = Data.file.getgroupcrew();

        for (int i = 0; i < Data.file.getgroupnumber(); i++) {
            DefaultMutableTreeNode dmtnGroup = new DefaultMutableTreeNode(s[i] + "   (" + crewnumber[i] + ")");
            //DefaultMutableTreeNode node3_1 = new DefaultMutableTreeNode(new TreeNode("1010","张三1","image/2-1.gif", "0"));
            DefaultTreeModel dt = new DefaultTreeModel(dmtnGroup);
            for (int j = 0; j < crewnumber[i]; j++) {

                DefaultMutableTreeNode p = new DefaultMutableTreeNode(crew[i][j]);

                dt.insertNodeInto(p, dmtnGroup, 0);
            }
            dtm.insertNodeInto(dmtnGroup, root, 0);
        }
        DefaultMutableTreeNode collectGroup = new DefaultMutableTreeNode("收藏分组" + "   (" + Data.file.getcollectnumber() + ")");
        DefaultTreeModel dtcollect = new DefaultTreeModel(collectGroup);
        String[] collect = Data.file.getcollect();
        for (int i = 0; i < Data.file.getcollectnumber(); i++) {
            DefaultMutableTreeNode collectcrew = new DefaultMutableTreeNode(collect[i]);
            dtcollect.insertNodeInto(collectcrew, collectGroup, 0);
        }
        dtm.insertNodeInto(collectGroup, root, 0);
        this.setModel(dtm);
        menu.add(addnew);
        menu.add(edit);
        menu.addSeparator();
        menu.add(deleteone);
        menu.add(deletegroup);
        menu.addSeparator();
        menu.add(deleteall);
        this.setDragEnabled(true);
        addnew.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                MainFrame.mainshow.newperson = true;
                String group = "新建分组";
                int i;
                boolean nothas = true;
                for (i = 0; i < mainshow.groupcom.getItemCount(); i++) {
                    if (group.equals(mainshow.groupcom.getItemAt(i).toString())) {
                        nothas = false;
                        break;
                    }
                }
                if (nothas) {
                    mainshow.groupcom.addItem(group);
                }
                String[] op = new String[29];
                for (int p = 0; p < 29; p++) {
                    op[p] = "";
                }
                new Setmodel();
                Data.SetterGetter.set(op);
                Data.SetterGetter.setboolean(true);
                MainFrame.mainshow.save.setEnabled(true);
                MainFrame.mainshow.cancel.setEnabled(true);
                MainFrame.mainshow.upload.setEnabled(true);
                MainFrame.mainshow.delete.setEnabled(true);
                MainFrame.mainshow.modifysure.setSelected(true);
                MainFrame.p1.remove(MainFrame.startshow);
                MainFrame.p1.add(MainFrame.mainshow);
                MainFrame.p1.updateUI();
                System.out.println("new add");
            }
        });
        edit.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (treeNode.toString().endsWith(")")) {
                    return;
                }
                Data.SetterGetter.setboolean(true);
                MainFrame.mainshow.save.setEnabled(true);
                MainFrame.mainshow.cancel.setEnabled(true);
                MainFrame.mainshow.upload.setEnabled(true);
                MainFrame.mainshow.delete.setEnabled(true);
                MainFrame.mainshow.modifysure.setSelected(true);
                String group = "新建分组";
                int i;
                boolean nothas = true;
                for (i = 0; i < mainshow.groupcom.getItemCount(); i++) {
                    if (group.equals(mainshow.groupcom.getItemAt(i).toString())) {
                        nothas = false;
                        break;
                    }
                }
                if (nothas) {
                    mainshow.groupcom.addItem(group);
                }
                System.out.println("edit");
            }
        });
        deleteone.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                 if (Tree.treeNode.toString().endsWith(")")) {
                    JOptionPane.showMessageDialog(null, "您选中的不是联系人节点", "系统提示", JOptionPane.WARNING_MESSAGE);
                    UI.MyMenu.detele = 0;
                    return;
                }
                if (Tree.treeNode.toString().startsWith("收藏分组")) {
                    JOptionPane.showMessageDialog(null, "收藏分组下不可以删除联系人！", "系统提示", JOptionPane.WARNING_MESSAGE);
                    UI.MyMenu.detele = 0;
                    return;
                }
               
                detele = 1;
                Data.file.deletecrew();
                System.out.println("delete one");
            }
        });
        deletegroup.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                if (!Tree.treeNode.toString().endsWith(")")) {
                    JOptionPane.showMessageDialog(null, "您选中的不是分组节点", "系统提示", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (Tree.parent.trim().equals("联系人")) {
                    JOptionPane.showMessageDialog(null, "根节点联系人不可删除！", "系统提示", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (Tree.parent.trim().startsWith("收藏分组")) {
                    JOptionPane.showMessageDialog(null, "收藏分组下不可以删除联系人！", "系统提示", JOptionPane.WARNING_MESSAGE);
                    UI.MyMenu.detele = 0;
                    return;
                }

                if (Tree.parent.trim().equals("默认分组")) {
                    JOptionPane.showMessageDialog(null, "默认分组不可删除！", "系统提示", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                Data.file.deletegroup();
                System.out.println("delete group");
            }
        });
        deleteall.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                Data.file.alldecete();
                System.out.println("delete all");
            }
        });

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                this_mousePressed(e);
            }
        });

        this.addTreeSelectionListener(
                new TreeSelectionListener() {
                    @Override
                    public void valueChanged(TreeSelectionEvent e) {

                        String[] file = new String[29];
                        if (MyMenu.detele == 1) {
                            MyMenu.detele = 0;
                            if (Tree.treeNode != null) {
                                treeNode = treeNode.getPreviousNode();
                                UI.MainFrame.jtz.clearSelection();
                                String[] op = new String[29];

                                for (int p = 0; p < 29; p++) {
                                    op[p] = "";
                                }
                                Data.SetterGetter.set(op);
                                return;
                            }
                            return;
                        } else {
                            treeNode = (DefaultMutableTreeNode) e.getPath().getLastPathComponent();
                        }
                        if (!(treeNode.toString().endsWith(")"))) {
                            //   System.out.println("treeNode.toString()="+treeNode.toString());
//                           if(MyMenu.detele == 1){
//                               MyMenu.detele=0;
//                               return;
//                           }
                            //                           else{ 
                            String parent = treeNode.getParent().toString();
                            int num = 0;
                            for (int i = 0; i < parent.length(); i++) {

                                if (parent.charAt(i) != ' ') {
                                    num++;
                                } else {
                                    break;
                                }
                            }
                            treegroupname = parent.substring(0, num);

                            //   System.out.println(treeNode.toString() + "   " + parent.substring(0, num));
                            String[][] information = Data.file.getinformation();
                            //  System.out.println("information[0][0]=" + information[0][0]);
                            //     System.out.println("Data.file.getsum()=" + Data.file.getsum());
                            //     System.out.println("UI.Search.sumbackup=" + UI.Search.sumbackup);
                            for (int i = 0; i < UI.Search.sumbackup; i++) {//Data.file.getsum()
                                //          System.out.println("iiiiiiiii=" + i);
                           
                                if (information[i][0].equals(treeNode.toString())) {
                                    file = information[i];
                                }
                                //       System.out.println("222222222222");
                            }
                            //          System.out.println("file[0]=" + file[0]);
                            Data.SetterGetter.set(file);
                            Data.SetterGetter.setboolean(false);
                            MainFrame.mainshow.upload.setEnabled(false);
                            MainFrame.mainshow.delete.setEnabled(false);
                            MainFrame.mainshow.save.setEnabled(false);
                            MainFrame.mainshow.cancel.setEnabled(false);
                            MainFrame.mainshow.modifysure.setSelected(false);

                            MainFrame.p1.remove(MainFrame.startshow);
                            MainFrame.p1.add(MainFrame.mainshow);
                            MainFrame.p1.updateUI();

//                           } //   MainFrame.mainshow.updateUI();
                        } else {
                            MainFrame.p1.remove(MainFrame.mainshow);
                            MainFrame.p1.add(MainFrame.startshow);
                            MainFrame.p1.updateUI();
                            MainFrame.mainshow.upload.setEnabled(false);
                            MainFrame.mainshow.delete.setEnabled(false);
                            MainFrame.mainshow.save.setEnabled(false);
                            MainFrame.mainshow.cancel.setEnabled(false);
                            MainFrame.mainshow.modifysure.setSelected(false);
                            //   MainFrame.mainshow.updateUI();

                            String parent2 = treeNode.toString();
                            int num1 = 0;
                            for (int i = 0; i < parent2.length(); i++) {

                                if (parent2.charAt(i) != '(') {
                                    num1++;
                                } else {
                                    break;
                                }
                            }
                            parent = parent2.substring(0, num1);
                        }

                    }
                });

    }

    void this_mousePressed(MouseEvent e) {

        TreePath path = this.getPathForLocation(e.getX(), e.getY());
        if (path == null) {
            return;
        }
        // System.out.println("treepath="+path .toString());
        this.setSelectionPath(path);

        if (e.getButton() == 3) {
            menu.show(this, e.getX(), e.getY());
        }

    }

}
