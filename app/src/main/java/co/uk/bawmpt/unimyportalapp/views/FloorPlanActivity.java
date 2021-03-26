package co.uk.bawmpt.unimyportalapp.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.github.chrisbanes.photoview.PhotoViewAttacher;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import co.uk.bawmpt.unimyportalapp.R;

public class FloorPlanActivity extends AppCompatActivity {


    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;


    Spinner s_campus, s_building, s_floors;

    ImageView imageView;
    Button btn_map;
    ArrayList<String> ArrayList_Campus;
    ArrayList<String> arrayList_London, arrayList_Birmingham, arrayList_Manchester;
    ArrayList<String> arrayList_London_floors, arrayList_Birmingham_floors, arrayList_Manchester_floors;
    ArrayAdapter<String> arrayAdapter_campus, arrayAdapter_building, arrayAdapter_floors;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floor_plan);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        s_campus = findViewById(R.id.s_campus);
        s_building = findViewById(R.id.s_building);
        s_floors = findViewById(R.id.s_floors);
        imageView = findViewById(R.id.imageView);
        PhotoViewAttacher photoView = new PhotoViewAttacher(imageView);
        photoView.update();
        btn_map = findViewById(R.id.btn_map);

        //
        ArrayList_Campus = new ArrayList<>();
        ArrayList_Campus.add("London");
        ArrayList_Campus.add("Birmingham");
        ArrayList_Campus.add("Manchester");
        //
        arrayAdapter_campus = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, ArrayList_Campus);
        s_campus.setAdapter(arrayAdapter_campus);

        //
        arrayList_London = new ArrayList<>();
        arrayList_London.add("London Blok 1");
        arrayList_London.add("London Blok 2");

        //
        arrayList_Birmingham = new ArrayList<>();
        arrayList_Birmingham.add("Birmingham Blok 1");
        //
        arrayList_Manchester = new ArrayList<>();
        arrayList_Manchester.add("Manchester Blok 1");
        arrayList_Manchester.add("Manchester Blok 2");

        //

        arrayList_London_floors = new ArrayList<>();
        arrayList_London_floors.add("London Ground floor");
        arrayList_London_floors.add("London 1th floor");
        arrayList_London_floors.add("London 2th floor");

        arrayList_Birmingham_floors = new ArrayList<>();
        arrayList_Birmingham_floors.add("Birmingham 12 floor");
        arrayList_Birmingham_floors.add("Birmingham 5th floor");

        arrayList_Manchester_floors = new ArrayList<>();
        arrayList_Manchester_floors.add("Manchester Ground floor");
        arrayList_Manchester_floors.add("Manchester 1th floor");


        s_campus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    arrayAdapter_building = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, arrayList_London);
                    arrayAdapter_floors = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, arrayList_London_floors);
                    //arrayAdapter_img_floor_plan=new ArrayAdapter<>(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,London_floor_img);

                }
                if (position == 1) {
                    arrayAdapter_building = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, arrayList_Birmingham);
                    arrayAdapter_floors = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, arrayList_Birmingham_floors);

                }
                if (position == 2) {
                    arrayAdapter_building = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, arrayList_Manchester);
                    arrayAdapter_floors = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, arrayList_Manchester_floors);
                }
                s_building.setAdapter(arrayAdapter_building);
                s_floors.setAdapter(arrayAdapter_floors);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btn_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String txtFrom_s_campus = s_campus.getSelectedItem().toString();
                String txtFrom_s_building = s_building.getSelectedItem().toString();
                String txtFrom_s_floors = s_floors.getSelectedItem().toString();
                // imageView.setVisibility(View.VISIBLE);

                if (txtFrom_s_campus.equals("London") & txtFrom_s_building.equals("London Blok 1")
                        & (txtFrom_s_floors.equals("London Ground floor")))
                {
                    imageView.setImageResource(R.drawable.l_b_g);
                }
                else
                if (txtFrom_s_campus.equals("London") & txtFrom_s_building.equals("London Blok 1")
                        & (txtFrom_s_floors.equals("London 1th floor")))
                {
                    imageView.setImageResource(R.drawable.l_b_1);
                }
                else
                if (txtFrom_s_campus.equals("London")
                        & txtFrom_s_building.equals("London Blok 1")
                        & (txtFrom_s_floors.equals("London 2th floor")))
                {
                    imageView.setImageResource(R.drawable.l_b_2);
                }
                else
                if (txtFrom_s_campus.equals("London")
                        & txtFrom_s_building.equals("London Blok 2") & (txtFrom_s_floors.equals("London Ground floor")))
                {
                    imageView.setImageResource(R.drawable.l_b2_g);
                }
                else
                if (txtFrom_s_campus.equals("London")
                        & txtFrom_s_building.equals("London Blok 2") & (txtFrom_s_floors.equals("London 1th floor")))
                {
                    imageView.setImageResource(R.drawable.l_b2_1);
                }
                else
                if (txtFrom_s_campus.equals("London")
                        & txtFrom_s_building.equals("London Blok 2") & (txtFrom_s_floors.equals("London 2th floor")))
                {
                    imageView.setImageResource(R.drawable.l_b2_2);
                }
                else
                if (txtFrom_s_campus.equals("Birmingham")
                        & txtFrom_s_building.equals("Birmingham Blok 1") & (txtFrom_s_floors.equals("Birmingham 5th floor")))
                {
                    imageView.setImageResource(R.drawable.b_b1_5);
                }
                else
                if (txtFrom_s_campus.equals("Birmingham")
                        & txtFrom_s_building.equals("Birmingham Blok 1") & (txtFrom_s_floors.equals("Birmingham 12th floor")))
                {
                    imageView.setImageResource(R.drawable.b_b1_12);
                }
                else
                if (txtFrom_s_campus.equals("Manchester")
                        & txtFrom_s_building.equals("Manchester Blok 1") & (txtFrom_s_floors.equals("Manchester Ground floor")))
                {
                    imageView.setImageResource(R.drawable.m_b1_gr);
                }
                else
                if (txtFrom_s_campus.equals("Manchester")
                        & txtFrom_s_building.equals("Manchester Blok 1") & (txtFrom_s_floors.equals("Manchester 1th floor")))
                {
                    imageView.setImageResource(R.drawable.m_b1_1);
                }
                else if (txtFrom_s_campus.equals("Manchester")
                        & txtFrom_s_building.equals("Manchester Blok 2") & (txtFrom_s_floors.equals("Manchester Ground floor")))
                {
                    imageView.setImageResource(R.drawable.m_b2_gr);
                }
                else
                if (txtFrom_s_campus.equals("Manchester") & txtFrom_s_building.equals("Manchester Blok 2")
                        & (txtFrom_s_floors.equals("Manchester 1th floor")))
                {
                    imageView.setImageResource(R.drawable.m_b2_1);
                }
                else
                {
                    imageView.setImageResource(R.drawable.floor_plan);
                }
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
                    startActivity(new Intent(FloorPlanActivity.this,
                            MainActivity.class));
                    finish();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}