package com.saddam.nur.kalimasadabaru.View;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.saddam.nur.kalimasadabaru.Model.PostResponseModel;
import com.saddam.nur.kalimasadabaru.R;
import com.saddam.nur.kalimasadabaru.Service.ApiClient;
import com.saddam.nur.kalimasadabaru.Service.ApiServicePOST;
import com.saddam.nur.kalimasadabaru.Service.UploadImageInterface;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class pemesanan extends AppCompatActivity implements EasyPermissions.PermissionCallbacks{
    private String kategori;
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int REQUEST_GALLERY_CODE = 200;
    private static final int READ_REQUEST_CODE = 300;
    Boolean btnUpload = true;
    String Desainimg ="";
    String logoimg = "";
    String urlDesain = "";
    String urllogo = "";
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemesanan);

        Bundle extras = getIntent().getExtras();
        kategori = extras.getString("Kategori");
        TextView lblKategori = (TextView)findViewById(R.id.lblKategori);
        lblKategori.setText(kategori);


        //Buat random no pemesanan
        TextView lblNopesan = (TextView)findViewById(R.id.lblNopesan);
        Random rand = new Random();
        int n = rand.nextInt(999999); // Gives n such that 0 <= n < 20

        SharedPreferences prefs = getSharedPreferences("kalimasada", Context.MODE_PRIVATE);
        String nouserid = prefs.getString("id_user", null);
        lblNopesan.setText(nouserid + Integer.toString(n));

        Button btnUploadDesain = (Button)findViewById(R.id.btnUploadDesain);
        btnUploadDesain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openGalleryIntent = new Intent(Intent.ACTION_PICK);
                openGalleryIntent.setType("image/*");
                startActivityForResult(openGalleryIntent, REQUEST_GALLERY_CODE);
                btnUpload = true;
            }
        });

        Button btnUploadLogo = (Button)findViewById(R.id.btnUploadLogo);
        btnUploadLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openGallery2Intent = new Intent(Intent.ACTION_PICK);
                openGallery2Intent.setType("image/*");
                startActivityForResult(openGallery2Intent, REQUEST_GALLERY_CODE);
                btnUpload = false;
            }
        });


        FloatingActionButton btnNext = (FloatingActionButton)findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Objects.equals(Desainimg, "") || Objects.equals(logoimg, "")){
                    Toast.makeText(pemesanan.this, "Data Belum Lengkap", Toast.LENGTH_SHORT).show();
                }else {
                    insertData(Desainimg, logoimg);
                }
            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, pemesanan.this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_GALLERY_CODE && resultCode == Activity.RESULT_OK){
            Uri uri = data.getData();
            if(EasyPermissions.hasPermissions(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                String filePath = getRealPathFromURIPath(uri, pemesanan.this);

                String [] FileName = filePath.split("/");
                int ke = FileName.length - 1;

                if (btnUpload){
                    TextView lblDesain = (TextView)findViewById(R.id.lblDesain);
                    urlDesain = FileName[ke];
                    lblDesain.setText(FileName[ke]);
                    Desainimg = filePath;
                    ImageView imgDesain = (ImageView)findViewById(R.id.imgDesain);
                    Picasso.with(this)
                            .load(new File(Desainimg))
                            .into(imgDesain);
                } else {
                    TextView lblLogo = (TextView)findViewById(R.id.lblLogo);
                    urllogo = FileName[ke];
                    lblLogo.setText(FileName[ke]);
                    logoimg = filePath;
                    ImageView imgLogo = (ImageView)findViewById(R.id.imgLogo);
                    Picasso.with(this)
                            .load(new File(logoimg))
                            .into(imgLogo);
                }

                /*File file = new File(filePath);
                Log.d(TAG, "Filename " + file.getName());
                //RequestBody mFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                RequestBody mFile = RequestBody.create(MediaType.parse("image*//*"), file);
                MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", file.getName(), mFile);
                RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), file.getName());*/

                /*UploadImageInterface apiService = ApiClient.getClient().create(UploadImageInterface.class);
                Call<PostResponseModel> fileUpload = apiService.uploadFile(fileToUpload, filename);
                fileUpload.enqueue(new Callback<PostResponseModel>() {
                    @Override
                    public void onResponse(Call<PostResponseModel> call, Response<PostResponseModel> response) {
                        Toast.makeText(detailpesanDesain.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onFailure(Call<PostResponseModel> call, Throwable t) {
                        Log.d(TAG, "Error " + t.getMessage());
                    }
                });*/
            }else{
                EasyPermissions.requestPermissions(this, getString(R.string.read_file), READ_REQUEST_CODE, Manifest.permission.READ_EXTERNAL_STORAGE);
            }
        }
    }
    private String getRealPathFromURIPath(Uri contentURI, Activity activity) {
        Cursor cursor = activity.getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            return contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(idx);
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        if(uri != null){
            String filePath = getRealPathFromURIPath(uri, pemesanan.this);
            if (btnUpload){
                TextView lblDesain = (TextView)findViewById(R.id.lblDesain);
                lblDesain.setText(filePath);
            } else {
                TextView lblLogo = (TextView)findViewById(R.id.lblLogo);
                lblLogo.setText(filePath);
            }

            /*File file = new File(filePath);
            RequestBody mFile = RequestBody.create(MediaType.parse("image*//*"), file);
            MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", file.getName(), mFile);
            RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), file.getName());

            UploadImageInterface apiService = ApiClient.getClient().create(UploadImageInterface.class);
            Call<PostResponseModel> fileUpload = apiService.uploadFile(fileToUpload, filename);
            fileUpload.enqueue(new Callback<PostResponseModel>() {
                @Override
                public void onResponse(Call<PostResponseModel> call, Response<PostResponseModel> response) {
                    Toast.makeText(detailpesanDesain.this, "Success " + response.message(), Toast.LENGTH_LONG).show();
                    Toast.makeText(detailpesanDesain.this, "Success " + response.body().toString(), Toast.LENGTH_LONG).show();
                }
                @Override
                public void onFailure(Call<PostResponseModel> call, Throwable t) {
                    Log.d(TAG, "Error " + t.getMessage());
                }
            });*/
        }
    }
    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Log.d(TAG, "Permission has been denied");
    }


    private void insertData(String Desain, final String logo){

        File file = new File(Desain);
        Log.d(TAG, "Filename " + file.getName());
        //RequestBody mFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        RequestBody mFile = RequestBody.create(MediaType.parse("image*//*"), file);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", file.getName(), mFile);
        RequestBody filenameDesain = RequestBody.create(MediaType.parse("text/plain"), file.getName());

        UploadImageInterface apiService = ApiClient.getClient().create(UploadImageInterface.class);
        Call<PostResponseModel> fileUpload = apiService.uploadFile(fileToUpload, filenameDesain);
        fileUpload.enqueue(new Callback<PostResponseModel>() {
            @Override
            public void onResponse(Call<PostResponseModel> call, Response<PostResponseModel> response) {


            }
            @Override
            public void onFailure(Call<PostResponseModel> call, Throwable t) {
                Log.d(TAG, "Error " + t.getMessage());
            }
        });


        File filelogo = new File(logo);
        Log.d(TAG, "Filename " + filelogo.getName());
        //RequestBody mFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        RequestBody mFilelogo = RequestBody.create(MediaType.parse("image*//*"), filelogo);
        MultipartBody.Part fileToUploadlogo = MultipartBody.Part.createFormData("file", filelogo.getName(), mFilelogo);
        RequestBody filenamelogo = RequestBody.create(MediaType.parse("text/plain"), filelogo.getName());

        UploadImageInterface apiService2 = ApiClient.getClient().create(UploadImageInterface.class);
        Call<PostResponseModel> fileUpload2 = apiService2.uploadFile(fileToUploadlogo, filenamelogo);
        fileUpload2.enqueue(new Callback<PostResponseModel>() {
            @Override
            public void onResponse(Call<PostResponseModel> call, Response<PostResponseModel> response) {


            }
            @Override
            public void onFailure(Call<PostResponseModel> call, Throwable t) {
                Log.d(TAG, "Error " + t.getMessage());
            }
        });


        TextView lblnopesan = (TextView)findViewById(R.id.lblNopesan);
        String id_pemesanan = lblnopesan.getText().toString();

        SharedPreferences prefs = getSharedPreferences("kalimasada", Context.MODE_PRIVATE);
        String nouserid = prefs.getString("id_user", null);

        EditText txtnamateam = (EditText)findViewById(R.id.txtNamaTim);
        String nama_team = txtnamateam.getText().toString();

        TextView lblurlDesain = (TextView)findViewById(R.id.lblDesain);
        String url_Desain = lblurlDesain.getText().toString();

        TextView lblurllogo = (TextView)findViewById(R.id.lblLogo);
        String url_logo = lblurllogo.getText().toString();

        TextView lblKategori = (TextView)findViewById(R.id.lblKategori);
        String kategori = lblKategori.getText().toString();

        TextView txtjmlpesanan = (TextView)findViewById(R.id.txtJmlPesanan);
        String jml_pesanan = txtjmlpesanan.getText().toString();
        
        ApiServicePOST apiService3 = ApiClient.getClient().create(ApiServicePOST.class);
        Call<PostResponseModel> namateamurl = apiService3.InsertPesanan(id_pemesanan, nouserid, nama_team, url_Desain, url_logo, kategori, jml_pesanan);
        namateamurl.enqueue(new Callback<PostResponseModel>() {
            @Override
            public void onResponse(Call<PostResponseModel> call, Response<PostResponseModel> response) {
                Toast.makeText(pemesanan.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<PostResponseModel> call, Throwable t) {
                Log.d(TAG, "Error " + t.getMessage());
            }
        });

        Intent intent=new Intent(getApplicationContext(),pemesanan2.class);
        intent.putExtra("Jumlah",  jml_pesanan);
        intent.putExtra("id_pesanan", id_pemesanan);
        startActivity(intent);
    }
}
