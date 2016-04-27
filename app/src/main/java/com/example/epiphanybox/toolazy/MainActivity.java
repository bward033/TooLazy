package com.example.epiphanybox.toolazy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //Defining views
    private EditText editTextFirst_Name;
    private EditText editTextLast_Name;
    private EditText editTextBirth_date;
    private EditText editTextPhone_Number;
    private EditText editTextEmail;

    private Button buttonAdd;
    private Button buttonView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializing views
        editTextFirst_Name = (EditText) findViewById(R.id.editTextFirst_Name);
        editTextLast_Name = (EditText) findViewById(R.id.editTextLast_Name);
        editTextBirth_date = (EditText) findViewById(R.id.editTextBirth_date);
        editTextPhone_Number = (EditText) findViewById(R.id.editTextPhone_Number);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);

        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonView = (Button) findViewById(R.id.buttonView);

        //Setting listeners to button
        buttonAdd.setOnClickListener(this);
        buttonView.setOnClickListener(this);
    }


    //Adding an employee
    private void addAccount(){

        final String First_Name = editTextFirst_Name.getText().toString().trim();
        final String Last_Name = editTextLast_Name.getText().toString().trim();
        final String Birth_date = editTextBirth_date.getText().toString().trim();
        final String Phone_Number = editTextPhone_Number.getText().toString().trim();
        final String Email = editTextEmail.getText().toString().trim();

        class AddAccount extends AsyncTask<Void,Void,String>{

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MainActivity.this,"Adding...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(MainActivity.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Config.KEY_First_Name,First_Name);
                params.put(Config.KEY_Last_Name,Last_Name);
                params.put(Config.KEY_Birth_date,Birth_date);
                params.put(Config.KEY_Phone_Number,Phone_Number);
                params.put(Config.KEY_Email,Email);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.URL_ADD, params);
                return res;
            }
        }

        AddAccount ae = new AddAccount();
        ae.execute();
    }

    @Override
    public void onClick(View v) {
        if(v == buttonAdd){
            addAccount();
        }

        if(v == buttonView){
            startActivity(new Intent(this,ViewAllTasks.class));
        }
    }
}