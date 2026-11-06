package utils;

public class SessionManager {
    // These variables hold the logged-in customer's details globally
    private static int currentCustomerId = -1; // -1 means no one is logged in
    private static String currentCustomerName = null;

    // Method to call when a customer successfully logs in
    public static void login(int customerId, String customerName) {
        currentCustomerId = customerId;
        currentCustomerName = customerName;
    }

    // Method to clear data when logging out
    public static void logout() {
        currentCustomerId = -1;
        currentCustomerName = null;
    }

    // Getters so other frames can access the current customer's data
    public static int getCurrentCustomerId() {
        return currentCustomerId;
    }

    public static String getCurrentCustomerName() {
        return currentCustomerName;
    }
}