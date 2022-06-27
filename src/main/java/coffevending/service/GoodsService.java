package coffevending.service;

import coffevending.model.Product;
import coffevending.repository.GoodsRepository;

import java.util.List;
import java.util.stream.Collectors;

public class GoodsService {
    private final GoodsRepository goodsRepository = new GoodsRepository();

    public List<Product> getAll(){
        return goodsRepository.getAll();
    }

    public List<Product> getFilter(List<Product> products, String filter){
                return products.stream().filter(product -> product.getName().toLowerCase().contains(filter.toLowerCase())).collect(Collectors.toList());
    }
}
