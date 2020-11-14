package mahajan.prateek.intuit.oms.repository.model;

/**
 * Created by: pramahajan on 11/11/20 4:12 AM GMT+05:30
 */
public enum Product {
    CHAIR(100.00f), TABLE(500.30f), SOFA(1000.50f);
    private Float price;

    Product(Float price) {
        this.price = price;
    }

    public Float getPrice() {
        return price;
    }
}
