package com.example.qrscannerv001;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class MainActivity extends AppCompatActivity {
    EditText qrValue;
    Button btnGenerate, btnScan;
    ImageView qrImg;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        qrValue = findViewById(R.id.qrValue);
        btnGenerate = findViewById(R.id.btnGenerate);
        btnScan = findViewById(R.id.btnScan);
        qrImg = findViewById(R.id.qrImg);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.mymenu, menu);
        return true;
    }

    public void generate(View view) {
        //Generate qr code
        String data = qrValue.getText().toString();
        if (data.isEmpty()) {
            qrValue.setError("Enter Your Data Please");
        } else {
            QRGEncoder qrgEncoder = new QRGEncoder(data, null, QRGContents.Type.TEXT, 500);
            usingBitmap(qrgEncoder);
        }
    }

    private void usingBitmap(QRGEncoder qrgEncoder) {
        try {
            bitmap = qrgEncoder.encodeAsBitmap();
            qrImg.setImageBitmap(bitmap);
        } catch (Exception e) {
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void scan(View view) {
        startActivity(new Intent(getApplicationContext(), myScanner.class));

    }
}
