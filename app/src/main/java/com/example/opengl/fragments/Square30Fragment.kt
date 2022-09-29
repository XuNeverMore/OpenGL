package com.example.opengl.fragments

import android.opengl.GLSurfaceView
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.opengl.R
import com.example.opengl.shape.Square300
import com.example.opengl.view.MyRenderer30

/**
 *
 * @author xct
 * create on: 2022/9/29 14:22
 *
 */
class Square30Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_square, container, false)
    }

    private val render = MyRenderer30 { Square300() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val glSurfaceView = view.findViewById<GLSurfaceView>(R.id.gl_surface_view)
        glSurfaceView.setEGLContextClientVersion(3)
        glSurfaceView.setRenderer(render)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        render.destroy()
    }

}