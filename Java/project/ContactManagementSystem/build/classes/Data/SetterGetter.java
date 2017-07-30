package Data;

//import  UI.MainFrame.mainshow;
import UI.MainFrame;
import static UI.MainFrame.mainshow;
import java.awt.Image;
import java.io.File;
import javax.swing.ImageIcon;

/**
 *
 * @author laudukang
 */
public class SetterGetter {

    public static void set(String[] s) {

        mainshow.nametext.setText(s[0]);
        mainshow.starflagboolean = true;
        //       System.out.println("s[1]=" + s[1]);
        //      System.out.println("s[2]=" + s[2]);
        if (s[1] != null) {
            switch (s[1]) {
                case "1":
                    mainshow.starjlabel.setIcon(mainshow.star1);
                    break;
                case "0":
                    mainshow.starjlabel.setIcon(mainshow.star2);
                    break;
            }
        }

        mainshow.mobiletext.setText(s[2]);
        mainshow.telephonetext.setText(s[3]);
        mainshow.mailtext.setText(s[4]);

//从JTree获取分组信息
        //   mainshow.groupcom.removeAllItems();
//        int y=0;
//       for(String q:Data.file.getGroupname()){System.out.println(q);y++;}
//        System.out.println("y="+y);
        //  System.out.println("Data.file.groupnumber="+Data.file.groupnumber);
        //   for(int i=99;i>99-Data.file.groupnumber;i--)
        //  mainshow.groupcom.addItem(item[i]);
        String group = UI.Tree.treegroupname.trim();
        //   System.out.println("selet="+group);
        //   System.out.println("group=" + group);
        if (group.equals("收藏分组")) {
            for (int i = 0; i < mainshow.groupcom.getItemCount(); i++) {
                group = mainshow.groupcom.getItemAt(i).toString().trim();
                File file = new File(System.getProperty("user.dir") + "\\文件\\" + LoginForm.getuser() + "\\"
                        + group + "\\" + s[0] + ".txt");
                if (file.exists()) {
                    mainshow.groupcom.setSelectedIndex(i);
                    break;
                }
            }
        } else {
            for (int i = 0; i < mainshow.groupcom.getItemCount(); i++) {

                if (mainshow.groupcom.getItemAt(i).toString().equals(group)) {
                    mainshow.groupcom.setSelectedIndex(i);
                    //     System.out.println("set 分组 done");
                }
            }
        }
        //    System.out.println("Data.file.setpicture()=" + Data.file.setpicture());
        if (!MainFrame.mainshow.newperson && Data.file.setpicture() != null) {
            ImageIcon selectimg = new ImageIcon(System.getProperty("user.dir") + "\\文件\\" + LoginForm.getuser() + "\\"
                    + group + "\\" + Data.file.setpicture());//145 165
            File imgf = new File(System.getProperty("user.dir") + "\\文件\\" + LoginForm.getuser() + "\\"
                    + group + "\\" + Data.file.setpicture());
            if (imgf.exists()) {
                MainFrame.mainshow.photopaneljlabel.setIcon(new ImageIcon(selectimg.getImage().getScaledInstance(145, 165, Image.SCALE_SMOOTH)));
            } else {
                MainFrame.mainshow.photopaneljlabel.setIcon(MainFrame.mainshow.defaultimg);
            }

        } else {
            MainFrame.mainshow.photopaneljlabel.setIcon(MainFrame.mainshow.defaultimg);
        }

        //    s[5] = "2000";
        //   System.out.println("s[5]=" + s[5]);
        for (int i = 0; i < mainshow.birthyearscom.getItemCount(); i++) {
            if (mainshow.birthyearscom.getItemAt(i).toString().equals(s[5])) {
                mainshow.birthyearscom.setSelectedIndex(i);
                //      System.out.println("set 出生1 done");
                break;
            }
        }
        //    s[6] = "12";
        for (int i = 0; i < mainshow.birthmonthscom.getItemCount(); i++) {
            if (mainshow.birthmonthscom.getItemAt(i).toString().equals(s[6])) {
                mainshow.birthmonthscom.setSelectedIndex(i);
                //       System.out.println("set 出生2 done");
                break;
            }
        }
        //   s[7] = "28";
        for (int i = 0; i < mainshow.birthdayscom.getItemCount(); i++) {
            if (mainshow.birthdayscom.getItemAt(i).toString().equals(s[7])) {
                mainshow.birthdayscom.setSelectedIndex(i);
                //      System.out.println("set 出生3 done");
                break;
            }
        }
        mainshow.jtextarea.setText(s[8]);
        // mainshow.groupcom.setEnabled(false);
        //      s[9] = "中国";
        for (int i = 0; i < mainshow.countrycom.getItemCount(); i++) {
            if (mainshow.countrycom.getItemAt(i).toString().equals(s[9])) {
                mainshow.countrycom.setSelectedIndex(i);
                //      System.out.println("set 国家 done");
                break;
            }
        }
        //     s[10] = "广东省";
        for (int i = 0; i < mainshow.provincecom.getItemCount(); i++) {
            if (mainshow.provincecom.getItemAt(i).toString().equals(s[10])) {
                mainshow.provincecom.setSelectedIndex(i);
                //       System.out.println("set 省份 done");
                break;
            }
        }
        //    s[11] = "深圳市";

        if ("广东省".equals(s[10])) {
            String[] ccom = mainshow.gdcityname;
            mainshow.citycom.removeAllItems();
            for (String ac : ccom) {
                mainshow.citycom.addItem(ac);
            }

        } else if ("湖南省".equals(s[10])) {
            String[] ccom = mainshow.hncityname;
            mainshow.citycom.removeAllItems();
            for (String ac : ccom) {
                mainshow.citycom.addItem(ac);
            }

        }

        for (int i = 0; i < mainshow.citycom.getItemCount(); i++) {
            //System.out.println(mainshow.groupcom.getItemAt(i).toString());
            if (mainshow.citycom.getItemAt(i).toString().equals(s[11])) {
                mainshow.citycom.setSelectedIndex(i);
                //          System.out.println("set 城市 done");
                break;
            }
        }
        mainshow.addresstext.setText(s[12]);
        mainshow.companytext.setText(s[13]);
        mainshow.departmenttext.setText(s[14]);
        mainshow.positiontext.setText(s[15]);
        mainshow.zipcodetext.setText(s[16]);
        mainshow.workphonetext.setText(s[17]);
        mainshow.faxtext.setText(s[18]);

        mainshow.personalhomepagetext.setText(s[19]);
        //       s[20] = "飞信";
        for (int i = 0; i < mainshow.imcom.getItemCount(); i++) {
            //System.out.println(mainshow.groupcom.getItemAt(i).toString());
            if (mainshow.imcom.getItemAt(i).toString().equals(s[20])) {
                mainshow.imcom.setSelectedIndex(i);
                //       System.out.println("set IMcom done");
                break;
            }
        }
        mainshow.imtext.setText(s[21]);
        mainshow.mailtext2.setText(s[22]);

        //    s[23] = "中国";
        for (int i = 0; i < mainshow.homecountrycom.getItemCount(); i++) {
            if (mainshow.homecountrycom.getItemAt(i).toString().equals(s[23])) {
                mainshow.homecountrycom.setSelectedIndex(i);
                //        System.out.println("set 国家home done");
                break;
            }
        }
        //     s[24] = "广东省";
        for (int i = 0; i < mainshow.homeprovincecom.getItemCount(); i++) {
            if (mainshow.homeprovincecom.getItemAt(i).toString().equals(s[24])) {
                mainshow.homeprovincecom.setSelectedIndex(i);
                //       System.out.println("set 省份home done");
                break;
            }
        }

        if ("广东省".equals(s[24])) {
            String[] ccom = mainshow.homegdcityname;
            mainshow.homecitycom.removeAllItems();
            for (String ac : ccom) {
                mainshow.homecitycom.addItem(ac);
            }

        } else if ("湖南省".equals(s[24])) {
            String[] ccom = mainshow.homehncityname;
            mainshow.homecitycom.removeAllItems();
            for (String ac : ccom) {
                mainshow.homecitycom.addItem(ac);
            }

        }

        //      s[25] = "东莞市";
        for (int i = 0; i < mainshow.homecitycom.getItemCount(); i++) {
            if (mainshow.homecitycom.getItemAt(i).toString().equals(s[25])) {
                mainshow.homecitycom.setSelectedIndex(i);
                //       System.out.println("set 城市home done");
                break;
            }
        }
        mainshow.homeaddresstext.setText(s[26]);
        mainshow.homezipcodetext.setText(s[27]);
        mainshow.homephonetext.setText(s[28]);

        mainshow.updateUI();
    }

    public static String[] get() {

        String[] g = new String[29];
        g[0] = mainshow.nametext.getText().trim();
        if (mainshow.starjlabel.getIcon().equals(mainshow.star1)) {
            g[1] = "1";
        } else {
            g[1] = "0";
        }
        g[2] = mainshow.mobiletext.getText().trim();
        g[3] = mainshow.telephonetext.getText().trim();
        g[4] = mainshow.mailtext.getText().trim();
        //    g[5] = (String) mainshow.groupcom.getSelectedItem();
        g[5] = (String) mainshow.birthyearscom.getSelectedItem();
        g[6] = (String) mainshow.birthmonthscom.getSelectedItem();
        g[7] = (String) mainshow.birthdayscom.getSelectedItem();
        g[8] = mainshow.jtextarea.getText().trim();
        g[9] = (String) mainshow.countrycom.getSelectedItem();
        g[10] = (String) mainshow.provincecom.getSelectedItem();
        g[11] = (String) mainshow.citycom.getSelectedItem();
        g[12] = mainshow.addresstext.getText().trim();
        g[13] = mainshow.companytext.getText().trim();
        g[14] = mainshow.departmenttext.getText().trim();
        g[15] = mainshow.positiontext.getText().trim();
        g[16] = mainshow.zipcodetext.getText().trim();
        g[17] = mainshow.workphonetext.getText().trim();
        g[18] = mainshow.faxtext.getText().trim();
        g[19] = mainshow.personalhomepagetext.getText().trim();
        g[20] = (String) mainshow.imcom.getSelectedItem();
        g[21] = mainshow.imtext.getText().trim();
        g[22] = mainshow.mailtext2.getText().trim();
        g[23] = (String) mainshow.homecountrycom.getSelectedItem();
        g[24] = (String) mainshow.homeprovincecom.getSelectedItem();
        g[25] = (String) mainshow.homecitycom.getSelectedItem();
        //  System.out.println("g[25]homecitycom=" + g[25]);
        g[26] = mainshow.homeaddresstext.getText().trim();
        g[27] = mainshow.homezipcodetext.getText().trim();
        g[28] = mainshow.homephonetext.getText().trim();
        return g;
    }

