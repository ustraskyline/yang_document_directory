
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

		// �ͻ��˷��ͶԱ�Servlet������ʱ,���Cookies���͸�����������request�����е�cookie���ǿͻ��˷�����������cookie
		cookies = request.getCookies();

		response.setContentType("text/html;charset=UTF-8");

		if (cookies != null) {
			out.println("<h4>Cookies���ƺ�ֵ</h4>");
			for (int i = 0; i < cookies.length; i++) {
				cookie = cookies[i];
				if ("name".equals(cookie.getName()) || "pwd".equals(cookie.getName())) {
					cookie.setMaxAge(0); // ɾ��ָ����cookie
					response.addCookie(cookie); // ��ɾ������д�ص��ͻ����е�cookie��

					out.print("��ɾ����cookie: " + cookie.getName() + "<br/>");
				}

				out.print("����: " + cookie.getName() + "--> ");
				out.print("ֵ: " + URLDecoder.decode(cookie.getValue(), "UTF-8") + "<br/>");
			}
		} else {
			out.println("<h4>δ�ҵ�Cookies</h4>");
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
