package au.edu.jcu.cp3406.memorygame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Setting extends AppCompatActivity {

    public static final int SETTING_REQUEST = 37527814;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Button slow_btn = findViewById(R.id.slow_btn);
        slow_btn.setOnClickListener(v -> setSpeed(slow_btn,"slow"));
        Button normal_btn = findViewById(R.id.default_btn);
        normal_btn.setOnClickListener(v->setSpeed(normal_btn,"default"));
        Button fast_btn = findViewById(R.id.fast_btn);
        fast_btn.setOnClickListener(v->setSpeed(fast_btn, "fast"));

    }

    public void setSpeed(View view, String speed){
        Intent data = new Intent();
        data.putExtra("speed",speed);
        setResult(RESULT_OK, data);
        finish();
    }
}