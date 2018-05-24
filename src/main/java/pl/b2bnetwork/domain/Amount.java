package pl.b2bnetwork.domain;

import java.util.Objects;

public class Amount {
    private String value;
    private String unit;

    public String getValue() {
        return value;
    }

    public String getUnit() {
        return unit;
    }


    public Amount() {
    }

    @Override
    public String toString() {
        return value + "";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Amount amount = (Amount) o;
        return Objects.equals(value, amount.value) &&
                Objects.equals(unit, amount.unit);
    }

    @Override
    public int hashCode() {

        return Objects.hash(value, unit);
    }

    private Amount(Builder builder) {
        value = builder.value;
        unit = builder.unit;
    }


    public static final class Builder {
        private String value;
        private String unit;

        public Builder() {
        }

        public Builder value(String val) {
            value = val;
            return this;
        }

        public Builder unit(String val) {
            unit = val;
            return this;
        }

        public Amount build() {
            return new Amount(this);
        }
    }
}