
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetCookies
 */
@WebServlet("/GetCookies.do")
public class GetCookies extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetCookies() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Cookie cookie = null;
		Cookie[] cookies = null;

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		cookies = request.getCookies();

		response.setContentType("text/html;charset=UTF-8");

		PrintWriter out = response.getWriter();
		if (cookies != null) {
			out.println("<h4>查找Cookies名称是值</h4>");
			for (int i = 0; i < cookies.length; i++) {
				cookie = cookies[i];
				out.print("名称：" + cookie.getName() + "--> ");
				out.print("值：" + URLDecoder.decode(cookie.getValue(), "UTF-8") + "<br/>");
			}
		} else {
			out.println("<h4>未找到Cookies</h4>");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
