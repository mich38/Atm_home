package com.tom.atm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import static com.tom.atm.R.id.grid;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private static final String TAG = "MainActivity";
    boolean logon = false;
    public static final int FUNC_LOGIN = 1;
    private Spinner spinner;
    //String[] func = {getString(R.string.amount_search),getString(R.string.detail),getString(R.string.news),getString(R.string.invest),getString(R.string.leave)};
    // String[] func = {"aaaa","bbbb","cccc","dddd","eeee"};
    //String test = getString(R.string.test);
    String[] func = {"餘額查詢","交易明細","最新消息","投資理財","離開"};

    int[] icons = {R.drawable.func_balance,
            R.drawable.func_history,
            R.drawable.func_news,
            R.drawable.func_finance,
            R.drawable.func_exit
    };

    class IconAdapter extends BaseAdapter{

        int style;
        public IconAdapter(int style){
            this.style = style;
        }
        @Override
        public int getCount() {
            return func.length;
        }

        @Override
        public Object getItem(int position) {
            return func[position];
        }

        @Override
        public long getItemId(int position) {
            return icons[position];
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;
            if (row == null){
                row = getLayoutInflater().inflate(style,null);
                ImageView iv = (ImageView) row.findViewById(R.id.item_image);
                TextView tv = (TextView) row.findViewById(R.id.item_text);
                iv.setImageResource(icons[position]);
                tv.setText(func[position]);
            }
            return row;
        }
    }

     @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == FUNC_LOGIN){
            if (resultCode == RESULT_OK){
                String uid = data.getStringExtra("LOGIN_USERID");
                String pwd = data.getStringExtra("LOGIN_PASSWD");
                Log.d("RESULT", uid + "/" + pwd);

            }
        }
    }

   
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //forListView();

        //forSpinner();

        //forGridView();
        //Log.d(TAG,"test====>" + test);

       // GridView grid = (GridView) findViewById(R.id.grid);
        ListView list = (ListView) findViewById(R.id.list);
        IconAdapter gAdapter = new IconAdapter(R.layout.item_rows_horizen);
        list.setAdapter(gAdapter);

        list.setOnItemClickListener(this);
       // grid.setAdapter(gAdapter);

        /*grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch ((int) id){
                    case R.drawable.func_balance:
                        break;
                    case R.drawable.func_history:
                        break;
                    case R.drawable.func_news:
                        break;
                    case R.drawable.func_finance:
                        break;
                    case R.drawable.func_exit:
                        Log.d(TAG,"exit");
                        finish();
                        break;
                }
            }
        });*/

        if(!logon){
            Intent intent = new Intent(this, LoginActivity.class);
            //startActivity(intent);
            startActivityForResult(intent,FUNC_LOGIN);
        }
    }

    private void forGridView() {
        GridView grid = (GridView) findViewById(R.id.grid);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,R.array.funcs,android.R.layout.simple_list_item_1);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(this);
    }

    private void forSpinner() {
        spinner = (Spinner) findViewById(R.id.spinner);
        final ArrayAdapter adapter = ArrayAdapter.createFromResource(this,R.array.notify_array,android.R.layout.simple_list_item_1);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG,adapter.getItem(position).toString());
                Log.d(TAG, String.valueOf(adapter.getItemId(position)));
                Log.d(TAG,spinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void forListView() {
        String[] func = {getString(R.string.amount_search),getString(R.string.detail),getString(R.string.news),getString(R.string.invest),getString(R.string.leave)};
        ListView list = (ListView) findViewById(R.id.list);
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, func);
        list.setAdapter(adapter);
    }

    public void getData(View v){
        String selectItem = spinner.getSelectedItem().toString();
        TextView tv = (TextView) findViewById(R.id.spinnerText);
        tv.setText(selectItem);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d(TAG, String.valueOf(id));
        Log.d(TAG, String.valueOf(position));
        switch ((int) id){
            case R.drawable.func_balance:
                break;
            case R.drawable.func_history:
                break;
            case R.drawable.func_news:
                break;
            case R.drawable.func_finance:
                startActivity(new Intent(this,FinanceActivity.class));
                break;
            case R.drawable.func_exit:
                finish();
                break;
        }


        /*switch (position){
            case 0:
                    break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                finish();
                break;
        }*/
    }
}
