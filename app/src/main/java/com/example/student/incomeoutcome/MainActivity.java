package com.example.student.incomeoutcome;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.RadioGroup;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    MyDBHelper helper;
    String transaction = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper = new MyDBHelper(this.getApplicationContext());

        Intent i = this.getIntent();
        if (i.hasExtra("iname")) {
            String iname = i.getStringExtra("iname");
            Double ivalue = i.getDoubleExtra("ivalue", 0.0);
            int iday = i.getIntExtra("iday", 0);
            String imonth = i.getStringExtra("imonth");
            int iyear = i.getIntExtra("iyear", 0);
            transaction = i.getStringExtra("itrans");

            EditText itemname = (EditText)findViewById(R.id.itemname);
            itemname.setText(iname);


            EditText value = (EditText)findViewById(R.id.value);
            value.setText(Double.toString(ivalue));

            EditText day = (EditText)findViewById(R.id.iday);
            day.setText(Integer.toString(iday));

            EditText month = (EditText)findViewById(R.id.imonth);
            month.setText(imonth);

            EditText year = (EditText)findViewById(R.id.iyear);
            year.setText(Integer.toString(iyear));
            Button btAdd = (Button)findViewById(R.id.add);
            btAdd.setText("Edit Item");

        }
    }


    public void buttonMain(View v) {
        int id = v.getId();
        Intent i;
        Toast t;
        switch(id) {
            case R.id.revenue:
                i = new Intent(this,RevenueResult.class);
                startActivity(i);
                break;

            case R.id.summary:
                i = new Intent(this, Summary.class);
                startActivity(i);
                break;

            case R.id.income:
                transaction ="+";
                t = Toast.makeText(this.getApplicationContext(),
                        "Income selected",
                        Toast.LENGTH_SHORT);
                t.show();
                break;

            case R.id.expense:
                transaction = "-";
                t = Toast.makeText(this.getApplicationContext(),
                        "Expense selected",
                        Toast.LENGTH_SHORT);
                t.show();
                break;
        }
    }


    public void addClicked(View v) {

        EditText itemname = (EditText) findViewById(R.id.itemname);
        EditText value = (EditText) findViewById(R.id.value);
        EditText day = (EditText) findViewById(R.id.iday);
        EditText month = (EditText) findViewById(R.id.imonth);
        EditText year = (EditText) findViewById(R.id.iyear);

        String sitemname = itemname.getText().toString();
        String svalue = value.getText().toString();
        String sday = day.getText().toString();
        String smonth = month.getText().toString();
        String syear = year.getText().toString();

        if (sitemname.trim().length() == 0 || svalue.trim().length() == 0 || sday.trim().length() == 0 || smonth.trim().length() == 0 || syear.trim().length() == 0 || transaction.trim().length() == 0) {
            Toast t = Toast.makeText(this.getApplicationContext(),
                    "Fill all information and select the type.",
                    Toast.LENGTH_SHORT);
            t.show();
        }
        else {
            SQLiteDatabase db = helper.getWritableDatabase();
            ContentValues r = new ContentValues();
            r.put("iname", sitemname);
            r.put("ivalue", Double.parseDouble(svalue));
            r.put("iday", Integer.parseInt(sday));
            r.put("imonth", smonth);
            r.put("iyear", Integer.parseInt(syear));
            r.put("itrans", transaction);

            long newId = db.insert("income", null, r);

            if (newId != -1) {
                Toast t = Toast.makeText(this.getApplicationContext(),
                        "Successfully added a new item",
                        Toast.LENGTH_SHORT);
                t.show();
                Intent i;
                i = new Intent(this, RevenueResult.class);
                startActivity(i);
            } else {
                Toast t = Toast.makeText(this.getApplicationContext(),
                        "Unable to add a new item",
                        Toast.LENGTH_SHORT);
                t.show();
            }
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
