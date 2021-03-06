package ro.pub.cs.systems.eim.practicaltest01var04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PracticalTest01Var04MainActivity extends AppCompatActivity {

    private EditText nume, grupa;
    private Button navigate, display;
    private CheckBox check1, check2;
    private TextView text;

    private MessageReceiver messageReceiver = new MessageReceiver();
    private IntentFilter intentFilter = new IntentFilter();

    private class MessageReceiver extends BroadcastReceiver
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            Log.d(Constants.TAG, "am primit mesajul");
            Log.d(Constants.TAG, intent.getStringExtra(Constants.BROADCAST_TAG));
        }
    }


    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.display:
                    if (check1.isChecked() && check2.isChecked()){
                        if(nume.getText().toString().equals("") || grupa.getText().toString().equals("")){
                            Toast.makeText(PracticalTest01Var04MainActivity.this, "necompletat! ", Toast.LENGTH_LONG).show();
                        } else {
                            text.setText(nume.getText().toString() + " " + grupa.getText().toString());
                            Intent intent1 = new Intent(getApplicationContext(), PracticalTest01Var04Service.class);
                            intent1.putExtra(Constants.second_nume, nume.getText().toString());
                            intent1.putExtra(Constants.second_grupa, grupa.getText().toString());
                            getApplicationContext().startService(intent1);
                            Log.d(Constants.TAG, "mesajjjjj");
                        }
                    }

                    break;
                case R.id.navigate:
                    Intent intent = new Intent(getApplicationContext(), PracticalTest01Var04SecondaryActivity.class);
                    intent.putExtra(Constants.second_nume, nume.getText().toString());
                    intent.putExtra(Constants.second_grupa, grupa.getText().toString());
                    startActivityForResult(intent, 2000);
                    break;
            }




        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nume = (EditText)findViewById(R.id.name);
        grupa = (EditText)findViewById(R.id.grupa);
        display = (Button)findViewById(R.id.display);
        navigate = (Button)findViewById(R.id.navigate);
        check1 = (CheckBox)findViewById(R.id.checkBox1);
        check2 = (CheckBox)findViewById(R.id.checkBox2);
        text = (TextView)findViewById(R.id.text);

        display.setOnClickListener(buttonClickListener);
        navigate.setOnClickListener(buttonClickListener);

        intentFilter.addAction(Constants.INTENT_ACTION);
        if (savedInstanceState != null) {
            Log.d(Constants.TAG, "onCreate() method was invoked with a previous state");
            if(savedInstanceState.containsKey(Constants.SAVED)){
                text.setText(savedInstanceState.getString(Constants.SAVED));
            }
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(Constants.TAG, "onStart() method was invoked");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(Constants.TAG, "onStop() method was invoked");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(Constants.TAG, "onDestroy() method was invoked");
    }

    @Override
    public  void onRestart() {
        super.onRestart();
        Log.d(Constants.TAG, "onRestart() method was invoked");
    }

    @Override
    public  void onResume() {
        super.onResume();
        registerReceiver(messageReceiver, intentFilter);
        Log.d(Constants.TAG, "onResume() method was invoked");
    }

    @Override
    public  void onPause() {
        super.onPause();
        unregisterReceiver(messageReceiver);
        Log.d(Constants.TAG, "onPause() method was invoked");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(Constants.TAG, "saveeee");
        outState.putString(Constants.SAVED, text.getText().toString());

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(Constants.TAG, "restoreee");
        if (savedInstanceState.containsKey(Constants.SAVED)) {
            text.setText(savedInstanceState.getString(Constants.SAVED));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == 2000) {
            Toast.makeText(this, "The activity returned with result " + resultCode, Toast.LENGTH_LONG).show();
        }
    }
}