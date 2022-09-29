#shader vertex
#version 300 es
//uniform mat4 uMVPMatrix;
layout(location = 0) in vec3 aPosition;
layout(location = 1) in  vec4 aColor;
out vec4 vColor;
void main(){
    vec4 poi = vec4(aPosition, 1.0f);
    gl_Position = /*uMVPMatrix **/ poi;
    vColor = aColor;
}

#shader fragment
#version 300 es
//precision mediump float;
in vec4 vColor;
out vec4 oColor;
void main(){
    oColor = vColor;
}