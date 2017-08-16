package com.wangjie.leanforward.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.wangjie.leanforward.R;
import com.wangjie.leanforward.sample.rv.RVGridSimpleActivity;
import com.wangjie.leanforward.sample.rv.RVListComplexActivity;
import com.wangjie.leanforward.sample.rv.RVListSimpleActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.activity_main_rv_grid_simple_btn).setOnClickListener(this);
        findViewById(R.id.activity_main_rv_list_simple_btn).setOnClickListener(this);
        findViewById(R.id.activity_main_rv_list_complex_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_main_rv_grid_simple_btn:
                startActivity(new Intent(this, RVGridSimpleActivity.class));
                break;
            case R.id.activity_main_rv_list_simple_btn:
                startActivity(new Intent(this, RVListSimpleActivity.class));
                break;
            case R.id.activity_main_rv_list_complex_btn:
                startActivity(new Intent(this, RVListComplexActivity.class));
                break;
            default:
                break;
        }
    }
}
