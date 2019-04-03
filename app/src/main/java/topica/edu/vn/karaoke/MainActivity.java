package topica.edu.vn.karaoke;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ContentResolver;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import topica.edu.model.Music;
import topica.edu.vn.adapter.MusicAdapter;

public class MainActivity extends AppCompatActivity {

    public static Database database;

    TabHost tabHost;

    ArrayList<Music> dsBaiHat;
    MusicAdapter musicAdapter;
    ListView lvMusic;

    ArrayList<Music> dsBaiHatYeuThich;
    MusicAdapter musiyeuThichcAdapter;
    ListView lvMusicYeuThich;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();

        addEvents();


    }



    private void addEvents() {
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if(tabId.equalsIgnoreCase("t1")){
                   xuLyHienThiBaiHat();
                }else if(tabId.equalsIgnoreCase("t2")){
                    xuLyHienThiBaiHatYeuThich();
                }
            }
        });
    }

    private void xuLyHienThiBaiHat() {
        database = new Database(this);
        Cursor cursor = database.getData(0);
        dsBaiHat.clear();
        while(cursor.moveToNext()){
            int id = cursor.getInt(0);
            String tenBaiHat = cursor.getString(1);
            String tenTacGia = cursor.getString(2);
            int yeuThich = cursor.getInt(3);

            dsBaiHat.add(new Music(id,tenBaiHat,tenTacGia,yeuThich==1));

        }
        cursor.close();
        musicAdapter.notifyDataSetChanged();
    }

    private void xuLyHienThiBaiHatYeuThich() {
       //
        database = new Database(this);
       Cursor cursor = database.getData(1);
        dsBaiHatYeuThich.clear();

           while (cursor.moveToNext()){
               int id = cursor.getInt(0);
               String tenBaiHat = cursor.getString(1);
               String tenTacGia = cursor.getString(2);
               int yeuThich = cursor.getInt(3);

               dsBaiHatYeuThich.add(new Music(id,tenBaiHat,tenTacGia,yeuThich==1));
           }


        cursor.close();
        musiyeuThichcAdapter.notifyDataSetChanged();
    }


    private void addControls() {

        tabHost = findViewById(R.id.tabHost);
        tabHost.setup();
        TabHost.TabSpec tab1 = tabHost.newTabSpec("t1");
        tab1.setIndicator("Music");
        tab1.setContent(R.id.tab1);
        tabHost.addTab(tab1);

        TabHost.TabSpec tab2 = tabHost.newTabSpec("t2");
        tab2.setIndicator("MusicLike");
        tab2.setContent(R.id.tab2);
        tabHost.addTab(tab2);

        lvMusic = findViewById(R.id.lvMusic);
        dsBaiHat = new ArrayList<>();
        musicAdapter = new MusicAdapter(MainActivity.this,R.layout.item,dsBaiHat);
        lvMusic.setAdapter(musicAdapter);

        lvMusicYeuThich = findViewById(R.id.lvMusicYeuThich);
        dsBaiHatYeuThich = new ArrayList<>();
        musiyeuThichcAdapter = new MusicAdapter(MainActivity.this,R.layout.item,dsBaiHatYeuThich);
        lvMusicYeuThich.setAdapter(musiyeuThichcAdapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.menu){
            diaLogThem();
        }
        return super.onOptionsItemSelected(item);
    }

    public void diaLogThem(){
        final Dialog diaLog = new Dialog(this);
        diaLog.setContentView(R.layout.dialog_them);
        TextView txtThem = diaLog.findViewById(R.id.txtThem);
        final EditText edTenBaiHat = diaLog.findViewById(R.id.edTenBaiHat);
        final EditText edTenTacGia = diaLog.findViewById(R.id.edTenTacGia);
        Button btnXacNhanThem = diaLog.findViewById(R.id.btnXacNhanThem);
        Button btnHuyThem = diaLog.findViewById(R.id.btnHuyThem);
        database = new Database(this);

        btnHuyThem.setOnClickListener(new View.OnClickListener() {
              @Override
               public void onClick(View v) {
                  diaLog.dismiss();
                }
            });
        btnXacNhanThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.insertMusic(edTenBaiHat.getText().toString(),edTenTacGia.getText().toString());
                diaLog.dismiss();
                xuLyHienThiBaiHat();
            }
        });

        diaLog.show();

    }

}
