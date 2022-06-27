package coffevending.model;

import java.math.BigDecimal;

public class Product {
    private long id;
    private long ean;
    private String name;
    private BigDecimal price;

    public Product(long id, long ean, String name, BigDecimal price) {
        this.id = id;
        this.ean = ean;
        this.name = name;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public long getEan() {
        return ean;
    }

    public void setEan(long ean) {
        this.ean = ean;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
