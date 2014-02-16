package view;


import chatserver.User;
import controller.ServerController;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author NADA
 */
public class ServerUI extends javax.swing.JFrame {

    /**
     * Creates new form ServerUI
     */
    
     ServerController controller;
    DefaultListModel allUsersNames ;
    ArrayList<User> allUsers;
    int serverStatus;
    public ServerUI(ServerController controller) {
         try {
             initComponents();
             this.controller = controller;
             allUsers = controller.getAllUsers();
                 allUsersNames = new DefaultListModel();
                 
                 for (User user : allUsers) {
                     //System.out.println(user.getfName()+" "+user.getLName());
                     allUsersNames.addElement(user.getLName()+" "+user.getfName());
                 }
                 jList1.setModel(allUsersNames);
                 
                 //Getting users Status
                 Thread th;
                 th = new Thread(new Runnable() {
                     
                     @Override
                     public void run() {
                         try {
                             while (true) {
                                 jTextArea2.setText(ServerUI.this.controller.getStatusOfUsers());
                                 Thread.sleep(1000);
                             }
                             
                             
                         } catch (InterruptedException ex) {
                             Logger.getLogger(ServerUI.class.getName()).log(Level.SEVERE, null, ex);
                         }
                     }
                 });
                 th.start();
         } catch (RemoteException ex) {
             Logger.getLogger(ServerUI.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelUI = new javax.swing.JPanel();
        jToggleButton1 = new javax.swing.JToggleButton();
        tabbedPane = new javax.swing.JTabbedPane();
        usersPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        onlineUsersPanel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        reportsPanel = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Server Admin");

        jToggleButton1.setBackground(new java.awt.Color(255, 0, 0));
        jToggleButton1.setText("OFF");
        jToggleButton1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jToggleButton1ItemStateChanged(evt);
            }
        });
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jList1);

        javax.swing.GroupLayout usersPanelLayout = new javax.swing.GroupLayout(usersPanel);
        usersPanel.setLayout(usersPanelLayout);
        usersPanelLayout.setHorizontalGroup(
            usersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(usersPanelLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        usersPanelLayout.setVerticalGroup(
            usersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
        );

        tabbedPane.addTab("Users", usersPanel);

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane3.setViewportView(jTextArea2);

        javax.swing.GroupLayout onlineUsersPanelLayout = new javax.swing.GroupLayout(onlineUsersPanel);
        onlineUsersPanel.setLayout(onlineUsersPanelLayout);
        onlineUsersPanelLayout.setHorizontalGroup(
            onlineUsersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(onlineUsersPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(151, Short.MAX_VALUE))
        );
        onlineUsersPanelLayout.setVerticalGroup(
            onlineUsersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(onlineUsersPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE))
        );

        tabbedPane.addTab("Online Users", onlineUsersPanel);

        javax.swing.GroupLayout reportsPanelLayout = new javax.swing.GroupLayout(reportsPanel);
        reportsPanel.setLayout(reportsPanelLayout);
        reportsPanelLayout.setHorizontalGroup(
            reportsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 395, Short.MAX_VALUE)
        );
        reportsPanelLayout.setVerticalGroup(
            reportsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 179, Short.MAX_VALUE)
        );

        tabbedPane.addTab("Reports", reportsPanel);

        jButton1.setText("addAdvertise");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelUILayout = new javax.swing.GroupLayout(panelUI);
        panelUI.setLayout(panelUILayout);
        panelUILayout.setHorizontalGroup(
            panelUILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelUILayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jToggleButton1)
                .addContainerGap())
            .addComponent(tabbedPane, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(panelUILayout.createSequentialGroup()
                .addGap(136, 136, 136)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelUILayout.setVerticalGroup(
            panelUILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUILayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToggleButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelUI, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelUI, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void jToggleButton1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jToggleButton1ItemStateChanged
        // TODO add your handling code here:
        if(evt.getStateChange() == ItemEvent.SELECTED){
            jToggleButton1.setText("ON");
            jToggleButton1.setBackground(Color.green);
        }
        else{
           jToggleButton1.setText("OFF");
            jToggleButton1.setBackground(Color.red); 
        }
    }//GEN-LAST:event_jToggleButton1ItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
        Advertiseframe advertiseframe = new Advertiseframe(controller);
       advertiseframe.setBounds(this.getX(), this.getY(), 400, 300);
        advertiseframe.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
        // TODO add your handling code here:
        
         jTextArea1.setText("");
        String name  = allUsersNames.get(jList1.getSelectedIndex()).toString();
        User selectedUser = new User();
        for (User user : allUsers) {
            
            if((user.getLName()+" "+user.getfName()).equals(name))
                selectedUser = user;
        }
        jTextArea1.setText("ID :"+ selectedUser.getId()+"\n"+"Name: " +selectedUser.getfName() +" " + selectedUser.getLName()+"\n"+
                "Age :" + selectedUser.getAge() +"\n" + "Gender: " + selectedUser.getGendar()+"\n" + "Status: " + selectedUser.getStatus());
        
        
        
    }//GEN-LAST:event_jList1MouseClicked

  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JList jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JPanel onlineUsersPanel;
    private javax.swing.JPanel panelUI;
    private javax.swing.JPanel reportsPanel;
    private javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JPanel usersPanel;
    // End of variables declaration//GEN-END:variables
}