package mahajan.prateek.intuit.oms.api.model.order;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import mahajan.prateek.intuit.oms.repository.model.Product;

import javax.validation.constraints.NotNull;

/**
 * Created by: pramahajan on 11/14/20 2:05 AM GMT+05:30
 */

@JsonDeserialize(builder = CreateOrderRequest.Builder.class)
public final class CreateOrderRequest {
    @NotNull
    private final Product product;
    @NotNull
    private final Integer quantity;
    @NotNull
    private final Integer userId;
    private final String email;
    private final String phone;
    private final String address;

    public Product getProduct() {
        return product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    private CreateOrderRequest(Builder builder) {
        this.product = Enum.valueOf(Product.class, builder.product);
        this.quantity = builder.quantity;
        this.userId = builder.userId;
        this.email = builder.email;
        this.phone = builder.phone;
        this.address = builder.address;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        @NotNull
        private String product;
        @NotNull
        private Integer quantity;
        @NotNull
        private Integer userId;
        private String email;
        private String phone;
        private String address;

        private Builder() {
        }

        public Builder withProduct(String product) {
            this.product = product;
            return this;
        }

        public Builder withQuantity(Integer quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder withUserId(Integer userId) {
            this.userId = userId;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder withAddress(String address) {
            this.address = address;
            return this;
        }

        public CreateOrderRequest build() {
            return new CreateOrderRequest(this);
        }

    }
}
