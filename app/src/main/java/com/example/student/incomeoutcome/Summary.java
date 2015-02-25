package com.example.student.incomeoutcome;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class Summary extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.summary);
    }

    public void monthClicked(View v){
        Intent i = new Intent(this,SelectMonth.class);
        startActivity(i);
    }

    public void buttonSummary(View v) {
        int id = v.getId();
        Intent i;

        switch(id) {
            case R.id.day:
                i = new Intent(this,SummaryResultByDay.class);
                startActivity(i);
                break;

            case R.id.month:
                i = new Intent(this,SelectMonth.class);
                startActivity(i);
                break;

            case R.id.revenue:
                i = new Intent(this, MainActivity.class);
                startActivity(i);
                break;

            case R.id.summary:
                i = new Intent(this, Summary.class);
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
