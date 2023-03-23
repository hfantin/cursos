package com.github.hfantin.accordion.component

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager

class AccordionView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    ConstraintLayout(context, attrs, defStyleAttr) {

    private val titleViewHolderArray = mutableListOf<ViewHolder>()

    private var contentViewHolder: ViewHolder? = null

    var animationDuration = 200L

    var selectedPosition = 0
        private set

    var adapter: AccordionAdapter? = null
        set(value) {
            field = value
            render()
        }

    /**
     * Update the selected position
     * The content of the selected position will be expanded
     * All onBindView*() methods in the adapter will be called
     */
    fun updatePosition(selectedPosition: Int) {
        adapter?.let { a ->
            if (selectedPosition < a.getItemCount() && selectedPosition >= 0 && this.selectedPosition != selectedPosition) {
                this.selectedPosition = selectedPosition
                applyConstraint()
                onBindAllViewHolders()
            }
        }
    }

    private fun render() {
        createTitleViews()
        createContent()
        addAllViews()
        applyConstraint()
    }

    private fun createTitleViews() {
        adapter?.let { a ->
            // loop and make bunch of Rows: store in array
            for (index in 0 until a.getItemCount()) {
                var viewHolder = titleViewHolderArray.getOrNull(index)
                if (viewHolder == null) {
                    viewHolder = a.onCreateViewHolderForTitle(this)
                    val row = viewHolder.itemView.apply {
                        id = generateViewId()
                        tag = id.toString()
                    }
                    row.setOnClickListener2 {
                        contentViewHolder?.let {
                            it.itemView.visibility = INVISIBLE
                            updatePosition(index)
                            it.itemView.visibility = VISIBLE
                        }
                    }
                    a.onBindViewForTitle(viewHolder, index, arrowDirection(index))
                    titleViewHolderArray.add(viewHolder)
                }
            }
        }
    }

    private fun onBindAllViewHolders() {
        adapter?.let {
            contentViewHolder?.let { _contentViewHolder ->
                titleViewHolderArray.forEachIndexed { innerIndex, innerViewHolder ->
                    it.onBindViewForTitle(innerViewHolder, innerIndex, arrowDirection(innerIndex))
                }
                it.onBindViewForContent(_contentViewHolder, selectedPosition)
            }
        }
    }

    private fun createContent() {
        adapter?.let { a ->
            if (contentViewHolder == null) {
                contentViewHolder = a.onCreateViewHolderForContent(this)
                contentViewHolder?.itemView?.let { i ->
                    i.id = generateViewId()
                    i.tag = i.id.toString()

                }
                contentViewHolder?.let { a.onBindViewForContent(it, selectedPosition) }
            }
        }
    }

    private fun addAllViews() {
        adapter?.let { a ->
            titleViewHolderArray.forEach {
                addView(it.itemView)
            }
            contentViewHolder?.apply { addView(itemView) }
        }
    }


    private fun arrowDirection(index: Int): AccordionAdapter.ArrowDirection {
        return if (selectedPosition == index) {
            AccordionAdapter.ArrowDirection.NONE
        } else if (index < selectedPosition) {
            AccordionAdapter.ArrowDirection.DOWN
        } else {
            AccordionAdapter.ArrowDirection.UP
        }
    }


    private fun applyConstraint() {
        val set = ConstraintSet()
        set.clone(this)
        for (index in 0..selectedPosition) {
            val row = titleViewHolderArray[index].itemView
            set.clear(row.id, ConstraintSet.TOP)
            set.clear(row.id, ConstraintSet.BOTTOM)
            if (index == 0) {
                set.connect(row.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP)
            } else {
                val previousRow = titleViewHolderArray[index - 1].itemView
                set.connect(row.id, ConstraintSet.TOP, previousRow.id, ConstraintSet.BOTTOM)
            }
            set.connect(row.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START)
            set.connect(row.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END)
        }

        for (reversedIndex in titleViewHolderArray.size - 1 downTo selectedPosition + 1) {
            val row = titleViewHolderArray[reversedIndex].itemView
            set.clear(row.id, ConstraintSet.TOP)
            set.clear(row.id, ConstraintSet.BOTTOM)
            if (reversedIndex == titleViewHolderArray.size - 1) {
                set.connect(row.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM)
            } else {
                val previousRow = titleViewHolderArray[reversedIndex + 1].itemView
                set.connect(row.id, ConstraintSet.BOTTOM, previousRow.id, ConstraintSet.TOP)
            }
            set.connect(row.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START)
            set.connect(row.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END)
        }
        TransitionManager.beginDelayedTransition(this, AutoTransition().apply { duration = animationDuration })
        set.applyTo(this)

        val set2 = ConstraintSet()
        set2.clone(this)
        contentViewHolder?.let {
            val content = it.itemView
            val positionAboveContent = selectedPosition
            val positionBelowContent = selectedPosition + 1

            val rowIdAboveContent = titleViewHolderArray[positionAboveContent].itemView.id
            val rowIdBelowContent = if (selectedPosition == titleViewHolderArray.size - 1) {
                ConstraintSet.PARENT_ID
            } else {
                titleViewHolderArray[positionBelowContent].itemView.id
            }
            set.clear(content.id, ConstraintSet.TOP)
            set.clear(content.id, ConstraintSet.BOTTOM)
            set2.connect(content.id, ConstraintSet.TOP, rowIdAboveContent, ConstraintSet.BOTTOM)
            set2.connect(
                content.id, ConstraintSet.BOTTOM, rowIdBelowContent, if (selectedPosition == titleViewHolderArray.size - 1) {
                    ConstraintSet.BOTTOM // align to parent's bottom if it is the last one
                } else {
                    ConstraintSet.TOP
                }
            )
            set.connect(content.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START)
            set.connect(content.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END)
        }
        TransitionManager.beginDelayedTransition(this, AutoTransition().apply { duration = animationDuration })
        set2.applyTo(this)
    }

    private fun View.setOnClickListener2(callback: (View) -> Unit) {
        setOnTouchListener { view, event ->
            if (event.actionMasked == MotionEvent.ACTION_UP) {
                callback.invoke(view)
                callOnClick()
            }
            true
        }
    }

    open class ViewHolder(val itemView: View)
}