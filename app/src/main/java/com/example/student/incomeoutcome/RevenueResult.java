package com.example.student.incomeoutcome;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;


public class RevenueResult extends ActionBarActivity{

    MyDBHelper helper;
    SimpleCursorAdapter adapter;
    long selectedId;
    ActionMode actionMode;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.revenueresult);

        helper = new MyDBHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();
        cursor = db.rawQuery("SELECT _id,(iday||'  '||imonth||'  '||iyear)d,(iname||'                '||itrans||' '||ivalue)g FROM income ORDER BY _id DESC;", null);

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_2, // A textview
                cursor, // cursor to a data collection
                new String[] {"d" , "g"}, // column to be displayed
                new int[] {android.R.id.text1 , android.R.id.text2}, // ID of textview to display
                0);

        ListView lv = (ListView)findViewById(R.id.listView);
        lv.setAdapter(adapter);
        db.close();
    }




    public void buttonRevenue(View v) {
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
