package com.util.ming.projectutil.link;

import android.os.Message;
import android.os.SystemClock;

import org.apache.log4j.chainsaw.Main;

/**
 * Created by ming on 2018/5/10.
 */

public class LinkUtil {

    protected static boolean handleLinkText(final String text) {
        String urlPattern = "((http|ftp|https)://)?(([a-zA-Z0-9\\._-]+\\.[a-zA-Z]{2,6})|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]{1,5})*(/[a-zA-Z0-9#\\&%_\\./-~-]*)?";
        //匹配成功，是一个网址
        if (text != null && text.matches(urlPattern)) {
            final int what = text.hashCode();
            final long uptime = SystemClock.uptimeMillis();
            LinkFetcher fetcher = new LinkFetcher();
            String url = text.contains("://") ? text : ("http://" + text);
            fetcher.loadUrl(url, new LinkFetcher.OnLinkListener() {
                @Override
                public void onLinkDataReady(String title, String image) {
//                    if (SystemClock.uptimeMillis() - uptime < 2000) {
//                        mTextHandler.removeMessages(what);
//                        sendLinkMessage(text, title, image);
//                    }
                }
            });
//            Message msg = mTextHandler.obtainMessage(what);
//            msg.obj = text;
//            mTextHandler.sendMessageAtTime(msg, uptime + 2000);
            return true;
        }
        return false;
    }
}
