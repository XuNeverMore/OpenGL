#shader vertex
uniform mat4 uMVPMatrix;
attribute vec4 vPosition;
void main(){
    gl_Position = uMVPMatrix * vPosition;
}

#shader fragment
precision mediump float;
uniform vec4 vColor;
void main(){
    gl_FragColor = vColor;
}