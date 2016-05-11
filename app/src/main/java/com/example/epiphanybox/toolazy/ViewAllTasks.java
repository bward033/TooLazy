package com.example.epiphanybox.toolazy;

/**
 * Created by Cody-McCants on 5/5/2016.
 */

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ViewAllTasks extends AppCompatActivity implements ListView.OnItemClickListener {

    private ListView listView;

    public String JSON_STRING;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_tasks);
        listView = (ListView) findViewById(R.id.listView);
        assert listView != null;
        listView.setOnItemClickListener(this);

        final FloatingActionButton myFAB = (FloatingActionButton) findViewById(R.id.myFAB);
        assert myFAB != null;
        myFAB.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(v == myFAB) {
                        startAct();
                    }
                }
        });
        final FloatingActionButton myMap = (FloatingActionButton) findViewById(R.id.search);
        assert myMap  != null;
        myMap.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(v == myMap){
                    startmap();
                }
            }
        });
        getJSON();
    }

    private void startAct() {
        startActivity(new Intent(this, AddTask.class));
    }
    private void startmap(){
        startActivity(new Intent(this, MapsActivity.class));
    }


    private void showEmployee(){
        JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String task_id = jo.getString(Config.TAG_Task_ID);
                String Title = jo.getString(Config.TAG_TITLE);


                HashMap<String,String> employees = new HashMap<>();
                employees.put(Config.TAG_Task_ID,task_id);
                employees.put(Config.TAG_TITLE,Title);
                list.add(employees);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                ViewAllTasks.this, list, R.layout.list_item,
                new String[]{Config.TAG_Task_ID,Config.TAG_TITLE},
                new int[]{R.id.task_id,R.id.Title});

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
                String s = rh.sendGetRequest(Config.URL_ALL_TASKS);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, ViewTask.class);
        HashMap map =(HashMap)parent.getItemAtPosition(position);
        String Task_ID = map.get(Config.TAG_Task_ID).toString();
        intent.putExtra(Config.Task_ID,Task_ID);
        startActivity(intent);
    }
}
