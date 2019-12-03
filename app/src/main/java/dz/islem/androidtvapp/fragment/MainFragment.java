/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package dz.islem.androidtvapp.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.leanback.app.BackgroundManager;
import androidx.leanback.app.BrowseSupportFragment;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.HeaderItem;
import androidx.leanback.widget.ListRow;
import androidx.leanback.widget.ListRowPresenter;
import androidx.leanback.widget.OnItemViewClickedListener;
import androidx.leanback.widget.OnItemViewSelectedListener;
import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.PresenterSelector;
import androidx.leanback.widget.Row;
import androidx.leanback.widget.RowPresenter;

import android.util.Log;

import java.util.List;

import dz.islem.androidtvapp.R;
import dz.islem.androidtvapp.interfaces.IMainFragment;
import dz.islem.androidtvapp.model.Radio;
import dz.islem.androidtvapp.model.Tv;
import dz.islem.androidtvapp.presenter.TvChannelPresenter;
import dz.islem.androidtvapp.presenter.RadioPresenter;
import dz.islem.androidtvapp.remote.RemoteManager;


public class MainFragment extends BrowseSupportFragment implements IMainFragment {
    private static final String TAG = "MainFragment";
    private static ArrayObjectAdapter mRowsAdapter;
    private static BackgroundManager mBackgroundManager;
    private static ArrayObjectAdapter tvRowAdapter;
    private static ArrayObjectAdapter radioRowAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate");
        super.onCreate(savedInstanceState);

        setupUIElements();
        setupAdapter();
        loadData();
        setupBackgroundManager();
        setupEventListeners();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void setupUIElements() {
        //setBadgeDrawable(getActivity().getResources().getDrawable(R.drawable.videos_by_google_banner));
        setTitle("Android TV Channels!"); // Badge, when set, takes precedent
        // over title
        setHeadersState(HEADERS_DISABLED);
        setHeadersTransitionOnBackEnabled(false);

        // set fastLane (or headers) background color
        setBrandColor(Color.TRANSPARENT);

        setHeaderPresenterSelector(new PresenterSelector() {
            @Override
            public Presenter getPresenter(Object item) {
                return new TvChannelPresenter();
            }
        });
    }

    private void setupAdapter() {

        mRowsAdapter = new ArrayObjectAdapter(new ListRowPresenter());

        /* First Row Adapter*/
        HeaderItem radioHeader = new HeaderItem(0,"Radio");
        RadioPresenter mRadioPresenter = new RadioPresenter();
        radioRowAdapter = new ArrayObjectAdapter(mRadioPresenter);
        mRowsAdapter.add(new ListRow(radioHeader, radioRowAdapter));

        /* Card Row Adapter*/
        HeaderItem tvHeader = new HeaderItem(1,"TV Channel");
        TvChannelPresenter mTvChannelPresenter = new TvChannelPresenter();
        tvRowAdapter = new ArrayObjectAdapter(mTvChannelPresenter);
        mRowsAdapter.add(new ListRow(tvHeader,tvRowAdapter));

        setAdapter(mRowsAdapter);
    }

    private void setupBackgroundManager() {
        mBackgroundManager = BackgroundManager.getInstance(getActivity());
        mBackgroundManager.attach(getActivity().getWindow());
    }

    private void setupEventListeners() {
        setOnItemViewClickedListener(new ItemViewClickedListener());
        setOnItemViewSelectedListener(new ItemViewSelectedListener());
    }

    private void loadData(){
        RemoteManager.newInstance(getContext(),this).featchData();
    }

    @Override
    public void notifyOnTvDataAvailable(List<Tv> tv) {
        for (Tv t : tv) tvRowAdapter.add(t);
    }

    @Override
    public void notifyOnRadioDataAvailable(List<Radio> radio) {
        for (Radio r :radio) radioRowAdapter.add(r);
    }


    private class ItemViewClickedListener implements OnItemViewClickedListener {
        @Override
        public void onItemClicked(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {

            PlayerFragment mFragment= new PlayerFragment();
            Bundle bundle = new Bundle();
            bundle.putString("link","https://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4");
            //bundle.putString("link","https://thepaciellogroup.github.io/AT-browser-tests/audio/jeffbob.mp3");
            mFragment.setArguments(bundle);
            getFragmentManager().beginTransaction()
                    .replace(R.id.main_browse_fragment, mFragment, null)
                    .addToBackStack(null)
                    .commit();

        }
    }

    private class ItemViewSelectedListener implements OnItemViewSelectedListener {
        @Override
        public void onItemSelected(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {

            if (item instanceof Tv) {
                mBackgroundManager.setDrawable(getResources().getDrawable(R.drawable.bg_tv));
            }else {
                mBackgroundManager.setDrawable(getResources().getDrawable(R.drawable.bg_radio));
            }
        }
    }

}





