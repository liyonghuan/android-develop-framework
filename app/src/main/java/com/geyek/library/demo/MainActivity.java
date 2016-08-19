package com.geyek.library.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.geyek.library.R;
import com.geyek.library.utils.Base64Util;
import com.geyek.library.utils.UiUtil;

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
                //String base64 = Base64Util.toBase64("今天真是一个好日子.1230=");
                String base64 = Base64Util.fromBase64("5L2g5aW9c2I=");
                Log.d("wsy", base64);
                UiUtil.toast(base64);
                break;
        }
    }
}
