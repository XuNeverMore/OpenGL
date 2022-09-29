precision mediump float;
//uniform vec4 vColor;
varying vec3 vColor;
void main(){
    gl_FragColor = vec4(vColor,1.0f);
}