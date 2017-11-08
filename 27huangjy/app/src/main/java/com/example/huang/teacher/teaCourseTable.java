package com.example.huang.teacher;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huang.a27huangjy.R;
/**
 * Created by huang on 2017/5/13.
 */
public class teaCourseTable extends BaseAdapter {

    private Context mContext;

    private String[][] contents;

    private int rowTotal;

    private int columnTotal;

    private int positionTotal;

    public teaCourseTable(Context context) {
        this.mContext = context;
    }

    public int getCount() {
        return positionTotal;
    }

    public long getItemId(int position) {
        return position;
    }

    public Object getItem(int position) {
        //求余得到二维索引
        int column = position % columnTotal;
        //求商得到二维索引
        int row = position / columnTotal;
        return contents[row][column];
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.grid_item, null);
        }
        TextView textView = (TextView) convertView.findViewById(R.id.text);
        String str = "";
        //如果有课,那么添加数据
        if (!("".equals(getItem(position)))) {

            textView.setText((String) getItem(position));
            textView.setTextColor(Color.BLACK);
        //变换颜色
        int rand = position % 7;
        switch (rand) {
            case 0:
                textView.setBackground(mContext.getResources().getDrawable(R.drawable.grid_item_bg));
                break;
            case 1:
                textView.setBackground(mContext.getResources().getDrawable(R.drawable.bg_12));
                break;
            case 2:
                textView.setBackground(mContext.getResources().getDrawable(R.drawable.bg_13));
                break;
            case 3:
                textView.setBackground(mContext.getResources().getDrawable(R.drawable.bg_14));
                break;
            case 4:
                textView.setBackground(mContext.getResources().getDrawable(R.drawable.bg_15));
                break;
            case 5:
                textView.setBackground(mContext.getResources().getDrawable(R.drawable.bg_16));
                break;
            case 6:
                textView.setBackground(mContext.getResources().getDrawable(R.drawable.bg_17));
                break;
            case 7:
                textView.setBackground(mContext.getResources().getDrawable(R.drawable.bg_18));
                break;
        }
    }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int row = position / columnTotal;
                int column = position % columnTotal;
                String con = "当前选中的是" + contents[row][column] + "课";
                Toast.makeText(mContext, con, Toast.LENGTH_SHORT).show();
            }
        });
          return convertView;
    }

    /**
     * 设置内容、行数、列数
     */
    public void setContent(String[][] contents, int row, int column) {
        this.contents = contents;
        this.rowTotal = row;
        this.columnTotal = column;
        positionTotal = rowTotal * columnTotal;
    }


}



