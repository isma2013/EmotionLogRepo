package bg.android.isma.emotionlog;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    EditText last_name;
    EditText first_name;
    EditText birth_date;
    EditText gender;
    EditText emotion_description;
    AutoCompleteTextView country_name;
    Button save_continue;
    Button cancel_logout;
    AutoCompleteTextView autocomplete;
    ArrayAdapter<String> adapter;

    DatabaseReference databaseReference;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        databaseReference = FirebaseDatabase.getInstance().getReference("Users");

        last_name = (EditText) findViewById(R.id.etLastname);
        first_name = (EditText) findViewById(R.id.etFirstname);
        birth_date = (EditText) findViewById(R.id.etBirthdate);
        gender = (EditText) findViewById(R.id.etGender);
        country_name = (AutoCompleteTextView) findViewById(R.id.acTvCountry);
        emotion_description = (EditText) findViewById(R.id.et_describe_emotion);
        save_continue = (Button) findViewById(R.id.BSignup);
        cancel_logout = (Button) findViewById(R.id.BCancel);


        String[] countries = getResources().getStringArray(R.array.countries_array);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, countries);
        autocomplete = (AutoCompleteTextView) findViewById(R.id.acTvCountry);
        autocomplete.setAdapter(adapter);
        autocomplete.setThreshold(1);

        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
        FirebaseUser user = auth.getCurrentUser();
        Toast.makeText(this, "Welcome " + user.getEmail(), Toast.LENGTH_LONG).show();

        save_continue.setOnClickListener(this);
        cancel_logout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == save_continue) {
            saveUser();
        }
        // Cancel registration and redirect to the Login activity
        if (v == cancel_logout) {
            auth.signOut();
            finish();
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        }
    }

    private void saveUser() {

        String LastName = last_name.getText().toString().trim();
        String FirstName = first_name.getText().toString().trim();
        String BirthDate = birth_date.getText().toString().trim();
        String Gender = gender.getText().toString().trim();
        String CountryName = country_name.getText().toString().trim();
        String EmotionDescription = emotion_description.getText().toString().trim();

        if (!TextUtils.isEmpty(LastName) && !TextUtils.isEmpty(LastName)
                && !TextUtils.isEmpty(FirstName)
                && !TextUtils.isEmpty(BirthDate) && !TextUtils.isEmpty(Gender)
                && !TextUtils.isEmpty(Gender) && !TextUtils.isEmpty(CountryName)
                && !TextUtils.isEmpty(EmotionDescription)) {

            String id = databaseReference.push().getKey();

            User user = new User(id, LastName, FirstName, BirthDate, Gender, CountryName, EmotionDescription);

            databaseReference.child(id).setValue(user);
            Toast.makeText(this, "User registered successfully", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_LONG).show();
        }

    }

}

