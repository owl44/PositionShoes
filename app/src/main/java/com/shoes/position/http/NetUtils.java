package com.shoes.position.http;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * NetConUtils(是否网络10sp工具类)
 *
 * Created by www on 2017/5/26.
 */

public class NetUtils
{
    /**
     * 检查网络10sp情况
     * @param context
     * @return
     */
   public static boolean isOpenNetwork(Context context)
    {
        ConnectivityManager connManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connManager.getActiveNetworkInfo() != null) {
            return connManager.getActiveNetworkInfo().isAvailable();
        }
        return false;
    }
}
