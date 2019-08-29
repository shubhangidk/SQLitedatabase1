package com.example.sqlitedatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText editText,editText1,editText2;
    Button buttonadd,buttondelete,buttonModify,buttonview,buttonviewall,buttonshow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText=findViewById(R.id.editRollno);
        editText1=findViewById(R.id.editName);
        editText2=findViewById(R.id.editmarks);

        buttonadd=findViewById(R.id.buttonadd);
        buttonModify=findViewById(R.id.buttonmodify);
        buttonview=findViewById(R.id.buttonview);
        buttonviewall=findViewById(R.id.buttonviewall);
        buttonshow=findViewById(R.id.buttonshow);
        buttondelete=findViewById(R.id.buttondel);


        buttonadd.setOnClickListener(this);
        buttondelete.setOnClickListener(this);
        buttonshow.setOnClickListener(this);
        buttonviewall.setOnClickListener(this);
        buttonModify.setOnClickListener(this);
        buttonview.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){

            case R.id.buttonadd:
                Toast.makeText(this, "ADD", Toast.LENGTH_SHORT).show();
                break;

            case R.id.buttondel:
                Toast.makeText(this, "DELETE", Toast.LENGTH_SHORT).show();
                break;

            case R.id.buttonmodify:
                Toast.makeText(this, "MODIFY", Toast.LENGTH_SHORT).show();
                break;

            case R.id.buttonshow:
                Toast.makeText(this, "SHOW", Toast.LENGTH_SHORT).show();
                break;

            case R.id.buttonview:
                Toast.makeText(this, "VIEW", Toast.LENGTH_SHORT).show();
                break;

            case R.id.buttonviewall:
                Toast.makeText(this, "VIEWALL", Toast.LENGTH_SHORT).show();
                break;



        }

    }
}
