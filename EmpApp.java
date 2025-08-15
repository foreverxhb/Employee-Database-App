import java.sql.*;
import java.util.Scanner;

public class EmpApp {

    private static final String URL = "jdbc:mysql://localhost:3306/empDB";
    private static final String USER = "root"; 
    private static final String PASSWORD = "hbzade007"; 

    private static Connection conn;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            //loading JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //connecting to DB
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to empDB database.");

            int choice;
            do {
                System.out.println("\n--- Employee Database Menu ---");
                System.out.println("1. Add Employee");
                System.out.println("2. View Employee");
                System.out.println("3. Update Employee");
                System.out.println("4. Delete Employee");
                System.out.println("5. Exit");
                System.out.print("Enter choice: ");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1 -> addEmp();
                    case 2 -> viewEmp();
                    case 3 -> updateEmp();
                    case 4 -> deleteEmp();
                    case 5 -> System.out.println("Exiting...GoodBye!");
                    default -> System.out.println("Invalid choice. Please try again!");
                }
            } while (choice != 5);

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addEmp() throws SQLException {
        System.out.print("Enter name: ");
        String name = scanner.next();
        System.out.print("Enter position: ");
        String position = scanner.next();
        System.out.print("Enter salary: ");
        double salary = scanner.nextDouble();
        
        //placeholders used in a PreparedStatement in JDBC.
        String sql = "INSERT INTO emp (name, position, salary) VALUES (?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, name);
        pstmt.setString(2, position);
        pstmt.setDouble(3, salary);
        pstmt.executeUpdate();
        System.out.println("Employee added successfully.");
    }

    private static void viewEmp() throws SQLException {
        String sql = "SELECT * FROM emp";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        System.out.println("\n--- Employee List ---");
        while (rs.next()) {
            System.out.printf("%d | %s | %s | %.2f%n",
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("position"),
                    rs.getDouble("salary"));
        }
    }

    private static void updateEmp() throws SQLException {
        System.out.print("Enter Employee ID to update: ");
        int id = scanner.nextInt();
        System.out.print("Enter new name: ");
        String name = scanner.next();
        System.out.print("Enter new position: ");
        String position = scanner.next();
        System.out.print("Enter new salary: ");
        double salary = scanner.nextDouble();

        String sql = "UPDATE emp SET name=?, position=?, salary=? WHERE id=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, name);
        pstmt.setString(2, position);
        pstmt.setDouble(3, salary);
        pstmt.setInt(4, id);

        int rows = pstmt.executeUpdate();
        if (rows > 0) {
            System.out.println("Employee updated successfully.");
        } else {
            System.out.println("Employee not found.");
        }
    }

    private static void deleteEmp() throws SQLException {
        System.out.print("Enter Employee ID to delete: ");
        int id = scanner.nextInt();

        String sql = "DELETE FROM emp WHERE id=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);

        int rows = pstmt.executeUpdate();
        if (rows > 0) {
            System.out.println("Employee deleted successfully.");
        } else {
            System.out.println("Employee not found.");
        }
    }
}
