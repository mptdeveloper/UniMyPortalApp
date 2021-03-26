package co.uk.bawmpt.unimyportalapp.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import co.uk.bawmpt.unimyportalapp.R;
import co.uk.bawmpt.unimyportalapp.util.UserApi;

public class CreateAccountActivity extends AppCompatActivity {

    /*
    Declaration of the widgets used in this Activity
     */
    private EditText fullNameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText studentIdEditText;
    private Button createAccount;
    private ProgressBar progressBar;

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseUser currentUser;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collectionReference = db.collection("Users");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        /*
        Initializations of the widgets and Firebase Authentication:
         */
        fullNameEditText = findViewById(R.id.createAccountName);
        emailEditText = findViewById(R.id.createAccountEmail);
        passwordEditText = findViewById(R.id.createAccountPassword);
        studentIdEditText = findViewById(R.id.createAccountStudentId);
        createAccount = findViewById(R.id.createAccountButton);
        progressBar = findViewById(R.id.createAccountProgressBar);

        firebaseAuth = FirebaseAuth.getInstance();

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                currentUser = firebaseAuth.getCurrentUser();
                if (currentUser != null) {
                }
                else {
                }
            }
        };
        /*
        Set OnClickListener on "Create Account" button and calling the method to register
        and save user as an object in Firebase:
         */
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!TextUtils.isEmpty(fullNameEditText.getText().toString())
                        && !TextUtils.isEmpty(emailEditText.getText().toString())
                        && !TextUtils.isEmpty(passwordEditText.getText().toString())
                        && !TextUtils.isEmpty(studentIdEditText.getText().toString())) {

                    String fullName = fullNameEditText.getText().toString().trim();
                    String email = emailEditText.getText().toString().trim();
                    String password = passwordEditText.getText().toString().trim();
                    String studentId = studentIdEditText.getText().toString().trim();

                    CreateUserAccount(fullName, email, password, studentId);

                }
                else {
                    Toast.makeText(CreateAccountActivity.this, "Empty Fields Not Allowed",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    /*
    Creating method to register and save user in Firebase Firestore Cloud Database:
     */
    private void CreateUserAccount(String fullName, String email, String password, String studentId) {
        if (!TextUtils.isEmpty(fullName) && !TextUtils.isEmpty(email)
                && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(studentId)) {

            progressBar.setVisibility(View.VISIBLE);

            firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                //We take user to the DashboardActivity.
                                currentUser = firebaseAuth.getCurrentUser();
                                String currentUserId = currentUser.getUid();

                                //Creating a User map, so we can create user in user collection in Firebase Firestore:
                                Map<String, String> userObj = new HashMap<>();
                                userObj.put("UserId", currentUserId);
                                userObj.put("Name", fullName);
                                userObj.put("Email", email);
                                userObj.put("StudentId", studentId);

                                //Saving the User in Firebase Firestore Database:
                                collectionReference.add(userObj)
                                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                            @Override
                                            public void onSuccess(DocumentReference documentReference) {
                                                documentReference.get()
                                                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                                if (Objects.requireNonNull(task.getResult()).exists()) {

                                                                    progressBar.setVisibility(View.INVISIBLE);

                                                                    String name = task.getResult().getString("Name");

                                                                    UserApi userApi = UserApi.getInstance();
                                                                    userApi.setUserId(currentUserId);
                                                                    userApi.setName(name);

                                                                    Intent intent = new Intent(CreateAccountActivity.this,
                                                                            LoginActivity.class);
                                                                    intent.putExtra("Name", name);
                                                                    intent.putExtra("UserId", currentUserId);
                                                                    startActivity(intent);
                                                                }
                                                                else {
                                                                    progressBar.setVisibility(View.INVISIBLE);
                                                                }

                                                            }
                                                        });
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                    }
                                });
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });
        }
        else {

        }
    }

    /*
    Overridden onStart method to authenticate user and hold as variable as current user,
    Set AuthStateListener:
     */
    @Override
    protected void onStart() {
        super.onStart();
        currentUser = firebaseAuth.getCurrentUser();
        firebaseAuth.addAuthStateListener(authStateListener);
    }
}