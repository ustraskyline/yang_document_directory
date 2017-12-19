
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * 此Servlet用来处理图片上传、保存到数据库
 */
@WebServlet(name = "FileUploadServlet", urlPatterns = { "/upload" })
// @MultipartConfig标注表示该Servlet期望请求使用multipart/form-data MIME类型
@MultipartConfig
public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FileUploadServlet() {
		super();
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		final Part filePart = request.getPart("file");
		final String fileName = getFileName(filePart);

		Connection conn = null;

		OutputStream out = null;
		InputStream filecontent = null;

		final PrintWriter writer = response.getWriter();

		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);

			String sql = "INSERT INTO images (name, image) VALUES (?, ?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, fileName);

			filecontent = filePart.getInputStream();
			stmt.setBinaryStream(2, filecontent, (int) filePart.getSize());
			stmt.execute();

			conn.commit();

			response.sendRedirect("dispimg.jsp");
		} catch (Exception e) {
			writer.println("<br/>错误: " + e.getMessage());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (filecontent != null) {
				try {
					filecontent.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (writer != null) {
				writer.close();
			}
		}
	}

	private String getFileName(final Part part) {
		// 上传文件的信息放在Request Payload中，如 Content-Disposition: form-data; name="file";
		// filename="Fight_Airplane.jpg"
		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename")) { // filename="Fight_Airplane.jpg"

				System.out.println("============>" + content);
				System.out.println("============> index = " + content.lastIndexOf('\\'));
				System.out.println("============> 替换前 = " + content.substring(content.lastIndexOf('\\') + 1).trim());
				String filename = content.substring(content.lastIndexOf('\\') + 1).trim().replace("\"", "");

				System.out.println("------------> 替换后:" + filename);
				return filename;
			}
		}
		return null;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}
}
