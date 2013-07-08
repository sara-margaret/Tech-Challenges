package mehfoud.helloworldandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import java.util.Calendar;


public class MainActivity extends Activity  {

    //Button button;
    public static final String PREFS = "examplePrefs";
    String mydate;
    String[] log = new String[50];
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (counter == 0){
            SharedPreferences settings = getSharedPreferences(PREFS, 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.clear();
            editor.commit();

        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button)findViewById(R.id.button);



        SharedPreferences settings = getSharedPreferences(PREFS, 0);
        boolean silent = settings.getBoolean("silentMode",false);
        //setSilent(silent);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v){
                Intent i = new Intent(getApplicationContext(), ActivityB.class);
                startActivity(i);
            }
        });
        // button.setOnClickListener(this);

    }

    @Override
    protected void onStop(){
        super.onStop();

        SharedPreferences settings = getSharedPreferences(PREFS,0);
        SharedPreferences.Editor editor = settings.edit();
        mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
        log[counter] = mydate;
        editor.putInt(log[counter], counter);
        counter++;
        //editor.putString(counter, log[counter]);
        //editor.putString(log[counter], counter+"");
        Log.d("VIVZ", ".............. logged");
        //editor.putBoolean("silentMode", mSilentMode);
        editor.commit();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);



    }


}
