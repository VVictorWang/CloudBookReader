package com.example.xrecyclerview;

/**
 * @author victor
 * @date 12/5/17
 * @email chengyiwang@hustunique.com
 * @blog www.victorwan.cn                                            #
 */
interface BaseRefreshHeader {
    int STATE_NORMAL = 0;
    int STATE_RELEASE_TO_REFRESH = 1;
    int STATE_REFRESHING = 2;
    int STATE_DONE = 3;

    void onMove(float delta);

    boolean releaseAction();

    void refreshComplate();

    int getVisiableHeight();
}
