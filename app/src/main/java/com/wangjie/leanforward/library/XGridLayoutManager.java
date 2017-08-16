package com.wangjie.leanforward.library;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.FocusFinder;
import android.view.View;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 15/08/2017.
 */
public class XGridLayoutManager extends GridLayoutManager {
    private String TAG = XGridLayoutManager.class.getSimpleName() + hashCode();
    private String message;

    public XGridLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public XGridLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
    }

    public XGridLayoutManager(Context context, int spanCount, String message) {
        this(context, spanCount);
        this.message = message;
        TAG += "[" + message + "]";
    }

    public XGridLayoutManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
    }

    @Override
    public View onInterceptFocusSearch(View focused, int direction) {
        RecyclerView rv = getRecyclerView();
        if (null == rv) {
            Log.i(TAG, "onInterceptFocusSearch getRecyclerView is null");
            return super.onInterceptFocusSearch(focused, direction);
        }
        int orientation = getOrientation();

        View view = FocusFinder.getInstance().findNextFocus(rv, focused, direction);

        if (null != view) {
            Log.i(TAG, "onInterceptFocusSearch findNextFocus and return: " + view);
            return view;
        }

        View focusedChild = findContainingItemView(focused);
        if (null == focusedChild) {
            // TODO: 15/08/2017 wangjie
            return null;
        }

        boolean isFirstPosition = isFirstPosition(focusedChild);
        boolean isLastPosition = isLastPosition(focusedChild);
        if (VERTICAL == orientation) { // 竖直
            if (View.FOCUS_UP == direction || View.FOCUS_DOWN == direction) {
                if (isFirstPosition || isLastPosition) {
                    // TODO: 15/08/2017 wangjie
                    return focused;
                }
            } else {
//                return super.onInterceptFocusSearch(focused, direction);
                View v = rv.getParent().focusSearch(focused, direction);
                Log.i(TAG, "onInterceptFocusSearch[VERTICAL] focusSearch from parent v: " + v);
                return v;
            }
        } else { // 水平
            if (View.FOCUS_LEFT == direction || View.FOCUS_RIGHT == direction) {
                if (isFirstPosition || isLastPosition) {
                    // TODO: 15/08/2017 wangjie
                    return focused;
                }
            } else {
                View v = rv.getParent().focusSearch(focused, direction);
                Log.i(TAG, "onInterceptFocusSearch[HORIZONTAL] focusSearch from parent v: " + v);
                return v;
//                return super.onInterceptFocusSearch(focused, direction);
            }
        }

//        try {
//            Field recycler = rv.getClass().getDeclaredField("mRecycler");
//            recycler.setAccessible(true);
//            Field state = rv.getClass().getDeclaredField("mState");
//            state.setAccessible(true);
//            View viewByFailed = onFocusSearchFailed(focused, direction,
//                    (RecyclerView.Recycler) recycler.get(rv),
//                    (RecyclerView.State) state.get(rv)
//            );
//            Log.i(TAG, "onInterceptFocusSearch viewByFailed and return: " + viewByFailed);
//            return viewByFailed;
//        } catch (Throwable e) {
//            Log.e(TAG, "", e);
//        }
//        Log.i(TAG, "onInterceptFocusSearch return super.");
//        recyclerView.scrollToPosition(getPosition(focusedChild) + 1);

//        View v = rv.getParent().focusSearch(focused, direction);
//        Log.i(TAG, "onInterceptFocusSearch[last return] focusSearch from parent v: " + v);
//        return null == v ? focused : v;

        Log.i(TAG, "onInterceptFocusSearch[last return] INVOKE SUPER");
        return super.onInterceptFocusSearch(focused, direction);
    }

    private boolean isFirstPosition(View view) {
        return 0 == getPosition(view);
    }

    private boolean isLastPosition(View view) {
        RecyclerView rv = getRecyclerView();
        return null != rv && rv.getAdapter().getItemCount() - 1 == getPosition(view);
    }

    private RecyclerView recyclerView;

    @Nullable
    private RecyclerView getRecyclerView() {
        if (null != recyclerView) {
            return recyclerView;
        }
        int childCount = getChildCount();
        if (childCount <= 0) {
            return null;
        }

        View parent = getChildAt(0);
        while (null != parent) {
            if (parent instanceof RecyclerView) {
                recyclerView = (RecyclerView) parent;
                break;
            }
            parent = (View) parent.getParent();
        }
        return recyclerView;
    }

    @Override
    public View onFocusSearchFailed(View focused, int focusDirection, RecyclerView.Recycler recycler, RecyclerView.State state) {
        View view = super.onFocusSearchFailed(focused, focusDirection, recycler, state);
        Log.i(TAG, "onFocusSearchFailed, view: " + view + ", position: " + (null == view ? "null" : getPosition(view)));
//        return null == view ? focused : view;
        // TODO: 15/08/2017 wangjie
        return null;
//        if (null == view) {
//            return null;
//        }
//        return view;
    }


    @Override
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
        super.smoothScrollToPosition(recyclerView, state, position);
//        RecyclerView.SmoothScroller smoothScroller = new CenterSmoothScroller(recyclerView.getContext());
//        smoothScroller.setTargetPosition(position);
//        startSmoothScroll(smoothScroller);
    }

    private static class CenterSmoothScroller extends LinearSmoothScroller {

        CenterSmoothScroller(Context context) {
            super(context);
        }

        @Override
        public int calculateDtToFit(int viewStart, int viewEnd, int boxStart, int boxEnd, int snapPreference) {
            return (boxStart + (boxEnd - boxStart) / 2) - (viewStart + (viewEnd - viewStart) / 2);
        }
    }
}
