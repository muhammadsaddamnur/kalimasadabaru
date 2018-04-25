package com.saddam.nur.kalimasadabaru.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.saddam.nur.kalimasadabaru.Model.PostResponseModel;
import com.saddam.nur.kalimasadabaru.R;
import com.saddam.nur.kalimasadabaru.Service.ApiClient;
import com.saddam.nur.kalimasadabaru.Service.ApiServicePOST;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class daftar extends AppCompatActivity implements View.OnClickListener {

    EditText nama1, email1, password1, no_hp1, alamat1, bank1, no_rek1, atasnama1;
    String      nama, email, password, no_hp, alamat, bank,  no_rek, atasnama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);


        nama1 = (EditText)findViewById(R.id.txtNama);
        email1 = (EditText)findViewById(R.id.txtEmail);
        password1 = (EditText)findViewById(R.id.txtPassword);
        no_hp1 = (EditText)findViewById(R.id.txtNoHp);
        alamat1 = (EditText)findViewById(R.id.txtAlamat);
        bank1 = (EditText)findViewById(R.id.txtBank);
        no_rek1 = (EditText)findViewById(R.id.txtNoRek);
        atasnama1 = (EditText)findViewById(R.id.txtAtasNama);


        Button btnDaftar = (Button)findViewById(R.id.btnDaftar);
        btnDaftar.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnDaftar:
                nama = nama1.getText().toString();
                email = email1.getText().toString();
                password = password1.getText().toString();
                no_hp = no_hp1.getText().toString();
                alamat = alamat1.getText().toString();
                bank = bank1.getText().toString();
                no_rek = no_rek1.getText().toString();
                atasnama = atasnama1.getText().toString();

                insertData(nama, email, password, no_hp, alamat, bank, no_rek, atasnama);
                break;
        }
    }

    private void insertData(String nama, String email, String password, String no_hp, String alamat, String bank, String no_rek, String atasnama){
        ApiServicePOST apiService = ApiClient.getClient().create(ApiServicePOST.class);
        Call<PostResponseModel> call = apiService.InsertPost(nama, email, password, no_hp, alamat, bank, no_rek, atasnama); //mengirim semua data ke server untuk di query
        call.enqueue(new Callback<PostResponseModel>() {
            @Override
            public void onResponse(Call<PostResponseModel> call, Response<PostResponseModel> response) {

                PostResponseModel PostResponseModel = response.body();

                //check the status code
                if(PostResponseModel.getSuccess()==1){
                    Toast.makeText(daftar.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    Intent login = new Intent(daftar.this, login.class);
                    startActivity(login);
                    finish();
                }else{
                    Toast.makeText(daftar.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PostResponseModel> call, Throwable t) {
                Toast.makeText(daftar.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
