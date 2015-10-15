package com.example.shuang.animator;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stephen on 2015/9/17.
 */
public class SecondActivity extends Activity implements AdapterView.OnItemClickListener {

    private static final String TAG = "Stephen";
    private RelativeLayout layout;
    private LayoutAnimationController lac;
    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        lac = new LayoutAnimationController(AnimationUtils.loadAnimation(this, R.anim.viewanim));
        lac.setOrder(LayoutAnimationController.ORDER_NORMAL);
        gridView.setLayoutAnimation(lac);
    }

    private void initViews() {
        layout = (RelativeLayout) findViewById(R.id.mainlayout);
        gridView = (GridView) findViewById(R.id.gridview);
        gridView.setAdapter(new MyAdapter(this));
        gridView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


        finish();
    }

    private class MyAdapter extends BaseAdapter {

        private Context context;
        private int[] res = new int[]{
                R.drawable.test1,
                R.drawable.test2,
                R.drawable.test3,
                R.drawable.test4,
                R.drawable.test5,
                R.drawable.test6,
                R.drawable.test7,
                R.drawable.test1,
                R.drawable.test1};
        private List<ImageView> lists = new ArrayList<>();

        public MyAdapter(Context context) {
            this.context = context;

        }


        @Override
        public int getCount() {
            return res.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
//            ImageView imageView;
//            if (convertView == null) {
//                imageView = new ImageView(context);
//                imageView.setLayoutParams(new GridView.LayoutParams(100, 100));
//                imageView.setAdjustViewBounds(true);
//            } else {
//                imageView = (ImageView) convertView;
//            }
//            imageView.setImageResource(res[position]);
//
//            return imageView;
            ViewHolder viewHolder = null;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = new ImageView(context);
                viewHolder.imageView = (ImageView) convertView;
                viewHolder.imageView.setLayoutParams(new GridView.LayoutParams(100, 100));
                viewHolder.imageView.setImageResource(res[position]);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            return convertView;
        }
    }

    class ViewHolder {
        ImageView imageView;
    }
}
