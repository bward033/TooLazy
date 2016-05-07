package com.example.epiphanybox.toolazy;



    import android.app.ProgressDialog;
    import android.content.DialogInterface;
    import android.content.Intent;
    import android.os.AsyncTask;
    import android.support.v7.app.AlertDialog;
    import android.support.v7.app.AppCompatActivity;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.Button;
    import android.widget.EditText;
    import android.widget.Toast;

    import org.json.JSONArray;
    import org.json.JSONException;
    import org.json.JSONObject;

    import java.util.HashMap;

    public class ViewTask extends AppCompatActivity {

        private EditText editTextID;
        private EditText editTextTitle;
        private EditText editTextDescription;
        private EditText editTextPrice;
        private EditText editTextCategory;

        private String task_id;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.view_task);

            Intent intent = getIntent();
            task_id = intent.getStringExtra(Config.Task_ID);

            editTextID = (EditText) findViewById(R.id.editTextID);
            editTextTitle = (EditText) findViewById(R.id.editTextTitle);
            editTextDescription = (EditText) findViewById(R.id.editTextDescription);
            editTextPrice = (EditText) findViewById(R.id.editTextPrice);
            editTextCategory = (EditText) findViewById(R.id.editTextCategory);



            editTextID.setText(task_id);

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
                    showEmployee(s);
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

        private void showEmployee(String json){

            try {
                JSONObject jsonObject = new JSONObject(json);
                JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);
                JSONObject c = result.getJSONObject(0);
                String Title = c.getString(Config.TAG_TITLE);
                String Description = c.getString(Config.TAG_DESCRIPTION);
                String Price = c.getString(Config.TAG_PRICE);
                String Category = c.getString(Config.TAG_CATOGORY);

                editTextTitle.setText(Title);
                editTextDescription.setText(Description);
                editTextPrice.setText(Price);
                editTextCategory.setText(Category);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


    }


