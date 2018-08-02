package book.view;

public class ErrorView implements BookView {

	@Override
	public void display(Object object) {
		// 비정상 상황의 응답은 String 타입을 발생
		String erroMsg = (String) object;
		System.err.println(erroMsg);

	}

}
