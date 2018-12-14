package com.delizarov.common.ui.viewholders

import android.support.v7.widget.RecyclerView
import android.view.View

abstract class ViewHolderBase<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun bind(item: T)

    open fun clear() {}
}
