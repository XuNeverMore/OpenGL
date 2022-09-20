package com.example.opengl.shape;

import android.content.Context;
import android.opengl.GLES20;
import android.util.Log;

import com.example.opengl.MyApp;
import com.example.opengl.MyUtil;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * @author xuchuanting
 * Create on 2020/5/20 15:33
 */
public class Triangle implements GLShape{

    // number of coordinates per vertex in this array
    static final int COORDS_PER_VERTEX = 3;
    static float[] triangleCoords = {   // in counterclockwise order:
            0.0f, 0.622008459f, 0.0f, // top
            -0.5f, -0.311004243f, 0.0f, // bottom left
            0.5f, -0.311004243f, 0.0f  // bottom right
    };
    private final int mProgram;
    // Set color with red, green, blue and alpha (opacity) values
    float color[] = {0.63671875f, 0.76953125f, 0.22265625f, 1.0f};
    private FloatBuffer vertexBuffer;
    private int mPositionHandle;

    private final int vertexCount = triangleCoords.length / COORDS_PER_VERTEX;
    private final int vertexStride = COORDS_PER_VERTEX * 4; // 4 bytes per vertex
    private int mColorHandle;
    private int vPMatrixHandle;

    public Triangle() {
        // initialize vertex byte buffer for shape coordinates
        ByteBuffer bb = ByteBuffer.allocateDirect(
                // (number of coordinate values * 4 bytes per float)
                triangleCoords.length * 4);
        // use the device hardware's native byte order
        bb.order(ByteOrder.nativeOrder());

        // create a floating point buffer from the ByteBuffer
        vertexBuffer = bb.asFloatBuffer();
        // add the coordinates to the FloatBuffer
        vertexBuffer.put(triangleCoords);
        // set the buffer to read the first coordinate
        vertexBuffer.position(0);

        Context context = MyApp.getContext();
        int shaderVertex = MyUtil.loadShader(GLES20.GL_VERTEX_SHADER, MyUtil.getStringFromAssets(context, "triangle_vertex_shader.glsl"));
        int shaderFragment = MyUtil.loadShader(GLES20.GL_FRAGMENT_SHADER, MyUtil.getStringFromAssets(context, "triangle_fragment_shader.glsl"));

        mProgram = GLES20.glCreateProgram();
        GLES20.glAttachShader(mProgram, shaderVertex);
        GLES20.glAttachShader(mProgram, shaderFragment);
        GLES20.glLinkProgram(mProgram);

    }

    public void draw(float[] mvpMatrix){
        GLES20.glUseProgram(mProgram);
        mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");
        Log.i("Triangle", "vposition:"+mPositionHandle);
        GLES20.glEnableVertexAttribArray(mPositionHandle);
        GLES20.glVertexAttribPointer(mPositionHandle,COORDS_PER_VERTEX,GLES20.GL_FLOAT,false,vertexStride,vertexBuffer);
        mColorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");
        GLES20.glUniform4fv(mColorHandle,1,color,0);


        // get handle to shape's transformation matrix
        vPMatrixHandle = GLES20.glGetUniformLocation(mProgram, "uMVPMatrix");

        // Pass the projection and view transformation to the shader
        GLES20.glUniformMatrix4fv(vPMatrixHandle, 1, false, mvpMatrix, 0);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES,0,vertexCount);
        GLES20.glDisableVertexAttribArray(mPositionHandle);
    }
}
