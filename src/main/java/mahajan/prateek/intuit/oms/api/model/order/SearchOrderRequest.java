package mahajan.prateek.intuit.oms.api.model.order;

/**
 * Created by: pramahajan on 11/14/20 3:23 AM GMT+05:30
 */
public final class SearchOrderRequest {
    private final DateSearchType dateSearchType;
    private final String product;
    private final Integer userId;
    private final String email;
    private final String phone;

    public DateSearchType getDateSearchType() {
        return dateSearchType;
    }

    public String getProduct() {
        return product;
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

    public static Builder builder() {
        return new Builder();
    }

    private SearchOrderRequest(Builder builder) {
        this.dateSearchType = builder.dateSearchType;
        this.product = builder.product;
        this.userId = builder.userId;
        this.email = builder.email;
        this.phone = builder.phone;
    }

    public static final class Builder {
        private DateSearchType dateSearchType;
        private String product;
        private Integer userId;
        private String email;
        private String phone;

        private Builder() {
        }

        public Builder withDateSearchType(DateSearchType dateSearchType) {
            this.dateSearchType = dateSearchType;
            return this;
        }

        public Builder withProduct(String product) {
            this.product = product;
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

        public SearchOrderRequest build() {
            return new SearchOrderRequest(this);
        }
    }

    public enum DateSearchType {
        LAST_15_MINS,
        OLDER_THAN_15_MINS;
    }

}
