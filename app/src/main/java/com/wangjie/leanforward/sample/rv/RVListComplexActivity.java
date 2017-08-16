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

import com.wangjie.leanforward.R;
import com.wangjie.leanforward.library.XGridLayoutManager;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 15/08/2017.
 */
public class RVListComplexActivity extends AppCompatActivity {
    private RecyclerView feedRv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_grid_simple);
        feedRv = (RecyclerView) findViewById(R.id.activity_rv_grid_simple_rv);

        XGridLayoutManager layoutManager = new XGridLayoutManager(this, 1, "OUTER");
        layoutManager.setOrientation(GridLayoutManager.VERTICAL);

        feedRv.setLayoutManager(layoutManager);
        FeedAdapter adapter = new FeedAdapter(300);
        feedRv.setAdapter(adapter);

    }


    private static class FeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private int count;

        public FeedAdapter(int count) {
            this.count = count;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new DefaultViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_list, parent, false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof DefaultViewHolder) {
                ((DefaultViewHolder) holder).onBindViewHolder(position);
            }
        }

        @Override
        public int getItemCount() {
            return count;
        }
    }

    private static class DefaultViewHolder extends RecyclerView.ViewHolder {
        private RecyclerView listRv;
        RVListSimpleActivity.SimpleFeedAdapter adapter = new RVListSimpleActivity.SimpleFeedAdapter(30);

        public DefaultViewHolder(View itemView) {
            super(itemView);
            listRv = (RecyclerView) itemView.findViewById(R.id.item_rv_list_rv);
            XGridLayoutManager layoutManager = new XGridLayoutManager(itemView.getContext(), 1);
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            listRv.setLayoutManager(layoutManager);
        }

        public void onBindViewHolder(int position) {
            listRv.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }


}
