package pl.b2bnetwork.domain;

import java.util.Objects;

public class Malt {
    private String name;
    private Amount amount;

    private Malt(Builder builder) {
        name = builder.name;
        amount = builder.amount;
    }

    @Override
    public String toString() {
        return name + ':' + amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Malt malt = (Malt) o;
        return Objects.equals(name, malt.name) &&
                Objects.equals(amount, malt.amount);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, amount);
    }

    public String getName() {
        return name;
    }

    public Amount getAmount() {
        return amount;
    }

    public Malt() {
    }


    public static final class Builder {
        private String name;
        private Amount amount;

        public Builder() {
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder amount(Amount val) {
            amount = val;
            return this;
        }

        public Malt build() {
            return new Malt(this);
        }
    }
}
