package uz.iutlab.assignment3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText userNameInput;
    EditText passwordInput;
    Button loginButton;
    Button signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userNameInput = (EditText) findViewById(R.id.loginEditTextXML);
        passwordInput = (EditText) findViewById(R.id.passwordEditTextXML);

        loginButton = (Button) findViewById(R.id.loginButtonXML);
        signupButton = (Button) findViewById(R.id.signupButtonXML);

        signupButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String user = userNameInput.getText().toString();
                String password = passwordInput.getText().toString();
                SharedPreferences sharedPref = getSharedPreferences("sharedPreferencesFile", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                Log.d("User",user);
                Log.d("User",password);
                editor.putString(user, password);
                editor.commit();

                Toast.makeText(LoginActivity.this, "user is Saved", Toast.LENGTH_SHORT).show();

            }
        });

        loginButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                String username = userNameInput.getText().toString();
                String password = passwordInput.getText().toString();




                SharedPreferences sharedPref = getSharedPreferences("sharedPreferencesFile",MODE_PRIVATE);
                String savedPassword = sharedPref.getString(username,"");

                Log.d("User", savedPassword);

                if (password.equals(savedPassword)){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("user_name",username);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(LoginActivity.this, "Please check user or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
