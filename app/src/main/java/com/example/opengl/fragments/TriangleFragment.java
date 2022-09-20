package com.example.opengl.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.opengl.R;
import com.example.opengl.view.MyGL;

/**
 * @author xct
 * create on: 2022/9/20 15:41
 */
public class TriangleFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_triangle, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MyGL surfaceView = (MyGL) view.findViewById(R.id.gl_surface_view);
//        surfaceView.setEGLContextClientVersion(2);
//        MyRenderer myRenderer = new MyRenderer(new Triangle());
//        surfaceView.setRenderer(myRenderer);
    }
}
