package com.elcha.uts.tugassqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lvMahasiswa;
    FloatingActionButton fabTambah;
    DatabaseHandler db;
    MahasiswaAdapter adapter;
    List<Mahasiswa> mahasiswaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvMahasiswa = findViewById(R.id.lv_mahasiswa);
        fabTambah = findViewById(R.id.fab_tambah);
        db = new DatabaseHandler(this);

        // Menampilkan data mahasiswa
        mahasiswaList = db.getAllMahasiswa();
        adapter = new MahasiswaAdapter(this, mahasiswaList);
        lvMahasiswa.setAdapter(adapter);

        fabTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogTambahMahasiswa();
            }
        });
    }

    private void showDialogTambahMahasiswa() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialogView = inflater.inflate(R.layout.frm_tambah, null);

        EditText etNama = dialogView.findViewById(R.id.et_nama);
        EditText etNim = dialogView.findViewById(R.id.et_nim);
        EditText etProdi = dialogView.findViewById(R.id.et_prodi);
        Button btnSimpan = dialogView.findViewById(R.id.btn_simpan);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Tambah Mahasiswa")
            .setView(dialogView)
            .setCancelable(true);

        AlertDialog dialog = builder.create();
        dialog.show();

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = etNama.getText().toString().trim();
                String nim = etNim.getText().toString().trim();
                String prodi = etProdi.getText().toString().trim();

                if (!nama.isEmpty() && !nim.isEmpty() && !prodi.isEmpty()) {
                    Mahasiswa mahasiswa = new Mahasiswa(nama, nim, prodi);
                    db.addMahasiswa(mahasiswa);
                    mahasiswaList.clear();
                    mahasiswaList.addAll(db.getAllMahasiswa());
                    adapter.notifyDataSetChanged();
                    dialog.dismiss();
                } else {
                    // Handle error case
                }
            }
        });
    }
}
