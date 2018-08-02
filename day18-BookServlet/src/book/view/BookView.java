package book.view;

public interface BookView {
	
	/**
	 * 입력 된 object에 따라 적절한 응답을 한다.
	 * @param object
	 */
	public abstract void display(Object object);
}
