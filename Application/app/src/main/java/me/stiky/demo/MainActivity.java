package me.stiky.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import me.stiky.utlis.R;
import me.stiky.utlis.P2PUrlUtil;
import me.stiky.utlis.UiUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View toast = findViewById(R.id.main_btn_toast);

        toast.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_btn_toast:
                String base64 = P2PUrlUtil.smartToBase64("ed2k://|file|%E4%BB%BB%E5%8A%A1%E8%BF%B7%E8%B5%B072%E5%B0%8F%E6%97%B6.BD1280%E9%AB%98%E6%B8%85%E4%B8%AD%E8%8B%B1%E5%8F%8C%E5%AD%97.mp4|1014685997|7B85B1C8A9F3E1BDB80D7B58414A2FD8|h=7YOPDAIM54NR7PL55VGFRFB5EAEGNI2G|/", P2PUrlUtil.FLASHGET);
                Log.d("wsy", base64);
                UiUtil.toast(base64);
                break;
        }
    }
}
