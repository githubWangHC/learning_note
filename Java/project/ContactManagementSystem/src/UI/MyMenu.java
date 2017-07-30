package UI;

import static UI.MainFrame.mainshow;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.KeyStroke;

/**
 *
 * @author Lau Dukang(laudukang@gmail.com)
 */
public class MyMenu extends JMenuBar {

    JMenuItem filemenuitem[] = {new JMenuItem("导入CSV文件"), new JMenuItem("导出CSV文件"), new JMenuItem("退出")};
    JMenuItem modifymenuitem[] = {new JMenuItem("新建联系人"), new JMenuItem("删除联系人"), new JMenuItem("删除组"), new JMenuItem("清空数据")};
    JCheckBoxMenuItem jcbmi[] = {new JCheckBoxMenuItem("显示状态", true), new JCheckBoxMenuItem("显示工具栏", true)};
    JMenuItem settingmenuitem[] = {new JMenuItem("修改登陆密码")};
    JMenuItem helpmenuitem[] = {new JMenuItem("帮助"), new JMenuItem("关于")};
    public static int detele = 0;

    public MyMenu() {

        JMenu jm1 = new JMenu("文件(F)");
        JMenu jm2 = new JMenu("编辑(E)");
        JMenu jm3 = new JMenu("设置(S)");
        JMenu jm4 = new JMenu("帮助(H)");

        jm1.setMnemonic(KeyEvent.VK_F);
        jm2.setMnemonic(KeyEvent.VK_E);
        jm3.setMnemonic(KeyEvent.VK_S);
        jm4.setMnemonic(KeyEvent.VK_H);

        filemenuitem[0].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        filemenuitem[1].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
        filemenuitem[2].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));

        modifymenuitem[0].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        modifymenuitem[1].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
         modifymenuitem[2].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK));

        helpmenuitem[0].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.ALT_MASK));
        helpmenuitem[1].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.ALT_MASK));

        jm1.setFont(new Font("TimesRoman", Font.PLAIN, 14));// 设置菜单显示的字体
        jm2.setFont(new Font("TimesRoman", Font.PLAIN, 14));// 设置菜单显示的字体
        jm3.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        jm4.setFont(new Font("TimesRoman", Font.PLAIN, 14));

        for (JMenuItem filemenuitem1 : filemenuitem) {
            filemenuitem1.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        }
        for (JMenuItem modifymenuitem1 : modifymenuitem) {
            modifymenuitem1.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        }
        for (JMenuItem settingmenuitem1 : settingmenuitem) {
            settingmenuitem1.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        }
        for (JMenuItem helpmenuitem1 : helpmenuitem) {
            helpmenuitem1.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        }
        for (JCheckBoxMenuItem jcbmi1 : jcbmi) {
            jcbmi1.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        }

        jm1.add(filemenuitem[0]);
        jm1.add(filemenuitem[1]);
        jm1.addSeparator();
        jm1.add(filemenuitem[2]);

        jm2.add(modifymenuitem[0]);
        jm2.add(modifymenuitem[1]);
        jm2.addSeparator();
        jm2.add(modifymenuitem[2]);
        jm2.addSeparator();
        jm2.add(modifymenuitem[3]);

        jm3.add(jcbmi[0]);
        jm3.add(jcbmi[1]);
        jm3.addSeparator();
        jm3.add(settingmenuitem[0]);

        jm4.add(helpmenuitem[0]);
        jm4.addSeparator();
        jm4.add(helpmenuitem[1]);

        this.add(jm1);
        this.add(jm2);
        this.add(jm3);
        this.add(jm4);

        FileMenuItemListener filelistener = new FileMenuItemListener();
        ModifyMenuItemListener modifylistener = new ModifyMenuItemListener();
        SettingMenuItemListener settinglistener = new SettingMenuItemListener();
        HelpMenuItemListener helplistener = new HelpMenuItemListener();
