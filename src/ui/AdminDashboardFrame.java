/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ui;

import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Humph
 */
public class AdminDashboardFrame extends javax.swing.JFrame {

    public AdminDashboardFrame() {
        initComponents();
        setLocationRelativeTo(null); 
        loadBookingsTable();
        this.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
    }

    
    public void loadBookingsTable() {
    DefaultTableModel model = new DefaultTableModel();

   
    model.addColumn("Booking ID");
    model.addColumn("Customer Name");
    model.addColumn("Item Ordered");
    model.addColumn("Quantity");
    model.addColumn("Address");
    model.addColumn("Contact");
    model.addColumn("Date");
    model.addColumn("Time");
    model.addColumn("Type");
    model.addColumn("Payment");
    model.addColumn("Total Amount");
    model.addColumn("Status");
    
    try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/selecta_booking_db", "root", "");

            // Query para kwaon ang orders pati ang ang customer fullnames
            String sql = "SELECT b.*, c.fullname FROM customer_bookings b " +
                         "LEFT JOIN customer_profiles c ON b.customer_id = c.customer_id " +
                         "ORDER BY b.booking_id DESC";

            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String custName = rs.getString("fullname");
                if (custName == null || custName.trim().isEmpty()) {
                    custName = "Customer #" + rs.getInt("customer_id");
                }

                // Safety handling para sa flavor name ag quantity
                String itemOrdered = "Selecta Ice Cream";
                try {
                    String fn = rs.getString("flavor_name");
                    if (fn != null && !fn.trim().isEmpty()) {
                        itemOrdered = fn;
                    }
                } catch (Exception e) {}

                int quantity = 1;
                try {
                    int q = rs.getInt("quantity");
                    if (q > 0) quantity = q;
                } catch (Exception e) {}

                model.addRow(new Object[]{
                    rs.getString("booking_id"),
                    custName,
                    itemOrdered,
                    quantity,
                    rs.getString("address"),
                    rs.getString("contact_number"),
                    rs.getString("preferred_date"),
                    rs.getString("preferred_time"),
                    rs.getString("booking_type"),
                    rs.getString("payment_method"),
                    "₱" + rs.getDouble("total_amount"),
                    rs.getString("status")
                });
            }

            tblBookings.setModel(model);
            conn.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
  
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(AdminDashboardFrame.class.getName());

   
     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBookings = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        btnUpdateStatus = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        cmbStatus = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 204, 255));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        jPanel1.setPreferredSize(new java.awt.Dimension(1920, 1080));

        tblBookings.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblBookings);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/IKA DUGA LOGO SA TAG IYA FINAL.jpg"))); // NOI18N

        btnUpdateStatus.setBackground(new java.awt.Color(102, 153, 255));
        btnUpdateStatus.setText("UPDATE STATUS");
        btnUpdateStatus.addActionListener(this::btnUpdateStatusActionPerformed);

        btnRefresh.setBackground(new java.awt.Color(255, 153, 153));
        btnRefresh.setText("REFRESH TABLE");
        btnRefresh.addActionListener(this::btnRefreshActionPerformed);

        btnLogout.setBackground(new java.awt.Color(204, 204, 204));
        btnLogout.setText("LOGOUT");
        btnLogout.addActionListener(this::btnLogoutActionPerformed);

        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Approved", "Canceled", "Pending" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnUpdateStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1244, 1244, 1244)
                        .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(264, 264, 264)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 1379, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(171, 171, 171)
                            .addComponent(jLabel2))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(32, 32, 32)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1841, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 608, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnUpdateStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnUpdateStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateStatusActionPerformed
        int selectedRow = tblBookings.getSelectedRow();

    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "\"Please select first the order in the table whose status you want to change.\"!", "Warning", JOptionPane.WARNING_MESSAGE);
        return;
    }

    // Kukunin ang Booking ID ng piniling row sa table
    String bookingId = tblBookings.getValueAt(selectedRow, 0).toString();
    String newStatus = cmbStatus.getSelectedItem().toString();

    try {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/selecta_booking_db", "root", "");
        
        String sql = "UPDATE customer_bookings SET status = ? WHERE booking_id = ?";

            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, newStatus);
            pst.setString(2, bookingId);

            int updated = pst.executeUpdate();
            if (updated > 0) {
                JOptionPane.showMessageDialog(this, "Order " + bookingId + " status updated to: " + newStatus);
                loadBookingsTable(); // Refresh table
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update status.", "Warning", JOptionPane.WARNING_MESSAGE);
            }
            conn.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error updating status: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    
        // TODO add your handling code here:
    }//GEN-LAST:event_btnUpdateStatusActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        loadBookingsTable();
    JOptionPane.showMessageDialog(this, "Table refreshed!");        // TODO add your handling code here:
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
     int confirm = JOptionPane.showConfirmDialog(
            this, 
            "Are you sure you want to logout?", 
            "Confirm Logout", 
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.QUESTION_MESSAGE
        );

        if (confirm == JOptionPane.YES_OPTION) {
            utils.SessionManager.logout();

            LoginFrame login = new LoginFrame();
            login.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_btnLogoutActionPerformed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new AdminDashboardFrame().setVisible(true));
        java.awt.EventQueue.invokeLater(() -> new AdminDashboardFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnUpdateStatus;
    private javax.swing.JComboBox<String> cmbStatus;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblBookings;
    // End of variables declaration//GEN-END:variables
}
