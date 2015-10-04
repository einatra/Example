package com.parse.starter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.SignUpCallback;

public class SignUp extends AppCompatActivity {

    EditText userN, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initViews();

        findViewById(R.id.signBtnDone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUser();
            }
        });
    }

    private void initViews(){
        userN = (EditText) findViewById(R.id.signEditUser);
        pass = (EditText) findViewById(R.id.signEditPass);
    }

    public User addUser(){
        User user = new User();
        user.setUsername(userN.getText().toString());
        user.setPassword(pass.getText().toString());
        //user.setEmail("email@example.com");

        // other fields can be set just like with ParseObject
        //user.put("phone", "650-555-0000");

        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(com.parse.ParseException e) {
                if (e == null) {
                    Toast.makeText(SignUp.this, "User created", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignUp.this, Welcome.class);
                    startActivity(intent);
                    finish();
                } else {
                    Log.d("error", e.toString());
                    // Sign up didn't succeed. Look at the ParseException
                    // to figure out what went wrong
                }
            }
        });
        return user;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sign_up, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
