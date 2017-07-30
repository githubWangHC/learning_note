/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import static UI.MainFrame.mainshow;
import UI.Tree;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Administrator
 */
public class file {

    public static String[] groupList = new String[100];
    public static String[][] groupcrew = new String[100][100];
    public static int groupnumber;
    public static int[] crewnumber = new int[100];
    public static int sum;
    public static String[][] information = new String[100][29];
    public static String[] collect = new String[100];
    public static int collectnumber;

    static int csv = 0;
    static String[] picture = new String[100];
    static int picturenumber;
    //static String[][] csvinformation = new String[100][29];

    public static int getcollectnumber() {
        return collectnumber;
    }

    public static String[] getcollect() {
        return collect;
    }

    public static String[][] getinformation() {
        return information;
    }

    public static int getsum() {

        return sum;
    }

    public static String[] getgrouplist() {
        return groupList;
    }

    public static String[][] getgroupcrew() {
        return groupcrew;
    }

    public static int getgroupnumber() {

        return groupnumber;
    }

    public static int[] getcrewnumber() {

        return crewnumber;
    }

    public static String[] getGroupname() {
        String[] groupname = getgrouplist();
        String[] s = new String[groupname.length];
        int j = 0;
        for (int i = groupname.length - 1; i >= 0; i--) {
            s[j] = groupname[i];

            j++;
        }
        return s;
    }

    public static void Loadgroup() throws FileNotFoundException, IOException {

        File file = new File(System.getProperty("user.dir") + "\\文件\\" + LoginForm.getuser());
        // for(File s1:file1.listFiles())
        // System.out.println("fn="+s1.getName());
        groupnumber = 0;
        groupList = file.list();
        // File file = new File(System.getProperty("user.dir") + "\\文件\\" + "\\用户\\"
        //   + "\\groups.txt");
        //  FileReader fr = new FileReader(file);
        //BufferedReader bfr = new BufferedReader(fr);
        // String group;
        // groupList = new String[100];
        int i = 0;

        for (int j = 0; j < groupList.length; j++) {
            //   group = input.nextLine();
            if (groupList[j] != null) {
                // groupList[i] =""+group;
                //groupList[i]  = group;

                //System.out.println(groupList[i]);
                i++;
            }
        }

        groupnumber = i;

        //bfr.close();
    }

    public static void searchgroup(String[] group) {
        crewnumber = new int[100];
        sum = 0;
        picturenumber = 0;
        for (int i = 0; i < groupnumber; i++) {
            File file = new File(System.getProperty("user.dir") + "\\文件\\" + LoginForm.getuser() + "\\"
                    + group[i]);
            // FileReader fre = new FileReader(file);
            //BufferedReader bfr = new BufferedReader(fre);
            //String crew;
            File[] s = file.listFiles();
            // try {
            // System.out.println(s.length);

            int j = 0;
            //System.out.println(s[0].getName());
            for (int a = 0; a < s.length; a++) {

                //groupcrew[i][j]=crew;
                if (s[a].getName().endsWith(".txt")) {
                    groupcrew[i][j] = s[a].getName().substring(0, s[a].getName().length() - 4);
                    // System.out.println("g="+groupcrew[i][j]);
                    j++;

                } else {
                    picture[picturenumber] = s[a].getName();
                    picturenumber++;
                }
            }
            crewnumber[i] = j;
            sum = sum + crewnumber[i];
            //bfr.close();
            //   } catch (FileNotFoundException ex) {
            //    Logger.getLogger(file.class.getName()).log(Level.SEVERE, null, ex);

            //  System.out.println("erro="+ex.toString());
            //  }
        }
    }

    public static void ReadInformation() throws FileNotFoundException {
        int n1 = 0;
        information = new String[100][29];
        for (int i = 0; i < groupnumber; i++) {
            for (int j = 0; j < crewnumber[i]; j++) {
                File file = new File(System.getProperty("user.dir") + "\\文件\\" + LoginForm.getuser() + "\\"
                        + groupList[i] + "\\" + groupcrew[i][j] + ".txt");
                Scanner input = new Scanner(file);
                int n2 = 0;
                while (input.hasNext()) {
                    information[n1][n2] = input.nextLine();
                    //   System.out.println("fuck=" + information[n1][n2]);
                    n2++;
                }
                input.close();
                n1++;
            }
        }
        searchcollect();
        //    System.out.println("fuck1=" + information[0][0]);
    }

    public static void csvSave() {
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.csv", "csv");
        JFileChooser jFileChooser = new JFileChooser(".");
        jFileChooser.setFileFilter(filter);
        int result = jFileChooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File jfile = jFileChooser.getSelectedFile();

            if (!jfile.getName().endsWith(".csv")) {
                jfile = new File(jfile.getPath() + ".csv");
            }
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(jfile, true))) {
                for (int i = 0; i < groupnumber; i++) {
                    //bw.write(groupList[i]);
                    //bw.write("\n");
                    for (int j = 0; j < crewnumber[i]; j++) {
                        File file = new File(System.getProperty("user.dir") + "\\文件\\" + LoginForm.getuser() + "\\"
                                + groupList[i] + "\\" + groupcrew[i][j] + ".txt");
                        bw.write(groupList[i]);
                        bw.write(",");
                        Scanner input = new Scanner(file);

                        int num = 0;
                        while (input.hasNext()) {
                            bw.write(input.nextLine());

                            if (num < 28) {
                                bw.write(",");
                            }
                            num++;
                        }
                        bw.write("\n");
                        input.close();
                    }
                }
                bw.close();
            } catch (IOException ex) {
                Logger.getLogger(file.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null, "保存成功！", "系统提示", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void csvChooser() throws Exception {
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.csv", "csv");
        JFileChooser jFileChooser = new JFileChooser(".");
        jFileChooser.setFileFilter(filter);

        if (jFileChooser.showOpenDialog(null)
                == JFileChooser.APPROVE_OPTION) {
            groupList = new String[100];
            groupcrew = new String[100][100];

            crewnumber = new int[100];

            information = new String[100][29];
            csv = 1;
            File file = jFileChooser.getSelectedFile();
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {

                int x = 0;
                int y = 0;
                int crew1 = 0;
                int count = 0;
                sum = 0;
                groupnumber = 0;
                String inp = new String();
                while ((inp = br.readLine()) != null) {

                    String[] string = inp.split(",");
                    if (count == 0) {
                        groupList[x] = string[0];
                        groupnumber++;

                        crewnumber[x] = 0;
                    } else {
                        if (!(groupList[x].equals(string[0]))) {
                            x++;

                            groupList[x] = string[0];
                            y = 0;
                            groupnumber++;
                            crewnumber[x] = 0;

                        }
                    }
                    groupcrew[x][y] = string[1];

                    crewnumber[x]++;
                    sum++;
                    y++;
                    count++;
                    int crew2 = 0;

                    for (int i = 1; i < string.length; i++) {
                        information[crew1][crew2] = string[i];
                        crew2++;
                    }

                    crew1++;

                }

                br.close();

            }
            UI.Search.sumbackup = Data.file.sum;
            searchcollect();
            new Setmodel();
            JOptionPane.showMessageDialog(null, "导入成功！", "系统提示", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    public static void saveToTxt(String[] str) {
        File file = new File(System.getProperty("user.dir") + "\\文件\\" + LoginForm.getuser() + "\\" + (String) UI.MainFrame.mainshow.groupcom.getSelectedItem() + "\\"
                + UI.MainFrame.mainshow.nametext.getText() + ".txt");
        if (file.exists()) {
            file.delete();
        } else if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("保存用户数据失败！" + e.toString());
            }
        }
        try (PrintWriter out = new PrintWriter(file)) {
            // out.println(str);//一次性写入数据
            for (String sco1 : str) {//分行写入数据

                out.println(sco1);

            }
            out.close();

            //  System.out.println("saveToTxt Done");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(file.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void searchcollect() {
        collectnumber = 0;
        for (int i = 0; i < UI.Search.sumbackup; i++) {
            if ("1".equals(information[i][1])) {
                collect[collectnumber] = information[i][0];
                collectnumber++;
            }
        }
    }

    public static void deletecrew() {
        if (Tree.treeNode == null) {
            return;
        }
        // System.out.println("Tree.treeNode.toString()="+Tree.treegroupname);

        if (UI.MainFrame.mainshow.showdelete && JOptionPane.NO_OPTION == JOptionPane.showConfirmDialog(null, "确定删除该联系人吗？", "系统提示", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE)) {
            UI.MainFrame.mainshow.showdelete = true;
            UI.MyMenu.detele = 0;
            return;
        }

        for (int i = 0; i < groupnumber; i++) {
            for (int j = 0; j < crewnumber[i]; j++) {
                if (Tree.treeNode.toString().equals(groupcrew[i][j])) {
                    File file = new File(System.getProperty("user.dir") + "\\文件\\" + LoginForm.getuser() + "\\"
                            + groupList[i] + "\\" + groupcrew[i][j] + ".txt");
                    file.delete();
                    if (Data.file.setpicture() != null) {
                        File file2 = new File(System.getProperty("user.dir") + "\\文件\\" + LoginForm.getuser() + "\\"
                                + groupList[i] + "\\" + Data.file.setpicture());
                        file2.delete();
                    }
                }
            }
        }
        UI.MainFrame.jtz.clearSelection();
        try {
            Loadgroup();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(file.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(file.class.getName()).log(Level.SEVERE, null, ex);
        }

        searchgroup(Data.file.getgrouplist());
        UI.Search.sumbackup = Data.file.sum;
        try {
            ReadInformation();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(file.class.getName()).log(Level.SEVERE, null, ex);
        }

        new Setmodel();
        
        UI.MainFrame.jtz.dtm.reload();
    }

    public static void deletegroup() {
        if (Tree.parent == null) {
            return;
        }
        if (JOptionPane.NO_OPTION == JOptionPane.showConfirmDialog(null, "确定删除该分组吗？", "系统提示", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE)) {

            return;
        }
        // System.out.println("parent="+Tree.parent);
        if (!(Tree.parent.trim().equals("默认分组"))) {
            if (!(Tree.parent.trim().equals("收藏分组"))) {
                for (int i = 0; i < groupnumber; i++) {
                    if (Tree.parent.trim().equals(groupList[i])) {
                        for (int j = 0; j < crewnumber[i]; j++) {
                            File file = new File(System.getProperty("user.dir") + "\\文件\\" + LoginForm.getuser() + "\\"
                                    + groupList[i] + "\\" + groupcrew[i][j] + ".txt");
                            file.delete();

                        }
                        File file2 = new File(System.getProperty("user.dir") + "\\文件\\" + LoginForm.getuser() + "\\"
                                + groupList[i]);

                        File[] s = file2.listFiles();
                        for (int del = 0; del < s.length; del++) {
                            File file3 = new File(System.getProperty("user.dir") + "\\文件\\" + LoginForm.getuser() + "\\"
                                    + groupList[i] + "\\" + s[del].getName());
                            file3.delete();
                        }
                        //    File file1 = new File(System.getProperty("user.dir") + "\\文件\\" + LoginForm.getuser() + "\\"
                        //           + groupList[i]);
                        file2.delete();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "收藏分组不可删除！", "系统提示", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "默认分组不可删除！", "系统提示", JOptionPane.WARNING_MESSAGE);
        }
        UI.MainFrame.jtz.clearSelection();
        try {
            Loadgroup();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(file.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(file.class.getName()).log(Level.SEVERE, null, ex);
        }

        searchgroup(Data.file.getgrouplist());
        UI.Search.sumbackup = Data.file.sum;
        try {
            ReadInformation();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(file.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        new Setmodel();
      
        mainshow.groupcom.removeAllItems();
        String[] additem = Data.file.getgrouplist();
        for (int add = 0; add < additem.length; add++) {
            mainshow.groupcom.addItem(additem[add]);

        }
    }

    public static void alldecete() {
        if (JOptionPane.NO_OPTION == JOptionPane.showConfirmDialog(null, "确定清空数据吗？", "系统提示", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE)) {
            return;
        }
        if (csv == 1) {
            groupList = new String[100];
            groupList[0] = "默认分组";
            groupcrew = new String[100][100];
            groupnumber = 1;
            crewnumber = new int[100];
            sum = 0;
            information = new String[100][29];
            collect = new String[100];
            collectnumber = 0;
        } else {
            for (int i = 0; i < groupnumber; i++) {
                for (int j = 0; j < crewnumber[i]; j++) {
                    File file = new File(System.getProperty("user.dir") + "\\文件\\" + LoginForm.getuser() + "\\"
                            + groupList[i] + "\\" + groupcrew[i][j] + ".txt");
                    file.delete();

                }
                File file1 = new File(System.getProperty("user.dir") + "\\文件\\" + LoginForm.getuser() + "\\"
                        + groupList[i]);
                File[] s1 = file1.listFiles();
                for (int del = 0; del < s1.length; del++) {
                    File file3 = new File(System.getProperty("user.dir") + "\\文件\\" + LoginForm.getuser() + "\\"
                            + groupList[i] + "\\" + s1[del].getName());
                    file3.delete();
                }
                if (!(groupList[i].equals("默认分组"))) {
                    File file4 = new File(System.getProperty("user.dir") + "\\文件\\" + LoginForm.getuser() + "\\"
                            + groupList[i]);

                    file4.delete();
                }
            }
            //      if( Tree.treeNode.toString()!=null)
            //       Tree.treeNode.removeFromParent();
            UI.MainFrame.jtz.clearSelection();

            try {
                Loadgroup();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(file.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(file.class.getName()).log(Level.SEVERE, null, ex);
            }

            searchgroup(Data.file.getgrouplist());
              UI.Search.sumbackup = Data.file.sum;
            try {
                ReadInformation();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(file.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
      
        new Setmodel();
    
        mainshow.groupcom.removeAllItems();
        String[] additem = Data.file.getgrouplist();
        for (int add = 0; add < additem.length; add++) {
            mainshow.groupcom.addItem(additem[add]);
        }
    }

    public static String setpicture() {

        if (Tree.treeNode == null) {

            return null;
        }
        for (int i = 0; i < picturenumber; i++) {
            if (Tree.treeNode.toString().equals(picture[i].substring(0, picture[i].length() - 4))) {
                return picture[i];
            }
        }

        return null;
    }

}
