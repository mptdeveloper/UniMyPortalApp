package co.uk.bawmpt.unimyportalapp.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import co.uk.bawmpt.unimyportalapp.R;

/*
Main Activity is launcher class and is the first thing a user can see after clicking on the icon
 */

public class MainActivity extends AppCompatActivity {

    //Declaring button widget used in this Activity:
    private Button getStartedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialise button widget to to be seen by corresponding Java Class:
        getStartedButton = findViewById(R.id.startButton);


        //Set OnCLickListener on the button widget:
        getStartedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //From here we going to login activity
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });
    }
}