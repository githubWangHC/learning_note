
静态网页内容一直不会变  
动态网站，用户可以在上面留言，时时更新网页。  
web服务器提供底层的socket程序  
开发服务器底端程序CGI、 Java 、asp.net  
嵌入式、路由器可以用CGI开发  
http:80端口（socket）一个端口只能被一个程序占用  

apache服务器配置
==================
1. 把压缩包解压到硬盘（最好不要有中文、空格、特殊符号），选择一个文件夹作为“网站根目录”，然后把apache的conf/httpd.conf中的DocumentRoot修改为“网站根目录”（注意：路径分割一定要用正斜线/，不要用\；要用英文的引号，不要用中文的引号；“网站根目录”最好不要有中文，否则httpd.conf要以Utf-8编码保存）。
2. 双击bin下的httpd.exe，启动服务器，如果屏幕一闪而过，则可能是出错了。启动命令行再运行httpd.exe，看报错信息。最好在dos命令行之下进入bin目录，然后运行httpd.exe，

可能的**问题**：“(OS 10048)通常每个套接字地址(协议/网络地址/端口)只允许使用一次”→有其他的Web服务器，把电脑上安装的其他的IIS、Apache、Tomcat等Web服务器卸载掉或者修改端口号。 http默认占用80端口，如鹏绿色版配置默认占用8080端口，麻烦地方在于每次网址都要带端口号。通过httpd.conf修改端口号。  
在“网站根目录（用记事本更改的httpd.conf中的根目录）”下放一个1.html，写点内容，浏览器访问http://127.0.0.1:80/1.html，如果能显示就没问题了。  
关闭命令行窗口apache服务器就关闭了。默认配置简单但是不安全，适用于开发，不适用于正式运行的网站。(*)正式运行以服务方式启动apche，但是开发时不要用“Windows 服务”方式启动，主要是权限导致的访问受限、调试等的麻烦。  
浏览器运行出错的一般形式  
ipcmg  
如果瞬间出现internal server error,一般说明是第一行出错  
如果是加载一会儿才出现internal server error，一般是程序有问题，被0除指针错误  
浏览器向apache服务器请求打开exe文件，然后apache服务器将运行的结果返回给浏览器，浏览器将结果显示出来。  
system("pause");//按任意键继续   
如果替换cgi时替换不了说明cgi程序apache正在运行着cgi  

获取用户请求参数  
```
?username=yzk&&password=123表示用户输入用户名，浏览器向服务器问用户名和密码是否匹配，服务器确定是否符合.有的地方两者之间只有一个&

    char * qs = getenv("QUERY_STRING");
    char username[256];			//username=yzk
    sscanf(qs,"username=%s",username);	//从qs中分析符合username=%s格式的字符串，把解析出的其中一部分放进username指针中。
    printf("Content-Type:text/html;charset=gbk\r\n\r\n");
    printf("<html><head></head><body>");
    if(strcmp(username,"admin")==0)	//比较两个字符串是否相等，如果相等则返回0
    {
        printf("登陆成功");
    }
    else
    {
        printf("登陆失败");
    }
    printf("</body></html>");

```
VS中变量声明只能放在代码块前边  
JavaScript的代码是写在浏览器中的，而CGI是写在服务器中的，不同的语言有不同的用处。  

**请求报文头request headers**
```
GET /1.html HTTP/1.1	//我想要1.html，我们之间通信用HTTP/1.1协议
Host: 127.0.0.1
Connection: keep-alive
Upgrade-Insecure-Requests: 1
User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36	//简称UA为浏览器的版本信息。通过这个信息可以读取浏览器是IE还是Firefox我支持什么样的浏览器版本
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8	//我支持什么样的格式
DNT: 1
Accept-Encoding: gzip, deflate, sdch			//我支持什么样的压缩格式
Accept-Language: zh-CN,zh;q=0.8,en-GB;q=0.6,en;q=0.4	//我支持什么样的语言
If-None-Match: "400000000cfa0-40-53769418226ab"
If-Modified-Since: Tue, 12 Jul 2016 05:08:13 GMT	//我现在的格林尼治时间是什么
```
***
**响应报文头response headers**
```
HTTP/1.1 200 OK						//文件找到了！“302”：Found暂时转移，用于重定向；“505”服务器错误
Date: Tue, 12 Jul 2016 13:08:51 GMT
Server: Apache/2.2.21 (Win32)
Last-Modified: Tue, 12 Jul 2016 13:08:38 GMT
ETag: "1500000000cfae-3d-5376ff7933438"
Accept-Ranges: bytes
Content-Length: 61
Keep-Alive: timeout=5, max=100
Connection: Keep-Alive
Content-Type: text/html
```

**响应报文体response**

```
				//报文头与报文体之间要有两行空行
HTTP/1.1 404 Not Found				//你需要的文件没有找到
Date: Tue, 12 Jul 2016 12:52:18 GMT		//当前时间是什么
Server: Apache/2.2.21 (Win32)			//我是一个什么服务器
Content-Length: 204
Keep-Alive: timeout=5, max=100
Connection: Keep-Alive
Content-Type: text/html; charset=iso-8859-1
```

Windows中写CGI程序一般都用"Content-Type:text/html;charset=gbk\r\n\r\n"   
服务器通过Content-Type告诉客户端相应的数据的类型，这样浏览器就根据返回数据的类型来进行不同的处理，如果是图片、文本类型就直接显示内容，如果用html类型就用浏览器显示内容，常用的Content-Type有：text/html.image/gif.image/jpeg.text/;lain.text/plain.text/javascript   
其中由于在Windows中的VS中编码故常用gbk编码故一般写"charset=gbk"   

```
printf("Location:http://baidu.com\r\n\r\n");	
printf("Content-Type:text/html;charset=gbk\r\n\r\n");	//以上两个都是报文头
printf("<html><head></head><body><font color='red'>如网<br/>用户名是：%s</font></body></html>");	//报文体
在代码前写上。一旦写上两个\r\n服务器就认为报文头结束了。
printf("Location:http://baidu.com\r\n");时服务器可以302重定向

printf("Location:http://baidu.com\r\n\r\n");
cgiHeaderLocation("http://baidu.com");
这两个函数都可以重定向，但是在重定向之前都不要写	？？？？？？？？？？？？？？？？
cgiHeaderContentType("text/html;charset=gbk")
或者
printf("Content-Type:text/html;charset=gbk\r\n\r\n");
```
***
```
#include <stdio.h>		//非studio.h
#include <string.h>
#include <stdlib.h>		//getenv函数所需的头文件
int main()
{
	char name[250]={0};	//要指定数组大小，否则在运行时会报错
	char * qs=getenv("QUERY_STRING");	//getenv("QUERY_STRING")
	sscanf(qs,"name=%s",name);	//sscanf
	if(strcmp(name,"abc",)==0)
	{
		printf("Location:1.html\r\n\r\n");
	}else
	{
		printf("Content-Type:text/html;charset=gbk\r\n\r\n"); //charset=gbk
		printf("<html><head></head><body>新华社报道：%s 和凤姐结婚了</body></html>",name);
	}
}
```
在解决方案下可以新建一个项目，并且点击右键设置“设定为启动项目”  

