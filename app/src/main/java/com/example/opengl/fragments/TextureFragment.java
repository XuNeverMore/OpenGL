package com.example.opengl.fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.opengl.R;
import com.example.opengl.texture.MyTextureRender;

/**
 * @author xct
 * create on: 2022/9/20 16:22
 */
public class TextureFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_square, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        GLSurfaceView surfaceView = (GLSurfaceView) view.findViewById(R.id.gl_surface_view);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.sea);
        surfaceView.setEGLContextClientVersion(2);
        surfaceView.setRenderer(new MyTextureRender(bitmap));
    }
}
