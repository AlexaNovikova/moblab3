package com.example.lab3;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PersonalPage extends AppCompatActivity {
    Button exitBt;
    EditText eText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        Toast toast= Toast.makeText(getApplicationContext(),
                "Добро пожаловать!", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
        exitBt = findViewById(R.id.saveButton);
        eText = findViewById(R.id.editText);
        SharedPreferences settings = getPreferences(0);
        String lasttext = settings.getString("LastText" , "");
        eText.setText(lasttext); //вставляе в поле последний текст
        exitBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    @Override
    public void onPause() { //для сохранения только что введенного текста
        super.onPause();
        //получаем содержимое текстового поля
        String updateText = eText.getText().toString();
        // загружаем редактор настроек
        SharedPreferences settings = getPreferences(0);
        SharedPreferences.Editor edit = settings.edit();
        //записывем в редактор настройки
        edit.putString("LastText", updateText);
        // сохраняем данные из редактора
        edit.apply();
    }
}
