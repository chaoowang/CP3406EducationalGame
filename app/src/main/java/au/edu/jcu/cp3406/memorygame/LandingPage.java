package au.edu.jcu.cp3406.memorygame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LandingPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
        Button start_btn = findViewById(R.id.start);
        start_btn.setOnClickListener(v->start(start_btn));
    }

    public void start(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivityForResult(intent, MainActivity.ACTIVITY_REQUEST);
    }
}