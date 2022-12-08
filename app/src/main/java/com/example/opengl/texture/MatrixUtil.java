package com.example.opengl.texture;

/**
 * @author xct
 * create on: 2022/12/8 11:45
 */
public class MatrixUtil {


    /**
     * 图像在屏幕中间最大化显示时，缩放比例
     *
     * @param screenWidth  屏幕宽
     * @param screenHeight 屏幕高
     * @param imageWidth   图像宽
     * @param imageHeight  图像高
     * @return x, y 缩放 (0,1]
     */
    public static float[] getDesireScalesInFullCenter(int screenWidth, int screenHeight, int imageWidth, int imageHeight) {
        float[] ret = {1f, 1f};
        if (screenWidth <= 0 || screenHeight <= 0 || imageWidth <= 0 || imageHeight <= 0) {
            return ret;
        }
        float ratioScreen = 1f * screenWidth / screenHeight;
        float ratioImage = 1f * imageWidth / imageHeight;
        if (ratioScreen > ratioImage) {//以屏幕高位基准缩放宽
            ret[0] = ratioImage * screenHeight / screenWidth;
        } else if (ratioScreen < ratioImage) {//以屏幕宽位基准缩放高
            ret[1] = screenWidth / ratioImage / screenHeight;
        }

        return ret;
    }
}
