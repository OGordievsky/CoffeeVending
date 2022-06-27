package coffevending.model;

import java.math.BigDecimal;

public class CheckLines {
    private String name;
    private long ean;
    private int count;
    private BigDecimal total;

    public CheckLines(Product product) {
        this.name = product.getName();
        this.ean = product.getEan();
        this.count = 1;
        this.total = new BigDecimal(String.valueOf(product.getPrice()));
    }

    public void addIncrease(Product product) {
        this.count++;
        this.total = this.total.add(product.getPrice());
    }

    public String getName() {
        return name;
    }

    public long getEan() {
        return ean;
    }

    public int getCount() {
        return count;
    }

    public BigDecimal getTotal() {
        return total;
    }
}