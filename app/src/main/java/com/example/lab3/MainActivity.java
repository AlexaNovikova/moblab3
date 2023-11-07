package com.example.lab3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button Enter;
    EditText Login, Password;
    String log = "admin", pas = "121212"; //аккаунт для входа
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Enter = findViewById(R.id.enterButton);
        Login = findViewById(R.id.loginTextField);
        Password = findViewById(R.id.passTextPassword);
        SharedPreferences settings = getPreferences(0);
        String login = settings.getString("Login" , "");
        Login.setText(login); // вставляем в текстовое поле загруженные настройки
        String password = settings.getString("Password" , "");
        Password.setText(password);
        Enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (log.equals(Login.getText().toString()) && pas.equals(Password.getText().toString()) )
                {
                    Intent intent = new Intent(MainActivity.this, PersonalPage.class);
                    startActivity(intent);
                }
                else {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Неверные логин/пароль", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                }
            }
        });
    }
    @Override
    protected void onStop() {
        super.onStop();
        //получаем содержимое текстового поля
        String login_str = Login.getText().toString();
        String password_str = Password.getText().toString();
        // загружаем редактор настроек
        SharedPreferences settings = getPreferences(0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("Login", login_str);        //записывем в редактор настройки
        editor.putString("Password", password_str);
        editor.apply();        // сохраняем данные из редактора

    }
}
