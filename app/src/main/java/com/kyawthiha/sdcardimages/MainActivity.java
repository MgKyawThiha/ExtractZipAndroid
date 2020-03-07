package com.kyawthiha.sdcardimages;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.progress.ProgressMonitor;

import java.io.File;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn=findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String source = "/sdcard/documents/test.zip";
                    String destination = "/sdcard/documents";
                    String videoPath="/sdcard/documents/test/191.jpg";
                    String password = "test";

                    ZipFile zipFile = new ZipFile(source);
                    if (zipFile.isEncrypted()) {
                        zipFile.setPassword(password);
                    }
                    zipFile.extractAll(destination);
                    ProgressMonitor progressMonitor = zipFile.getProgressMonitor();
                    if(progressMonitor.getPercentDone()==100){
                        File f = new File(videoPath);
                        ImageView mImgView1 = (ImageView)findViewById(R.id.imageview);
                        Bitmap bmp = BitmapFactory.decodeFile(f.getAbsolutePath());
                        mImgView1.setImageBitmap(bmp);
                        Toast.makeText(v.getContext(),"done",Toast.LENGTH_LONG).show();
                    }
                } catch (ZipException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
