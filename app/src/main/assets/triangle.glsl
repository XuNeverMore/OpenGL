#shader vertex
#version 300 es
//uniform mat4 uMVPMatrix;
layout(location = 0) in vec3 aPosition;
layout(location = 1) in  vec3 aColor;
out vec3 vColor;
void main(){
    gl_Position = /*uMVPMatrix * */vec4(aPosition,1.0);
    vColor = aColor;
}

#shader fragment
#version 300 es
out vec4 FragColor;
in vec3 vColor;
void main(){
    FragColor = vec4(vColor,1.0f);
}