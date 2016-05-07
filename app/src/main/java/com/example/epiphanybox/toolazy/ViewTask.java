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

        private EditText editTextTitle;
        private EditText editTextDescription;
        private EditText editTextPrice;
        private EditText editTextCategory;

        private String id;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.view_task);

            Intent intent = getIntent();

            id = intent.getStringExtra(Config.TAG_TITLE_ID);

            editTextTitle = (EditText) findViewById(R.id.editTextTitle);
            editTextDescription = (EditText) findViewById(R.id.editTextDescription);
            editTextPrice = (EditText) findViewById(R.id.editTextPrice);
            editTextCategory = (EditText) findViewById(R.id.editTextCategory);



            editTextTitle.setText(id);

            getTask();
        }

        private void getTask(){
            class GetEmployee extends AsyncTask<Void,Void,String> {
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
                    String s = rh.sendGetRequestParam(Config.URL_GET_TASK,id);
                    return s;
                }
            }
            GetEmployee ge = new GetEmployee();
            ge.execute();
        }

        private void showEmployee(String json){
            try {
                JSONObject jsonObject = new JSONObject(json);
                JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);
                JSONObject c = result.getJSONObject(0);
                String First_Name = c.getString(Config.TAG_TITLE);
                String Last_Name = c.getString(Config.TAG_DESCRIPTION);
                String Phone_Number = c.getString(Config.TAG_PRICE);
                String Email = c.getString(Config.TAG_CATOGORY);

                editTextTitle.setText(First_Name);
                editTextDescription.setText(Last_Name);
                editTextPrice.setText(Phone_Number);
                editTextCategory.setText(Email);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


    }


