
��̬��ҳ����һֱ�����  
��̬��վ���û��������������ԣ�ʱʱ������ҳ��  
web�������ṩ�ײ��socket����  
�����������׶˳���CGI�� Java ��asp.net  
Ƕ��ʽ��·����������CGI����  
http:80�˿ڣ�socket��һ���˿�ֻ�ܱ�һ������ռ��  

apache����������
==================
1. ��ѹ������ѹ��Ӳ�̣���ò�Ҫ�����ġ��ո�������ţ���ѡ��һ���ļ�����Ϊ����վ��Ŀ¼����Ȼ���apache��conf/httpd.conf�е�DocumentRoot�޸�Ϊ����վ��Ŀ¼����ע�⣺·���ָ�һ��Ҫ����б��/����Ҫ��\��Ҫ��Ӣ�ĵ����ţ���Ҫ�����ĵ����ţ�����վ��Ŀ¼����ò�Ҫ�����ģ�����httpd.confҪ��Utf-8���뱣�棩��
2. ˫��bin�µ�httpd.exe�������������������Ļһ��������������ǳ����ˡ�����������������httpd.exe����������Ϣ�������dos������֮�½���binĿ¼��Ȼ������httpd.exe��

���ܵ�**����**����(OS 10048)ͨ��ÿ���׽��ֵ�ַ(Э��/�����ַ/�˿�)ֻ����ʹ��һ�Ρ�����������Web���������ѵ����ϰ�װ��������IIS��Apache��Tomcat��Web������ж�ص������޸Ķ˿ںš� httpĬ��ռ��80�˿ڣ�������ɫ������Ĭ��ռ��8080�˿ڣ��鷳�ط�����ÿ����ַ��Ҫ���˿ںš�ͨ��httpd.conf�޸Ķ˿ںš�  
�ڡ���վ��Ŀ¼���ü��±����ĵ�httpd.conf�еĸ�Ŀ¼�����·�һ��1.html��д�����ݣ����������http://127.0.0.1:80/1.html���������ʾ��û�����ˡ�  
�ر������д���apache�������͹ر��ˡ�Ĭ�����ü򵥵��ǲ���ȫ�������ڿ���������������ʽ���е���վ��(*)��ʽ�����Է���ʽ����apche�����ǿ���ʱ��Ҫ�á�Windows ���񡱷�ʽ��������Ҫ��Ȩ�޵��µķ������ޡ����Եȵ��鷳��  
��������г����һ����ʽ  
ipcmg  
���˲�����internal server error,һ��˵���ǵ�һ�г���  
����Ǽ���һ����ų���internal server error��һ���ǳ��������⣬��0��ָ�����  
�������apache�����������exe�ļ���Ȼ��apache�����������еĽ�����ظ��������������������ʾ������  
system("pause");//�����������   
����滻cgiʱ�滻����˵��cgi����apache����������cgi  

��ȡ�û��������  
```
?username=yzk&&password=123��ʾ�û������û��������������������û����������Ƿ�ƥ�䣬������ȷ���Ƿ����.�еĵط�����֮��ֻ��һ��&

    char * qs = getenv("QUERY_STRING");
    char username[256];			//username=yzk
    sscanf(qs,"username=%s",username);	//��qs�з�������username=%s��ʽ���ַ������ѽ�����������һ���ַŽ�usernameָ���С�
    printf("Content-Type:text/html;charset=gbk\r\n\r\n");
    printf("<html><head></head><body>");
    if(strcmp(username,"admin")==0)	//�Ƚ������ַ����Ƿ���ȣ��������򷵻�0
    {
        printf("��½�ɹ�");
    }
    else
    {
        printf("��½ʧ��");
    }
    printf("</body></html>");

```
VS�б�������ֻ�ܷ��ڴ����ǰ��  
JavaScript�Ĵ�����д��������еģ���CGI��д�ڷ������еģ���ͬ�������в�ͬ���ô���  

**������ͷrequest headers**
```
GET /1.html HTTP/1.1	//����Ҫ1.html������֮��ͨ����HTTP/1.1Э��
Host: 127.0.0.1
Connection: keep-alive
Upgrade-Insecure-Requests: 1
User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36	//���UAΪ������İ汾��Ϣ��ͨ�������Ϣ���Զ�ȡ�������IE����Firefox��֧��ʲô����������汾
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8	//��֧��ʲô���ĸ�ʽ
DNT: 1
Accept-Encoding: gzip, deflate, sdch			//��֧��ʲô����ѹ����ʽ
Accept-Language: zh-CN,zh;q=0.8,en-GB;q=0.6,en;q=0.4	//��֧��ʲô��������
If-None-Match: "400000000cfa0-40-53769418226ab"
If-Modified-Since: Tue, 12 Jul 2016 05:08:13 GMT	//�����ڵĸ�������ʱ����ʲô
```
***
**��Ӧ����ͷresponse headers**
```
HTTP/1.1 200 OK						//�ļ��ҵ��ˣ���302����Found��ʱת�ƣ������ض��򣻡�505������������
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

**��Ӧ������response**

```
				//����ͷ�뱨����֮��Ҫ�����п���
HTTP/1.1 404 Not Found				//����Ҫ���ļ�û���ҵ�
Date: Tue, 12 Jul 2016 12:52:18 GMT		//��ǰʱ����ʲô
Server: Apache/2.2.21 (Win32)			//����һ��ʲô������
Content-Length: 204
Keep-Alive: timeout=5, max=100
Connection: Keep-Alive
Content-Type: text/html; charset=iso-8859-1
```

Windows��дCGI����һ�㶼��"Content-Type:text/html;charset=gbk\r\n\r\n"   
������ͨ��Content-Type���߿ͻ�����Ӧ�����ݵ����ͣ�����������͸��ݷ������ݵ����������в�ͬ�Ĵ��������ͼƬ���ı����;�ֱ����ʾ���ݣ������html���;����������ʾ���ݣ����õ�Content-Type�У�text/html.image/gif.image/jpeg.text/;lain.text/plain.text/javascript   
����������Windows�е�VS�б���ʳ���gbk�����һ��д"charset=gbk"   

```
printf("Location:http://baidu.com\r\n\r\n");	
printf("Content-Type:text/html;charset=gbk\r\n\r\n");	//�����������Ǳ���ͷ
printf("<html><head></head><body><font color='red'>����<br/>�û����ǣ�%s</font></body></html>");	//������
�ڴ���ǰд�ϡ�һ��д������\r\n����������Ϊ����ͷ�����ˡ�
printf("Location:http://baidu.com\r\n");ʱ����������302�ض���

