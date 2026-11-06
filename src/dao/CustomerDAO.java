package dao;

import config.DatabaseConnection;
import model.Customer;
import utils.SessionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    // LIGON SA COSTUMERSA
    public boolean loginCustomer(String username, String password) {
        String query = "SELECT * FROM customer_accounts WHERE username = ? AND password = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    // GiGAMIT ANG SAKTO METHOD sa SessionManager
                    SessionManager.login(rs.getInt("id"), rs.getString("full_name"));
                    return true;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error login: " + e.getMessage());
        }
        return false;
    }

    // 2. READ: Kumuha ng lahat ng customers
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        String query = "SELECT * FROM customer_accounts";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
             
            while (rs.next()) {
                Customer c = new Customer();
                c.setCustomerId(rs.getInt("id")); 
                c.setName(rs.getString("full_name"));
                c.setEmail(rs.getString("email"));
                c.setPhone(rs.getString("contact_number")); 
                c.setAddress(rs.getString("address"));
                customers.add(c);
            }
        } catch (SQLException e) {
            System.out.println("Error reading customers: " + e.getMessage());
        }
        return customers;
    }

    // 3. CREATE: Mag-add ng bagong customer
    public boolean addCustomer(Customer customer) {
        String query = "INSERT INTO customer_accounts (full_name, email, contact_number, address) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
             
            pstmt.setString(1, customer.getName());
            pstmt.setString(2, customer.getEmail());
            pstmt.setString(3, customer.getPhone()); 
            pstmt.setString(4, customer.getAddress());
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error adding customer: " + e.getMessage());
            return false;
        }
    }
}