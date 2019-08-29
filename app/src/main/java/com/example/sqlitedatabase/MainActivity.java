package com.example.sqlitedatabase;

import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
    SQLiteDatabase sqLiteDatabase;

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
        sqLiteDatabase=openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);
        sqLiteDatabase.execSQL("Create Table IF Not Exists Student(rollno VARCHAR,name VARCHAR,marks VARCHAR);");


    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){

            case R.id.buttonadd:
                if (editText.getText().toString().trim().length()==0||editText1.getText().toString().trim().length()==0){
                    Toast.makeText(this, "Invaild input", Toast.LENGTH_SHORT).show();
                    shwmsg("Error","Invaild input");
                    return;
                }
                sqLiteDatabase.execSQL("INSERT INTO student VALUES('"+editText.getText()+"','"+editText1.getText()+
                        "','"+editText2.getText()+"');");
                shwmsg("Success", "Record added");

                clearText();
                Toast.makeText(this, "add", Toast.LENGTH_SHORT).show();
                break;




            case R.id.buttondel:
                if(editText.getText().toString().trim().length()==0)
                {
                    shwmsg("Error", "Please enter Rollno");
                    return;


                }
                Cursor c=sqLiteDatabase.rawQuery("SELECT * FROM student WHERE rollno='"+editText.getText()+"'", null);
                if(c.moveToFirst())
                {
                    sqLiteDatabase.execSQL("DELETE FROM student WHERE rollno='"+editText.getText()+"'");
                    shwmsg("Success", "Record Deleted");
                }
                else
                {
                    shwmsg("Error", "Invalid Rollno");
                }
                clearText();
                break;

            case R.id.buttonmodify:

                if(editText1.getText().toString().trim().length()==0)
                {
                    shwmsg("Error", "Please enter Rollno");
                    return;
                }
                Cursor c1=sqLiteDatabase.rawQuery("SELECT * FROM student WHERE rollno='"+editText.getText()+"'", null);
                if(c1.moveToFirst())
                {
                    sqLiteDatabase.execSQL("UPDATE student SET name='"+editText1.getText()+"',marks='"+editText2.getText()+
                            "' WHERE rollno='"+editText.getText()+"'");
                    shwmsg("Success", "Record Modified");
                }
                else
                {
                    shwmsg("Error", "Invalid Rollno");
                }
                clearText();
                break;

            case R.id.buttonshow:
                Toast.makeText(this, "SHOW", Toast.LENGTH_SHORT).show();
                shwmsg("Developed By-","Ms.Shubhangi D K" );
                break;

            case R.id.buttonview:
                //Toast.makeText(this, "VIEW", Toast.LENGTH_SHORT).show();
                if(editText.getText().toString().trim().length()==0)
                {
                    shwmsg("Error", "Please enter Rollno");
                    return;
                }
                Cursor c2=sqLiteDatabase.rawQuery("SELECT * FROM student WHERE rollno='"+editText.getText()+"'", null);
                if(c2.moveToFirst())
                {
                    editText1.setText(c2.getString(1));
                    editText2.setText(c2.getString(2));
                }
                else
                {
                    shwmsg("Error", "Invalid Rollno");
                    clearText();
                }

                break;

            case R.id.buttonviewall:
                Cursor c3=sqLiteDatabase.rawQuery("SELECT * FROM student", null);
                if(c3.getCount()==0)
                {
                    shwmsg("Error", "No records found");
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while(c3.moveToNext())
                {
                    buffer.append("Rollno: "+c3.getString(0)+"\n");
                    buffer.append("Name: "+c3.getString(1)+"\n");
                    buffer.append("Marks: "+c3.getString(2)+"\n\n");
                }
                shwmsg("Student Details", buffer.toString());
        }

            shwmsg("Student Record Application", "Developed By Chandan Prasad");



    }

    private void clearText() {
        editText.setText("");
        editText1.setText("");
        editText2.setText("");

    }

    private void shwmsg(String title, String msg) {
        AlertDialog.Builder alertDialog=new AlertDialog.Builder(this);
        alertDialog.setCancelable(true);
        alertDialog.setTitle(title);
        alertDialog.setMessage(msg);
        alertDialog.setIcon(R.mipmap.ic_launcher_round);
        alertDialog.show();



    }
}
