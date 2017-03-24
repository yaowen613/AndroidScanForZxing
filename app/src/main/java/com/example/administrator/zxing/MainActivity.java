package com.example.administrator.zxing;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xys.libzxing.zxing.activity.CaptureActivity;
import com.xys.libzxing.zxing.encoding.EncodingUtils;

public class MainActivity extends AppCompatActivity {

    //private Button button;
    private EditText inputText;
    private ImageView mImageView;
    private CheckBox mCheckBox;
    private TextView tv_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        //button= (Button) findViewById(R.id.btn_scan);
        inputText = (EditText) findViewById(R.id.et_text);
        mImageView = (ImageView) findViewById(R.id.scan_image);
        mCheckBox = (CheckBox) findViewById(R.id.checkbox_logo);
        tv_result=(TextView)findViewById(R.id.tv_result);
    }

    public void scan(View view) {
        //解析二维码主要代码
        startActivityForResult(new Intent(MainActivity.this, CaptureActivity.class), 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            String result = bundle.getString("result");
            tv_result.setText("掃描的結果為："+ result);
        }
    }

    public void make(View v) {
        String input = inputText.getText().toString();
        if (input.equals("")) {
            Toast.makeText(MainActivity.this, "請輸入有效數據！", Toast.LENGTH_SHORT).show();
        } else {

            //Bitmap bitmap = EncodingUtils.createQRCode(input, 50, 50, null);
            Bitmap bitmap = EncodingUtils.createQRCode(input, 500, 500,
                    mCheckBox.isChecked() ? BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher) :
                            null);
            mImageView.setImageBitmap(bitmap);
        }
    }
}
