package UI;

import Data.LoginForm;
import Data.Setmodel;
import static Data.file.Loadgroup;
import static Data.file.ReadInformation;
import static Data.file.searchgroup;
import static UI.MainFrame.mainshow;
import static UI.MyMenu.detele;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Lau Dukang(laudukang@gmail.com)
 */
public class Show extends JPanel implements Runnable {

    //��ӭʹ��
    private final JLabel startjlabel = new JLabel(new ImageIcon(
            new ImageIcon(getClass().getResource("/Icon/welcome.jpg")).getImage().getScaledInstance(580, 360, Image.SCALE_SMOOTH)));

    //��ѡ�����
    public JTabbedPane jtabbedpane = new JTabbedPane(JTabbedPane.RIGHT);//, JTabbedPane.SCROLL_TAB_LAYOUT
    public JPanel basicinfo = new JPanel(new GridLayout(4, 1, 10, 10));
    public JPanel workinfo = new JPanel(new GridLayout(5, 1, 10, 10));//new BorderLayout()
    // BoxLayout workinfoboxlayout = new BoxLayout(workinfo, BoxLayout.Y_AXIS);
    public JPanel internetinfo = new JPanel(new GridLayout(5, 1, 10, 10));

    public JPanel homeinfo = new JPanel(new GridLayout(5, 1, 10, 10));

    //������Ϣ����
    public JTextField nametext = new JTextField(12);
    public JTextField mobiletext = new JTextField(12);
    public JTextField telephonetext = new JTextField(12);

    public JTextField mailtext = new JTextField(12);

    public JTextArea jtextarea = new JTextArea();

    public JButton upload = new JButton("�ϴ�");

    public JButton delete = new JButton("ɾ��");

    public String[] groupname = new String[100];//= {"Ĭ�Ϸ���", "�½�����"};//, "�½�����", "caonima", "hehe"

    public String defaultgroupname = "Ĭ�Ϸ���";
    public String[] birthyears = new String[115];

    public String[] setbirthyears() {
        int temp = 1900;
        for (int i = 0; i <= 114; i++) {

            birthyears[i] = String.valueOf(temp + i);
        }

        return birthyears;
    }

    // {"1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997","1998","1999",
    //    "2000","2001","2002",};
    public String[] birthmonths = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
    public String[] birthdays = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
        "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
        "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};

    public JComboBox groupcom = new JComboBox(Data.file.getgrouplist());  //(groupname);
    public JComboBox birthyearscom = new JComboBox(setbirthyears());
    public JComboBox birthmonthscom = new JComboBox(birthmonths);
    public JComboBox birthdayscom = new JComboBox(birthdays);

    public JPanel photopanel = new JPanel();

    public JLabel photopaneljlabel = new JLabel();

    //  Image  star1 = Toolkit.getDefaultToolkit().getImage("/Icon/welcome.jpg");
    public ImageIcon star1 = new ImageIcon(new ImageIcon(getClass().getResource("/Icon/star1.gif")).getImage().getScaledInstance(150, 50, Image.SCALE_SMOOTH));
    public ImageIcon star2 = new ImageIcon(new ImageIcon(getClass().getResource("/Icon/star2.gif")).getImage().getScaledInstance(150, 50, Image.SCALE_SMOOTH));

    public JLabel starjlabel = new JLabel();
    //{
// public void paintComponent(Graphics g) {
//  super.paintComponent(g);
// ImageIcon img = new javax.swing.ImageIcon(getClass().getResource("/Icon/me.png"));
//  g.drawImage(img.getImage(), 0, 0, null);
// }
//};

    //�����޸ı���ȡ��JPanel
    public JPanel modifypanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
    public JCheckBox modifysure = new JCheckBox();
    public JButton save = new JButton("����");
    public JButton cancel = new JButton("ȡ��");

