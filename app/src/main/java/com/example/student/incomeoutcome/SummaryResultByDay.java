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

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class SummaryResultByDay extends ActionBarActivity implements AdapterView.OnItemLongClickListener{

    MyDBHelper helper;
    SimpleCursorAdapter adapter;
    String m = "";


    public void summaryByDayButton(View v) {
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
        setContentView(R.layout.summaryresultbyday);
        helper = new MyDBHelper(this.getApplicationContext());
        SQLiteDatabase db = helper.getReadableDatabase();

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String curdate = df.format(c.getTime());

        String[] parts = curdate.split("-");

        m = convertMonth(parts[1]);

        TextView tv = (TextView)findViewById(R.id.tvSumDay);
        tv.setText("Summary of Today");

        TextView income = (TextView)findViewById(R.id.tvIncome);
        Cursor cincome = db.rawQuery("SELECT SUM(ivalue) AS sum FROM income where itrans='+' AND iday='"+parts[0]+"' AND imonth = '"+m+"' AND iyear = '"+parts[2]+"' ;", null);

        cincome.moveToFirst();
        int sincome = cincome.getInt(0);
        income.setText("Income              "+sincome);

        TextView expense = (TextView)findViewById(R.id.tvExpense);
        Cursor cexpense = db.rawQuery("SELECT SUM(ivalue) AS sum FROM income where itrans='-' AND iday='"+parts[0]+"' AND imonth = '"+m+"' AND iyear = '"+parts[2]+"' ;", null);

        cexpense.moveToFirst();
        int sexpense = cexpense.getInt(0);
        expense.setText("Expense            "+sexpense);

        TextView balance = (TextView)findViewById(R.id.tvBalance);
        int sum = sincome-sexpense;

        if(sincome>sexpense){
            balance.setText("Balance            +"+sum);
        }
        else if(sexpense>sincome){
            balance.setText("Balance           "+sum);
        }
        else{
            balance.setText("Balance           "+sum);
        }
    }
    public String convertMonth(String month){
        if(month.equals("01"))
            return "January";
        else if(month.equals("02"))
            return "February";
        else if(month.equals("03"))
            return "March";
        else if(month.equals("04"))
            return "April";
        else if(month.equals("05"))
            return "May";
        else if(month.equals("06"))
            return "June";
        else if(month.equals("07"))
            return "July";
        else if(month.equals("08"))
            return "August";
        else if(month.equals("09"))
            return "September";
        else if(month.equals("10"))
            return "October";
        else if(month.equals("11"))
            return "November";
        else if(month.equals("12"))
            return "December";
        return "";
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
                    "SELECT _id,date,(itemname||'                '||value)g FROM income;",
                    null);

            // update the adapter

            SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                    android.R.layout.simple_list_item_2, // A textview
                    cursor, // cursor to a data collection
                    new String[] {"date" , "g"}, // column to be displayed
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
