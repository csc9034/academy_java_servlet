package book.mapper;

import java.util.List;
import java.util.Map;

import book.vo.Book;


public interface BookMapper {
	
	/**
	 * Book 전체 목록 조회
	 * @return : Book 전체 목록을 Map 으로 리턴
	 */
	public abstract List<Book> selectAll();
	
	/**
	 * 매개변수로 입력된 prodCode 에 해당하는 
	 * 책 한 건의 정보를 조회 
	 * @param empno : 조회 할 직원 사번
	 * @return      : 직원 1명의 상세 정보
	 */
	public abstract Book selectOne(Book book);
	
	/**
	 * 책의 정보 1건을 insert
	 * @param book 	  : 추가할 직원의 정보가 담긴 book 객체
	 * @return	      : 추가에 성공한 행의 개수
	 */
	public abstract int insert(Book book);
	
	/**
	 * 책 정보를 1건 update
	 * @param book    : 수정할 책의 정보가 담긴 book 객체
	 * @return		  : 수정에 성공한 행의 개수
	 */
	public abstract int update(Book book);
	
	/**
	 * 책 정보를 1건 delete
	 * @param book    : 삭제할 책의 정보가 담긴 book 객체
	 * @return		  : 삭제에 성공한 행의 개수
	 */
	public abstract int delete(Book book);
	
	/**
	 * 책의 데이터가 존재하는 판단
	 * @param book 	: 조회할 책의 정보가 담긴 book 객체
	 * @return		: 존재 : true , 존재X : false
	 */
	public abstract String isExists(Book book);
	
	/**
	 * low ~ high 사이의 가격대 책을 검색 
	 * 검색 결과는 목록으로 리턴함	
	 * @param low  : 검색 최저가
	 * @param high : 검색 최고가
	 * @return     : 최고가와 최저가 사이의 목록
	 */
	public abstract List<Book> selectPrice(Map<String, Integer> price); 
	
	/**
	 * 책 title에 keyword 가 들어가는 책을 검색 
	 * 검색 결과는 목록으로 리턴함 
	 * @param keyword : 타이틀 키워드
	 * @return		  : 타이틀 키워드가 포함 된 목록
	 */
	public abstract List<Book> selectKeyword(String keyword); 
	
	/**
	 * 전체 등록된 책의 개수를 구하여 리턴 
	 * @return	: 전체 등록 된 책의 개수
	 */
	public abstract int selectTotalCnt(); 
	

}
