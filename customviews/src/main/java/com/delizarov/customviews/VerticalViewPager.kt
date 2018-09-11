package com.delizarov.customviews

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View


class VerticalViewPager(ctx: Context, attrs: AttributeSet?) : ViewPager(ctx, attrs) {

    constructor(ctx: Context): this(ctx, null)

    init {
        setPageTransformer(true, VerticalPageTransformer())

        overScrollMode = View.OVER_SCROLL_NEVER
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        val intercepted = super.onInterceptTouchEvent(swapXY(ev))
        swapXY(ev) // return touch coordinates to original reference frame for any child views
        return intercepted
    }

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        return super.onTouchEvent(swapXY(ev))
    }

    /**
     * Swaps the X and Y coordinates of your touch event.
     */
    private fun swapXY(ev: MotionEvent): MotionEvent {
        val width = width.toFloat()
        val height = height.toFloat()

        val newX = ev.y / height * width
        val newY = ev.x / width * height

        ev.setLocation(newX, newY)

        return ev
    }

}


private class VerticalPageTransformer : ViewPager.PageTransformer {

    override fun transformPage(page: View, position: Float) {
        when {
            position < -1 -> // [-Infinity,-1)
                // This page is way off-screen to the left.
                page.alpha = 0f
            position <= 1 -> { // [-1,1]
                page.alpha = 1f

                // Counteract the default slide transition
                page.translationX = page.width * -position

                //set Y position to swipe in from top
                val yPosition = position * page.height
                page.translationY = yPosition

            }
            else -> // (1,+Infinity]
                // This page is way off-screen to the right.
                page.alpha = 0f
        }
    }

}