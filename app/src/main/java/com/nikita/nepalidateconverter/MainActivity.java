package com.nikita.nepalidateconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText etxtNepaliYear=(EditText)findViewById(R.id.etxtNepaliYear);
        final EditText etxtNepaliMonth=(EditText)findViewById(R.id.etxtNepaliMonth);
        final EditText etxtNepaliDay=(EditText)findViewById(R.id.etxtNepaliDay);
        final TextView txtEnglishYear=(TextView)findViewById(R.id.txtEnglishYear);
        Button btnConvert=(Button)findViewById(R.id.btnConvert) ;

        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int year=Integer.valueOf(etxtNepaliYear.getText().toString());
                int month=Integer.valueOf(etxtNepaliMonth.getText().toString());
                int day=Integer.valueOf(etxtNepaliDay.getText().toString());
                DateConverter dateConverter=new DateConverter();
                Date date=(dateConverter.getConvertedEnglishDate(year,month,day));
                txtEnglishYear.setText(date.toString());
            }


        });

    }
}
