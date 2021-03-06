package gui;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import mininet.Person;
import mininet.Relation;
/**
 *
 * @author Xinyu YE s3468489
 */
public class QueryRelationship extends javax.swing.JFrame 
{
    
    /**
     * Creates new form QueryRelationship
     */
    public QueryRelationship()
    {
        initComponents();  
        refCombos();
        setFrame();
    }
     
    private void setFrame()
    {
        setResizable(false);
        setLocation(500, 300);
        setSize(450, 395);
    }
    
    private void initComponents() 
    {

        jLabName1 = new javax.swing.JLabel();
        jLabName2 = new javax.swing.JLabel();
        jCBName1 = new javax.swing.JComboBox<>();
        jCBName2 = new javax.swing.JComboBox<>();
        jBQuery = new javax.swing.JButton();
        jScrollPane = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
       
        addWindowListener(new java.awt.event.WindowAdapter() 
        {
            public void windowOpened(java.awt.event.WindowEvent evt) 
            {
                formWindowOpened(evt);
            }
            public void windowActivated(java.awt.event.WindowEvent evt) 
            {
                formWindowActivated(evt);
            }
        });

        jLabName1.setText("Please select a person： ");

        jLabName2.setText("Please select another person： ");

        jBQuery.setText("Query");
        
        jBQuery.addActionListener(new java.awt.event.ActionListener() 
        {
            public void actionPerformed(java.awt.event.ActionEvent evt) 
            {
                jBQueryActionPerformed(evt);
            }
        });

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Name", "Relationship"
            }
        ));
        jScrollPane.setViewportView(table);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabName1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabName2))
                        .addGap(52, 52, 52)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCBName1, 0, 179, Short.MAX_VALUE)
                            .addComponent(jCBName2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jBQuery))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabName1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCBName1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabName2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jCBName2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jBQuery))
        );

        pack();
    }
    
    /**
     * If the window is activated,
     * refresh the combo-box storing names of persons,
     * in case of new persons been added
     * 
     * @param evt
     */
    private void formWindowActivated(java.awt.event.WindowEvent evt)
    {
        this.refCombos();
    }
    
    /**
     * If the window is opened,
     * refresh the combo-boxes storing names of persons,
     * in case of new persons been added
     * 
     * @param evt
     */
    private void formWindowOpened(java.awt.event.WindowEvent evt) 
    {
        this.refCombos();
    }

    /**
     * To query the relations between the selected two persons
     * 
     * @param evt
     */
    private void jBQueryActionPerformed(java.awt.event.ActionEvent evt) 
    {//Refresh the table to display the relation(s) between two selected persons
      refTable(query(jCBName1.getSelectedItem().toString(), jCBName2.getSelectedItem().toString()));      
    }
    
    /**
     * 
     * @param name1
     * @param name2
     * @return an arrayList storing objects of the Relation type
     */
    private List<Relation> query(String name1, String name2)
    {     
        List <Relation> relations = MiniNet.driver.getRelations();
        List <Relation> result = new ArrayList<Relation>();
        
        for(int i = 0;i < relations.size();i++)
        {
            Relation r = relations.get(i);
            
            if((name1.equals(r.getName1()) && name2.equals(r.getName2()))||
               (name2.equals(r.getName1()) && name1.equals(r.getName2())))
            {
                result.add(r);
            }              
        }           
        return result;       
    }
    
    /**
     * Refresh the combo-boxes storing names of persons
     */
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
    
    /**
     * Refresh the table to display the retrieved
     * relations of the selected two persons
     * 
     * @param result
     */
    private void refTable(List<Relation> result)
    {
        DefaultTableModel dtm = new DefaultTableModel();
        
        String[] tableHeads = new String[]{"Name", "Name", "Relationship"};
    
        dtm.setColumnIdentifiers(tableHeads);  
        
        for(int i = 0;i < result.size();i++)
        {
	        	Relation r = result.get(i);
	
	        	{
	        		Object[] dataRow =
	        			{r.getName1(),r.getName2(),r.getRelationType()};
	
	        		dtm.addRow(dataRow);
	        	}		 
        }            
        table.setModel(dtm);
    }
    
    // Variables declaration
    private javax.swing.JButton jBQuery;
    private javax.swing.JComboBox<String> jCBName1;
    private javax.swing.JComboBox<String> jCBName2;
    private javax.swing.JLabel jLabName1;
    private javax.swing.JLabel jLabName2;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JTable table;
}