设置一个断点、下一个、打一个、插入  
因为CGI程序是apache运行起来的，运行完结束，来不及VS的调试器附加到CGI程序Web服务器不能以windows服务的形式运行  
````
#include<windows.h>
MessageBoxA(0,"ok","ok",0);当执行到这个代码时对话框关闭才能继续向下执行
```
这时可以点击调试下拉列表，在列表中选择附加到进程然后在程序左侧灰色地带点击添加断点，这些工作做好之后点击OK清除弹出来的对话框，然后程序就在断点处停下来了，这是将鼠标放在变量上就可以看到变量的值了，从而起到调试的目的。  

**CGI库**
不同的开发语言都有不同的开发框架	框架 Framework  
Java: Structs servlet JSP  
.Net: WebForm ASP.Net MVC  
PHP  
asp.net MVC  
CGIC只是简化开发而已，  
No Magic!没有魔法  
离了它，不用它，我也能自己造一个类似的东西来。  


使用开发框架
=================
打开vs新建win32控制台项目，新建.c项目，在解决方案下的项目上点击右键选择属性，对于其中的保存位置以及保持扩展名更改一下。  
.\   表示项目文件所在目录之下的目录。  
..\ 表示项目文件所在目录向上一级目录下的目录。  
..\..\表示项目文件所在目录向上二级目录之下的目录。  
..\..可以保存到和文件目录同一级别的目录中.也就是说本来该工程都包括在abc文件夹内的，但是此时调试出来的cgi文件和abc文件夹放在了一起。  
再右键--在文件资源管理器中打开文件夹，将cgi.c 与cgi.h两个文件夹复制到打开的文件夹中。  
再右键--添加--现有项目--选择这两个文件添加，  
```
cgiFormString("password",pwd,sizeof(pwd));函数可以从用户输入的”password=123“数据中分析出密码为123并且存放在pwd字符串中，字符串的长度是sizeof(pwd)。
strlen(pwd)==0

cgiFormString("password",pwd,sizeof(pwd))!=cgiFormSuccess)
上述两种方法均可以判断用户输入的数据是否为空。

在html中打印一段话的方法：
fprintf(cgiOut,"请填写姓名！<br/>");
printf("请填写姓名\n");

CGI文件中定义完变量之后要写
fprintf(cgiOut,"<html><head></head><body>");
或者
printf("<html><head></head><body>");

选择输出的方法
fprintf(cgiOut,strcmp(str_gender,"nan")==0?"帅哥":"美女");
```
**浏览器和服务器的交互**
表单提交  
注意id是给JS操作Dom用的，name才是提交给服务器用的。id不能重复，name可以重复（如果提交的元素中name相同，在检测时可能出错），重复的值都会白提交给服务器  
服务器端用cgiFormString等来根据表单项的name来获得提交的属性值。  
html表单<form>可以自动给服务器提交参数（get是通过URL，post是通过报文体），不用用户自己拼url。action制定把表单内容提交给谁。  
浏览器向服务器端提交数据，被提交数据的表单（input、select、textarea等）放到form中，form中通过action属性设定表单被提交给哪个页面，为了在服务器端去除表单项的值，必须在HTML中为表单元素设定name以及value属性，否者不能提交给服务器。  
当点击input type=”submit“的按钮的时候，会把它所在的form中的所有有name属性的”input/select/textarea/checkbox/radio/submit“的值提交给服务器。  
input type="text"或者input type="password"的值就是用户输入的值；  
textarea就是输入的文本  
select为选择项对应的option的value;  
checkbox类型必须设定name和value的值，不设name不会提交，不设value只是提交“name=”   
radio类型的同样设定name和value的值，不设定name变成多选，而且一旦选中就不能更改，不设定value无论选哪项提交都是sex=on，  
input type="submit"类型的如果设定name那么所点按钮的name和value也会提交给服务器。  

案例：通过表单和服务器进行交互
==============================
html中的代码：
```

    <form action="CGIC1.cgi">
        <br/>										//文本输入框
        用户名：<input type="text" name="name" value="请输入用户名"/>		//name=enter+username//类型是text
        密  码：<input type="password" name="password" value="请输入密码"/>	//password=123
        <br/>
        <textarea name="textareaword">请在此填写个人简介</textarea>		//textareaword=textarea+word
        <br/>
        民族
        <select name="minzu">								//下拉列表
        <option value="han" selected>汉族</option>				//minzu=han
        <option value="hui">回族</option>
        </select>
        <br/>										//复选框
        <br/>你喜欢的水果是<br/>						//checkbox1=apple&checkbox2=pear&checkbox3=banana
        <input type="checkbox" name="checkbox1" value="apple" id="cb1"/><label for="cb1">苹果</label>
        <input type="checkbox" name="checkbox2" value="pear" id="cb2"/><label for="cb2">梨子</label>
        <input type="checkbox" name="checkbox3" value="banana" id="cb3"/><label for="cb3">香蕉</label>
        <br/>性别<br/>									//单选框
        <input type="radio" name="sex" value="nan" id="nan"/><label for="nan">男生</label>	//sex=nan
        <input type="radio" name="sex" value="nv" id="nv"/><label for="nv">女生</label>
        <br/><br/>									//按钮
        <input type="submit" name="submitting" value="login"/>			//submit=login
        <input type="submit" name="submitting" value="loginout"/>
    </form>
```
***
		#include "cgic.h"

		name=enter+username&password=123&textareaword=textarea+word&minzu=han&checkbox1=apple&checkbox2=pear&checkbox3=banana&sex=nan&submit=login

判断用户是否点击了name="submitting",value="="的按钮的方法  
```
方法1：	if(cgiFormString("submitting",submit,sizeof(submit))!=cgiFormSuccess)  
方法2：		cgiFormString("submitting",submit,sizeof(submit));  
		if(strcmp(submit,"=")!=0)    
