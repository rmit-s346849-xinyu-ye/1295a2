/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import javax.swing.table.DefaultTableModel;
import mininet.PathNode;
import mininet.Person;
import mininet.Relation;
import mininet.RelationPath;

/**
 *
 * @author xinyuye
 */
public class ConnectionChain extends javax.swing.JFrame {

    /** Creates new form ConnectionChain */
    public ConnectionChain() 
    {
        initComponents();
        refCombos();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jCBName1 = new javax.swing.JComboBox<>();
        jCBName2 = new javax.swing.JComboBox<>();
        jBQuery = new javax.swing.JButton();

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
                "Name", "Name"
            }
        ));
        jScrollPane1.setViewportView(table);

        jLabel1.setText("Please select a person: ");

        jLabel2.setText("Please select another person: ");

        jBQuery.setText("Query");
        jBQuery.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBQueryActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 182, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCBName2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCBName1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 67, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 574, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jBQuery)
                        .addGap(18, 18, 18))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jCBName1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jCBName2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jBQuery)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBQueryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBQueryActionPerformed
        
        Hashtable nodes=new Hashtable();
        
        List<RelationPath> data=new ArrayList<RelationPath>();
        
        creatNodes(nodes);
        
        PathNode p=(PathNode)nodes.get(this.jCBName1.getSelectedItem().toString());
        
        this.findPath(p,this.jCBName2.getSelectedItem().toString(),nodes,data);
        
        List<RelationPath> path=new ArrayList<RelationPath>();
        
        for(RelationPath rp:data)
        {
            if(path.size()==0)
            {
                path.add(rp);
            }
            else
            {
                if(rp.getLength()<path.get(0).getLength())
                {
                    path.clear();
                    path.add(rp);
                }
                else if(rp.getLength()==path.get(0).getLength())
                {
                    path.add(rp);
                }
            }
        }
        
        for(RelationPath rp:path)
        {
            rp.setPath("Shortest Path: ".concat(rp.getPath()));
        }
        
        this.refTable(data);
    }//GEN-LAST:event_jBQueryActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        refCombos();
    }//GEN-LAST:event_formWindowOpened

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        refCombos();
    }//GEN-LAST:event_formWindowActivated
    private void creatNodes(Hashtable nodes)
    {
        List<Relation> relations=MiniNet.driver.getRelations();
        
        List<Person> persons=MiniNet.driver.getTheMiniNet();
        
        for(Person person:persons)
        {
            PathNode pn=new PathNode(null,person.getName(),"");
            nodes.put(person.getName(), pn);
        }
        for(Object entry: nodes.values())
        {
            PathNode pn=(PathNode)entry;
            for(Relation r:relations)
            {
                String name="";
                if(r.getName1().equals(pn.getName()))
                {
                    name=r.getName2();
                }
                else if(r.getName2().equals(pn.getName()))
                {
                    name=r.getName1();
                }
                else
                {
                    continue;
                }
                PathNode child=(PathNode)nodes.get(name);
                if(!pn.getChildren().containsKey(name))
                {                  
                    pn.getChildren().put(name,child);     
                }
            }
        }
        
        
    }
    private void findPath(PathNode p,String target,Hashtable nodes,List<RelationPath> data)
    {
        //Recursivelly call findPath(Pathnode p) to find 
        if(p.getParent()==null)
        {
            p.setRelation(p.getName());
        }else{
            p.setRelation(p.getParent().getRelation()+"<"+this.getRelationBetween(p.getName(), p.getParent().getName())+">"+p.getName());
        }
        
        for(Object entry: p.getChildren().values())
        {
            
            PathNode pn=(PathNode)entry;
            pn.setParent(p);
            if(pn.getName().equals(target))
            {
                data.add(new RelationPath(p.getRelation()+"<"+this.getRelationBetween(pn.getName(), p.getName())+">"+pn.getName()));
                continue;
            }
            if(!(p.getRelation().indexOf(pn.getName())>=0))
            {
                findPath(pn,target,nodes,data);
            }
        }
        
    }
    private String getRelationBetween(String name1,String name2)
    {
        for(Relation r:MiniNet.driver.getRelations())
        {
            if((r.getName1().equals(name1)&&r.getName2().equals(name2))||(r.getName2().equals(name1)&&r.getName1().equals(name2)))
            {
                return r.getRelationType();
            }
        }
        return "";
    }
    
    private void refCombos()
    {
        List<Person> mini = MiniNet.driver.getTheMiniNet();
        
        this.jCBName1.removeAllItems();
        this.jCBName2.removeAllItems();
        
        for (int i = 0; i < mini.size(); i++ )
        {
            this.jCBName1.addItem(mini.get(i).getName());
            this.jCBName2.addItem(mini.get(i).getName());
        }
    }
    
    private void refTable(List<RelationPath> data)
    { 
        DefaultTableModel dtm = new DefaultTableModel();
        
        String[] tableHeads = new String[]{"Relationship"};
    
        dtm.setColumnIdentifiers(tableHeads);
        
        for(int i = 0;i < data.size();i++)
	{
            
            Object[] dataRow ={data.get(i).getPath()};
            
            dtm.addRow(dataRow);	 
	}            
        table.setModel(dtm);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBQuery;
    private javax.swing.JComboBox<String> jCBName1;
    private javax.swing.JComboBox<String> jCBName2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables

}
