package co.uk.bawmpt.unimyportalapp.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import co.uk.bawmpt.unimyportalapp.R;

public class MainActivity extends AppCompatActivity {


    private Button getStartedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getStartedButton = findViewById(R.id.startButton);

        getStartedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //From here we going to login activity
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });
    }
}