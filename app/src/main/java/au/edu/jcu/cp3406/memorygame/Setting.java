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
        slow_btn.setOnClickListener(v -> setspeed(slow_btn,2));
        Button normal_btn = findViewById(R.id.normal_btn);
        normal_btn.setOnClickListener(v->setspeed(normal_btn,1));
        Button fast_btn = findViewById(R.id.fast_btn);
        fast_btn.setOnClickListener(v->setspeed(fast_btn, 0.5F));

    }

    public void setspeed(View view, float speed){
        Intent data = new Intent();
        data.putExtra("speed",speed);
        setResult(RESULT_OK, data);
        finish();
    }
}