package vo;

// 포워딩정보를 저장할수 있는 클래스.
public class ActionForward 
{
	private String path;
	private boolean redirect;
	
	public ActionForward(String path, boolean redirect) 
	{
		this.path = path; this.redirect = redirect;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean isRedirect() {
		return redirect;
	}

	public void setRedirect(boolean redirect) {
		this.redirect = redirect;
	}
	
}
