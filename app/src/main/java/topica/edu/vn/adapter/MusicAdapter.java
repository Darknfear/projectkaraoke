package topica.edu.vn.adapter;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import topica.edu.model.Music;
import topica.edu.vn.karaoke.Database;
import topica.edu.vn.karaoke.MainActivity;
import topica.edu.vn.karaoke.R;

public class MusicAdapter extends ArrayAdapter<Music> {

    MainActivity context;
    int resource;
    List<Music> objects;

    public MusicAdapter( MainActivity context, int resource,  List<Music> objects) {
        super(context, resource, objects);
        this.context = context;
        this.objects = objects;
        this.resource = resource;
    }


    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {

        LayoutInflater inflater = this.context.getLayoutInflater();
        View row = inflater.inflate(resource,null);

        TextView txtMaSo = row.findViewById(R.id.txtMaSo);
        TextView txtTenBaiHat = row.findViewById(R.id.txtTenBaiHat);
        TextView txtTenTacGia = row.findViewById(R.id.txtTenTacGia);

        Button btnLike = row.findViewById(R.id.btnLike);
        Button btnDisLike = row.findViewById(R.id.btnDisLike);


        final Music music = this.objects.get(position);
        if(music.isYeuThich()){
            btnLike.setVisibility(View.INVISIBLE);
            btnDisLike.setVisibility(View.VISIBLE);
        }else{
            btnDisLike.setVisibility(View.INVISIBLE);
            btnLike.setVisibility(View.VISIBLE);
        }

        txtMaSo.setText(String.valueOf(music.getiD()));
        txtTenBaiHat.setText(music.getTenBaiHat());
        txtTenTacGia.setText(music.getTenTacGia());



        btnDisLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.database.upDate(music.getiD(),0);
            }
        });
        btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.database.upDate(music.getiD(),1);
            }
        });

        return row;
    }


}
