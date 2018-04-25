package com.saddam.nur.kalimasadabaru.View;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.saddam.nur.kalimasadabaru.Model.PostResponseModel;
import com.saddam.nur.kalimasadabaru.R;
import com.saddam.nur.kalimasadabaru.Service.ApiClient;
import com.saddam.nur.kalimasadabaru.Service.ApiServicePOST;
import com.saddam.nur.kalimasadabaru.Service.ApiServicePUT;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class pemesanan2 extends AppCompatActivity {
    public String id_pesanan;
    public Integer jumlah;
    String[] listArray={};
    private static final String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemesanan2);

        Bundle extras = getIntent().getExtras();
        id_pesanan = extras.getString("id_pesanan");
        TextView lblnopesan = (TextView)findViewById(R.id.lblNopesan);
        lblnopesan.setText(id_pesanan);
        jumlah = extras.getInt("Jumlah");

        final ListView listview =(ListView) findViewById(R.id.listDataPemain);

        final String[] dataPemain = new String[] {};

        final List<String> dataPemain_list = new ArrayList<String>(Arrays.asList(dataPemain));

        // Create an ArrayAdapter from List
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, dataPemain_list);

        // DataBind ListView with items from ArrayAdapter
        listview.setAdapter(arrayAdapter);


        final EditText txtDataPemain=(EditText)findViewById(R.id.txtDataPemain);

        txtDataPemain.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    String data = txtDataPemain.getText().toString();
                    dataPemain_list.add(data);
                    arrayAdapter.notifyDataSetChanged();
                    txtDataPemain.setText("");
                    Toast.makeText(pemesanan2.this, "Data Kosong ", Toast.LENGTH_LONG).show();

                }
                return false;

            }

        });


        FloatingActionButton btnNext = (FloatingActionButton)findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dataPemain_list==null){
                    Toast.makeText(pemesanan2.this, "Data Belum Lengkap", Toast.LENGTH_SHORT).show();
                }else{
                    String joined = TextUtils.join(" , ", dataPemain_list);
                    insertData(id_pesanan, joined);
                }
            }
        });
    }


    private void insertData(String id_pesanan,String joined){

        ApiServicePUT apiService = ApiClient.getClient().create(ApiServicePUT.class);
        Call<PostResponseModel> namateamurl = apiService.updatePemesanan(id_pesanan, joined);
        namateamurl.enqueue(new Callback<PostResponseModel>() {
            @Override
            public void onResponse(Call<PostResponseModel> call, Response<PostResponseModel> response) {
                Toast.makeText(pemesanan2.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<PostResponseModel> call, Throwable t) {
                Log.d(TAG, "Error " + t.getMessage());
            }
        });
    }

}
