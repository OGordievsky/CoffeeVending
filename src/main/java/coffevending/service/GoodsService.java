package coffevending.service;

import coffevending.model.Product;
import coffevending.repository.GoodsRepository;

import java.util.List;
import java.util.stream.Collectors;

public class GoodsService {
    private List<Product> productList;
    private final GoodsRepository goodsRepository = new GoodsRepository();

    public GoodsService(){
        this.productList = goodsRepository.getAll();
    }

    public List<Product> getAll() {
        return this.productList;
    }

    public List<Product> getFilter(String filter) {
        return productList.stream().filter(product -> product.getName().toLowerCase().contains(filter.toLowerCase())).collect(Collectors.toList());
    }
}
