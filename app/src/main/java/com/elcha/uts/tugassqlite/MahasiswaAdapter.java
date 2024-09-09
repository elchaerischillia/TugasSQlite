package com.elcha.uts.tugassqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MahasiswaAdapter extends BaseAdapter {
    Context context;
    List<Mahasiswa> mahasiswaList;

    public MahasiswaAdapter(Context context, List<Mahasiswa> mahasiswaList) {
        this.context = context;
        this.mahasiswaList = mahasiswaList;
    }

    @Override
    public int getCount() {
        return mahasiswaList.size();
    }

    @Override
    public Object getItem(int position) {
        return mahasiswaList.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(R.layout.item, parent, false);

        TextView tvNim = v.findViewById(R.id.tvNim);
        TextView tvNama = v.findViewById(R.id.tvNama);
        TextView tvProdi = v.findViewById(R.id.tvProdi);

        // Mengisi data ke dalam TextView
        Mahasiswa mahasiswa = mahasiswaList.get(i);
        tvNim.setText(mahasiswa.getNim());
        tvNama.setText(mahasiswa.getNama());
        tvProdi.setText(mahasiswa.getProdi());

        return v;
    }
}
