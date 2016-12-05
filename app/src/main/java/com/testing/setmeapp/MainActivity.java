package com.testing.setmeapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.testing.setmeapp.host.Host;

public class MainActivity extends AppCompatActivity {
    Host host = new Host();
    WebService myObject = new WebService();
    String realHost = host.returnHost();
    private String add_data_url = "http://"+realHost+"/csv/survey.php";
    EditText edName;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edName = (EditText)findViewById(R.id.edName);
        btn = (Button)findViewById(R.id.btnPress);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addData();
            }
        });
    }
    private void addData(){
        myObject.add_alter_delete(add_data_url+"?name="+edName.getText(),getApplicationContext());
    }
}
