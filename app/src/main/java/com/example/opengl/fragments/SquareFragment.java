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
import com.example.opengl.shape.Square;
import com.example.opengl.view.MyRenderer;

/**
 * @author xct
 * create on: 2022/9/20 15:50
 */
public class SquareFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_square, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        GLSurfaceView surfaceView = (GLSurfaceView) view.findViewById(R.id.gl_surface_view);
        surfaceView.setEGLContextClientVersion(2);
        MyRenderer myRenderer = new MyRenderer(new ShapeCreator() {
            @Override
            public GLShape createShape() {
                return new Square();
            }
        });
        surfaceView.setRenderer(myRenderer);
    }
}
