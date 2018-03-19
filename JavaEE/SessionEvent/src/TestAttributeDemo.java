import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class TestAttributeDemo
 */
@WebServlet("/TestAttributeDemo")
public class TestAttributeDemo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TestAttributeDemo() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");

		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(); //第一次执行时，将Session id写入Cookie,然后发送给客户端

		out.println("<h4>测试HttpSessionListener和HttpSessionAttributeListener</h4>");
		out.println("<b>当前活动会话总数:</b>");
		out.println(SessionCounter.getActivedSessionCount() + "<br>");
		
		session.setAttribute("name1", "value1");
		session.setAttribute("name2", "value2");

		out.println("<b>添加两个属性后的会话范围属性</b><br>");
		Enumeration<String> e = session.getAttributeNames();

		while (e.hasMoreElements()) {
			String name = (String) e.nextElement();
			out.println(name + "-->" + session.getAttribute(name) + "<br/>");
		}

		session.removeAttribute("name2");

		out.println("<b>删除一个属性后的会话范围属性:</b><br>");
		e = session.getAttributeNames();

		while (e.hasMoreElements()) {
			String name = (String) e.nextElement();
			out.println(name + "-->" + session.getAttribute(name) + "<br>");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
