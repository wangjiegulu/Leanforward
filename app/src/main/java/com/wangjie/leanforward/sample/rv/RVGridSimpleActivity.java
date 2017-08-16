package com.wangjie.leanforward.sample.rv;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.wangjie.leanforward.R;
import com.wangjie.leanforward.library.XGridLayoutManager;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 15/08/2017.
 */
public class RVGridSimpleActivity extends AppCompatActivity {
    private RecyclerView feedRv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_grid_simple);
        feedRv = (RecyclerView) findViewById(R.id.activity_rv_grid_simple_rv);

        XGridLayoutManager layoutManager = new XGridLayoutManager(this, 5);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if(0 == position){
                    return 5;
                }
                if(0 == position % 13){
                    return 3;
                }
                return 1;
            }
        });

        feedRv.setLayoutManager(layoutManager);
        FeedAdapter adapter = new FeedAdapter();
        feedRv.setAdapter(adapter);

    }


    private static class FeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new DefaultViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_sample, parent, false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if(holder instanceof DefaultViewHolder){
                ((DefaultViewHolder) holder).onBindViewHolder(position);
            }
        }

        @Override
        public int getItemCount() {
            return 300;
        }
    }

    private static class DefaultViewHolder extends RecyclerView.ViewHolder{
        private Button button;
        public DefaultViewHolder(View itemView) {
            super(itemView);
            button = (Button) itemView.findViewById(R.id.item_rv_simple_btn);
        }

        public void onBindViewHolder(int position) {
            button.setText("click " + position);
        }
    }

}
