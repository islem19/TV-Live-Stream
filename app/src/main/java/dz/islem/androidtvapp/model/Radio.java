package dz.islem.androidtvapp.model;

import android.content.Context;

import com.google.gson.annotations.SerializedName;

public class Radio {

    @SerializedName("id") private int id;
    @SerializedName("title") private String title;
    @SerializedName("description") private String description;
    @SerializedName("localImageResource") private String thumbnail;
    @SerializedName("url") private String url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public int getImageResource(Context context) {
        return context.getResources()
                .getIdentifier(thumbnail, "drawable", context.getPackageName());
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
