package com.example.opengl.view;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.os.SystemClock;

import com.example.opengl.shape.GLShape;
import com.example.opengl.shape.ShapeCreator;
import com.example.opengl.shape.Square;
import com.example.opengl.shape.Triangle;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * @author xuchuanting
 * Create on 2020/5/20 15:36
 */
public class MyRenderer implements GLSurfaceView.Renderer {


    private GLShape mGLShape;

    private ShapeCreator mShapeCreator;

    public MyRenderer(ShapeCreator shapeCreator) {
        mShapeCreator = shapeCreator;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        mGLShape = mShapeCreator.createShape();
    }
    // vPMatrix is an abbreviation for "Model View Projection Matrix"
    private final float[] vPMatrix = new float[16];
    private final float[] projectionMatrix = new float[16];
    private final float[] viewMatrix = new float[16];

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES20.glViewport(0, 0, width, height);

        float ratio = (float) width / height;

        // this projection matrix is applied to object coordinates
        // in the onDrawFrame() method
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        Matrix.frustumM(projectionMatrix, 0, -ratio, ratio, -1, 1, 3, 7);
//        Matrix.orthoM(projectionMatrix,0, -ratio, ratio, -1, 1,1,7);
    }


    public volatile float mAngle;

    public float getAngle() {
        return mAngle;
    }

    public void setAngle(float angle) {
        mAngle = angle;
    }

    private final float[] rotationMatrix = new float[16];

    @Override
    public void onDrawFrame(GL10 gl) {
        float[] scratch = new float[16];

        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);


        // Set the camera position (View matrix)
        Matrix.setLookAtM(viewMatrix, 0, 0, 0, -3, 0f, 0f, 0f, 0f, 1.0f, 0.0f);

        // Calculate the projection and view transformation
        Matrix.multiplyMM(vPMatrix, 0, projectionMatrix, 0, viewMatrix, 0);


        // Create a rotation for the triangle
        // long time = SystemClock.uptimeMillis() % 4000L;
        // float angle = 0.090f * ((int) time);
        Matrix.setRotateM(rotationMatrix, 0, mAngle, 0, 0, -1.0f);

        // Combine the rotation matrix with the projection and camera view
        // Note that the vPMatrix factor *must be first* in order
        // for the matrix multiplication product to be correct.
        Matrix.multiplyMM(scratch, 0, vPMatrix, 0, rotationMatrix, 0);

        // Draw triangle
//        mTriangle.draw(scratch);

        //draw rect
        mGLShape.draw(scratch);
    }
}
