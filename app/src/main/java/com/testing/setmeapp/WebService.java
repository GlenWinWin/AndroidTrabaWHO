package com.testing.setmeapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Glenwin18 on 12/4/2016.
 */
public class WebService {
    String result="";
    Context webServiceContext;
    public WebService(Context context){
        webServiceContext = context;
    }
    public void dataManipulation(final String urlThatPass, final String toast){
        class Add_Alter_Delete_Data extends AsyncTask<String, Void, String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected String doInBackground(String... params) {

                BufferedReader bufferedReader;

                try {
                    URL url = new URL(urlThatPass);
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
                Toast.makeText(webServiceContext, toast, Toast.LENGTH_SHORT).show();
            }
        }
        Add_Alter_Delete_Data alter_delete_data = new Add_Alter_Delete_Data();
        alter_delete_data.execute();

    }
    public void fetchData(final String urltobeFetch, final ListView lView, final ArrayList<HashMap<String, String>> arrayList, final int layoutID, final int[] textViewIds, final String[] tag_names, final String[] valuesToGet) {
        class GetDataJSON extends AsyncTask<String, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected String doInBackground(String... params) {

                BufferedReader bufferedReader;
                try {
                    URL url = new URL(urltobeFetch);
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
                showList(lView, arrayList, layoutID, textViewIds, tag_names, valuesToGet);
            }
        }
        GetDataJSON getDataJSON = new GetDataJSON();
        getDataJSON.execute();
    }

    protected void showList(ListView list,ArrayList<HashMap<String, String>> myList,int layoutID,int[] textViewIDS,String[] valueTAGS,String[] valueToFetchFromDB) {
        try {
            JSONArray jArray = new JSONArray(result);
            String[] valueFetch = new String[valueToFetchFromDB.length];
            if(jArray.length() > 0) {
                list.setEnabled(true);
                for (int i = 0; i < jArray.length(); i++) {
                    JSONObject c = jArray.getJSONObject(i);
                    for(int k=0;k<valueToFetchFromDB.length;k++){
                        valueFetch[k] = c.getString(valueToFetchFromDB[k]);
                    }
                    HashMap<String, String> temporaryList = new HashMap<>();
                    for(int j=0;j<valueTAGS.length;j++){
                        temporaryList.put(valueTAGS[j],valueFetch[j]);
                    }
                    myList.add(temporaryList);
                }
            }
            else{
                list.setEnabled(false);
            }
            ListAdapter adapter = new SimpleAdapter(webServiceContext, myList, layoutID,valueTAGS,textViewIDS);
            list.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
