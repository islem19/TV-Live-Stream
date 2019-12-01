package dz.islem.androidtvapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class LocalData implements Serializable {

    @SerializedName ("tv") private List<Tv> tv;
    @SerializedName ("radio") private List<Radio> radio;

    public List<Radio> getRadio() {
        return radio;
    }

    public void setRadio(List<Radio> radio) {
        this.radio = radio;
    }

    public List<Tv> getTv() {
        return tv;
    }

    public void serTv(List<Tv> tv) {
        this.tv = tv;
    }
}
