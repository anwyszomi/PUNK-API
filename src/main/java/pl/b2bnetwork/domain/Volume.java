package pl.b2bnetwork.domain;

import java.util.Objects;

public class Volume {
    private int value;
    private String unit;

    @Override
    public String toString() {
        return value + " ";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Volume volume = (Volume) o;
        return value == volume.value &&
                Objects.equals(unit, volume.unit);
    }

    @Override
    public int hashCode() {

        return Objects.hash(value, unit);
    }

    private Volume(Builder builder) {
        value = builder.value;
        unit = builder.unit;
    }

    public int getValue() {
        return value;
    }

    public String getUnit() {
        return unit;
    }

    public Volume() {
    }


    public static final class Builder {
        private int value;
        private String unit;

        public Builder() {
        }

        public Builder value(int val) {
            value = val;
            return this;
        }

        public Builder unit(String val) {
            unit = val;
            return this;
        }

        public Volume build() {
            return new Volume(this);
        }
    }
}
