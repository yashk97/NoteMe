package com.example.yash.noteme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddNote extends AppCompatActivity {

    public static String EXTRA_MESSAGE = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
    }

    public void sendMessage(View view){
        Intent intent = new Intent(AddNote.this,DisplayMessageActivity.class);
        EditText TitleText = (EditText) findViewById(R.id.editText);
        EditText NoteText = (EditText) findViewById(R.id.editText2);
        String [] str = new String[2];
        str[0] = TitleText.getText().toString();
        str[1] = NoteText.getText().toString();
//        intent.putExtra(EXTRA_MESSAGE, title+ ":" +mynote);
        intent.putExtra(EXTRA_MESSAGE, str);
        startActivity(intent);
    }
}
