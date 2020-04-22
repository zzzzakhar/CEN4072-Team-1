package utilities;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

public static Connection createConnection()
{
Connection con = null;
String url = "jdbc:mysql://localhost:3306/PMS";

try
{
try
{
Class.forName("com.mysql.jdbc.Driver");
}
catch (ClassNotFoundException e)
{
e.printStackTrace();
}

con = DriverManager.getConnection(url, "root", "");

}
catch (Exception e)
{
e.printStackTrace();
}

return con;
}
}