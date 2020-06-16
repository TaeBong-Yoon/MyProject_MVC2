package jsp.member.action;

//페이지 처리 위한 클래스

public class ActionForward {

	private boolean isRedirect = false;
	private String nextPath = null;
	
	public boolean isRedirect() {
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
	public String getNextPath() {
		return nextPath;
	}
	public void setNextPath(String nextPath) {
		this.nextPath = nextPath;
	}
	
}
