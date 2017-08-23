
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
 * Servlet implementation class DelCookies
 */
@WebServlet("/DelCookies.do")
public class DelCookies extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DelCookies() {
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

		PrintWriter out = response.getWriter();

		// 客户端发送对本Servlet的请求时,会把Cookies发送给服务器，此request对象中的cookie就是客户端发给服务器的cookie
		cookies = request.getCookies();

		response.setContentType("text/html;charset=UTF-8");

		if (cookies != null) {
			out.println("<h4>Cookies名称和值</h4>");
			for (int i = 0; i < cookies.length; i++) {
				cookie = cookies[i];
				if ("name".equals(cookie.getName()) || "pwd".equals(cookie.getName())) {
					cookie.setMaxAge(0); // 删除指定的cookie
					response.addCookie(cookie); // 将删除操作写回到客户端中的cookie中

					out.print("已删除的cookie: " + cookie.getName() + "<br/>");
				}

				out.print("名称: " + cookie.getName() + "--> ");
				out.print("值: " + URLDecoder.decode(cookie.getValue(), "UTF-8") + "<br/>");
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
