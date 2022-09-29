package com.example.opengl.shape

import android.opengl.GLES30
import android.util.Log
import com.example.opengl.GLES30Util
import com.example.opengl.MyApp
import java.nio.Buffer
import java.nio.ByteBuffer
import java.nio.ByteOrder

/**
 *
 * @author xct
 * create on: 2022/9/29 11:06
 *
 */
class Triangle300 : GLShape {

    private val vertices = floatArrayOf(
        // 位置              // 颜色
        0.0f,  0.5f, 0.0f,  0.0f, 0.0f, 1.0f,    // 顶部
        -0.5f, -0.5f, 0.0f,  0.0f, 1.0f, 0.0f,   // 左下
        0.5f, -0.5f, 0.0f,  1.0f, 0.0f, 0.0f,   // 右下
    )

    private var program: Int
    private var buffer: Buffer
    private var matLoc: Int

    init {

        buffer = ByteBuffer.allocateDirect(vertices.size * 4)
            .order(ByteOrder.nativeOrder())
            .asFloatBuffer()
            .put(vertices)
            .position(0)

        program = GLES30Util.createProgram(MyApp.getContext(), "triangle.glsl")
        Log.i("xct", "program:$program")
        matLoc = GLES30.glGetUniformLocation(program, "uMVPMatrix")

        GLES30.glEnableVertexAttribArray(0)
        GLES30Util.getError("glEnableVertexAttribArray")
        GLES30.glVertexAttribPointer(0, 3, GLES30.GL_FLOAT, false, 6 * 4, buffer)
        GLES30Util.getError("glVertexAttribPointer")

        GLES30.glEnableVertexAttribArray(1)
        GLES30Util.getError("glEnableVertexAttribArray")
        GLES30.glVertexAttribPointer(1, 3, GLES30.GL_FLOAT, false, 6 * 4, buffer)

    }

    override fun draw(mvpMatrix: FloatArray?) {
        GLES30.glUseProgram(program)
        GLES30Util.getError("glUseProgram")
        // Pass the projection and view transformation to the shader
//        GLES20.glUniformMatrix4fv(matLoc, 1, false, mvpMatrix, 0)

//        GLES30.glEnableVertexAttribArray(1)
//        GLES30.glVertexAttribPointer(0,3,GLES30.GL_FLOAT,false,6*4,buffer)
        GLES30.glDrawArrays(GLES30.GL_TRIANGLES, 0, 3)
    }

}