printf("Location:http://baidu.com\r\n\r\n");
cgiHeaderLocation("http://baidu.com");
�����������������ض��򣬵������ض���֮ǰ����Ҫд	��������������������������������
cgiHeaderContentType("text/html;charset=gbk")
����
printf("Content-Type:text/html;charset=gbk\r\n\r\n");
```
***
```
#include <stdio.h>		//��studio.h
#include <string.h>
#include <stdlib.h>		//getenv���������ͷ�ļ�
int main()
{
	char name[250]={0};	//Ҫָ�������С������������ʱ�ᱨ��
	char * qs=getenv("QUERY_STRING");	//getenv("QUERY_STRING")
	sscanf(qs,"name=%s",name);	//sscanf
	if(strcmp(name,"abc",)==0)
	{
		printf("Location:1.html\r\n\r\n");
	}else
	{
		printf("Content-Type:text/html;charset=gbk\r\n\r\n"); //charset=gbk
		printf("<html><head></head><body>�»��籨����%s �ͷ������</body></html>",name);
	}
}
```
�ڽ�������¿����½�һ����Ŀ�����ҵ���Ҽ����á��趨Ϊ������Ŀ��  

����һ���ϵ㡢��һ������һ��������  
��ΪCGI������apache���������ģ������������������VS�ĵ��������ӵ�CGI����Web������������windows�������ʽ����  
````
#include<windows.h>
MessageBoxA(0,"ok","ok",0);��ִ�е��������ʱ�Ի���رղ��ܼ�������ִ��
```
��ʱ���Ե�����������б����б���ѡ�񸽼ӵ�����Ȼ���ڳ�������ɫ�ش������Ӷϵ㣬��Щ��������֮����OK����������ĶԻ���Ȼ�������ڶϵ㴦ͣ�����ˣ����ǽ������ڱ����ϾͿ��Կ���������ֵ�ˣ��Ӷ��𵽵��Ե�Ŀ�ġ�  

**CGI��**
��ͬ�Ŀ������Զ��в�ͬ�Ŀ������	��� Framework  
Java: Structs servlet JSP  
.Net: WebForm ASP.Net MVC  
PHP  
asp.net MVC  
CGICֻ�Ǽ򻯿������ѣ�  
No Magic!û��ħ��  
������������������Ҳ���Լ���һ�����ƵĶ�������  


ʹ�ÿ������
=================
��vs�½�win32����̨��Ŀ���½�.c��Ŀ���ڽ�������µ���Ŀ�ϵ���Ҽ�ѡ�����ԣ��������еı���λ���Լ�������չ������һ�¡�  
.\   ��ʾ��Ŀ�ļ�����Ŀ¼֮�µ�Ŀ¼��  
..\ ��ʾ��Ŀ�ļ�����Ŀ¼����һ��Ŀ¼�µ�Ŀ¼��  
..\..\��ʾ��Ŀ�ļ�����Ŀ¼���϶���Ŀ¼֮�µ�Ŀ¼��  
..\..���Ա��浽���ļ�Ŀ¼ͬһ�����Ŀ¼��.Ҳ����˵�����ù��̶�������abc�ļ����ڵģ����Ǵ�ʱ���Գ�����cgi�ļ���abc�ļ��з�����һ��  
���Ҽ�--���ļ���Դ�������д��ļ��У���cgi.c ��cgi.h�����ļ��и��Ƶ��򿪵��ļ����С�  
���Ҽ�--���--������Ŀ--ѡ���������ļ���ӣ�  
```
cgiFormString("password",pwd,sizeof(pwd));�������Դ��û�����ġ�password=123�������з���������Ϊ123���Ҵ����pwd�ַ����У��ַ����ĳ�����sizeof(pwd)��
strlen(pwd)==0

cgiFormString("password",pwd,sizeof(pwd))!=cgiFormSuccess)
�������ַ����������ж��û�����������Ƿ�Ϊ�ա�

��html�д�ӡһ�λ��ķ�����
fprintf(cgiOut,"����д������<br/>");
printf("����д����\n");

CGI�ļ��ж��������֮��Ҫд
fprintf(cgiOut,"<html><head></head><body>");
����
printf("<html><head></head><body>");

ѡ������ķ���
fprintf(cgiOut,strcmp(str_gender,"nan")==0?"˧��":"��Ů");
```
**������ͷ������Ľ���**
���ύ  
ע��id�Ǹ�JS����Dom�õģ�name�����ύ���������õġ�id�����ظ���name�����ظ�������ύ��Ԫ����name��ͬ���ڼ��ʱ���ܳ������ظ���ֵ������ύ��������  
����������cgiFormString�������ݱ����name������ύ������ֵ��  
html��<form>�����Զ����������ύ������get��ͨ��URL��post��ͨ�������壩�������û��Լ�ƴurl��action�ƶ��ѱ������ύ��˭��  
���������������ύ���ݣ����ύ���ݵı���input��select��textarea�ȣ��ŵ�form�У�form��ͨ��action�����趨�����ύ���ĸ�ҳ�棬Ϊ���ڷ�������ȥ�������ֵ��������HTML��Ϊ��Ԫ���趨name�Լ�value���ԣ����߲����ύ����������  
�����input type=��submit���İ�ť��ʱ�򣬻�������ڵ�form�е�������name���Եġ�input/select/textarea/checkbox/radio/submit����ֵ�ύ����������  
input type="text"����input type="password"��ֵ�����û������ֵ��  
textarea����������ı�  
selectΪѡ�����Ӧ��option��value;  
checkbox���ͱ����趨name��value��ֵ������name�����ύ������valueֻ���ύ��name=��   
radio���͵�ͬ���趨name��value��ֵ�����趨name��ɶ�ѡ������һ��ѡ�оͲ��ܸ��ģ����趨value����ѡ�����ύ����sex=on��  
input type="submit"���͵�����趨name��ô���㰴ť��name��valueҲ���ύ����������  

������ͨ�����ͷ��������н���
==============================
html�еĴ��룺
```

    <form action="CGIC1.cgi">
        <br/>										//�ı������
        �û�����<input type="text" name="name" value="�������û���"/>		//name=enter+username//������text
        ��  �룺<input type="password" name="password" value="����������"/>	//password=123
        <br/>
        <textarea name="textareaword">���ڴ���д���˼��</textarea>		//textareaword=textarea+word
        <br/>
        ����
        <select name="minzu">								//�����б�
        <option value="han" selected>����</option>				//minzu=han
        <option value="hui">����</option>
        </select>
        <br/>										//��ѡ��
        <br/>��ϲ����ˮ����<br/>						//checkbox1=apple&checkbox2=pear&checkbox3=banana
        <input type="checkbox" name="checkbox1" value="apple" id="cb1"/><label for="cb1">ƻ��</label>
        <input type="checkbox" name="checkbox2" value="pear" id="cb2"/><label for="cb2">����</label>
        <input type="checkbox" name="checkbox3" value="banana" id="cb3"/><label for="cb3">�㽶</label>
        <br/>�Ա�<br/>									//��ѡ��
        <input type="radio" name="sex" value="nan" id="nan"/><label for="nan">����</label>	//sex=nan
        <input type="radio" name="sex" value="nv" id="nv"/><label for="nv">Ů��</label>
        <br/><br/>									//��ť
        <input type="submit" name="submitting" value="login"/>			//submit=login
        <input type="submit" name="submitting" value="loginout"/>
    </form>
```
***
		#include "cgic.h"

		name=enter+username&password=123&textareaword=textarea+word&minzu=han&checkbox1=apple&checkbox2=pear&checkbox3=banana&sex=nan&submit=login

�ж��û��Ƿ�����name="submitting",value="="�İ�ť�ķ���  
```
����1��	if(cgiFormString("submitting",submit,sizeof(submit))!=cgiFormSuccess)  
����2��		cgiFormString("submitting",submit,sizeof(submit));  
		if(strcmp(submit,"=")!=0)    
����3��	if(cgiFormSubmitClicked("submitting")==cigFormSuccess)	//���ʱ�ύ�ı���ͷ����"submitting="��������Ϳ���ʶ���ˣ���˱�����name
����3Ǳ�ڵ�bug,���ύ�ı���������һ����submitting�İ�ť�������ı���ʱ��ʹû�е��submitting����Ȼ��submitting
���ύ�������ڸ�������ʱҪע�⣬���ֵ�Ψһ
```
���ֻ��һ���ʱ�ж�checkbox�Ƿ�ѡ�У�cgiFormCheckboxSingle(char*name)��

