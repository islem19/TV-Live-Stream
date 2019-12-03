package dz.islem.androidtvapp.fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.google.android.exoplayer2.ui.PlayerView;

import dz.islem.androidtvapp.R;
import dz.islem.androidtvapp.player.AndroidPlayer;

public class PlayerFragment extends Fragment {

    private AndroidPlayer player;
    private String link;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        PlayerView mView = getView().findViewById(R.id.playerView);

        Uri uri = Uri.parse(link);

        mView.setFocusableInTouchMode(false);
        mView.setFocusable(false);
        Log.e("tag", "onActivityCreated: " );
        player = new AndroidPlayer(getContext(), mView);
        player.play(uri);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        link = getArguments().getString("link");
        return inflater.inflate(R.layout.activity_player, container, false);
    }

    @Override
    public void onStop() {
        Log.e("tag", "onStop: " );
        super.onStop();

        player.releasePlayer();
    }
}
