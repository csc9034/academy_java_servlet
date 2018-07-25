package shop.dao;

import static shop.mybatis.MybatisClient.getFactory;

import java.util.List;

import javax.xml.parsers.FactoryConfigurationError;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import shop.exception.DuplicateException;
import shop.exception.NotFoundException;
import shop.mapper.ProductMapper;
import shop.mybatis.MybatisClient;
import shop.vo.Product;

public class MybatisWarehouse implements GeneralWarehouse {

	private SqlSessionFactory factory;

	public MybatisWarehouse() {
		factory = getFactory();
	}

	@Override
	public int add(Product product) throws DuplicateException {
		// 등록 할 대상 제품이 있는지 선 조회
		if (isExists(product)) {
			throw new DuplicateException("등록", product);
		}

		// 1. SqlSession 얻기 : DML 작업은 auto-commit을 활성화
		SqlSession session = factory.openSession(true);
		int addCnt = 0;

		// 2. Mapper 인터페이스 객체를 session에서 얻기
		ProductMapper mapper;
		mapper = session.getMapper(ProductMapper.class);

		try {
			// 3. mapper를 통하여 삭제 진행
			addCnt = mapper.insert(product);

		} finally {

			if (session != null) {
				session.close();
			}
		}
		return addCnt;
	}

	@Override
	public Product get(Product product) throws NotFoundException {
		// 조회 할 대상 제품이 있는지 선 조회
		if (!isExists(product)) {
			throw new NotFoundException("수정", product);
		}

		// 1. SqlSession 얻기
		SqlSession session = factory.openSession(false);
		Product found = null;
		// 2. Mapper 인터페이스 객체를 session에서 얻기
		ProductMapper mapper;
		mapper = session.getMapper(ProductMapper.class);

		try {
			// 3. mapper를 통하여 삭제 진행
			found = mapper.selectOne(product);

		} finally {

			if (session != null) {
				session.close();
			}
		}
		return found;
	}

	@Override
	public int set(Product product) throws NotFoundException {
		// 수정 할 대상 제품이 있는지 선 조회
		if (!isExists(product)) {
			throw new NotFoundException("수정", product);
		}

		// 1. SqlSession 얻기 : DML 작업은 auto-commit을 활성화
		SqlSession session = factory.openSession(true);
		int setCnt = 0;

		// 2. Mapper 인터페이스 객체를 session에서 얻기
		ProductMapper mapper;
		mapper = session.getMapper(ProductMapper.class);

		try {
			// 3. mapper를 통하여 삭제 진행
			setCnt = mapper.update(product);

		} finally {

			if (session != null) {
				session.close();
			}
		}
		return setCnt;
	}

	@Override
	public int remove(Product product) throws NotFoundException {
		// 삭제 할 대상 제품이 있는지 선 조회
		if (product != null && !isExists(product)) {
			throw new NotFoundException("삭제", product);
		}

		// 1. SqlSession 얻기 : DML 작업은 auto-commit을 활성화
		SqlSession session = factory.openSession(true);
		int rmCnt = 0;
		// 2. Mapper 인터페이스 객체를 session에서 얻기
		ProductMapper mapper;
		mapper = session.getMapper(ProductMapper.class);

		try {
			// 3. mapper를 통하여 삭제 진행
			rmCnt = mapper.delete(product);

		} finally {

			if (session != null) {
				session.close();
			}
		}
		return rmCnt;

	}

	@Override
	public List<Product> getAllProducts() {
		SqlSession session = factory.openSession();

		ProductMapper mapper;
		mapper = session.getMapper(ProductMapper.class);

		List<Product> products = null;

		try {
			products = mapper.selectAll();

		} finally {

			if (session != null) {
				session.close();

			}
		}
		return products;
	}

	// isExists 지원 메소드 작성
	private boolean isExists(Product product) {
		boolean isExists = false;

		// 1. SqlSession 얻기
		SqlSession session = factory.openSession();

		// 2. Mapper 인터페이스 객체를 session에서 얻기
		ProductMapper mapper;
		mapper = session.getMapper(ProductMapper.class);

		// 3. mapper 객체에 메소드 호출로 쿼리 실행
		try {
			String prodCode = mapper.isExists(product);

			if (prodCode != null) {
				isExists = true;
			}

		} finally {

			if (session != null) {
				session.close();
			}
		}
		return isExists;
	}

}
