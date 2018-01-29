package bg.android.isma.emotionlog;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LoginActivity extends Activity implements View.OnClickListener {

    TextView sign_up, forgot_password;
    EditText EtUserEmail;
    TextInputEditText EtUserPassword;
    Button b_Sign_in;

    private ProgressDialog progressDialog;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.setTitle("                       Welcome");


        //Initialize Firebase Auth
        auth = FirebaseAuth.getInstance();

        //Check already session if Ok -> opens registerActivity
        if (auth.getCurrentUser() != null) {
            finish();
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        }
        // Views

        EtUserEmail = (EditText) findViewById(R.id.et_email);
        EtUserPassword = (TextInputEditText) findViewById(R.id.et_password);
        b_Sign_in = (Button) findViewById(R.id.b_Sign_in);
        forgot_password = (TextView) findViewById(R.id.tv_forgot_details);
        sign_up = (TextView) findViewById(R.id.tv_sign_up);

        progressDialog = new ProgressDialog(this);
        b_Sign_in.setOnClickListener(this);
        sign_up.setOnClickListener(this);

    }

    private void loginUser() {
        String email = EtUserEmail.getText().toString().trim();
        final String password = EtUserPassword.getText().toString().trim();

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
        progressDialog.setMessage("Logging In, please wait...");
        progressDialog.show();
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (password.length() < 6) {
                            Toast.makeText(LoginActivity.this, "Password length must be over 6 characters", Toast.LENGTH_SHORT).show();
                        } else if (task.isSuccessful()) {

                            finish();
                            startActivity(new Intent(LoginActivity.this, EmotionsActivity.class));

                        } else {

                            Toast.makeText(LoginActivity.this, "Login failed!" + task.getException(), Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();


                    }
                });

    }

    @Override
    public void onClick(View v) {
        if (v == b_Sign_in) {
            loginUser();
        }
        if (v == forgot_password) {
            startActivity(new Intent(LoginActivity.this, ForgotPassword.class));
            finish();
        }
         /* Open Register activity when sign up here is clicked */
        if (v == sign_up) {
            Intent SignUpIntent = new Intent(LoginActivity.this, SignUpActivity.class);
            LoginActivity.this.startActivity(SignUpIntent);
            finish();
        }

    }


}
