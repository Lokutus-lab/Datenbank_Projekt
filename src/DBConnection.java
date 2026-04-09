import java.sql.*;

public class DBConnection {

    private static final String SERVER   = "sql7.freesqldatabase.com";
    private static final int    PORT     = 3306;
    private static final String DATABASE = "sql7817972";
    private static final String USER     = "sql7817972";
    private static final String PASSWORD = "Q3ylYu8mY8";
    private static final String URL      = "jdbc:mysql://" + SERVER + ":" + PORT + "/" + DATABASE
            + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC&characterEncoding=utf8";

    // Treiber einmalig laden
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL Driver nicht gefunden", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static boolean testConnection() {
        try (Connection con = getConnection()) {
            System.out.println("[OK] Verbindung erfolgreich!");
            System.out.println("     Server:    " + SERVER + ":" + PORT);
            System.out.println("     Datenbank: " + DATABASE);
            System.out.println("     MySQL:     " + con.getMetaData().getDatabaseProductVersion());
            return true;
        } catch (SQLException e) {
            System.out.println("[FEHLER] Verbindung fehlgeschlagen: " + e.getMessage());
            return false;
        }
    }
}
