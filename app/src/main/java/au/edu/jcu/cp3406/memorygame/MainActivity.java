package au.edu.jcu.cp3406.memorygame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView tv_level, tv_number, tv_speed;
    EditText et_number;
    Button confirm;

    Random rand;

    String generatedNumber;
    String speed_string;
    int speed = 1000;
    int currentlevel = 1;

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

        confirm = findViewById(R.id.b_confirm);


        // Display the current level
        tv_level.setText("Level : " + currentlevel);

        rand = new Random();

        //Display the speed
        tv_speed.setText("Speed: " + speed_string);

        //Hide the Input and the Button and show the number
        et_number.setVisibility(View.GONE);
        confirm.setVisibility(View.GONE);
        tv_number.setVisibility(View.VISIBLE);

        //Display random numbers according to levels
        generatedNumber = generatenumber(currentlevel);
        tv_number.setText(generatedNumber);

        //Display the elements after a second and Hide the number
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                et_number.setVisibility(View.VISIBLE);
                confirm.setVisibility(View.VISIBLE);
                tv_number.setVisibility(View.GONE);
            }

        }, speed);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (generatedNumber.equals(et_number.getText().toString())) {

                    et_number.setVisibility(View.GONE);
                    confirm.setVisibility(View.GONE);
                    tv_number.setVisibility(View.VISIBLE);

                    currentlevel++;

                    // Display the current level
                    tv_level.setText("Level : " + currentlevel);

                    //Display random numbers according to levels
                    generatedNumber = generatenumber(currentlevel);
                    tv_number.setText(generatedNumber);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            et_number.setVisibility(View.VISIBLE);
                            confirm.setVisibility(View.VISIBLE);
                            tv_number.setVisibility(View.GONE);
                        }
                    }, speed);
                } else {
                    tv_level.setText("Game Over!! The Number was " + generatedNumber);
                    confirm.setEnabled(false);
                }
            }
        });
    }

    public String generatenumber(int digits) {
        String output = "";
        for (int i = 0; i < digits; i++) {
            int randomDigit = rand.nextInt(10);
            output = output + "" + randomDigit;
        }
        return output;
    }

    public void checkSpeed(String speed_string) {
        switch (speed_string) {
            case "slow":
                speed = 2000;
                break;
            case "fast":
                speed = 700;
                break;
            case "default":
                speed = 1000;
                break;
        }
    }
}