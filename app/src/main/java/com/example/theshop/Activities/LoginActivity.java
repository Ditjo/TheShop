package com.example.theshop.Activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.theshop.R;
import com.example.theshop.data.Logon;

public class LoginActivity extends AppCompatActivity {

    private EditText logon_username, logon_password;
    private Button logon_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initGui();

    }

    void initGui(){
        logon_btn = findViewById(R.id.logon_btn);
        logon_username = findViewById(R.id.logon_username);
        logon_password = findViewById(R.id.logon_password);
        logon_btn.setOnClickListener( x -> checkLogon());
    }

    void checkLogon(){
        String username = logon_username.getText().toString();
        String password = logon_password.getText().toString();
        if(username.equals(Logon.userName) && password.equals(Logon.password)){
            finish();
        } else {
            logon_username.setText("");
            logon_password.setText("");
            Toast.makeText(this,"Invalid Logon Given!", Toast.LENGTH_SHORT).show();
        }
    }
}