package com.zwwl.kotlintest.media.mediaplayer;

/**
 * Created by zhanghuipeng on 8/3/21.
 */
public class PlayBean {
    private String url;
    private String name;

    public PlayBean(String url, String name) {
        this.url = url;
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
