package com.chenyao.annotationutils;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chenyao.annotationutil.AdapterInitDataUtils;
import com.chenyao.annotationutil.baseadapter.BaseRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;

    BaseRecyclerAdapter<Bean> adapter;
    List<Bean> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        adapter = new BaseRecyclerAdapter<Bean>(this, list, R.layout.item_layout) {
            @Override
            public void convert(BaseViewHolder viewHolder, Bean item, int position) {
                AdapterInitDataUtils.init(MainActivity.this, item, viewHolder);
            }
        };
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        list.add(new Bean("aa", "http://img4.imgtn.bdimg.com/it/u=1978205210,2226637880&fm=21&gp=0.jpg", "喜欢电脑", Color.RED));
        list.add(new Bean("cc", "http://img0.imgtn.bdimg.com/it/u=2894834465,1689369125&fm=21&gp=0.jpg", "喜欢手机", Color.BLUE));
        list.add(new Bean("vv", "http://img3.imgtn.bdimg.com/it/u=512488808,1947245461&fm=21&gp=0.jpg", "喜欢游戏", Color.YELLOW));
    }
}
