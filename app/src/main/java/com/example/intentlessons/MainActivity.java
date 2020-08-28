package com.example.intentlessons;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button button;
    Button button2;
    public static final String INTENT_KEY_ONE = "key1";
    public static final String INTENT_KEY_TWO = "key2";
    public static final int REQUEST_CODE = 666;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//yavno
        button = findViewById(R.id.button);
        button.setOnClickListener(v->{
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        });

        //neyavno

        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(v->{
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, "SOME  TEXT");
            intent.putExtra(INTENT_KEY_ONE, 6);
            intent.putExtra(INTENT_KEY_TWO, 10);
            startActivityForResult(intent, REQUEST_CODE);
        });




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK){
            Toast.makeText(this, data.getIntExtra(SecondActivity.RESULT, -1)
            + " ", Toast.LENGTH_SHORT).show();
        }
    }
}