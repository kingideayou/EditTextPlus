package me.next.edittextplusdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import me.next.edittextplus.EditTextPlus;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditTextPlus editTextPlus = (EditTextPlus) findViewById(R.id.et_plus);

        editTextPlus.setOnButtonClickListener(new EditTextPlus.OnButtonClickListener() {
            @Override
            public void onButtonClick() {

            }
        });

        editTextPlus.setOnButtonCheckListener(new EditTextPlus.OnButtonCheckListener() {
            @Override
            public void onButtonCheck(boolean isChecked) {
            }
        });
    }
}
