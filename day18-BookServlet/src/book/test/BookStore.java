package book.test;

import book.controller.BookManager;
import book.dao.BookShelf;
import book.factory.BookShelfFactory;
import book.vo.Book;

public class BookStore {

	public static void main(String[] args) {
		// 1. 책장에 들여놓을 책 선반이 먼저 필요, 2. 선반을 설치할 책장를 지어야 함

		BookShelf bookshelf = BookShelfFactory.getBookShelf("mybatis");

		// 3. 북매니저 고용.

		BookManager bookmanager = new BookManager(bookshelf);

		// 4. Book 객체 선언과 초기화
		Book b001 = new Book("B001", "역사의 역사", "유시민", 14400, "9788971998557", "돌베게");
		Book b002 = new Book("B002", "모든 순간이 너였다", "하태완", 12420, "9791162202913", "위즈덤하우스");
		Book b003 = new Book("B003", "곰돌이 푸, 행복한 일은 매일 있어", "곰돌이 푸", 10800, "9788925563350", "알에이치코리아");
		Book b004 = new Book("B004", "고양이. 1 ", "베르나르 베르베르", 11520, "9788932919126", "열린책들");
		Book b005 = new Book("B005", "행복해지는 연습을 해요", "전승환", 13050, "9788968331831", "허밍버드");

		// 1. 매장에 도착한 제품을 입고
		bookmanager.insert(b001);
		bookmanager.insert(b002);
		bookmanager.insert(b003);
		bookmanager.insert(b004);
		bookmanager.insert(b005);

		// 2. 입고된 제품 전체 확인
		bookmanager.select();

		// 3. 아디다스 제품 수정
		Book b001_2 = new Book("B001", null, "조성철", 0, null, null);

		bookmanager.update(b001_2);

		// 4. 수정된 아디다스 제품 수정 확인
		System.out.println();
		bookmanager.select(new Book("B001", null, null, 0, null, null));

		// 5. 판매 종료할 제품 폐기
		bookmanager.delete(new Book("B001", null, null, 0, null, null));

		// 6. 폐기 확인을 위해 전체 목록 재 조회
		System.out.println("==============================================================================");
		bookmanager.select();

		// 7. 없는 코드 조회
		Book p007 = new Book("P007");
		bookmanager.select(p007);

		// 8. 없는 제품 수정 ==> NotFoundException 발생 상황
		bookmanager.update(p007);

		// 9. 있는 제품 중복 추가 ==> DuplicateException
		bookmanager.insert(b002);
		
		bookmanager.select(10000, 12000);
		bookmanager.select("곰돌이");
		bookmanager.selectTotalCnt();

		bookmanager.delete(b001);
		bookmanager.delete(b002);
		bookmanager.delete(b003);
		bookmanager.delete(b004);
		bookmanager.delete(b005);

	}
}
