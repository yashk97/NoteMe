package com.example.yash.noteme;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        //get the intent that started this activity and extract the string
        Intent intent = getIntent();
        String [] str = intent.getStringArrayExtra(MainActivity.EXTRA_MESSAGE);
        Calendar today = Calendar.getInstance();
        MyData md = new MyData(str[0], str[1], today.getTime().toString());
        NoteDbHelper db = new NoteDbHelper(this);
        db.insert_data(md);
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(str[0]+"\n"+str[1]);
    }

}
