package com.victor.cloudbookreader.widget

import android.animation.Animator
import android.transition.ChangeBounds
import android.transition.TransitionValues
import android.view.ViewGroup
import android.view.animation.AnimationUtils

/**
 * @author victor
 * @date 12/7/17
 * @email chengyiwang@hustunique.com
 * @blog www.victorwan.cn
 */

class CustomChangeBounds : ChangeBounds() {

    override fun createAnimator(sceneRoot: ViewGroup,
                                startValues: TransitionValues?,
                                endValues: TransitionValues?): Animator? {

        val changeBounds = super.createAnimator(sceneRoot, startValues, endValues)
        if (startValues == null || endValues == null || changeBounds == null) {
            return null
        }
        changeBounds.duration = 500
        changeBounds.interpolator = AnimationUtils.loadInterpolator(sceneRoot.context,
                android.R.interpolator.fast_out_slow_in)
        return changeBounds
    }

}