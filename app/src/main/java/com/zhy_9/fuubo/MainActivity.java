package com.zhy_9.fuubo;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy_9.fuubo.util.FileUtil;

import java.io.File;
import java.io.IOException;


public class MainActivity extends ActionBarActivity {

    private Toolbar toolbar;
    private SwipeRefreshLayout refreshLayout;
    private TextView text;
    private Button btn;
    private float currentX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        if (android.os.Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }

        Point point = new Point(0, 0);
        Point point1 = new Point(1000, 1000);
        ValueAnimator valueAnimator = ValueAnimator.ofObject(new PointEvaluator(), point, point1);
        valueAnimator.setDuration(1000);
        valueAnimator.start();

        text = (TextView) findViewById(R.id.text);
        currentX = text.getTranslationX();

        btn = (Button) findViewById(R.id.click);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animator animator = AnimatorInflater.loadAnimator(MainActivity.this, R.animator.animator_set);
                animator.setTarget(text);
                ObjectAnimator colorAnim = ObjectAnimator.ofInt(text, "textColor", R.color.material_color);
                ObjectAnimator transAnim = ObjectAnimator.ofFloat(text, "translationX", -500f, currentX);
                ObjectAnimator alphAnim = ObjectAnimator.ofFloat(text, "alpha", 0.3f, 1f);
                ObjectAnimator rotationAnim = ObjectAnimator.ofFloat(text, "rotation", 0f, 360f);
                AnimatorSet set = new AnimatorSet();
                set.play(transAnim).with(alphAnim).with(colorAnim).before(rotationAnim);
                set.setDuration(3000);
                set.start();
            }
        });


        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh_layout);
//        refreshLayout.setColorSchemeColors(android.R.color.holo_blue_dark, android.R.color.holo_blue_light,
//                android.R.color.holo_green_light, android.R.color.holo_orange_light);//4.4系统上此方法不起作用
        refreshLayout.setColorScheme(android.R.color.holo_blue_dark, android.R.color.holo_blue_light,
                android.R.color.holo_green_light, android.R.color.holo_orange_light);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshLayout.setRefreshing(true);
                showToast();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.setRefreshing(false);

                    }
                }, 2500);
            }
        });

        String path = FileUtil.getSDPath() + File.separator + "cache";
//        String fileContent = "Hello world!";
        String fileContent = "今天天气很好！";
        try {
            FileUtil.writeFileOnSDCard(path, fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.e("path", path);
        try {
            String readFileContent = FileUtil.readFileFromSDCard(path);
            Log.e("readContent", readFileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void showToast() {
        LayoutInflater inflater = getLayoutInflater();
        View toastLayout = inflater.inflate(R.layout.toast_layout, null);
        TextView toastText = (TextView) toastLayout.findViewById(R.id.toast_content);
        toastText.setText("弹出消息！");
        Toast toast = new Toast(this);
        toast.setView(toastLayout);
        toast.setGravity(Gravity.RELATIVE_HORIZONTAL_GRAVITY_MASK, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
    }


}
