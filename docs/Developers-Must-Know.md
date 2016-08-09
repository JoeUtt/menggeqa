# 项目须知 #

#### 使用[IntelliJ IDEA](https://www.jetbrains.com/idea/) ####

- 将compiler修改为Eclipse,如下图

![eclipse compiler](https://cloud.githubusercontent.com/assets/4927589/14228367/6fce184e-f91b-11e5-837c-2673446d24ea.png)

#### 使用Eclipse ####

- 安装Luna或更高级版本

#### 使用[Google Java Style](http://google-styleguide.googlecode.com/svn/trunk/javaguide.html)编码标准 ####

* 克隆项目[Google Style Guide](https://github.com/google/styleguide.git)

**Intellij IDEA** 
`Files -> Other Settings -> Default Settings ->Editor -> Code Style -> Manage -> Manage -> Import -> eclipse-java-google-style.xml -> Apply`

**Eclipse IDE** 
`Preferences -> Java -> Code Style -> Formatter -> Import -> eclipse-java-google-style.xml -> Apply`

* Reformat后提交

## 代码风格 ##

请保持一致的代码风格

### 如何执行代码风格检查 

- 执行

`mvn clean site`

或者在IntelliJ IDEA的Maven Project中选中clean和site执行build.

- 打开文件
 
`{project__folder}/target/site/index.html`

然后

![](https://cloud.githubusercontent.com/assets/4927589/14588981/d9eef6f6-04df-11e6-9c6f-9bbd2bed3400.png)

然后

![](https://cloud.githubusercontent.com/assets/4927589/14588999/29ca76e6-04e0-11e6-8647-d868ab185682.png)

选择`Checkstyle`. 出现errors请修复, 请保持尽可能少的warnings. 

![](https://cloud.githubusercontent.com/assets/4927589/14589025/e817bed8-04e0-11e6-9eb9-8987f24672e0.png)

## 代码覆盖率 ##

测试被`maven-surefire-plugin`成功运行后,`jacoco-maven-plugin`插件就会生成此报告.

**Intellij IDEA**查看报告
`Analyse -> Show Coverage Data -> "+" -> 选择${basedir}/target/coverage-reports/jacoco-unit.exec -> Show Selected`

