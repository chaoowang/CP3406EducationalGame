package au.edu.jcu.cp3406.memorygame;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LandingPage extends AppCompatActivity {

    String speed_string = "default";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
        Button start_btn = findViewById(R.id.start);
        start_btn.setOnClickListener(v -> start(start_btn));
        Button setting_btn = findViewById(R.id.setting);
        setting_btn.setOnClickListener(v -> setting(setting_btn));
        Intent intent = getIntent();
        speed_string = intent.getStringExtra("speed");
    }

    public void start(View view) {
        Intent intent = new Intent(LandingPage.this, MainActivity.class);
        intent.putExtra("speed_string", speed_string);
        startActivity(intent);
        LandingPage.this.finish();
    }

    public void setting(View view) {
        Intent intent = new Intent(LandingPage.this, Setting.class);
        startActivityForResult(intent, Setting.SETTING_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Setting.SETTING_REQUEST) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    speed_string = data.getStringExtra("speed");
                }
            }
        }
    }
}