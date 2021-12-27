# 一、CSS 是什么

&emsp;&emsp;层叠样式表（Cascading Style Sheets);

&emsp;&emsp;CSS 能够对网页中元素位置的**排版进行像素级精确控制, 实现美化页面的效果， 能够做到页面的样式和结构分离**。

# 二、基本语法规范

&emsp;&emsp;选择器 + {一条/N条声
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
# 三、引入方式
## 1、内部样式表

&emsp;&emsp;写在 ``style``标签中，嵌入到 ``html``内部。

&emsp;&emsp;理论上``style``放到``html``的任意位置都可以，但是一般放到``head``标签中。

&emsp;&emsp;**优点**：能够让样式和页面结构分离。
&emsp;&emsp;**缺点**：分离的不够彻底，尤其是 CSS 内容较多的时候。

## 2、行内样式表

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

## 3、外部样式

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

# 四、代码风格
## 1、样式风格

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
## 2、样式大小写

&emsp;&emsp;虽然 CSS 样式不区分大小写，但是开发时统一使用小写字母。

## 3、空格规范

* 冒号后面带空格
* 选择器和“{”之间也有一个空格

# 五、选择器
## **1、选择器的功能**

&emsp;&emsp;选中一个或一组元素，然后针对这些元素应用样式。

## **2、选择器的种类***
### **1、基础选择器**

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

### **2、复合选择器**
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
# 六、常用元素的属性
## 1、字体属性
**1. 设置字体**

&emsp;&emsp;`` font-fanily`` 设置字体的类型。（宋体、隶书、微软雅黑···）
```
    body {
        font-family: '微软雅黑';
        font-family: 'Microsoft YaHei';
    }
```
* 字体名称可以用中文，但是不建议
* 多个字体之间使用逗号分割（从左到右查找字体，如果找不到，会使用默认字体）
* 如果字体名有空格，使用引号分隔。
* 建议使用常见字体，负责兼容性不好。
* font 相关的 CSS 的属性是可以被“继承”的，子元素会自动的继承父元素的相关属性。
* 如果在 body 中设置的字体，由于 body 就是该界面的次顶层元素（仅次于 html），页面中的其他元素也会继承这个字体。
* 如果需要某个元素使用不同的字体，就可以针对这个元素来单独设置字体，此时设置的新字体样式会覆盖继承子父元素的字体。
* 设置字体的时候要注意!需要保证用户的计算机上一定有这种字体（如果没有，可能会显示出错），如果需要设置特殊字体，就需要让用户的浏览器，从程序员提供的服务器上下载到相关的字体文件。

```
    <style>
        .font-family .one {
            font-family: 'Microsoft YaHei';
        }
        .font-family .two {
            font-family: '宋体';
        }
    </style>
    <div class="font-family">
        <div class="one">
            这是微软雅黑
        </div>
        <div class="two">
            这是宋体
        </div>
    </div>
```
![文字类型](https://raw.githubusercontent.com/yimu-0412/image/master/image/%E6%96%87%E5%AD%97%E7%B1%BB%E5%9E%8B.png)

**2. 大小**

&emsp;&emsp;``font-size``设置字体大小，单位是 px。

```
    p {
        font-size: 20px;
    }
```
* 不同的浏览器默认字号不一样，最好给一个明确值。（chrome 默认是 16px）
* 可以给 body 便签使用 font-size。
* 注意单位 px 不要忘记。
* 标题便签需要单独指定大小。
* **注意**：实际上它设置的是字体中**字符框**的高度；实际的字符字形可能比这些框高或矮
* 实际使用其他工具测量的时候，不一定等于设置的值，**主要取决于缩放！**

```
    <style>
        .font-size .one {
            font-size:40px;
        }
        .font-size .two {
            font-size:20px;
        }
    </style>
    <div class="font-size">
        <div class="one">
            大大大
        <div>
        <div class="two">
            小小小
        <div>
    </div>
```
![文字大小](https://raw.githubusercontent.com/yimu-0412/image/master/image/%E6%96%87%E5%AD%97%E5%A4%A7%E5%B0%8F.png)

**3. 粗细**

&emsp;&emsp;``font-weight``设置字体大小。

```
    p {
        font-weight: bold;
        font-weight: 700;
    }
```
* 可以使用数字表示粗细。
* 700 == bold，400 是不变粗，== normal。
* 取值范围是 100 ~ 900。

```
<style>
    .font-weight .one {
        font-weight: 900;
    }
    .font-weight .two {
        font-weight: 100;
    }
</style>
<div class="font-weight">
    <div class="one">
        粗粗粗
    </div>
    <div class="two">
        细细细
    </div>
</div>
```
![文字粗细](https://raw.githubusercontent.com/yimu-0412/image/master/image/%E6%96%87%E5%AD%97%E7%B2%97%E7%BB%86.png)

**4. 文字样式**

&emsp;&emsp;``font-style``设置字体样式。

```
    /* 设置倾斜 */
    font-style: italic;
    /* 取消倾斜 */
    font-style: normal;
```
* 很少把某个文字倾斜，但是经常要把 em / i 改成不倾斜。

```
    <style>
        .font-style em {
            font-style: normal;
        }
        .font-style div {
            font-style: italic;
        }
    </style>
    <div class="font-style">
        <em>
            放假啦
        </em>
        <div class="one">
            听说要加班
        </div>
    </div>
```
![文字样式举例](https://raw.githubusercontent.com/yimu-0412/image/master/image/%E6%96%87%E5%AD%97%E6%A0%B7%E5%BC%8F%E4%B8%BE%E4%BE%8B.png)

## 2、文本属性
### 1. 文本颜色

   1. **认识 RGB**
   
        &emsp;&emsp;显示器是由很多很多的“像素”构成，每个像素视为一个点，这个点就能反应出一个具体的颜色。使用 R（red）、G（green）、B（blue）的方式表示颜色（色光三原色）。三种颜色按照不同的比例搭配，就能混合出各种五彩斑斓的效果。

        &emsp;&emsp;计算机中针对R，G，B三个分量，分别使用一个字节表示（8个比特位，表示的范围是0~255，十六进制表示为 00~FF）。

        &emsp;&emsp;数值越大，表示该分量的颜色越浓。**255，255，255 表示白色**;**0，0，0 表示黑色**。

  1. **设置文本颜色**

    ```
        color: red;
        color: #ff0000;
        color: rgb(255, 0, 0);
    ```

&emsp;&emsp;**color 属性值的写法**：

       1. 预定义的颜色值（直接是单词）
       2. [**最常用**]十六进制形式。两个十六进制是一个字节，RGB 三个字节，意味着用六个十六进制数字来表示。用“#”开头。同时，使用十六进制的表示的时候，如果颜色是形如 #000000 可以缩写为 #000；形如 #ffffff 缩写成 #fff；形如 #ff00ff 缩写成 #f0f。
       3. RGB 方式，后面还可以加 透明度选项 alpha。使用 1个字节8个比特位来表示。在使用 rgba 写法的时候，a 往往是写作一个 0~1 之间的小数。 

```
    <style>
    .color {
        color: red;
        /* color: rgb(255, 0, 0); */
        /* color: #ff0000; */
    }
    </style>
    <div class="color">
        这是一段话
    </div>
```

![文本颜色](https://raw.githubusercontent.com/yimu-0412/image/master/image/%E6%96%87%E6%9C%AC%E9%A2%9C%E8%89%B2.png)

### 2. 文本对齐

&emsp;&emsp;控制文字水平方向的对齐。（不仅可以控制文本对齐，还可以控制图片等元素居中或者靠右）

```
    text-align: [值];
```
* center : 居中对齐
* left：左对齐
* right：右对齐

```
    <style>
        .text-align .one {
            text-align: left;
        }
        .text-align .two {
            text-align: right;
        }
        .text-align .three {
            text-align: center;
        }
    </style>
        <div class="text-align">
        <div class="one">左对齐</div>
        <div class="two">右对齐</div>
        <div class="three">居中对齐</div>
    </div>
```
![文本对齐](https://raw.githubusercontent.com/yimu-0412/image/master/image/%E6%96%87%E6%9C%AC%E5%AF%B9%E9%BD%90.png)

### 3. 文本装饰

```
    text-decoration: [值];
```
&emsp;**常用取值：**

* underline 下划线[常用]。
* none 什么都没有，可以给 a 标签去掉下划线。
* overline 上划线。[不常用]
* line-through 删除线[不常用]

```
<style>
    .text-decorate .one {
        text-decoration: none;
    }
    .text-decorate .two {
        text-decoration: underline;
    }
    .text-decorate .three {
        text-decoration: overline;
    }
    .text-decorate .four {
        text-decoration: line-through;
    }
</style>
    <div class="text-decorate">
    <div class="one">啥都没有</div>
    <div class="two">下划线</div>
    <div class="three">上划线</div>
    <div class="four">删除线</div>
</div>
```
![文本装饰](https://raw.githubusercontent.com/yimu-0412/image/master/image/%E6%96%87%E6%9C%AC%E8%A3%85%E9%A5%B0.png)

### 4. 文本缩进

&emsp;&emsp;控制段落的**首行**缩进，对于其他行不影响。根据**字体大小**来设置缩进的像素数量。

```
text-indent：[值]；
```
* 单位可以使用 px 或者 em。
* 使用 em 作为单位更好。1 个em就是当前元素的文字大小。
* 缩进可以是负的，表示往左缩进。

```
    <style>
        .text-indent .one {
            text-indent: 2em;
        }
        .text-indent .two {
            text-indent: -2em;
        }
    </style>
        <div class="text-indent">
        <div class="one">正常缩进</div>
        <div class="two">反向缩进</div>
    </div>
```
![文本缩进](https://raw.githubusercontent.com/yimu-0412/image/master/image/%E6%96%87%E6%9C%AC%E7%BC%A9%E8%BF%9B.png)

### 5. 行高

&emsp;&emsp;行高指的是上下文本之间的**基线距离**。包含文字本身的 **高度 和 行间距**。

&emsp;&emsp;HTML 中展示文字会涉及到几个基准线：
     
* 顶线
* 中线
* 基线
* 底线
  
  ![行高基准](https://raw.githubusercontent.com/yimu-0412/image/master/image/%E8%A1%8C%E9%AB%98%E5%9F%BA%E5%87%86.png)

  * 内容区：底线和顶线包裹的区域，即上图深灰色背景区域。
  * 其实基线之间距离 = 顶线之间的距离 = 底线之间的距离 = 中线之间的距离。

```
    line-height: [值];
```
&emsp;**注意1**：行高 = 上边距 + 下边距 + 字体大小

&emsp;&emsp;上下边距是相等的, 此处字体大小是 16px, 行高 40px, 上下边距就分别是 12px

```
    <style>
        .line-height .one {
            line-height: 40px;
            font-size: 16px;
        }
    </style>
    <div class="line-height">
        <div>
            上一行
        </div>
        <div class="one">
            中间行
        </div>
        <div>
            下一行
        </div>
    </div>
```

![行高举例](https://raw.githubusercontent.com/yimu-0412/image/master/image/%E8%A1%8C%E9%AB%98%E4%B8%BE%E4%BE%8B.png)

&emsp;**注意2**：行高也可以取 normal 等值

&emsp;&emsp;这个取决于浏览器的实现. chrome 上 normal 为 21 px。

&emsp;**注意3**：行等高与元素高度，就可以实现文字居中对齐。

```
    <style>
        .line-height .two {
            height: 100px;
            line-height: 100px;
        }
    </style>
    <div class="line-height">
        <div class="two">
            本垂直居中
        </div>
    </div>
```

![行高举例2](https://raw.githubusercontent.com/yimu-0412/image/master/image/%E8%A1%8C%E9%AB%98%E4%B8%BE%E4%BE%8B2.png)
## 3、背景属性
### 1. 背景颜色

```
    background-color: [指定颜色]
```
&emsp;&emsp;background-color 属性的值和 color 的值写法格式完全相同。

1. 可以支持单词。
2. 可以支持 #十六进制。
3. 可以支持 rgb。
4. 可以支持 rgba。
5. 这个属性还有一个特殊的值，transparent 表示背景透明（透过这个元素，看见该元素的父级元素）。
   
```
    <style>
        body{
            background-color: gray;
        }
        .bgc .one{
            background-color: red;
        }
        .bgc .two {
            background-color: #0f0;
        }
        .bgc .three {
            background-color: transparent;
        }
    </style>
    <div class="bgc">
        <div class="one">红色背景</div>
        <div class="two">绿色背景</div>
        <div class="three">透明背景</div>
    </div>
```
![背景颜色](https://raw.githubusercontent.com/yimu-0412/image/master/image/%E8%83%8C%E6%99%AF%E9%A2%9C%E8%89%B2.png)

### 2. 背景图片

&emsp;&emsp;指定一个图片作为背景图。比 image 更方便控制位置。（图片在盒子中的位置）

```
    background-image: url(...); 图片地址
```
&emsp;&emsp;**注意**：

1. url 不要遗漏
2. url 可以是绝对路径，也可以是相对路径
3. url 上可以加引号，也可以不加
   
```
    <style>
        .bgi .one {
            background-image: url(rose.jpg);
            height: 300px;
        }
    </style>
    <div class="bgi">
    <div class="one">背景图片</div>
</div>
```
![背景图片](https://raw.githubusercontent.com/yimu-0412/image/master/image/%E8%83%8C%E6%99%AF%E5%9B%BE%E7%89%87.png)

### 3. 背景平铺
```
    background-repeat: [平铺方式]
```

&emsp;**重要取值：**

       1. repeat ：平铺
       2. no-repeat：不平铺
       3. repeat-x: 水平平铺
       4. repeat-y：垂直平铺
        默认是 reapeat。

&emsp;&emsp;背景颜色和背景图片可以同时存在. **背景图片在背景颜色的上方。**

```
    <style>
        .bgr .one {
            background-image: url(rose.jpg);
            height: 300px;
            background-repeat: no-repeat;
        }
        .bgr .two {
            background-image: url(rose.jpg);
            height: 300px;
            background-repeat: repeat-x;
        }
        .bgr .three {
            background-image: url(rose.jpg);
            height: 300px;
            background-repeat: repeat-y;
        }
    </style>
    <div class="bgr">
        <div class="one">不平铺</div>
        <div class="two">水平平铺</div>
        <div class="three">垂直平铺</div>
    </div>
```
![背景平铺](https://raw.githubusercontent.com/yimu-0412/image/master/image/%E8%83%8C%E6%99%AF%E5%B9%B3%E9%93%BA.png)
### 4. 背景位置

&emsp;&emsp;修改图片的位置。
```
    background-position: x y;
```
&emsp;&emsp;参数有三种风格：

1. 方位名词：（top，left，right，bottom）
2. 精确位置： 坐标或者百分比（以左上角为原点）
3. 混合单位： 同时包含方位名词和精确单位
   
```
    .bgp .five {
        background-image: url(dog.jpg);
        width: 500px;
        height: 300px;
        background-repeat: no-repeat;
        background-color: purple;
        background-position: center;
        background-size: 350px 250px;
    }
    .bgp .sex {
        background-image: url(dog.jpg);
        width: 500px;
        height: 300px;
        background-repeat: no-repeat;
        background-color: rgb(211, 197, 211);
        background-position: left;
        background-size: 350px 250px;
    }
    </style>
    <div class="bgp">
        <div class="five">背景(center)</div> 
        div class="sex">背景(left)</div>
    </div>
```

![背景位置](https://raw.githubusercontent.com/yimu-0412/image/master/image/%E8%83%8C%E6%99%AF%E4%BD%8D%E7%BD%AE.png)

&emsp;&emsp;**注意：**
    
1. 如果参数的两个值都是方位名词, 则前后顺序无关. (top left 和 left top 等效)。
2. 如果只指定了一个方位名词, 则第二个默认居中。(left 则意味着水平居中, top 意味着垂直居中. )
3. 如果参数是精确值, 则的的第一个肯定是 x , 第二个肯定是 y。 (100 200 意味着 x 为 100, y 为 200)
4. 如果参数是精确值, 且只给了一个数值, 则该数值一定是 x 坐标, 另一个默认垂直居中。
5. 如果参数是混合单位, 则第一个值一定为 x, 第二个值为 y 坐标. (100 center 表示横坐标为 100, 垂直居中)

&emsp;&emsp;**关于坐标系**：

        计算机中的平面坐标系, 一般是左手坐标系(和高中数学上常用的右手系不一样. y轴是往下指的).

![坐标系](https://raw.githubusercontent.com/yimu-0412/image/master/image/%E5%9D%90%E6%A0%87%E7%B3%BB.png)

### 5. 背景尺寸

```
    background-size: length|percentage|cover|contain;
```
* 可以填具体的数值：如 40px 60px 表示宽度为 40px，高度为 60px。
* 也可以填百分比，按照父元素的尺寸设计
* cover：把背景图像扩展至足够大，以使背景图像完全覆盖背景区域。背景图像的某些部分也许无法显示在背景定位区域中。
* contain: 把图像图像扩展至最大尺寸，以使其宽度和高度完全适应内容区域.

```
    <style>
        .bgs .one {
            width: 500px;
            height: 300px;
            background-color: green;
            background-image: url(dog.jpg);
            background-repeat: no-repeat;
            background-position: center;
            background-size: contain;
        }
        .bgs .two {
            width: 500px;
            height: 300px;
            background-color: gray;
            background-image: url(dog.jpg);
            background-repeat: no-repeat;
            background-position: center;
            background-size: cover;
        }
        .bgs .three {
            width: 500px;
            height: 300px;
            background-color: white;
            background-image: url(dog.jpg);
            background-repeat: no-repeat;
            background-position: center;
            background-size: 350px 250px;
        }
        .bgs .four {
            width: 500px;
            height: 300px;
            background-color: #f1f1f7;
            background-image: url(dog.jpg);
            background-repeat: no-repeat;
            background-position: center;
            background-size: 90% 90%;
        }
        
    </style>
    <div class="bgs">
        <div class="one">背景尺寸(contain)</div>
        <div class="two">背景尺寸(cover)</div>
        <div class="three">背景尺寸(length)</div>
        <div class="four">背景尺寸(percentage)</div>
    </div>
```
![背景尺寸](https://raw.githubusercontent.com/yimu-0412/image/master/image/%E8%83%8C%E6%99%AF%E5%B0%BA%E5%AF%B8.png)

### 6.背景总结
1. 背景颜色 background-color,设置的值和color一样。
2. 背景图片 background-image,url()中填写的是图片的具体路径。
3. 背景平铺 background-repeat，平铺、不平铺、垂直方向平铺、水平方向平铺。
4. 背景位置 background-position，方位名词/坐标，灵活设置。
5. 背景大小 background-size，数值/百分比/cover/contain。
   
## 4、圆角矩形
&emsp;&emsp;通过 border-radius 使边框带圆角效果. 
### 1. 基本用法
```
    border-radius: length;
```
* length: 内切圆的半径，数值越大，圆弧越强烈。

```
    <body>
        <div>蛤蛤</div>
    <style>
        div {
        width: 200px;
        height: 100px;
        text-align: center;
        border: 2px solid green;
        border-radius: 15px;

    }
```

![圆角边矩](https://raw.githubusercontent.com/yimu-0412/image/master/image/%E5%9C%86%E8%A7%92%E8%BE%B9%E7%9F%A9.png)

### 2. 生成圆形
&emsp;&emsp;让 border-radius 的值为正方形宽度的一半即可。
```
    div {
        width: 200px;
        height: 200px;
        border: 2px solid green;
        border-radius: 100px;
        /* 或者用 50% 表示宽度的一半 */
        border-radius: 50%;
    }
    <div></div>
```
![生成圆形](https://raw.githubusercontent.com/yimu-0412/image/master/image/%E7%94%9F%E6%88%90%E5%9C%86%E5%BD%A2.png)

### 3. 生成圆角矩形

&emsp;&emsp;通过把 border-radius 的值为矩形高度的一半，可以实现圆角矩形。

![圆角矩形](https://raw.githubusercontent.com/yimu-0412/image/master/image/%E5%9C%86%E8%A7%92%E7%9F%A9%E5%BD%A2.png)

### 4. 展开写法

&emsp;&emsp;border-radius 是一个复合写法. 实际上可以针对四个角分别设置. 
```
    border-radius:2em;
```
&emsp;&emsp;等价于
```
    order-top-left-radius:2em;
    border-top-right-radius:2em;
    border-bottom-right-radius:2em;
    border-bottom-left-radius:2em;
```



&emsp;&emsp;**CSS 中一般按照4个角/4个方向来设置属性 ，一般按照顺时针方向进行设置。**

```
    border-radius:10px 20px 30px 40px;
```
&emsp;&emsp;等价于

```
    border-top-left-radius:10px;
    border-top-right-radius:20px;
    border-bottom-left-radius:30px;
    border-bottom-right-radius:40px;
```

![展开模式](https://raw.githubusercontent.com/yimu-0412/image/master/image/%E5%B1%95%E5%BC%80%E6%A8%A1%E5%BC%8F.png)

# 七、Chrome 调试工具 -- 查看 CSS 属性
## 1、打开浏览器

&emsp;有两种方式可以打开 Chrome 调试工具:

  * 直接按 F12 键
  * 鼠标右键页面 => 检查元素

## 2、标签页含义

![标签页（english）](https://raw.githubusercontent.com/yimu-0412/image/master/image/%E6%A0%87%E7%AD%BE%E9%A1%B5%EF%BC%88english%EF%BC%89.png)

![标签页（中文）](https://raw.githubusercontent.com/yimu-0412/image/master/image/%E6%A0%87%E7%AD%BE%E9%A1%B5%EF%BC%88%E4%B8%AD%E6%96%87%EF%BC%89.png)

* 元素（elements）：查看页面结构和样式。（前端）
* 控制台（console）:查看 javaScript 的打印日志。（前端）
* 源代码（sources）：查看源代码和断点调试（html/css/js源代码）。
* 网络（network）：查看浏览器和服务器之间的网络交互。（前端和后端）
* 应用程序（application）：查看浏览器提供的以一些扩展功能（本地储存等）。

&emsp;&emsp;控制台里面可以执行 js 代码，也可以查看 js 的代码。

## 3、elements 标签页使用
* trl + 滚轮进行缩放, ctrl + 0 恢复原始大小。
* 使用 左上角 箭头选中元素。
* 右侧可以查看当前元素的属性, 包括引入的类。
* 右侧可以修改选中元素的 css 属性. 例如颜色, 可以点击颜色图标, 弹出颜色选择器, 修改颜色. 例如字体大小, 可以使用方向键来微调数值。
* 此处的修改不会影响代码, 刷新就还原了。
* 如果 CSS 样式写错了, 也会在这里有提示. (黄色感叹号)

![错误提示](https://raw.githubusercontent.com/yimu-0412/image/master/image/%E9%94%99%E8%AF%AF%E6%8F%90%E7%A4%BA.png)

## 4、元素的显示模式
&emsp;在 CSS 中, HTML 的标签的显示模式有很多。

&emsp;此处只重点介绍两个:
* 块级元素
* 行内元素
### 1.块级元素（block‘）
1. 常见的元素

```
    h1~h6 p div ul li ···
```
&emsp;&emsp;**特点：**

* 独占一行
* 高度，宽度，内外边距，行高都可以控制。
* 宽度默认是父级元素的 100%（和父级元素一样宽）。
* 是一个容器（盒子），里面可以放行内元素和块级元素。
  
```
    <style>
        .demo1 .parent {
            width: 500px;
            height: 500px;
            background-color: green;
        }
        .demo1 .child {
            /* 不写 width, 默认和父元素一样宽 */
            /* 不写 height, 默认为 0 (看不到了) */
            height: 200px;
            background-color: red;
        }    

    </style>

    <div class="demo1">
        <div class="parent">
            <div class="child">
                child1
            </div>
            <div class="child">
                child2
            </div>
        </div>
    </div>
```
![块级元素](https://raw.githubusercontent.com/yimu-0412/image/master/image/%E5%9D%97%E7%BA%A7%E5%85%83%E7%B4%A0.png)

&emsp;&emsp;**注意：**
* 文字类的元素内不能使用块级元素。
* p 标签只要用于存放文字，内部不能使用块级元素，尤其是 div。

### 2.行内元素/内联元素

1. 常见的元素

```
    a  strong  b  em  i  del  s  ins  u  span ···
```

&emsp;&emsp;**特点：**

* 不独占一行，一行可以显示多个。
* 设置高度，宽度，行高无效。
* 左右外边距有效（上下无效），内边距有效。
* 默认宽度就是本身的内容。
* 行内元素只能容纳文本和其他行内元素，不能存放块级元素。

```
    <style>
        .demo2 span {
            width: 800px;
            height: 500px;
            background-color: red;
        }
    </style>
    <div class="demo2">
        <span>child1</span>
        <span>child2</span>
        <span>child3</span>
    </div>
```

![行内元素](https://raw.githubusercontent.com/yimu-0412/image/master/image/%E8%A1%8C%E5%86%85%E5%85%83%E7%B4%A0.png)

&emsp;&emsp;**注意：**
* a 标签中不能再放 a 标签（虽然 chrome 不报错，但是最好不要这么做）。
* a 标签里面可以放块级元素，但是建议先把 a 转换成块级元素。
* img 标签既不是行内元素，也不是块级元素，叫做“行内块元素”。
  
### 3.行内元素和块级元素的区别

1. 是否独占一行
2. 块级元素的高度，宽度，内外边距，行高···都是可以控制的；行内元素的高度，宽度，行高都是无效的，左右外边距有效，上下无效，内边距都有效。
3. 块级元素的默认宽度和父级元素一样；行内元素的默认宽度和本身内容的相关（如果没有内容，宽度就是0）。
4. 块级元素内部可以容纳其他的块级元素和行内元素，行内元素内部只能包含行内元素，不能包含块级元素。
   
### 4.改变显示模式

&emsp;&emsp;使用 display 属性可以修改元素的显示模式。

      可以把 div 等变成行内元素，也可以 a,span 等变成块级元素。
        1. display：block 改成块级元素（常用）。
        2. display：inline 改成行内元素（很少用）。
        3. display：inline-block 改成行内块元素。
   
# 八、盒模型
&emsp;&emsp;每一个 HTML 元素就相当于是一个矩形的“盒子”。这几个盒子由这几个部分构成：

* 边框（border）
* 内容（content）
* 内边距（padding）
* 外边距（margin）

![盒模型](https://raw.githubusercontent.com/yimu-0412/image/master/image/%E7%9B%92%E6%A8%A1%E5%9E%8B.png)

## 1、边框
### 1.基础属性

1. border-width：边框的粗细
2. border-style：边框的样式。默认没边框，solid 实线边框，dashed 虚线边框，dotted 点线边框。
3. border-color：边框的颜色

```
    <style>
        .test {
            width: 200px;
            height: 200px;
            border-width: 5px;
            border-style: solid;
            border-color: blueviolet;
            background-color: red;
        }
    </style>
    <div class="test">测试</div>
```
![边框](https://raw.githubusercontent.com/yimu-0412/image/master/image/%E8%BE%B9%E6%A1%86.png)

&emsp;&emsp;支持简写，没有顺序要求：
```
    border: 1px solid red;  
```
&emsp;&emsp;可以改四个方向的任意边框：
```
    border-top/bottom/left/right
```
&emsp;&emsp;以下的代码只给上边框设置为红色，其余为蓝色。利用到的是**层叠性**。就近元素的生效。
```
    <style>
        .t {
            width: 200px;
            height: 100px;
            border-width: 5px;
            border-style: solid;
            border-color: blue;
            border-top:5px solid greenyellow;
            background-color: red;
        }
    </style>
    <div class="t">测试</div>
```
![单独设置边框](https://raw.githubusercontent.com/yimu-0412/image/master/image/%E5%8D%95%E7%8B%AC%E8%AE%BE%E7%BD%AE%E8%BE%B9%E6%A1%86.png)

### 2.边框会撑大盒子

&emsp;&emsp;由下图可以看到，width、height是200*100，而盒子的最终大小为 210*100。边框5个像素相当于扩大了盒子的大小。

![边框撑大盒子](https://raw.githubusercontent.com/yimu-0412/image/master/image/%E8%BE%B9%E6%A1%86%E6%92%91%E5%A4%A7%E7%9B%92%E5%AD%90.png)

&emsp;&emsp;通过 box-sizing 属性可以修改浏览器的行为，是边框不在扩大盒子。

```
    * 为通配符选择器
    * {
        box-sizing:border-box;
    }
```

![边框不撑大盒子](https://raw.githubusercontent.com/yimu-0412/image/master/image/%E8%BE%B9%E6%A1%86%E4%B8%8D%E6%92%91%E5%A4%A7%E7%9B%92%E5%AD%90.png)

## 2、内边距

&emsp;&emsp;padding 设置内容和边框之间的距离。

### 1.基础写法

&emsp;&emsp;默认内容是顶着边框来放置的。使用 padding 来控制这个距离。
&emsp;&emsp;可以给四个方向都加上边距。

```
    padding-top
    padding-bottom
    padding-left
    padding-right
```
&emsp;&emsp;未加 padding 之前

```
    <style>
        .p {
        height:100px;
        width:150px;
        background-color: greenyellow;
    }
    </style>
    <div class="p">测试 padding </div>
```
![padding 测试-1](https://raw.githubusercontent.com/yimu-0412/image/master/image/padding%20%E6%B5%8B%E8%AF%95-1.png)

&emsp;&emsp;加 padding 之后：
```
    <style>
        .p1 {
        height:100px;
        width:150px;
        padding-top:10px;
        padding-left:15px;
        background-color: greenyellow;
    }
    </style>
    <div class="p1">测试 padding </div>
```
![padding 测试-4](https://raw.githubusercontent.com/yimu-0412/image/master/image/padding%20%E6%B5%8B%E8%AF%95-4.png)

&emsp;&emsp;**注意：**
    * 整个盒子的大小从原来的 150px * 100px =》 165px * 110px，说明内边距也会影响到盒子的大小。
    * 使用``border-sizing：border-box``属性也可以使内边距不再撑大盒子。
     
### 2.复合写法

```
    padding: 5px; 表示四个方向都是 5px
    padding: 5px 10px; 表示上下内边距 5px, 左右内边距为 10px
    padding: 5px 10px 20px; 表示上边距 5px, 左右内边距为 10px, 下内边距为 20px
    padding: 5px 10px 20px 30px; 表示 上5px, 右10px, 下20px, 左30px (顺时针)
```
## 3、外边距
### 1.基础写法
&emsp;&emsp;**控制盒子与盒子之间的距离。**

&emsp;&emsp;可以给四个方向都加上边距。

```
    margin-top;
    margin-bottom;
    margin-left;
    margin-right;
```
```
    <style>
        div {
            background-color: greenyellow;
            width: 200px;
            height: 150px;
        }

        .first {
            margin-top: 10px;
            margin-bottom: 10px;
        }
    </style>
    <div class="first">哈哈哈哈</div>
```
![margin 测试](https://raw.githubusercontent.com/yimu-0412/image/master/image/margin%20%E6%B5%8B%E8%AF%95.png)

### 2.复合写法

&emsp;&emsp;规则和 padding 相同。

```
    margin: 10px; // 四个方向都设置
    margin: 10px 20px; // 上下为 10, 左右 20
    margin: 10px 20px 30px; // 上 10, 左右 20, 下 30
    margin: 10px 20px 30px 40px; // 上 10, 右 20, 下 30, 左 40
```

### 3.块级元素水平居中
&emsp;&emsp;前提：
* 指定宽度（如果不指定宽度，默认和父元素一致）
* 把水平 margin 设为 auto；

&emsp;&emsp;三种写法均可：
```
    margin-left: quto; margin-right: auto;
    margin: auto;
    margin: 0 auto;
```

&emsp;&emsp;示例：

```
    <style>
        div {
            background-color: greenyellow;
            width: 500px;
            height: 150px;
            margin: auto;
        }

        .first {
            width: 300px;
            height: 100px;
            margin-top: 10px;
            background-color: gray;
        }
    </style>
    <div>
        <div class="first">哈哈哈哈</div>
    </div>
```
![块级元素居中](https://raw.githubusercontent.com/yimu-0412/image/master/image/%E5%9D%97%E7%BA%A7%E5%85%83%E7%B4%A0%E5%B1%85%E4%B8%AD.png)

    注意：
        1. 这个水平居中和 text-align 不一样。
        2. margin：auto 是给块级元素使用，产生的水平居中是整个元素在父元素中水平居中。
        3. text-align：center 是让行内元素或者行内块元素居中的。
    另：对于垂直居中，不能使用“上下margin 为auto”的方式。

### 4.去除浏览器默认样式

&emsp;&emsp;浏览器会给元素加上一些默认的样式, 尤其是内外边距. 不同浏览器的默认样式存在差别。为了保证代码在不同的浏览器上都能按照统一的样式显示, 往往我们会去除浏览器默认样式。使用通**配符选择器**即可完成这件事情。

```
    margin: 0;
    padding: 0;
    box-sizing: border-box;
```
# 九、弹性布局
## 1、初体验

&emsp;&emsp;创建一个 div ，内部包含三分 span
```
    <div>
        <span>1</span>
        <span>2</span>
        <span>3</span>
    </div>

    <style>
        div {
            width: 100%;
            height: 150px;
            background-color: gray;
        }

        div>span {
            background-color: greenyellow;
            width: 100px;
        }
    </style>
```

&emsp;&emsp;此时看到的效果是：

![弹性布局-1](https://raw.githubusercontent.com/yimu-0412/image/master/image/%E5%BC%B9%E6%80%A7%E5%B8%83%E5%B1%80-1.png)

&emsp;&emsp;当我们给 div 加上 display:flex 之后, 效果为：

![弹性布局-2](https://raw.githubusercontent.com/yimu-0412/image/master/image/%E5%BC%B9%E6%80%A7%E5%B8%83%E5%B1%80-2.png)

&emsp;&emsp;此时看到, span 有了高度, 不再是 "行内元素"。再给 div 加上 justify-content: space-around; 此时效果为：

![弹性布局-3](https://raw.githubusercontent.com/yimu-0412/image/master/image/%E5%BC%B9%E6%80%A7%E5%B8%83%E5%B1%80-3.png)

&emsp;&emsp;此时可以看到这些 span 已经能够水平隔开。把 justify-content: space-around; 改为 justify-content: flex-end; 可以看到此时三个元素显示在
右侧。

![弹性布局-4](https://raw.githubusercontent.com/yimu-0412/image/master/image/%E5%BC%B9%E6%80%A7%E5%B8%83%E5%B1%80-4.png)

## 2、flex 布局基本概念
&emsp;&emsp;flex 是 flexible box 的缩写，意思为“弹性盒子”。

&emsp;&emsp;任何一个 html 元素，都可以指定为 ``display：flex``完成弹性布局。

&emsp;&emsp;flex 布局的本质是给父盒子添加``display：flex``属性，来控制盒子的位置和排列方式。

&emsp;&emsp;**基础概念**：
* 被设置为``display：flex``属性的元素，称为 **flex container**。
* 它的所有子元素立刻称为该元素的成员。称为 **flex item**。
* flex item 可以纵向平排列，也可以横向排列。称为 **flex direction（主轴）**。默认情况下，水平方向是主轴，垂直方向是侧轴。
* 使用 align-item：center实现垂直居中，推荐做法！

![弹性布局基本概念](https://raw.githubusercontent.com/yimu-0412/image/master/image/%E5%BC%B9%E6%80%A7%E5%B8%83%E5%B1%80%E5%9F%BA%E6%9C%AC%E6%A6%82%E5%BF%B5.png)

&emsp;&emsp;**注意**：

&emsp;&emsp;当父元素设置为``display：flex``之后，子元素的float，clear,vertical-algin 都会失效。

## 3、常用属性
### 1. justify-content

&emsp;&emsp;设置**主轴子元素**的排列方式。

         使用之前一定要确定号主轴是哪个方向。

&emsp;&emsp;属性取值：

![justify-content属性](https://raw.githubusercontent.com/yimu-0412/image/master/image/justify-content%E5%B1%9E%E6%80%A7.png)

```
    <div>
        <span>1</span>
        <span>2</span>
        <span>3</span>
        <span>4</span>
        <span>5</span>
    </div>

    <style>
        div {
            width: 100%;
            height: 150px;
            background-color: gray;
            display: flex;
        }

        div>span {
            background-color: greenyellow;
            width: 100px;
        }
    </style>
```
&emsp;&emsp;未指定 justify-content 时, 默认按照从左到右的方向布局。

![justify-content属性举例-1](https://raw.githubusercontent.com/yimu-0412/image/master/image/justify-content%E5%B1%9E%E6%80%A7%E4%B8%BE%E4%BE%8B-1.png)

&emsp;&emsp;设置 justify-content: flex-end , 此时元素都排列到右侧了.

![justify-content属性举例-2](https://raw.githubusercontent.com/yimu-0412/image/master/image/justify-content%E5%B1%9E%E6%80%A7%E4%B8%BE%E4%BE%8B-2.png)

&emsp;&emsp;设置 jutify-content: center , 此时元素居中排列。

![justify-content属性举例-3](https://raw.githubusercontent.com/yimu-0412/image/master/image/justify-content%E5%B1%9E%E6%80%A7%E4%B8%BE%E4%BE%8B-3.png)

&emsp;&emsp;设置 justify-content: space-around;平分了剩余空间。

![justify-content属性举例-4](https://raw.githubusercontent.com/yimu-0412/image/master/image/justify-content%E5%B1%9E%E6%80%A7%E4%B8%BE%E4%BE%8B-4.png)

&emsp;&emsp;设置 justify-content: space-between;先两边元素贴近边缘, 再平分剩余空间。

![justify-content属性举例-5](https://raw.githubusercontent.com/yimu-0412/image/master/image/justify-content%E5%B1%9E%E6%80%A7%E4%B8%BE%E4%BE%8B-5.png)

### 2. aligin-items

&emsp;&emsp;设置侧轴上的元素排列方式。

&emsp;&emsp;**属性取值**：

![align-items 属性](https://raw.githubusercontent.com/yimu-0412/image/master/image/align-items%20%E5%B1%9E%E6%80%A7.png)

**理解 stretch(拉伸):**

    这个是 align-content 的默认值. 意思是如果子元素没有被显式指定高度, 那么就会填充满父元素的高度.

![align-items属性举例-1](https://raw.githubusercontent.com/yimu-0412/image/master/image/align-items%E5%B1%9E%E6%80%A7%E4%B8%BE%E4%BE%8B-1.png)

&emsp;&emsp;可以使用 align-items 实现垂直居中。
```
    <div>
        <span>1</span>
        <span>2</span>
        <span>3</span>
        <span>4</span>
        <span>5</span>
    </div>

    <style>
        div {
            width: 100%;
            height: 150px;
            background-color: gray;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        div>span {
            background-color: greenyellow;
            width: 50px;
        }
    </style>
```
![align-items属性举例-2](https://raw.githubusercontent.com/yimu-0412/image/master/image/align-items%E5%B1%9E%E6%80%A7%E4%B8%BE%E4%BE%8B-2.png)