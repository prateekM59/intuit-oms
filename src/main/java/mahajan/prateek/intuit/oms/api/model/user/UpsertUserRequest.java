package mahajan.prateek.intuit.oms.api.model.user;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import mahajan.prateek.intuit.oms.repository.model.UsersType;

import javax.validation.constraints.NotNull;

/**
 * Created by: pramahajan on 11/13/20 2:57 AM GMT+05:30
 */
@JsonDeserialize(builder = UpsertUserRequest.Builder.class)
public final class UpsertUserRequest {
    @NotNull
    private final String name;
    @NotNull
    private final String email;
    @NotNull
    private final String phone;
    @NotNull
    private final String address;
    private final UsersType type;

    @Override
    public String toString() {
        return "UpsertUserRequest{" + "name='" + name + '\'' + ", email='" + email + '\'' + ", phone='" + phone + '\'' + ", address='" + address
                + '\'' + ", type=" + type + '}';
    }

    public String getName() {
        return name;
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

    public UsersType getType() {
        return type;
    }

    private UpsertUserRequest(Builder builder) {
        this.name = builder.name;
        this.email = builder.email;
        this.phone = builder.phone;
        this.address = builder.address;
        this.type = builder.type == null ? UsersType.NORMAL : Enum.valueOf(UsersType.class, builder.type);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        @NotNull
        private String name;
        @NotNull
        private String email;
        @NotNull
        private String phone;
        @NotNull
        private String address;
        @NotNull
        private String type;

        private Builder() {

        }

        public Builder withName(String name) {
            this.name = name;
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

        public Builder withType(String type) {
            this.type = type;
            return this;
        }

        public UpsertUserRequest build() {
            return new UpsertUserRequest(this);
        }
    }
}
