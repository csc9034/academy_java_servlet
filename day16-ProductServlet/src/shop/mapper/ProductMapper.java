package shop.mapper;

import java.util.List;
import java.util.Map;
import shop.vo.Product;

/**
 * ProductMapper.xml 에 정의되어 있는
 * 각 쿼리 아이디를 메소드로 매핑하는 인터페이스
 * @author CHO
 *
 */
public interface ProductMapper {
	
	/**
	 * Product 전체 목록 조회
	 * @return : Product 전체 목록을 Map 으로 리턴
	 */
	public abstract List<Product> selectAll();
	
	/**
	 * 매개변수로 입력된 prodCode 에 해당하는 
	 * 제품 한 건의 정보를 조회 
	 * @param empno : 조회 할 직원 사번
	 * @return      : 직원 1명의 상세 정보
	 */
	public abstract Product selectOne(Product product);
	
	/**
	 * 제품의 정보 1건을 insert
	 * @param product : 추가할 직원의 정보가 담긴 product 객체
	 * @return	      : 추가에 성공한 행의 개수
	 */
	public abstract int insert(Product product);
	
	/**
	 * 제품 정보를 1건 update
	 * @param product : 수정할 제품의 정보가 담긴 product 객체
	 * @return		  : 수정에 성공한 행의 개수
	 */
	public abstract int update(Product product);
	
	public abstract int delete(Product product);
	
	public abstract String isExists(Product product);
}