    public static void setboolean(boolean flag) {

        mainshow.nametext.setEditable(flag);
        mainshow.starflagboolean = flag;
        mainshow.mobiletext.setEditable(flag);
        mainshow.telephonetext.setEditable(flag);
        mainshow.mailtext.setEditable(flag);
        mainshow.groupcom.setEnabled(flag);

        // mainshow.groupcom.setBorder(BorderFactory.createLineBorder(Color.white));
        // mainshow.groupcom.getModel().
        //  mainshow.groupcom.setForeground(Color.red);
        mainshow.birthyearscom.setEnabled(flag);
        mainshow.birthmonthscom.setEnabled(flag);
        mainshow.birthdayscom.setEnabled(flag);
        mainshow.jtextarea.setEditable(flag);
        mainshow.countrycom.setEnabled(flag);
        mainshow.provincecom.setEnabled(flag);
        mainshow.citycom.setEnabled(flag);
        mainshow.addresstext.setEditable(flag);
        mainshow.companytext.setEditable(flag);
        mainshow.departmenttext.setEditable(flag);
        mainshow.positiontext.setEditable(flag);
        mainshow.zipcodetext.setEditable(flag);
        mainshow.workphonetext.setEditable(flag);
        mainshow.faxtext.setEditable(flag);

        mainshow.personalhomepagetext.setEditable(flag);
        mainshow.imcom.setEnabled(flag);
        mainshow.imtext.setEditable(flag);
        mainshow.mailtext2.setEditable(flag);
        mainshow.homecountrycom.setEnabled(flag);;
        mainshow.homeprovincecom.setEnabled(flag);
        mainshow.homecitycom.setEnabled(flag);
        mainshow.homeaddresstext.setEditable(flag);
        mainshow.homezipcodetext.setEditable(flag);
        mainshow.homephonetext.setEditable(flag);

        mainshow.updateUI();
        System.out.println("setEditable done");
    }
}
