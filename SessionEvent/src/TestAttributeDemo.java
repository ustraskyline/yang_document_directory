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
		HttpSession session = request.getSession(); //��һ��ִ��ʱ����Session idд��Cookie,Ȼ���͸��ͻ���

		out.println("<h4>����HttpSessionListener��HttpSessionAttributeListener</h4>");
		out.println("<b>��ǰ��Ự����:</b>");
		out.println(SessionCounter.getActivedSessionCount() + "<br>");
		
		session.setAttribute("name1", "value1");
		session.setAttribute("name2", "value2");

		out.println("<b>����������Ժ�ĻỰ��Χ����</b><br>");
		Enumeration<String> e = session.getAttributeNames();

		while (e.hasMoreElements()) {
			String name = (String) e.nextElement();
			out.println(name + "-->" + session.getAttribute(name) + "<br/>");
		}

		session.removeAttribute("name2");

		out.println("<b>ɾ��һ�����Ժ�ĻỰ��Χ����:</b><br>");
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