//        filemenuitem[0].addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.out.println("saveeeeee");
//            }
//        });

        filemenuitem[0].addActionListener(filelistener);
        filemenuitem[1].addActionListener(filelistener);
        filemenuitem[2].addActionListener(filelistener);

        modifymenuitem[0].addActionListener(modifylistener);
        modifymenuitem[1].addActionListener(modifylistener);
        modifymenuitem[2].addActionListener(modifylistener);
        modifymenuitem[3].addActionListener(modifylistener);

        jcbmi[0].addActionListener(settinglistener);
        jcbmi[1].addActionListener(settinglistener);
        settingmenuitem[0].addActionListener(settinglistener);

        helpmenuitem[0].addActionListener(helplistener);
        helpmenuitem[1].addActionListener(helplistener);
    }
// JMenuItem filemenuitem[] = {new JMenuItem("保存"), new JMenuItem("导入"), new JMenuItem("导出"), new JMenuItem("退出")};

    public class FileMenuItemListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == filemenuitem[0]) {

                try {
                    Data.file.csvChooser();

                } catch (Exception ex) {
                    Logger.getLogger(MyMenu.class.getName()).log(Level.SEVERE, null, ex);
                }

                //  MainFrame.p1.remove(MainFrame.mainshow);
                //    MainFrame.p1.add(MainFrame.startshow);
                MainFrame.p1.updateUI();
                MainFrame.startshow.updateUI();
                System.out.println("导入done");

            } else if (e.getSource() == filemenuitem[1]) {
//                MainFrame.p1.remove(MainFrame.startshow);
//                MainFrame.p1.add(MainFrame.mainshow);
//
//                //   ImageIcon testimg = new ImageIcon(getClass().getResource("/Icon/welcome.jpg"));
//                ImageIcon testimg = new ImageIcon(new ImageIcon(getClass().getResource("/Icon/welcome.jpg")).getImage().getScaledInstance(145, 165, Image.SCALE_SMOOTH));
//                MainFrame.mainshow.photopaneljlabel.setIcon(testimg);
//
//                ImageIcon star2 = new ImageIcon(new ImageIcon(getClass().getResource("/Icon/star2.gif")).getImage().getScaledInstance(150, 50, Image.SCALE_SMOOTH));
//                MainFrame.mainshow.starjlabel.setIcon(star2);
////MainFrame.mainshow.jtextarea.setText("66666666");
//
//                //  MainFrame.mainshow.nametext.setText("fuvkkkkkkkkkkkkkk");
//                String[] s = new String[29];
//                for (int i = 0; i < 29; i++) {
//                    s[i] = "caonima";
//                }
//
//                Data.SetterGetter.set(s);
//                Data.SetterGetter.setboolean(false);
                //       MainFrame.mainshow.updateUI();
                Data.file.csvSave();

                System.out.println("导出 done");
            } else if (e.getSource() == filemenuitem[2]) {
                System.out.println("Quit done");
                System.exit(0);

            }
        }

    }
    //   JMenuItem modifymenuitem[] = {new JMenuItem("新建联系人"), new JMenuItem("删除联系人"), new JMenuItem("删除组"), new JMenuItem("清空数据")};

    public class ModifyMenuItemListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == modifymenuitem[0]) {
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
                // new Setmodel();
                UI.MainFrame.jtz.clearSelection();
                Data.SetterGetter.set(op);
                Data.SetterGetter.setboolean(true);
                MainFrame.mainshow.save.setEnabled(true);
                MainFrame.mainshow.cancel.setEnabled(true);
                MainFrame.mainshow.upload.setEnabled(true);
                MainFrame.mainshow.delete.setEnabled(true);
                MainFrame.p1.remove(MainFrame.startshow);
                MainFrame.p1.add(MainFrame.mainshow);
                MainFrame.p1.updateUI();
                //   MainFrame.mainshow.modifysure.setSelectedIcon( MainFrame.mainshow.modifysure.getIcon());
                System.out.println("add one done");

            } else if (e.getSource() == modifymenuitem[1]) {
                if (Tree.treeNode.toString().startsWith("收藏分组")) {
                    JOptionPane.showMessageDialog(null, "收藏分组下不可以删除联系人！", "系统提示", JOptionPane.WARNING_MESSAGE);
                    UI.MyMenu.detele = 0;
                    return;
                }
                if (Tree.treeNode.toString().endsWith(")")) {
                    JOptionPane.showMessageDialog(null, "您选中的不是联系人节点", "系统提示", JOptionPane.WARNING_MESSAGE);

                    UI.MyMenu.detele = 0;
                    return;
                }

                detele = 1;
                Data.file.deletecrew();

                System.out.println("delete one done");
            } else if (e.getSource() == modifymenuitem[2]) {
                if (!Tree.treeNode.toString().endsWith(")")) {
                    JOptionPane.showMessageDialog(null, "您选中的不是分组节点", "系统提示", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (Tree.parent.trim().equals("联系人")) {
                    JOptionPane.showMessageDialog(null, "根节点联系人不可删除！", "系统提示", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (Tree.parent.trim().equals("默认分组")) {
                    JOptionPane.showMessageDialog(null, "默认分组不可删除！", "系统提示", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (Tree.parent.trim().equals("收藏分组")) {
                    JOptionPane.showMessageDialog(null, "收藏分组不可删除！", "系统提示", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                Data.file.deletegroup();
                System.out.println("delete one group done");
            } else if (e.getSource() == modifymenuitem[3]) {
                Data.file.alldecete();

                System.out.println("delete all data done");
            }

        }
    }
    //   JCheckBoxMenuItem jcbmi[] = {new JCheckBoxMenuItem("显示状态", true), new JCheckBoxMenuItem("显示工具栏", true)};
    //  JMenuItem settingmenuitem[] = {new JMenuItem("修改登陆密码")};

    public class SettingMenuItemListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == jcbmi[1]) {
                if (MainFrame.tool.isVisible()) {
                    MainFrame.tool.setVisible(false);
                } else {
                    MainFrame.tool.setVisible(true);
                }
                System.out.println("show or hide status done");

            } else if (e.getSource() == jcbmi[0]) {
                if (MainFrame.sta.isVisible()) {
                    MainFrame.sta.setVisible(false);
                } else {
                    MainFrame.sta.setVisible(true);
                }
                System.out.println("show or hide tools done");
            } else if (e.getSource() == settingmenuitem[0]) {
                final JFrame setpasswordframe = new JFrame();
                setpasswordframe.setTitle("修改登陆密码");
                setpasswordframe.setResizable(false); // 设置窗口不可拖动大小
                setpasswordframe.setSize(410, 320);

                setpasswordframe.setUndecorated(false);//禁用或启用此窗体的装饰

                setpasswordframe.setLocationRelativeTo(null);
                setpasswordframe.setVisible(true);

                setpasswordframe.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

                setpasswordframe.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {

                        setpasswordframe.dispose();
                    }
                });
                JPanel spall = new JPanel(new GridLayout(7, 1, 10, 10));
                JPanel sp1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
                JPanel sp2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
                JPanel sp3 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
                JPanel sp4 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));

                final JPasswordField oldpassword = new JPasswordField(13);
                final JPasswordField newpassword = new JPasswordField(13);
                final JPasswordField newpassword2 = new JPasswordField(13);
                oldpassword.setFont(new Font("宋体", Font.BOLD, 16));
                newpassword.setFont(new Font("宋体", Font.BOLD, 16));
                newpassword2.setFont(new Font("宋体", Font.BOLD, 16));
                oldpassword.setEchoChar('*');
                newpassword.setEchoChar('*');
                newpassword2.setEchoChar('*');
                JButton[] spjb = {new JButton("确定"), new JButton("取消")};
                sp1.add(new JLabel("请输入原密码："));
                sp1.add(oldpassword);
                sp2.add(new JLabel("请输入新密码："));
                sp2.add(newpassword);
                sp3.add(new JLabel("                   请再次输入新密码："));
                sp3.add(newpassword2);
                sp4.add(spjb[0]);
                sp4.add(spjb[1]);

                spall.add(new JLabel(""));
                spall.add(sp1);
                spall.add(sp2);
                spall.add(sp3);
                spall.add(sp4);
                spall.add(new JLabel(""));

                setpasswordframe.add(spall);
                spjb[0].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (Data.User.identifypassword(String.valueOf(oldpassword.getPassword()),
                                String.valueOf(newpassword.getPassword()), String.valueOf(newpassword2.getPassword()))) {

                            Data.User.changepassword(String.valueOf(newpassword.getPassword()));

                            JOptionPane.showMessageDialog(setpasswordframe, "修改密码成功！\n请记住您的新密码！", "系统提示",
                                    JOptionPane.INFORMATION_MESSAGE);
                            setpasswordframe.dispose();
                        } else {

                            JOptionPane.showMessageDialog(setpasswordframe, "您输入的原密码有误或两次新密码输入不一致！", "系统提示",
                                    JOptionPane.INFORMATION_MESSAGE);
                            oldpassword.setText("");
                            newpassword.setText("");
                            newpassword2.setText("");
                        }

                    }
                });

                spjb[1].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("取消修改密码");
                        setpasswordframe.dispose();
                    }
                });

                System.out.println("enter modify load password");
            }

        }
    }