�����ı��������name����valueֵ�û����ľ�ֻ���õ�һ�ַ�����
�����б���ѡ�򡢵�ѡ��ȿ�������name�ֿ�������value�ı����������һ�����ַ�����
ͬʱ���ڸ�ѡ�򡢵�ѡ�򡢰�ť���������name����ʹû��valueϵͳ�Զ������һ��"on"��Ϊvalue��ֵ��Ҳ����˵�ύʱ��name=on�����������б����������name��û����value����ô�ύʱ��name=��ǩ��

html��ע�Ͷ�����
<!--
-->
3.input type='radio'
4.select <select name='addr'><option value='hebei'>�ӱ�</option>Ҳ������cgiFormString
fprintf(cgiOut,"<html><head></head><body>���������useragent�ǣ�%s<br/>����IP��ַ�ǣ�%s<br/>���CGI��ַ�ǣ�%s<br/>���Query_string�ǣ�%s</body></html>"
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
		printf("����������");
	}else if(cgiFormString("password",str_password,sizeof(str_password))!=cgiFormSuccess)
	{				//�������־������жϻ�õ���ֵ�Ƿ�Ϊ�գ����ǵڶ��ֺ���û�л����ֵ��Ҫ����д�����ܻ����ֵ��
		printf("�����뿼����");
	}else if(strlen(str_gender)==0)
	{
		printf("��ѡ���Ա�");
	}
	if(strcmp(str_ti1,"a")!=0)
	{
		printf("��һ������");
	}else
	{
		printf("��ϲ�㣬��һ������");
	}
	return 0;
}

get�ύ��ʽ������ͷ�У�require head�����ύ��Ϣ��ͨ��URL���ݱ�ֵ���������ַ���п��Կ����ύ��Ϣ�������󴫵ݳ��������޵ģ���������������ַ�ߣ����ύ�����ַ�������˺���˿��Կ���һ���Ľ�������������ַ�����enter��get��ʽ
post�ύ��ʽ��������ݷ������������еġ�type="file" type=��password�� <textarea>
method="post";

jsp struts velocity
.net aspx razor
MVC�ǽ���ַ���ƴ��֮������⡣
=====================================================
.NET��չ���ƣ���ҵ��Ϣϵͳ��ҵ
WinForm�½���
ASP.Net WebForm�½���ASP.Net MVC����
.NET����Ч�ʸߣ����˻�������˾ʹ��.NET������
ӵ����Դ��ӵ���Ƽ��㡢ӵ���ƶ�������

===============================
��ģ�������html���н���=======
===============================

��p1.htm��д�����´���
��һ�������ֵ��<TMPL_VAR name="test1"/>	//Ψһ�ɱ�ľ���������ߵ�test1��
�ڶ��������ֵ��<TMPL_VAR name="age1"/>
����ⲿ��������д�����´���
<input type="text" name="number1" value="<TMPL_VAR name='number1'/>"/>

��cgi��д��
#include<stdio.h>
#include"ctemplate.h"
#include"cgic.h"
int cgiMain()
{
TMPL_varlist * varlist1=0;	//����һ���յĲ����б�	//���յĶ����ַ����͡�	//�κ���ʽ����������TMPL_varlist*
varlist1 = TMPL_add_var(varlist1,"test1","��˹��",0);	//��0��β��ʾ�ɱ������text1��age������htm�ļ��е�һ��.0�ܹؼ�����Ȼ����Ѫ���ܵĽ�ѵ20160716.
varlist1 = TMPL_add_var(varlist1,"age","20",0);	//����д�ַ�������20��������������.��������ַ������Ǳ��� char * string;string="�Һܸ���";
						//varlist1 = TMPL_add_var(varlist1,"age",string,0);
TMPL_write("p1.htm",0,0,varlist1,cgiOut,cgiOut);//�����TMPL_write������varlist1д��HTML�ļ�.��ֻ��д������������Ե���html�ļ���
}

=====================================================
�ṹ��ͬ����������д��
��cgi�ļ���
TMPL_varlist* varlist_all=0;
TMPL_loop* loopUsers=0;		//����һ���������нṹ��ı���
loopUsers = TMPL_add_varlist(loopUsers,TMPL_add_var(0,"name","rupeng","age","8",0));
				//��TMPL_add_varlist();����д�룬��TMPL_add_var()���������������û��0;
				//��TMPL_add_var(0,"name","rupeng","age","8",0)��һ���ṹ������д��һ��TMPL_varlist������ֻ��������һ����ֵ
loopUsers = TMPL_add_varlist(loopUsers,TMPL_add_var(0,"name","taobao","age","10"));
loopUsers = TMPL_add_varlist(loopUsers,TMPL_add_var(0,"name","jd","age","15"));	
				//���ֵ�������ַ�����Ȼ15������������Ҫ�����������ġ�������Ҫ��һ�������������������Ҫ���ַ��͵ı��������������������Ҫ��itoa���������͵ı����滻Ϊ������
varlist_all = TMPL_add_loop(varlist_all,"user",loopUsers);	//����һ���������������нṹ��ı���д��varlist_all,��ʶΪuser
varlist_all = TMPL_add_var(varlist1,"Title","����loop",0);	//��ʱ�������ٱ�̬һ�㣬����varlist_all���дһ���ǽṹ��ı�������ʶΪtitle��
cgiHeaderContentType("text/html;charset=gbk");
TMPL_write("loop.htm",0,0,varlist_all,cgiOut,cgiOut);		//����װ���ˣ�����д��html��
=======================================================
html�е��ļ�

    <table>
        <TMPL_VAR name="Title"/>	//����Title������㣬���Կ���ֱ������
        <tm><td>��վ</td><td>����</td></tm>
        <TMPL_LOOP name="user">		//�ȴ򿪽ṹ������İ�װ�������һ��ѽṹ��
    <tr><td><TMPL_VAR name="name"/></td><td><TMPL_VAR name="age"/></td></tr> 	//���������У�һ��һ������
        </TMPL_LOOP>
    </table>

TMPL_add_loop		������õĽṹ������ӵ���һ���ܰ���
TMPL_add_var		��һ����ͨ������ӵ���ͨ����
TMPL_add_varlist	��һ���������ṹ����ӵ��ṹ�����У�
������������߶���Ҫ�б��������գ�����ͬ����Ѫ���ܵĽ�ѵ��20160718

ͨ��form�е�action�����Լ�TMPL_write();�������Խ�htm�ļ���cgi�ļ����н���������ԭ�����û�����cgi�ļ���Ȼ��write��������html�ļ���html�е�action��ת��cgi�ļ������������������E:\C_lang\www\c_html\calculate_project\calculate_project.sln
�����Ҫֻ����ʾһ��ҳ��Ļ��Ϳ���ʡ��һ��������TMPL_write("loop.htm",0,0,0,cgiOut,cgiOut);
�������������Ҳ������cgi�ļ���ֱ����fprintf();������htm�ļ��Ĵ��������ȥ�����������֮����ʾ��������

