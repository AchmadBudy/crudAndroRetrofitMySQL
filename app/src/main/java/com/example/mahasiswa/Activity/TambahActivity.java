package com.example.mahasiswa.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mahasiswa.API.APIRequestData;
import com.example.mahasiswa.API.RetroServer;
import com.example.mahasiswa.Model.ResponseModel;
import com.example.mahasiswa.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahActivity extends AppCompatActivity {
    private EditText etNama, etNIM;
    private Button btnSimpan;
    private String nama,nim;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        etNama = findViewById(R.id.et_nama);
        etNIM = findViewById(R.id.et_nim);
        btnSimpan = findViewById(R.id.btn_simpan);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nama = etNama.getText().toString();
                nim = etNIM.getText().toString();

                if (nama.trim().equals("")){
                    etNama.setError("Nama Harus Diisi");
                }else if (nim.trim().equals("")){
                    etNIM.setError("NIM Harus Diisi");
                }else {
                    CreateData();
                }
            }
        });
    }
    private void CreateData(){
        APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModel> simpanData = ardData.ardCreateData(nama,nim);
        simpanData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                String pesan = response.body().getPesan();

                Toast.makeText(TambahActivity.this, "Pesan : "+pesan, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(TambahActivity.this, "Gagal Menghubungi Server"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}