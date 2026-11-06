package dao;

import config.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    
    // Class para sa Product Model (o kaya ay ihiwalay sa model package)
    public static class ProductItem {
        public int productId;
        public String productName;
        public String description;
        public double price;
        public int stock;
        public String imagePath;

        public ProductItem(int productId, String productName, String description, double price, int stock, String imagePath) {
            this.productId = productId;
            this.productName = productName;
            this.description = description;
            this.price = price;
            this.stock = stock;
            this.imagePath = imagePath;
        }
    }

    // Kunin ang lahat ng produkto o kaya ay ayon sa category
    public List<ProductItem> getAllProducts() {
        List<ProductItem> products = new ArrayList<>();
        String query = "SELECT * FROM products WHERE status = 'Active'";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                products.add(new ProductItem(
                    rs.getInt("product_id"),
                    rs.getString("product_name"),
                    rs.getString("description"),
                    rs.getDouble("price"),
                    rs.getInt("stock"),
                    rs.getString("image_path")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
}