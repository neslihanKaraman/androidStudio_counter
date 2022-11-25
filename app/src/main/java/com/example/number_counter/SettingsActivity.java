package com.example.number_counter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.math.MathUtils;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;

public class SettingsActivity extends AppCompatActivity {
    ImageButton upperLimitMinus,upperLimitPlus, downLimitMinus, downLimitPlus;
    EditText upperLimitValue,downLimitValue;
    CheckBox upperVib,upperSound,downVib,downSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        upperLimitMinus=(ImageButton) findViewById(R.id.ibttn_upperMinus);
        upperLimitPlus=(ImageButton) findViewById(R.id.ibttn_upperPlus);
        downLimitMinus=(ImageButton) findViewById(R.id.ibttn_downMinus);
        downLimitPlus=(ImageButton) findViewById(R.id.ibttn_downPlus);

        upperLimitValue=(EditText) findViewById(R.id.txt_upperValue);
        upperLimitValue.setText(String.valueOf(SharedPreferencesClass.getUpLimitValue()));
        downLimitValue=(EditText) findViewById(R.id.txt_downValue);
        downLimitValue.setText(String.valueOf(SharedPreferencesClass.getDownLimitValue()));

        upperVib=(CheckBox) findViewById(R.id.cb_upperVib);
        upperVib.setChecked(SharedPreferencesClass.getUpVib());
        upperSound=(CheckBox) findViewById(R.id.cb_upperSound);
        upperSound.setChecked(SharedPreferencesClass.getUpSound());
        downVib=(CheckBox) findViewById(R.id.cb_downVib);
        downVib.setChecked(SharedPreferencesClass.getDownVib());
        downSound=(CheckBox) findViewById(R.id.cb_downSound);
        downSound.setChecked(SharedPreferencesClass.getDownSound());

        upperVib.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                SharedPreferencesClass.setUpVib(b);
            }
        });
        upperSound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                SharedPreferencesClass.setUpSound(b);
            }
        });
        downVib.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                SharedPreferencesClass.setDownVib(b);
            }
        });
        downSound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                SharedPreferencesClass.setDownSound(b);
            }
        });


        upperLimitMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateUpLimit(-1);
            }
        });
        upperLimitPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateUpLimit(1);
            }
        });
        downLimitMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateDownLimit(-1);
            }
        });
        downLimitPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateDownLimit(1);
            }
        });

    }
    private void updateUpLimit(int number){
        SharedPreferencesClass.setUpLimitValue(MathUtils.clamp(number+SharedPreferencesClass.getUpLimitValue() ,SharedPreferencesClass.getDownLimitValue()+1,Integer.MAX_VALUE));
        upperLimitValue.setText(String.valueOf(SharedPreferencesClass.getUpLimitValue()));
    }
    private void updateDownLimit(int number){
        SharedPreferencesClass.setDownLimitValue(MathUtils.clamp(number+SharedPreferencesClass.getDownLimitValue(),Integer.MIN_VALUE,SharedPreferencesClass.getUpLimitValue() -1));
        downLimitValue.setText(String.valueOf(SharedPreferencesClass.getDownLimitValue()));
    }

}