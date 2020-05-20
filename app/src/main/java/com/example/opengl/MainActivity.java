package com.example.opengl;

import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.example.opengl.view.MyGL;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        MyGL gl = findViewById(R.id.my_gl);

        String ts = MyUtil.getStringFromAssets(this, "triangle_fragment_shader.glsl");
        Log.i(TAG, "onCreate: "+ts);
        gl.start();
    }

    private static final String TAG = "MainActivity";
}