//    JMenuItem helpmenuitem[] = {new JMenuItem("帮助"), new JMenuItem("关于")};
    public class HelpMenuItemListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == helpmenuitem[0]) {
//
//                JPanel all = new JPanel(new GridLayout(1, 1, 10, 10));
//
//                JTextArea jt = new JTextArea();
//                jt.setText("一万个caonima的\n"
//                        + "caonima的\n"
//                        + "caonima的\n"
//                        + "caonima的\n"
//                        + "caonima的\n"
//                        + "通讯录管理系统\n");
//                all.add(jt);
//                JOptionPane.showMessageDialog(null, all, "帮助", JOptionPane.CLOSED_OPTION);
                JLabel lb1 = new JLabel("感谢您使用本通讯录管理系统");
                JLabel lb2 = new JLabel("程序版本号：v3.0");
                JLabel lb3 = new JLabel("帮助文本待添加");
                JLabel lb4 = new JLabel("占楼备用");
                JLabel lb5 = new JLabel("未实现的功能：按号码查找联系人、手机号码归属地匹配、Vcard格式文件的导入导出");
                JLabel lb6 = new JLabel("已知问题：备注栏内容不可以输入回车");

                JPanel all = new JPanel(new GridLayout(8, 1, 10, 10));
                all.add(new JLabel(""));
                all.add(lb1);
                all.add(lb2);
                all.add(lb3);
                all.add(lb4);
                all.add(lb5);
                all.add(lb6);
                all.add(new JLabel(""));
                JOptionPane.showConfirmDialog(null, all, "帮助", JOptionPane.DEFAULT_OPTION);

                System.out.println("show help text done ");

            } else if (e.getSource() == helpmenuitem[1]) {
                ImageIcon myicon = new ImageIcon(getClass().getResource("/Icon/about.gif"));
                JLabel lb1 = new JLabel("感谢您使用本通讯录管理系统！");
                JLabel lb2 = new JLabel("当前程序版本号：v3.0");
                JLabel lb3 = new JLabel("2012级计算机科学与技术3班第五小组：");
                JLabel lb4 = new JLabel("刘杜康(laudukang@gmail.com)、林冠德、刘博文                                              ");

                JPanel all = new JPanel(new GridLayout(6, 1, 10, 10));
                all.add(new JLabel(""));
                all.add(lb1);
                all.add(lb2);
                all.add(lb3);
                all.add(lb4);

                JOptionPane.showConfirmDialog(null, all, "关于", JOptionPane.DEFAULT_OPTION, INFORMATION_MESSAGE, myicon);

                System.out.println("show about me");
            }

        }
    }
}
