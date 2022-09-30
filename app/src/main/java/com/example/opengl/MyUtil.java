package com.example.opengl;

import android.content.Context;
import android.content.res.AssetManager;
import android.opengl.GLES20;
import android.opengl.GLES30;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.IntBuffer;

/**
 * @author xuchuanting
 * Create on 2020/5/20 15:27
 */
public class MyUtil {

    public static void getError(String tag) {
        int error = GLES30.glGetError();
        if (error != GLES30.GL_NO_ERROR) {
            throw new RuntimeException(tag+":" + error);
        }
    }
    /**
     * #vertex #fragment shader在一个文件中
     *
     * @param context    Context
     * @param assetsPath assets路径
     * @return 两个shader字符串数组，0 ：vertex 1:fragment
     */
    public static String[] getShaders(Context context, String assetsPath) {
        StringBuilder sbVertex = new StringBuilder();
        StringBuilder sbFragment = new StringBuilder();
        InputStream inputStream = null;
        try {
            inputStream = context.getAssets().open(assetsPath);
            InputStreamReader reader = new InputStreamReader(inputStream);
            BufferedReader re = new BufferedReader(reader);
            String str;
            StringBuilder sbCurrent = null;
            while ((str = re.readLine()) != null) {
                if (str.contains("#")) {
                    sbCurrent = str.toLowerCase().contains("vertex") ? sbVertex : sbFragment;
                } else {
                    if (sbCurrent != null) {
                        sbCurrent.append(str).append("\n");
                    }
                }
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
        return new String[]{sbVertex.toString(), sbFragment.toString()};
    }

    public static int createProgram(Context context, String assetsPath) {
        String[] shaders = getShaders(context, assetsPath);
        int program = GLES20.glCreateProgram();
        int shaderVertex = loadShader(GLES20.GL_VERTEX_SHADER, shaders[0]);
        int shaderFragment = loadShader(GLES20.GL_FRAGMENT_SHADER, shaders[1]);
        GLES20.glAttachShader(program, shaderVertex);
        GLES20.glAttachShader(program, shaderFragment);
        GLES20.glLinkProgram(program);
        return program;
    }

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

    public static int loadShader(int type, String shaderCode) {

        // create a vertex shader type (GLES20.GL_VERTEX_SHADER)
        // or a fragment shader type (GLES20.GL_FRAGMENT_SHADER)
        int shader = GLES20.glCreateShader(type);

        // add the source code to the shader and compile it
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);
        IntBuffer intBuffer = IntBuffer.allocate(1);
        GLES30.glGetShaderiv(shader, GLES30.GL_COMPILE_STATUS, intBuffer);
        if (intBuffer.get(0) == GLES30.GL_FALSE) {
            Log.e("xct", "compile shader:\n"
                    + shaderCode + "\n error:"
                    + GLES30.glGetShaderInfoLog(shader));
        }
        return shader;
    }
}
