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

    JMenuItem filemenuitem[] = {new JMenuItem("����CSV�ļ�"), new JMenuItem("����CSV�ļ�"), new JMenuItem("�˳�")};
    JMenuItem modifymenuitem[] = {new JMenuItem("�½���ϵ��"), new JMenuItem("ɾ����ϵ��"), new JMenuItem("ɾ����"), new JMenuItem("�������")};
    JCheckBoxMenuItem jcbmi[] = {new JCheckBoxMenuItem("��ʾ״̬", true), new JCheckBoxMenuItem("��ʾ������", true)};
    JMenuItem settingmenuitem[] = {new JMenuItem("�޸ĵ�½����")};
    JMenuItem helpmenuitem[] = {new JMenuItem("����"), new JMenuItem("����")};
    public static int detele = 0;

    public MyMenu() {

        JMenu jm1 = new JMenu("�ļ�(F)");
        JMenu jm2 = new JMenu("�༭(E)");
        JMenu jm3 = new JMenu("����(S)");
        JMenu jm4 = new JMenu("����(H)");

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

        jm1.setFont(new Font("TimesRoman", Font.PLAIN, 14));// ���ò˵���ʾ������
        jm2.setFont(new Font("TimesRoman", Font.PLAIN, 14));// ���ò˵���ʾ������
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
// JMenuItem filemenuitem[] = {new JMenuItem("����"), new JMenuItem("����"), new JMenuItem("����"), new JMenuItem("�˳�")};

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
                System.out.println("����done");

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

                System.out.println("���� done");
            } else if (e.getSource() == filemenuitem[2]) {
                System.out.println("Quit done");
                System.exit(0);

            }
        }

    }
    //   JMenuItem modifymenuitem[] = {new JMenuItem("�½���ϵ��"), new JMenuItem("ɾ����ϵ��"), new JMenuItem("ɾ����"), new JMenuItem("�������")};

    public class ModifyMenuItemListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == modifymenuitem[0]) {
                MainFrame.mainshow.newperson = true;
                String group = "�½�����";
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
                if (Tree.treeNode.toString().startsWith("�ղط���")) {
                    JOptionPane.showMessageDialog(null, "�ղط����²�����ɾ����ϵ�ˣ�", "ϵͳ��ʾ", JOptionPane.WARNING_MESSAGE);
                    UI.MyMenu.detele = 0;
                    return;
                }
                if (Tree.treeNode.toString().endsWith(")")) {
                    JOptionPane.showMessageDialog(null, "��ѡ�еĲ�����ϵ�˽ڵ�", "ϵͳ��ʾ", JOptionPane.WARNING_MESSAGE);

                    UI.MyMenu.detele = 0;
                    return;
                }

                detele = 1;
                Data.file.deletecrew();

                System.out.println("delete one done");
            } else if (e.getSource() == modifymenuitem[2]) {
                if (!Tree.treeNode.toString().endsWith(")")) {
                    JOptionPane.showMessageDialog(null, "��ѡ�еĲ��Ƿ���ڵ�", "ϵͳ��ʾ", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (Tree.parent.trim().equals("��ϵ��")) {
                    JOptionPane.showMessageDialog(null, "���ڵ���ϵ�˲���ɾ����", "ϵͳ��ʾ", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (Tree.parent.trim().equals("Ĭ�Ϸ���")) {
                    JOptionPane.showMessageDialog(null, "Ĭ�Ϸ��鲻��ɾ����", "ϵͳ��ʾ", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (Tree.parent.trim().equals("�ղط���")) {
                    JOptionPane.showMessageDialog(null, "�ղط��鲻��ɾ����", "ϵͳ��ʾ", JOptionPane.WARNING_MESSAGE);
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
    //   JCheckBoxMenuItem jcbmi[] = {new JCheckBoxMenuItem("��ʾ״̬", true), new JCheckBoxMenuItem("��ʾ������", true)};
    //  JMenuItem settingmenuitem[] = {new JMenuItem("�޸ĵ�½����")};

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
                setpasswordframe.setTitle("�޸ĵ�½����");
                setpasswordframe.setResizable(false); // ���ô��ڲ����϶���С
                setpasswordframe.setSize(410, 320);

                setpasswordframe.setUndecorated(false);//���û����ô˴����װ��

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
                oldpassword.setFont(new Font("����", Font.BOLD, 16));
                newpassword.setFont(new Font("����", Font.BOLD, 16));
                newpassword2.setFont(new Font("����", Font.BOLD, 16));
                oldpassword.setEchoChar('*');
                newpassword.setEchoChar('*');
                newpassword2.setEchoChar('*');
                JButton[] spjb = {new JButton("ȷ��"), new JButton("ȡ��")};
                sp1.add(new JLabel("������ԭ���룺"));
                sp1.add(oldpassword);
                sp2.add(new JLabel("�����������룺"));
                sp2.add(newpassword);
                sp3.add(new JLabel("                   ���ٴ����������룺"));
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

                            JOptionPane.showMessageDialog(setpasswordframe, "�޸�����ɹ���\n���ס���������룡", "ϵͳ��ʾ",
                                    JOptionPane.INFORMATION_MESSAGE);
                            setpasswordframe.dispose();
                        } else {

                            JOptionPane.showMessageDialog(setpasswordframe, "�������ԭ����������������������벻һ�£�", "ϵͳ��ʾ",
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
                        System.out.println("ȡ���޸�����");
                        setpasswordframe.dispose();
                    }
                });

                System.out.println("enter modify load password");
            }

        }
    }

//    JMenuItem helpmenuitem[] = {new JMenuItem("����"), new JMenuItem("����")};
    public class HelpMenuItemListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == helpmenuitem[0]) {
//
//                JPanel all = new JPanel(new GridLayout(1, 1, 10, 10));
//
//                JTextArea jt = new JTextArea();
//                jt.setText("һ���caonima��\n"
//                        + "caonima��\n"
//                        + "caonima��\n"
//                        + "caonima��\n"
//                        + "caonima��\n"
//                        + "ͨѶ¼����ϵͳ\n");
//                all.add(jt);
//                JOptionPane.showMessageDialog(null, all, "����", JOptionPane.CLOSED_OPTION);
                JLabel lb1 = new JLabel("��л��ʹ�ñ�ͨѶ¼����ϵͳ");
                JLabel lb2 = new JLabel("����汾�ţ�v3.0");
                JLabel lb3 = new JLabel("�����ı������");
                JLabel lb4 = new JLabel("ռ¥����");
                JLabel lb5 = new JLabel("δʵ�ֵĹ��ܣ������������ϵ�ˡ��ֻ����������ƥ�䡢Vcard��ʽ�ļ��ĵ��뵼��");
                JLabel lb6 = new JLabel("��֪���⣺��ע�����ݲ���������س�");

                JPanel all = new JPanel(new GridLayout(8, 1, 10, 10));
                all.add(new JLabel(""));
                all.add(lb1);
                all.add(lb2);
                all.add(lb3);
                all.add(lb4);
                all.add(lb5);
                all.add(lb6);
                all.add(new JLabel(""));
                JOptionPane.showConfirmDialog(null, all, "����", JOptionPane.DEFAULT_OPTION);

                System.out.println("show help text done ");

            } else if (e.getSource() == helpmenuitem[1]) {
                ImageIcon myicon = new ImageIcon(getClass().getResource("/Icon/about.gif"));
                JLabel lb1 = new JLabel("��л��ʹ�ñ�ͨѶ¼����ϵͳ��");
                JLabel lb2 = new JLabel("��ǰ����汾�ţ�v3.0");
                JLabel lb3 = new JLabel("2012���������ѧ�뼼��3�����С�飺");
                JLabel lb4 = new JLabel("���ſ�(laudukang@gmail.com)���ֹڵ¡�������                                              ");

                JPanel all = new JPanel(new GridLayout(6, 1, 10, 10));
                all.add(new JLabel(""));
                all.add(lb1);
                all.add(lb2);
                all.add(lb3);
                all.add(lb4);

                JOptionPane.showConfirmDialog(null, all, "����", JOptionPane.DEFAULT_OPTION, INFORMATION_MESSAGE, myicon);

                System.out.println("show about me");
            }

        }
    }
}
