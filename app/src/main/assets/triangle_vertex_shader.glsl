uniform mat4 uMVPMatrix;
attribute vec4 vPosition;
attribute vec3 aColor;
varying vec3 vColor;
void main(){
    gl_Position = uMVPMatrix * vPosition;
    vColor = aColor;
}