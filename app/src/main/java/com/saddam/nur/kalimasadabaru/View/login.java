package com.saddam.nur.kalimasadabaru.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.saddam.nur.kalimasadabaru.Model.ListUserLoginModel;
import com.saddam.nur.kalimasadabaru.Model.user;
import com.saddam.nur.kalimasadabaru.R;
import com.saddam.nur.kalimasadabaru.Service.ApiClient;
import com.saddam.nur.kalimasadabaru.Service.ApiServiceGET;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class login extends AppCompatActivity  implements View.OnClickListener{

    EditText txtEmail, txtPassword;
    String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        //deklarasi button
        Button btnDaftar = (Button)findViewById(R.id.btnDaftar);
        Button btnLogin = (Button)findViewById(R.id.btnLogin);

        //fungsi set on clicklistener
        btnDaftar.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnDaftar:
                Intent daftar = new Intent(this, daftar.class);
                startActivity(daftar);
                break;
            case R.id.btnLogin:
                txtEmail        = (EditText)findViewById(R.id.txtEmail);
                txtPassword     = (EditText)findViewById(R.id.txtPassword);

                email           = txtEmail.getText().toString();
                password        = txtPassword.getText().toString();

                String user64       = email + ":" + password;
                String authHeader   = "Basic " + Base64.encodeToString(user64.getBytes(), Base64.NO_WRAP);

                login(authHeader);
                break;
        }
    }


    private void login(final String auth){
        ApiServiceGET apiService = ApiClient.getClient().create(ApiServiceGET.class);
        Call<ListUserLoginModel> call = apiService.getUser(auth); //mengirim semua data ke server untuk di query
        call.enqueue(new Callback<ListUserLoginModel>() {
            @Override
            public void onResponse(Call<ListUserLoginModel> call, Response<ListUserLoginModel> response) {

                ListUserLoginModel ListUserLoginModel = response.body();

                //check the status code
                if(ListUserLoginModel.getSuccess()==1){

                    SharedPreferences prefs = getSharedPreferences("kalimasada", MODE_PRIVATE);
                    SharedPreferences.Editor mEditor = prefs.edit();

                    List<user> user     = ListUserLoginModel.getUser();
                    String idusersp     = user.get(0).getIdUser().toString();
                    String namasp       = user.get(0).getNama().toString();
                    String emailsp      = user.get(0).getEmail().toString();
                    String passwordsp   = user.get(0).getPassword().toString();
                    String nohpsp       = user.get(0).getNoHp().toString();
                    String alamatsp     = user.get(0).getAlamat().toString();
                    String banksp       = user.get(0).getBank().toString();
                    String noreksp      = user.get(0).getNoRek();
                    String atsnamasp    = user.get(0).getAtas_nama().toString();

                    mEditor.putString("id_user", idusersp);
                    mEditor.putString("nama", namasp);
                    mEditor.putString("email", emailsp);
                    mEditor.putString("password", passwordsp);
                    mEditor.putString("no_hp", nohpsp);
                    mEditor.putString("alamat", alamatsp);
                    mEditor.putString("bank", banksp);
                    mEditor.putString("no_rek", noreksp);
                    mEditor.putString("atas_nama", atsnamasp);

                    mEditor.commit();

                    Intent main = new Intent(login.this, MainActivity.class);
                    startActivity(main);
                    finish();
                }else{
                    Toast.makeText(login.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ListUserLoginModel> call, Throwable t) {
                Toast.makeText(login.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
