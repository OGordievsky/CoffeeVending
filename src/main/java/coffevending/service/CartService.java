package coffevending.service;

import coffevending.model.CheckLines;
import coffevending.repository.CartRepository;
import coffevending.repository.CheckRepository;

import java.math.BigDecimal;
import java.util.List;

public class CartService {
    CheckRepository checkRepository = new CheckRepository();
    CartRepository cartRepository = new CartRepository();

    public void newCheck(List<CheckLines> checkLines, BigDecimal totalSum) {
        long checkId = checkRepository.newCheck(totalSum);
        cartRepository.saveAll(checkLines, checkId);
    }
}
