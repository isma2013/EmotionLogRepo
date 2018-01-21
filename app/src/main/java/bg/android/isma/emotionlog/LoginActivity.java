package bg.android.isma.emotionlog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity implements View.OnClickListener{

    TextView sign_up;
    EditText EtUsername;
    TextInputEditText Et_password;
    Button b_Sing_in ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.setTitle("                       Welcome");

        EtUsername = (EditText) findViewById(R.id.et_username);
        Et_password = (TextInputEditText) findViewById(R.id.et_password);

        b_Sing_in = (Button) findViewById(R.id.b_Sign_in);

        sign_up = (TextView) findViewById(R.id.tv_sign_up);

        sign_up.setOnClickListener(this);
        b_Sing_in.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v == b_Sing_in){

        }
         /* Open Register activity when sign up here is clicked */
        if( v == sign_up){
            Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
            LoginActivity.this.startActivity(registerIntent);
        }

    }
}
