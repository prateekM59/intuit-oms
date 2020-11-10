package mahajan.prateek.intuit.oms.repository.model;

/**
 * Created by: pramahajan on 11/11/20 4:12 AM GMT+05:30
 */
public enum Product {
    CHAIR(100), TABLE(500), SOFA(1000);
    private int price;

    Product(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
