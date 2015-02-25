package com.example.student.incomeoutcome;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;


public class SummaryResultByMonth extends ActionBarActivity implements AdapterView.OnItemLongClickListener{

    MyDBHelper helper;
    String input="";
    SimpleCursorAdapter adapter;

    public void summaryByMonthButton(View v) {
        int id = v.getId();
        Intent i;
        switch (id) {
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.summaryresultbymonth);

        helper = new MyDBHelper(this.getApplicationContext());
        SQLiteDatabase db = helper.getReadableDatabase();

        Intent i = this.getIntent();
        input = i.getStringExtra("smonth");

        TextView tv = (TextView)findViewById(R.id.tvSumMonth);
        tv.setText("Summary of "+input);

        TextView income = (TextView)findViewById(R.id.tvIncome);
        Cursor cincome = db.rawQuery("SELECT SUM(ivalue) AS sum FROM income where itrans='+' AND imonth='"+input+"' ;", null);

        cincome.moveToFirst();
            int sincome = cincome.getInt(0);
            income.setText("Income             "+sincome);

        TextView expense = (TextView)findViewById(R.id.tvExpense);
        Cursor cexpense = db.rawQuery("SELECT SUM(ivalue) AS sum FROM income where itrans='-' AND imonth='"+input+"' ;", null);

        cexpense.moveToFirst();
        int sexpense = cexpense.getInt(0);
        expense.setText("Expense            "+sexpense);

        TextView balance = (TextView)findViewById(R.id.tvBalance);
        int sum = sincome-sexpense;

        if(sincome>sexpense){
            balance.setText("Balance            +"+sum);
        }
        else if(sexpense>sincome){
            balance.setText("Balance             "+sum);
        }
        else{
            balance.setText("Balance             "+sum);
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view,
                                   int position, long id) {

        SQLiteDatabase db = helper.getWritableDatabase();

        int n = db.delete("income",
                "_id = ?",
                new String[]{Long.toString(id)});

        if (n == 1) {
            Toast t = Toast.makeText(this.getApplicationContext(),
                    "Successfully deleted the selected item.",
                    Toast.LENGTH_SHORT);
            t.show();

            // retrieve a new collection of records
            Cursor cursor = db.rawQuery(
                    "SELECT _id,idate,(itemname||'                '||value)g FROM income;",
                    null);

            // update the adapter

            SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                    android.R.layout.simple_list_item_2, // A textview
                    cursor, // cursor to a data collection
                    new String[] {"idate" , "g"}, // column to be displayed
                    new int[] {android.R.id.text1 , android.R.id.text2}, // ID of textview to display
                    0);

            ListView lv = (ListView)findViewById(R.id.listView);
            lv.setAdapter(adapter);
            adapter.changeCursor(cursor);
        }
        db.close();
        return true;
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
