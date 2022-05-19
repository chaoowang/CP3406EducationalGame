package au.edu.jcu.cp3406.memorygame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;

public class HighScoreScreen extends AppCompatActivity {

    int currentlevel = 1;
    int currentscore = 0;
    TextView tv_level, tv_score, tv_speed, tv_highestscore;
    String speed = "default";
    Button restart_btn;
    SharedPreferences prefs;
    String[] highscore;
    String highscore_string;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score_screen);

        prefs = this.getSharedPreferences("preference", Context.MODE_PRIVATE);
        getHighscore();

        Intent intent = getIntent();
        currentlevel = intent.getIntExtra("level", 0);
        currentscore = intent.getIntExtra("score", 0);
        speed = intent.getStringExtra("speed");

        tv_level = findViewById(R.id.tv_level);
        tv_score = findViewById(R.id.tv_score);
        tv_speed = findViewById(R.id.speed_test);
        tv_highestscore = findViewById(R.id.tv_highestscore);
        tv_highestscore.setVisibility(View.GONE);

        tv_level.setText("Level: " + currentlevel);
        tv_score.setText("Your Score: " + currentscore);
        tv_speed.setText("Speed: " + speed);
        tv_highestscore.setText("Congrautions! You are in the highscore!");

        checkHighScore();
        saveHighscore();

        restart_btn = findViewById(R.id.restart_btn);
        restart_btn.setOnClickListener(v -> restart());
    }

    public void restart() {
        Intent intent = new Intent(HighScoreScreen.this, LandingPage.class);
        startActivity(intent);
        HighScoreScreen.this.finish();
    }

    public void checkHighScore() {
        Integer[] scores = new Integer[6];
        for (int i = 0; i < 5; i++) {
            scores[i] = Integer.parseInt(highscore[i]);
        }
        Arrays.sort(scores, Collections.reverseOrder());
        if (currentscore >= scores[4]) {
            scores[4] = currentscore;
            tv_highestscore.setVisibility(View.VISIBLE);
            for (int i = 0; i < 5; i++) {
                highscore[i] = scores[i].toString();
            }
        }
    }

    public void saveHighscore() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            sb.append(highscore[i]).append(",");
        }

        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("highscore", sb.toString());
        editor.apply();
    }

    public void getHighscore() {
        highscore_string = prefs.getString("highscore", "");
        highscore = highscore_string.split(",");
    }
}