    //������λ����
    public String[] countryname = {"�й�"};
    public JComboBox countrycom = new JComboBox(countryname);
    public String[] provincename = {"�㶫ʡ", "����ʡ"};
    //���ڳ���������Ϣ�ο���δʹ�õ�
    //java��JComboboxʵ���й�ʡ������������
    //http://blog.csdn.net/qiantujava/article/details/10006949
    public JComboBox provincecom = new JComboBox(provincename);
    public String[] gdcityname = {"������", "�ع���", "������", "�麣��", "��ͷ��",
        "��ɽ��", "������", "տ����", "ï����", "������",
        "������", "÷����", "��β��", "��Դ��", "������",
        "��Զ��", "��ݸ��", "��ɽ��", "������", "������", "�Ƹ���"};
    public String[] hncityname = {"��ɳ��", "������", "��̶��", "������", "������",
        "������", "������", "�żҽ���", "������", "������", "������",
        "������", "¦����", "��������������������"};

    public JComboBox citycom = new JComboBox(gdcityname);
    public JTextField addresstext = new JTextField(31);
    public JTextField companytext = new JTextField(12);
    public JTextField departmenttext = new JTextField(12);
    public JTextField positiontext = new JTextField(12);
    public JTextField zipcodetext = new JTextField(12);
    public JTextField workphonetext = new JTextField(12);
    public JTextField faxtext = new JTextField(12);

    //������Ϣ����
    public JPanel personalhomepagepanel = new JPanel();
    public JTextField personalhomepagetext = new JTextField(28);
    public JLabel personalhomepagejlabel = new JLabel("");
    public String[] imname = {"QQ", "����"};
    public JComboBox imcom = new JComboBox(imname);
    public JTextField imtext = new JTextField(12);
    public JTextField mailtext2 = new JTextField(28);

    //��ͥ��Ϣ����
    public String[] homecountryname = {"�й�"};
    public JComboBox homecountrycom = new JComboBox(homecountryname);
    public String[] homeprovincename = {"�㶫ʡ", "����ʡ"};
    public JComboBox homeprovincecom = new JComboBox(homeprovincename);
    public String[] homegdcityname = {"������", "�ع���", "������", "�麣��", "��ͷ��",
        "��ɽ��", "������", "տ����", "ï����", "������",
        "������", "÷����", "��β��", "��Դ��", "������",
        "��Զ��", "��ݸ��", "��ɽ��", "������", "������", "�Ƹ���"};
    public String[] homehncityname = {"��ɳ��", "������", "��̶��", "������", "������",
        "������", "������", "�żҽ���", "������", "������", "������",
        "������", "¦����", "��������������������"};

    public JComboBox homecitycom = new JComboBox(homegdcityname);
    public JTextField homeaddresstext = new JTextField(31);
    public JTextField homezipcodetext = new JTextField(12);
    public JTextField homephonetext = new JTextField(12);
    public boolean starflagboolean = true;
    String newgroupname;
    File photofile;
    public boolean newperson = false;
    public boolean showdelete = true;
    public boolean photochange = false;
    public boolean newgroup = false;
    public ImageIcon defaultimg = new ImageIcon(
            new ImageIcon(getClass().getResource("/Icon/defaultimg.png")).getImage().getScaledInstance(145, 165, Image.SCALE_SMOOTH));//145 165
//    public String[] getGroupname() {
//        return groupname;
//    }

    public Show() {
        TitledBorder title = new TitledBorder("");
        this.setBorder(title);

        //welcomeimg.setImage(welcomeimg.getImage().getScaledInstance(580, 350, Image.SCALE_DEFAULT));
        this.setLayout(new GridLayout(1, 3, 10, 10));

        this.add(startjlabel);
        new Thread(this).start();
    }

    public Show(String text) {
        new Thread(this).start();
        //START������Ϣ����************************************************************************************************
        JPanel photopaneladd = new JPanel();//new BorderLayout()
        BoxLayout photopaneladdboxlayout = new BoxLayout(photopaneladd, BoxLayout.Y_AXIS);
        JPanel photobuttonpanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        JPanel jp1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        JPanel jp2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        JPanel jp3 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));

        JPanel jpanel1234 = new JPanel();//new GridLayout(3, 1, 10, 10)
        BoxLayout jpanel1234boxlayout = new BoxLayout(jpanel1234, BoxLayout.Y_AXIS);

        JPanel jp4 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        JPanel jpanel1234photo = new JPanel();
        BoxLayout boxlayout1234photo = new BoxLayout(jpanel1234photo, BoxLayout.X_AXIS);

        JPanel jpanel6 = new JPanel();
        BoxLayout boxlayoutjpanel6 = new BoxLayout(jpanel6, BoxLayout.X_AXIS);

        BoxLayout mainshowboxlayout = new BoxLayout(basicinfo, BoxLayout.Y_AXIS);

        starjlabel.setIcon(star2);
        starjlabel.setToolTipText("����������ϵ���ղع��ܣ���ֵ��ӵ�У�˭��˭֪����");
        starjlabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (starflagboolean) {
                    if (e.getClickCount() == 1) {
                        if (!starjlabel.getIcon().equals(star1)) {
                            starjlabel.setIcon(star1);
                        } else {
                            starjlabel.setIcon(star2);
                        }
                    }
                } else {
                    System.out.println("�Ƿ���������ֹ����starͼ��");
                }
            }

        });
        TitledBorder title = new TitledBorder("");
        this.setBorder(title);
        this.setLayout(new GridLayout(1, 3, 10, 10));

        jp1.add(new JLabel("����"));
        jp1.add(nametext);
        jp1.add(new JLabel("      "));
        jp1.add(starjlabel);

        jp2.add(new JLabel("�ֻ�"));
        jp2.add(mobiletext);
        jp2.add(new JLabel("�绰"));
        jp2.add(telephonetext);

        jp3.add(new JLabel("����"));
        jp3.add(mailtext);
        jp3.add(new JLabel("����"));
        jp3.add(groupcom);
        //groupcom.setBorder(BorderFactory.createTitledBorder("��ѡ�����"));
        //     groupcom.setEditable(true);//��JComboBox����ǿɱ༭��.
        //       ComboBoxEditor editor = groupcom.getEditor();
        //       groupcom.configureEditor(editor, defaultgroupname);

        //���Է���
        final showListener listener = new showListener();
        groupcom.addActionListener(listener);

        groupcom.setPreferredSize(new Dimension(143, 30));

        birthyearscom.setPreferredSize(new Dimension(80, 30));

        birthmonthscom.setPreferredSize(new Dimension(80, 30));

        birthdayscom.setPreferredSize(new Dimension(80, 30));
        jp4.add(new JLabel("����"));
        jp4.add(birthyearscom);
        jp4.add(new JLabel("��"));
        jp4.add(birthmonthscom);
        jp4.add(new JLabel("��"));
        jp4.add(birthdayscom);
        jp4.add(new JLabel("��"));

        jpanel1234.add(jp1);
        jpanel1234.add(jp2);
        jpanel1234.add(jp3);
        jpanel1234.add(jp4);
        jpanel1234.setLayout(jpanel1234boxlayout);

        //jpanel123.setPreferredSize(new Dimension(255,200));
        photopaneljlabel.setIcon(defaultimg);
        photopanel.add(photopaneljlabel);

        photopanel.setBorder(title);
        photopanel.setPreferredSize(new Dimension(120, 180));
        // upload.setVisible(false);
        upload.setEnabled(false);
        delete.setEnabled(false);
        upload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFileChooser fileChooser = new JFileChooser(".");

                FileNameExtensionFilter filter = new FileNameExtensionFilter("ͼƬ�ļ�", "jpg", "png", "jpeg", "bmp");//����������    
                //   FileNameExtensionFilter filter1 = new FileNameExtensionFilter("ͼƬ�ļ�png", "png");
                fileChooser.setFileFilter(filter);
                //    fileChooser.setFileFilter(filter1);
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fileChooser.setDialogTitle("��ѡ����Ƭ");
                fileChooser.setFileHidingEnabled(false);

                if (fileChooser.showOpenDialog(null) == javax.swing.JFileChooser.APPROVE_OPTION) {
                    photofile = fileChooser.getSelectedFile();
                    // System.out.println(fileChooser.getCurrentDirectory() + "\\" + fileChooser.getName(file));
                    String path = photofile.getPath();
                    System.out.println("photo path from=" + path);

                    ImageIcon selectimg = new ImageIcon(path);//145 165
                    MainFrame.mainshow.photopaneljlabel.setIcon(new ImageIcon(selectimg.getImage().getScaledInstance(145, 165, Image.SCALE_SMOOTH)));
                    photochange = true;
                }
            }

        });
        delete.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e
                    ) {
                        if (UI.Tree.treegroupname.equals("�ղط���")) {
                            MainFrame.mainshow.photopaneljlabel.setIcon(defaultimg);
                            return;
                        }
                        File file = new File(System.getProperty("user.dir") + "\\�ļ�\\" + LoginForm.getuser() + "\\"
                                + UI.Tree.treegroupname + "\\" + Data.file.setpicture());
                        if (file.exists()) {
                            file.delete();
                            System.out.println(file.getName() + " has been deleted.");
                            MainFrame.mainshow.photopaneljlabel.setIcon(defaultimg);

                            System.out.println("delete lone");
                        }

                    }

                }
        );
        photobuttonpanel.add(upload);

        photobuttonpanel.add(delete);

        photopaneladd.add(photopanel, BorderLayout.CENTER);

        photopaneladd.add(photobuttonpanel, BorderLayout.SOUTH);

        // photopaneladd.setPreferredSize(new Dimension(150, 200));
        photopaneladd.setLayout(photopaneladdboxlayout);

        jpanel1234photo.add(jpanel1234);

        jpanel1234photo.add(photopaneladd);

        jpanel1234photo.setLayout(boxlayout1234photo);

        jpanel6.add(
                new JLabel("    ��ע   "));
        jtextarea.setLineWrap(
                true);
        JScrollPane jtextareajsc = new JScrollPane(jtextarea);

        jpanel6.add(jtextareajsc);

        jpanel6.setLayout(boxlayoutjpanel6);

        jpanel6.setPreferredSize(
                new Dimension(550, 130));
        basicinfo.add(jpanel1234photo);

        basicinfo.add(jpanel6);

        basicinfo.setLayout(mainshowboxlayout);

        //END������Ϣ����****l********************************************************************************************
//START������Ϣ����***********************************************************************************************
        JPanel workjp[] = {new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5)),
            new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5)),
            new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5)),
            new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5)),
            new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5)),};

        workjp[0].add(
                new JLabel("��        ַ"));
        workjp[0].add(countrycom);

        workjp[0].add(provincecom);

        workjp[0].add(citycom);

        countrycom.setPreferredSize(
                new Dimension(112, 30));
        provincecom.setPreferredSize(
                new Dimension(112, 30));
        citycom.setPreferredSize(
                new Dimension(112, 30));

        provincecom.addActionListener(listener);

        workjp[1].add(
                new JLabel("                "));
        workjp[1].add(addresstext);

        workjp[2].add(
                new JLabel("������λ"));
        workjp[2].add(companytext);
        workjp[2].add(
                new JLabel("��������"));
        workjp[2].add(departmenttext);

        workjp[3].add(
                new JLabel("ְ        λ"));
        workjp[3].add(positiontext);

        workjp[3].add(
                new JLabel("��        ��"));
        workjp[3].add(zipcodetext);
        workjp[4].add(
                new JLabel("��        ��"));
        workjp[4].add(workphonetext);
        workjp[4].add(
                new JLabel("��        ��"));
        workjp[4].add(faxtext);

        workinfo.add(workjp[0]);
        workinfo.add(workjp[1]);
        workinfo.add(workjp[2]);
        workinfo.add(workjp[3]);
        workinfo.add(workjp[4]);

        //END������Ϣ����***********************************************************************************************
        //START������Ϣ����***********************************************************************************************
        JPanel internetjp[] = {new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5)),
            new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5)),
            new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5)),};

        personalhomepagepanel.add(
                new JLabel("������ҳ"));
        personalhomepagepanel.add(personalhomepagetext);

        internetjp[0].add(
                new JLabel("��ʱͨ��   "));
        internetjp[0].add(imcom);

        imcom.setPreferredSize(
                new Dimension(90, 30));
        internetjp[0].add(
                new JLabel("       ����   "));
        internetjp[0].add(imtext);

        internetjp[1].add(
                new JLabel("��������"));
        internetjp[1].add(mailtext2);

        internetinfo.add(
                new JLabel(" "));
        internetinfo.add(personalhomepagepanel);

        internetinfo.add(internetjp[0]);
        internetinfo.add(internetjp[1]);
            //END������Ϣ����***********************************************************************************************

        //START��ͥ��Ϣ����***********************************************************************************************
        JPanel homejp[] = {new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5)),
            new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5)),
            new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5)),};

        homejp[0].add(
                new JLabel("��        ַ"));
        homejp[0].add(homecountrycom);

        homejp[0].add(homeprovincecom);

        homejp[0].add(homecitycom);

        homecountrycom.setPreferredSize(
                new Dimension(112, 30));
        homeprovincecom.setPreferredSize(
                new Dimension(112, 30));
        homecitycom.setPreferredSize(
                new Dimension(112, 30));

        homeprovincecom.addActionListener(listener);
        homejp[1].add(
                new JLabel("                "));
        homejp[1].add(homeaddresstext);

        homejp[2].add(
                new JLabel("��        ��"));
        homejp[2].add(homezipcodetext);
        homejp[2].add(
                new JLabel("��        ��"));
        homejp[2].add(homephonetext);

        homeinfo.add(
                new JLabel(""));
        homeinfo.add(homejp[0]);
        homeinfo.add(homejp[1]);
        homeinfo.add(homejp[2]);
        homeinfo.add(
                new JLabel(""));
           //END��ͥ��Ϣ����***********************************************************************************************     

        //��ӵ���ѡ�����
        jtabbedpane.add(
                "<html>��<br>��<br>��<br>Ϣ</html>", basicinfo);
        jtabbedpane.add(
                "<html>��<br>��<br>��<br>λ</html>", workinfo);
        jtabbedpane.add(
                "<html>��<br>��<br>��<br>Ϣ</html>", internetinfo);
        jtabbedpane.add(
                "<html>��<br>ͥ<br>��<br>��</html>", homeinfo);

        //�����޸ı���ȡ������
        modifypanel.add(
                new JLabel("�Ƿ��޸�"));
        modifysure.setSelected(false);
        modifysure.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e
                    ) {
                        if (modifysure.isSelected()) {

                            Data.SetterGetter.setboolean(true);
                            save.setEnabled(true);
                            cancel.setEnabled(true);
                            upload.setEnabled(true);
                            delete.setEnabled(true);

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

                        } else {
                            Data.SetterGetter.setboolean(false);
                            upload.setEnabled(false);
                            delete.setEnabled(false);
                            save.setEnabled(false);
                            cancel.setEnabled(false);

                            //    MainFrame.mainshow.modifypanel.remove(save);
                            //      MainFrame.mainshow.modifypanel.remove(cancel);
                        }

                    }

                }
        );
        modifypanel.add(modifysure);

        modifypanel.add(new JLabel("   "));
        //   save.addActionListener(listener );
        //    cancel.addActionListener(listener );
        modifypanel.add(save);

        modifypanel.add(cancel);
        save.addActionListener(listener);
        cancel.addActionListener(listener);

        save.setEnabled(false);
        cancel.setEnabled(false);
        //���ܵ�show���
        this.setLayout(
                new BorderLayout());

        this.add(jtabbedpane, BorderLayout.CENTER);

        this.add(modifypanel, BorderLayout.SOUTH);

    }

    /*
     public void setJTextArea(String s) {
     jtextarea.setText(s);
     }

     public void setStarJlabelIcon(ImageIcon w) {
     starjlabel.setIcon(w);
     }

     public void setDefaultimg(ImageIcon defaultimg) {

     photopaneljlabel.setIcon(defaultimg);
     }*/
    public void savebutton() {
        if (newperson) {
            String[][] check = Data.file.getinformation();
            for (int ci = 0; ci < Data.file.getsum(); ci++) {
                if (nametext.getText().equals(check[ci][0])) {
                    JOptionPane.showMessageDialog(null, " ��ϵ�ˣ� " + nametext.getText() + " �Ѵ��ڣ����ʵ��", "ϵͳ��ʾ", JOptionPane.INFORMATION_MESSAGE);
                    //    newperson = false;
                    return;
                }
                if (mobiletext.getText().equals(check[ci][2])) {
                    JOptionPane.showMessageDialog(null, " �ֻ��� " + mobiletext.getText() + " �Ѵ��ڣ����ʵ��", "ϵͳ��ʾ", JOptionPane.INFORMATION_MESSAGE);
                    //    newperson = false;
                    return;
                }
                if (telephonetext.getText().equals(check[ci][3])) {
                    JOptionPane.showMessageDialog(null, " �绰�� " + telephonetext.getText() + " �Ѵ��ڣ����ʵ��", "ϵͳ��ʾ", JOptionPane.INFORMATION_MESSAGE);
                    //      newperson = false;
                    return;
                }

            }
        }
        if (newgroupname != null) {
            System.out.println("groupcom.getSelectedItem().toString()=" + groupcom.getSelectedItem().toString());
            File file = new File(System.getProperty("user.dir") + "\\�ļ�\\" + LoginForm.getuser()
                    + "\\" + groupcom.getSelectedItem().toString());
            if (!file.exists() && !file.isDirectory()) {
                file.mkdir();
            }
            newgroupname = null;
            newgroup = false;
            System.out.println("��������ɹ���");
        }

        if ((photochange || newperson) && !mainshow.photopaneljlabel.getIcon().equals(defaultimg)) {
            photochange = false;
            ArrayList<Integer> list = new ArrayList<>();
            InputStream is = null;

            try {

                is = new FileInputStream(photofile);
                BufferedInputStream bis = new BufferedInputStream(is);

                int temp;

                while ((temp = bis.read()) != -1) {
                    list.add(temp);
                }

            } catch (IOException ex) {
                System.out.println("���Ƹ�����Ƭ����=" + ex.toString());
            }

            File file2 = new File(System.getProperty("user.dir") + "\\�ļ�\\" + LoginForm.getuser()
                    + "\\" + groupcom.getSelectedItem().toString() + "\\" + mainshow.nametext.getText() + photofile.getName().substring(photofile.getName().length() - 4, photofile.getName().length()));
            if (file2.exists()) {
                file2.delete();
            }

            try {

                file2.createNewFile();
                OutputStream os = new FileOutputStream(file2);
                BufferedOutputStream bos = new BufferedOutputStream(os);
                for (int i = 0; i < list.size(); i++) {
                    bos.write(list.get(i));
                }
                bos.flush();
                bos.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            System.out.println("upload done");
        }
        //  System.out.println("name=" + nametext.getText());
        Data.file.saveToTxt(Data.SetterGetter.get());
        Data.SetterGetter.setboolean(false);

        if (!newperson) {
////            File file = new File(System.getProperty("user.dir") + "\\�ļ�\\" + LoginForm.getuser() + "\\" + (String) UI.MainFrame.jtz.treegroupname + "\\"
////                    + Tree.treeNode.toString() + ".txt");
////            if (file.exists()) {
////                file.delete();
////            }
            if (Data.file.setpicture() != null) {
                File pfile = new File(System.getProperty("user.dir") + "\\�ļ�\\" + LoginForm.getuser()
                        + "\\" + UI.Tree.treegroupname + "\\" + Data.file.setpicture());
                if (pfile.exists()) {

                    pfile.renameTo(new File(System.getProperty("user.dir") + "\\�ļ�\\" + LoginForm.getuser()
                            + "\\" + UI.Tree.treegroupname + "\\" + nametext.getText()
                            + Data.file.setpicture().substring(Data.file.setpicture().length() - 4, Data.file.setpicture().length())));
                }
            }
            if (UI.Tree.treeNode != null && !nametext.getText().equals(UI.Tree.treeNode.toString())) {
                detele = 1;
                showdelete = false;
                Data.file.deletecrew();
                System.out.println("������ϵ��������ɾ������ϵ��done");
            }
            if (UI.Tree.treeNode != null && !UI.Tree.treegroupname.trim().equals((String) UI.MainFrame.mainshow.groupcom.getSelectedItem())) {
                File photofile = new File(System.getProperty("user.dir") + "\\�ļ�\\" + LoginForm.getuser() + "\\"
                        + UI.Tree.treegroupname + "\\" + Data.file.setpicture());
                if (photofile.exists()) {
                    ArrayList<Integer> list = new ArrayList<>();
                    InputStream is = null;
                    try {
                        is = new FileInputStream(photofile);
                        BufferedInputStream bis = new BufferedInputStream(is);
                        int temp;
                        while ((temp = bis.read()) != -1) {
                            list.add(temp);
                        }
                        bis.close();
                        is.close();
                    } catch (IOException ex) {
                        System.out.println("���Ƹ�����Ƭ����=" + ex.toString());
                    }

                    File file2 = new File(System.getProperty("user.dir") + "\\�ļ�\\" + LoginForm.getuser()
                            + "\\" + groupcom.getSelectedItem().toString() + "\\" + mainshow.nametext.getText() + photofile.getName().substring(photofile.getName().length() - 4, photofile.getName().length()));
                    if (file2.exists()) {
                        file2.delete();
                        System.out.println("file2.delete()");
                    }

                    try {
                        file2.createNewFile();
                        OutputStream os = new FileOutputStream(file2);
                        BufferedOutputStream bos = new BufferedOutputStream(os);
                        for (int i = 0; i < list.size(); i++) {
                            bos.write(list.get(i));
                        }
                        bos.flush();
                        bos.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    photofile.delete();
                    System.out.println("���ķ����ɾ������ϵ����Ƭdone");
                }

                detele = 1;
                showdelete = false;
                Data.file.deletecrew();
                System.out.println("���ķ����ɾ������ϵ��done");
            }

        }

        try {
            Loadgroup();
        } catch (IOException ex) {
            Logger.getLogger(Show.class.getName()).log(Level.SEVERE, null, ex);
        }

        searchgroup(Data.file.getgrouplist());
        UI.Search.sumbackup = Data.file.sum;
        try {
            ReadInformation();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Show.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Data.file.searchcollect();

        new Setmodel();

        newperson = false;
        System.out.println("save to TXT done");
        mainshow.updateUI();
        upload.setEnabled(false);
        delete.setEnabled(false);
        save.setEnabled(false);
        cancel.setEnabled(false);
    }

    @Override
    public void run() {
        System.out.println("show thread running");
        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public class showListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == groupcom) {
                //    mainshow.groupcom.getItemCount() > 1 && 
                if (mainshow.groupcom.getItemCount() > Data.file.getgroupnumber() && mainshow.groupcom.getSelectedIndex() == mainshow.groupcom.getItemCount() - 1) {
                    newgroupname = (String) JOptionPane.showInputDialog(null, "�������µķ������ƣ�",
                            "ϵͳ��ʾ", JOptionPane.PLAIN_MESSAGE, null, null, null);
                    if (newgroupname == null) {
                        return;
                    }
                    boolean add = true;
                    for (int i = 0; i < mainshow.groupcom.getItemCount(); i++) {
                        // System.out.println("fenzu=" + mainshow.groupcom.getItemAt(i).toString());
                        if (newgroupname.equals(mainshow.groupcom.getItemAt(i).toString())) {
                            JOptionPane.showMessageDialog(null, newgroupname + " �Ѵ��ڣ����������룡", "ϵͳ��ʾ", JOptionPane.INFORMATION_MESSAGE);
                            add = false;
                            break;
                        }
                    }
                    if (add) {
                        newgroup = true;
                        mainshow.groupcom.removeItemAt(mainshow.groupcom.getItemCount() - 1);
                        mainshow.groupcom.addItem(newgroupname);
                        mainshow.groupcom.setSelectedIndex(mainshow.groupcom.getItemCount() - 1);
                        mainshow.groupcom.addItem("�½�����");
                    }

                }

            } else if (e.getSource() == provincecom) {

                if (mainshow.provincecom.getSelectedItem().toString().equals("�㶫ʡ")) {
                    String[] ccom = mainshow.gdcityname;
                    mainshow.citycom.removeAllItems();
                    for (String ac : ccom) {
                        mainshow.citycom.addItem(ac);
                    }

                } else if (mainshow.provincecom.getSelectedItem().toString().equals("����ʡ")) {
                    String[] ccom = mainshow.hncityname;
                    mainshow.citycom.removeAllItems();
                    for (String ac : ccom) {
                        mainshow.citycom.addItem(ac);
                    }

                }

            } else if (e.getSource() == homeprovincecom) {
                if (mainshow.homeprovincecom.getSelectedItem().toString().equals("�㶫ʡ")) {
                    String[] ccom = mainshow.homegdcityname;
                    mainshow.homecitycom.removeAllItems();
                    for (String ac : ccom) {
                        mainshow.homecitycom.addItem(ac);
                    }

                } else if (mainshow.homeprovincecom.getSelectedItem().toString().equals("����ʡ")) {
                    String[] ccom = mainshow.homehncityname;
                    mainshow.homecitycom.removeAllItems();
                    for (String ac : ccom) {
                        mainshow.homecitycom.addItem(ac);
                    }

                }

            } else if (e.getSource() == save) {
                if (nametext.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "��������Ϊ�գ�", "ϵͳ��ʾ", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (mobiletext.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "�ֻ�����Ϊ�գ�", "ϵͳ��ʾ", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (telephonetext.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "�绰����Ϊ�գ�", "ϵͳ��ʾ", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                savebutton();
            } else if (e.getSource() == cancel) {
                if (newperson) {
                    newperson = false;
                }
                if (newgroup) {
                    newgroup = false;
                    System.out.println("ȡ���½�����");
                    mainshow.groupcom.removeItemAt(mainshow.groupcom.getItemCount() - 2);
                }
                upload.setEnabled(false);
                delete.setEnabled(false);
                save.setEnabled(false);
                cancel.setEnabled(false);
                modifysure.setSelected(false);
                String[] op = new String[29];
                for (int p = 0; p < 29; p++) {
                    op[p] = "";
                }
                Data.SetterGetter.set(op);
                Data.SetterGetter.setboolean(false);
                MainFrame.mainshow.newperson = false;

                photochange = false;

                System.out.println("cancel save to TXT done");
            }
        }

    }

}
