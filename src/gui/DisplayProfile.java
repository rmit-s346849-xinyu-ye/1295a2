/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import java.awt.Image;
import java.util.List;
import javax.swing.ImageIcon;
import mininet.Adult;
import mininet.Person;
import mininet.Relation;

/**
 *
 * @author Yifan ZHANG s3615625
 */
public class DisplayProfile extends javax.swing.JFrame 
{

    /** Creates new form DisplayProfile */
    public DisplayProfile(String name)
    {
        initComponents();
    
        Person p = MiniNet.driver.getPersonByName(name);
        String path = p.getPhotoPath();
        setFrame();
        
        if (path.equals(""))
        {//show the text "No Image" if the selected person's
            //profile photo is not available
            this.jLABImage.setText("No Image");
        }
        else
        {//show the selected person's profile photo if available
            ImageIcon imageIcon = new ImageIcon(new ImageIcon("").getImage()
                    .getScaledInstance(jLABImage.getWidth(), jLABImage.getHeight(), Image.SCALE_SMOOTH));
            this.jLABImage.setIcon(new ImageIcon(path));
        }
        
        this.jLABName.setText(p.getName());
        
        this.jLABStatus.setText(p.getStatus());
        
        if(p.getGender() == 'M')
        {//'M' represents "Male"
            this.jLABGender.setText("Male");
        }
        else
        {//'F' represents "Female"
            this.jLABGender.setText("Female");
        }
        //String type conversion
        this.jLABAge.setText(String.valueOf(p.getAge()));
        
        this.jLABState.setText(p.getState());
        
        //If the selected person is an adult
        //because we assume that an adult does not
        //have the record of parents, so the
        //corresponding labels will be hidden
        //from the profile window
        if(p instanceof Adult)
        {
            this.jLabFather.setVisible(false);
            this.jLabMother.setVisible(false);
            this.jLABFN.setVisible(false);
            this.jLABMN.setVisible(false);
        }
        else
        {
            this.jLABFN.setText(getParent(p.getName(), 'M'));
            this.jLABMN.setText(getParent(p.getName(), 'F'));
        }
    }
    
    private void setFrame()
    {
        setResizable(true);
        setLocation(500, 300);
        setSize(710, 370);
    }
    
     /**
     * 
     * @param name
     * @param gender
     * @return the name of a child's parent
     */
    public String getParent(String name,char gender)
    {
        List <Relation> data = MiniNet.driver.getRelations();
        
        for(int i = 0;i < data.size();i++)
        {
            Relation r = data.get(i);
            
            if (r.getRelationType().equals("Parent"))
            {
                if(name.equals(r.getName1()))
                {
                   if(MiniNet.driver.getPersonByName(r.getName2()).getGender() == gender)
                       return r.getName2();
                }              
                else if(name.equals(r.getName2()))
                {
                    if(MiniNet.driver.getPersonByName(r.getName1()).getGender() == gender)
                        return r.getName1();
                }                
            }           
        }
        return "";
    }
    
    public javax.swing.JLabel getJLABImage()
    {
        return jLABImage;
    }
 
    public javax.swing.JLabel getJLABName()
    {
        return jLABName;
    }
    
    public javax.swing.JLabel getJLABStatus()
    {
        return jLABStatus;
    }
    
    public javax.swing.JLabel getJLABGender()
    {
        return jLABGender;
    }
    
    public javax.swing.JLabel getJLABAge()
    {
        return jLABAge;
    }
    
    public javax.swing.JLabel getJLABState()
    {
        return jLABState;
    }
    
    public javax.swing.JLabel getJLABFN()
    {
        return jLABFN;
    }
    
    public javax.swing.JLabel getJLABMN()
    {
        return jLABMN;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabName = new javax.swing.JLabel();
        jLabStatus = new javax.swing.JLabel();
        jLabGen = new javax.swing.JLabel();
        jLabAge = new javax.swing.JLabel();
        jLabState = new javax.swing.JLabel();
        jLabFather = new javax.swing.JLabel();
        jLabMother = new javax.swing.JLabel();
        jLABImage = new javax.swing.JLabel();
        jLABName = new javax.swing.JLabel();
        jLABStatus = new javax.swing.JLabel();
        jLABGender = new javax.swing.JLabel();
        jLABAge = new javax.swing.JLabel();
        jLABState = new javax.swing.JLabel();
        jLABFN = new javax.swing.JLabel();
        jLABMN = new javax.swing.JLabel();
        jBUpdate = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(710, 385));

        jLabName.setText("Name:");

        jLabStatus.setText("Status:");

        jLabGen.setText("Gender:");

        jLabAge.setText("Age:");

        jLabState.setText("State:");

        jLabFather.setText("Father:");

        jLabMother.setText("Mother:");

        jBUpdate.setText("Update");
        jBUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBUpdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabGen)
                                    .addComponent(jLabFather)
                                    .addComponent(jLabMother)
                                    .addComponent(jLabAge, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabState, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(21, 21, 21)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLABAge, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLABState, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLABFN, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jLABGender, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jLABMN, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabStatus)
                                    .addComponent(jLabName, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLABName, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLABStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(jBUpdate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addComponent(jLABImage, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLABImage, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabName, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLABName, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLABStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(jLabGen, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLABGender, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabAge, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(jLabState, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jLABAge, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLABState, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabFather, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLABFN, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabMother, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLABMN, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBUpdateActionPerformed
       UpdateProfile up = new UpdateProfile(this);
       up.setVisible(true);
    }//GEN-LAST:event_jBUpdateActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBUpdate;
    private javax.swing.JLabel jLABAge;
    private javax.swing.JLabel jLABFN;
    private javax.swing.JLabel jLABGender;
    private javax.swing.JLabel jLABImage;
    private javax.swing.JLabel jLABMN;
    private javax.swing.JLabel jLABName;
    private javax.swing.JLabel jLABState;
    private javax.swing.JLabel jLABStatus;
    private javax.swing.JLabel jLabAge;
    private javax.swing.JLabel jLabFather;
    private javax.swing.JLabel jLabGen;
    private javax.swing.JLabel jLabMother;
    private javax.swing.JLabel jLabName;
    private javax.swing.JLabel jLabState;
    private javax.swing.JLabel jLabStatus;
    // End of variables declaration//GEN-END:variables

}
