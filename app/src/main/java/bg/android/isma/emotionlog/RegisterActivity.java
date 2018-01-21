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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    EditText last_name;
    EditText first_name;
    EditText birth_date;
    EditText gender;
    AutoCompleteTextView country_name;
    EditText email_address;
    EditText username;
    EditText password;
    EditText retype_password;
    Button sign_Up;
    TextView termsOfUseLink;
    CheckBox agreeToTerms;
    Button cancel;
    TextView alreadyMember;
    AutoCompleteTextView autocomplete;
    ArrayAdapter<String> adapter;

    DatabaseReference databaseReference;


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
        email_address = (EditText) findViewById(R.id.et_email);
        username = (EditText) findViewById(R.id.etUsername);
        password = (EditText) findViewById(R.id.etPassword);
        retype_password = (EditText) findViewById(R.id.etCheckPassword);
        sign_Up = (Button) findViewById(R.id.BSignup);
        cancel = (Button) findViewById(R.id.BCancel);
        termsOfUseLink = (TextView) findViewById(R.id.tv_TermsOfUse);
        alreadyMember = (TextView) findViewById(R.id.tvAlreadyMember);
        agreeToTerms = (CheckBox) findViewById(R.id.cbTermsofUse);


        String[] countries = getResources().getStringArray(R.array.countries_array);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, countries);
        autocomplete = (AutoCompleteTextView) findViewById(R.id.acTvCountry);
        autocomplete.setAdapter(adapter);
        autocomplete.setThreshold(1);

        sign_Up.setOnClickListener(this);

        cancel.setOnClickListener(this);

        termsOfUseLink.setOnClickListener(this);

        alreadyMember.setOnClickListener(this);

        agreeToTerms.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == sign_Up) {
            if (!agreeToTerms.isChecked()) {
                Toast.makeText(this, "You must agree to the terms and conditions of use.",
                        Toast.LENGTH_LONG).show();
            } else {
                saveUser();
            }

        }
        // Cancel registration and redirect to the Login activity
        if (v == cancel) {
            Intent CancelItent = new Intent(RegisterActivity.this, LoginActivity.class);
            CancelItent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(CancelItent);
        }
        // Open the TermsOfUse activity when the Terms and Conditions text_view is clicked

        if (v == termsOfUseLink) {
            Intent termsOfUseIntent = new Intent(RegisterActivity.this, TermsOfUse.class);
            RegisterActivity.this.startActivity(termsOfUseIntent);
        }
        // Redirect to the Login activity when alreadyMember text_view is clicked
        if (v == alreadyMember) {
            Intent LoginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
            LoginIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(LoginIntent);
        }
    }

    private void saveUser() {

        String LastName = last_name.getText().toString().trim();
        String FirstName = first_name.getText().toString().trim();
        String BirthDate = birth_date.getText().toString().trim();
        String Gender = gender.getText().toString().trim();
        String CountryName = country_name.getText().toString().trim();
        String EmailAddress = email_address.getText().toString().trim();
        String UserName = username.getText().toString().trim();
        String Password = password.getText().toString().trim();
        String RetypePassword = retype_password.getText().toString().trim();


        if (!TextUtils.isEmpty(LastName) && !TextUtils.isEmpty(LastName)
                && !TextUtils.isEmpty(FirstName)
                && !TextUtils.isEmpty(BirthDate) && !TextUtils.isEmpty(Gender)
                && !TextUtils.isEmpty(Gender)
                && !TextUtils.isEmpty(CountryName) && !TextUtils.isEmpty(EmailAddress)
                && !TextUtils.isEmpty(UserName)
                && !TextUtils.isEmpty(Password) && !TextUtils.isEmpty(RetypePassword)) {

            String id = databaseReference.push().getKey();

            User user = new User(id, LastName, FirstName, BirthDate, Gender, CountryName,
                    EmailAddress, UserName, Password, RetypePassword);

            databaseReference.child(id).setValue(user);
            Toast.makeText(this, "User registered successfully", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_LONG).show();
        }

    }

}

