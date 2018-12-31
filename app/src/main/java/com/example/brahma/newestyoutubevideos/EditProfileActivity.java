package com.example.brahma.newestyoutubevideos;





import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

//Creating and saving profile details for new user (name, age, gender)
public class EditProfileActivity extends AppCompatActivity {

    private EditText nameTxt, ageTxt;
    RadioGroup genderRadioGroup;
    private Button btnSave;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        // Displaying fields
        btnSave = (Button) findViewById(R.id.btnSave);
        nameTxt = (EditText) findViewById(R.id.name);
        ageTxt = (EditText) findViewById(R.id.age);
        genderRadioGroup = (RadioGroup)findViewById(R.id.gender);

        mFirebaseInstance = FirebaseDatabase.getInstance();

        // get reference to 'users' node
        mFirebaseDatabase = mFirebaseInstance.getReference("UserProfile");

        // Save / update the user
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name= nameTxt.getText().toString().trim();
                final String age = ageTxt.getText().toString().trim();
                String gender="";
                if (genderRadioGroup.getCheckedRadioButtonId()!=-1) {
                    int id = genderRadioGroup.getCheckedRadioButtonId();
                    View radioButton = genderRadioGroup.findViewById(id);
                    int radioID = genderRadioGroup.indexOfChild(radioButton);
                    RadioButton btn = (RadioButton)genderRadioGroup.getChildAt(radioID);
                    gender = (String) btn.getText();
                }

                // Check for already existed userId
                if (TextUtils.isEmpty(userId)) {
                    createUser(name, age, gender);
                } /*else {
                    updateUser(name, age, gender);
                }*/
                Intent I = new Intent(EditProfileActivity.this, User.class);
                startActivity(I);
            }

        });

        toggleButton();
    }

    // Changing button text
    private void toggleButton() {
        if (TextUtils.isEmpty(userId)) {
            btnSave.setText("Save");
        } /*else {
            btnSave.setText("Update");
        }*/
    }

    /**
     * Creating new user node under 'users'
     */
    private void createUser(String name, String age, String gender) {
        // TODO
        // In real apps this userId should be fetched
        // by implementing firebase auth
        if (TextUtils.isEmpty(userId)) {
            userId = mFirebaseDatabase.push().getKey();
        }

        UserProfile user = new UserProfile(name, age, gender);

        mFirebaseDatabase.child(userId).setValue(user);

        //addUserChangeListener();
    }

    /**
     * User data change listener
     */
    /*private void addUserChangeListener() {
        // User data change listener
        mFirebaseDatabase.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);

                // Check for null
                if (user == null) {
                    Log.e(TAG, "User data is null!");
                    return;
                }

                Log.e(TAG, "User data is changed!" + user.name + ", " + user.email);

                // Display newly updated name and email
                txtDetails.setText(user.name + ", " + user.email);

                // clear edit text
                inputEmail.setText("");
                inputName.setText("");

                toggleButton();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e(TAG, "Failed to read user", error.toException());
            }
        });
    }

    private void updateUser(String name, String email) {
        // updating the user via child nodes
        if (!TextUtils.isEmpty(name))
            mFirebaseDatabase.child(userId).child("name").setValue(name);

        if (!TextUtils.isEmpty(email))
            mFirebaseDatabase.child(userId).child("email").setValue(email);
    }*/
}