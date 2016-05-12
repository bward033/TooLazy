package com.example.epiphanybox.toolazy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Bryan on 5/10/16.
 */
public class AcceptTask  extends AppCompatActivity {

    private TextView TextViewID;
    private TextView TextViewTitle;
    private TextView TextViewAddress;
    private TextView TextViewPrice;
    private TextView TextViewZip;
    private TextView TextViewCity;


    private String task_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accept_task);

        Intent intent = getIntent();
        task_id = intent.getStringExtra(Config.Task_ID);

        TextViewTitle = (TextView) findViewById(R.id.TextViewTitle);
        TextViewCity = (TextView) findViewById(R.id.TextViewCity);
        TextViewPrice = (TextView) findViewById(R.id.TextViewPrice);
        TextViewAddress = (TextView) findViewById(R.id.TextViewAddress);
        TextViewZip = (TextView) findViewById(R.id.TextViewZip);

        assert  task_id != null;
        TextViewID.setText(task_id);


        //Setting listeners to button


        getTask();
    }

    private void getTask(){
        class GetTask extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(AcceptTask.this,"Fetching...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showTask(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                return rh.sendGetRequestParam(Config.URL_GET_TASK,task_id);
            }
        }
        GetTask ge = new GetTask();
        ge.execute();
    }

    private void showTask(String json){

        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String Title = c.getString(Config.TAG_TITLE);
            String Address = c.getString(Config.TAG_ADDRESS);
            String Price = c.getString(Config.TAG_PRICE);
            String Zip = c.getString(Config.TAG_ZIP);
            String City = c.getString(Config.TAG_CITY);


            TextViewTitle.setText(Title);
            TextViewAddress.setText(Address);
            TextViewPrice.setText(Price);
            TextViewZip.setText(Zip);
            TextViewCity.setText(City);


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



}


