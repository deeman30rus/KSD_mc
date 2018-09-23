package com.delizarov.common.ui.decorators

import android.content.Context
import android.graphics.Rect
import android.support.annotation.DimenRes
import android.support.v7.widget.RecyclerView
import android.view.View

class ItemOffsetDecoration(
        context: Context,
        @DimenRes itemOffsetId: Int
) : RecyclerView.ItemDecoration() {

    private val itemOffset = context.resources.getDimension(itemOffsetId).toInt()

    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
        super.getItemOffsets(outRect, view, parent, state)

        outRect?.set(0, 0, 0, itemOffset)
    }
}