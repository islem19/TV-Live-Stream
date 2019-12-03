package dz.islem.androidtvapp.player;

import android.net.Uri;

import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.dash.DashMediaSource;
import com.google.android.exoplayer2.source.dash.DefaultDashChunkSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;

public class MediaSourceBuilder {

    //Build various MediaSource depending upon the type of Media for a given video uri
    public MediaSource build(Uri uri) {
        String userAgent = "Navi-Android-Play";
        String lastPath = uri.getLastPathSegment();
        String FORMAT_MP3 = "mp3";
        String FORMAT_MP4 = "mp4";
        String FORMAT_M3U8 = "m3u8";

        DefaultHttpDataSourceFactory defaultHttpDataSourceFactory = new DefaultHttpDataSourceFactory(userAgent);

        if (lastPath.contains(FORMAT_MP3) ||
                lastPath.contains(FORMAT_MP4)) {

            return new ExtractorMediaSource.Factory(defaultHttpDataSourceFactory)
                    .createMediaSource(uri);

        } else if (lastPath.contains(FORMAT_M3U8)) {

            return new HlsMediaSource.Factory(defaultHttpDataSourceFactory)
                    .createMediaSource(uri);

        } else {
            DefaultDashChunkSource.Factory dashChunkSourceFactory = new DefaultDashChunkSource.Factory(defaultHttpDataSourceFactory);

            return new DashMediaSource.Factory(dashChunkSourceFactory, defaultHttpDataSourceFactory).createMediaSource(uri);
        }
    }
}

