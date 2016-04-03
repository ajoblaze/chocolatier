package com.choco.chocolatier;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CheckOutActivity extends AppCompatActivity {

    private ArrayList<Choco> menuList;
    private Account account;
    private EditText qty;
    private String q;
    private Choco c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("Check Out");

        menuList = MainActivity.getMenuList();

        Bundle b = getIntent().getExtras();
        String username = "";
        int chocoIndex = -1;
        if (b != null) {
            username = b.getString("username", "");
            chocoIndex = b.getInt("choco", -1);
        }
        account = MainActivity.getAccount(username);
        c = menuList.get(chocoIndex);

        TextView name = (TextView) findViewById(R.id.co_name);
        TextView desc = (TextView) findViewById(R.id.co_desc);
        TextView price = (TextView) findViewById(R.id.co_price);
        qty = (EditText) findViewById(R.id.co_qty);

        name.setText(c.getName());
        desc.setText(c.getDesc());
        price.setText(c.getPrice());

        Button bp = (Button) findViewById(R.id.btn_purchase);
        bp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q = qty.getText().toString();
                if (qty.getText().toString().isEmpty()) {
                    Toast.makeText(CheckOutActivity.this, "Quantity must be filled", Toast.LENGTH_SHORT).show();
                } else if (!validateQty()) {
                    Toast.makeText(CheckOutActivity.this, "Quantity must be numeric", Toast.LENGTH_SHORT).show();
                } else {
                    account.getTransactionList().add(new Transaction(c, q));
                    Toast.makeText(CheckOutActivity.this, "Transaction successfully recorded", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    private boolean validateQty() {
        for (int i = 0; i < q.length(); i++) {
            if (!Character.isDigit(q.charAt(i))) {
                return false;
            }
        }
        return true;
    }

}
