package book.dao;

import java.util.List;

import book.exception.DuplicateException;
import book.exception.NotFoundException;
import book.vo.Book;

public interface BookShelf {
	/**
	 * 1건 입력
	 * @param book	: 추가할 책
	 * @return	    : 성공 : 1 , 실패 : 0
	 * @throws DuplicateException 
	 */
	public abstract int insert(Book book) throws DuplicateException; 

	/**
	 * 1건 수정
	 * @param book	: 수정할 책
	 * @return		: 성공 : 1 , 실패 : 0
	 * @throws NotFoundException 
	 * @throws DuplicateException 
	 */
	public abstract int update(Book book) throws NotFoundException; 
	
	/**
	 * 1건 삭제
	 * @param book   : 삭제할 책
	 * @return		 : 성공 : 1 , 실패 : 0
	 * @throws NotFoundException 
	 * @throws DuplicateException 
	 */
	public abstract int delete(Book book) throws NotFoundException; 
	
	/**
	 * 1건 조회
	 * @param book	 : 조회할 1건의 책
	 * @return		 : 해당 되는 book
	 * @throws NotFoundException 
	 */
	public abstract Book select(Book book) throws NotFoundException; 
	
	/**
	 * 모든 책 정보 조회
	 * @return
	 */
	public abstract List<Book> select(); 
	
	/**
	 * low ~ high 사이의 가격대 책을 검색 
	 * 검색 결과는 목록으로 리턴함	
	 * @param low  : 검색 최저가
	 * @param high : 검색 최고가
	 * @return     : 최고가와 최저가 사이의 목록
	 */
	public abstract List<Book> select(int low, int high); 
	
	/**
	 * 책 title에 keyword 가 들어가는 책을 검색 
	 * 검색 결과는 목록으로 리턴함 
	 * @param keyword : 타이틀 키워드
	 * @return		  : 타이틀 키워드가 포함 된 목록
	 */
	public abstract List<Book> select(String keyword); 
	
	/**
	 * 전체 등록된 책의 개수를 구하여 리턴 
	 * @return	: 전체 등록 된 책의 개수
	 */
	public abstract int totalCount(); 
	
	
	     

}