cgi׼�����ݴ������ݣ�html�������ݾ�����������ôչʾ����˾��ְ��
MVC model view controller
==================================================================

========================================================
��int cgiMain()�����޷�����ʱӦ�ÿ����Ƿ��.c�ļ����󽨳�.cpp�ļ���
���������֮��apache��չ�
Visual Studio 9.0���͡�Visual Studio 2010��������ѡ����Ӧ���룬Ȼ��Ctrl+K, ��Ctrl+F
============================================================================================================
C��������MYSQL
===========================
��������
1������Ŀ�����С�VC++Ŀ¼����������Ŀ¼����ѡ��mysql��include�ļ��У�����Ŀ¼��ѡ��mysql��lib�ļ��У������������������롿�ġ�������������ӡ�libmysql.lib����

2��C������includeͷ�ļ�mysql.h��������֮ǰҪincludeͷ�ļ�winsock.h��

3��MYSQL *pConn = mysql_init(0);

4���������У��ᱨ���Ҳ�����libmysql.dll������mysql��libmysql.dll���Ƶ�exe��Ŀ¼�¡�

��¼��û�ù�gcc��ͬѧ���ÿ����������ͬѧʹ�ù�gcc����C�������ģ��������˽����ͨ��gcc����ʹ��mysql�ĳ���ʹ���������µ������м��ɣ�gcc main.c -o test.exe -ID:\greeninstall\mysql\include -LD:\greeninstall\mysql\lib -llibmysql
Ϊʲô��ô���ã���Ϊgcc��ʹ��-I����ͷ�ļ���λ�ã�-L����lib�ļ���λ�ã�-l����Ҫ���ӵĿ����ƣ�Ҫ����ԭ������ֻ������������Ľ��ۣ���������������޷��Լ�����ˡ�
���������ͨ��gcc�����б��룬��ôҲ��֪�������EditPlus+gcc���������ñ��������ˣ�$(FileName) -o $(FileNameNoExt).exe -ID:\greeninstall\mysql\include -LD:\greeninstall\mysql\lib -llibmysql

gotoһ�㲻�Ƽ��������ڴ������ʱ�򣬺ܺ��á�
��MYSQL��ѡ��һ�д�����Խ���ִ����һ�С�

	MYSQL  *mysql = mysql_init(0);	//����һ��MYSQL���͵�ָ��mysql
	if(!mysql_real_connect(mysql,"localhost","root","root","study1",0,0,0))
		//ͨ��mysql����ܵ��������û��������롢Ҫ���ӵı�
	{
		printf("�������ݳ���%s",mysql_error(mysql));
		goto exit;
	}else
	{
		printf("�������ݿ�ɹ�\n");
	}
	if(mysql_query(mysql,"set names gbk"))	//�������к���������gbk����
	{
		printf("�趨���ӱ���ʧ��%s",mysql_error(mysql));
		goto exit;
	}
	if(mysql_query(mysql,"insert into t_study(username,password) value('��','123')"))
		//������߲�ֹ������insert����������select��update��delete��
	{
		printf("����ʧ�ܣ�%s",mysql_error(mysql));
		goto exit;
	}
	printf("insert�ɹ�\n");
exit:							//����һ����ǩ����������
	mysql_close(mysql);
	getchar();					//��ֹ������

��sprintf������̬ƴ�Ӳ���
int i;
char str[200]={0};
for(i=0;i<10;i++)
{
sprintf(str,"insert into t_study(username,password) values('%d','%d')",i,i);//ע���ʽ����C���Բ��ܴ�û�и�ֵ����values��ע��Ҫ�õ���������������Ϊ���ַ�
if(mysql_query(mysql,str))
{
printf("����ʧ�ܣ�%s",mysql_error(mysql));
goto exit;
}
}


�����ݿ��е����ݴ�ӡ����mysql_store_resul��mysql_store_result���������ǹؼ�
	if(mysql_query(mysql,"select * from t_study"))	//���ж���ʱ�����ﲻҪ��*��������д���ж�����
	{
		goto exit;	//���Ӵ����˳��ر�����
	}else
	{
		MYSQL_RES * result = mysql_store_result(mysql);	//���ߴ��ڵ����ڴ��С���ѯ���֮��Ϳ��ԶϿ����ݿ����ӡ��ٽ���á�
		MYSQL_ROW * mysql_row;	//����һ����//���������������Ǻŵ�.��Ҳ��row����û��
		while(mysql_row=mysql_fetch_row(result))	//��ֵ�Ľ�����������ж�
		{
		printf("ID=%s,username=%s,password=%s\n",mysql_row[0],mysql_row[1],mysql_row[2]);
		}
	}
mysql_free_result(result);

