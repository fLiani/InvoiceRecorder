import java.sql.*;

public class DatabaseManager
{
    Connection c = null;
    Statement stmt;
    public DatabaseManager()
    {

    }

    public void createDatabase()
    {

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:SalesRecord.db");
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }

    public void createQuarter(String quarter) throws SQLException
    {
        stmt = c.createStatement();
        String tableName = "Quarter" + quarter;
        String sql = "CREATE TABLE Quarter" +
                "(Date TEXT, " +
                "Inv_No INT NOT NULL, " +
                "Name_Details TEXT, " +
                "Amount DOUBLE, " +
                "Total DOUBLE, " +
                "VAT DOUBLE, " +
                "Net DOUBLE, " +
                "PRIMARY KEY (Inv_No))";

        stmt.executeUpdate(sql);
        System.out.println("Created Quarter table in database...");
    }

    public void fillTable(Person p) throws SQLException
    {
        try
        {
//            System.out.println(p.getInvNo());
            c = DriverManager.getConnection("jdbc:sqlite:SalesRecord.db");
            stmt = c.createStatement();
            String nm = p.getNameDetails().replace(",", "").replace("'","");
            String sql = "INSERT INTO Quarter VALUES ('" + p.getDate() + "', " + p.getInvNo() + ", '" + nm
                    + "', " + p.getAmount() + ", " + p.getTotal() + ", " + p.getVat() + ", " + p.getNet() + ")";

            stmt.executeUpdate(sql);
        }
        catch(SQLException e)
        {
            if(e.getErrorCode() == 19)
            {
                System.out.println("Duplicate");
            }
        }
    }
}
