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
### 5. 背景尺寸

```
    background-size: length|percentage|cover|contain;
```
* 可以填具体的数值：如 40px 60px 表示宽度为 40px，高度为 60px。
* 也可以填百分比，按照父元素的尺寸设计
* cover：把背景图像扩展至足够大，以使背景图像完全覆盖背景区域。背景图像的某些部分也许无法显示在背景定位区域中。
* 


## 4、圆角矩形
### 1. 基本用法
### 2. 生成圆形
### 3. 生成圆角矩形
### 4. 展开写法
# 七、Chrome 调试工具 -- 查看 CSS 属性
## 1、打开浏览器
## 2、标签页含义
## 3、elements 标签页使用
## 4、元素的显示模式
### 1.块级元素
### 2.行内元素/内联元素
### 3.行内元素和块级元素的区别
### 4.改变显示模式
# 八、盒模型
## 1、边框
### 1.基础属性
### 2.边框会撑大盒子
## 2、内边距
### 1.基础写法
### 2.复合写法
## 3、外边距
### 1.基础写法
### 2.复合写法
### 3.块级元素水平居中