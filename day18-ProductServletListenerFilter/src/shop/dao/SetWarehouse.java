package shop.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import shop.exception.DuplicateException;
import shop.exception.NotFoundException;
import shop.vo.Product;

public class SetWarehouse implements GeneralWarehouse {
	// 1. 멤버 변수 선언
	private Set<Product> products = new HashSet<Product>();

	// 2. 기본 생성자 선언

	public SetWarehouse() {

	}

	public SetWarehouse(Set<Product> products) {
		this.products = products;

	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	// 3. 메소드 선언
	public int add(Product product) throws DuplicateException {
		int addCnt = 0;

		// 같은 객체가 있는지 검사
		if (!isExists(product)) {
			products.add(product);
			addCnt++;
		} else {
			throw new DuplicateException("add", product);
		}

		return addCnt;
	}

	@Override
	public Product get(Product product) throws NotFoundException {
		Product found = null;

		if (products.contains(product)) {
			// 찾아올 제품이 존재
			for (Product prod : products) {
				found = prod;
			}
		} else {
			throw new NotFoundException("get", product);
		}

		return found;
	}

	@Override
	public int set(Product product) throws NotFoundException {
		int result = -1;

		if (products.contains(product)) {
			products.remove(product);
			products.add(product);
			result = 1;
		} else {
			throw new NotFoundException("set", product);
		}

		return result;
	}

	@Override
	public int remove(Product product) throws NotFoundException {
		int rmIdx = -1;

		if (products.contains(product)) {
			products.remove(product);
			rmIdx = 1;
		} else {
			throw new NotFoundException("remove", product);
		}

		return rmIdx;
	}

	@Override
	public List<Product> getAllProducts() {
		List<Product> prods = new ArrayList<Product>();

		for (Product prod : products) {
			prods.add(prod);
		}

		return prods;
	}

	private boolean isExists(Product product) {
		return products.contains(product);
	}

}
