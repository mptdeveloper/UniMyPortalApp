package co.uk.bawmpt.unimyportalapp.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import co.uk.bawmpt.unimyportalapp.R;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton dashTimeTable;
    private ImageButton dashFloorPlan;
    private ImageButton dashLibrary;
    private ImageButton dashBookSale;
    private ImageButton dashForum;
    private ImageButton dashActivities;
    private ImageButton dashMyMessages;

    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        dashTimeTable = findViewById(R.id.dashButtonTimeTable);
        dashTimeTable.setOnClickListener(this);
        dashFloorPlan = findViewById(R.id.dashButtonFloorPlan);
        dashFloorPlan.setOnClickListener(this);
        dashLibrary = findViewById(R.id.dashButtonLibrary);
        dashLibrary.setOnClickListener(this);
        dashBookSale = findViewById(R.id.dashButtonBookSale);
        dashBookSale.setOnClickListener(this);
        dashForum = findViewById(R.id.dashButtonForum);
        dashForum.setOnClickListener(this);
        dashActivities = findViewById(R.id.dashButtonActivities);
        dashActivities.setOnClickListener(this);
        dashMyMessages = findViewById(R.id.dashButtonMyMessages);
        dashMyMessages.setOnClickListener(this);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
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
                    startActivity(new Intent(DashboardActivity.this, MainActivity.class));
                    finish();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dashButtonTimeTable:
                //Moving user to TimeTableActivities:
                startActivity(new Intent(DashboardActivity.this, TimeTableActivity.class));
                break;
            case R.id.dashButtonFloorPlan:
                //This button will take user to Floor Plan of Campuses:
                startActivity(new Intent(DashboardActivity.this, FloorPlanActivity.class));
                break;
            case R.id.dashButtonLibrary:
                //This button will take user to Library Activity:
                startActivity(new Intent(DashboardActivity.this, LibraryActivity.class));
                break;
            case R.id.dashButtonBookSale:
                //This button will take user to Book Sale Activity which represents books posted by fellow Students:
                startActivity(new Intent(DashboardActivity.this, BookSaleListActivity.class));
                break;
            case R.id.dashButtonForum:
                startActivity(new Intent(DashboardActivity.this, ForumActivity.class));
                break;
            case R.id.dashButtonActivities:
                //This button will take user to Activities Activity/Screen where on campus activities will by posted
                //By Welfare Team:
                startActivity(new Intent(DashboardActivity.this, ActivitiesActivity.class));
                break;
            case R.id.dashButtonMyMessages:
                //This button will take user to messages inbox where the users can exchange messages e.g about book for sale:
                startActivity(new Intent(DashboardActivity.this, MessagesActivity.class));
        }
    }
}