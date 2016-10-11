package com.tom.atm;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    private static final String TAG = "AddActivity";
    private EditText edDate;
    private EditText edInfo;
    private EditText edAmount;
    private MyDBHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        findViews();

        helper = new MyDBHelper(this,"expense.db",null,1);
    }

    private void findViews() {
        edDate = (EditText)  findViewById(R.id.ed_date);
        edInfo = (EditText) findViewById(R.id.ed_info);
        edAmount = (EditText) findViewById(R.id.ed_amount);
    }

    public void add(View v){
        String cdate = edDate.getText().toString();
        String cinfo = edInfo.getText().toString();
        String camount = edAmount.getText().toString();
        ContentValues values = new ContentValues();
        values.put("cdate",cdate);
        values.put("info",cinfo);
        values.put("amount",camount);
        long id = helper.getWritableDatabase().insert("exp",null,values);
        Log.d(TAG, String.valueOf(id));
    }
}
