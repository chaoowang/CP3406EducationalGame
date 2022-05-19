package au.edu.jcu.cp3406.memorygame;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView tv_level, tv_number, tv_speed, tv_score;
    EditText et_number;
    Button confirm, endgame;

    Random rand;

    String generatedNumber;
    String speed_string;
    int speed = 1000;
    int currentlevel = 1;
    int currentscore = 0;
    int point = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        speed_string = intent.getStringExtra("speed_string");
        checkSpeed(speed_string);

        tv_level = findViewById(R.id.tv_level);
        tv_number = findViewById(R.id.tv_number);
        et_number = findViewById(R.id.et_number);
        tv_speed = findViewById(R.id.speed_test);
        tv_score = findViewById(R.id.tv_score);

        confirm = findViewById(R.id.b_confirm);
        endgame = findViewById(R.id.end_btn);

        // Display the current level and score
        String level_msg = "Level: "+currentlevel;
        tv_level.setText(level_msg);
        String score_msg = "Score: "+currentscore;
        tv_score.setText(score_msg);

        rand = new Random();

        //Display the speed
        String speed_msg = "Speed: "+speed_string;
        tv_speed.setText(speed_msg);

        //Hide the Input and the Button and show the number
        et_number.setVisibility(View.GONE);
        confirm.setVisibility(View.GONE);
        tv_number.setVisibility(View.VISIBLE);
        endgame.setVisibility(View.GONE);

        //Display random numbers according to levels
        generatedNumber = generatenumber(currentlevel);
        tv_number.setText(generatedNumber);


        //Display the elements after a second and Hide the number
        new Handler().postDelayed(() -> {
            et_number.setVisibility(View.VISIBLE);
            confirm.setVisibility(View.VISIBLE);
            tv_number.setVisibility(View.GONE);
        }, speed);

        confirm.setOnClickListener(view -> {

            if (generatedNumber.equals(et_number.getText().toString())) {

                et_number.setVisibility(View.GONE);
                confirm.setVisibility(View.GONE);
                tv_number.setVisibility(View.VISIBLE);

                currentlevel++;
                currentscore += point;

                // Display the current level
                tv_level.setText(level_msg);
                tv_score.setText(score_msg);

                //Display random numbers according to levels
                generatedNumber = generatenumber(currentlevel);
                tv_number.setText(generatedNumber);

                new Handler().postDelayed(() -> {
                    et_number.setVisibility(View.VISIBLE);
                    confirm.setVisibility(View.VISIBLE);
                    tv_number.setVisibility(View.GONE);
                }, speed);
            } else {
                String end_msg = "Game Over! The Number was"+generatedNumber;
                tv_level.setText(end_msg);
                confirm.setEnabled(false);
                endgame.setVisibility(View.VISIBLE);
                endgame.setOnClickListener(view1 -> {
                    Intent intent1 = new Intent();
                    intent1.setClass(MainActivity.this, HighScoreScreen.class);
                    intent1.putExtra("level",currentlevel);
                    intent1.putExtra("score", currentscore);
                    intent1.putExtra("speed",speed_string);
                    startActivity(intent1);
                    MainActivity.this.finish();
                });
            }
        });
    }

    public String generatenumber(int digits) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < digits; i++) {
            int randomDigit = rand.nextInt(10);
            output.append(randomDigit);
        }
        return output.toString();
    }

    public void checkSpeed(String speed_string) {
        if (speed_string != null) {
            switch (speed_string) {
                case "slow":
                    speed = 2000;
                    point = 7;
                    break;
                case "fast":
                    speed = 700;
                    point = 20;
                    break;
                case "default":
                    speed = 1000;
                    point = 10;
                    break;
            }
        } else {
            speed = 1000;
        }

    }
}