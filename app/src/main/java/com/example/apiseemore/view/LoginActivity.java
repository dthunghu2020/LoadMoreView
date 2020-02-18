package com.example.apiseemore.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apiseemore.KEY;
import com.example.apiseemore.R;
import com.example.apiseemore.presenter.login.ILoginPresenter;
import com.example.apiseemore.presenter.login.LoginPresenter;

public class LoginActivity extends AppCompatActivity implements ILoginPresenter {

    private EditText edtUserId;
    private EditText edtUserName;
    private EditText edtUserPass;
    private TextView btnForgotPass;
    private Button btnLogin;

    private LoginPresenter mLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        init();
        event();

    }


    private void initView() {
        edtUserId = findViewById(R.id.edtUserId);
        edtUserName = findViewById(R.id.edtUserName);
        edtUserPass = findViewById(R.id.edtUserPass);
        btnForgotPass = findViewById(R.id.btnForgotPass);
        btnLogin = findViewById(R.id.btnLogin);
    }

    private void init() {
        mLoginPresenter = new LoginPresenter(this);
    }

    private void event() {
        btnForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Todo
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLoginPresenter.checkEdtUserLogin(edtUserId.getText().toString(), edtUserName.getText().toString(), edtUserPass.getText().toString());
            }
        });
    }


    @Override
    public void onLoginSuccess(String token) {
        Toast.makeText(this, getString(R.string.login_success), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(LoginActivity.this, SeeMoreActivity.class);
        intent.putExtra(KEY.TOKEN, "Bearer" + token);
        startActivity(intent);
    }

    @Override
    public void onLoginFail() {
        Toast.makeText(this, getString(R.string.login_fail), Toast.LENGTH_SHORT).show();
    }
}
