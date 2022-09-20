# OpenGL

## 齐次坐标

目前为止，我们仍然把三维顶点视为三元组(x,y,z)。现在引入一个新的分量w，得到向量(x,y,z,w)。请先记住以下两点（稍后我们会给出解释）：

若w==1，则向量(x, y, z, 1)为空间中的点。
若w==0，则向量(x, y, z, 0)为方向。
（请务必将此牢记在心。）

二者有什么区别呢？对于旋转，这点区别倒无所谓。当您旋转点和方向时，结果是一样的。但对于平移（将点沿着某个方向移动）情况就不同了。”平移一个方向”是毫无意义的。

齐次坐标使得我们可以用同一个公式对点和方向作运算。

## 单位矩阵（Identity matrix）

单位矩阵很特殊，它什么也不做。单位矩阵的身份和自然数”1”一样基础而重要，因此在这里要特别提及一下。

## 累积变换
```java
TransformedVector = TranslationMatrix * RotationMatrix * ScaleMatrix * OriginalVector;
```
**注意**这行代码首先执行缩放，接着旋转，最后才是平移。这就是矩阵乘法的工作方式。

## 复合变换：模型观察投影矩阵（MVP）
- 第一步：创建模型观察投影（MVP）矩阵。任何要渲染的模型都要做这一步。
```
// Projection matrix : 45° Field of View, 4:3 ratio, display range : 0.1 unit <-> 100 units
glm::mat4 Projection = glm::perspective(glm::radians(45.0f), (float) width / (float)height, 0.1f, 100.0f);

// Or, for an ortho camera :
//glm::mat4 Projection = glm::ortho(-10.0f,10.0f,-10.0f,10.0f,0.0f,100.0f); // In world coordinates

// Camera matrix
glm::mat4 View = glm::lookAt(
    glm::vec3(4,3,3), // Camera is at (4,3,3), in World Space
    glm::vec3(0,0,0), // and looks at the origin
    glm::vec3(0,1,0)  // Head is up (set to 0,-1,0 to look upside-down)
    );

// Model matrix : an identity matrix (model will be at the origin)
glm::mat4 Model = glm::mat4(1.0f);
// Our ModelViewProjection : multiplication of our 3 matrices
glm::mat4 mvp = Projection * View * Model; // Remember, matrix multiplication is the other way around
```

- 把MVP传给GLSL
```java
// Get a handle for our "MVP" uniform
// Only during the initialisation
GLuint MatrixID = glGetUniformLocation(programID, "MVP");

// Send our transformation to the currently bound shader, in the "MVP" uniform
// This is done in the main loop since each model will have a different MVP matrix (At least for the M part)
glUniformMatrix4fv(MatrixID, 1, GL_FALSE, &mvp[0][0]);
```

- 第三步：在GLSL中用MVP变换顶点
```java
// Input vertex data, different for all executions of this shader.
layout(location = 0) in vec3 vertexPosition_modelspace;

// Values that stay constant for the whole mesh.
uniform mat4 MVP;

void main(){
  // Output position of the vertex, in clip space : MVP * position
  gl_Position =  MVP * vec4(vertexPosition_modelspace,1);
}
```