package com.example.opengl.texture;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.GLUtils;

import com.example.opengl.MyApp;
import com.example.opengl.MyUtil;
import com.example.opengl.R;
import com.example.opengl.utils.OpenGLUtils;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * @author xct
 * create on: 2022/9/20 16:16
 */
public class MyTextureRender implements GLSurfaceView.Renderer {


    private int[] textureIds;
    private Bitmap mBitmap;
    private int mProgram;
    private BitmapSquare mBitmapSquare;

    private int glTextureId = 0;

    public MyTextureRender(Bitmap bitmap) {
        mBitmap = bitmap;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        mBitmapSquare = new BitmapSquare();

        glTextureId = OpenGLUtils.createTexture(mBitmap,GLES20.GL_NEAREST,GLES20.GL_LINEAR,
                GLES20.GL_CLAMP_TO_EDGE,GLES20.GL_CLAMP_TO_EDGE);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
        mBitmapSquare.draw(glTextureId);
    }
}
