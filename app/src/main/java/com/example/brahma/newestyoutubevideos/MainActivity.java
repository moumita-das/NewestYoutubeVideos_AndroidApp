package com.example.brahma.newestyoutubevideos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (user!=null){
            startActivity(new Intent(MainActivity.this, User.class));
        }
        else{
            startActivity(new Intent(MainActivity.this, SignUpActivity.class));
        }
    }
}
