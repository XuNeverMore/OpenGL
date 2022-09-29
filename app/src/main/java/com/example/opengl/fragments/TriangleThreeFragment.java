package com.example.opengl.fragments;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.opengl.R;
import com.example.opengl.shape.GLShape;
import com.example.opengl.shape.ShapeCreator;
import com.example.opengl.shape.Triangle300;
import com.example.opengl.view.MyRenderer;
import com.example.opengl.view.MyRenderer30;

/**
 * @author xct
 * create on: 2022/9/29 11:30
 */
public class TriangleThreeFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_triangle30, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        GLSurfaceView  gsv = (GLSurfaceView) view;
        gsv.setEGLContextClientVersion(3);
        MyRenderer30 myRenderer = new MyRenderer30(Triangle300::new);
        gsv.setRenderer(myRenderer);
    }
}
