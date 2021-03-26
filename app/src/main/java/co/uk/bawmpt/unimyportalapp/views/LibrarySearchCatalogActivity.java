package co.uk.bawmpt.unimyportalapp.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.icu.text.StringSearch;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import co.uk.bawmpt.unimyportalapp.R;
import co.uk.bawmpt.unimyportalapp.model.Book;
import co.uk.bawmpt.unimyportalapp.recyclerview.LibraryAdaptar;

public class LibrarySearchCatalogActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Book> fetchDataList;
    private DatabaseReference databaseReference;
    private LibraryAdaptar libraryAdaptar;
    private Button buttonAll;
    private Button buttonBusiness;
    private Button buttonComputing;
    private Button buttonEconomy;
    private Button buttonMath;

    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library_search_catalog);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        buttonAll = findViewById(R.id.btn_all);
        buttonBusiness = findViewById(R.id.btn_bussiness);
        buttonComputing = findViewById(R.id.btn_computing);
        buttonEconomy = findViewById(R.id.btn_ecomonics);
        buttonMath = findViewById(R.id.btn_math);

        //Search bar
        EditText editText=findViewById(R.id.edittext);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });
        //end search
        recyclerView = findViewById(R.id.bookRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fetchDataList = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference("Library");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                fetchDataList.clear();

                for (DataSnapshot ds : snapshot.getChildren()) {
                    Book book = ds.getValue(Book.class);
                    fetchDataList.add(book);
                }

                libraryAdaptar = new LibraryAdaptar(fetchDataList);
                recyclerView.setAdapter(libraryAdaptar);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        buttonAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference = FirebaseDatabase.getInstance().getReference("Library");
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        fetchDataList.clear();
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            Book book = ds.getValue(Book.class);
                            fetchDataList.add(book);
                        }
                        libraryAdaptar = new LibraryAdaptar(fetchDataList);
                        recyclerView.setAdapter(libraryAdaptar);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
            }
        });
        buttonBusiness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Query queryBusiness = FirebaseDatabase.getInstance().getReference("Library")
                       .orderByChild("Category")
                       .equalTo("business");

               queryBusiness.addListenerForSingleValueEvent(new ValueEventListener() {
                   @Override
                   public void onDataChange(@NonNull DataSnapshot snapshot) {
                       fetchDataList.clear();
                       for (DataSnapshot ds : snapshot.getChildren()) {
                           Book book = ds.getValue(Book.class);
                           fetchDataList.add(book);

                           libraryAdaptar = new LibraryAdaptar(fetchDataList);
                           recyclerView.setAdapter(libraryAdaptar);
                       }
                   }
                   @Override
                   public void onCancelled(@NonNull DatabaseError error) {
                   }
               });
            }
        });
        buttonComputing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Query queryBusiness = FirebaseDatabase.getInstance().getReference("Library")
                        .orderByChild("Category")
                        .equalTo("computing");

                queryBusiness.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        fetchDataList.clear();
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            Book book = ds.getValue(Book.class);
                            fetchDataList.add(book);

                            libraryAdaptar = new LibraryAdaptar(fetchDataList);
                            recyclerView.setAdapter(libraryAdaptar);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
            }
        });

        buttonEconomy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Query queryBusiness = FirebaseDatabase.getInstance().getReference("Library")
                        .orderByChild("Category")
                        .equalTo("economics");

                queryBusiness.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        fetchDataList.clear();
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            Book book = ds.getValue(Book.class);
                            fetchDataList.add(book);

                            libraryAdaptar = new LibraryAdaptar(fetchDataList);
                            recyclerView.setAdapter(libraryAdaptar);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
            }
        });

        buttonMath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Query queryBusiness = FirebaseDatabase.getInstance().getReference("Library")
                        .orderByChild("Category")
                        .equalTo("Math");

                queryBusiness.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        fetchDataList.clear();
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            Book book = ds.getValue(Book.class);
                            fetchDataList.add(book);

                            libraryAdaptar = new LibraryAdaptar(fetchDataList);
                            recyclerView.setAdapter(libraryAdaptar);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
            }
        });

    }
    private  void filter (String text){
        List<Book> filteredList=new ArrayList<>();
        for (Book item:fetchDataList){
            if (item.getTitle().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }
            libraryAdaptar.filteredList(filteredList);
        }
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
                    startActivity(new Intent(LibrarySearchCatalogActivity.this,
                            MainActivity.class));
                    finish();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}