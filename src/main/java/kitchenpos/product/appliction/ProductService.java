package kitchenpos.application;

import kitchenpos.dao.ProductDao;
import kitchenpos.product.domain.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
public class ProductService {
    private final ProductDao productDao;

    public ProductService(final ProductDao productDao) {
        this.productDao = productDao;
    }

    @Transactional
    public Product create(final Product product) {
        this.validateProductByPrice(product);

        return productDao.save(product);
    }

    /**
     * 해당 상품의 가격이 적합한지 검사합니다.
     * @param product
     */
    private void validateProductByPrice(Product product) {
        final BigDecimal price = product.getPrice();

        if (Objects.isNull(price) || price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException();
        }
    }

    public List<Product> list() {
        return productDao.findAll();
    }
}
