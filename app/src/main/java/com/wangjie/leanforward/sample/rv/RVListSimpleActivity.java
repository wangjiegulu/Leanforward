package com.wangjie.leanforward.sample.rv;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.wangjie.leanforward.R;
import com.wangjie.leanforward.library.XGridLayoutManager;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 15/08/2017.
 */
public class RVListSimpleActivity extends AppCompatActivity {
    private RecyclerView feedRv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_grid_simple);
        feedRv = (RecyclerView) findViewById(R.id.activity_rv_grid_simple_rv);

        XGridLayoutManager layoutManager = new XGridLayoutManager(this, 1);
        layoutManager.setOrientation(GridLayoutManager.VERTICAL);

        feedRv.setLayoutManager(layoutManager);
        SimpleFeedAdapter adapter = new SimpleFeedAdapter(300);
        feedRv.setAdapter(adapter);

    }


    public static class SimpleFeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private int count;

        public SimpleFeedAdapter(int count) {
            this.count = count;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new SimpleDefaultViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_sample, parent, false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof SimpleDefaultViewHolder) {
                ((SimpleDefaultViewHolder) holder).onBindViewHolder(position);
            }
        }

        @Override
        public int getItemCount() {
            return count;
        }
    }

    public static class SimpleDefaultViewHolder extends RecyclerView.ViewHolder {
        private Button button;
        private TextView tv;

        public SimpleDefaultViewHolder(View itemView) {
            super(itemView);
            button = (Button) itemView.findViewById(R.id.item_rv_simple_btn);
            tv = (TextView) itemView.findViewById(R.id.item_rv_sample_tv);
            button.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    button.setBackgroundColor(hasFocus ? Color.BLUE : Color.GREEN);
                }
            });
            tv.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    tv.setBackgroundColor(hasFocus ? Color.BLUE : Color.GREEN);
                }
            });
        }

        public void onBindViewHolder(int position) {
            button.setText("click " + position);
        }
    }

}
