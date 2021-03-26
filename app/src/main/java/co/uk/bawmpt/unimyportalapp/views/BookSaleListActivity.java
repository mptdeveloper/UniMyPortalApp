package co.uk.bawmpt.unimyportalapp.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

import co.uk.bawmpt.unimyportalapp.R;
import co.uk.bawmpt.unimyportalapp.model.Book;
import co.uk.bawmpt.unimyportalapp.recyclerview.BookSaleAdapter;

public class BookSaleListActivity extends AppCompatActivity {


    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collectionReference = db.collection("Book_Sale");
    private List<Book> bookSaleList;
    private RecyclerView bookSaleRecyclerView;
    private BookSaleAdapter bookSaleAdapter;
    private TextView noBooks;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_sale_list);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        noBooks = findViewById(R.id.bookSaleNoBooks);
        bookSaleList = new ArrayList<>();

        bookSaleRecyclerView = findViewById(R.id.bookListRecyclerView);
        bookSaleRecyclerView.setHasFixedSize(true);
        bookSaleRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_book_sale, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                //This will move user to PostBookSaleActivity to allows user add a book he would like to sell:
                if (user != null && firebaseAuth != null) {
                    startActivity(new Intent(BookSaleListActivity.this, PostBookForSaleActivity.class));
                }
                break;
            case R.id.action_singout:
                if (user != null && firebaseAuth != null) {
                    firebaseAuth.signOut();
                    startActivity(new Intent(BookSaleListActivity.this, MainActivity.class));
                    finish();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();

        collectionReference.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            for (QueryDocumentSnapshot saleBooks : queryDocumentSnapshots) {
                                Book bookSale = saleBooks.toObject(Book.class);
                                bookSaleList.add(bookSale);
                            }

                            //Invoke recycler view:
                            bookSaleAdapter = new BookSaleAdapter(BookSaleListActivity.this,
                                    bookSaleList);
                            bookSaleRecyclerView.setAdapter(bookSaleAdapter);
                            bookSaleAdapter.notifyDataSetChanged();
                        }
                        else {
                            noBooks.setVisibility(View.VISIBLE);
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                    }
                });
    }
}