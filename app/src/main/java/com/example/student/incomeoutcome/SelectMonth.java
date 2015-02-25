package com.example.student.incomeoutcome;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import android.widget.Toast;


public class SelectMonth extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selectmonth);
    }

    public void selectMonthButton(View v) {
        int id = v.getId();
        Intent i;
        switch(id) {
            case R.id.revenue:
                i = new Intent(this, MainActivity.class);
                startActivity(i);
                break;

            case R.id.summary:
                i = new Intent(this, Summary.class);
                startActivity(i);
                break;

            case R.id.jan:
                i = new Intent(this, SummaryResultByMonth.class);
                i.putExtra("smonth","January");
                startActivity(i);
                break;

            case R.id.feb:
                i = new Intent(this, SummaryResultByMonth.class);
                i.putExtra("smonth","February");
                startActivity(i);
                break;
            case R.id.mar:
                i = new Intent(this, SummaryResultByMonth.class);
                startActivity(i);
                break;
            case R.id.apr:
                i = new Intent(this, SummaryResultByMonth.class);
                startActivity(i);
                break;
            case R.id.may:
                i = new Intent(this, SummaryResultByMonth.class);
                startActivity(i);
                break;
            case R.id.june:
                i = new Intent(this, SummaryResultByMonth.class);
                startActivity(i);
                break;
            case R.id.july:
                i = new Intent(this, SummaryResultByMonth.class);
                startActivity(i);
                break;
            case R.id.aug:
                i = new Intent(this, SummaryResultByMonth.class);
                startActivity(i);
                break;
            case R.id.sep:
                i = new Intent(this, SummaryResultByMonth.class);
                startActivity(i);
                break;
            case R.id.oct:
                i = new Intent(this, SummaryResultByMonth.class);
                startActivity(i);
                break;
            case R.id.nov:
                i = new Intent(this, SummaryResultByMonth.class);
                startActivity(i);
                break;
            case R.id.dec:
                i = new Intent(this, SummaryResultByMonth.class);
                startActivity(i);
                break;
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
