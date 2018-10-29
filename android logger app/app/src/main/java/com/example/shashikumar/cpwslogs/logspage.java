package com.example.shashikumar.cpwslogs;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Toolbar;
import  android.view.Menu;
import java.util.ArrayList;
import java.util.Date;

public class logspage extends AppCompatActivity {

private dbhelper helper;
private SQLiteDatabase mSqLiteDb;
ArrayList dblogs = new ArrayList();
ArrayList fetcheddblogs = new ArrayList();
ArrayList filteredlogs = new ArrayList();
    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logspage);

        //databse

        dbhelper helper = new dbhelper(logspage.this, "logs", null, 1);

        mSqLiteDb = helper.getWritableDatabase();


        //databse creating cursor to read
        Cursor c  = mSqLiteDb.query("logs",null,null,null,null,null,null);

        //reading from databse
        while (c.moveToNext()) {

            fetcheddblogs.add(c.getString(c.getColumnIndex("shedname")) + " " +
                    c.getString(c.getColumnIndex("temperature")) + " " +
                    c.getString(c.getColumnIndex("humidity")) + " " +
                    c.getString(c.getColumnIndex("ammonia")) + " " +
                    c.getString(c.getColumnIndex("treatment")) + " " +
                    c.getString(c.getColumnIndex("date")));

        }


        Bundle extras = getIntent().getExtras();
       ArrayList<String> logsarray = (ArrayList<String>) extras.getStringArrayList("logdata");
       //copy
        dblogs = logsarray;

        for (int i=0;i<logsarray.size();i++) {
            filteredlogs.add(logsarray.get(i) +" " + logsarray.get(i + 1) + " " + logsarray.get(i+2) + " " + logsarray.get(i+3) + " "+logsarray.get(i+4) + " " + logsarray.get(i+5));
            i=i+5;
        }
        for(int i=0;i<fetcheddblogs.size();i++) {
            filteredlogs.add(fetcheddblogs.get(i));
        }

//        Log.e("fetched", String.valueOf(fetcheddblogs));


        final ListView listview =(ListView) findViewById(R.id.logs);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,filteredlogs);
        listview.setAdapter(adapter);

    }

    public  boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.popover_menu,menu);
        return  true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch ((menuItem.getItemId())){
            case  R.id.save:
                savelogs();
                return true;
            case  R.id.send:
                sendlogs();
                return true;
            case  R.id.profile:
                profilepage();
                return true;

                default:
                    return super.onOptionsItemSelected(menuItem) ;
        }

    }

    public void sendlogs() {

            Toast.makeText(this, "Logs  sent to server", Toast.LENGTH_SHORT).show();

    }
    public void profilepage() {
        Intent profilepage =  new Intent(logspage.this,profiles.class);
        startActivity(profilepage);

    }

    public void savelogs() {


        Log.e("fetched", String.valueOf(dblogs));
        for(int i=0;i<dblogs.size();i++) {
            ContentValues cv =  new ContentValues();
            cv.put("shedname", dblogs.get(i).toString());
            cv.put("temperature", dblogs.get(i+1).toString());
            cv.put("humidity",dblogs.get(i+2).toString());
            cv.put("ammonia",dblogs.get(i+3).toString());
            cv.put("treatment", dblogs.get(i+4).toString());
            cv.put("date", dblogs.get(i+5).toString());
            long id  = mSqLiteDb.insert("logs", null,cv);
            i=i+5;
        }




        Toast.makeText(this, "saved to database", Toast.LENGTH_SHORT).show();


        return;
    }


}
