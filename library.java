import java.sql.*;
class library
{
	public static void main(String[] args) throws Exception{
		ConnectDB b = new ConnectDB();
		Connection c = b.connect();
	}
}