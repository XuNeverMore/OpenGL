package com.example.opengl;

import android.content.Context;
import android.content.res.AssetManager;
import android.opengl.GLES20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author xuchuanting
 * Create on 2020/5/20 15:27
 */
public class MyUtil {

    public static String getStringFromAssets(Context context, String filePath) {
        InputStream inputStream = null;
        StringBuilder sb = new StringBuilder();
        try {
            inputStream = context.getAssets().open(filePath);
            InputStreamReader reader = new InputStreamReader(inputStream);
            BufferedReader re = new BufferedReader(reader);
            String str;
            while ((str = re.readLine()) != null) {
                sb.append(str).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

    public static int loadShader(int type, String shaderCode){

        // create a vertex shader type (GLES20.GL_VERTEX_SHADER)
        // or a fragment shader type (GLES20.GL_FRAGMENT_SHADER)
        int shader = GLES20.glCreateShader(type);

        // add the source code to the shader and compile it
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);

        return shader;
    }
}
