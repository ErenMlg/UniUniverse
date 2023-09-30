package com.softcross.uniuniverse.presentation.login

import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.core.view.updatePaddingRelative
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutParams
import kotlin.math.abs

class CenterZoomLayoutManager(context: Context, orientation: Int, private val itemsPerPage: Int) :
    LinearLayoutManager(context, orientation, false) {

    private lateinit var recyclerView: RecyclerView
    private val mShrinkDistance = 0.7f
    private val mShrinkAmount = 0.25f


    //This funcs for  the padding start and end items for the be usable
    override fun onLayoutChildren(recycler: RecyclerView.Recycler, state: RecyclerView.State) {
        if (childCount == 0 && state.itemCount > 0) {
            val firstChild = recycler.getViewForPosition(0)
            measureChildWithMargins(firstChild, 0, 0)
            recycler.recycleView(firstChild)
        }
        super.onLayoutChildren(recycler, state)
    }

    override fun measureChildWithMargins(child: View, widthUsed: Int, heightUsed: Int) {
        val lp = (child.layoutParams as LayoutParams).viewLayoutPosition
        super.measureChildWithMargins(child, widthUsed, heightUsed)
        if (lp != 0 && lp != itemCount - 1) return
        // after determining first and/or last items size use it to alter host padding
        when (orientation) {
            RecyclerView.HORIZONTAL -> {
                Log.e("width:", width.toString())
                Log.e("Measuredwidth:", child.measuredWidth.toString())
                val hPadding = ((width - child.measuredWidth) / 2).coerceAtLeast(0)
                Log.e("padding:", hPadding.toString())

                if (!reverseLayout) {
                    if (lp == 0) recyclerView.updatePaddingRelative(start = hPadding)
                    if (lp == itemCount - 1) recyclerView.updatePaddingRelative(end = hPadding)
                } else {
                    if (lp == 0) recyclerView.updatePaddingRelative(end = hPadding)
                    if (lp == itemCount - 1) recyclerView.updatePaddingRelative(start = hPadding)
                }
            }

            RecyclerView.VERTICAL -> {
                val vPadding = ((height - child.measuredHeight) / 2).coerceAtLeast(0)
                if (!reverseLayout) {
                    if (lp == 0) recyclerView.updatePaddingRelative(top = vPadding)
                    if (lp == itemCount - 1) recyclerView.updatePaddingRelative(bottom = vPadding)
                } else {
                    if (lp == 0) recyclerView.updatePaddingRelative(bottom = vPadding)
                    if (lp == itemCount - 1) recyclerView.updatePaddingRelative(top = vPadding)
                }
            }
        }
    }

    override fun onAttachedToWindow(view: RecyclerView) {
        recyclerView = view
        super.onAttachedToWindow(view)
    }

    //This func for the when clicked item focus it and smooth scroll it
    override fun smoothScrollToPosition(
        recyclerView: RecyclerView,
        state: RecyclerView.State?,
        position: Int
    ) {
        val centerSmoothScroller = CenterSmoothScroller(
            recyclerView.context,
            recyclerView
        )
        centerSmoothScroller.targetPosition = position
        Log.e("Position:", position.toString())
        startSmoothScroll(centerSmoothScroller)
    }

    private class CenterSmoothScroller(context: Context, var view: View) :
        LinearSmoothScroller(context) {
        override fun calculateDtToFit(
            viewStart: Int,
            viewEnd: Int,
            boxStart: Int,
            boxEnd: Int,
            snapPreference: Int
        ): Int {
            Log.e("padding s e",view.paddingStart.toString()+","+view.paddingEnd.toString())
            Log.e("boxStart:", boxStart.toString())
            Log.e("boxEnd:", (boxEnd - view.paddingStart).toString())
            Log.e("viewStart:", viewStart.toString())
            Log.e("viewEnd:", viewEnd.toString())
            Log.e(
                "calculated:",
                ((boxStart + (boxEnd - boxStart - view.paddingStart) / 2) - (viewStart + (viewEnd - viewStart) / 2)).toString()
            )
            return ((boxStart + (boxEnd - boxStart - view.paddingStart) / 2) - (viewStart + (viewEnd - viewStart - view.paddingEnd) / 2))
        }
    }


    //This funcs for the when scrolling rc, set size items
    override fun onLayoutCompleted(state: RecyclerView.State?) {
        super.onLayoutCompleted(state)
        scaleChildren()
    }

    override fun scrollVerticallyBy(
        dy: Int,
        recycler: RecyclerView.Recycler?,
        state: RecyclerView.State?
    ): Int {
        val orientation = orientation
        return if (orientation == VERTICAL) {
            scaleChildren()
            super.scrollVerticallyBy(dy, recycler, state)
        } else {
            0
        }
    }

    override fun scrollHorizontallyBy(
        dx: Int,
        recycler: RecyclerView.Recycler?,
        state: RecyclerView.State?
    ): Int {
        val orientation = orientation
        return if (orientation == HORIZONTAL) {
            scaleChildren()
            super.scrollHorizontallyBy(dx, recycler, state)
        } else {
            0
        }
    }

    private fun scaleChildren() {
        val midpoint = width / 2f
        val d1 = mShrinkDistance * midpoint
        val s1 = 1f - mShrinkAmount
        for (i in 0 until childCount) {
            val child: View? = getChildAt(i)
            if (child != null) {
                val childMidPoint = (getDecoratedRight(child) + getDecoratedLeft(child)) / 2f
                val d = d1.coerceAtMost(abs(midpoint - childMidPoint))
                val scale = 1f + (s1 - 1f) * (d - 0f) / (d1 - 0f)
                child.scaleX = scale
                child.scaleY = scale
            }
        }
    }

    //This funcs for the linear layout like the grid, show given amount of the item on the one frame
    override fun checkLayoutParams(lp: RecyclerView.LayoutParams?): Boolean {
        return super.checkLayoutParams(lp) && lp!!.width == getItemSize()
    }

    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        return setProperItemSize(super.generateDefaultLayoutParams())
    }

    override fun generateLayoutParams(lp: ViewGroup.LayoutParams?): RecyclerView.LayoutParams {
        return setProperItemSize(super.generateLayoutParams(lp))
    }

    private fun setProperItemSize(lp: RecyclerView.LayoutParams): RecyclerView.LayoutParams {
        val itemSize = getItemSize()
        if (orientation == HORIZONTAL) {
            lp.width = itemSize
        } else {
            lp.height = itemSize
        }
        return lp
    }


    private fun getItemSize(): Int {
        val pageSize = if (orientation == HORIZONTAL) width else height
        return Math.round(pageSize.toFloat() / itemsPerPage)
    }


}