========================================================================================================================
����ѯ�����ǲ�ѯ����װ������
========================================================================================================================
1.�ǲ�ѯ��䣨��ɾ�Ĳ飩
========================================================================================================================
MYSQL MYSQL_QUERY(char* str_mysql)
{
	MYSQL* mysql_tube = mysql_init(0);
	if(!mysql_real_connect(mysql_tube,"localhost","root","root","study1",0,0,0))
	{
		goto error;
	}else
	{
		printf("���ӳɹ�\n");
	}
	if(mysql_query(mysql_tube,"set names gbk"))
	{
		goto error;
	}else
	{
		printf("���ñ���ɹ�\n");
	}
	if(mysql_query(mysql_tube,str_mysql))
	{
		goto error;
	}else
	{
		printf("���ݿ�����ɹ�\n");
		goto exist;
	}
error: printf("���ݿ���� %s",mysql_error(mysql_tube));
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
2.��ѯ��䣨�����ݵ������
======================================================================================================================
MYSQL MYSQL_NON_QUERY(char* str_mysql)
{
	MYSQL * mysql_tube = mysql_init(0);
	if(!mysql_real_connect(mysql_tube,"localhost","root","root","study1",0,0,0))
	{
		goto error;
	}else
	{
		printf("���ݿ����ӳɹ�\n");
	}
	if(mysql_query(mysql_tube,"set names gbk"))
	{
		goto error;
	}else
	{
		printf("���ݿ����ñ���ɹ�\n");
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
error: printf("���ݿ����\n",mysql_error(mysql_tube));
exit: mysql_close(mysql_tube);
	getchar();
}
int main()
{
	char* str_non_query="select * from t_customers";
	MYSQL_NON_QUERY(str_non_query);
	
}

���Ҫ������ʱ���Զ���һ���������

void printError(char* errorMessage)
{
printError("���ִ����ŵ��ַ���Ҳ����ֱ�ӽ���")��
}

MYSQL_ROW* data_row;����ʱ���Բ����Ǻš�

cgi��html��mysql֮��ı���Ҫһ�£���ֹ��������������
�½�mysql���ݿ��ʱ��һ��Ҫѡ��gbk����
����ʱҪmysql_query(mysql_tube,"set names gbk")
htmlҪcgiHeaderContentType("text/html;char set=gbk");	//��charset

========================================================================
����ʱ��������ŵĴ�����������ַ�������16����ת��mysql_hex_string===
========================================================================
cgiHeaderContentType("text/html;charset=gbk");
cgiFormString("username_input",username_get,sizeof(username_get));
cgiFormString("password_input",password_get,sizeof(password_get));
mysql_hex_string(hex_username,username_get,strlen(username_get));
mysql_hex_string(hex_password,password_get,strlen(password_get));
TMPL_add_var(tube,"username_input","username_get","password_input","password_get",0);
==================================================
�жϵ�¼�Ƿ�ɹ�
========================
char sql[1024]={0};
sprintf(sql,"select count(*) from T_Users where UserName='%s' and Password='%s'",userName,password);
{
MYSQL_RES* res = executeQuery(sql);
MYSQL_ROW row = mysql_fetch_row(res);
char *count = row[0];
if(strcmp(count,"0")==0)
{
printLoginError("�û��������������");
}
else
{
printLoginError("��½�ɹ�")
}
}

=====================
=====SQLע��©��=====
=====================

�������һ���û����������롶1' or '1'='1��
ƴ��֮���SQL�����
select count(*) from T_Users where UserName='abc' and Password='1' or '1'='1'
����or����������������һֱΪ�档
�������֮һ�ǽ���16����ת��ת��֮��value(0xabcd,0xdfkjdf)����������0x��ʼ���Ծ������˷���
===========================================================================
int age;
cgiFormInteger("age_html",&age,998);
��htm����age_html�ı�����ȡ���������ŵ���ַ&age�У����û��ȡ�����998�����������&age��
==============================================================================
�������
double height;
cigFormDouble("height_html",&height,0);
ͬ�ϣ����Ҫ��TMPL_add_var();��Ҳ��Ҫ��double���ͱ���ת��Ϊchar
������sprintf();������
sprintf(char_height,"%f",height);	//������%f��ѡ����С��λ��

===================================================================================
��ȡ�ǳ�������������ķ���	����
===============================================================================
int length_need;
char* char_cgi;
//cgiFormStringSpaceNeeded(char* html_name,int* length);
cgiFormStringSpaceNeeded("string_html",&length);	//�鿴һ������html������Ϊstring_html�ַ����ĳ���
char_cgi = (char*)malloc(len+1);
cigformString("string_html",char_cgi,length);	//������Ϊstring_html���ַ�������char_cgi�ַ�����
free(char_cgi);
==============================================================
CGI�д����ļ��ϴ�
==============================================================
html�ļ��д���==
================
    <form action="uplood_project.cgi" method="post" enctype="multipart/form-data">
        ѡ��Ҫ�ϴ����ļ���<input type="file" name="file_name_html"/>
        <input type="submit" name="submit_html" value="uplood">
        <TMPL_VAR name="message_html"/>
    </form>

=====================
cgi�ļ��д���========
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
			messageprintf("������ѡ���ļ�<br/>");
		}else{		
			_splitpath(upload_file_name,NULL,NULL,NULL,file_ext);
			if((stricmp(file_ext,".jpg")!=0)&&(stricmp(file_ext,".zip"))&&(stricmp(file_ext,".png")))
			{
				messageprintf("��֧��.jpg��.zip��.png�����ļ���ʽ<br/>");
			}else
			{
				cgiFormFileSize("file_name_html",&file_size);
				if(file_size>2024*1024*1024)
				{
					messageprintf("��������ϴ�1M���ļ�<br/>");
					return;
				}else
				{
					{
						char buffer[1024];	//�ڻ�����һ��һ��ȡ�ļ�
						int lenRead;		//ʵ�ʶ�ȡ�ֽ���
						cgiFilePtr upload_file;
						FILE* targetFile=fopen(upload_file_name,"wb");	//�򿪱��صĽ����ļ�
						cgiFormFileOpen("file_name_html",&upload_file);
						while(cgiFormFileRead(upload_file,buffer,sizeof(buffer),&lenRead)==cgiFormSuccess)
						{
							fwrite(buffer,lenRead,1,targetFile);
						}
						fclose(targetFile);	
					}
					messageprintf("�ļ��ϴ��ɹ�<br/>");
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
MVC��һ�ֿ���ģʽ���;���ļ����޹ء�model varlist\view(html)\controller(cgi)
html�ŵ�������ģ���ļ��У����ڴ�����ƴ��html��html�༭�����ס����������
�û�����cgi����ֱ�ӷ���html���룻
cgi�������ݣ�����ģ��������ʾ����htmlҳ�棬

//
��ifҪдelse���ټӺܶ��
<a herf="studentMgr.cgi?mod=teacher&action="addnew"">	//ʹ��ʱ����cgi�ļ����ж��û��缫ʲô�ˣ��ܷ��㡣ֻҪ���ú���ת���ҳ�漴�ɡ�


<input type="hidden" name="mode" value="">������ʾ��html�����ǻ��ύ��

char* string;
char string[];
���ִ���ַ�������ʹ�� ��gcc���뻷����char*�����ڳ�ʼ�������⣬���Ծ������char*����visual studio�������sprintf�������ַ���ƴ���������ͱ���Ҫ�ܹ��ڶ��֡����д����ʱ�Ϳ���ȷ�صظ�ֵ��һ�ַ�������ʡ�ڴ棬���ڶ��ַ���Ҳ�ǿ��Եģ��൱�ڰѳ�ʼ�����ɸ�ֵ���á��Եڶ��ַ����ַ���������ֵ��һ�����ѣ����Ը�ֵʱ���õ�һ��


http://127.0.0.1/cgi_htm_mysql.cgi?mode_html=teacher&action_html=query_modify&id_htm=112&name_htm=hacker&age_htm=99
http://127.0.0.1/cgi_htm_mysql.cgi?mode_html=teacher&action_html=query_modify&id_htm=112&name_htm=hacker&age_htm=99&name_html_input=hacker1&age_html_input=99&change_message_html=%CC%E1%BD%BB

http://127.0.0.1/cgi_htm_mysql.cgi?mode_html=teacher&action_html=query_modify&id_htm=112&name_htm=hacker1&age_htm=99&change_message_html=%CC%E1%BD%BB
=============================================================================================================================================================
����Ŀ�ĵã�
��ɾ�Ĳ鶼Ҫ���������ݿ⣬�����ߵ�Ŀ�Ĳ�ͬ������ֵҲ��ͬ����ɾֻ�������ݿ��������ʧ��ʱ�÷��ش�����Ϣ�����Ĳ�������Ҫ�������ݿ��е����ݡ���ϸ˵������ֻ�÷���һ�����ݣ�
������Ҫ���ض������ݡ���ˣ�����˵��ʹ�ڲ����Ǵ�����Ϣ���ص�����������ѯ���ݿ�Ĳ���Ҳ��Ҫ������������С���ɾ�ܺð죬���Ĳ���Ҫ�����ݿ�����ݷ��أ������֪����ô����ѯ����ֵ��main����
����Ҫ�ڲ�ѯ����֮�ڽ������ݵ�����������������ݿ��ѯ�����ĳ��ȣ�������main���������á��ʼ��ѹܵ����أ����ǲ���MYSQL�ڲ���������ܵ��ģ���ʹ�����˹ܵ�Ҳ
�����á���һ�������з��࣬�ڸò�ʱ��MYSQL_RES���͵İ����������ݵı������ء��ʼ�õ����������ڴ�����ȻҲ������return�����������յ�return��������MYSQL_RES���ͱ��������������ܷ�ѹܵ���
return�����������أ������Ŀ��Դ�������������ɾ�Ĳ�Ϳ��Բ��÷�����ˣ�ֻ�ðѹܵ���ã��ѹܵ������������ˡ����ܵ�return������·�ǡ���Ȼ���Դ���MYSQL_ROW������
MYSQL* MYSQL_QUERY(char* query_string,char* error_message_return)
{
	MYSQL* tube = mysql_init(0);
	char error_message[200]={0};
	TMPL_loop* qurey_result_loop=0;
	if(!(mysql_real_connect(tube,"localhost","root","root","study1",0,0,0)))
	{
		sprintf(error_message,"���ݿ����ӳ���<br/> %s",mysql_error(tube));
		strcpy(error_message_return,error_message);
	}else if(mysql_query(tube,"set names gbk"))
	{
		sprintf(error_message,"���ñ������<br/>%s",mysql_error(tube));
		strcpy(error_message_return,error_message);
	}else if(mysql_query(tube,query_string))
	{
		sprintf(error_message,"���ݿ��ѯʧ��<br/>%s",mysql_error(tube));
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
		sprintf(error_message,"���ݿ����ӳ���<br/> %s",mysql_error(tube));
		strcpy(error_message_return,error_message);
		goto exit;
	}else if(mysql_query(tube,"set names gbk"))
	{
		sprintf(error_message,"���ñ������<br/>%s",mysql_error(tube));
		strcpy(error_message_return,error_message);
		goto exit;
	}else if(mysql_query(tube,query_string))
	{
		sprintf(error_message,"���ݿ��ѯʧ��<br/>%s",mysql_error(tube));
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
���ڳ�ʼ����һ����visual status�Ĳ�ʹ�ľ���:����Ϸ�������Ƿ���û�г�ʼ����
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

MYSQL*tube_modify = MYSQL_QUERY(mysql_change_msg,mysql_return_change_message);	//���壬��Ȼ��Ȼ��д��
�������£�
==================================================================================================================
int id_cgi;
char mysql_change_msg[200]={0};
char mysql_return_change_message[200]={0};
MYSQL*tube_modify = mysql_init(0);
cgiFormInteger("id_htm",&id_cgi,999);
sprintf(mysql_change_msg,"select * from t_students where id=%d",id_cgi);
tube_modify = MYSQL_QUERY(mysql_change_msg,mysql_return_change_message);
===================================================================================================
������վ����
��ͬ������Ҫ���ڲ�ͬ�ķ�������������������Զ�ͬһ��������ʱ�ô������١�����ͬ�ķ���������ڲ�ͬ������֮����cooki�Ͳ��÷���
�Ӷ�����cooki��������
ҳ�澲̬���������ݲ����������shtml������ҳ�澲̬�����ڷ����������ɾ�̬ҳ�棬���������������ݿ���з��ʣ���
��̬CDNͬ����������һ�����ķ�����������ͬ�����ķ���������һ��ҳ��ʱ���ķ��������߲�ͬ�����������ĸ�����ķ�������
��������Ƕ�̬������ôֻ�ܷ������ķ������������þ�̬ҳ�棬Ҳ������Ч����CDN������

js/cssѹ������дhtml�еı����Ȼ��ɼ򵥵���ĸ��ɾ�����еȡ�

css sprint,����ͼƬƴ��һ����ͼ��һ�������ô�ͼ��Ȼ���ڲ�ͬ�ĵط���ʾͼƬ�е�һ�����֡�

������վ�������ܹ�

���������������û���web֮���������תվ���û��ȷ��ʷ������������������ٰ������web��������������ʹ�û�����Ҳ�Ǵ�������⣬web������������ʧ
���⣬����û����ٺ��������û���web������֮������ʱ���û��ķ���ʱ��ͻ�䳤������web�������Ĳ������������Ӷ�ʹ��web���������ܴ����µ�����ͨ������
web���������Խ�������Ѹ�ٸ������������������û�������web�������Ϳ���һֱ��Ч���������ˡ�

���룺ͼƬ���ļ�����ҳ�����ڲ�ͬ�����������ļ���������web���������롣ͬһ���¶���ͬϸ�ڲ�ͬ��������ɣ��ϴ����ز�ͬ��������ǰ��IE�ϴ����ϴ���������Ȼ���ϴ����Ʒ�����������IEֱ�Ӵ��Ʒ���������
����Դ�Ĵ���ͼƬ��ˮӡ����Ƶ������web����������
���ݿ��д���룻
�Ѳ�ͬ�ı�����ݷ��ڲ�ͬ��Ӳ�̣���������ͬʱ���ʶ��Ӳ�̣��൱��ͬʱ�Զ�������д�����֮�������ͬһ��Ӳ������ͬʱ��д��

int/int=int
sprintf("�ļ���С��%01f KB",sizeof(file_size)/1024.0);

ֻҪ�����ӵ��ļ����ڵؾͿ�������

==========================================================================================================================
cookiʵ�ּ�ס����
========================
cgi������д�£�
cgiHeaderCookieSetString("testname","baidu.com",20,"","");//��cooki����Ϊtestname������Ϊbaidu.com��cooki
��������ı���ͷ���У�
Set-Cookie:testname=baidu.com; domain=; expires=Wed, 27-Jul-2016 13:26:49 GMT; path=
�������������һ���ط������cooki�����ڱ����������Ժ������ÿ����������������󶼴������cooki

cgiCookieString("textname",value,sizeof(value));
fprintf(cgiOut,"ok,%s",value);				//��cooki��ȡ������

��������ı���ͷ�У�Cookie:testname=baidu.com
cooki���ܴ洢̫����Ϣ�����ܴ洢����û��۸Ķ�ϵͳ���Ӱ�쵽��Ϣ�����ܰѲ��ܶ�����Ϣ���ܷ�cooki��cooki���ܿ������ʹ�õġ�����˽ģʽ��cooki���ܺͷ���˽ģ�͹���
ie������е�����cooki�����������chrom����Ҫ��cgiHeaderCookieSetString("testname","baidu.com",20,"","127.0.0.1")

session�Ự���������˵�cooki�����˵Ļ�����Ϣ���ݴ洢�ڷ������ˣ����û���ȷ����֮������������������һ������ļ��ܵ�sessionID������������sessionID��ΪCooki����ڵ��Ե�ĳ���ط������sessionID����������������֮�佻����ƾ֤���´���������ʷ�����ʱ�����sessionIDͬ�����ظ��������������������ƾ֤�Ϳ����ж��û��ĸ�����Ϣ��
��һ���û������߶�Ӧһ��洢���򣻿��Զ�дsession�е����ݣ�sessionsessionҪ�趨��Ч�ڣ�ÿ�η��ʷ�������һ�����ڣ����ڲ�����������
�ܶ���վ��¼��ĸ�����Ϣ����session������������Ϣ�Ϳ��Բ����û��޸ġ�
ͬһ�������ϵĲ�ͬ�����֮���session�����໥Ӱ�죬��ͬ�û���sessionҲ�����໥Ӱ�졣

Guid����������MAC��ַ��ϵͳ����ʱ�䣨��ȷ��ϵͳCPUʱ�ӵ������ڣ���������ͬ���Բ�����Guid��һ������ͬ���Բ�ͬʱ�̲�����guid��һ����
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
��ʹ������һ��ȫ�ֱ��������ڳ��������ʱ����Ȼ�ᱻ�ͷŵ���������´�ִ�г���ʱ����ĳ��������ֵ��ô����Ҫ����ת�������γ���������һ�𡣻���ʹ��cooki


atoi(one_row_result[0]);���Խ�MYSQL_ROW���ʹ�����ֵ�ĵĽ�����ء�

����֪��mysql��ѯ����ĸ��ط��д�ʱ����ʹ�û��У���������ط��ڵڼ���

��ʹ�Ľ�ѵ��mysql_stringƴ�ӵ������õı���Ҫ�㹻�����������bug�����Ҳ��ǵ�һ���ˡ�bug��ʾ���£� stack around the variable '������'
������ִ�е�returnʱֱ��������������������Ĵ��붼������ִ���ˡ�

�ֲ�������ȫ�ֱ���������
#include <stdio.h>
int a =0;
int main(int argc, char *argv[])
{
	int a=1;
	if(1)
	{
		int a=2;
		printf("a=%d\n",a);	//������Ϊa=2
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
		printf("a=%d\n",a);	//������Ϊa=1
	}
}
=========================================================================================
˵��ȫ�ֶ���ı����;ֲ�����ı��������ڵ�ʱ��ִ�����ִ������ϱߵ�ֵ���ͽ�ԭ��
========================================================================================
������ÿһ���ֲ�ֻ�ܶ���һ����������������ظ����塣��Ȼ����Ǹ�ֵ����һ���ֲ����Զ�θ�ֵ��
#include <stdio.h>
int a =0;
int main(int argc, char *argv[])
{
	int a=1;
	if(1)
	{
		int a=2;
		//int a=3;	//���벻ͨ�����ظ�����error: redefinition of 'a'
		printf("a=%d\n",a);	//������Ϊa=2
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
}	//������Ϊa=2;a=1	//���ǲ��õ���дϰ�ߣ�VB���������ϲ����һ��if��ߵ�printf��ߵ�aֻ��a=2�е�
a�����Ӱ����a=1���ᡣa=1Ӧ�÷��ڵڶ���if��ߡ�ֻ���Լ���Ӱ�Ķ���Ҫɾ��
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
}	//������Ϊa=2;a=2	�����δ���˵��ͬһ���������ֻ������һ�Σ���ô�ڳ���ִ�й�������ֵ
һֱ��䣬�����ĳ���ֲ����½����˶��壬��ô��ͻ���������ֲ���Ӱ�졣
=============================================================================================
============================================================================================
����gotoʱ���鷳���ˣ�
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
		label:printf("a=%d\n",a);	//������Ϊa=1
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
		label:printf("a=%d\n",a);	//������Ϊa=1
		}
	}
	return 0;
}
==============================================================================================
��������ִ�н������������a=2��
==============================================================================================
#include <stdio.h>
int a =0;
int main(int argc, char *argv[])
{
	int a=1;
	if(1)
	{
		a=2;	//��a���½��и�ֵ�ˣ��������Ϊ2�������������ǿ�������goto���ݱ�����ֵ
			//cgiHeaderLocationͬ������ʵ��������Ĺ��ܣ�������ת��ȥ��û��Я��������ֵ
		goto label;
	}
	if(0)
	{
		if(1)
		{
		label:printf("a=%d\n",a);	//������Ϊa=2
		}
	}
	return 0;
}
cgiHeaderLocation("cgi_htm_mysql.cgi?mode_html=student&action_html=query_select");
���������ض����ִ�й����л���Ҫ���mode_html��action_html��ֵ�Ƿ���student��query_select�ġ�
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
		a=3;	//������¸���䲻�ܳ�ʼ��
		if(1)
		{
		a=5;	//��Ϊ��Щ���û��ִ�У�����û�б���ֵ��
		label:printf("a=%d\n",a);	//������Ϊa=2������������������ִ��goto���ʱ��һ���������ִ��goto���������һ�������
		}
	}
	return 0;
}
===================================================================================================


====================================================================================================
һ������ִ�����֮����������Ļ��߳�ʼ���ı��������ͷţ�����return����ֵ�����������ﷵ�ص�ֵ֮�⡣
mysql_string error_msg�ȶ��ʹ�õı�������һ�ζ�����ʹ��

��(&)�Ͷ�·��(&&)�������Լ���(|)�Ͷ�·��(||)��Ҫ��ȫ��ִ����ʹ�� & | ����þ��ˣ��˴�����gcc��������ʵ��
&Ϊ��λ���������£�
1����������Ѹ�����㣬a&b����aѸ�����㣨��a�ж�Ӧλ��Ϊ1��λ����b�����ó�0��
2������ȡ���ض���λ����ȡ��a�ĺ��λ��a&0xff��
3���ж�����ż����a&1��
|Ϊ��Ϊ���������£�
1���������ö�Ӧ��λΪ1�����ڰ�λ����Ϊ1��a|0xff��
^Ϊ��λ����������£�
1����a�е�����1��Ϊ0��0��Ϊ1��a^(~0)��������Ҳ����~
2��������ֵ��a=a^b;b=b^a;a=a^b��
<<n��ʵ��a*2^n��
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
	printf("ִ�н����x=%d,y=%d",x,y);
	return 0;
}	//ִ�н����x=0,y=1
==========================================
#include <stdio.h>
int main(int argc, char *argv[])
{
	int x=1;
	int y=1;
	if((x=0)&(y=2))
	{
	}
	printf("ִ�н����x=%d,y=%d",x,y);
	return 0;
}	//ִ�н����x=0,y=2
=========================================
#include <stdio.h>
int main(int argc, char *argv[])
{
	int x=0;
	int y=0;
	if((x=1)|(y=2))
	{
	}
	printf("ִ�н����x=%d,y=%d",x,y);
	return 0;
}
ִ�н����x=1,y=2
==============================================
���if���ִ�й������ܰ�ֵȡ�����𣿵�Ȼ���ԣ�������Ҫ�ú��ʵ�����ó�����ȫִ����ȥ��
if((cgiFormString("username_htm",username_cgi,sizeof(username_cgi))!=cgiFormSuccess)|(cgiFormString("pwd_htm",pwd_cgi,sizeof(pwd_cgi))!=
cgiFormSuccess)|(cgiFormString("id_number_htm",student_teacher_number_cgi,sizeof(student_teacher_number_cgi))!=cgiFormSuccess))
============================================================================================
������ִ�м�ʹû�е��ñ���Ҳ��Ҫ�����ţ�
touch_session();
========================================
Ϊ�˱���ϵͳ��ȫ����������ʹ��set_cooki,get_cooki,touch_cooki
�û�A�������A��¼�ɹ������������һ��guid�ַ���A��Ȼ����������棬���ȷ����������guid�ַ���A��Ϊ����ֵ��������ݿ��е�һ������У�һ�����Ļ����û���ID��Ψһȷ�����������ڷ�������
���guid�ַ����ͺ��û�ID��ϵ��һ����,��һ��������������guid����cooki���ظ������A������һ���û�B�������������BҲ��¼�ɹ�ʱͬ�������guid�ַ���B��ͬ���������ݿ��к��û�B��IDһ�����
���ҽ����guid�ַ���B��Ϊcooki�������B�д��ϡ�
�´������A����B������������ʱ���������Ѹ��Ե�cooki�������������������õ�guid�ַ���֮������ݿ��е��ַ����Ƚ�һ�£����һ�¾���Ϊ���û�A���û�B�ĵ�¼�������ܹ�ȷ���û���ID��������֤��
A��B�Ϳ��Բ鿴���޸ĸ�����Ϣ�����޸ġ�������Ϊ����guid�ַ������Ѳ²⣬�����ǻ���MAC��ַ�͵�¼ʱ�䣨��ȷ��CPU�����ڣ��Զ����ɵ�������Ψһ��һ�������Կ����϶�����û�����֮ǰ�ɹ���¼�����Ǹ���

����û�A���û�B����ͬһ�������C��¼��A�ȵ�¼����¼�ɹ����������ݿ�������guid�ַ���A���Լ����û�ID���������������cooki�ַ���A��֮��B�������A�ĸ�����ϢҲ�ǿ��Է��ʵģ���Ϊ��ͬһ�������
��������е�cooki�л�����A��guid�ַ���������guid�����Բ²���������Բ������û��޸�guid���������α��������������Ҫ��������˵�cooki����һ�����ʱ�䣬�������ʱ�����guid�ַ�����ɾ���ˣ�
�����ʱ��֮���û������������������Ͳ����ٴ���cooki�ˡ��������Ͱ������ҳ����ת����¼ҳ�棬��ʾ�û��ȵ�¼������һ�ַ����Ƿ�����ÿ��һ��ʱ��Ͱ����ݿ��д��ʱ��ܳ���guid����ֵ����Ӧ����Ŀ
ɾ������������ʹ������л������û��ϴη���ʱд���cooki��ô�������������cooki�����ݿ������ݱȽ�ʱ�ͻᷢ�����ݿ��в����ڶ�Ӧ���ͬ�����ض��򵽵�¼ҳ�棬��ʾ�û����µ�¼��
�⼸�����̷ֱ�����ü���������ʵ�֣����ȵ�¼�ɹ�ʱ��guid�ַ���д��cooki����һ�����ʱ�䣬����guid�ַ������û���д�����ݿ�Ĺ�����set_cookiʵ�֡����ʸ�����Ϣ֮ǰ��Ҫ��cooki�����ݿ��е����ݽ��бȽ�
���ƥ�佫�û���ȡ�����������get_cookiʵ�֡�ÿ��һ��ʱ��ͽ����ݿ��еıȽϾõ�cooki��������������touch_cookiʵ�֡�
�м���������Ȼ�����嵫����ֵ����һ�£�
���ݿ��з���һ���û����в�ͬ��guid�����������������˵�cooki���ʱ���touch_sessionʱ�仹�̣�û�ȷ����������ݿ��е�����ɾ��������е����ݾ͹����ˣ������û��ٲ���ʱ��Ҫ����д��cooki�ˡ�
�������û��ڶ�ʱ�������˲�ͬ����������������˲�ͬ�ĵ��Ե�¼�ˡ�
������������Դ����cooki���Կ��Խ���¼�ɹ�ʱ���û�������������
cgiHeaderCookieSetString("username","student",20,"","");//����һ��cooki����Ϊusername������Ϊstudent��cooki
cgiCookieString("username",value,sizeof(value));//������Ϊusername��cooki��ȡ�����������ڱ���value��
=========================================================================================================================================================================
void touch_session()
{
	char session_id[50] = {0};
	char mysql_query_string[500]={0};
	char error_message_search[500] = {0};
	//MessageBoxA(0,"ok","ok",0);
	sprintf(mysql_query_string,"delete from t_session where (unix_timestamp(now())-unix_timestamp(last_update_time))>=5*60");
	//�����ݿ��г���5���ӵļ�¼��ɾ��
	MYSQL_QUERY(mysql_query_string,error_message_search);
	if(cgiCookieString("session_string",session_id,sizeof(session_id))==cgiFormSuccess)
	{
		sprintf(mysql_query_string,"update t_session set last_update_time=now() where id= '%s'",session_id);	//�����û���ʱ�䲻�Ǳ���ģ��������ӳ�һ�»��Ǻõģ�˼�������β���ʱ�䲻����5���ӣ����������ʱ����ܻ�û��5���ӵ�ʱ�������һ�β���ʱ�������˾Ͱ��û��ɵ��ˡ�
		MYSQL_QUERY(mysql_query_string,error_message_search);
		cgiHeaderCookieSetString("session_string",session_id,30,"","127.0.0.1");	//ͬ��Ҫ����һ���������cooki���߻�û��5����������˵�cooki�����ˣ�ȡ�����������cooki�û�Ҳ��Ҫ���ߵġ�ͬʱ����ӳ���ʱ��Ҳ��ҪС��5���ӡ���ȻҲ��һ����
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
		if(strcmp(one_row_result[0],"0")==0)	//������������������gui�ַ������������ݿ����Ѿ�û���ˣ�˵����������guid�ı�touch_cooki�ɵ��ˡ���������¿������´���һ��guid�ַ�����Ҳ���Ծ�������������ԭ�еģ��µĺ;ɵ����ʶ�һ����Ϊɶ��ô�϶����������ȡ���������session_string���ַ�������guid�ַ����أ���Ϊ�����session_string�����Ҳ����guid�����ġ�
		{
			need_create_session = TRUE;
		}else									//�������������������ݿⶼ�У��������ݿ��е����guid�ַ�����Ӧ���û�ID�п��ܲ���user_id,����Ҫ�����ݿ��е��û�������һ�¡�
		{
			sprintf(mysql_query_string,"update t_session set user_id = '%s',last_update_time=now() where id= '%s'",user_id,session_id);
			MYSQL_QUERY(mysql_query_string,query_string_result);
			if(strlen(query_string_result)==0)
			{
				need_create_session = FALSE;
				return 1;
			}else
			{
				need_create_session = TRUE;	//û���ĳɹ��������guid�������ɣ������µġ�
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
		createGuid(guid_set);	//����һ��guid�ַ�����
		cgiHeaderCookieSetString("session_string",guid_set,60*30,"","127.0.0.1");	//������ַ�������Ϊsession_string,����д��cooki
		sprintf(mysql_query_insert_string,"insert into t_session(id,user_id,last_update_time) values('%s','%s',now())",guid_set,user_id);
		MYSQL_QUERY(mysql_query_insert_string,error_message_creat);	//������ַ�����Ϊ����ID������user_idһ��д�����ݿ⡣
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
	{		//��һ��cooki����û�н�session_string��cooki�������ȡ����ֵ��һ��cooki����Ȼֻ����һ��ֵ���������Ȼ���Դ�ö�cooki���������ֶ��ǲ�ͬ�ġ�
		MYSQL_RES* get_result;
		MYSQL_ROW one_row_result;
		MYSQL* mysql_tube = mysql_init(0);
		//cgiCookieString("session_string",get_session_id_string,sizeof(get_session_id_string));
		sprintf(mysql_query_string,"select user_id from t_session where id='%s'",get_session_id_string);
		mysql_tube = MYSQL_QUERY(mysql_query_string,error_message);//ȥ���ݿ⿴�����������ȡ�������cookiֵ���ݿ�����û���û�����һ�㶼���еġ����벻�����⡣
		if(strlen(error_message)==0)
		{		
			get_result = mysql_store_result(mysql_tube);
			if(one_row_result = mysql_fetch_row(get_result))
			{
				sprintf(the_session_id_result,one_row_result[0]);
				return 1;	//������������ȡ�������cooki�����ݿ����У������ж�Ӧ�û�������ô����Ϊ����û��ǺϷ��û�
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
ʹ��set_cooki���û���ID�����һ�����У�������е�������һ��guid�ַ���
