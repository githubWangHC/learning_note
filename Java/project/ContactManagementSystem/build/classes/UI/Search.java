/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Data.Pinyin;
import Data.Setmodel;
import Data.Stack;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.tree.TreeModel;

/**
 *
 * @author Lau Dukang(laudukang@gmail.com)
 */
public class Search extends JPanel {

    public static JTextField jtfMessage = new JTextField(13);
    public static JCheckBox namesearch = new JCheckBox();
    public static JCheckBox numsearch = new JCheckBox();
    public static JCheckBox pinyinsearch = new JCheckBox();
    static String[] groupListbackup;//=Data.file.groupList ;
    static String[][] groupcrewbackup;//= Data.file.groupcrew ;
    static int groupnumberbackup;//=Data.file.groupnumber;
    static int[] crewnumberbackup;//= Data.file.crewnumber ;
    public static int sumbackup = Data.file.sum;
    static String[][] informationbackup;//=Data.file.information ;
    static String[] collectbackup;//=Data.file.collect ;
    static int collectnumberbackup;//=Data.file.collectnumber;
    static boolean first = true;
    static Stack statck = new Stack();
    static String typedstring = "";

    public Search() {

        BoxLayout searchdboxlayout = new BoxLayout(this, BoxLayout.X_AXIS);

        JLabel jlb = new JLabel("查找联系人", JLabel.CENTER);

        Font font = new Font("TimesRoman", Font.PLAIN, 15);

        TitledBorder title = new TitledBorder("");
        //  title.setTitleFont(font);
        //   title.setTitleColor(Color.BLACK);

        jtfMessage.setHorizontalAlignment(JTextField.CENTER);

        jlb.setFont(font);

        jtfMessage.setToolTipText("温馨提示：输入姓名或拼音进行模糊查找，暂不支持号码查找！ 查找结果不改变收藏分组联系人！");
        jtfMessage.addKeyListener(new MyKeyListener());

        this.setBorder(title);

        this.add(jlb);
        this.add(jtfMessage);
        this.add(new JLabel("按姓名查找："));
        this.add(namesearch);
        namesearch.setSelected(true);
        this.add(new JLabel("按拼音查找："));
        this.add(pinyinsearch);
        pinyinsearch.setSelected(true);
        this.add(new JLabel("按号码查找:："));
        this.add(numsearch);
        // numsearch.setEnabled(false);

        this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));

    }

    public static void find(String input) {
        //    ArrayList<TreeModel> list = new ArrayList<>();    
        //  list.add(MainFrame.jtz.getModel());

        statck.push(MainFrame.jtz.getModel());

        if (first) {
//            statck.push(Data.file.groupList);
//             statck.push(Data.file.groupcrew);
//             statck.push(Data.file.groupnumber);
//             statck.push(Data.file.crewnumber);
//             statck.push(Data.file.sum);
//             statck.push(Data.file.information);
//             statck.push(Data.file.collect);
//             statck.push(Data.file.collectnumber);

            groupListbackup = Data.file.groupList;
            groupcrewbackup = Data.file.groupcrew;
            groupnumberbackup = Data.file.groupnumber;
            crewnumberbackup = Data.file.crewnumber;
            sumbackup = Data.file.sum;
            informationbackup = Data.file.information;
            collectbackup = Data.file.collect;
            collectnumberbackup = Data.file.collectnumber;
            first = false;

        }

        String[] groupList2 = new String[100];
        String[][] groupcrew2 = new String[100][100];
        int groupnumber2 = 0;
        int[] crewnumber2 = new int[100];
        int sum2 = 0;
        String[][] information2 = new String[100][29];
        String[] collect2 = new String[100];
        int collectnumber2;
        int jude = 0;
        int m = 0;
        String[] s = Data.file.getgrouplist();
        int[] crewnumber = Data.file.getcrewnumber();

        String[][] crew = Data.file.getgroupcrew();

        for (int i = 0; i < Data.file.getgroupnumber(); i++) {
            jude = 0;
            int n = 0;
            for (int j = 0; j < crewnumber[i]; j++) {
                if (namesearch.isSelected() && crew[i][j].indexOf(input) >= 0) {//.contains(input)
                    jude++;
                    if (jude == 1) {
                        groupList2[m] = s[i];
                        crewnumber2[m] = 0;
                        groupnumber2++;
                        System.out.println("NAME ADD");
                    }
                    groupcrew2[m][n] = crew[i][j];
                    crewnumber2[m]++;
                    sum2++;
                    n++;
                    continue;
                }
                if (pinyinsearch.isSelected()) {
                    Pinyin changeto = new Pinyin();
                    input = input.toLowerCase();
                    String myString = "";
                   // System.out.println("crew[i][j]).toString()=" + crew[i][j].toString());
                    String newString = changeto.GetLetter(crew[i][j]).toString().replace("[", "").replace("]", "");
                    //   System.out.println("chi newString=" + newString);
                    String[] string = newString.split(", ");
                    for (int i2 = 0; i2 < string.length; i2++) {
                        myString = myString + string[i2].charAt(0);
                    }
                    //  System.out.println("myString.toLowerCase()=" + myString.toLowerCase());
                    String fuckstring = changeto.GetLetter(crew[i][j]).toString().replace("[", "").replace("]", "")
                            .replaceAll(",", "").replaceAll(" ", "").toLowerCase();
                    if (myString.toLowerCase().contains(input) || fuckstring.toLowerCase().contains(input)) {
                        jude++;
                        if (jude == 1) {
                            System.out.println("s[i]+" + i);
                            groupList2[m] = s[i];
                            crewnumber2[m] = 0;
                            groupnumber2++;
                            System.out.println("PINYIN ADD");
                        }
                        groupcrew2[m][n] = crew[i][j];
                        crewnumber2[m]++;
                        sum2++;
                        n++;

                    }

                }
            }
            m = groupnumber2;
        }

        for (int i = 0; i < groupnumber2; i++) {
//            System.out.println("groupList 011 =" + groupListbackup[0]);
//            System.out.println("groupList 111 =" + groupListbackup[1]);
//            System.out.println("groupList 211 =" + groupListbackup[2]);
//            System.out.println("groupList2[i]=" + groupList2[i]);
            
            Data.file.groupList[i] = groupList2[i];
            
//            System.out.println("groupList 0111 =" + groupListbackup[0]);
//            System.out.println("groupList 1111 =" + groupListbackup[1]);
//            System.out.println("groupList 2111 =" + groupListbackup[2]);
            Data.file.crewnumber[i] = crewnumber2[i];
            for (int j = 0; j < crewnumber2[i]; j++) {
                Data.file.groupcrew[i][j] = groupcrew2[i][j];
            }
        }

        Data.file.sum = sum2;
        Data.file.groupnumber = groupnumber2;
        Data.file.searchcollect();

        new Setmodel();

    }

    public static class MyKeyListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
            //  System.out.println(jtfMessage.getText());
            typedstring = jtfMessage.getText();
            //  System.out.println("jtfMessage.getText()  typed=" + jtfMessage.getText());
        }

        @Override
        public void keyPressed(KeyEvent e) {
            // System.out.println(jtfMessage.getText());
            //        System.out.println("jtfMessage.getText() pressed=" + jtfMessage.getText());
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                //  System.out.println("check input=" + jtfMessage.getText());
                find(jtfMessage.getText());
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            //     System.out.println("jtfMessage.getText() released=" + jtfMessage.getText());
            if (!typedstring.equals("") && typedstring.length() > jtfMessage.getText().length()) {
                MainFrame.jtz.setModel((TreeModel) statck.pop());
                System.out.println("呢个出栈");
            }

            if (jtfMessage.getText().equals("")) {
                //if (e.getKeyCode()==KeyEvent.VK_A){
                if (statck.getStatckSize() != 0) {

             
//                    Data.file.collectnumber =(int)statck.pop();
//             Data.file.collect =(String[])statck.pop();
//              Data.file.information = (String[][])statck.pop();
//              Data.file.sum = (int)statck.pop();
//              Data.file.crewnumber = (int[])statck.pop();
//               Data.file.groupnumber =(int)statck.pop();
//                 Data.file.groupcrew = (String[][])statck.pop();
//             Data.file.groupList = (String[])statck.pop();
//                    System.out.println("Data.file.groupList0="+Data.file.groupList[0]);
//   System.out.println("Data.file.groupList1="+Data.file.groupList[1]);
//    System.out.println("Data.file.groupList2="+Data.file.groupList[2]);
                    MainFrame.jtz.setModel((TreeModel) statck.getTopObjcet());
                    statck.clear();
                    Data.file.groupList = groupListbackup;
                    Data.file.groupcrew = groupcrewbackup;
                    Data.file.groupnumber = groupnumberbackup;
                    Data.file.crewnumber = crewnumberbackup;
                    Data.file.sum = sumbackup;
                    System.out.println("sumbackup" + sumbackup);
                    Data.file.information = informationbackup;
                    Data.file.collect = collectbackup;
                    Data.file.collectnumber = collectnumberbackup;

                    first = true;
                    // new Setmodel();
                    System.out.println("出栈done");
                }
            }
        }
    }
}
