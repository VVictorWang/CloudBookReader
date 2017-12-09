package com.victor.cloudbookreader.api;

import android.util.Log;

/**
 * @author victor
 * @date 12/9/17
 * @email chengyiwang@hustunique.com
 * @blog www.victorwan.cn                                            #
 */

public class Logger implements LoggingInterceptor.Logger {

    @Override
    public void log(String message) {
        Log.i("@victor", " http :" + message);
    }
}
