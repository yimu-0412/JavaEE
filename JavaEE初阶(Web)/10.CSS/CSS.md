## 一、CSS 是什么

&emsp;&emsp;层叠样式表（Cascading Style Sheets);

&emsp;&emsp;CSS 能够对网页中元素位置的**排版进行像素级精确控制, 实现美化页面的效果， 能够做到页面的样式和结构分离**。

## 二、基本语法规范

&emsp;&emsp;选择器 + {一条/N条声明}

* 选择器决定针对什么修改（找谁）
* 声明决定修改内容
* 声明的属性是键值对。使用“；”区分键值对，使用“：”区分键和值。

```
    <style>
    p {
        /* 设置字体颜色 */
        color: red;
        /* 设置字体大小 */
        font-size: 30px;
    }
    </style>

    <p>hello</p>
```
&emsp;&emsp;**注意**：
1. CSS 要写到 ``sytle``标签中
2. ``style``标签可以放到页面的任意位置，一般放到``head``标签中
3. CSS 使用``/* */``作为注释（使用 ``ctrl + /``快速切换）
4. 
## 三、引入方式
### 1、内部样式表

&emsp;&emsp;写在 ``style``标签中，嵌入到 ``html``内部。

&emsp;&emsp;理论上``style``放到``html``的任意位置都可以，但是一般放到``head``标签中。

&emsp;&emsp;**优点**：能够让样式和页面结构分离。
&emsp;&emsp;**缺点**：分离的不够彻底，尤其是 CSS 内容较多的时候。

### 2、行内样式表

&emsp;&emsp;通过 ``style``属性，来指定某个标签的样式。

&emsp;&emsp;只适合于写简单样式，只针对某个变迁生效。

&emsp;&emsp;**缺点**：不能写复杂的样式，这种写法的优先级比较高，会覆盖其他的样式。

