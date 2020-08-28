package com.example.intentlessons;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class SecondActivity extends AppCompatActivity {

    //public static final String RESULT = "result";
    EditText editText;
    SpeechRecognizer mSpeechRecoognizer;
    Intent mSpeechRecoognizerIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        checkPermission();

        editText = findViewById(R.id.editText);

        mSpeechRecoognizer = SpeechRecognizer.createSpeechRecognizer(this);

        mSpeechRecoognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        mSpeechRecoognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);


        mSpeechRecoognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, Locale.getDefault());
        mSpeechRecoognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle bundle) {

            }

            @Override
            public void onBeginningOfSpeech() {

            }

            @Override
            public void onRmsChanged(float v) {

            }

            @Override
            public void onBufferReceived(byte[] bytes) {

            }

            @Override
            public void onEndOfSpeech() {

            }

            @Override
            public void onError(int i) {

            }

            @Override
            public void onResults(Bundle bundle) {
                ArrayList<String> matches = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

                if(matches !=null)
                    editText.setText(matches.get(0));

            }

            @Override
            public void onPartialResults(Bundle bundle) {

            }

            @Override
            public void onEvent(int i, Bundle bundle) {

            }
        });

//       String str = getIntent().getStringExtra(Intent.EXTRA_TEXT);
//       int six = getIntent().getIntExtra(MainActivity.INTENT_KEY, -1);

    //    Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
       // Toast.makeText(this, six + "", Toast.LENGTH_SHORT).show();
//
//        int first = getIntent().getIntExtra(MainActivity.INTENT_KEY_ONE, -1);
//        int second = getIntent().getIntExtra(MainActivity.INTENT_KEY_TWO, -1);
//
//        Intent result = new Intent();
//        result.putExtra(RESULT, first + second);
//        setResult(Activity.RESULT_OK, result);
//        finish();

        editText = findViewById(R.id.editText);

        findViewById(R.id.button).setOnTouchListener(new View.OnTouchListener(){

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_UP:
                        editText.setHint("You will see the input here");
                        break;
                        case MotionEvent.ACTION_DOWN:
                            editText.setText("");
                            editText.setHint("Listening...");
                            mSpeechRecoognizer.startListening(mSpeechRecoognizerIntent);
                            break;

                }
                return false;
            }
        });

    }

    private void checkPermission(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(!(ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
            == PackageManager.PERMISSION_GRANTED)){
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                        Uri.parse("package:" + getPackageName()));
                startActivity(intent);
                finish();
            }

        }
    }
}