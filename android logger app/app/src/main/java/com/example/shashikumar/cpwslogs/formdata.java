package com.example.shashikumar.cpwslogs;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

public class formdata extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //Button declarations
    Button savebtn;
    Button previousbtn;
    Button nexbtn;
    Button homebtn;
    Button getlogsbtn;

    public String shed;



    //text values


    EditText temperatare;
    EditText humidity;
    EditText ammonia;
    public String treatmant;

    ArrayList localstore = new ArrayList();

logspage logspage = new logspage();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formdata);

        String shedvalue = getIntent().getStringExtra("shedvalue");
        TextView textView =(TextView) findViewById(R.id.shedvalue);
         shed = shedvalue;
        //set text value as heading
        textView.setText(shedvalue);

        //spuinerr values load

        Spinner spinner = findViewById(R.id.treatmentlist);
        ArrayAdapter<CharSequence> spinneradapter = ArrayAdapter.createFromResource(this,R.array.treatmentvalues,android.R.layout.simple_spinner_item);
        spinneradapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter((spinneradapter));
        spinner.setOnItemSelectedListener(this);



        //navigation buttons on form page
        savebtn = (Button) findViewById(R.id.savebtn);
        previousbtn = (Button) findViewById(R.id.previous);
        nexbtn = (Button) findViewById(R.id.next);
        homebtn = (Button) findViewById(R.id.home);
        getlogsbtn =findViewById(R.id.getlogs);

        //find elements
        temperatare = findViewById(R.id.tempinput);
        humidity = findViewById(R.id.humidityinput);
        ammonia = findViewById(R.id.ammoniainput);

        //menu

        previousbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mainpage();
            }

            private void mainpage() {

                Intent page = new Intent(formdata.this,MainActivity.class);
                startActivity(page);
            }
        });
        homebtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mainpage();
            }

            private void mainpage() {

                Intent page = new Intent(formdata.this,MainActivity.class);
                startActivity(page);
            }
        });

        nexbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                logspage();
            }

            private void logspage() {

                Intent page = new Intent(formdata.this,logspage.class);
                page.putExtra("logdata", localstore);
                startActivity(page);
            }
        });


        //save logs

        savebtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                savedata();
            }

            private void savedata() {
                String tempvalue = temperatare.getText().toString();
                String humidityvalue = humidity.getText().toString();
                String ammoniavalue = ammonia.getText().toString();
                String currentdate =new Date().toString();


                if(tempvalue ==" " || humidityvalue ==" " || ammoniavalue =="") {

                        showerror();
                        return ;

                }



                    localstore.add(shed);
                    localstore.add(tempvalue);
                    localstore.add(humidityvalue);
                    localstore.add(ammoniavalue);
                    localstore.add(treatmant);
                    localstore.add(currentdate);
                    Log.e("localdata", String.valueOf(localstore));
                    confirmation();


            }
        });


        getlogsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getlogdata();
            }
        });
    }

    private void showerror() {

            Toast.makeText(this, "Log not saved , Enter all input fields ", Toast.LENGTH_SHORT).show();
            return ;
    }

    private void confirmation() {
        Toast.makeText(this, "Entry  saved", Toast.LENGTH_SHORT).show();
    }

    private void getlogdata() {

        Intent intent = new Intent(formdata.this,logspage.class);
        intent.putExtra("logdata", localstore);
        startActivity(intent);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int k, long l) {
        treatmant = adapterView.getItemAtPosition(k).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    //menu pop over

//    logspage logspage= new logspage();

    public  boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.popover_menu,menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch ((menuItem.getItemId())){
            case  R.id.save:
                logspage.savelogs();
                return true;
            case  R.id.send:
                logspage.sendlogs();
                return true;
            case  R.id.profile:
                logspage.profilepage();
                return true;

            default:
                return super.onOptionsItemSelected(menuItem) ;
        }

    }
    public void sendlogs() {
            Toast.makeText(this, "Logs  sent", Toast.LENGTH_SHORT).show();

    }
    public void profilepage() {

    }

    public void savelogs() {
        Toast.makeText(this, "save", Toast.LENGTH_SHORT).show();
        return;
    }
}
