package com.example.opengl.shape

import android.opengl.GLES20
import android.opengl.GLES30
import android.util.Log
import com.example.opengl.GLES30Util
import com.example.opengl.MyApp
import java.nio.Buffer
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer

/**
 *
 * @author xct
 * create on: 2022/9/29 11:06
 *
 */
class Square300 : GLShape {

    private val vertices = floatArrayOf(
        -1f, 1f, 0.0f,       // top left
        -1f, -1f, 0.0f,  // bottom left
        1f, -1f, 0.0f,  // bottom right
        1f, 1f, 0.0f,  // top right
    )
    private val drawOrder = shortArrayOf(0, 1, 2, 0, 2, 3)
    private var program: Int
    private var buffer: Buffer
    private var matLoc: Int
    private var drawBuffer: Buffer

    private val colors = floatArrayOf(
        1.0f, 0.0f, 0.0f, 1.0f,
        0.0f, 1.0f, 0.0f, 1.0f,
        0.0f, 0.0f, 1.0f, 1.0f,
        1.0f, 1.0f, 0.0f, 0.0f
    )


    private val countPerVertex = 3
    val colorBuffer: Buffer = ByteBuffer.allocateDirect(colors.size * 4)
        .order(ByteOrder.nativeOrder())
        .asFloatBuffer().put(colors).position(0)

    init {

        buffer = ByteBuffer.allocateDirect(vertices.size * 4)
            .order(ByteOrder.nativeOrder())
            .asFloatBuffer()
            .put(vertices)
            .position(0)

        drawBuffer = ByteBuffer.allocateDirect(drawOrder.size * 2)
            .order(ByteOrder.nativeOrder())
            .asShortBuffer()
            .put(drawOrder)
            .position(0)

        program = GLES30Util.createProgram(MyApp.getContext(), "square.glsl")
        Log.i("xct", "program:$program")
        matLoc = GLES30.glGetUniformLocation(program, "uMVPMatrix")

    }


    override fun draw(mvpMatrix: FloatArray?) {

        GLES30.glUseProgram(program)
        GLES30Util.getError("glUseProgram")

        GLES30.glEnableVertexAttribArray(0)
        GLES30Util.getError("glEnableVertexAttribArray")
        GLES30.glVertexAttribPointer(0, 3, GLES30.GL_FLOAT, false, countPerVertex * 4, buffer)
        GLES30Util.getError("glVertexAttribPointer")


        GLES30.glEnableVertexAttribArray(1)
        GLES30Util.getError("glEnableVertexAttribArray")
        GLES30.glVertexAttribPointer(1, 4, GLES30.GL_FLOAT, false, 4 * 4, colorBuffer)


        // Pass the projection and view transformation to the shader
//        GLES20.glUniformMatrix4fv(matLoc, 1, false, mvpMatrix, 0)

//        GLES30.glEnableVertexAttribArray(1)
//        GLES30.glVertexAttribPointer(0,3,GLES30.GL_FLOAT,false,6*4,buffer)
//        GLES30.glDrawArrays(GLES30.GL_TRIANGLES, 0, 4)
        GLES30.glDrawElements(
            GLES20.GL_TRIANGLES,
            drawOrder.size,
            GLES20.GL_UNSIGNED_SHORT,
            drawBuffer
        )
    }

    override fun destroy() {
        GLES30.glDeleteProgram(program)
    }

}