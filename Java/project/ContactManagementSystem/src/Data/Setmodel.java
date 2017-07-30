/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import UI.MainFrame;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author Administrator
 */
public class Setmodel {
    public Setmodel(){
        Setnewmodel();
    }
    private void Setnewmodel(){
        DefaultMutableTreeNode  root = new DefaultMutableTreeNode("联系人   (" + Data.file.getsum() + ")");

      
      DefaultTreeModel     dtm1 = new DefaultTreeModel(root);
         String[] s= Data.file.getgrouplist();
       int[] crewnumber = Data.file.getcrewnumber();
      String[][] crew = Data.file.getgroupcrew();

        for (int i = 0; i < Data.file.getgroupnumber(); i++) {
            DefaultMutableTreeNode dmtnGroup = new DefaultMutableTreeNode(s[i] + "   (" + crewnumber[i] + ")");
            
            DefaultTreeModel dt = new DefaultTreeModel(dmtnGroup);
            
            for (int j = 0; j < crewnumber[i]; j++) {

                
                
                DefaultMutableTreeNode p = new DefaultMutableTreeNode(crew[i][j]);

                dt.insertNodeInto(p, dmtnGroup, 0);
            }
            dtm1.insertNodeInto(dmtnGroup, root, 0);
        }
           DefaultMutableTreeNode collectGroup = new DefaultMutableTreeNode("收藏分组" + "   (" + Data.file.getcollectnumber() + ")");
        DefaultTreeModel dtcollect = new DefaultTreeModel(collectGroup);
        String[]collect=Data.file.getcollect();
        for(int i=0;i<Data.file.getcollectnumber();i++){
            DefaultMutableTreeNode collectcrew=new DefaultMutableTreeNode(collect[i]);
            dtcollect.insertNodeInto(collectcrew, collectGroup, 0);
        }
        dtm1.insertNodeInto(collectGroup, root, 0);
                     
                     
                     
                    // MainFrame.jtz.
                     
                    MainFrame.jtz.setModel(dtm1);
                      MainFrame.jtz.setScrollsOnExpand(true);
                 MainFrame.jtz.updateUI();
            
                 } 
    }

