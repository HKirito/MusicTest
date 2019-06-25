package com.example.kirito.musictest.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kirito.musictest.UI.MainActivity;
import com.example.kirito.musictest.Utils.MusicUtils;
import com.example.kirito.musictest.R;
import com.example.kirito.musictest.Bean.Song;

import java.util.List;

public class Myadapter extends BaseAdapter {
    private Context context;
    private List<Song> list;
    private int position_flag = 0;

    private int myBlue = Color.argb(0xff, 0x00, 0xBF, 0xFF);
    private int myPurple = Color.argb(0xff, 0xFF, 0x00, 0xFF);
    private int myGreeen = Color.argb(0xff, 0x00, 0xFF, 0x00);
    private int myRed = Color.argb(0xff, 0xFF, 0x00, 0x00);

    private String Theme;


    public Myadapter(MainActivity mainActivity, List<Song> list){
        this.context = mainActivity;
        this.list = list;
    }

    public void setFlag(int flag){
        this.position_flag = flag;
    }

    public void setTheme(String Theme){
        this.Theme = Theme;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        ViewHolder holder;
        View view = null;
        if (convertView == null) {
            // 引入布局
            view = View.inflate(context, R.layout.list_item,null);

            holder = new ViewHolder();

            // 实例化对象
            holder.song = (TextView) view
                    .findViewById(R.id.item_mymusic_song);
            holder.singer = (TextView) view
                    .findViewById(R.id.item_mymusic_singer);
            holder.duration = (TextView) view
                    .findViewById(R.id.item_mymusic_duration);
            holder.position = (TextView) view
                    .findViewById(R.id.item_mymusic_postion);

            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }
        // 给控件赋值

        String string_song = list.get(i).getSong();
        if (string_song.length() >= 5
                && string_song.substring(string_song.length() - 4,
                string_song.length()).equals(".mp3")){
            //Toast.makeText(context,"No Song in Phone",Toast.LENGTH_SHORT).show();
            //System.out.println("No Song in your Phone");
            holder.song.setText(string_song.trim());
        }
        else {
            holder.song.setText(string_song.trim());
        }

        if (i == position_flag) {
            switch (Theme) {
                case "blue":
                    holder.song.setTextColor(myBlue);
                    holder.singer.setTextColor(myBlue);
                    holder.duration.setTextColor(myBlue);
                    holder.position.setText("");
                    holder.position.setBackgroundResource(R.mipmap.play_small);
                    break;
                case "purple":
                    holder.song.setTextColor(myPurple);
                    holder.singer.setTextColor(myPurple);
                    holder.duration.setTextColor(myPurple);
                    holder.position.setText("");
                    holder.position
                            .setBackgroundResource(R.mipmap.play_small_purple);
                    break;
                case "green":
                    holder.song.setTextColor(myGreeen);
                    holder.singer.setTextColor(myGreeen);
                    holder.duration.setTextColor(myGreeen);
                    holder.position.setText("");
                    holder.position
                            .setBackgroundResource(R.mipmap.play_small_green);
                    break;
                case "red":
                    holder.song.setTextColor(myRed);
                    holder.singer.setTextColor(myRed);
                    holder.duration.setTextColor(myRed);
                    holder.position.setText("");
                    holder.position
                            .setBackgroundResource(R.mipmap.play_small_red);
                    break;
                default:
                    break;
            }

        } else {
            holder.song.setTextColor(Color.BLACK);
            holder.singer.setTextColor(Color.BLACK);
            holder.duration.setTextColor(Color.BLACK);
            holder.position.setText(i + 1 + "");
            holder.position.setBackground(null);
        }


        holder.singer.setText(list.get(i).getSinger().toString().trim());
        // 时间转换为时分秒
        int duration = list.get(i).getDuration();
        String time = MusicUtils.formatTime(duration);
        holder.duration.setText(time);

        return view;

    }
    class ViewHolder{
        TextView song;
        TextView singer;
        TextView duration;
        TextView position;

    }
}
