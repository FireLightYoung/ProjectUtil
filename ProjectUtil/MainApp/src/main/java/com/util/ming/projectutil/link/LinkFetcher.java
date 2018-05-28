package com.util.ming.projectutil.link;

import android.text.TextUtils;
import android.widget.ImageView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;

/**
 * Created by xxhx on 2017/6/6.
 */

public class LinkFetcher {

    private OnLinkListener mOnLinkListener;
    private LoadFailedListener mOnFailedListener;
    private LoadLinkImageListener mLoadLinkImageListener;
    private GetFirstImageUrl mGetFirstImageUrl;
    public ImageView view;


    public interface GetFirstImageUrl {
        void ongetFirstImageUrl(String imageUrl);
    }

    public interface OnLinkListener {
        void onLinkDataReady(String title, String image);
    }

    public interface LoadLinkImageListener {

        void onLoadingComplete(String title, String image, ImageView view);
    }

    public interface LoadFailedListener {

        void onLoadingFailed();
    }

    public void loadUrl(final String url, OnLinkListener listener) {
        mOnLinkListener = listener;

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL linkUrl = new URL(url);
                    Document document = Jsoup.parse(linkUrl, 2000);
                    URL imageUrl = null;
                    if (url.contains("mp.weixin.qq.com")) {
                        Elements imgElements = document.getElementsByTag("img");
                        for(int i = 0; i < imgElements.size(); i++) {
                            String src = imgElements.get(i).attr("src");
                            if(src != null && src.indexOf("mmbiz.qpic.cn") > 0) {
                                imageUrl = new URL(linkUrl, src);
                                break;
                            }
                        }
                    }
                    if(imageUrl == null) {
                        Element img = document.getElementsByTag("img").first();
                        if(img != null) imageUrl = new URL(linkUrl, img.attr("src"));
                    }
                    if(mOnLinkListener != null) {
                        mOnLinkListener.onLinkDataReady(document.title(), imageUrl == null ? "" : imageUrl.toString());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }


    public void loadUrlImageView(final String url, final int outTime, ImageView prcView, LoadLinkImageListener onLoadingComplete) {
        mLoadLinkImageListener = onLoadingComplete;
        this.view = prcView;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL linkUrl = new URL(url);
                    URL imageUrl = null;
                    Document document = Jsoup.parse(linkUrl, outTime > 2000 ? outTime : 2000);
                    Elements imgElements = document.getElementsByTag("img");
                    if (url.contains("mp.weixin.qq.com")) {
                        for (int i = 0; i < imgElements.size(); i++) {
                            String src = imgElements.get(i).attr("src");
                            if (src != null && src.indexOf("mmbiz.qpic.cn") > 0) {
                                imageUrl = new URL(linkUrl, src);
                                break;
                            }
                        }
                    }
                    if (imageUrl == null) {
                        if (imgElements != null && imgElements.size() > 0) {
                            for (int i = 0; i < imgElements.size(); i++) {
                                String urlStr = imgElements.get(i).attr("src");
                                if (!TextUtils.isEmpty(urlStr) && isImageUrlType(urlStr)) {
                                    imageUrl = new URL(linkUrl, urlStr);
                                    break;
                                }
                            }

                        }
                    }
                    if (mLoadLinkImageListener != null) {
                        mLoadLinkImageListener.onLoadingComplete(document.title(), imageUrl == null ? "" : imageUrl.toString(), view);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }


    public void loadUrlImage(final String url, final int outTime, OnLinkListener listener,LoadFailedListener failedListener) {
        mOnLinkListener = listener;
        mOnFailedListener = failedListener;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL linkUrl = new URL(url);
                    URL imageUrl = null;
                    Document document = Jsoup.parse(linkUrl, outTime > 2000 ? outTime : 2000);
                    Elements imgElements = document.getElementsByTag("img");
                    if (url.contains("mp.weixin.qq.com")) {
                        for (int i = 0; i < imgElements.size(); i++) {
                            String src = imgElements.get(i).attr("src");
                            if (src != null && src.indexOf("mmbiz.qpic.cn") > 0) {
                                imageUrl = new URL(linkUrl, src);
                                break;
                            }
                        }
                    }
                    if (imageUrl == null) {
                        if (imgElements != null && imgElements.size() > 0) {
                            for (int i = 0; i < imgElements.size(); i++) {
                                String urlStr = imgElements.get(i).attr("src");
                                if (!TextUtils.isEmpty(urlStr) && isImageUrlType(urlStr)) {
                                    imageUrl = new URL(linkUrl, urlStr);
                                    break;
                                }
                            }

                        }
                    }
                    if (mOnLinkListener != null) {
                        mOnLinkListener.onLinkDataReady(document.title(), imageUrl == null ? "" : imageUrl.toString());
                    }
                } catch (IOException e) {
                    mOnFailedListener.onLoadingFailed();
                    e.printStackTrace();
                }
            }
        }).start();

    }

    public boolean isImageUrlType(String url) {
        if (!TextUtils.isEmpty(url)) {
            String prn = url.substring(url.lastIndexOf("/") + 1, url.length()).toLowerCase();
            String end = url.substring(url.lastIndexOf(".") + 1, url.length()).toLowerCase();
//            if ("jpg".equals(end) || "png".equals(end) || "jpeg".equals(end) || "bmp".equals(end) || end.equals("ico") ||
//                    "jpeg2000".equals(end) || "psd".equals(end) || "tiff".equals(end) || "pic".equals(end) || "pcx".equals(end) || end.equals("cdr") ||
//                    "raw".equals(end) || "ai".equals(end) || "tga".equals(end) || "exif".equals(end) || "fpx".equals(end) || end.equals("svg") ||
//                    "dxf".equals(end) || "ufo".equals(end) || "eps".equals(end) || "hdri".equals(end) || "fpx".equals(end) || end.equals("svg")) {
//
//                return true;
//            }
            //如果是gif的图 不要
            if ("gif".equals(end) || "GIF".equals(end)) {
                return false;
            }
            return true;
        }
        return false;

    }


    /**
     * 获取url第一张可用的图片
     *
     * @param url
     * @param outTime
     * @param onLoadingComplete
     */
    public void getFirsImageUrl(final String url, final int outTime, GetFirstImageUrl onLoadingComplete) {
        mGetFirstImageUrl = onLoadingComplete;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL linkUrl = new URL(url);
                    URL imageUrl = null;
                    Document document = Jsoup.parse(linkUrl, outTime > 2000 ? outTime : 2000);
                    Elements imgElements = document.getElementsByTag("img");
                    if (url.contains("mp.weixin.qq.com")) {
                        for (int i = 0; i < imgElements.size(); i++) {
                            String src = imgElements.get(i).attr("src");
                            if (src != null && src.indexOf("mmbiz.qpic.cn") > 0) {
                                imageUrl = new URL(linkUrl, src);
                                break;
                            }
                        }
                    }
                    if (imageUrl == null) {
                        if (imgElements != null && imgElements.size() > 0) {
                            for (int i = 0; i < imgElements.size(); i++) {
                                String urlStr = imgElements.get(i).attr("src");
                                if (!TextUtils.isEmpty(urlStr) && isImageUrlType(urlStr)) {
                                    imageUrl = new URL(linkUrl, urlStr);
                                    break;
                                }
                            }
                        }
                    }
                    if (mGetFirstImageUrl != null) {
                        mGetFirstImageUrl.ongetFirstImageUrl(imageUrl != null ? imageUrl.toString() : "");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

}
