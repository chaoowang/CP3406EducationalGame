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

public class HighScoreScreen extends AppCompatActivity {

    int currentLevel = 1;
    int currentScore = 0;
    TextView tv_level, tv_score, tv_speed, tv_highestScore, tv_highScore1, tv_highScore2, tv_highScore3, tv_highScore4, tv_highScore5;
    String speed = "default";
    Button restart_btn;
    SharedPreferences prefs;
    String[] highScore;
    String highScoreString = "0,0,0,0,0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score_screen);

        prefs = this.getSharedPreferences("preference", Context.MODE_PRIVATE);
        getHighScore();

        Intent intent = getIntent();
        currentLevel = intent.getIntExtra("level", 0);
        currentScore = intent.getIntExtra("score", 0);
        speed = intent.getStringExtra("speed");

        tv_level = findViewById(R.id.tv_level);
        tv_score = findViewById(R.id.tv_score);
        tv_speed = findViewById(R.id.speed_test);
        tv_highestScore = findViewById(R.id.tv_highestscore);
        tv_highestScore.setVisibility(View.GONE);
        tv_highScore1 = findViewById(R.id.highscore1);
        tv_highScore2 = findViewById(R.id.highscore2);
        tv_highScore3 = findViewById(R.id.highscore3);
        tv_highScore4 = findViewById(R.id.highscore4);
        tv_highScore5 =findViewById(R.id.highscore5);

        String level_msg = "Level: "+ currentLevel;
        String score_msg = "Your Score: "+ currentScore;
        String speed_msg = "Speed: "+speed;
        tv_level.setText(level_msg);
        tv_score.setText(score_msg);
        tv_speed.setText(speed_msg);
        String congrats_msg = "Congratulations! You are in the high score!";
        tv_highestScore.setText(congrats_msg);

        checkHighScore();
        saveHighScore();

        tv_highScore1.setText(highScore[4]);
        tv_highScore2.setText(highScore[3]);
        tv_highScore3.setText(highScore[2]);
        tv_highScore4.setText(highScore[1]);
        tv_highScore5.setText(highScore[0]);

        restart_btn = findViewById(R.id.restart_btn);
        restart_btn.setOnClickListener(v -> restart());
    }

    public void restart() {
        Intent intent = new Intent(HighScoreScreen.this, LandingPage.class);
        intent.putExtra("speed", speed);
        startActivity(intent);
        HighScoreScreen.this.finish();
    }

    public void checkHighScore() {
        Integer[] scores = new Integer[5];
        for (int i = 0; i < 5; i++) {
            scores[i] = Integer.parseInt(highScore[i]);
        }
        Arrays.sort(scores);
        if (currentScore >= scores[0]) {
            scores[0] = currentScore;
            tv_highestScore.setVisibility(View.VISIBLE);
            Arrays.sort(scores);
            for (int i = 0; i < 5; i++) {
                highScore[i] = scores[i].toString();
            }
        }
    }

    public void saveHighScore() {
        String sb = highScore[0] + "," + highScore[1] + "," + highScore[2] + "," + highScore[3] + "," + highScore[4];
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("highScore", sb);
        editor.apply();
    }

    public void getHighScore() {
        highScoreString = prefs.getString("highScore", "0,0,0,0,0");
        highScore = highScoreString.split(",");

    }
}