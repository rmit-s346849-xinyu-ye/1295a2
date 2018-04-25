/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import mininet.Adult;
import mininet.Person;
import mininet.Relation;
import mininet.NoParentException;

/**
 *
 * @author Yifan ZHANG s3615625
 */
public class ListEveryone extends javax.swing.JFrame 
{

    
    
    /**
     * Creates new form ListEveryone
     */
    public ListEveryone() 
    {
        initComponents(); 
        refTable();  
        setFrame();
    }
    
    private void setFrame()
    {
        setResizable(false);
        setLocation(500, 300);
        setSize(470, 350);
    }

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jBProfile = new javax.swing.JButton();
        jBDelete = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        table.setName("table"); // NOI18N
        jScrollPane1.setViewportView(table);

        jBProfile.setText("View Profile");
        jBProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBProfileActionPerformed(evt);
            }
        });

        jBDelete.setText("Delete");
        jBDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jBDelete)
                        .addGap(18, 18, 18)
                        .addComponent(jBProfile)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 8, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBProfile)
                    .addComponent(jBDelete))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBProfileActionPerformed
        String message = "";
        
        if(table.getSelectedRow()<0)
        {
            message = "Please select a person to view his/her profile !";
            JOptionPane.showMessageDialog(null, message ,"Message", JOptionPane.ERROR_MESSAGE); 
            return;
        }
        
        try 
        {
            String name = table.getValueAt(table.getSelectedRow(),0).toString();
            Profile p = new Profile(name);
            p.setVisible(true);
        } 
        catch (Exception ex) 
        {
            JOptionPane.showMessageDialog(null, ex);
        }
    }//GEN-LAST:event_jBProfileActionPerformed

    private void jBDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBDeleteActionPerformed
        
        String message = "";
        
        if(table.getSelectedRow()<0)
        {
            message = "Please select a person to delete !";
            JOptionPane.showMessageDialog(null, message ,"Message", JOptionPane.ERROR_MESSAGE); 
            return;
        }
        
        try 
        {
            String name = (table.getValueAt(table.getSelectedRow(), 0).toString());
            
            //delete the corresponding relations of a particular person
            deleteRelations(name);
            
            List<Person> data = MiniNet.driver.getTheMiniNet();           
            for(int i = 0;i < data.size();i++)
            {
                Person p = data.get(i);
                //delete the corresponding person in the MiniNet
                if(name.equals(p.getName()))
                    data.remove(p);   
            } 
        } 
        //in case the user did not select any row
        //catch the IndexOutOfBoundary Exception
        catch (Exception ex) 
        {
            JOptionPane.showMessageDialog(null, ex);
        }
  
       
    
        //Refreshing the table that listing every existing member in MiniNet
        //after deleting a selected person
        
        refTable();
    }//GEN-LAST:event_jBDeleteActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        this.refTable();
    }//GEN-LAST:event_formWindowOpened

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        this.refTable();
    }//GEN-LAST:event_formWindowActivated

    private void deleteRelations(String name) throws NoParentException
    { 
        List <Relation> relations = MiniNet.driver.getRelations();
        
        //delete the corresponding person's relations in the MiniNet       
        
        
        for(int i = 0;i < relations.size();i++)
	{
            Relation r = relations.get(i);
            
                if(MiniNet.driver.getPersonByName(name) instanceof Adult)
                {
                    if(r.getRelationType().equals("Parent") &&
                            ((r.getName1().equals(name))|| (r.getName2().equals(name))))
                    {
                        throw new NoParentException();
                    }
                    
                }
                
                if((r.getName1().equals(name))|| (r.getName2().equals(name)))
                {
                        relations.remove(r);
                        //restroing the arraylist's size by subtracting 1
                       //from each index after removing the particular object
                       //for looping over all the elements in the arraylist
                        i--;
                }
	} 
    }
       
    private void refTable()
    {	
        DefaultTableModel dtm = new DefaultTableModel();

        String[] tableHeads = new String[]{"Name", "PhotoPath", "Status", "Gender", "Age", "State"};
        
        dtm.setColumnIdentifiers(tableHeads);
        
        List<Person> data = MiniNet.driver.getTheMiniNet();	
        
        for(int i = 0; i < data.size();i++)
        {
            Person p = data.get(i);
            {
                Object[] dataRow =
                {p.getName(),p.getPhotoPath(),
                    p.getStatus(), p.getGender(), p.getAge(), p.getState()};

                dtm.addRow(dataRow);
            }
        }    
        table.setModel(dtm);
    }
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBDelete;
    private javax.swing.JButton jBProfile;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
