package com.tom.atm;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static android.content.Context.MODE_PRIVATE;

public class LoginActivity extends AppCompatActivity {

    private EditText edUser;
    private EditText edPasswd;
    private String uid;
    private String pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SharedPreferences setting = getSharedPreferences("atm",MODE_PRIVATE);
        findViews();
        edUser.setText(setting.getString("PREF_USERID",""));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void findViews() {
        edUser = (EditText) findViewById(R.id.userid);
        edPasswd = (EditText) findViewById(R.id.passwd);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void login(View v){
        uid = edUser.getText().toString();
        pwd = edPasswd.getText().toString();
        Log.d("login",uid);
        Log.d("login",pwd);
        if (uid.equals("jack") && pwd.equals("1234")){
            SharedPreferences setting = getSharedPreferences("atm",MODE_PRIVATE);
            setting.edit().
                    putString("PREF_USERID",uid)
                    .commit();

            Toast.makeText(this,"登入成功",Toast.LENGTH_LONG).show();
            Intent intent = getIntent();
            intent.putExtra("LOGIN_USERID",uid);
            intent.putExtra("LOGIN_PASSWD",pwd);
            setResult(RESULT_OK,intent);
            finish();
        }else{
            Log.d("login","fail");
            new AlertDialog.Builder(this)
                    .setTitle("ATM")
                    .setMessage("登入失敗")
                    .setPositiveButton("OK",null)
                    .show();
        }
    }

    public void cancel(View v){

    }
}
