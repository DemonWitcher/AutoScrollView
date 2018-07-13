package com.witcher.autoscrollview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    AutoScrollView autoScrollView;
    AutoScrollAdapter<Data> adapter;
    List<Data> dataList = new ArrayList<>();
    private boolean flag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        autoScrollView = findViewById(R.id.autoscrollview);
        initTestData();
        initTest();
        setData();
    }

    private void setData() {
        adapter = new AutoScrollAdapter<Data>() {
            @Override
            public int getLayoutId() {
                return R.layout.item_haha;
            }

            @Override
            public int getCount() {
                return dataList.size();
            }

            @Override
            public Data getItem(int position) {
                return dataList.get(position);
            }

            @Override
            public void bindView(final int position, View view) {
                TextView tv1 = view.findViewById(R.id.tv1);
                TextView tv2 = view.findViewById(R.id.tv2);
                tv1.setText(getItem(position).s1);
                tv2.setText(getItem(position).s2);
                if(flag){
                    flag = false;
                    view.setBackgroundResource(R.color.colorPrimary);
                }else{
                    view.setBackgroundResource(R.color.colorAccent);
                    flag = true;
                }
            }
        };
        autoScrollView.setAdapter(adapter);

    }

    private void initTestData() {
        for (int i = 0; i < 10; ++i) {//0 1 2 3
            dataList.add(new Data(i+"买了套餐","1分钟后"));
        }
    }
    private void initTest() {
        findViewById(R.id.bt1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autoScrollView.test1();
//                autoScrollView2.test1();
//                autoScrollView3.test1();
            }
        });
        findViewById(R.id.bt2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        findViewById(R.id.bt3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int size = dataList.size();
                for (int i = size; i < size+10; ++i) {//0 1 2 3
                    dataList.add(new Data(i+"买了套餐","1分钟后"));
                }
            }
        });
    }
}
