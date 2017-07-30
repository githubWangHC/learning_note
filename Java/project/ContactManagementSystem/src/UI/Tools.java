package UI;

import static UI.MainFrame.mainshow;
import static UI.MyMenu.detele;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JToolBar;
import javax.swing.border.TitledBorder;

public class Tools extends JToolBar {

    JButton bt1 = new JButton("新建", new ImageIcon(
            new ImageIcon(getClass().getResource("/Icon/add.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
    JButton bt2 = new JButton("修改", new ImageIcon(
            new ImageIcon(getClass().getResource("/Icon/modify.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
    JButton bt3 = new JButton("保存", new ImageIcon(
            new ImageIcon(getClass().getResource("/Icon/save.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
    JButton bt4 = new JButton("删除", new ImageIcon(
            new ImageIcon(getClass().getResource("/Icon/delete.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
    JButton bt5 = new JButton("设置", new ImageIcon(
            new ImageIcon(getClass().getResource("/Icon/setting.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
    JButton bt6 = new JButton("退出", new ImageIcon(
            new ImageIcon(getClass().getResource("/Icon/quit.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
    JButton bt7 = new JButton("关于", new ImageIcon(
            new ImageIcon(getClass().getResource("/Icon/help.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));

    public Tools() {
        TitledBorder title = new TitledBorder("");
        this.setBorder(title);
        JButtonListener jblistener = new JButtonListener();
        bt1.addActionListener(jblistener);
        bt2.addActionListener(jblistener);
        bt3.addActionListener(jblistener);
        bt4.addActionListener(jblistener);
        bt5.addActionListener(jblistener);
        bt6.addActionListener(jblistener);
        bt7.addActionListener(jblistener);

        this.add(bt1);
        this.add(bt2);
        this.add(bt3);
        this.add(bt4);
        this.add(bt5);
        this.add(bt7);
        this.add(bt6);

        this.setRollover(true);
      //  this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));

        //  BoxLayout toolsboxlayout = new BoxLayout(this, BoxLayout.X_AXIS);
        //   this.setLayout(  toolsboxlayout);
        //  setFloatable(true);
    }

    class JButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == bt1) {
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
                UI.MainFrame.jtz.clearSelection();
                //   new Setmodel();
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
                System.out.println("tool 添加 done");
            } else if (e.getSource() == bt2) {

                if (Tree.treegroupname.startsWith("收藏分组")) {
                    JOptionPane.showMessageDialog(null, "收藏分组下不可以修改联系人！", "系统提示", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                Data.SetterGetter.setboolean(true);
                MainFrame.mainshow.save.setEnabled(true);
                MainFrame.mainshow.cancel.setEnabled(true);
                MainFrame.mainshow.upload.setEnabled(true);
                MainFrame.mainshow.delete.setEnabled(true);

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

                System.out.println("tool 修改 done");
            } else if (e.getSource() == bt3) {
                if (mainshow.nametext.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "姓名不能为空！", "系统提示", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (mainshow.mobiletext.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "手机不能为空！", "系统提示", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (mainshow.telephonetext.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "电话姓名不能为空！", "系统提示", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                mainshow.savebutton();

                System.out.println("tool 保存 done");
            } else if (e.getSource() == bt4) {
                if (Tree.treeNode.toString().endsWith(")")) {

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
                } else if (Tree.treeNode.toString() != null) {
                    if (Tree.treegroupname.startsWith("收藏分组")) {
                        JOptionPane.showMessageDialog(null, "收藏分组下不可以删除联系人！", "系统提示", JOptionPane.WARNING_MESSAGE);
                        UI.MyMenu.detele = 0;
                        return;
                    }

                    detele = 1;
                    Data.file.deletecrew();
                }
                System.out.println("tool 删除 done");
            } else if (e.getSource() == bt5) {
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

                System.out.println("tool 设置 done");
            } else if (e.getSource() == bt6) {
                System.out.println("tool 退出 done");
                System.exit(0);

            } else if (e.getSource() == bt7) {

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

                System.out.println("tool 帮助 done");
            }
        }
    }
}
