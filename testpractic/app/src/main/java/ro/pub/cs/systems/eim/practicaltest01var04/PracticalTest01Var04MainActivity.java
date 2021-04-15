package ro.pub.cs.systems.eim.practicaltest01var04;

import androidx.appcompat.app.AppCompatActivity;

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
                        }
                    }
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


        if (savedInstanceState != null) {
            Log.d(Constants.TAG, "onCreate() method was invoked with a previous state");
            if(savedInstanceState.containsKey(Constants.SAVED)){
                text.setText(savedInstanceState.getString(Constants.SAVED));
            }
        }

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
}