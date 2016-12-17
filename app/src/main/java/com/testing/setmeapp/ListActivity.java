package com.testing.setmeapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.testing.setmeapp.host.Host;

import java.util.ArrayList;
import java.util.HashMap;

public class ListActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<HashMap<String, String>> lIsT;
    Host host = new Host();
    WebService myWebServiceObject;
    String realHost = host.returnHost();
    private String fetch_data_url = "http://"+realHost+"/csv/fetch.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //object for the WebService class
        myWebServiceObject = new WebService(getApplicationContext());

        //listView in this activity if a fragment the one that needs to be inflate
        listView = (ListView)findViewById(R.id.listView);

        //arrayList for the storage of values
        lIsT = new ArrayList<>();

        //array that holds the ids of your element
        int[] tvIDS = {R.id.name,R.id.age};

        //array for tag names
        String[] tags = {"name_tag","age_tag"};

        //values in the database that will fetch
        String[] valuesToFetch = {"name","age"};

        myWebServiceObject.fetchData(fetch_data_url,listView,lIsT,R.layout.list_item,tvIDS,tags,valuesToFetch);
    }
}
