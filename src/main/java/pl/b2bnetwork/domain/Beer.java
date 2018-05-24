package pl.b2bnetwork.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Beer {
    private int id;
    private String name;
    private Double abv;
    private int ibu;
    private int ebc;
    private int srm;
    private double ph;

    @JsonProperty("attenuation_level")
    private int attenuationLevel;

    private Volume volume;
    @JsonProperty("boil_volume")
    private BoilVolume boilVolume;

    private Ingredients ingredients;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Beer beer = (Beer) o;
        return id == beer.id &&
                ibu == beer.ibu &&
                ebc == beer.ebc &&
                srm == beer.srm &&
                Double.compare(beer.ph, ph) == 0 &&
                attenuationLevel == beer.attenuationLevel &&
                Objects.equals(name, beer.name) &&
                Objects.equals(abv, beer.abv) &&
                Objects.equals(volume, beer.volume) &&
                Objects.equals(boilVolume, beer.boilVolume) &&
                Objects.equals(ingredients, beer.ingredients);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, abv, ibu, ebc, srm, ph, attenuationLevel, volume, boilVolume, ingredients);
    }

    @Override
    public String toString() {
        return "Beer: " +
                " name='" + name +
                ", alcohol by volume=" + abv +
//                ", ibu=" + ibu +'\n'+
//                ", ebc=" + ebc +'\n' +
//                ", srm=" + srm +'\n' +
//                ", ph=" + ph +'\n'+
                ", attenuationLevel=" + attenuationLevel +
                ", volume=" + volume +
                ", boilVolume=" + boilVolume +
                ", ingredients=" + ingredients;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Beer() {
    }

    private Beer(Builder builder) {
        id = builder.id;
        name = builder.name;
        abv = builder.abv;
        ibu = builder.ibu;
        ebc = builder.ebc;
        srm = builder.srm;
        ph = builder.ph;
        attenuationLevel = builder.attenuationLevel;
        volume = builder.volume;
        boilVolume = builder.boilVolume;
        ingredients = builder.ingredients;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getAbv() {
        return abv;
    }

    public int getIbu() {
        return ibu;
    }

    public int getEbc() {
        return ebc;
    }

    public int getSrm() {
        return srm;
    }

    public double getPh() {
        return ph;
    }

    public int getAttenuationLevel() {
        return attenuationLevel;
    }

    public Volume getVolume() {
        return volume;
    }

    public BoilVolume getBoilVolume() {
        return boilVolume;
    }

    public Ingredients getIngredients() {
        return ingredients;
    }

    public static final class Builder {
        private int id;
        private String name;
        private Double abv;
        private int ibu;
        private int ebc;
        private int srm;
        private double ph;
        private int attenuationLevel;
        private Volume volume;
        private BoilVolume boilVolume;
        private Ingredients ingredients;


        public Builder id(int val) {
            id = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder abv(Double val) {
            abv = val;
            return this;
        }

        public Builder ibu(int val) {
            ibu = val;
            return this;
        }

        public Builder ebc(int val) {
            ebc = val;
            return this;
        }

        public Builder srm(int val) {
            srm = val;
            return this;
        }

        public Builder ph(double val) {
            ph = val;
            return this;
        }

        public Builder attenuationLevel(int val) {
            attenuationLevel = val;
            return this;
        }

        public Builder volume(Volume val) {
            volume = val;
            return this;
        }

        public Builder boilVolume(BoilVolume val) {
            boilVolume = val;
            return this;
        }

        public Builder ingredients(Ingredients val) {
            ingredients = val;
            return this;
        }

        public Beer build() {
            return new Beer(this);
        }
    }
}
