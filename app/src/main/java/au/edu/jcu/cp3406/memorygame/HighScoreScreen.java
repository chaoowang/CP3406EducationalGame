package au.edu.jcu.cp3406.memorygame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class HighScoreScreen extends AppCompatActivity {

    public static final int HIGHSCORE_REQUEST = 435678;

    int currentlevel = 1;
    int currentscore = 0;
    TextView tv_level, tv_score, tv_speed;
    String speed = "default";

    Button restart_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score_screen);

        Intent intent = this.getIntent();
        currentlevel = intent.getIntExtra("level", 0);
        currentscore = intent.getIntExtra("score", 0);
        speed = intent.getStringExtra("speed");

        tv_level = findViewById(R.id.tv_level);
        tv_score = findViewById(R.id.tv_score);
        tv_speed = findViewById(R.id.speed_test);

        tv_level.setText("Level: " + currentlevel);
        tv_score.setText("Your Score: " + currentscore);
        tv_speed.setText("Speed: " + speed);

        restart_btn = findViewById(R.id.restart_btn);
        restart_btn.setOnClickListener(v -> restart());
    }

    public void restart() {
        finish();
    }
}