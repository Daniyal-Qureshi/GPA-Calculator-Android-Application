package com.example.gpacalculator;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    float[] GPA;
    float[] credit;
    TextView t;
    float totalCredit, CGPA, sum;
    EditText G[], name[];
    Spinner C[];


    public void onBackPressed() {

        super.onBackPressed();
        finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.i("oncrea","aahya");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GPA = new float[10];
        credit = new float[10];
        G = new EditText[10];
        C = new Spinner[10];
        name = new EditText[10];

        name[0] = findViewById(R.id.name1);
        name[1] = findViewById(R.id.name2);
        name[2] = findViewById(R.id.name3);
        name[3] = findViewById(R.id.name4);
        name[4] = findViewById(R.id.name5);
        name[5] = findViewById(R.id.name6);
        name[6] = findViewById(R.id.name7);
        name[7] = findViewById(R.id.name8);
        name[8] = findViewById(R.id.name9);
        name[9] = findViewById(R.id.name10);

        G[0] = findViewById(R.id.gpa1);
        G[1] = findViewById(R.id.gpa2);
        G[2] = findViewById(R.id.gpa3);
        G[3] = findViewById(R.id.gpa4);
        G[4] = findViewById(R.id.gpa5);
        G[5] = findViewById(R.id.gpa6);
        G[6] = findViewById(R.id.gpa7);
        G[7] = findViewById(R.id.gpa8);
        G[8] = findViewById(R.id.gpa9);
        G[9] = findViewById(R.id.gpa10);

        C[0] = findViewById(R.id.spinner1);
        C[1] = findViewById(R.id.spinner2);
        C[2] = findViewById(R.id.spinner3);
        C[3] = findViewById(R.id.spinner4);
        C[4] = findViewById(R.id.spinner5);
        C[5] = findViewById(R.id.spinner6);
        C[6] = findViewById(R.id.spinner7);
        C[7] = findViewById(R.id.spinner8);
        C[8] = findViewById(R.id.spinner9);
        C[9] = findViewById(R.id.spinner10);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        int id = item.getItemId();
        if (id == R.id.item1) {
            Intent i = new Intent(MainActivity.this, GradingPolicy.class);
            startActivity(i);
        }
        return true;

    }

    public void Done(View v) {
        totalCredit = 0.0f;
        CGPA = 0.0f;
        sum = 0.0f;
        try {


            for (int i = 0; i < 10; i++) {
                credit[i] = Float.parseFloat(C[i].getSelectedItem().toString());
                try {
                    GPA[i] = Float.parseFloat(G[i].getText().toString());
                    if (GPA[i] > 4 || GPA[i] < 0)
                        throw new Exception();
                } catch (Exception e) {
                    if (G[i].getText().length() != 0) {
                        G[i].setError("Invalid GPA");


                    }

                    if (GPA[i] > 4)
                        Toast.makeText(this, "GPA must be less than 4", Toast.LENGTH_SHORT).show();

                    else if (GPA[i] < 0)
                        Toast.makeText(this, "GPA must be greater than 0", Toast.LENGTH_SHORT).show();
                    GPA[i] = 0;
                    credit[i] = 0;
                }


                Log.i("val gpa", "" + G[i].getText());
                boolean flag = true;
                if (name[i].getText().length() == 0)
                    flag = false;


                for (int k = 65; k < 123 && flag; k++) {
                    if ((int) name[i].getText().toString().charAt(0) == k)
                        flag = false;
                }


                if (name[i].getText().toString().length() == 0 && GPA[i] != 0) {

                    name[i].setError("Empty Field");
                    credit[i] = 0;
                    GPA[i] = 0;

                } else if (flag) {
                    name[i].setError("Invalid Course Name");
                    credit[i] = 0;
                    GPA[i] = 0;

                } else if (G[i].getText().length() == 0 && name[i].getText().toString().length() != 0) {
                    G[i].setError("Empty Field");
                    credit[i] = 0;
                    GPA[i] = 0;

                }


                totalCredit += credit[i];
                sum += GPA[i] * credit[i];

                Log.i("sum", "" + sum);

            }
            t = findViewById(R.id.gp);
            DecimalFormat o = new DecimalFormat("#.000");
            Log.i("total", "" + totalCredit);
            CGPA = Float.parseFloat(o.format(sum / totalCredit));


            if (CGPA <= 4 && CGPA > 0)
                t.setText(CGPA + "  GPA");
        } catch (Exception e) {

            Log.i("in", "excepion");
            e.printStackTrace();
        }

    }


    public void Clear(View v) {
        for (int i = 0; i < 10; i++) {
            credit[i] = 0.0f;

            C[i].setSelection(0);
            if(G[i]!=null) {
                G[i].setText("");
                G[i].setError(null);
            }
             if(name[i]!=null) {
                name[i].setText("");
                name[i].setError(null);
            }
        }
        if(t!=null)
            t.setText("");

        totalCredit = 0.0f;
        CGPA = 0.0f;
        sum = 0.0f;

    }
}