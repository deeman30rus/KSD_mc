package com.delizarov.common.ui.views

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class VerticalViewPager(context: Context, attrs: AttributeSet?) : ViewPager(context, attrs) {

    init {

        setPageTransformer(true, VerticalPageTransformer())
        overScrollMode = View.OVER_SCROLL_NEVER
    }

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        return super.onTouchEvent(swapXY(ev))
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {

        val intercepted = super.onInterceptTouchEvent(swapXY(ev))
        swapXY(ev)
        return intercepted
    }

    private fun swapXY(event: MotionEvent): MotionEvent {
        val newX = (event.y / height) * width
        val newY = (event.x / width) * height

        event.setLocation(newX, newY)
        return event
    }
}


internal class VerticalPageTransformer : ViewPager.PageTransformer {
    override fun transformPage(page: View, position: Float) {
        when {
            position < -1 -> page.alpha = 0f
            position <= 1 -> {

                page.alpha = 1f

                page.translationX = page.width * -position
                val yPos = position * page.height

                page.translationY = yPos
            }
            else -> page.alpha = 0f
        }
    }

}