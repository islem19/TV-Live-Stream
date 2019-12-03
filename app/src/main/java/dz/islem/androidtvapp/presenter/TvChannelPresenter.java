package dz.islem.androidtvapp.presenter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;

import androidx.leanback.widget.BaseCardView;
import androidx.leanback.widget.ImageCardView;
import androidx.leanback.widget.Presenter;

import dz.islem.androidtvapp.model.Tv;

public class TvChannelPresenter extends Presenter {

    private static Context mContext;
    private static int CARD_WIDTH = 300;
    private static int CARD_HEIGHT = 200;

    private static class  ViewHolder extends Presenter.ViewHolder {
        private Tv tv;
        private ImageCardView mCardView;

        public ViewHolder(View view) {
            super(view);
            mCardView = (ImageCardView) view;
        }

        public Tv getTv() {
            return tv;
        }
        public void setTv(Tv tv) {
            this.tv = tv;
        }
        public ImageCardView getmCardView() {
            return mCardView;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {

        mContext = parent.getContext();

        ImageCardView mCardView = new ImageCardView(mContext);
        mCardView.setCardType(BaseCardView.CARD_TYPE_INFO_UNDER);
        mCardView.setInfoVisibility(BaseCardView.CARD_REGION_VISIBLE_ALWAYS);
        mCardView.setFocusable(true);
        mCardView.setFocusableInTouchMode(true);
        mCardView.setBackgroundColor(Color.WHITE);
        return new ViewHolder(mCardView);
    }

    @Override
    public void onBindViewHolder(Presenter.ViewHolder viewHolder, Object item) {
        Tv tv = (Tv) item;
        ((ViewHolder) viewHolder).setTv(tv);

        ((ViewHolder) viewHolder).mCardView.setTitleText(tv.getTitle());
        ((ViewHolder) viewHolder).mCardView.setContentText(tv.getDescription());
        ((ViewHolder) viewHolder).mCardView.setMainImageDimensions(CARD_WIDTH,CARD_HEIGHT);
        ((ViewHolder) viewHolder).mCardView.setMainImage(mContext.getDrawable(tv.getImageResource(mContext)));

    }

    @Override
    public void onUnbindViewHolder(Presenter.ViewHolder viewHolder) {

    }


}
