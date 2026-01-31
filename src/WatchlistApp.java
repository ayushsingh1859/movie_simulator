import java.sql.*;
import java.util.Scanner;

public class WatchlistApp {

    static final String URL = "jdbc:mysql://localhost:3306/movielistdb";
    static final String USER = "root";
    static final String PASS = "Ayush@123";

    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, USER, PASS);

            Scanner sc = new Scanner(System.in);
            int choice;

            do {
                System.out.println("\n===== WATCHLIST MENU =====");
                System.out.println("1. Add Movie");
                System.out.println("2. View Watchlist");
                System.out.println("3. Update Movie");
                System.out.println("4. Delete Movie");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");

                choice = sc.nextInt();
                sc.nextLine(); 

                switch (choice) {
                    case 1 -> insertMovie(con, sc);
                    case 2 -> readMovies(con);
                    case 3 -> updateMovie(con, sc);
                    case 4 -> deleteMovie(con, sc);
                    case 5 -> System.out.println("Exiting program...");
                    default -> System.out.println("Invalid choice!");
                }

            } while (choice != 5);

            sc.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= INSERT =================
    static void insertMovie(Connection con, Scanner sc) throws SQLException {

        String sql = "INSERT INTO watchlist (name, genre, rating, status) VALUES (?, ?, ?, ?)";

        System.out.print("Enter movie name: ");
        String name = sc.nextLine();

        System.out.print("Enter genre: ");
        String genre = sc.nextLine();

        System.out.print("Enter rating: ");
        double rating = sc.nextDouble();
        sc.nextLine();

        System.out.print("Enter status: ");
        String status = sc.nextLine();

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, name);
        ps.setString(2, genre);
        ps.setDouble(3, rating);
        ps.setString(4, status);

        int rows = ps.executeUpdate();
        System.out.println(rows + " movie added");

        ps.close();
    }

    // ================= READ =================
    static void readMovies(Connection con) throws SQLException {

        String sql = "SELECT * FROM watchlist";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        System.out.println("\n--- WATCHLIST ---");
        while (rs.next()) {
            System.out.println(
                rs.getInt("sl_no") + " | " +
                rs.getString("name") + " | " +
                rs.getString("genre") + " | " +
                rs.getBigDecimal("rating") + " | " +
                rs.getString("status")
            );
        }

        rs.close();
        ps.close();
    }

    // ================= UPDATE =================
    static void updateMovie(Connection con, Scanner sc) throws SQLException {

        String sql = "UPDATE watchlist SET rating = ?, status = ? WHERE sl_no = ?";

        System.out.print("Enter movie ID to update: ");
        int id = sc.nextInt();

        System.out.print("Enter new rating: ");
        double rating = sc.nextDouble();
        sc.nextLine();

        System.out.print("Enter new status: ");
        String status = sc.nextLine();

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setDouble(1, rating);
        ps.setString(2, status);
        ps.setInt(3, id);

        int rows = ps.executeUpdate();
        System.out.println(rows + " movie updated");

        ps.close();
    }

    // ================= DELETE =================
    static void deleteMovie(Connection con, Scanner sc) throws SQLException {

        System.out.print("Enter movie ID to delete: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Are you sure you want to delete? (yes/no): ");
        String confirm = sc.nextLine();

        if (confirm.equalsIgnoreCase("yes")) {
            String sql = "DELETE FROM watchlist WHERE sl_no = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            int rows = ps.executeUpdate();
            System.out.println(rows + " movie deleted");

            ps.close();
        } else {
            System.out.println("Delete operation cancelled");
        }
    }
}