方法3：	if(cgiFormSubmitClicked("submitting")==cigFormSuccess)	//点击时提交的报文头就有"submitting="，浏览器就可以识别了，因此必须有name
方法3潜在的bug,当提交的表单中有另外一个叫submitting的按钮，或者文本框时即使没有点击submitting，仍然有submitting
被提交，所以在给表单命名时要注意，名字的唯一
```
如果只有一项，此时判断checkbox是否被选中：cgiFormCheckboxSingle(char*name)；

对于文本输入框、有name但是value值用户定的就只能用第一种方法。
下拉列表、复选框、单选框既可以设置name又可以设置value的表单项都有上述第一、二种方法。
同时对于复选框、单选框、按钮如果设置了name、即使没有value系统自动会分配一个"on"作为value的值，也就是说提交时会name=on，对于下拉列表如果设置了name但没设置value，那么提交时会name=标签；

html中注释多行用
<!--
-->
3.input type='radio'
4.select <select name='addr'><option value='hebei'>河北</option>也可以用cgiFormString
fprintf(cgiOut,"<html><head></head><body>你浏览器的useragent是：%s<br/>您的IP地址是：%s<br/>你的CGI地址是：%s<br/>你的Query_string是：%s</body></html>"
	,cgiUserAgent,cgiRemoteAddr,cgiScriptName,cgiQueryString);

#include<stdio.h>
#include"cgic.h"
#include<string.h>

inline int cgiMain()
{
	char str_username[20];
	char str_password[20];
	char str_gender[10];
	char str_ti1[10];
	char str_ti2[10];
	cgiHeaderContentType("text/html;charset=gbk");
	cgiFormString("username",str_username,sizeof(str_username));
	cgiFormString("password",str_password,sizeof(str_password));
	cgiFormString("gender",str_gender,sizeof(str_gender));
	cgiFormString("ti1",str_ti1,sizeof(str_ti1));
	if(strlen(str_username)==0)	
	{
		printf("请输入姓名");
	}else if(cgiFormString("password",str_password,sizeof(str_password))!=cgiFormSuccess)
	{				//以上两种均可以判断获得的数值是否为空，但是第二种好像没有获得数值，要单独写出才能获得数值。
		printf("请输入考生号");
	}else if(strlen(str_gender)==0)
	{
		printf("请选择性别");
	}
	if(strcmp(str_ti1,"a")!=0)
	{
		printf("第一题答错了");
	}else
	{
		printf("恭喜你，第一题答对了");
	}
	return 0;
}

get提交方式表单请求头中（require head）有提交信息、通过URL传递表单值，浏览器地址栏中可以看到提交信息、因此最大传递长度是有限的，请求内容随着网址走，把提交后的网址发给被人后别人可以看到一样的结果。在浏览器地址栏点击enter是get方式
post提交方式请求的数据放在请求正文中的。type="file" type=“password” <textarea>
method="post";

jsp struts velocity
.net aspx razor
MVC是解决字符串拼接之间的问题。
=====================================================
.NET发展趋势：企业信息系统行业
WinForm下降；
ASP.Net WebForm下降，ASP.Net MVC上升
.NET开发效率高，新兴互联网公司使用.NET在上升
拥抱开源、拥抱云计算、拥抱移动互联网

===============================
用模板引擎和html进行交互=======
===============================

在p1.htm中写下如下代码
第一个传入的值：<TMPL_VAR name="test1"/>	//唯一可变的就是引号里边的test1；
第二个传入的值：<TMPL_VAR name="age1"/>
如果外部有引号则写成如下代码
<input type="text" name="number1" value="<TMPL_VAR name='number1'/>"/>

在cgi中写下
#include<stdio.h>
#include"ctemplate.h"
#include"cgic.h"
int cgiMain()
{
TMPL_varlist * varlist1=0;	//定义一个空的参数列表	//接收的都是字符类型。	//任何形式的声明都是TMPL_varlist*
varlist1 = TMPL_add_var(varlist1,"test1","特斯拉",0);	//以0结尾表示可变参数；text1与age必须与htm文件中的一样.0很关键，不然报错，血淋淋的教训20160716.
varlist1 = TMPL_add_var(varlist1,"age","20",0);	//必须写字符串（故20必须引号起来）.被传入的字符可以是变量 char * string;string="我很高兴";
						//varlist1 = TMPL_add_var(varlist1,"age",string,0);
TMPL_write("p1.htm",0,0,varlist1,cgiOut,cgiOut);//最后用TMPL_write函数将varlist1写入HTML文件.不只是写入参数，还可以调用html文件。
}

=====================================================
结构体同样可以这样写入
在cgi文件中
TMPL_varlist* varlist_all=0;
TMPL_loop* loopUsers=0;		//定义一个包括所有结构体的变量
loopUsers = TMPL_add_varlist(loopUsers,TMPL_add_var(0,"name","rupeng","age","8",0));
				//用TMPL_add_varlist();函数写入，与TMPL_add_var()函数的区别是最后没有0;
				//用TMPL_add_var(0,"name","rupeng","age","8",0)将一个结构体数据写入一个TMPL_varlist变量，只不过少了一个赋值
loopUsers = TMPL_add_varlist(loopUsers,TMPL_add_var(0,"name","taobao","age","10"));
loopUsers = TMPL_add_varlist(loopUsers,TMPL_add_var(0,"name","jd","age","15"));	
				//填的值必须是字符，虽然15是整数但还是要引号引起来的。因此如果要用一个变量代替则这个变量要是字符型的变量，如果是整数变量则要用itoa将整数类型的变量替换为整数。
varlist_all = TMPL_add_loop(varlist_all,"user",loopUsers);	//用这一个函数将包含所有结构体的变量写入varlist_all,标识为user
varlist_all = TMPL_add_var(varlist1,"Title","测试loop",0);	//此时还可以再变态一点，再在varlist_all里边写一个非结构体的变量，标识为title。
cgiHeaderContentType("text/html;charset=gbk");
TMPL_write("loop.htm",0,0,varlist_all,cgiOut,cgiOut);		//终于装满了，可以写入html了
=======================================================
html中的文件

    <table>
        <TMPL_VAR name="Title"/>	//由于Title在最外层，所以可以直接拿来
        <tm><td>网站</td><td>年龄</td></tm>
        <TMPL_LOOP name="user">		//先打开结构体变量的包装，里边有一大堆结构体
    <tr><td><TMPL_VAR name="name"/></td><td><TMPL_VAR name="age"/></td></tr> 	//不急，都有，一个一个发。
        </TMPL_LOOP>
    </table>

TMPL_add_loop		将打包好的结构体大包添加到上一层总包中
TMPL_add_var		将一个普通变量添加到普通包中
TMPL_add_varlist	将一个个单个结构体添加到结构体大包中；
这三个函数左边都需要有变量来接收，否者同样是血淋淋的教训！20160718

通过form中的action动作以及TMPL_write();函数可以将htm文件和cgi文件进行交互，具体原理是用户访问cgi文件，然后write函数调用html文件，html中的action又转向cgi文件，如此往复。案例在E:\C_lang\www\c_html\calculate_project\calculate_project.sln
如果需要只是显示一个页面的话就可以省略一个参数。TMPL_write("loop.htm",0,0,0,cgiOut,cgiOut);
如果不这样交互也可以在cgi文件中直接用fprintf();函数将htm文件的代码输出出去，浏览器编译之后显示所需结果。

cgi准备数据传出数据，html接收数据决定将数据怎么展示。各司其职。
MVC model view controller
==================================================================

========================================================
当int cgiMain()函数无法解析时应该看看是否把.c文件错误建成.cpp文件了
计算机休眠之后apache会罢工
Visual Studio 9.0　和　Visual Studio 2010调整缩进选中相应代码，然后Ctrl+K, 再Ctrl+F
============================================================================================================
C语言链接MYSQL
===========================
环境配置
1、在项目属性中【VC++目录】→“包含目录”，选择mysql的include文件夹；“库目录”选择mysql的lib文件夹；【链接器】→【输入】的“附加依赖项”增加“libmysql.lib”。

2、C代码里include头文件mysql.h，并且在之前要include头文件winsock.h。

3、MYSQL *pConn = mysql_init(0);

4、编译运行，会报错找不到“libmysql.dll”，把mysql的libmysql.dll复制到exe的目录下。

附录（没用过gcc的同学不用看）：如果有同学使用过gcc进行C代码编译的，可能想了解如果通过gcc编译使用mysql的程序，使用类似如下的命令行即可：gcc main.c -o test.exe -ID:\greeninstall\mysql\include -LD:\greeninstall\mysql\lib -llibmysql
为什么这么设置，因为gcc中使用-I设置头文件的位置，-L设置lib文件的位置，-l设置要链接的库名称，要明白原理，不能只记我这里给出的结论，否则遇到问题就无法自己解决了。
明白了如何通过gcc命令行编译，那么也就知道如何在EditPlus+gcc环境中配置编译设置了：$(FileName) -o $(FileNameNoExt).exe -ID:\greeninstall\mysql\include -LD:\greeninstall\mysql\lib -llibmysql

goto一般不推荐，但是在错误处理的时候，很好用。
在MYSQL中选中一行代码可以仅仅执行这一行。

	MYSQL  *mysql = mysql_init(0);	//定义一个MYSQL类型的指针mysql
	if(!mysql_real_connect(mysql,"localhost","root","root","study1",0,0,0))
		//通过mysql这个管道，传送用户名、密码、要连接的表单
	{
		printf("链接数据出错：%s",mysql_error(mysql));
		goto exit;
	}else
	{
		printf("链接数据库成功\n");
	}
	if(mysql_query(mysql,"set names gbk"))	//如果里边有汉字则设置gbk编码
	{
		printf("设定链接编码失败%s",mysql_error(mysql));
		goto exit;
	}
	if(mysql_query(mysql,"insert into t_study(username,password) value('我','123')"))
		//引号里边不止可以用insert、还可以用select、update、delete等
	{
		printf("插入失败，%s",mysql_error(mysql));
		goto exit;
	}
	printf("insert成功\n");
exit:							//设置一个标签，名字任意
	mysql_close(mysql);
	getchar();					//防止命令行

用sprintf函数动态拼接插入
int i;
char str[200]={0};
for(i=0;i<10;i++)
{
sprintf(str,"insert into t_study(username,password) values('%d','%d')",i,i);//注意格式，和C语言差别很大。没有赋值，是values，注意要用单引号引起来，因为是字符
if(mysql_query(mysql,str))
{
printf("插入失败，%s",mysql_error(mysql));
goto exit;
}
}


将数据库中的数据打印出来mysql_store_resul、mysql_store_result两个函数是关键
	if(mysql_query(mysql,"select * from t_study"))	//在有多行时，这里不要用*，而是用写出列队名字
	{
		goto exit;	//链接错误，退出关闭连接
	}else
	{
		MYSQL_RES * result = mysql_store_result(mysql);	//离线存在电脑内存中、查询完成之后就可以断开数据库连接、少结果用、
		MYSQL_ROW * mysql_row;	//定义一个列//以上两个都是有星号的.非也，row可以没有
		while(mysql_row=mysql_fetch_row(result))	//赋值的结果可以用来判断
		{
		printf("ID=%s,username=%s,password=%s\n",mysql_row[0],mysql_row[1],mysql_row[2]);
		}
	}
mysql_free_result(result);

========================================================================================================================
将查询语句与非查询语句封装起来用
========================================================================================================================
1.非查询语句（增删改查）
========================================================================================================================
MYSQL MYSQL_QUERY(char* str_mysql)
{
	MYSQL* mysql_tube = mysql_init(0);
	if(!mysql_real_connect(mysql_tube,"localhost","root","root","study1",0,0,0))
	{
		goto error;
	}else
	{
		printf("链接成功\n");
	}
	if(mysql_query(mysql_tube,"set names gbk"))
	{
		goto error;
	}else
	{
		printf("设置编码成功\n");
	}
	if(mysql_query(mysql_tube,str_mysql))
	{
		goto error;
	}else
	{
		printf("数据库操作成功\n");
		goto exist;
	}
error: printf("数据库出错 %s",mysql_error(mysql_tube));
exist: mysql_close(mysql_tube);
	getchar();
}
int main()
{
	char* str_mysql;
	str_mysql="insert into t_study(username,password) value('hacker','ackdole')";
	MYSQL_QUERY(str_mysql);
}
======================================================================================================================
2.查询语句（有数据的输出）
======================================================================================================================
MYSQL MYSQL_NON_QUERY(char* str_mysql)
{
	MYSQL * mysql_tube = mysql_init(0);
	if(!mysql_real_connect(mysql_tube,"localhost","root","root","study1",0,0,0))
	{
		goto error;
	}else
	{
		printf("数据库链接成功\n");
	}
	if(mysql_query(mysql_tube,"set names gbk"))
	{
		goto error;
	}else
	{
		printf("数据库设置编码成功\n");
	}
	if(mysql_query(mysql_tube,str_mysql))
	{
		goto error;
	}else
	{
		{
			MYSQL_RES * data_store =mysql_store_result(mysql_tube);
			MYSQL_ROW* data_row;
			while(data_row=mysql_fetch_row(data_store))
			{
				printf("username=%s	password=%s\n",data_row[1],data_row[2]);
			}
			goto exit;
		}
	}
error: printf("数据库出错\n",mysql_error(mysql_tube));
exit: mysql_close(mysql_tube);
	getchar();
}
int main()
{
	char* str_non_query="select * from t_customers";
	MYSQL_NON_QUERY(str_non_query);
	
}

如果要多次输出时可以定义一个输出函数

void printError(char* errorMessage)
{
printError("这种带引号的字符串也可以直接接收")；
}

MYSQL_ROW* data_row;定义时可以不加星号。

cgi、html、mysql之间的编码要一致，防止出现乱码的情况：
新建mysql数据库的时候一定要选择gbk编码
连接时要mysql_query(mysql_tube,"set names gbk")
html要cgiHeaderContentType("text/html;char set=gbk");	//是charset

========================================================================
输入时对特殊符号的处理，对输入的字符串进行16进制转换mysql_hex_string===
========================================================================
cgiHeaderContentType("text/html;charset=gbk");
cgiFormString("username_input",username_get,sizeof(username_get));
cgiFormString("password_input",password_get,sizeof(password_get));
mysql_hex_string(hex_username,username_get,strlen(username_get));
mysql_hex_string(hex_password,password_get,strlen(password_get));
TMPL_add_var(tube,"username_input","username_get","password_input","password_get",0);
==================================================
判断登录是否成功
========================
char sql[1024]={0};
sprintf(sql,"select count(*) from T_Users where UserName='%s' and Password='%s'",userName,password);
{
MYSQL_RES* res = executeQuery(sql);
MYSQL_ROW row = mysql_fetch_row(res);
char *count = row[0];
if(strcmp(count,"0")==0)
{
printLoginError("用户名或者密码错误");
}
else
{
printLoginError("登陆成功")
}
}

=====================
=====SQL注入漏洞=====
=====================

随便输入一个用户名密码输入《1' or '1'='1》
拼接之后的SQL语句是
select count(*) from T_Users where UserName='abc' and Password='1' or '1'='1'
由于or运算在最后所以最后一直为真。
解决方法之一是进行16进制转换转换之后value(0xabcd,0xdfkjdf)，由于是以0x开始所以就消除了风险
===========================================================================
int age;
cgiFormInteger("age_html",&age,998);
从htm表单中age_html的变量中取得整数，放到地址&age中，如果没有取得则把998这个参数放入&age中
==============================================================================
与此类似
double height;
cigFormDouble("height_html",&height,0);
同上，如果要用TMPL_add_var();则也需要将double类型变量转换为char
可以用sprintf();函数。
sprintf(char_height,"%f",height);	//可以在%f中选择保留小数位。

===================================================================================
获取非常长的请求参数的方法	博客
===============================================================================
int length_need;
char* char_cgi;
//cgiFormStringSpaceNeeded(char* html_name,int* length);
cgiFormStringSpaceNeeded("string_html",&length);	//查看一下来自html的名字为string_html字符串的长度
char_cgi = (char*)malloc(len+1);
cigformString("string_html",char_cgi,length);	//把名字为string_html的字符串放在char_cgi字符串中
free(char_cgi);
==============================================================
CGI中处理文件上传
==============================================================
html文件中代码==
================
    <form action="uplood_project.cgi" method="post" enctype="multipart/form-data">
        选择要上传的文件：<input type="file" name="file_name_html"/>
        <input type="submit" name="submit_html" value="uplood">
        <TMPL_VAR name="message_html"/>
    </form>

=====================
cgi文件中代码========
=====================

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include"cgic.h"
#include"ctemplate.h"

int messageprintf(char* message_input);
int cgiMain()
{
	char upload_file_name[200]={0};
	char file_ext[10]={0};
	int file_size;
	if(cgiFormSubmitClicked("submit_html")!=cgiFormSuccess)
	{
		messageprintf("");
	}else if(cgiFormFileName("file_name_html",upload_file_name,sizeof(upload_file_name))!=cgiFormSuccess)
		{
			messageprintf("请首先选择文件<br/>");
		}else{		
			_splitpath(upload_file_name,NULL,NULL,NULL,file_ext);
			if((stricmp(file_ext,".jpg")!=0)&&(stricmp(file_ext,".zip"))&&(stricmp(file_ext,".png")))
			{
				messageprintf("仅支持.jpg、.zip、.png三种文件格式<br/>");
			}else
			{
				cgiFormFileSize("file_name_html",&file_size);
				if(file_size>2024*1024*1024)
				{
					messageprintf("最大允许上传1M的文件<br/>");
					return;
				}else
				{
					{
						char buffer[1024];	//在缓冲区一点一点取文件
						int lenRead;		//实际读取字节数
						cgiFilePtr upload_file;
						FILE* targetFile=fopen(upload_file_name,"wb");	//打开本地的接受文件
						cgiFormFileOpen("file_name_html",&upload_file);
						while(cgiFormFileRead(upload_file,buffer,sizeof(buffer),&lenRead)==cgiFormSuccess)
						{
							fwrite(buffer,lenRead,1,targetFile);
						}
						fclose(targetFile);	
					}
					messageprintf("文件上传成功<br/>");
				}

			}
		}
	return 0;
}
	
int messageprintf(char* message_input)
{
	TMPL_varlist* message=0;
	cgiHeaderContentType("text/html;charset=gbk");
	message=TMPL_add_var(message,"message_html",message_input,0);
	TMPL_write("uplood.htm",0,0,message,cgiOut,cgiOut);
}

===================================================================
MVC是一种开发模式，和具体的技术无关。model varlist\view(html)\controller(cgi)
html放到单独的模板文件中，不在代码中拼接html，html编辑更容易、代码更清晰
用户访问cgi，不直接访问html代码；
cgi生成数据，给到模板引擎显示最终html页面，

//
有if要写else，少加很多班
<a herf="studentMgr.cgi?mod=teacher&action="addnew"">	//使用时不用cgi文件来判断用户电极什么了，很方便。只要设置好跳转后的页面即可。


<input type="hidden" name="mode" value="">不会显示到html，但是会提交会

char* string;
char string[];
两种存放字符变量的使用 在gcc编译环境中char*不存在初始化的问题，可以尽情地用char*。在visual studio中如果用sprintf函数将字符串拼接起来，就必须要能够第二种。如过写代码时就可以确地地赋值第一种方法更节省内存，但第二种方法也是可以的，相当于把初始化当成赋值来用。对第二种方法字符串变量赋值是一个灾难，所以赋值时都用第一种


http://127.0.0.1/cgi_htm_mysql.cgi?mode_html=teacher&action_html=query_modify&id_htm=112&name_htm=hacker&age_htm=99
http://127.0.0.1/cgi_htm_mysql.cgi?mode_html=teacher&action_html=query_modify&id_htm=112&name_htm=hacker&age_htm=99&name_html_input=hacker1&age_html_input=99&change_message_html=%CC%E1%BD%BB

http://127.0.0.1/cgi_htm_mysql.cgi?mode_html=teacher&action_html=query_modify&id_htm=112&name_htm=hacker1&age_htm=99&change_message_html=%CC%E1%BD%BB
=============================================================================================================================================================
做项目心得：
增删改查都要用连接数据库，但四者的目的不同，返回值也不同。增删只是在数据库操作出现失误时用返回错误信息，而改查则则需要返回数据库中的数据。详细说来而改只用返回一条数据，
查则需要返回多条数据。因此，可以说即使在不考虑错误信息返回的情况下这个查询数据库的操作也需要分三种情况进行。增删很好办，但改查需要将数据库的数据返回，如果不知道怎么将查询的数值返main函数
就需要在查询函数之内进行数据的输出，大大加重了数据库查询函数的长度，弱化了main函数的作用。最开始想把管道返回，但是不懂MYSQL内部怎样定义管道的，即使接收了管道也
不能用。退一步，进行分类，在该查时把MYSQL_RES类型的包含所有数据的变量返回。最开始用的是在括号内传，当然也可以用return函数来传，收到return函数传回MYSQL_RES类型变量的启发，想能否把管道用
return函数传回来呢？结果真的可以传回来，这样增删改查就可以不用分情况了，只用把管道搭建好，把管道传回来就行了。万能的return。哈里路亚。当然可以传回MYSQL_ROW变量，
MYSQL* MYSQL_QUERY(char* query_string,char* error_message_return)
{
	MYSQL* tube = mysql_init(0);
	char error_message[200]={0};
	TMPL_loop* qurey_result_loop=0;
	if(!(mysql_real_connect(tube,"localhost","root","root","study1",0,0,0)))
	{
		sprintf(error_message,"数据库链接出错<br/> %s",mysql_error(tube));
		strcpy(error_message_return,error_message);
	}else if(mysql_query(tube,"set names gbk"))
	{
		sprintf(error_message,"设置编码出错<br/>%s",mysql_error(tube));
		strcpy(error_message_return,error_message);
	}else if(mysql_query(tube,query_string))
	{
		sprintf(error_message,"数据库查询失败<br/>%s",mysql_error(tube));
		strcpy(error_message_return,error_message);
	}else 
	{
		return tube;
	}
}

int cgimain()
{
	MYSQL*tube =MYSQL_QUERY(query_msg,error_message);
}

===========================================================================================================
MYSQL_RES* MYSQL_QUERY_text1(int printf_1_not_0,char* query_string,char* error_message_return)
{
	MYSQL* tube = mysql_init(0);
	char error_message[200]={0};
	TMPL_loop* qurey_result_loop=0;
	if(!(mysql_real_connect(tube,"localhost","root","root","study1",0,0,0)))
	{
		sprintf(error_message,"数据库链接出错<br/> %s",mysql_error(tube));
		strcpy(error_message_return,error_message);
		goto exit;
	}else if(mysql_query(tube,"set names gbk"))
	{
		sprintf(error_message,"设置编码出错<br/>%s",mysql_error(tube));
		strcpy(error_message_return,error_message);
		goto exit;
	}else if(mysql_query(tube,query_string))
	{
		sprintf(error_message,"数据库查询失败<br/>%s",mysql_error(tube));
		strcpy(error_message_return,error_message);
		goto exit;
	}else 
	{
		if(printf_1_not_0)
		{
			MYSQL_RES* mysql_all_result=mysql_store_result(tube);			
			return mysql_all_result;
		}else
		{
			return 0;
			goto exit;
		}
	}
exit: mysql_close(tube);
}

int cgiMain()
{
	MYSQL_RES* mysql_all_result=MYSQL_QUERY_text(printf_1_not_0,query_msg,error_message);
}
====================================================================================================================
关于初始化有一个用visual status的惨痛的经历:上面合法，下面非法，没有初始化。
=====================================================================================
TMPL_varlist* message_cgi_insert_result=0;
char mysql_query_return_message[1024]={0};
char* mysql_query_msg = "select qq.id,qq.name,gd.gender,ps.political_status.....";

MYSQL*tube_query = MYSQL_QUERY(mysql_query_msg,mysql_query_return_message);
=========================================================================================
int id_cgi;
char mysql_change_msg[200]={0};
char mysql_return_change_message[200]={0};
cgiFormInteger("id_htm",&id_cgi,999);
sprintf(mysql_change_msg,"select * from t_students where id=%d",id_cgi);

MYSQL*tube_modify = MYSQL_QUERY(mysql_change_msg,mysql_return_change_message);	//陷阱，自然而然地写错。
改正如下：
==================================================================================================================
int id_cgi;
char mysql_change_msg[200]={0};
char mysql_return_change_message[200]={0};
MYSQL*tube_modify = mysql_init(0);
cgiFormInteger("id_htm",&id_cgi,999);
sprintf(mysql_change_msg,"select * from t_students where id=%d",id_cgi);
tube_modify = MYSQL_QUERY(mysql_change_msg,mysql_return_change_message);
===================================================================================================
大型网站开发
不同的数据要放在不同的服务器上这样浏览器可以对同一个服务器时访次数较少。而不同的服务器如果在不同的域名之内则cooki就不用返回
从而减少cooki的流量。
页面静态化：将内容不变的内容用shtml技术做页面静态化（在服务器上生成静态页面，服务器不再向数据库进行访问）。
静态CDN同步技术，有一个中心服务器，当不同地区的服务器访问一个页面时中心服务器告诉不同的浏览器浏览哪个最近的服务器。
如果请求是动态请求那么只能访问中心服务器，所以用静态页面，也可以有效利用CDN技术。

js/css压缩，将写html中的变量等换成简单的字母，删除换行等。

css sprint,所有图片拼成一个大图，一次请求获得大图，然后在不同的地方显示图片中的一个部分。

大型网站服务器架构

反向代理服务器是用户和web之间的连接中转站，用户先访问反向代理服务器，代理再把请求给web服务器，这样即使用户攻击也是代理出问题，web服务器不会损失
另外，如果用户网速很慢，在用户与web服务器之间链接时，用户的访问时间就会变长，减少web服务器的并发连接数，从而使得web服务器不能处理新的请求。通过代理，
web服务器可以将请求结果迅速给代理，代理再慢慢给用户，这样web服务器就可以一直高效处理请求了。

分离：图片等文件、网页、放在不同服务器做到文件服务器和web服务器分离。同一件事都不同细节不同服务器完成，上传下载不同方法处理，前者IE上传给上传服务器，然后上传给云服务器，后者IE直接从云服务器下载
耗资源的处理（图片加水印、视频处理）和web服务器分离
数据库读写分离；
把不同的表和内容放在不同的硬盘，这样可以同时访问多个硬盘，相当于同时对多个表格进行处理，反之如果放在同一个硬盘则不能同时读写。

int/int=int
sprintf("文件大小是%01f KB",sizeof(file_size)/1024.0);

只要超链接到文件所在地就可以下载

==========================================================================================================================
cooki实现记住密码
========================
cgi程序中写下：
cgiHeaderCookieSetString("testname","baidu.com",20,"","");//将cooki名字为testname，内容为baidu.com的cooki
在浏览器的报文头中有：
Set-Cookie:testname=baidu.com; domain=; expires=Wed, 27-Jul-2016 13:26:49 GMT; path=
这样浏览器就找一个地方将这个cooki保存在本地起来，以后浏览器每次向服务器发生请求都带着这个cooki

cgiCookieString("textname",value,sizeof(value));
fprintf(cgiOut,"ok,%s",value);				//将cooki读取出来。

此浏览器的报文头有：Cookie:testname=baidu.com
cooki不能存储太大信息，不能存储如果用户篡改对系统造成影响到信息，不能把不能丢的信息不能放cooki。cooki不能跨浏览器使用的。在隐私模式下cooki不能和非隐私模型共享
ie浏览器中的设置cooki必须加域名，chrom不需要：cgiHeaderCookieSetString("testname","baidu.com",20,"","127.0.0.1")

session会话，服务器端的cooki。个人的机密信息数据存储在服务器端，当用户正确访问之后服务器给浏览器返回一个无序的加密的sessionID，浏览器把这个sessionID作为Cooki存放在电脑的某个地方。这个sessionID就是浏览器与服务器之间交互的凭证，下次浏览器访问服务器时把这个sessionID同样返回给服务器，服务器用这个凭证就可以判断用户的个人信息。
；一个用户访问者对应一块存储区域；可以读写session中的数据；sessionsession要设定有效期，每次访问服务器做一次延期，长期不访问则销毁
很多网站登录后的个人信息就是session。这样个人信息就可以不被用户修改。
同一个电脑上的不同浏览器之间的session不能相互影响，不同用户的session也不能相互影响。

Guid：基于网卡MAC地址、系统运行时间（精确到系统CPU时钟的震荡周期），这样不同电脑产生的Guid不一样，相同电脑不同时刻产生的guid不一样。
============================================================================================
#include <objbase.h>
void createGuid(char* guidStr)
{
GUID guid={0};
CoCreateGuid(&guid);
sprintf(guidStr, "{%08X-%04X-%04x-%02X%02X-%02X%02X%02X%02X%02X%02X}", 
guid.Data1, 
guid.Data2, 
guid.Data3, 
guid.Data4[0], 
guid.Data4[1], 
guid.Data4[2], 
guid.Data4[3], 
guid.Data4[4], 
guid.Data4[5], 
guid.Data4[6], 
guid.Data4[7]);
}

================================================================================================
即使声明了一个全局变量，则在程序结束的时候仍然会被释放掉。如过想下次执行程序时还用某个变量的值那么则需要用跳转，将两次程序连接在一起。或者使用cooki


atoi(one_row_result[0]);可以将MYSQL_ROW类型存有数值的的结果返回。

当不知道mysql查询语句哪个地方有错时可以使用换行，看看出错地方在第几行

惨痛的教训：mysql_string拼接帝语句放置的变量要足够长，否则出现bug，而且不是第一次了。bug提示如下： stack around the variable '变量名'
当程序执行到return时直接跳出这个函数，其他的代码都不会再执行了。

局部变量与全局变量的区别：
#include <stdio.h>
int a =0;
int main(int argc, char *argv[])
{
	int a=1;
	if(1)
	{
		int a=2;
		printf("a=%d\n",a);	//输出结果为a=2
	}
}
========================================================================================
#include <stdio.h>
int a =0;
int main(int argc, char *argv[])
{
	int a=1;
	if(1)
	{
		printf("a=%d\n",a);	//输出结果为a=1
	}
}
=========================================================================================
说明全局定义的变量和局部定义的变量都存在的时候，执行最靠近执行语句上边的值。就近原则。
========================================================================================
但是在每一个局部只能定义一个变量，否则就是重复定义。当然如果是赋值则在一个局部可以多次赋值：
#include <stdio.h>
int a =0;
int main(int argc, char *argv[])
{
	int a=1;
	if(1)
	{
		int a=2;
		//int a=3;	//编译不通过，重复定义error: redefinition of 'a'
		printf("a=%d\n",a);	//输出结果为a=2
	}
}
=============================================================================================
================================================================================================
#include <stdio.h>
int a =0;
int main(int argc, char *argv[])
{
	int a=1;
	if(1)
	{
		int a=2;
		printf("a=%d\n",a);	
	}
	if (1)
	{
		printf("a=%d\n",a);	
	}
}	//输出结果为a=2;a=1	//这是不好的书写习惯，VB在这种情况喜爱第一个if里边点printf里边的a只有a=2中的
a会加阴影，而a=1不会。a=1应该放在第二个if里边。只有自己阴影的都需要删除
============================================================================================
#include <stdio.h>
int a =0;
int main(int argc, char *argv[])
{
	int a=1;
	if(1)
	{
		a=2;
		printf("a=%d\n",a);	
	}
	if (1)
	{
		printf("a=%d\n",a);	
	}
}	//输出结果为a=2;a=2	这两段代码说明同一个变量如果只定义了一次，那么在程序执行过程中其值
一直会变，如果在某个局部重新进行了定义，那么其就会对其他部分不再影响。
=============================================================================================
============================================================================================
在有goto时就麻烦多了：
======================================================================================
#include <stdio.h>
int a =0;
int main(int argc, char *argv[])
{
	int a=1;
	if(1)
	{
		goto label;
	}
	if(0)
	{
		if(1)
		{
		label:printf("a=%d\n",a);	//输出结果为a=1
		}
	}
	return 0;
}
===============================================================================================
#include <stdio.h>
int a =0;
int main(int argc, char *argv[])
{
	int a=1;
	if(1)
	{
		int a=2;
		goto label;
	}
	if(0)
	{
		if(1)
		{
		label:printf("a=%d\n",a);	//输出结果为a=1
		}
	}
	return 0;
}
==============================================================================================
下面两个执行结果输出结果都是a=2，
==============================================================================================
#include <stdio.h>
int a =0;
int main(int argc, char *argv[])
{
	int a=1;
	if(1)
	{
		a=2;	//对a重新进行赋值了，所以输出为2这个代码的意义是可以利用goto传递变量的值
			//cgiHeaderLocation同样可以实现跳代码的功能，但是跳转过去后没有携带变量的值
		goto label;
	}
	if(0)
	{
		if(1)
		{
		label:printf("a=%d\n",a);	//输出结果为a=2
		}
	}
	return 0;
}
cgiHeaderLocation("cgi_htm_mysql.cgi?mode_html=student&action_html=query_select");
此外这种重定向后执行过程中还是要检查mode_html和action_html的值是否是student和query_select的。
================================================================================================
#include <stdio.h>
int a =0;
int main(int argc, char *argv[])
{
	int a=1;
	if(1)
	{
		a=2;
		goto label;
	}
	if(1)
	{
		a=3;	//这个和下个语句不能初始化
		if(1)
		{
		a=5;	//因为这些语句没有执行，所以没有被赋值。
		label:printf("a=%d\n",a);	//输出结果为a=2，这个代码的意义是在执行goto语句时是一个结果，不执行goto语句是另外一个结果，
		}
	}
	return 0;
}
===================================================================================================


====================================================================================================
一个函数执行完成之后他所定义的或者初始化的变量都将释放，除了return的数值，和在括号里返回的值之外。
mysql_string error_msg等多次使用的变量可以一次定义多次使用

与(&)和短路与(&&)的区别以及或(|)和短路或(||)的要想全部执行则使用 & | 结果好惊人，此代码在gcc编译器下实现
&为按位与作用如下：
1，可以用来迅速清零，a&b，将a迅速清零（将a中对应位处为1的位置在b中设置成0）
2，可以取出特定的位数，取出a的后八位（a&0xff）
3，判断奇数偶数，a&1。
|为按为或，作用如下：
1，可以设置对应的位为1，将第八位设置为1（a|0xff）
^为按位异或，作用如下：
1，将a中的数据1变为0，0变为1（a^(~0)），好像也可以~
2，交换数值（a=a^b;b=b^a;a=a^b）
<<n，实现a*2^n。
>>n
===========================================
#include <stdio.h>
int main(int argc, char *argv[])
{
	int x=1;
	int y=1;
	if((x=0)&&(y=2))
	{
	}
	printf("执行结果：x=%d,y=%d",x,y);
	return 0;
}	//执行结果：x=0,y=1
==========================================
#include <stdio.h>
int main(int argc, char *argv[])
{
	int x=1;
	int y=1;
	if((x=0)&(y=2))
	{
	}
	printf("执行结果：x=%d,y=%d",x,y);
	return 0;
}	//执行结果：x=0,y=2
=========================================
#include <stdio.h>
int main(int argc, char *argv[])
{
	int x=0;
	int y=0;
	if((x=1)|(y=2))
	{
	}
	printf("执行结果：x=%d,y=%d",x,y);
	return 0;
}
执行结果：x=1,y=2
==============================================
这个if语句执行过程中能把值取出来吗？当然可以，不过需要用合适的算符让程序完全执行下去。
if((cgiFormString("username_htm",username_cgi,sizeof(username_cgi))!=cgiFormSuccess)|(cgiFormString("pwd_htm",pwd_cgi,sizeof(pwd_cgi))!=
cgiFormSuccess)|(cgiFormString("id_number_htm",student_teacher_number_cgi,sizeof(student_teacher_number_cgi))!=cgiFormSuccess))
============================================================================================
函数的执行即使没有调用变量也需要用括号：
touch_session();
========================================
为了保持系统安全，可以联合使用set_cooki,get_cooki,touch_cooki
用户A用浏览器A登录成功后服务器生成一个guid字符串A。然后分两个方面，首先服务器将这个guid字符串A作为主键值存放在数据库中的一个表格中，一起存入的还有用户的ID（唯一确定），这样在服务器端
这个guid字符串就和用户ID联系在一起了,另一方面服务器将这个guid当作cooki返回给浏览器A。当另一个用户B用其他的浏览器B也登录成功时同样会产生guid字符串B，同样会在数据库中和用户B的ID一起存上
并且将这个guid字符串B作为cooki在浏览器B中存上。
下次浏览器A或者B跟服务器交互时浏览器都会把各自的cooki传给服务器，服务器拿到guid字符串之后跟数据库中的字符串比较一下，如果一致就认为是用户A和用户B的登录，并且能够确定用户的ID。经过验证后
A和B就可以查看或修改个人信息进行修改。这是因为由于guid字符串很难猜测，并且是基于MAC地址和登录时间（精确到CPU振荡周期）自动生成的世界上唯一的一个。所以可以认定这个用户就是之前成功登录过的那个。

如果用户A和用户B都用同一个浏览器C登录，A先登录，登录成功后在在数据库中留下guid字符串A和自己的用户ID，在浏览器端生成cooki字符串A。之后B如果访问A的个人信息也是可以访问的，因为是同一个浏览器
，浏览器中的cooki中还存着A的guid字符串，由于guid的难以猜测的特性所以不考虑用户修改guid的情况。如何避免这种情况？就要给浏览器端的cooki设置一个存活时间，过了这个时间这个guid字符串就删除了，
在这个时间之后用户再向浏览器发送请求就不会再带有cooki了。服务器就把请求的页面跳转到登录页面，提示用户先登录。另外一种方法是服务器每隔一段时间就把数据库中存活时间很长的guid主键值所对应的条目
删除掉。这样即使浏览器中还存着用户上次访问时写入的cooki那么服务器跟将这个cooki与数据库中数据比较时就会发现数据库中不存在对应的项，同样会重定向到登录页面，提示用户重新登录。
这几个过程分别可以用几个函数来实现，首先登录成功时将guid字符串写入cooki设置一个存活时间，并将guid字符串和用户名写入数据库的工作有set_cooki实现。访问个人信息之前需要将cooki和数据库中的数据进行比较
如果匹配将用户名取出这个过程由get_cooki实现。每隔一段时间就将数据库中的比较久的cooki清除掉这个工作由touch_cooki实现。
有几个问题虽然无意义但还是值得想一下：
数据库中发现一个用户名有不同个guid，可能情况是浏览器端的cooki存活时间比touch_session时间还短，没等服务器将数据库中的数据删除浏览器中的数据就过期了，这样用户再操作时就要重新写入cooki了。
或者是用户在短时间内用了不同的浏览器，或者用了不同的电脑登录了。
由于浏览器可以带多个cooki所以可以将登录成功时的用户名或者密码用
cgiHeaderCookieSetString("username","student",20,"","");//创建一个cooki名字为username，内容为student的cooki
cgiCookieString("username",value,sizeof(value));//将名字为username的cooki读取出来，并放在变量value中
=========================================================================================================================================================================
void touch_session()
{
	char session_id[50] = {0};
	char mysql_query_string[500]={0};
	char error_message_search[500] = {0};
	//MessageBoxA(0,"ok","ok",0);
	sprintf(mysql_query_string,"delete from t_session where (unix_timestamp(now())-unix_timestamp(last_update_time))>=5*60");
	//将数据库中超过5分钟的记录都删掉
	MYSQL_QUERY(mysql_query_string,error_message_search);
	if(cgiCookieString("session_string",session_id,sizeof(session_id))==cgiFormSuccess)
	{
		sprintf(mysql_query_string,"update t_session set last_update_time=now() where id= '%s'",session_id);	//更新用户的时间不是必须的，但是能延长一下还是好的，思想是两次操作时间不超过5分钟，如果不更新时间可能还没到5分钟的时候进行下一次操作时服务器端就把用户干掉了。
		MYSQL_QUERY(mysql_query_string,error_message_search);
		cgiHeaderCookieSetString("session_string",session_id,30,"","127.0.0.1");	//同样要更新一下浏览器的cooki否者还没到5分钟浏览器端的cooki就死了，取不到浏览器的cooki用户也是要下线的。同时这个延长到时间也不要小于5分钟。不然也是一样。
	}
}
======================================================================================================================================================================
int set_session_id(char* user_id)
{
	BOOL need_create_session = TRUE;
	char session_id[50] = {0};
	char mysql_query_string[500] = {0};
	char query_string_result[500] = {0};
	if(cgiCookieString("session_string",session_id,sizeof(session_id))==cgiFormSuccess)
	{
		MYSQL* mysql_tube = mysql_init(0);
		MYSQL_RES* mysql_result;
		MYSQL_ROW one_row_result;
		sprintf(mysql_query_string,"select count(*) from t_session where id = '%s'",session_id);
		mysql_tube = MYSQL_QUERY(mysql_query_string,query_string_result);
		mysql_result = mysql_store_result(mysql_tube);
		one_row_result = mysql_fetch_row(mysql_result);
		if(strcmp(one_row_result[0],"0")==0)	//这种情况是浏览器还有gui字符串，但是数据库中已经没有了，说明服务器中guid的被touch_cooki干掉了。这种请况下可以重新创建一个guid字符串，也可以就用浏览器中这个原有的，新的和旧的性质都一样，为啥这么肯定从浏览器中取出的这个叫session_string的字符串就是guid字符串呢，因为这个叫session_string的最初也是由guid创立的。
		{
			need_create_session = TRUE;
		}else									//这种情况下浏览器和数据库都有，但是数据库中的这个guid字符串对应的用户ID有可能不是user_id,所以要把数据库中的用户名更改一下。
		{
			sprintf(mysql_query_string,"update t_session set user_id = '%s',last_update_time=now() where id= '%s'",user_id,session_id);
			MYSQL_QUERY(mysql_query_string,query_string_result);
			if(strlen(query_string_result)==0)
			{
				need_create_session = FALSE;
				return 1;
			}else
			{
				need_create_session = TRUE;	//没更改成功，那这个guid就舍弃吧，来个新的。
			}
		}
	}else
	{
		need_create_session = TRUE;
	}
	if(need_create_session)
	{
		char guid_set[50] = {0};
		char error_message_creat[50] = {0};
		char mysql_query_insert_string[500]={0};
		createGuid(guid_set);	//创建一个guid字符串，
		cgiHeaderCookieSetString("session_string",guid_set,60*30,"","127.0.0.1");	//把这个字符串命名为session_string,并且写入cooki
		sprintf(mysql_query_insert_string,"insert into t_session(id,user_id,last_update_time) values('%s','%s',now())",guid_set,user_id);
		MYSQL_QUERY(mysql_query_insert_string,error_message_creat);	//把这个字符串作为主键ID和用名user_id一起写入数据库。
		if(strlen(error_message_creat)==0)
		{
			return 1;
		}else
		{
			return 0;
		}
	}
}
=========================================================================================================================================================================
int get_session_id(char* the_session_id_result)
{
	char get_session_id_string[100] = {0};
	char mysql_query_string[500] = {0};
	char error_message[500] = {0};
	//MessageBoxA(0,"ok","ok",0);
	if(cgiCookieString("session_string",get_session_id_string,sizeof(get_session_id_string))==cgiFormSuccess)
	{		//看一下cooki中有没有叫session_string的cooki，如果有取出其值。一个cooki名当然只能有一个值。浏览器当然可以存好多cooki，但是名字都是不同的。
		MYSQL_RES* get_result;
		MYSQL_ROW one_row_result;
		MYSQL* mysql_tube = mysql_init(0);
		//cgiCookieString("session_string",get_session_id_string,sizeof(get_session_id_string));
		sprintf(mysql_query_string,"select user_id from t_session where id='%s'",get_session_id_string);
		mysql_tube = MYSQL_QUERY(mysql_query_string,error_message);//去数据库看看从浏览器中取到的这个cooki值数据库中有没有用户名，一般都是有的。还想不出例外。
		if(strlen(error_message)==0)
		{		
			get_result = mysql_store_result(mysql_tube);
			if(one_row_result = mysql_fetch_row(get_result))
			{
				sprintf(the_session_id_result,one_row_result[0]);
				return 1;	//如果从浏览器中取到的这个cooki在数据库中有，并且有对应用户名，那么就认为这个用户是合法用户
			}else
			{
				return 0;
			}

		}else
		{
			return 0;
		}
	}else
	{
		return 0;
	}
}
============================================================================================================================================================================
使用set_cooki将用户的ID存放在一个表中，这个表中的主键是一个guid字符串
