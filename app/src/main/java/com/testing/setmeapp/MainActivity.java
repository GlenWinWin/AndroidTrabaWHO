package com.testing.setmeapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.testing.setmeapp.host.Host;

public class MainActivity extends AppCompatActivity {
    Host host = new Host();
    WebService myWebServiceObject;
    String realHost = host.returnHost();
    private String add_data_url = "http://"+realHost+"/csv/survey.php";
    EditText edName;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myWebServiceObject = new WebService(getApplicationContext());

        edName = (EditText)findViewById(R.id.edName);
        btn = (Button)findViewById(R.id.btnPress);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addData();
            }
        });

    }
    public void doThis(View view){
        startActivity(new Intent(getApplicationContext(),ListActivity.class));
    }
    private void addData(){
        myWebServiceObject.dataManipulation(add_data_url+"?name="+edName.getText(),"text to show");
    }
}
