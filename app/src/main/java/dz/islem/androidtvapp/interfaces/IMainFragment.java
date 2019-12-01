package dz.islem.androidtvapp.interfaces;

import java.util.List;

import dz.islem.androidtvapp.model.Radio;
import dz.islem.androidtvapp.model.Tv;

public interface IMainFragment {

    void notifyOnTvDataAvailable(List<Tv> tv);

    void notifyOnRadioDataAvailable(List<Radio> radio);

}
