#shader vertex
attribute vec4 aPosition;
attribute vec4 aTextureCoord;
varying vec2 vTextureCoord;
void main(){
    gl_Position = aPosition;
    vTextureCoord = aTextureCoord.xy;
}

#shader fragment
varying highp vec2 vTextureCoord;
uniform sampler2D inputTexture;
void main(){
    gl_FragColor = texture2D(inputTexture,vTextureCoord);
}
