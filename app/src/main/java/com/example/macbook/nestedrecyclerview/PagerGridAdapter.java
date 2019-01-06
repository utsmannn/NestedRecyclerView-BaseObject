/*
 * Created by Muhammad Utsman on 6/1/2019
 * Copyright (c) 2019 . All rights reserved.
 * Last modified 1/6/19 11:09 PM
 */

package com.example.macbook.nestedrecyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.macbook.nestedrecyclerview.adapter.RecyclerHorizontalGridAdapter;
import com.example.macbook.nestedrecyclerview.model.ModelHorizontalGrid;

import java.util.ArrayList;

/* Ini class adapter buat viewpager nya */
public class PagerGridAdapter extends PagerAdapter {

    private Context context;

    /* Buat list item page 1 dan 2 di viewpager */
    private ArrayList<ModelHorizontalGrid> list1 = new ArrayList<>();
    private ArrayList<ModelHorizontalGrid> list2 = new ArrayList<>();

    public PagerGridAdapter(Context context, ArrayList<ModelHorizontalGrid> list) {
        this.context = context;

        /* Nambahin berdasarkan jumlah item
         * page pertama urutan 0 sampe 6 karena colom nya ada 3
         * page kedua urutan 6 sampe jumlah total item */
        list1.addAll(list.subList(0, 6));
        list2.addAll(list.subList(6, list.size()));
    }

    /* daftarin adapter buat recyclerview di page satu dan dua */
    private RecyclerHorizontalGridAdapter[] listAdapter = new RecyclerHorizontalGridAdapter[] {
            new RecyclerHorizontalGridAdapter(list1), // list1 buat page 1
            new RecyclerHorizontalGridAdapter(list2) // list 2 buat page 2
    };

    @Override
    public int getCount() {
        return listAdapter.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        /* Ngambil layout yang isi nya recyclerview */
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_horizontal_grid, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_horizontal_grid);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 3));

        /* Seting adapter tiap page */
        recyclerView.setAdapter(listAdapter[position]);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        /* object cast ke LinearLayout karena parent di recycler_horizontal_grid.xml
         * adalah LinearLayout */
        container.removeView((LinearLayout)object);
    }
}
