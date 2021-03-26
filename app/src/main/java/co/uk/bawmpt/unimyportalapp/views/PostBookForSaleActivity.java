package co.uk.bawmpt.unimyportalapp.views;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Date;
import java.util.concurrent.TimeoutException;

import co.uk.bawmpt.unimyportalapp.R;
import co.uk.bawmpt.unimyportalapp.model.Book;
import co.uk.bawmpt.unimyportalapp.util.UserApi;

public class PostBookForSaleActivity extends AppCompatActivity implements View.OnClickListener {


    private static final int GALLERY_CODE = 1;
    private static final String TAG = "PostBookForSaleActivity";
    private Button saveButton;
    private ProgressBar progressBar;
    private ImageView addPhotoButton;
    private ImageView postBookImageView;
    private EditText titleEditText;
    private EditText authorEditText;
    private EditText descriptionEditText;
    private TextView currentUserTextView;
    private TextView timeStamp;

    private String currentUserId;
    private String currentUserName;

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseUser user;

    //Connection to Firestore:
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private StorageReference storageReference;

    private CollectionReference collectionReference = db.collection("Book_Sale");
    private Uri imageUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_book_for_sale);

        firebaseAuth = FirebaseAuth.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        progressBar = findViewById(R.id.postBookProgressBar);
        titleEditText = findViewById(R.id.postBookTtitle);
        authorEditText = findViewById(R.id.postBookAuthor);
        descriptionEditText = findViewById(R.id.postBookDescription);
        currentUserTextView = findViewById(R.id.postBookName);
        timeStamp = findViewById(R.id.postBookTimeStamp);
        postBookImageView = findViewById(R.id.postBookImageView);

        saveButton = findViewById(R.id.postBookButtonSave);
        saveButton.setOnClickListener(this);
        addPhotoButton = findViewById(R.id.postBookCameraButton);
        addPhotoButton.setOnClickListener(this);


        progressBar.setVisibility(View.INVISIBLE);

        if (UserApi.getInstance() != null) {
            currentUserName = UserApi.getInstance().getName("Name");
            currentUserId = UserApi.getInstance().getUserId("UserId");

            currentUserTextView.setText(currentUserName);
        }

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                user = firebaseAuth.getCurrentUser();
                if (user != null){

                } else {

                }
            }
        };
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.postBookButtonSave:
                //Save Posted Book
                saveBookForSale();
                break;
            case R.id.postBookCameraButton:
                //Get images from gallery or phone:
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALLERY_CODE);
                break;
        }
    }

    private void saveBookForSale() {

        String title = titleEditText.getText().toString().trim();
        String author = authorEditText.getText().toString().trim();
        String description = descriptionEditText.getText().toString().trim();

        progressBar.setVisibility(View.VISIBLE);

        if (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(author)
                && !TextUtils.isEmpty(description) && imageUri != null ) {


            //Saving images into Firebase Storage, using Timestamp as part of naming images:
            StorageReference filepath = storageReference
                    .child("books_for_sale") //Image folder where "Books for sale gonna be stored
                    .child("image_" + Timestamp.now().getSeconds()); //.../image_646464646

            filepath.putFile(imageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {

                                    String imageUrl = uri.toString();

                                    //Create a Post Book Object - model:
                                    Book bookSale = new Book();

                                    bookSale.setTitle(title);
                                    bookSale.setAuthor(author);
                                    bookSale.setDescription(description);
                                    bookSale.setImageUrl(imageUrl);
                                    bookSale.setTimeAdded(new Timestamp(new Date()));
                                    bookSale.setUserName(currentUserName);
                                    bookSale.setUserId(currentUserId);


                                    //Invoke our Collection Reference:
                                    collectionReference.add(bookSale)
                                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                                @Override
                                                public void onSuccess(DocumentReference documentReference) {
                                                    progressBar.setVisibility(View.INVISIBLE);

                                                    startActivity(new Intent(PostBookForSaleActivity.this,
                                                            BookSaleListActivity.class));
                                                    finish();

                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Log.d(TAG, "onFailure: " + e.getMessage());

                                                }
                                            });


                                    //Save Post Book instance:
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressBar.setVisibility(View.INVISIBLE);


                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });

        } else {

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_CODE && resultCode == RESULT_OK) {
            if (data != null) {
                imageUri = data.getData();
                postBookImageView.setImageURI(imageUri);
            }
        }
    }



    @Override
    protected void onStart() {
        super.onStart();
        user = firebaseAuth.getCurrentUser();
        firebaseAuth.addAuthStateListener(authStateListener);
    }



    @Override
    protected void onStop() {
        super.onStop();
        if (firebaseAuth != null) {
            firebaseAuth.removeAuthStateListener(authStateListener);
        }
    }
}