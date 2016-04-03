package com.choco.chocolatier;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static ArrayList<Account> accountList;
    private static ArrayList<Choco> menuList;
    private EditText lu, lp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("Login");

        accountList = new ArrayList<>();
        menuList = new ArrayList<>();
        populate();

        lu = (EditText) findViewById(R.id.login_username);
        lp = (EditText) findViewById(R.id.login_password);
        Button bl = (Button) findViewById(R.id.btn_login);
        Button br = (Button) findViewById(R.id.btn_register);
        bl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lu.getText().toString().equals("") || lp.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "Username and Password must be filled", Toast.LENGTH_SHORT).show();
                } else {
                    if (isRegistered()) {
                        Intent i = new Intent(MainActivity.this, MenuActivity.class);
                        i.putExtra("username", lu.getText().toString());
                        startActivity(i);
                    } else {
                        Toast.makeText(MainActivity.this, "Username and Password combination wrong", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        br.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
        });
    }

    private boolean isRegistered() {
        String username = lu.getText().toString();
        String password = lp.getText().toString();
        for (int i = 0; i < accountList.size(); i++) {
            if (accountList.get(i).getUsername().equals(username) && accountList.get(i).getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public static Account getAccount(String username) {
        for (Account a : accountList) {
            if (a.getUsername().equals(username)) {
                return a;
            }
        }
        return new Account();
    }

    public static void addAccount(Account a) {
        accountList.add(a);
    }

    public static ArrayList<Choco> getMenuList() {
        return menuList;
    }

    private void populate() {
        menuList.add(new Choco("Chocolate bar",
                "rolled crepe filled with our special mix, covered with our special Belgian chocolate",
                "Rp 35.000"));
        menuList.add(new Choco("Chocolate rose",
                "creme stuffed with cream patisserie, topped with our homemade rose chocolate and Belgian chocolate ",
                "Rp 40.000"));
        menuList.add(new Choco("Crepe pouch",
                "folded crepe filled with cream cheese, raspberry, and blueberry, served with a cup of our Belgian chocolate",
                "Rp 45.000"));
        menuList.add(new Choco("Brownies chocolate",
                "homemade brownies chocolate with extra chocolate and melted Belgian chocolate inside",
                "Rp 45.000"));
        menuList.add(new Choco("Banana chocolate",
                "sliced banana with cream cheese, wrapped with crepe, and topped with our special Belgian chocolate",
                "Rp 45.000"));
        menuList.add(new Choco("Chocolate Barley's",
                "our special Belgian chocolate with macadamia and filled with Barley's",
                "Rp 50.000"));
    }
}
