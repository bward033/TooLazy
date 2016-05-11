package com.example.epiphanybox.toolazy;



    import android.app.ProgressDialog;
    import android.content.DialogInterface;
    import android.content.Intent;
    import android.os.AsyncTask;
    import android.support.v7.app.AlertDialog;
    import android.support.v7.app.AppCompatActivity;
    import android.os.Bundle;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.Button;
    import android.widget.EditText;
    import android.widget.TextView;
    import android.widget.Toast;

    import com.google.android.gms.common.server.converter.StringToIntConverter;

    import org.json.JSONArray;
    import org.json.JSONException;
    import org.json.JSONObject;

    import java.util.HashMap;

    public class ViewTask extends AppCompatActivity implements View.OnClickListener{

        private TextView TextViewID;
        private TextView TextViewTitle;
        private TextView TextViewDescription;
        private TextView TextViewPrice;
        private TextView TextViewCategory;

        private String task_id;
        public String tid;
        private Button button4;
        private Button button5;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.view_task);

            Intent intent = getIntent();
            task_id = intent.getStringExtra(Config.Task_ID);

            TextViewID = (TextView) findViewById(R.id.TextViewID);
            TextViewTitle = (TextView) findViewById(R.id.TextViewTitle);
            TextViewDescription = (TextView) findViewById(R.id.TextViewDescription);
            TextViewPrice = (TextView) findViewById(R.id.TextViewPrice);
            TextViewCategory = (TextView) findViewById(R.id.TextViewCategory);



            TextViewID.setText(task_id);
            button4 = (Button) findViewById(R.id.button4);
            button5 = (Button) findViewById(R.id.button5);


            //Setting listeners to button

            assert button4 != null;
            button4.setOnClickListener(this);
            assert button5 !=null;
            button5.setOnClickListener(this);


            getTask();
        }

        private void getTask(){
            class GetTask extends AsyncTask<Void,Void,String> {
                ProgressDialog loading;
                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    loading = ProgressDialog.show(ViewTask.this,"Fetching...","Wait...",false,false);
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
                    tid = task_id;
                    int id = Integer.parseInt(tid);
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
                String Description = c.getString(Config.TAG_DESCRIPTION);
                String Price = c.getString(Config.TAG_PRICE);
                String Category = c.getString(Config.TAG_CATOGORY);

                TextViewTitle.setText(Title);
                TextViewDescription.setText(Description);
                TextViewPrice.setText(Price);
                TextViewCategory.setText(Category);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        @Override
        public void onClick(View v) {
            if (v == button4) {
                startActivity(new Intent(this, ViewAllTasks.class));
            }
            if (v == button5) {
                startActivity(new Intent(this, AcceptTask.class));
            }
        }


    }


