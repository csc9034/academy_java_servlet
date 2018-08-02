package book.view;


import book.vo.Book;

public class SingleView implements BookView{

	@Override
	public void display(Object object) {
		
		Book book = (Book)object;
		System.out.println(book);
	}

}
