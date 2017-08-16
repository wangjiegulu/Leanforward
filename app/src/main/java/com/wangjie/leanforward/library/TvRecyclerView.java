package com.wangjie.leanforward.library;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 15/08/2017.
 */
public class TvRecyclerView extends RecyclerView {
    private static final String TAG = TvRecyclerView.class.getSimpleName();

    public TvRecyclerView(Context context) {
        super(context);
        init();
    }

    public TvRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TvRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        getViewTreeObserver().addOnGlobalFocusChangeListener(new ViewTreeObserver.OnGlobalFocusChangeListener() {
            @Override
            public void onGlobalFocusChanged(View oldFocus, View newFocus) {
                LayoutManager layoutManager;
                if (null == newFocus || null == (layoutManager = getLayoutManager()) || !(layoutManager instanceof LinearLayoutManager)) {
                    return;
                }

                View itemView = layoutManager.findContainingItemView(newFocus);
                if (null == itemView) {
                    return;
                }

                int position = layoutManager.getPosition(itemView);
                if (NO_POSITION == position) {
                    return;
                }

                int[] rvLocation = new int[2];
                getLocationInWindow(rvLocation);

                int[] itemLocation = new int[2];
                itemView.getLocationInWindow(itemLocation);

                if (((LinearLayoutManager) layoutManager).getOrientation() == LinearLayoutManager.HORIZONTAL) {
                    int rvWidth = getWidth();
                    if (rvWidth <= 0) {
                        return;
                    }
                    int cur = itemLocation[0];
                    int target = rvLocation[0] + rvWidth / 2 - itemView.getWidth() / 2;
                    if (cur != target) {
                        smoothScrollBy(cur - target, 0);
                    }
                } else {
                    int rvHeight = getHeight();
                    if (rvHeight <= 0) {
                        return;
                    }
                    int cur = itemLocation[1];
                    int target = rvLocation[1] + rvHeight / 2 - itemView.getHeight() / 2;
                    if (cur != target) {
                        smoothScrollBy(0, cur - target);
                    }
                }

            }
        });
    }

}
