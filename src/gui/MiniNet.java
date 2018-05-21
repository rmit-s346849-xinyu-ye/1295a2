package gui;

import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import mininet.DBConnect;
import mininet.Driver;
import mininet.FileOperation;

/**
 *
 * @author Xinyu YE s3468489
 */
public class MiniNet extends javax.swing.JFrame 
{

    /**
     * Creates new form MiniNet
     */
    ListEveryone le;
    AddPerson addPerson;
    ConnectionChain cc;
    DefineRelation define;
    QueryRelationship qr;
    QueryParentChild qpc;
    public static Driver driver;
    public static final int AGE = 16;
    public DBConnect dbTest;
    
    public MiniNet() 
    {
        
        initComponents();
        setFrame();
        try 
        {
            driver = new Driver();
            le = new ListEveryone();
            addPerson = new AddPerson();
            cc = new ConnectionChain();
            define = new DefineRelation();
            qr = new QueryRelationship();
            qpc = new QueryParentChild();
        } 
        catch (Exception e) 
        {
            JOptionPane.showMessageDialog(null, e);
            System.exit(0);
        }
        
        //Set up shortcut keys for menu items
        jMILE.setAccelerator(KeyStroke.getKeyStroke('L', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
        jMIAP.setAccelerator(KeyStroke.getKeyStroke('A', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
        jMIQR.setAccelerator(KeyStroke.getKeyStroke('Q', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
        jMIFPC.setAccelerator(KeyStroke.getKeyStroke('F', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
        jMICC.setAccelerator(KeyStroke.getKeyStroke('C', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
        jMIR.setAccelerator(KeyStroke.getKeyStroke('R', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
    }
    
    private void setFrame()
    {
        setResizable(false);
        setLocation(500, 300);
        setSize(420, 300);
    }

    private void initComponents() 
    {

        jMenuBar1 = new javax.swing.JMenuBar();
        View = new javax.swing.JMenu();
        jMILE = new javax.swing.JMenuItem();
        jMIAP = new javax.swing.JMenuItem();
        jMIQR = new javax.swing.JMenuItem();
        jMIFPC = new javax.swing.JMenuItem();
        jMICC = new javax.swing.JMenuItem();
        Query = new javax.swing.JMenu();
        jMIR = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) 
            {
                try 
                {
					formWindowClosing(evt);
				} 
                catch (Exception e) 
                {
	                	JOptionPane.showMessageDialog(null, e);
	                	System.exit(0);
				}
            }
        });

        View.setText("View");

        jMILE.setText("List Everyone");
        jMILE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMILEActionPerformed(evt);
            }
        });
        View.add(jMILE);

        jMIAP.setText("Add a Person");
        jMIAP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMIAPActionPerformed(evt);
            }
        });
        View.add(jMIAP);

        jMIQR.setText("Query Relationship");
        jMIQR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMIQRActionPerformed(evt);
            }
        });
        View.add(jMIQR);

        jMIFPC.setText("Find Parent/Child");
        jMIFPC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMIFPCActionPerformed(evt);
            }
        });
        View.add(jMIFPC);

        jMICC.setText("Connection Chain");
        jMICC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMICCActionPerformed(evt);
            }
        });
        View.add(jMICC);

        jMenuBar1.add(View);

        Query.setText("Edit");

        jMIR.setText("Relationships");
        jMIR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMIRActionPerformed(evt);
            }
        });
        Query.add(jMIR);

        jMenuBar1.add(Query);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 416, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 261, Short.MAX_VALUE)
        );

        pack();
    }

    private void jMILEActionPerformed(java.awt.event.ActionEvent evt) 
    {
        le.setVisible(true);   
    }

    private void jMIAPActionPerformed(java.awt.event.ActionEvent evt) 
    {
        addPerson.setVisible(true);
    }

    private void jMIRActionPerformed(java.awt.event.ActionEvent evt) 
    {
        define.setVisible(true);
    }

    private void formWindowClosing(java.awt.event.WindowEvent evt) throws Exception 
    {
        if(JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?", "Really exiting?", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
        {
            try  
            {
	            	FileOperation.writePeopleToFile(driver.getTheMiniNet());
	
	            	FileOperation.writeRelationsToFile(driver.getRelations());
            } 
            catch (Exception e) 
            {
//                JOptionPane.showMessageDialog(null, e); 		
	            	dbTest = new DBConnect();
			    	dbTest.connect();
			    	dbTest.writeToDB(driver.getTheMiniNet());
            }
            System.exit(0);          
        }
        else
        {
            return;
        }
    }

    private void jMIQRActionPerformed(java.awt.event.ActionEvent evt) {
        qr.setVisible(true);
    }

    private void jMIFPCActionPerformed(java.awt.event.ActionEvent evt) {
        qpc.setVisible(true);
    }

    private void jMICCActionPerformed(java.awt.event.ActionEvent evt) {
        cc.setVisible(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) 
    {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MiniNet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MiniNet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MiniNet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MiniNet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            public void run() 
            {
                new MiniNet().setVisible(true);
            }
        });
    }

    // Variables declaration 
    private javax.swing.JMenu Query;
    private javax.swing.JMenu View;
    private javax.swing.JMenuItem jMIAP;
    private javax.swing.JMenuItem jMICC;
    private javax.swing.JMenuItem jMIFPC;
    private javax.swing.JMenuItem jMILE;
    private javax.swing.JMenuItem jMIQR;
    private javax.swing.JMenuItem jMIR;
    private javax.swing.JMenuBar jMenuBar1;
}
