package com.tom.atm;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

public class FinanceActivity extends AppCompatActivity {

    private static final String TAG = "FinanceActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finance);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        MyDBHelper helper = new MyDBHelper(this,"expense.db",null,1);

        //Cursor c = helper.getReadableDatabase().query("exp",null,"_id=?&cdate=?",new String[]{"1","2016-10-10"},null,null,null);
        /*Log.d(TAG,c.getColumnName(0));

        Log.d(TAG,c.getColumnCount()+"");
        Log.d(TAG,c.getCount()+"");*/
        //SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,R.id.)
        ListView list = (ListView) findViewById(R.id.list);
        String[] from = {"_id","cdate","info","amount"};
        int[] to = {R.id.item_id,R.id.item_date,R.id.item_info,R.id.item_amount};
        Cursor c = helper.getReadableDatabase().query("exp",null,null,null,null,null,null);
        /*Cursor c = helper.getReadableDatabase().query("exp",null,"_id=? and cdate=?",new String[]{"1","2016-10-10"},null,null,null);
         int rows_num = cursor.getCount();//取得資料表列數
        if(rows_num != 0) {
            cursor.moveToFirst();   //將指標移至第一筆資料
            for(int i=0; i<rows_num; i++) {
                sNote[i]=cursor.getString(0)+" "+cursor.getString(1);
                cursor.moveToNext();//將指標移至下一筆資料
            }
            }
         */
        Log.d(TAG,"count=>" + c.getCount()+"");
        c.moveToFirst();
        Log.d(TAG,"string=>"+ c.getString(1));
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,R.layout.finance_row,c,from,to,0);
        list.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FinanceActivity.this,AddActivity.class));
            }
        });
    }

}
