package co.uk.bawmpt.unimyportalapp.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import co.uk.bawmpt.unimyportalapp.R;

public class LibraryActivity extends AppCompatActivity {

    //Declared widgets used in Activity Class:
    private ImageView searchButton;
    private ImageView myReservation;

    //Firebase Auth declaration to inflate the menu in Activity:
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        //Widgets Initialization:
        searchButton = findViewById(R.id.librarySearchCatalogButton);
        myReservation = findViewById(R.id.libraryMyReservationButton);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        //Set OnClickListener on the buttons:
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Define the Intent method of the buttons:
                Intent i = new Intent(LibraryActivity.this, LibrarySearchCatalogActivity.class);
                startActivity(i);
            }
        });
        myReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Define the Intent method of the buttons:
                Intent i = new Intent(LibraryActivity.this, LibraryMyReservationActivity.class);
                startActivity(i);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_general, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_singout:
                //This option menu will Sign Out user:
                if (user != null && firebaseAuth != null) {
                    firebaseAuth.signOut();
                    startActivity(new Intent(LibraryActivity.this,
                            MainActivity.class));
                    finish();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}