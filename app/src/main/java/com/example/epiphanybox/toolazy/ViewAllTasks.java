package com.example.epiphanybox.toolazy;

/**
 * Created by EpiphanyBox on 4/19/16.
 *


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class ViewAllTasks extends AppCompatActivity implements ListView.OnItemClickListener {


        private ListView listView;

        private String JSON_STRING;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_view_all_tasks);
            listView = (ListView) findViewById(R.id.listView);
            listView.setOnItemClickListener(this);
            getJSON();
        }


        private void showEmployee(){
            JSONObject jsonObject = null;
            ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
            try {
                jsonObject = new JSONObject(JSON_STRING);
                JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);

                for(int i = 0; i<result.length(); i++){
                    JSONObject jo = result.getJSONObject(i);
                    String First_Name = jo.getString(Config.TAG_First_Name);
                    String Last_Name = jo.getString(Config.TAG_Last_Name);
                    String Email = jo.getString(Config.TAG_Email);
                    String Phone_Number = jo.getString(Config.TAG_Phone_Number);


                    HashMap<String,String> employees = new HashMap<>();
                    employees.put(Config.TAG_First_Name,First_Name);
                    employees.put(Config.TAG_Last_Name,Last_Name);
                    employees.put(Config.TAG_Email,Email);
                    employees.put(Config.TAG_Phone_Number,Phone_Number);
                    list.add(employees);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            ListAdapter adapter = new SimpleAdapter(
                    ViewAllTasks.this, list, R.layout.list_item,
                    new String[]{Config.TAG_First_Name,Config.TAG_Last_Name},
                    new int[]{R.id.First_Name, R.id.Last_Name});

            listView.setAdapter(adapter);
        }

        private void getJSON(){
            class GetJSON extends AsyncTask<Void,Void,String>{

                ProgressDialog loading;
                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    loading = ProgressDialog.show(ViewAllTasks.this,"Fetching Data","Wait...",false,false);
                }

                @Override
                protected void onPostExecute(String s) {
                    super.onPostExecute(s);
                    loading.dismiss();
                    JSON_STRING = s;
                    showEmployee();
                }

                @Override
                protected String doInBackground(Void... params) {
                    RequestHandler rh = new RequestHandler();
                    String s = rh.sendGetRequest(Config.URL_GET_ALL);
                    return s;
                }
            }
            GetJSON gj = new GetJSON();
            gj.execute();
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(this, ViewTask.class);
            HashMap<String,String> map =(HashMap)parent.getItemAtPosition(position);
            String empId = map.get(Config.TAG_ID).toString();
            intent.putExtra(Config.EMP_ID,empId);
            startActivity(intent);
        }
    }
 */