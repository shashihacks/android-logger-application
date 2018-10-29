package com.example.shashikumar.cpwslogs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import  android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Toolbar;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        final String listdata[] = new String[]{"Shed 0", "Shed 1", "Shed 2", "Shed 3","Shed 4"};

        final ListView listview =(ListView) findViewById(R.id.listdata);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listdata);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int k, long l) {
//                Toast.makeText(MainActivity.this, listdata[k] , Toast.LENGTH_SHORT).show();

                Intent formintent = new Intent(MainActivity.this,formdata.class);
                formintent.putExtra("shedvalue",listview.getItemAtPosition(k).toString());
                startActivity(formintent);


            }
        }) ;
    }


    logspage logspage= new logspage();

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
        Toast.makeText(this, "Return to display logs page to send", Toast.LENGTH_SHORT).show();
        return;
    }
    public void profilepage() {

    }

    public void savelogs() {
        Toast.makeText(this, "save", Toast.LENGTH_SHORT).show();
        return;
    }
}
