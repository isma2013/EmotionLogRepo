package bg.android.isma.emotionlog;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_Sign_Up;
    EditText input_email;
    TextInputEditText input_password, input_confirm_password;
    TextView termsOfUse, signInHere;
    CheckBox iAgree;
    RelativeLayout activity_sign_up;

    private ProgressDialog progressDialog;
    private FirebaseAuth auth;
    Snackbar snackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Views
        btn_Sign_Up = (Button) findViewById(R.id.b_Sign_Up);
        input_email = (EditText) findViewById(R.id.et_userEmail);
        input_password = (TextInputEditText) findViewById(R.id.et_password);
        input_confirm_password = (TextInputEditText) findViewById(R.id.et_pass_confirm);
        signInHere = (TextView) findViewById(R.id.tv_sign_in);
        termsOfUse = (TextView) findViewById(R.id.tv_TermsOfUse);
        iAgree = (CheckBox) findViewById(R.id.cb_TermsOfUse);
        activity_sign_up = (RelativeLayout) findViewById(R.id.activity_sign_up);

        btn_Sign_Up.setOnClickListener(this);
        signInHere.setOnClickListener(this);
        termsOfUse.setOnClickListener(this);

        progressDialog = new ProgressDialog(this);
        auth = FirebaseAuth.getInstance();
    }

    private void signUpUser() {
        String email = input_email.getText().toString().trim();
        String password = input_password.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            //email is empty
            Toast.makeText(this, "Please enter your e-mail address", Toast.LENGTH_SHORT).show();
            // Stop the function execution further
            return;
        }
        if (TextUtils.isEmpty(password)) {
            //password is empty
            Toast.makeText(this, "Please enter your password ", Toast.LENGTH_SHORT).show();
            // Stop the function execution further
            return;
        }
        if (!iAgree.isChecked()) {
            Toast.makeText(this, "You must agree to the terms and conditions of use.",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        //if validations are ok a progress bar will be shawn
        progressDialog.setMessage("Registering user, please wait...");
        progressDialog.show();
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            snackbar = Snackbar.make(activity_sign_up, "Error:" + task.getException(), Snackbar.LENGTH_LONG);
                            snackbar.show();
                        } else {
                            Toast.makeText(SignUpActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    }
                });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.b_Sign_Up) {

            if (input_password.getText().toString().equals(input_confirm_password.getText().toString())) {
                signUpUser();
            } else {
                Toast.makeText(this, " The passwords do not match, please check again",
                        Toast.LENGTH_LONG).show();
                return;
            }
        }
        if (v.getId() == R.id.tv_sign_in) {
            startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
            finish();
        }
        if (v.getId() == R.id.tv_TermsOfUse) {
            startActivity(new Intent(SignUpActivity.this, TermsOfUse.class));
        }
    }


}
