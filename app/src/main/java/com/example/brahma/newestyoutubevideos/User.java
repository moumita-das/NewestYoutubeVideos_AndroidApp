package com.example.brahma.newestyoutubevideos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;



public class User extends AppCompatActivity {
    TextView nameTxt;
    Button btnLogOut;
    FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        btnLogOut = (Button) findViewById(R.id.btnLogOut);
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseAuth.getInstance().signOut();
                Intent I = new Intent(User.this, LoginActivity.class);
                startActivity(I);

            }
        });
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("UserProfile");

        userId = mDatabaseReference.push().getKey();
        mDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserProfile currentUser = dataSnapshot.getValue(UserProfile.class);
                nameTxt = (TextView)findViewById(R.id.name);

                //currrentUser = mDatabaseReference.child(userId).child("name");
                nameTxt.setText(currentUser.getName());
                Toast.makeText(User.this.getApplicationContext(),"Hi"+
                        currentUser.getName(),
                        Toast.LENGTH_SHORT).show();
                //Log.e("Data snapshot","Fetched Name"+currentUser.getName());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("Data snapshot error",""+databaseError);
            }
        });

    }
}
