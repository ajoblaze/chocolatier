package com.choco.chocolatier;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {

    private Account account;
    private EditText pu, pp, pc, pa, pn, po;
    private String u, p, c, a, n, o;
    private String username = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("Profile");

        Bundle b = getIntent().getExtras();
        if (b != null) {
            username = b.getString("username", "");
        }
        account = MainActivity.getAccount(username);

        pu = (EditText) findViewById(R.id.prof_username);
        po = (EditText) findViewById(R.id.prof_opassword);
        pp = (EditText) findViewById(R.id.prof_password);
        pc = (EditText) findViewById(R.id.prof_cpassword);
        pa = (EditText) findViewById(R.id.prof_address);
        pn = (EditText) findViewById(R.id.prof_phone);
        Button bu = (Button) findViewById(R.id.btn_update);
        bu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                u = pu.getText().toString();
                p = pp.getText().toString();
                c = pc.getText().toString();
                a = pa.getText().toString();
                n = pn.getText().toString();
                o = po.getText().toString();

                if (u.length() < 7) {
                    Toast.makeText(ProfileActivity.this, "Username must be at least 7 characters", Toast.LENGTH_SHORT).show();
                } else if (!validateUsername()) {
                    Toast.makeText(ProfileActivity.this, "Username not available", Toast.LENGTH_SHORT).show();
                } else if (!o.equals(account.getPassword())) {
                    Toast.makeText(ProfileActivity.this, "Old password must match with current password", Toast.LENGTH_SHORT).show();
                } else if (p.length() == 0) {
                    Toast.makeText(ProfileActivity.this, "Password must be filled", Toast.LENGTH_SHORT).show();
                } else if (!validatePassword()) {
                    Toast.makeText(ProfileActivity.this, "Password must contain at least 1 lowercase latter, 1 uppercase letter, and 1 number", Toast.LENGTH_SHORT).show();
                } else if (c.length() == 0) {
                    Toast.makeText(ProfileActivity.this, "Confirm password must be filled", Toast.LENGTH_SHORT).show();
                } else if (!c.equals(p)) {
                    Toast.makeText(ProfileActivity.this, "Confirm password doesn't match with password", Toast.LENGTH_SHORT).show();
                } else if (a.length() < 5 || a.length() > 20) {
                    Toast.makeText(ProfileActivity.this, "Address must be between 5 and 20 characters", Toast.LENGTH_SHORT).show();
                } else if (!a.toLowerCase().endsWith("street")) {
                    Toast.makeText(ProfileActivity.this, "Address must end with 'street'", Toast.LENGTH_SHORT).show();
                } else if (n.length() == 0) {
                    Toast.makeText(ProfileActivity.this, "Phone number must be filled", Toast.LENGTH_SHORT).show();
                } else if (!n.startsWith("+62")) {
                    Toast.makeText(ProfileActivity.this, "Phone number must start with '+62'", Toast.LENGTH_SHORT).show();
                } else if (!validatePhone()) {
                    Toast.makeText(ProfileActivity.this, "Phone number must be numeric", Toast.LENGTH_SHORT).show();
                } else {
                    account.update(u, p, a, n);
                    Toast.makeText(ProfileActivity.this, "Profile updated successfully", Toast.LENGTH_SHORT).show();
                    goToMenu();
                }
            }
        });
    }

    private boolean validateUsername() {
        //check if username is available
        Account a = MainActivity.getAccount(u);
        return a.getUsername().equals(u) || a.getUsername().isEmpty();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_about:
                startActivity(new Intent(ProfileActivity.this, AboutActivity.class));
                return true;
            case R.id.action_logout:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void goToMenu() {
        Intent i = new Intent(ProfileActivity.this, MenuActivity.class);
        i.putExtra("username", username);
        startActivity(i);
        finish();
    }

    @Override
    public void onBackPressed() {
        goToMenu();
    }
}
