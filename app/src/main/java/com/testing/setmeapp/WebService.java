package com.testing.setmeapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Glenwin18 on 12/4/2016.
 */
public class WebService {
    String result="";
    //ProgressDialog progressDialog;

    public void add_alter_delete(final String urlPass, final Context myActivity){
        class Add_Alter_Delete_Data extends AsyncTask<String, Void, String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
//                progressDialog = new ProgressDialog(myActivity);
//                progressDialog.setMessage("Adding...");
//                progressDialog.setIndeterminate(false);
//                progressDialog.setCancelable(false);
//                progressDialog.show();
            }

            @Override
            protected String doInBackground(String... params) {

                BufferedReader bufferedReader;

                try {
                    URL url = new URL(urlPass);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }
                    result = sb.toString();
                } catch (Exception e) {
                    Log.e("log_tag", "Error converting result" + e.toString());
                }
                return result;
            }

            @Override
            protected void onPostExecute(String result) {
                //progressDialog.dismiss();
                Toast.makeText(myActivity,"Added",Toast.LENGTH_SHORT).show();
            }
        }
        Add_Alter_Delete_Data alter_delete_data = new Add_Alter_Delete_Data();
        alter_delete_data.execute();
    }
}
