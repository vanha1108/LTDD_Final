package com.laptrinhdidong.electroniccomunications;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LogInActivity extends AppCompatActivity {

    private EditText edtEmail, edtPassword;
    private CheckBox chkRemember;
    private Button btnLogin;
    private String email, password;
    FirebaseAuth mAuthencation;

    private static final String SESSION = "SESSION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        mAuthencation = FirebaseAuth.getInstance();

        edtEmail = findViewById(R.id.edtUsernameLogIn);
        edtPassword = findViewById(R.id.edtPasswordLogIn);
        chkRemember = findViewById(R.id.chkRemember);
        btnLogin = findViewById(R.id.btnLogIn);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogIn();
            }
        });
    }

    private void getdata() {
        email = edtEmail.getText().toString();
        password = edtPassword.getText().toString();
    }

    private boolean validate() {
        if (TextUtils.isEmpty(email)) {
            edtEmail.setError("Email is required");
            return false;
        }
        edtEmail.setError(null);
        if (TextUtils.isEmpty(password)) {
            edtPassword.setError("Password is required");
            return false;
        }
        edtPassword.setError(null);
        return true;
    }

    private void LogIn() {
        email = edtEmail.getText().toString();
        password = edtPassword.getText().toString();

        getdata();
        if (validate()) {
            mAuthencation.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Log.i("AA", email + " " + password);
                                if (email.equals("admin@gmail.com")) {         // ADMIN
                                    Intent i = new Intent(LogInActivity.this, MainActivity.class);
                                    startActivity(i);
                                } else {
                                    String mail = email;
                                    String[] word = mail.split("@");
                                    int l = word[0].length();
                                    String check = word[0].substring(l - 2, l);
                                    Log.i("TT", check);
                                    if (check.equals("ph")) {         // PHU HUYNH
                                        Intent intent = new Intent(getApplicationContext(), HomeParentActivity.class);
                                        startActivity(intent);
                                    } else {     // SINH VIEN
                                        Intent intent = new Intent(getApplicationContext(), HomeStudentActivity.class);
                                        startActivity(intent);
                                    }
                                }

                                SharedPreferences preferences = getSharedPreferences(SESSION, MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putString("Email", email);
                                editor.putString("Password", password);
                                editor.putBoolean("Remember", chkRemember.isChecked());
                                editor.commit();

                            } else {
                                openLoginFailedDialog();
                            }
                        }
                    });
        }
    }

    private void openLoginFailedDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialog_login_failed, null);
        AlertDialog alertDialog = builder.create();
        alertDialog.setView(view);
        alertDialog.getWindow().setLayout(300, 500);
        Button btnYes = view.findViewById(R.id.btnYes);
        btnYes.setOnClickListener(v -> {
            alertDialog.cancel();
        });
        alertDialog.show();
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences preferences = getSharedPreferences(SESSION, MODE_PRIVATE);
        String email = preferences.getString("Email", "");
        String password = preferences.getString("Password", "");
        boolean remember = preferences.getBoolean("Remember", false);
        if (remember) {
            edtEmail.setText(email);
            edtPassword.setText(password);
            chkRemember.setChecked(remember);
        }
    }
}