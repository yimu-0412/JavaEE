# 一、HTTP 协议
## 1、HTTP 是什么
&emsp;&emsp;HTTP（全程为超文本传输协议）是一种应用非常广泛的**应用层协议**。HTTP 协议是沟通前后端的桥梁。

&emsp;&emsp;超文本：所谓 "超文本" 的含义, 就是传输的内容不仅仅是文本(比如 html, css 这个就是文本), 还可以是一些其他的资源, 比如图片, 视频, 音频等二进制的数据.

&emsp;&emsp;HTTP 这种的应用层协议需要下层协议作为支撑，HTTP 协议在传输层主要是基于 TCP 来实现的。TCP 是传输字节流的协议，只是把数据按照字节来传输而已。TCP 并没有传输一个“结构化”的数据。

&emsp;&emsp;应用层协议，就是把传输的字节流赋予了一定的含义。

![分层模型](https://raw.githubusercontent.com/yimu-0412/image/master/image/%E5%88%86%E5%B1%82%E6%A8%A1%E5%9E%8B.png)

&emsp;&emsp;HTTP 诞生与1991年. 目前已经发展为最主流使用的一种应用层协议。

![HTTP 发展历程](https://raw.githubusercontent.com/yimu-0412/image/master/image/HTTP%20%E5%8F%91%E5%B1%95%E5%8E%86%E7%A8%8B.png)

&emsp;&emsp;HTTP 协议主要有三个大版本：

        1. HTTP 1：目前主流使用的版本，基于 TCP。
        2. HTTP 2：也是基于 TCP，主要引入的是安全性（相当于 HTTPS 的加强版本）。
        3. HTTP 3：建设中，基于 UDP，主要是为了提高效率。 

&emsp;&emsp;平时打开网页，就是基于 HTTP 协议来传输数据的。

![网页和HTTP 协议](https://raw.githubusercontent.com/yimu-0412/image/master/image/%E7%BD%91%E9%A1%B5%E5%92%8CHTTP%20%E5%8D%8F%E8%AE%AE.png)

![访问网页图示](https://raw.githubusercontent.com/yimu-0412/image/master/image/%E8%AE%BF%E9%97%AE%E7%BD%91%E9%A1%B5%E5%9B%BE%E7%A4%BA.png)

&emsp;&emsp;当在浏览器中输入一个搜狗搜索的“网址”（URL）时，浏览器就要给搜狗的服务器发送一个 HTTP 请求，搜狗的服务器返回一个 HTTP 响应。

&emsp;&emsp;当响应结果被浏览器解析之后，就展示我们需要的页面内容（这个过程中浏览器可能会给服务器发送多个 HTTP 请求，服务器会返回多个响应，这些响应就包含了页面 HTML，CSS,javaScript，图片，字体等信息）。

## 2、理解“应用层协议”

&emsp;&emsp;使用网络，是为了解决实际问题！

&emsp;&emsp;在已知的网络层次中，传输层、网络层、数据链路层、物理层，每个层次都有各自的使命。都是为了保证数据的传输，而不关心数据的内容。应用层则是**关注数据的具体内容，来解决实际的问题**。

&emsp;&emsp;日常中，我们的精力关注的主要是应用层协议。为了解决当下的某个问题，应用层协议的**请求都要传输什么信息，按照什么样式的格式；响应包含什么信息，按照什么格式传输**[信息 + 格式]。在实际开发中，很多时候都需要程序员自行设计应用层协议。对于现成的应用层协议，需要重点理解里面的 **信息 + 格式**。

## 3、理解 HTTP 协议的工作过程
&emsp;&emsp;当我们在浏览器中输入一个 "网址", 此时浏览器就会给对应的服务器发送一个 HTTP 请求. 对方服务器收到这个请求之后, 经过计算处理, 就会返回一个 HTTP 响应。

 ![HTTP 协议的工作过程](https://raw.githubusercontent.com/yimu-0412/image/master/image/HTTP%20%E5%8D%8F%E8%AE%AE%E7%9A%84%E5%B7%A5%E4%BD%9C%E8%BF%87%E7%A8%8B.png)

 &emsp;&emsp;事实上, 当我们访问一个网站的时候, 可能涉及不止一次的 HTTP 请求/响应 的交互过程.
&emsp;&emsp;可以通过 chrome 的开发者工具观察到这个详细的过程.可以通过 chrome 的开发者工具观察到这个详细的过程。

![HTTP协议交互过程](https://raw.githubusercontent.com/yimu-0412/image/master/image/HTTP%E5%8D%8F%E8%AE%AE%E4%BA%A4%E4%BA%92%E8%BF%87%E7%A8%8B.png)

## 4、HTTP 协议格式
### 1. 抓包工具的使用
&emsp;&emsp;以 Fiddler 为例：（下载地址: https://www.telerik.com/fiddler/）

![Fiddler 界面](https://raw.githubusercontent.com/yimu-0412/image/master/image/Fiddler%20%E7%95%8C%E9%9D%A2.png)

* 左侧窗口显示了所有的 HTTP请求/响应, 可以选中某个请求查看详情。
* 右侧上方显示了 HTTP 请求的报文内容. (切换到 Raw 标签页可以看到详细的数据格式)。
* 右侧下方显示了 HTTP 响应的报文内容. (切换到 Raw 标签页可以看到详细的数据格式)。
* 请求和响应的详细数据, 可以通过右下角的 View in Notepad 通过记事本打开。
  
### 2. 抓包工具的原理
&emsp;&emsp;Fiddler 相当于一个 "代理"。浏览器访问 sogou.com 时, 就会把 HTTP 请求先发给 Fiddler, Fiddler 再把请求转发给 sogou 的服务器，当 sogou 服务器返回数据时, Fiddler 拿到返回数据, 再把数据交给浏览器。因此 Fiddler 对于浏览器和 sogou 服务器之间交互的数据细节, 都是非常清楚的。

&emsp;&emsp;**正向代理**:站在服务器的角度，把客户端进行隐藏。

&emsp;&emsp;**反向代理**:站在客户端的角度，将真实的服务器进行隐藏。

### 3. 抓包结果

1. **HTTP 请求**：

![HTTP 请求（sogou抓包）](https://raw.githubusercontent.com/yimu-0412/image/master/image/HTTP%20%E8%AF%B7%E6%B1%82%EF%BC%88sogou%E6%8A%93%E5%8C%85%EF%BC%89.png)

   * 首行: [方法] + [url] + [版本]。
   * Header: 请求的属性, 冒号分割的键值对;每组属性之间使用\n分隔;遇到**空行**表Header部分结束。
   * Body: 空行后面的内容都是Body。Body允许为空字符串. 如果Body存在, 则在Header中会有一个``Content-Length``属性来标识Body的长度。
  
2. **HTTP 响应**：

![HTTP 响应（sogou抓包）](https://raw.githubusercontent.com/yimu-0412/image/master/image/HTTP%20%E5%93%8D%E5%BA%94%EF%BC%88sogou%E6%8A%93%E5%8C%85%EF%BC%89.png)

* 首行: [版本号] + [状态码] + [状态码解释]。
* Header: 请求的属性, 冒号分割的键值对;每组属性之间使用\n分隔;遇到空行表示Header部分结束Header: 请求的属性, 冒号分割的键值对;每组属性之间使用\n分隔;遇到**空行**表Header部分结束。
* Body: 空行后面的内容都是Body. Body允许为空字符串. 如果Body存在, 则在Header中会有一个``Content-Length``属性来标识Body的长度; 如果服务器返回了一个html页面, 那么html页面内容就是在body中.

### 4. 协议格式的总结


![HTTP 协议格式总结](https://raw.githubusercontent.com/yimu-0412/image/master/image/HTTP%20%E5%8D%8F%E8%AE%AE%E6%A0%BC%E5%BC%8F%E6%80%BB%E7%BB%93.png)

&emsp;&emsp;**思考问题**：为什么 HTTP 报文中要存在“空行”？

        因为 HTTP 协议并没有规定报头部分的键值对有多少个. 空行就相当于是 "报头的结束标记", 或者是 "报头和正文之间的分隔符"。HTTP 在传输层依赖 TCP 协议, TCP 是面向字节流的. 如果没有这个空行, 就会出现 "粘包问题"。

## 5、HTTP 请求（request）
### 1.认识 URL

&emsp;&emsp;平时我们俗称的“网址”其实就是指的就是 URL（Unifrom Resource Locator 统一资源定位）。

&emsp;&emsp;互联网上的每个文件都有一个唯一的 URL，它包含的信息指出文件的位置以及浏览器应该怎么处理它。

&emsp;&emsp;URL 的详细规则由因特网标准 RFC1738 进行了约定。(https://datatracker.ietf.org/doc/html/rfc1738）。

![URL 地址](https://raw.githubusercontent.com/yimu-0412/image/master/image/URL%20%E5%9C%B0%E5%9D%80.png)

1. 协议名称：URL 不仅仅是为 HTTP 服务的，还可以为其他协议提供服务。（HTTPS、FTP、jdbc:mysql://、file：//）
2. 用户名密码：现在已经废弃了，不再使用
3. 服务器地址：协议名称后面的“//”内容是固定的。``www.sogou.com``域名，本质就是一个IP 地址，可以写域名，也可以写 IP 地址，写域名的意思是为了方便记忆。
4. 端口号：“域名后面：”，表示服务器要访问的端口。如果不写端口号，浏览器会给一个默认的端口号。（HTTP 是80，HTTPS 是443）
5. 路径：URL 上的路径，表示访问服务器上的不同资源。（一个服务程序上会有很多的资源，例如图片、CSS、JS等）
6. URL 中的参数（查询字符串）：浏览器给服务器传递的一些参数，程序员自己定义的。也可以没有，这些参数都是以键值对的方式进行组织。

        ```
        query=蛋糕&_asf=www.sogou.com&_ast=&w=01019900&p=40040100&ie=utf8&from=index-nologin&s_from=index&sut=2434&sst0=1641648725946&lkt=0%2C0%2C0&sugsuv=1635424571781841&sugtime=1641648725946
        ```
   * 键值对之间，采用 & 分割
   * 键和值之间，采用 = 分割
   * query string 整体，使用 ？作为起始标志。

**URL 设计的初心**： 
1. 先通过服务器地址，定位到一个具体的服务器
2. 在通过端口号定位到一个具体的应用程序
3. 在通过路径定位到应用程序的一个具体资源
4. 在通过查询字符串，对这个具体的资源的要求作出进一步的解释
5. 最后通过片段标识来确定定位到这个资源的哪个部分
   
### 2.认识“方法”（method）

![HTTP 协议方法](https://raw.githubusercontent.com/yimu-0412/image/master/image/HTTP%20%E5%8D%8F%E8%AE%AE%E6%96%B9%E6%B3%95.png)

1. **GET 方法**

&emsp;&emsp;GET 是最常用的 HTTP 方法。常用于获取服务器上的某个标签。
&emsp;&emsp;在浏览器上直接输入 url，此时浏览器就会发送一个 GET 请求。另外，HTML 中的link、img、script等标签，也会触发 GET 请求。

&emsp;&emsp;**使用 Fidder 观察 GET 请求**

&emsp;&emsp;打开 fidder，访问搜狗主页，观察抓包结果。

![搜狗主页抓包结果](https://raw.githubusercontent.com/yimu-0412/image/master/image/%E6%90%9C%E7%8B%97%E4%B8%BB%E9%A1%B5%E6%8A%93%E5%8C%85%E7%BB%93%E6%9E%9C.png)

&emsp;&emsp;在以上的结果中可以看到：

&emsp;&emsp;最上面的![搜狗抓包](https://raw.githubusercontent.com/yimu-0412/image/master/image/%E6%90%9C%E7%8B%97%E6%8A%93%E5%8C%85.jpg)是通过浏览器地址栏发送的 GET 请求。下面的和 sogou 域名相关的请求，有些是通过 html 中的link/script/img 标签产生的。例如：

![搜狗标签触发的GET请求](https://raw.githubusercontent.com/yimu-0412/image/master/image/%E6%90%9C%E7%8B%97%E6%A0%87%E7%AD%BE%E8%A7%A6%E5%8F%91%E7%9A%84GET%E8%AF%B7%E6%B1%82.png)

&emsp;&emsp;有些是通过 ajax 的方式产生的，例如：

![ajax 产生的GET请求](https://raw.githubusercontent.com/yimu-0412/image/master/image/ajax%20%E4%BA%A7%E7%94%9F%E7%9A%84GET%E8%AF%B7%E6%B1%82.png)

&emsp;&emsp;选中第一条观察请求的详细结果：

```
        GET https://www.sogou.com/ HTTP/1.1
        Host: www.sogou.com
        Connection: keep-alive
        sec-ch-ua: " Not;A Brand";v="99", "Microsoft Edge";v="97", "Chromium";v="97"
        sec-ch-ua-mobile: ?0
        sec-ch-ua-platform: "Windows"
        Upgrade-Insecure-Requests: 1
        User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/97.0.4692.71 Safari/537.36 Edg/97.0.1072.55
        Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9
        Sec-Fetch-Site: cross-site
        Sec-Fetch-Mode: navigate
        Sec-Fetch-Dest: document
        Accept-Encoding: gzip, deflate, br
        Accept-Language: zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6
        Cookie: SUID=210A193C7450A00A00000000617A993C; SUV=1635424571781841; usid=210A193C963C990A00000000617A994A; ssuid=8213280075; 
        SGINPUT_UPSCREEN=1638456013689; SMYUV=1638795123776349; sw_uuid=8055982996; ABTEST=4|1641388418|v17; browerV=3; osV=1; 
        SNUID=796173566A6EBE96366277A36B5D45F6; IPLOC=CN1200; ld=Ckllllllll2PrjgEYzZO@u4B6OPPrjgM5x6aMlllll9llllljllll5@@@@@@@@@@; sst0=946
```
&emsp;&emsp;**GET 请求的特点**：

* 首行的第一部分为 GET
* URL 的 query String 可以为空，也可以不为空
* header 部分有若干个键值对结构
* body 部分为空

      关于GET请求的 URL 的长度问题：网上有些资料上描述：get请求长度最多1024kb 这样的说法是错误的。

      HTTP 协议由 RFC 2616 标准定义，标准中原文明确说明：“"Hypertext Transfer Protocol HTTP/1.1," does not specify any requirement for URL length.”没有对 URL 的长度有任何的限制。

      实际上 URL 的长度取决于浏览器的实现和HTTP 服务端的实现。在浏览器端，不同的浏览器的最大长度是不同的。但是现代浏览器支持的长度一般都很长；在服务器端，这个长度是可以配置的。

2. **POST 方法**

&emsp;&emsp;POST 方法也是一种常见的方法. 多用于提交用户输入的数据给服务器(例如登陆页面)。通过 HTML 中的 form 标签可以构造 POST 请求, 或者使用 JavaScript 的 ajax 也可以构造 POST 请求。

&emsp;&emsp;**使用 Fiddler 观察 POST 方法 **：

观察网易邮箱登录页面的 POST 请求：

```
HTTP/1.1 200 OK
Server: nginx
Date: Sat, 08 Jan 2022 15:17:38 GMT
Content-Type: text/html
Connection: keep-alive
Vary: Accept-Encoding
Last-Modified: Thu, 06 Jan 2022 13:11:20 GMT
Expires: Sat, 08 Jan 2022 16:17:38 GMT
Cache-Control: max-age=3600
Strict-Transport-Security: max-age=31536000; preload
Content-Length: 17200

<!doctype html><html lang="zh_CN"><head><meta http-equiv="X-UA-Compatible" content="IE=edge"><meta http-equiv="Content-type" content="text/html;charset=utf-8"><title data-lang-key="网易企业邮箱 - 登录入口">网易企业邮箱 - 登录入口</title><meta name="keywords" content="网易企业邮箱,登录企业邮箱,企业邮箱注册,电子邮箱"/><meta name="description" content="登录网易企业邮箱，请填写完整的邮件地址或管理员帐号，支持手机扫码登录。用邮箱大师，随时随地，极速收发。"/><link rel="shortcut icon" href="https://qiye.163.com/favicon.ico" type="image/x-icon"/><link rel="prefetch" as="script" href="//mimg.127.net/p/freemail/lib/track/raven-3.27.0.min.js"><script id="jsBase" src="//mimg.127.net/index/lib/scripts/base_v3.js"></script><script src="//mimg.qiye.163.com/o/index/lib/scripts/qiye_algorithm.js"></script><script src="//mimg.127.net/p/freemail/lib/track/raven-3.27.0.min.js"></script><script id="jsBaseInner">//判断cookie
        fCheckCookie();
        //HTML5初始化
        fHtml5Tag();</script><link href="css/main.4898c72c.css" rel="stylesheet"></head><body><header class="g-hd"><!--[if lt IE 9]>
        <div class="upgrade-tips" id="upgradeTips" style="display: block">
                <span class="tips-content" id="tipsContent"><i class="hint"></i>您正在使用的浏览器版本过低，将不能正常访问网易邮箱，建议您使用谷歌浏览器，或<a class="tips-link" target="_blank" href="https://support.microsoft.com/zh-cn/help/17621/internet-explorer-downloads">升级IE浏览器</a>。</span><i class="del" id="tipsDel">X</i>
                </div>
                <![endif]--><div><h1 class="w-qiyelogo"><a href="//qiye.163.com/" target="_blank" data-lang-title="中国第一大电子邮件服务商" title="中国第一大电子邮件服务商" data-tj-key="b_Logo_click"></a></h1><nav class="m-hdnav"><a href="//qiye.163.com/entry/buy-price.htm?from=login_pc" target="_blank" data-tj-key="b_Registe_click" data-lang-key="新用户开通" id="xyhkt">新用户开通</a> <a href="?hl=zh_CN" data-tj-key="b_CN_Language_click" id="hlCn" class="f-hide">简体版</a> <a href="?hl=en_US" data-tj-key="b_EN_Language_click" id="hlEn">English</a> <a href="https://hw.qiye.163.com/" target="_blank" data-tj-key="b_ForeignUserLogin_click" data-lang-key="国外用户登录" id="gwyhdl">国外用户登录</a> <a download href="javascript:;" id="lxbg" class="official-app">网易邮箱官方客户端<div class="download-triangle"></div><div class="download-qrcode"><img src="https://cowork-storage-public-cdn.lx.netease.com/qyy/2021/07/20/3f123d729c924c6692926a292384171d" width="102" height="102"/><p>扫码下载官方APP</p></div></a><a href="http://mail.163.com/dashi/dlpro.html?from=mail45" target="_blank" data-tj-key="b_Dashi_click" data-lang-key="邮箱大师" id="yxds">邮箱大师</a> <a id="help-url-id" href="javascript:void(0);" target="_blank" data-tj-key="b_Help_click" data-lang-key="帮助中心">帮助中心</a></nav></div></header><section class="g-bd"><div class="g-bd-mn js-bdImg" id="bdImg"><div class="m-theme"><div class="g-wrap"><a id="linkTheme" class="link js-linkTheme" href="javascript:;" target="_blank" data-tj-key="b_BdImg_click"></a><div class="themectrl"><a class="js-prevTheme" id="prevTheme" href="javascript:void(0);" data-lang-title="上一张" title="上一张"></a> <a class="js-nextTheme" id="nextTheme" href="javascript:void(0);" data-lang-title="下一张" title="下一张"></a></div></div></div><div id="loginBlock" class="m-login m-login-with-ad js-loginpanel"><div class="new-loginFunc"></div><div class="login-bd"><h3 class="loginbox-title"><span class="active" id="switchAccCtrl" data-lang-key="邮箱帐号登录">邮箱帐号登录</span><span id="switchAdminCtrl" data-lang-key="管理员登录">管理员登录</span></h3><form class="login-form login-form-acc js-loginform js-loginform-acc" name="accountlogin" action="https://entry.qiye.163.com/domain/domainEntLogin" method="post" target="_top"><input type="hidden" class="js-domain" name="domain" value=""> <input type="hidden" class="js-accname" name="account_name" value=""> <input type="hidden" class="js-isSecure" name="secure" value="1"> <input type="hidden" class="js-isAllSecure" name="all_secure" value="1"> <input type="hidden" class="js-language" name="language" value="0"> <input type="hidden" class="js-pubid" name="pubid" value=""> <input type="hidden" class="js-passtype" name="passtype" value=""> <input type="hidden" class="js-referer" name="referer" value=""> <input type="hidden" class="js-module" name="module" value=""> <input type="hidden" class="js-ua" name="ua" value=""> <input type="hidden" class="js-ch" name="ch" value=""><div class="m-ipt js-ipt"><span class="icon icon-account"></span> <input id="accname" class="ipt js-value js-username" tabindex="1" data-lang-placeholder="邮箱地址" placeholder="邮箱地址" data-lang-title="请输入完整邮箱地址" title="请输入完整邮箱地址" name="accname"><div class="m-error"></div></div><div class="m-ipt js-ipt"><span class="icon icon-pwd"></span> <input id="accpwd" class="ipt js-value js-pwd" tabindex="2" data-lang-placeholder="密码" placeholder="密码" data-lang-title="请输入密码" title="请输入密码" type="password" name="password"><div class="m-error"></div></div><div><div class="m-verifycode"><div class="m-ipt js-ipt"><input id="accverifycode" class="ipt js-value js-verifycode" tabindex="2" data-lang-title="请输入验证码" title="请输入验证码" data-lang-placeholder="验证码" placeholder="验证码" name="verify_code"><div class="m-error"></div></div><img width="90" id="imgVerifycode" class="refreshVerifycode" data-lang-title="点击切换" title="点击切换"></div></div><div class="loginconf"><div class="logincheck js-logincheck"><span class="icon icon-checkbox js-checkbox"></span> <label for="accautologin" class="checklabel js-autolabel"><input tabindex="3" data-lang-title="记住帐号" title="记住帐号" class="checkipt js-autologin" type="checkbox" id="accautologin"> <span data-lang-key="记住帐号">记住帐号</span></label><div class="securetip js-securetip" data-lang-key="为了您的信息安全，请不要在网吧或公用电脑上使用此功能！">为了您的信息安全，请不要在网吧或公用电脑上使用此功能！</div></div><a href="https://mail.qiye.163.com/static/qiyeurs/?from=http%3A%2F%2Fmail.qiye.163.com%2F#/resetPwd" class="forgetpwd" target="_blank" data-tj-key="b_ResetPwd_click" data-lang-key="忘记密码">忘记密码</a></div><div class="loginbtn"><button class="w-button w-button-account js-loginbtn" type="submit" tabindex="4" onclick="return formActionReset()" data-lang-key="登 录">登 录</button></div><div id="accLoginSslSelector" class="loginact"><div class="loginselect"><a class="selector js-sslsel" href="javascript:;" hidefocus="true"><span data-lang-key="正使用">正使用</span><span class="js-ssltxt" data-lang-key="全程SSL">全程SSL</span><span class="icon icon-arrow"></span></a><div class="m-lgselect js-lgselect"><ul><li><a class="js-selitem selected" href="javascript:;" hidefocus="true" data-allssl="1" data-tj-value="1" data-lang-key="全程SSL">全程SSL</a></li><li><a class="js-selitem" href="javascript:;" hidefocus="true" data-allssl="0" data-tj-value="0" data-lang-key="SSL登录">SSL登录</a></li></ul></div></div><div class="chselect"><a class="selector js-chsel" href="javascript:;" hidefocus="true"><span data-lang-key="正在使用">正在使用</span><span class="js-chtxt" data-lang-key="默认线路">默认线路</span><span class="icon icon-arrow"></span></a><div class="m-lgselect js-lgchselect"><ul><li><a class="js-selitem selected" href="javascript:;" hidefocus="true" data-ch="" data-tj-value="1" data-lang-key="默认线路">默认线路</a></li><li><a class="js-selitem" href="javascript:;" hidefocus="true" data-ch="hw" data-lang-key="国际线路">国际线路</a></li></ul></div></div></div></form><form class="login-form login-form-admin js-loginform js-loginform-admin" name="adminlogin" action="https://entry.qiye.163.com/domain/domainAdminLogin" method="post" target="_top"><input type="hidden" class="js-domain" name="domain" value=""> <input type="hidden" class="js-accname" name="account_name" value=""> <input type="hidden" class="js-isSecure" name="secure" value="1"> <input type="hidden" class="js-isAllSecure" name="all_secure" value="1"> <input type="hidden" class="js-language" name="language" value="0"> <input type="hidden" class="js-pubid" name="pubid" value=""> <input type="hidden" class="js-passtype" name="passtype" value=""> <input type="hidden" class="js-target" name="target" value=""> <input type="hidden" class="js-ua" name="ua" value=""> <input type="hidden" class="js-ch" name="ch" value=""><div class="m-ipt js-ipt"><span class="icon icon-account"></span> <input id="adminname" class="ipt js-value js-username" tabindex="1" data-lang-title="请输入完整邮箱地址" title="请输入完整邮箱地址" data-lang-placeholder="邮箱地址" placeholder="邮箱地址" name="accname"><div class="m-error"></div></div><div class="m-ipt js-ipt"><span class="icon icon-pwd"></span> <input id="adminpwd" class="ipt js-value js-pwd" tabindex="2" data-lang-title="请输入密码" title="请输入密码" data-lang-placeholder="密码" placeholder="密码" type="password" name="password"><div class="m-error"></div></div><div><div class="m-verifycode"><div class="m-ipt js-ipt"><input id="adminverifycode" class="ipt js-value js-verifycode" tabindex="2" data-lang-title="请输入验证码" title="请输入验证码" data-lang-placeholder="验证码" placeholder="验证码" name="verify_code"><div class="close-bg"></div><div class="m-error"></div></div><img width="90" id="imgadminVerifycode" class="refreshVerifycode" data-lang-title="点击切换" title="点击切换"></div></div><div class="loginconf"><div class="logincheck js-logincheck"><span class="icon icon-checkbox js-checkbox"></span> <label for="adminautologin" class="checklabel js-autolabel"><input tabindex="3" data-lang-title="记住帐号" title="记住帐号" class="checkipt js-autologin" type="checkbox" id="adminautologin"> <span data-lang-key="记住帐号">记住帐号</span></label><div class="securetip js-securetip" data-lang-key="为了您的信息安全，请不要在网吧或公用电脑上使用此功能！">为了您的信息安全，请不要在网吧或公用电脑上使用此功能！</div></div><a href="https://mail.qiye.163.com/mailapp/qiyeurs/?from=http%3A%2F%2Fmail.qiye.163.com%2F#/resetPwd" class="forgetpwd" target="_blank" data-tj-key="b_ResetAdminPwd_click" data-lang-key="忘记密码">忘记密码</a></div><div class="loginbtn"><button class="w-button w-button-admin js-loginbtn" type="submit" tabindex="4" onclick="return formAdminActionReset()" data-lang-key="管理员登录">管理员登录</button></div><div class="loginact" id="adminLoginSslSelector"><div class="loginselect"><div class="selector" data-lang-key="正使用全程SSL">正使用全程SSL</div></div><div class="chselect"><a class="selector js-chsel" href="javascript:;" hidefocus="true"><span data-lang-key="正在使用">正在使用</span><span class="js-chtxt" data-lang-key="默认线路">默认线路</span><span class="icon icon-arrow"></span></a><div class="m-lgselect js-lgchselect"><ul><li><a class="js-selitem selected" href="javascript:;" hidefocus="true" data-ch="" data-lang-key="默认线路">默认线路</a></li><li><a class="js-selitem" href="javascript:;" hidefocus="true" data-ch="hw" data-lang-key="国际线路">国际线路</a></li></ul></div></div></div></form><div id="msgpid" class="loginerror"></div><div class="js-switch-qrcode"><span class="icon-dashi" data-type="1" id="dashiApp"></span> <span class="icon-wx" data-type="2" id="wxApp"></span></div></div><div class="m-codebox js-codebox f-zindex-10"><div id="appLoginTab" class="appLoginTab"><h3><span data-lang-key="请使用">请使用</span><span id="appLoginText"></span><span data-lang-key="扫描二维码登录">扫描二维码登录</span></h3><div id="appLoginWait" style="display: block;"><div id="appCodeWrap" class="appCodeWrap allowmove"><div class="appCode-example"></div><div id="appCodeBox" class="appCodeBox"><img id="appCode" class="appCode" width="180" height="180" src="https://mail.qiye.163.com/commonweb/qrcode/getqrcode.do?w=130&h=130"><div id="appCodeRefresh" class="appCodeRefresh" style="display: none;"><div class="appCode-mask"></div><div class="appCode-wrap"><p data-lang-key="二维码已失效">二维码已失效</p><a href="javascript:;" data-tj-key="b_appLogin_refresh_qrcode_click" data-lang-key="请点击刷新">请点击刷新</a></div></div></div></div><p id="appLoginTxtNormal" class="appLoginTxt appLoginTxtNormal txt-err"><span data-lang-key="扫码并关注网易企业邮箱服务号">扫码并关注网易企业邮箱服务号</span><a href="https://qiye.163.com/help/l-3.html" target="_blank"></a></p><p id="appLoginTxt" class="appLoginTxt txt-err" data-lang-key="请您在手机端完成邮箱帐号绑定">请您在手机端完成邮箱帐号绑定<a href="https://qiye.163.com/help/l-3.html" target="_blank"></a></p></div><div id="appLoginScan" class="appLoginScan" style="display:none"><div class="appLogin-scanSuc"></div><p class="appLogin-scantxt txt-suc" data-lang-key="成功扫描，请在手机上确认登录">成功扫描，请在手机上确认登录</p><a id="appLoginRestart" class="appLoginRestart" href="javascript:void(0)" data-lang-key="返回重新扫描">返回重新扫描</a></div></div><div id="qrcodeLoginSslSelector" class="loginact"><input type="hidden" id="appCh" value="0"><div class="loginselect"><a class="selector js-sslsel" href="javascript:;" hidefocus="true"><span data-lang-key="正使用">正使用</span><span class="js-ssltxt" data-lang-key="全程SSL">全程SSL</span><span class="icon icon-arrow"></span></a><div class="m-lgselect js-lgselect"><ul><li><a class="js-selitem selected" href="javascript:;" hidefocus="true" data-allssl="1" data-lang-key="全程SSL">全程SSL</a></li><li><a class="js-selitem" href="javascript:;" hidefocus="true" data-allssl="0" data-lang-key="SSL登录">SSL登录</a></li></ul></div></div><div class="chselect"><a class="selector js-chsel" href="javascript:;" hidefocus="true"><span data-lang-key="正在使用">正在使用</span><span class="js-chtxt" data-lang-key="默认线路">默认线路</span><span class="icon icon-arrow"></span></a><div class="m-lgselect js-lgchselect"><ul><li><a class="js-selitem selected" href="javascript:;" hidefocus="true" data-ch="" data-lang-key="默认线路">默认线路</a></li><li><a class="js-selitem" href="javascript:;" hidefocus="true" data-ch="hw" data-lang-key="国际线路">国际线路</a></li></ul></div></div></div><div class="pane-handler"><a href="javascript:;" id="switchNormalCtrl" data-tj-key="b_AccountPWD_text_click" data-lang-key="密码登录">密码登录</a></div></div><div id="normalLoginFormMask" class="login-form-mask"><p class="login-form-mask-loading"><i></i><span data-lang-key="载入中...">载入中...</span></p></div><div id="loginPanelBottomAdBlock" class="m-login-bottom-ad"></div></div></div></section><footer class="g-ft"><div class="g-wrap"><nav class="m-ftnav"><a href="http://gb.corp.163.com/gb/home.shtml" target="_blank" data-tj-key="b_AboutNetease_click" data-lang-key="关于网易">关于网易</a><a href="http://weibo.com/163qiye" target="_blank" data-tj-key="b_Weibo_click" data-lang-key="官方微博">官方微博</a><a href="http://gb.corp.163.com/gb/legal.html" target="_blank" data-tj-key="b_AboutLegal_click" data-lang-key="相关法律">相关法律</a><a href="https://reg.163.com/agreement_mobile_ysbh_wap.shtml?v=20171127" target="_blank" data-tj-key="b_PrivacyPolicy_click" data-lang-key="隐私政策">隐私政策</a>&emsp;|&emsp;<span data-lang-key="网易公司版权所有">网易公司版权所有</span>&copy;1997-<script src="//mimg.127.net/copyright/year.js"></script><a id="KX_IMG" class="w-kximg" href="https://ss.knet.cn/verifyseal.dll?sn=e12051044010020841301459&ct=df&pa=151131" target="_blank"><img src="//mimg.127.net/logo/knet.png" alt="可信网站，身份验证"></a></nav></div></footer><img src="https://ssl.mail.163.com/httpsEnable.gif" style="position: absolute;left: -999em;top: -999em;width: 0;height: 0;"/><script>if(!Function.prototype.bind){
        Function.prototype.bind = function(){
                if(typeof this !== 'function'){
                　　　throw new TypeError('Function.prototype.bind - what is trying to be bound is not callable');
                }
                var _this = this;
                var obj = arguments[0];
                var ags = Array.prototype.slice.call(arguments,1);
                return function(){
                _this.apply(obj,ags);
                };
        };
}</script><script src="https://mimg.127.net/p/freemail/lib/polyfill/es5-polyfill.js"></script><script src="js/bundle.4898c72c.js"></script></body></html>
```
&emsp;&emsp;**POST 请求的特点**:

* 首行的第一部分为 POST
* URL 的 query string 一般为空 (也可以不为空)
* header 部分有若干个键值对结构.
* body 部分一般不为空. body 内的数据格式通过 header 中的 Content-Type 指定. body 的长度由header 中的``Content-Length``指定。

3. **经典面试题**：谈谈 GET 和 POST 的区别？

* 语义不同：GET 一般用于获取数据。POST 一般用于提交数据。
* GET 的body一般为空，需要传递数据时通过 query String 传递，POST 的 query String一般为空，需要数据时一般通过 body 传递。
* GET 请求一般是幂等的，POST 请求一般不是幂等的（如果多次请求得到的结果是一样的，就视为请求是幂等的）。
* GET 可以被缓存，POST 不能被缓存（这一点也是承接幂等的）。

&emsp;&emsp;**补充说明**：

* 关于语义：GET 完全可以用于提交数据；POST 完全可以用于获取数据。
* 关于幂等性：标准建议GET实现为幂等的。实际开发中GET也不必完全遵守这个规则。
* 关于安全性：有些资料说“POST 比 GET 更安全”，这个说法是不科学的，是否安全取决于前端在传输密码等敏感信息时是否进行加密。和 GET、POST 无关。
* 关于数据传输量：有的资料上说：“GET 传输的数据量小，POST 传输的数据量大”。这个也是不科学的，标准没有规定 GET 的 URL 长度，也没有规定 POST 的body长度，传输量的多少，完全取决于不同浏览器和不同服务器之间的实现区别。
* 关于数据传输类型：有的资料上说：“GET 只能传输文本数据，POST 可以传输二进制数据”。这个也是完全不科学的。GET 的 query String虽然无法直接传输二进制数据，但是可以针对二进制数据进行 url encode。

4.** 其他方法**
        
1. PUT 和 POST 相似，只是具有幂等特性，一般用于更新。
2. DELETE 删除服务区指定资源。
3. OPTIONS 返回服务器所支持的请求方法。
4. HEAD 类似于 GET，只不过响应体不返回，只返回响应头。
5. TRACE 回显服务器所支持的请求方法，测试的时候会用到这个
6. CONNECT 预留，暂无使用 

### 3.认识请求“报头”（header）

&emsp;&emsp;header 的整体格式也是“键值对”结构。 每个键值对占一行，键和值之间使用分号隔开。

&emsp;&emsp;**Host**：表示服务器主机的地址和端口。

&emsp;&emsp;**Content-length**：表示body中的数据长度。

&emsp;&emsp;**Content-Type**：表示请求的body的数据类型。

&emsp;&emsp;**常见选项**：

* application/x-www-from-urlencoded:from 表单提交的数据格式。此时body的格式形如：
  
        ```
        title=test&coontent=hello
        ```
* multipart/from-data:from表单提交的数据格式（在from标签中加上 enctyped="multipart/form-data"）。通常用于提交图片/文件。body的格式如下：

```
Content-Type:multipart/form-data; boundary=----
WebKitFormBoundaryrGKCBY7qhFd3TrwA

------WebKitFormBoundaryrGKCBY7qhFd3TrwA
Content-Disposition: form-data; name="text"

title
------WebKitFormBoundaryrGKCBY7qhFd3TrwA
Content-Disposition: form-data; name="file"; filename="chrome.png"
Content-Type: image/png

PNG ... content of chrome.png ...
------WebKitFormBoundaryrGKCBY7qhFd3TrwA--
```
* application/json: 数据为 json 格式. body 格式形如:

```
{"username":"123456789","password":"xxxx","code":"jw7l","uuid":"d110a05ccde64b16a861fa2bddfdcd15"}
```

&emsp;&emsp;**User-Agent(简称UA)**:

&emsp;&emsp;表示浏览器/操作系统的属性。形如：

```
Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko)Chrome/91.0.4472.77 Safari/537.36
```
&emsp;&emsp;其中``Windows NT 10.0; Win64; x64``表示操作系统信息。`` AppleWebKit/537.36 (KHTML, like Gecko)Chrome/91.0.4472.77 Safari/537.36``表示浏览器的信息。

&emsp;&emsp;UA 的新使命：可以用来区分PC端和移动端。同一个网站，PC 和手机分别访问，看到的网页排版，可能是不一样的。

&emsp;&emsp;**Referer**

&emsp;&emsp;表示这个页面是从哪里来（从那个页面跳转过来的）。

&emsp;&emsp;直接在浏览器地址栏输入 URL/点击收藏夹打开的页面是没有 Referer 的。通过搜索主页，跳转到搜索结果页就带有 Referer。

&emsp;&emsp;每个 HTTP 请求的 Referer 就是上个跳转之前的页面。 

&emsp;&emsp;**Cookie**

&emsp;&emsp;Cookie 中存储了一个字符串，这个数据可能是客户端（网页）自行通过js写入的，也可能来自于服务器（服务器在 HTTP 响应的 header 中通过 Set-Cookie）字段给浏览器返回数据。往往通过这个字段实现“**身份标识**”的功能。

&emsp;&emsp;每个不同的域名下都可以有不同的 Cookie，不同网站之间的 Cookie 并不冲突。

&emsp;&emsp;可以通过抓包观察页面登录的过程（以码云为例）：

1. **清除之前的 Cookie**

&emsp;&emsp;为了方便观察，先清除掉之前登录的 Cookie

&emsp;&emsp;在码云页面上，点击 url 左侧的图标。选择 Cookie

![码云Cookie抓包](https://raw.githubusercontent.com/yimu-0412/image/master/image/%E7%A0%81%E4%BA%91Cookie%E6%8A%93%E5%8C%85.png)

&emsp;&emsp;然后移除已经存在的 Cookie

![清除码云Cookie](https://raw.githubusercontent.com/yimu-0412/image/master/image/%E6%B8%85%E9%99%A4%E7%A0%81%E4%BA%91Cookie.png)

2. **登录操作**

&emsp;&emsp;登录请求

```
POST https://gitee.com/login HTTP/1.1
Host: gitee.com
Connection: keep-alive
Content-Length: 394
Cache-Control: max-age=0
sec-ch-ua: " Not;A Brand";v="99", "Google Chrome";v="91", "Chromium";v="91"
sec-ch-ua-mobile: ?0
Upgrade-Insecure-Requests: 1
Origin: https://gitee.com
Content-Type: application/x-www-form-urlencoded
User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML,
like Gecko) Chrome/91.0.4472.101 Safari/537.36
Accept:
text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,imag
e/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9
Sec-Fetch-Site: same-origin
Sec-Fetch-Mode: navigate
Sec-Fetch-User: ?1
Sec-Fetch-Dest: document
Referer: https://gitee.com/login
Accept-Encoding: gzip, deflate, br
Accept-Language: zh-CN,zh;q=0.9,en;q=0.8
encrypt_key=password&utf8=%E2%9C%93&authenticity_token=36ZqO9tglSN6EB6pF6f2Gt%2B
dalgkbpTDUsJC5OER7w8%3D&redirect_to_url=%2FHGtz2222&user%5Blogin%5D=HGtz2222&enc
rypt_data%5Buser%5Bpassword%5D%5D=Hy2gjJ60312Ss12jSe21GMLPEb766tAhCygL281FLRMpiz
xJVaWGOPlQF7lZhelab1HS2vBiwfBo5C7BnR5ospoBiK1hR6jNXv1lesaYifv9dP1iRC6ozLLMszo%2F
aRh5j5DeYRyKcE0QJjXRGEDg4emXEK1LHVY4M1uqzFS0W58%3D&user%5Bremember_me%5D=0
```

&emsp;&emsp;登录响应

```
HTTP/1.1 302 Found
Date: Thu, 10 Jun 2021 04:15:58 GMT
Content-Type: text/html; charset=utf-8
Connection: keep-alive
Keep-Alive: timeout=60
Server: nginx
X-XSS-Protection: 1; mode=block
X-Content-Type-Options: nosniff
X-UA-Compatible: chrome=1
Expires: Sun, 1 Jan 2000 01:00:00 GMT
Pragma: must-revalidate, no-cache, private
Location: https://gitee.com/HGtz2222
Cache-Control: no-cache
Set-Cookie: oschina_new_user=false; path=/; expires=Mon, 10 Jun 2041 04:16:00
-0000
Set-Cookie: gitee_user=true; path=/
Set-Cookie: gitee-sessionn=
M1Rhbk1QUUxQdWk1VEZVQ1BvZXYybG13ZUJFNGR1V0pSYTZyTllEa21pVHlBUE5QU2Qwdk44NXdEam
11T3FZYXFidGNYaFJxcTVDRE1xU05GUXN0ek1Uc08reXRTay9ueTV3OGl5bTdzVGJjU1lUbTJ4bTUvN1
l3RFl4N2hNQmI1SEZpdmVJWStETlJrdWtyU0lDckhvSGJHc3NEZDFWdHc5cjdHaGVtNThNcEVOeFZlaH
c0WVY5NGUzWjc2cjdOcCtSdVJ0VndzdVNxb3dHK1hPSDBDSFB6WlZDc3prUVZ2RVJyTnpTb1c4aFg1Mm
UxM1YvQTFkb1EwaU4zT3hJcmRrS3dxVFZJNXoxaVJwa1liMlplbWR5QXQxY0lvUDNic1hxN2o0WDg1Wk
E9LS10N0VIYXg4Vm5xdllHVzdxa0VlUEp3PT0%3D-
-2f6a24f8d33929fe88ed19d4dea495fbb40ebed6; domain=.gitee.com; path=/; HttpOnly
X-Request-Id: 77f12d095edc98fab27d040a861f63b1
X-Runtime: 0.166621
Content-Length: 92
<html><body>You are being <a href="https://gitee.com/HGtz2222">redirected</a>.
</body></html>
```

        可以看到，响应中包含了3个 Set-Cookie 属性。其中重点关注的是第三个，里面包含了一个 gitee-session-n 这样的属性, 属性值是一串很长的加密之后的信息. 这个信息就是用户当前登陆的身份标识. 也称为 "令牌(token)"

 3. **访问其他页面**

&emsp;&emsp;登陆成功之后，此时可以看到后续访问码云的其他页面（比如个人主页），请求中就会带着刚才获取到的 Cookie 信息。

```
GET https://gitee.com/HGtz2222 HTTP/1.1
Host: gitee.com
Connection: keep-alive
Cache-Control: max-age=0
Upgrade-Insecure-Requests: 1
User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML,
like Gecko) Chrome/91.0.4472.101 Safari/537.36
Accept:
text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,imag
e/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9
Sec-Fetch-Site: same-origin
Sec-Fetch-Mode: navigate
Sec-Fetch-User: ?1
Sec-Fetch-Dest: document
sec-ch-ua: " Not;A Brand";v="99", "Google Chrome";v="91", "Chromium";v="91"
sec-ch-ua-mobile: ?0
Referer: https://gitee.com/login
Accept-Encoding: gzip, deflate, br
Accept-Language: zh-CN,zh;q=0.9,en;q=0.8
Cookie: oschina_new_user=false; user_locale=zh-CN; yp_riddler_id=1ce4a551-a160-
4358-aa73-472762c79dc0; visit-gitee--2021-05-06%2010%3A12%3A24%20%2B0800=1;
sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%22726826%22%2C%22first_id%22%3
A%22175869ba5888b6-0ea2311dc53295-303464-2073600-
175869ba5899ac%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E
7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%
9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%2C%22%2
4latest_referrer%22%3A%22%22%7D%2C%22%24device_id%22%3A%22175869ba5888b6-
0ea2311dc53295-303464-2073600-175869ba5899ac%22%7D; remote_way=svn;
tz=Asia%2FShanghai;
Hm_lvt_24f17767262929947cc3631f99bfd274=1622637014,1622712683,1622863899,1623298
442; Hm_lpvt_24f17767262929947cc3631f99bfd274=1623298550; gitee_user=true;
gitee-sessionn=
M1Rhbk1QUUxQdWk1VEZVQ1BvZXYybG13ZUJFNGR1V0pSYTZyTllEa21pVHlBUE5QU2Qwdk44NXdEam
11T3FZYXFidGNYaFJxcTVDRE1xU05GUXN0ek1Uc08reXRTay9ueTV3OGl5bTdzVGJjU1lUbTJ4bTUvN1
l3RFl4N2hNQmI1SEZpdmVJWStETlJrdWtyU0lDckhvSGJHc3NEZDFWdHc5cjdHaGVtNThNcEVOeFZlaH
c0WVY5NGUzWjc2cjdOcCtSdVJ0VndzdVNxb3dHK1hPSDBDSFB6WlZDc3prUVZ2RVJyTnpTb1c4aFg1Mm
UxM1YvQTFkb1EwaU4zT3hJcmRrS3dxVFZJNXoxaVJwa1liMlplbWR5QXQxY0lvUDNic1hxN2o0WDg1Wk
E9LS10N0VIYXg4Vm5xdllHVzdxa0VlUEp3PT0%3D-
-2f6a24f8d33929fe88ed19d4dea495fbb40ebed6
```

        请求你中的 Cookie 字段也包含了一个 gitee-session-n 属性, 里面的值和刚才服务器返回的值相同. 后续只要访问 gitee 这个网站, 就会一直带着这个令牌, 直到令牌过期/下次重新登陆

&emsp;&emsp;**理解登录过程**：

![理解码云的登录过程（Cookie）](https://raw.githubusercontent.com/yimu-0412/image/master/image/%E7%90%86%E8%A7%A3%E7%A0%81%E4%BA%91%E7%9A%84%E7%99%BB%E5%BD%95%E8%BF%87%E7%A8%8B%EF%BC%88Cookie%EF%BC%89.png)


### 4.认识请求“正文”（body）
## 6、HTTP 响应详解
### 1. 认识"状态码"（status code）
### 2. 认识响应“报头”（header）
### 3. 认识响应“正文”（body）
### 4. 通过 form 表单构造 HTTP 请求
### 5. 通过 ajax 构造 HTTP 请求
### 6. 通过 java socket 构造 HTTP 请求
# 二、HTTPS
## 1、HTTPS 是什么
## 2、“加密”是什么
## 3、HTTPS 的工作过程
### 1. 引入对称加密
### 2. 引入非对称加密
### 3. 引入证书
### 4. 完整流程
## 4、总结
# 三、Tomcat
## 1、Tomcat 是什么
## 2、下载安装
## 3、目录结构
## 4、启动服务器
## 5、部署静态页面
### 1. 部署单个 HTML
### 2. 部署带有 CSS/JavaScript/图片的 HTML
### 3. 部署 HTML 到单独的目录中
### 4. 部署“博客系统”页面
### 5. 部署“博客系统”到云服务器






