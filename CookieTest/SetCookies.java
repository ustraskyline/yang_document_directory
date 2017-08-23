
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SetCookies
 */
@WebServlet("/SetCookie.do")
public class SetCookies extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public SetCookies() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String paramName = request.getParameter("name");
		String paramPwd = URLEncoder.encode(request.getParameter("pwd"), "UTF-8");

		Cookie name = new Cookie("name", URLEncoder.encode(paramName, "UTF-8"));
		Cookie pwd = new Cookie("pwd", URLEncoder.encode(paramPwd, "UTF-8"));

		name.setMaxAge(30 * 60);
		pwd.setMaxAge(30 * 60);

		response.addCookie(name);
		response.addCookie(pwd);

		response.setContentType("text/html;charset=UTF-8");

		PrintWriter out = response.getWriter();
		out.println("–’√˚: " + paramName + "<br/>");
		out.println("√‹¬Î£∫" + paramPwd + "<br/>");
	}

}