```
    <style>
        div {
            color: red;
        }
    </style>

    <div style="color:green">想要生活过的去, 头上总得带点绿</div>
```
![行内样式表](https://raw.githubusercontent.com/yimu-0412/image/master/image/%E8%A1%8C%E5%86%85%E6%A0%B7%E5%BC%8F%E8%A1%A8.png)

&emsp;&emsp;可以看到，``red`` 的颜色被改变了。

### 3、外部样式

&emsp;&emsp;实际开放中最常用的方法：
1. 创建一个 css 文档
2. 使用 link 标签引入 css。

```
<link rel="stylesheet" href="[CSS文件路径]">
```
创建 ``deml.html``

```
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>外部样式</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <div>上帝为你关上一扇门, 然后就去睡觉了</div>
</body>
```
创建 ``style.css``

```
div {
    color: red;
}
```
**注意**：不要忘记使用 ``link``标签调用 CSS，否则不生效。

**优点**:样式和结构彻底分离了。

**缺点**：受到浏览器缓冲影响，修改之后**不一定**生效。

&emsp;&emsp;关于缓冲：

&emsp;&emsp;这是计算机中一种常见的提升性能的技术手段。

&emsp;&emsp;网页依赖的资源（图片/CSS/JS等）通常是从服务器上获取的。如果频繁的获取该网站，那么这些资源就没有必要反复从服务器获取，就可以使用缓存先存起来（就是存在本地磁盘上了），从而提高访问效率。

&emsp;&emsp;可以通过 ctrl + F5 强制刷新页面，强制浏览器重新获取 CSS 文件。

## 四、代码风格
### 1、样式风格

1. 紧凑风格

```
    p { color: red; font-size: 30px;}
```
2. 展开风格（推荐）

```
    p {
        color: red;
        font-size: 30px;
    }
```
### 2、样式大小写

&emsp;&emsp;虽然 CSS 样式不区分大小写，但是开发时统一使用小写字母。

### 3、空格规范

* 冒号后面带空格
* 选择器和“{”之间也有一个空格

## 五、选择器
### **1、选择器的功能**

&emsp;&emsp;选中一个或一组元素，然后针对这些元素应用样式。

### **2、选择器的种类***
#### **1、基础选择器**

1. **标签选择器**
   
&emsp;&emsp;根据标签名，将同一类型的标签都选中。但是不能差异化选择。

```
    <style>
        p {
            color: red;
        }
        div {
            color: green;
        }
    </style>

    <p>咬人猫</p>
    <p>咬人猫</p>
    <p>咬人猫</p>
    <div>阿叶君</div>
    <div>阿叶君</div>
    <div>阿叶君</div>
```
2. **类选择器**

&emsp;&emsp;定义一个类名“.”，以“.”开头的，然后在具体的元素中，通过 class 属性引入对应的类。

&emsp;&emsp;**特点**：差异化表示不同的标签。可以让多个标签的使用同一标签。

```
    <style>
        .blue {
        color: blue;
    }
    </style>

    <div class="blue">咬人猫</div>
    <div>咬人猫</div>
```
&emsp;&emsp;**语法细节**：
* 类名用“.”开头的
* 下方的标签使用 class 属性来**调用**。
* 一个类可以被多个标签使用，一个标签也能使用类（多个类要使用空格分割，这样的代码有很好的的复用性）。
* 如果是长的类名，可以用 “-” 分割。
* 不要使用纯数字，或者中文以及标签名来命名类名。

&emsp;&emsp;**代码示例：**

&emsp;&emsp;注意：一个标签可以同时使用多个类名，这样做可以同时把相同的属性提取出来，达到简化代码的效果。
&emsp;&emsp;
```
    <style>
    .box {
        width: 200px;
        height: 150px;
    }
    .red {
        background-color: red;
    }
    .green {
        background-color: green;
    }
    </style>

    <div class="box red"></div>
    <div class="box green"></div>
    <div class="box red"></div>
```
3. **id 选择器**

&emsp;&emsp;定义一个“#”开头的，“#”后面的内容要和页面中某个元素的 id 相匹配。（一个页面上的 id 是唯一的）

&emsp;&emsp;**特点**：
* CSS 中使用的“#”开头表示 id 选择器。
* id 选择器的值和 html 中的某个元素的 id 值相同。
* html 的元素 id 不必带 “#”。
* id 是唯一的，不能被多个标签使用（是和类选择器最大的区别）

```
    <style>
    #ha {
        color: red;
    }
    </style>

    <div id="ha">蛤蛤蛤</div>
```

4. **通配符选择器**

&emsp;&emsp;"*"选中页面中的所有元素，不需要具体的元素来引入。**一般用于清楚浏览器的默认样式。**

```
    * {
        color: red;
    }
```
5. **基础选择器小结**

![基础选择器](https://raw.githubusercontent.com/yimu-0412/image/master/image/%E5%9F%BA%E7%A1%80%E9%80%89%E6%8B%A9%E5%99%A8.png)

#### **2、复合选择器**
1. **后代选择器**

&emsp;&emsp;又叫包含选择器，选择某个父元素中的某个子元素。

```
元素1 元素2 {样式声明}
```
* 元素1 和 元素2 要使用空格分割
* 元素1 是父级，元素2 是子集，只选元素2，不影响元素1.

(1. **代码示例1**：先指定一个父元素，然后再指定一个子元素。

```
    <ul>
        <li>aaa</li>
        <li>bbb</li>
        <li>ccc</li>
    </ul>
    <ol>
        <li>ddd</li>
        <li>eee</li>
        <li>fff</li>
    </ol>
    ol li {
        color: red;
    }
```
(2. **代码示例2**：后代不一定非得是子元素，也可以是孙子元素。

```
    <ul>
        <li>aaa</li>
        <li>bbb</li>
        <li><a href="#">ccc</a></li>
    </ul>
    ul li a {
        color: yellow;
    }
    或者
    ul a {
        color: yellow;
    }
```
(3. **代码示例3**：后代选择器不一定非得是标签选择器的组合，也可以是任意的基础新选择器的组合。

```
    <ol class="one">
        <li>ddd</li>
        <li>eee</li>
        <li><a href="#">fff</a></li>
        <li><a href="#">fff</a></li>
        <li><a href="#">fff</a></li>
    </ol>
    .one li a {
        color: green;
    }
```

2. **子选择器**

&emsp;&emsp;**只能选择子元素**。

```
元素1 > 元素2 { 样式声明 }
```
* 使用“>”分割
* 只能选**亲儿子**，不能选孙子元素

```
    <div class="two">
        <a href="#">链接1</a>
        <p>
            <a href="#">链接2</a>
        </p>
    </div>
```
&emsp;&emsp;后代选择器的写法，会把链接1 和链接2 都选中。

```
    . two a {
        color : red;
    }
```
&emsp;&emsp;子选择器的写法，只选链接1。

```
    .two > a {
        color : green;
    }
```
3. **并集选择器**

&emsp;&emsp;一次选中多个标签。

```
    元素1，元素2 { 样式声明 } 
```
* 通过**逗号**分割多个元素。
* 表示同时选中元素1 和元素2 。
* 任何基础选择器都可以使用并集选择器。
* 并集选择器建议竖着写，每个选择器占一行，（最后一个选择器不能加逗号）

&emsp;&emsp;**代码示例**：

```
    <div>苹果</div>
    <h3>香蕉</h3>
    <ul>
        <li>鸭梨</li>
        <li>橙子</li>
    </ul>
```
&emsp;&emsp;1. 把苹果和香蕉颜色改成红色
 ```
    div,h3 {
        color : red;
    }
 ```
&emsp;&emsp;2. 把鸭梨和橙子也都一起改成红色
```
    div,
    h3,
    ul>li {
        color: red;
    }
```
4. **伪类选择器**

   1. 连接伪类选择器
   
        &emsp;&emsp;和 a 标签搭配使用，根据 a 标签的状态来选择。
   ```
        a:link 选择未被访问过的链接
        a:visited 选择已经被访问过的链接
        a:hover 选择鼠标指针悬停上的链接
        a:active 选择活动链接(鼠标按下了但是未弹起)
   ```
    &emsp;&emsp;**示例**：
    ```
        <a href="#">小猫</a>

        a:link {
            color: black;
            /* 去掉 a 标签的下划线 */
            text-decoration: none;
        }
        a:visited {
            color: green;
        }
        a:hover {
            color: red;
        }
        a:active {
            color: blue;
        }
    ```
   &emsp;&emsp;如何让一个已经被访问过的链接恢复成未访问的状态?清空浏览器历史记录即可. **ctrl + shift + delete**

   &emsp;&emsp;**注意事项：**

   * 按照 LVHA 的顺序书写, 例如把 active 拿到前面去, 就会导致 active 失效. 记忆规则 "**绿化**"
   * 浏览器的 a 标签都有默认样式, 一般实际开发都需要单独制定样式.
   * 实际开发主要给链接做一个样式, 然后给 hover 做一个样式即可. link, visited, active 用的不多.

    2. force 伪类选择器

        &emsp;&emsp;表示选取获取焦点的 input 表单元素。

        ```
        <div class="three">
            <input type="text">
            <input type="text">
            <input type="text">
            <input type="text">
        </div>
        .three>input:focus {
            color: red;
        }
        ```
        &emsp;&emsp;此时被选中的表单的字体就会变成红色.

5. **复合选择器小结**

![复合选择器](https://raw.githubusercontent.com/yimu-0412/image/master/image/%E5%A4%8D%E5%90%88%E9%80%89%E6%8B%A9%E5%99%A8.png)
## 六、常用元素的属性
### 1、字体属性
1. 设置字体

&emsp;&emsp;`` font-fanily`` 设置字体的类型。（宋体、隶书、微软雅黑···）

1. 大小
2. 粗细
3. 文字样式
### 2、文本属性
1. 文本颜色
2. 文本对齐
3. 文本装饰
4. 文本缩进
5. 行高
### 3、背景属性
1. 背景颜色
2. 背景图片
3. 背景平铺
4. 背景位置
5. 背景尺寸
### 4、圆角矩形
1. 基本用法
2. 生成圆形
3. 生成圆角矩形
4. 展开写法
