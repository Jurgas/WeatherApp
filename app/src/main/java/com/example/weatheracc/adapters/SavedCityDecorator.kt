package com.example.weatheracc.adapters

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SavedCityDecorator(private val spaceHeight: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView, state: RecyclerView.State
    ) {
        val dataSize = state.itemCount
        val position = parent.getChildAdapterPosition(view)
        if (dataSize > 0)
            if (dataSize % 2 == 0)
                with(outRect) {
                    if (position >= dataSize - 2) {
                        bottom = spaceHeight
                    }
                }
            else
                with(outRect) {
                    if (position == dataSize - 1) {
                        bottom = spaceHeight
                    }
                }
    }
}
