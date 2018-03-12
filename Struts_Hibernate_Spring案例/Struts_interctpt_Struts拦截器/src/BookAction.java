import com.opensymphony.xwork2.ActionSupport;

public class BookAction extends ActionSupport {
	private static final long serialVersionUID = 1L;

	public String add() {
		System.out.println("book add");
		return SUCCESS;
	}

	public String del() {
		System.out.println("book del");
		return SUCCESS;
	}

	public String update() {
		System.out.println("book update");
		return SUCCESS;
	}

	public String find() {
		System.out.println("book find");
		return SUCCESS;
	}
}
