package com.choco.chocolatier;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private EditText ru, rp, rc, ra, rn, rf;
    private String u, p, c, a, n, f;
    private RadioButton rgm, rgf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("Register Account");

        ru = (EditText) findViewById(R.id.regis_username);
        rp = (EditText) findViewById(R.id.regis_password);
        rc = (EditText) findViewById(R.id.regis_cpassword);
        ra = (EditText) findViewById(R.id.regis_address);
        rn = (EditText) findViewById(R.id.regis_phone);
        rf = (EditText) findViewById(R.id.regis_fullname);
        rgm = (RadioButton) findViewById(R.id.regis_male);
        rgf = (RadioButton) findViewById(R.id.regis_female);

        Button br = (Button) findViewById(R.id.btn_register);
        br.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                u = ru.getText().toString();
                p = rp.getText().toString();
                c = rc.getText().toString();
                a = ra.getText().toString();
                n = rn.getText().toString();
                f = rf.getText().toString();

                if (u.length() < 7) {
                    Toast.makeText(RegisterActivity.this, "Username must be at least 7 characters", Toast.LENGTH_SHORT).show();
                } else if (!validateUsername()) {
                    Toast.makeText(RegisterActivity.this, "Username not available", Toast.LENGTH_SHORT).show();
                } else if (p.length() == 0) {
                    Toast.makeText(RegisterActivity.this, "Password must be filled", Toast.LENGTH_SHORT).show();
                } else if (!validatePassword()) {
                    Toast.makeText(RegisterActivity.this, "Password must contain at least 1 lowercase latter, 1 uppercase letter, and 1 number", Toast.LENGTH_SHORT).show();
                } else if (c.length() == 0) {
                    Toast.makeText(RegisterActivity.this, "Confirm password must be filled", Toast.LENGTH_SHORT).show();
                } else if (!c.equals(p)) {
                    Toast.makeText(RegisterActivity.this, "Confirm password doesn't match with password", Toast.LENGTH_SHORT).show();
                } else if (a.length() < 5 || a.length() > 20) {
                    Toast.makeText(RegisterActivity.this, "Address must be between 5 and 20 characters", Toast.LENGTH_SHORT).show();
                } else if (!a.toLowerCase().endsWith("street")) {
                    Toast.makeText(RegisterActivity.this, "Address must end with 'street'", Toast.LENGTH_SHORT).show();
                } else if (!rgm.isChecked() && !rgf.isChecked()) {
                    Toast.makeText(RegisterActivity.this, "Gender must be chosen", Toast.LENGTH_SHORT).show();
                } else if (n.length() == 0) {
                    Toast.makeText(RegisterActivity.this, "Phone number must be filled", Toast.LENGTH_SHORT).show();
                } else if (!n.startsWith("+62")) {
                    Toast.makeText(RegisterActivity.this, "Phone number must start with '+62'", Toast.LENGTH_SHORT).show();
                } else if (!validatePhone()) {
                    Toast.makeText(RegisterActivity.this, "Phone number must be numeric", Toast.LENGTH_SHORT).show();
                } else {
                    MainActivity.addAccount(new Account(u, f, p, a, n));
                    Intent i = new Intent(RegisterActivity.this, MenuActivity.class);
                    i.putExtra("username", u);
                    startActivity(i);
                    finish();
                }
            }
        });
    }

    private boolean validateUsername() {
        //check if username is available
        Account a = MainActivity.getAccount(u);
        return a.getUsername().isEmpty();
    }

    private boolean validatePhone() {
        for (int i = 1; i < n.length(); i++) {
            if (!Character.isDigit(n.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private boolean validatePassword() {
        int nm = 0;
        int lc = 0;
        int uc = 0;
        for (int i = 0; i < p.length(); i++) {
            if (Character.isDigit(p.charAt(i))) {
                nm++;
            } else if (Character.isLowerCase(p.charAt(i))) {
                lc++;
            } else if (Character.isUpperCase(p.charAt(i))) {
                uc++;
            }
            if (nm > 0 && lc > 0 && uc > 0) {
                return true;
            }
        }
        return false;
    }
}
