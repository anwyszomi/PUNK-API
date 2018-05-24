package pl.b2bnetwork.domain;

import java.util.List;
import java.util.Objects;

public class Ingredients {
    private List<Malt> malt;
    private List<Hops> hops;

    @Override
    public String toString() {
        return "Ingredients: " +
                "malt=" + malt +
                ", hops=" + hops;
    }

    private Ingredients(Builder builder) {
        malt = builder.malt;
        hops = builder.hops;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredients that = (Ingredients) o;
        return Objects.equals(malt, that.malt) &&
                Objects.equals(hops, that.hops);
    }

    @Override
    public int hashCode() {

        return Objects.hash(malt, hops);
    }

    public List<Malt> getMalt() {
        return malt;
    }

    public List<Hops> getHops() {
        return hops;
    }

    public Ingredients() {
    }


    public static final class Builder {
        private List<Malt> malt;
        private List<Hops> hops;

        public Builder() {
        }

        public Builder malt(List<Malt> val) {
            malt = val;
            return this;
        }

        public Builder hops(List<Hops> val) {
            hops = val;
            return this;
        }

        public Ingredients build() {
            return new Ingredients(this);
        }
    }
}
