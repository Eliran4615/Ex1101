package com.example.ex1101;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.assist.AssistStructure;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    Button exit,count,reset;
    TextView num;
    EditText name;
    int sum = 0;

    SharedPreferences save;
    SharedPreferences.Editor editor;
    int counter1 = 0;
    String Name;
    Intent credits;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        exit = (Button) findViewById(R.id.exit);
        count = (Button) findViewById(R.id.count);
        reset = (Button) findViewById(R.id.reset);
        num = (TextView) findViewById(R.id.num);
        name = (EditText) findViewById(R.id.name);
        save = getSharedPreferences("saved_data", MODE_PRIVATE);
        editor = save.edit();

        counter1 = save.getInt("counter", 0);
        Name = save.getString("Name", "");

        name.setText(Name);
        count.setText(String.valueOf(counter1));
        credits = new Intent(this, credits.class);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /**
         * function will make the option menu
         * param menu: the menu
         */
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        /**
         * function will check if the user clicked the credit button and if he did it will get him to the credits screen
         * param item: the item clicked
         */
        String st = item.getTitle().toString();
        if(st.equals("credit"))
        {
            startActivity(credits);
        }
        return super.onOptionsItemSelected(item);
    }

    public void count(View view) {
        /**
         * function will count how many times the user clicked the button count
         * param view: when button clicked
         */
        counter1++;
        count.setText(String.valueOf(counter1));
    }

    public void reset(View view) {
        /**
         * function will reset the counter to 0
         * param view: when button clicked
         */
        counter1 = 0;
        count.setText(String.valueOf(counter1));
    }

    public void exit(View view) {
        /**
         * function will exit the application and save the user data for next time
         * param view: when button clicked
         */
        Name = Name.toString();
        editor.putInt("counter", counter1);
        editor.putString("Name", Name);
        editor.commit();
        finish();
    }

}
