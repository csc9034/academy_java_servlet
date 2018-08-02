package book.vo;

public class Book {
//	- bookId  : String  (책을 관리할 아이디 BK001 등)
//	- title   : String  (책 제목)
//	- author  : String  (저자명)
//	- price   : int     (가격)
//	- isbn    : String  (13자리 숫자 도서 ISBN)
//	- publish : String  (출판사)
	
	// 1. 멤버 변수 선언
	private String bookId;
	private String title;
	private String author;
	private int price;
	private String isbn;
	private String publish;
	
	// 2. 생성자 선언부
	// (1) 기본생성자
	public Book() {

	}
	
	// (2) 매개 변수가 있는 생성자 (모두)
	public Book (String bookId) {
		this.bookId = bookId;
	}
	
	public Book (String bookId, String title) {
		this(bookId);
		this.title = title;
	}

	public Book (String bookId, String title, String author) {
		this(bookId, title);
		this.author = author;
	}

	public Book (String bookId, String title, String author, int price) {
		this(bookId, title, author);
		this.price = price;
	}
	
	public Book (String bookId, String title, String author, int price, String isbn) {
		this(bookId, title, author, price);
		this.isbn = isbn;
	}

	public Book (String bookId, String title, String author, int price, String isbn, String publish) {
		this(bookId, title, author, price, isbn);
		this.publish = publish;
	}


	
	// 3. setter, getter 메소드 선언
	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getPublish() {
		return publish;
	}

	public void setPublish(String publish) {
		this.publish = publish;
	}

	
	// 4. toString(), equals(), hashCode() 오버라이딩
	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", title=" + title + ", author=" + author + ", price=" + price + ", isbn="
				+ isbn + ", publish=" + publish + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookId == null) ? 0 : bookId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (bookId == null) {
			if (other.bookId != null)
				return false;
		} else if (!bookId.equals(other.bookId))
			return false;
		return true;
	}
}
