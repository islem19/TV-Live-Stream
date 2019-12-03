package dz.islem.androidtvapp.remote;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import dz.islem.androidtvapp.R;
import dz.islem.androidtvapp.interfaces.IMainFragment;
import dz.islem.androidtvapp.model.LocalData;
import dz.islem.androidtvapp.util.Utils;

public class RemoteManager {

    private IMainFragment mFragment;
    private Context mContext;
    private static RemoteManager INSTANCE = null;

    private RemoteManager(Context mContext,IMainFragment mFragment){
        this.mContext = mContext;
        this.mFragment = mFragment;
    }

    public static RemoteManager newInstance(Context mContext, IMainFragment mFragment){
        return (INSTANCE == null ? INSTANCE= new RemoteManager(mContext,mFragment) : INSTANCE);
    }

    public void featchData(){
        Log.e("TAG", "featchData: " );
        String json = Utils.inputStreamToString(mContext.getResources().openRawResource(
                R.raw.data));
        LocalData dataRow = new Gson().fromJson(json, LocalData.class);
        mFragment.notifyOnTvDataAvailable(dataRow.getTv());
        mFragment.notifyOnRadioDataAvailable(dataRow.getRadio());
    }
}
