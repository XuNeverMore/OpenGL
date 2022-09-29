package com.example.opengl.fragments

import android.opengl.GLSurfaceView
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.opengl.R
import com.example.opengl.shape.Triangle300
import com.example.opengl.view.MyRenderer

/**
 *
 * @author xct
 * create on: 2022/9/29 11:22
 *
 */
class Triangle3Fragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_triangle30, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val glSurfaceView = view as GLSurfaceView

        glSurfaceView.setEGLContextClientVersion(3)
        glSurfaceView.setRenderer(MyRenderer { Triangle300() })
    }
}