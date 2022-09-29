package com.example.opengl.shape;

/**
 * @author xct
 * create on: 2022/9/20 15:52
 */
public interface GLShape {

    void draw(float[] mvpMatrix);

    default void destroy(){

    }

}
