package com.choco.chocolatier;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    private Account account;
    private String username = "";
    private ArrayList<Choco> menuList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("Chocolate Menu");

        Bundle b = getIntent().getExtras();
        if (b != null) {
            username = b.getString("username", "");
        }
        account = MainActivity.getAccount(username);

        menuList = MainActivity.getMenuList();
        ListView lv = (ListView) findViewById(R.id.menu_list);
        lv.setAdapter(new ChocoListAdapter(this, R.layout.item_choco_list, menuList, username));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_profile:
                Intent ip = new Intent(MenuActivity.this, ProfileActivity.class);
                ip.putExtra("username", username);
                startActivity(ip);
                finish();
                return true;
            case R.id.action_history:
                Intent ih = new Intent(MenuActivity.this, HistoryActivity.class);
                ih.putExtra("username", username);
                startActivity(ih);
                return true;
            case R.id.action_about:
                startActivity(new Intent(MenuActivity.this, AboutActivity.class));
                return true;
            case R.id.action_logout:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        Toast.makeText(MenuActivity.this, "Logout to exit application", Toast.LENGTH_SHORT).show();
    }
}
