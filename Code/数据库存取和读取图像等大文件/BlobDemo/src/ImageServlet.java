
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ImageServlet
 */
@WebServlet("/getLastImage")
public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ImageServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("image/jpeg"); //设置响应的类型
		request.setCharacterEncoding("UTF-8");
		
		// ServletResponse提供了2种输出流：ServletOutputStream用于输出字节；PrintWriter输出字符
		ServletOutputStream os = response.getOutputStream();

		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT name, image FROM images ORDER BY id DESC";

			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				@SuppressWarnings("unused")
				String name = rs.getString(1);

			    //从数据库中读取图像的二进制数据，并输出到浏览器
				byte[] buffer = new byte[1];
				InputStream is = rs.getBinaryStream(2);

				while (is.read(buffer) > 0) {
					os.write(buffer);
				}
				os.flush();
			}

		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (os != null) {
				os.close();
			}
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
