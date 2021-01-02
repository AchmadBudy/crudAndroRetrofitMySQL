package com.example.mahasiswa.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mahasiswa.API.APIRequestData;
import com.example.mahasiswa.API.RetroServer;
import com.example.mahasiswa.Model.DataModel;
import com.example.mahasiswa.Model.ResponseModel;
import com.example.mahasiswa.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UbahActivity extends AppCompatActivity {
    private EditText etNama, etNIM;
    private Button btnUbah;
    private String nama,nim;
    private int idLaundry;
    private List<DataModel> listData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah);

        etNama = findViewById(R.id.et_nama);
        etNIM = findViewById(R.id.et_nim);
        btnUbah = findViewById(R.id.btn_ubah);
        Bundle extras = getIntent().getExtras();
        idLaundry= Integer.parseInt(extras.getString("key"));
        getDataDetail();


        btnUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nama = etNama.getText().toString();
                nim = etNIM.getText().toString();

                if (nama.trim().equals("")){
                    etNama.setError("Nama Harus Diisi");
                }else if (nim.trim().equals("")){
                    etNIM.setError("NIM Harus Diisi");
                }else {
                    EditData();
                }
            }
        });


    }

    private void EditData(){
        APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModel> editDatas = ardData.ardEditData(idLaundry,nama,nim);

        editDatas.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                String pesan = response.body().getPesan();

                Toast.makeText(UbahActivity.this, "Pesan : "+pesan, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(UbahActivity.this, "Gagal Menghubungi Server"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getDataDetail(){
        APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModel> detailData = ardData.ardGetDetail(idLaundry);

        detailData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                String pesan = response.body().getPesan();

                etNama.setText(response.body().getNama());
                etNIM.setText(response.body().getNim());

                Toast.makeText(UbahActivity.this, "Pesan : "+pesan, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(UbahActivity.this, "Gagal Menghubungi Server"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}