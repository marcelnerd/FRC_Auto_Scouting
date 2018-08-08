package com.example.cameron.sql_testing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.database.sqlite.*;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    DBHelper helper;
    TextView textBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textBox = findViewById(R.id.textBox);
        helper = new DBHelper(this);
        helper.enterData(223, 4, "the bois");
        textBox.setText(helper.getTeamData(223));
    }
}
