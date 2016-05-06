package com.example.epiphanybox.toolazy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

/**
 * Created by Cody-McCants on 5/5/2016.
 */
public class AddTask extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextTitle;
    private EditText editTextDescription;
    private EditText editTextPrice;
    private EditText editTextCategory;

    private Button button4;
    private Button button5;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_a_post);

        //Initializing views
        editTextTitle = (EditText) findViewById(R.id.editTextTitle);
        editTextDescription = (EditText) findViewById(R.id.editTextDescription);
        editTextPrice = (EditText) findViewById(R.id.editTextPrice);
        editTextCategory = (EditText) findViewById(R.id.editTextCategory);

        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        //Setting listeners to button

        button4.setOnClickListener(this);
        button5.setOnClickListener(this);

    }


    //Adding an employee
    private void Addtask(){

        final String Title = editTextTitle.getText().toString().trim();
        final String Description = editTextDescription.getText().toString().trim();
        final String Price = editTextPrice.getText().toString().trim();
        final String Category = editTextCategory.getText().toString().trim();


        class AddAccount extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(AddTask.this,"Adding...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(AddTask.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Config.KEY_TITLE,Title);
                params.put(Config.KEY_DESCRIPTION,Description);
                params.put(Config.KEY_PRICE,Price);
                params.put(Config.KEY_CATOGORY,Category);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.URL_ADD_TASKS, params);
                return res;
            }
        }

        AddAccount ae = new AddAccount();
        ae.execute();



    }



    @Override
    public void onClick(View v) {
        if(v == button4){
            startActivity(new Intent(this,ViewAllTasks.class));
        }
        if(v == button5){
            Addtask();
            startActivity(new Intent(this,ViewAllTasks.class));
        }

    }
}
