package stu.edu.vn.cuoikyandroid.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import stu.edu.vn.cuoikyandroid.R;
import stu.edu.vn.cuoikyandroid.store.HangHoa;

public class HangHoaAdapter extends ArrayAdapter<HangHoa> {
    Activity context;
    int layout;

    ArrayList<HangHoa> dshh;

    public HangHoaAdapter(Activity context, int layout, ArrayList<HangHoa> dshh) {
        super(context, layout, dshh);
        this.context = context;
        this.layout = layout;
        this.dshh = dshh;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(layout, null);

        HangHoa hh = dshh.get(position);

        TextView txtMa, txtTen, txtGia;

        txtMa = convertView.findViewById(R.id.txtMa);
        txtTen = convertView.findViewById(R.id.txtTen);
        txtGia = convertView.findViewById(R.id.txtGia);

        txtMa.setText("Mã: "+ hh.getMa());
        txtTen.setText("Tên: "+hh.getTen());
        txtGia.setText("Giá:" +hh.getGia());

        return convertView;
    }
}
