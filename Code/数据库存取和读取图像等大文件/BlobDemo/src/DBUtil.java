

import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.Connection;

public class DBUtil {
	public static Connection getConnection() throws NamingException, SQLException {
		//��ȡJNDI�ж��������Դ������Connection����
		Connection result = null;
		InitialContext ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/MySQL");
		result = ds.getConnection();

		return result;
	}
}