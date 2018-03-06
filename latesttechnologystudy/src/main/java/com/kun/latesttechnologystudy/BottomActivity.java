package com.kun.latesttechnologystudy;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Administrator on 2018/2/8.
 */

public class BottomActivity extends AppCompatActivity {

    private BottomNavigationView bottom_navigation3;
    private Button btn_enter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom);

        initView();

    }

    private void initView() {

        bottom_navigation3 = (BottomNavigationView) findViewById(R.id.bottom_navigation3);
        BottomNavigationViewHelper.disableShiftMode(bottom_navigation3);

        btn_enter = (Button) findViewById(R.id.btn_enter);

        btn_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enter();
            }
        });

    }

    public void enter(){
        Intent intent = new Intent(this,BottomSecondActivity.class);

        startActivity(intent);
    }
}
