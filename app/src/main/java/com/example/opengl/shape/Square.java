package com.example.opengl.shape;

import android.content.Context;
import android.opengl.GLES20;
import android.util.Log;

import com.example.opengl.MyApp;
import com.example.opengl.MyUtil;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

/**
 * @author xuchuanting
 * Create on 2020/5/20 15:34
 */
public class Square implements GLShape {
    private FloatBuffer vertexBuffer;
    private ShortBuffer drawListBuffer;

    // number of coordinates per vertex in this array
    static final int COORDS_PER_VERTEX = 3;
    static final float squareCoords[] = {
            -1f, 1f, 0.0f,      // top left
            -1f, -1f, 0.0f,      // bottom left
            1f, -1f, 0.0f,      // bottom right
            1f, 1f, 0.0f       // top right
    };

    float color[] = {0f, 1f, 1f, 1.0f};
    private final short drawOrder[] = {0, 1, 2, 0, 2, 3}; // order to draw vertices
    private final int mProgram;
    private int mPositionHandle;
    private int mColorHandle;
    int vertexStride = COORDS_PER_VERTEX * 4;
    private int mVPMatrixHandle;

    public Square() {
        // initialize vertex byte buffer for shape coordinates
        ByteBuffer bb = ByteBuffer.allocateDirect(
                // (# of coordinate values * 4 bytes per float)
                squareCoords.length * 4);
        bb.order(ByteOrder.nativeOrder());
        vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(squareCoords);
        vertexBuffer.position(0);

        // initialize byte buffer for the draw list
        ByteBuffer dlb = ByteBuffer.allocateDirect(
                // (# of coordinate values * 2 bytes per short)
                drawOrder.length * 2);
        dlb.order(ByteOrder.nativeOrder());
        drawListBuffer = dlb.asShortBuffer();
        drawListBuffer.put(drawOrder);
        drawListBuffer.position(0);

        Context context = MyApp.getContext();
        mProgram = MyUtil.createProgram(context,"square20.glsl");
    }

    public void draw(float[] mvpMatrix) {
        GLES20.glUseProgram(mProgram);

        mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");
        mColorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");
        mVPMatrixHandle = GLES20.glGetUniformLocation(mProgram, "uMVPMatrix");

        GLES20.glVertexAttribPointer(mPositionHandle, COORDS_PER_VERTEX, GLES20.GL_FLOAT, false, vertexStride, vertexBuffer);
        GLES20.glEnableVertexAttribArray(mPositionHandle);
        GLES20.glUniform4fv(mColorHandle, 1, color, 0);

        // Pass the projection and view transformation to the shader
        GLES20.glUniformMatrix4fv(mVPMatrixHandle, 1, false, mvpMatrix, 0);

//        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, 4);

        GLES20.glDrawElements(GLES20.GL_TRIANGLES, drawOrder.length, GLES20.GL_UNSIGNED_SHORT, drawListBuffer);

        GLES20.glDisableVertexAttribArray(mPositionHandle);

    }
}
