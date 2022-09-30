package com.example.opengl.utils;

import android.graphics.Bitmap;
import android.opengl.GLES20;


public class OpenGLUtils {
    /**
     * 根据bitmap创建2D纹理
     * @param bitmap
     * @param minFilter     缩小过滤类型 (1.GL_NEAREST ; 2.GL_LINEAR)
     * @param magFilter     放大过滤类型
     * @param wrapS         纹理S方向边缘环绕;也称作X方向
     * @param wrapT         纹理T方向边缘环绕;也称作Y方向
     * @return 返回创建的 Texture ID
     */
    public static int createTexture(Bitmap bitmap, int minFilter, int magFilter, int wrapS, int wrapT) {
        int[] textureHandle = createTextures(GLES20.GL_TEXTURE_2D, 1, minFilter, magFilter, wrapS, wrapT);
        if (bitmap != null) {
            // 4.把bitmap加载到纹理中
            android.opengl.GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0);
        }
        return textureHandle[0];
    }

    /**
     * 创建纹理
     * @param textureTarget Texture类型。
     *                      1. 相机用 GLES11Ext.GL_TEXTURE_EXTERNAL_OES
     *                      2. 图片用 GLES20.GL_TEXTURE_2D
     * @param count         创建纹理数量
     * @param minFilter     缩小过滤类型 (1.GL_NEAREST ; 2.GL_LINEAR)
     * @param magFilter     放大过滤类型
     * @param wrapS         纹理S方向边缘环绕;也称作X方向
     * @param wrapT         纹理T方向边缘环绕;也称作Y方向
     * @return 返回创建的 Texture ID
     */
    public static int[] createTextures(int textureTarget, int count, int minFilter, int magFilter, int wrapS,
                                       int wrapT) {
        int[] textureHandles = new int[count];
        for (int i = 0; i < count; i++) {
            // 1.生成纹理
            GLES20.glGenTextures(1, textureHandles, i);
            // 2.绑定纹理
            GLES20.glBindTexture(textureTarget, textureHandles[i]);
            // 3.设置纹理属性
            // 设置纹理的缩小过滤类型（1.GL_NEAREST ; 2.GL_LINEAR）
            GLES20.glTexParameterf(textureTarget, GLES20.GL_TEXTURE_MIN_FILTER, minFilter);
            // 设置纹理的放大过滤类型（1.GL_NEAREST ; 2.GL_LINEAR）
            GLES20.glTexParameterf(textureTarget, GLES20.GL_TEXTURE_MAG_FILTER, magFilter);
            // 设置纹理的X方向边缘环绕
            GLES20.glTexParameteri(textureTarget, GLES20.GL_TEXTURE_WRAP_S, wrapS);
            // 设置纹理的Y方向边缘环绕
            GLES20.glTexParameteri(textureTarget, GLES20.GL_TEXTURE_WRAP_T, wrapT);
        }
        return textureHandles;
    }
}
