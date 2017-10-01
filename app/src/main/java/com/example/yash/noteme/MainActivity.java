package com.example.yash.noteme;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Iterator;


public class MainActivity extends AppCompatActivity {

    public static String EXTRA_MESSAGE = null;
    private RecyclerView recyclerView;
    private CardAdapter adapter;
    private ArrayList<MyData> cardList;
    private Menu local_menu;
    private boolean isGrid;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences("prefs",MODE_PRIVATE);
        editor = sharedPreferences.edit();
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddNote.class);
                startActivity(intent);
            }
        });


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        cardList = prepareNotes();
        adapter = new CardAdapter(this, cardList);
        adapter.notifyDataSetChanged();

        if (isGrid)
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        else
            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        recyclerView.setAdapter(adapter);


        Iterator<MyData> i = cardList.iterator();
        while (i.hasNext())
            Log.e("OUTPUT", i.next().get_data());
    }

    private ArrayList<MyData> prepareNotes() {
        NoteDbHelper db = new NoteDbHelper(this);
        db.getReadableDatabase();
        return db.getAllData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        local_menu = menu;
        getMenuInflater().inflate(R.menu.layout_menu, menu);
        if (isGrid) {
            local_menu.findItem(R.id.changelistgrid).setIcon(R.drawable.view_stream_white_36px);
        } else {
            local_menu.findItem(R.id.changelistgrid).setIcon(R.drawable.dashboard_white_36px);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        isGrid = !isGrid;
        if(isGrid){
            local_menu.findItem(R.id.changelistgrid).setIcon(R.drawable.view_stream_white_36px);
        }
        else{
            local_menu.findItem(R.id.changelistgrid).setIcon(R.drawable.dashboard_white_36px);
        }
        editor.putBoolean("isGrid",isGrid);
        editor.apply();
        onResume();

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onResume() {
        super.onResume();
        isGrid = sharedPreferences.getBoolean("isGrid", true);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        if (isGrid) {
            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        } else {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
        adapter = new CardAdapter(getApplicationContext(), cardList);
        recyclerView.setAdapter(adapter);
        recyclerView.scrollToPosition(0);
    }
}


