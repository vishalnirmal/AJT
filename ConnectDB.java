import java.sql.*;
class ConnectDB
{
	public Connection connect() throws Exception
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection c = DriverManager.getConnection("jdbc:mysql://localhost:808/vishal","admin","admin@98");
		return c;
	}
}