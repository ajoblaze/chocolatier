package com.choco.chocolatier;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

public class HistoryActivity extends AppCompatActivity {

    private String username;
    private Account account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("History");

        Bundle b = getIntent().getExtras();
        if (b != null) {
            username = b.getString("username", "");
        }
        account = MainActivity.getAccount(username);
        ListView lv = (ListView) findViewById(R.id.menu_list);
        ArrayList<Transaction> transactions = account.getTransactionList();
        if (transactions.isEmpty()) {
            findViewById(R.id.empty).setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.empty).setVisibility(View.GONE);
        }
        Collections.reverse(transactions);
        lv.setAdapter(new TransactionListAdapter(this, R.layout.item_transaction_list, transactions));
    }
}
