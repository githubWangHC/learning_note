.c文件====>.i文件==>.s文件==>.o文件==>可执行文件
     预处理     编译      汇编    链接

#define ADD(a,b) a+b
int c= ADD(a,b);
/*通过宏来传递参数
宏可以不考虑参数的实际数据类型而统统看成字符串
宏不考虑C语法，不以分号为结尾。
预处理时宏已经被替换了；
宏的作用域是在定义了宏以后的区域
条件编译

typedef可以给数据类型起别名
typedef int tni；
typedef int *p；//int *的别名是p
typedef在预处理时不会被替换
typedef的作用域和变量的作用范围一样，定义在局部只能在局部使用。

typedef结合结构体可以简化结构体的使用；
typedef struct student
{
char name[20];
}stu_t;

两种定义结构体变量xxx的方法：
stu_t xxx;
struct student xxx;
定义结构体指针yyy
struct student *yyy;
（*yyy）.name=stu_name;//等价于yyy->name;
struct student stus[2];
yyy = &stus;
yyy++//这yyy自加之后指向stus[1]
structure stu{
int a;
char b;
int c;
}
结构体的大小为12不是9；因为c变量相对于结构体首地址偏移了5，不是自身大小4的整数倍，所以要在b与c之间填充3个字节使得c相对于首地址的偏移量是自身大小的整数倍。填充之后结构体大小为12，结构体中最长的数据类型为4个字节，12也是4的整数倍，不用在c变量后填充了。

共用体指的是共同使用一块内存的结构
union data{
int a;
char b;
int c;
}
在一个时间中只能存储一个数值。
链表可以动态存储数据

对于一个大的工程，不可能将所有的代码放在一起，需要将不变的代码汇编成.o文件，然后将所需要的.o文件和main函数文件链接起来做成可执行文件
main.c文件中有
  #include <stdio.h>
  #include "min.h"
  #include "max.h"
  int main(){
          int a=1, b=2;
          printf("the max is %d\nthe min is %d\n", max(a,b), min(a,b));
  }
而max()函数和min()函数定义在另外两个文件中这两个函数可以预先被编译成.o文件
gcc -c max.c -o max.o
gcc -c max.c -o max.o

max.c文件中有
int max(int a, int b){
        return (a>b)?a:b;
}

min.c文件中有
int min(int a, int b){
        return (a<b)?a:b;
}

最后一起连接编译成可执行文件a.out
gcc max.o min.o main.c

如果main函数中没有包含min.h 以及max.h则在一些低版本的编译器中则出现警告，但仍然是可以得到结果的，在高版本的编译器中则不出现警告
min.h文件中有如下代码：
int min(int a, int b);
max.h文件中有如下代码：
int max(int a, int b);
这两个文件主要是提供一个函数的声明，并且给程序员提示怎么使用这个函数。
make命令可以将上述编译过程批处理
首先用vim编辑一个名叫Makefile的文件文件内容可以如下：
# this is a makefile
main.out:max.o min.o main.c
        gcc max.o min.o main.c -o main.out
max.o:max.c
        gcc -c max.c -o max.o
min.o:min.c
        gcc -c min.c -o min.o
第一行以#开头，是注释
第二行意思是说如果要得到main.out文件则需要哪些文件，有三个
第三行是要得到main.out文件要用什么命令
以下内容类似，但需要将最终得到的main.out文件放在最前面，类似于第递归。
gcc的-c表示编译的意思，-o表示目标文件的意思。
==============
想同时执行两条命令则可以使用&&，表示如果前一条指令执行成功则继续执行ls命令
./main.out && ls
想要查看上一条命令的返回值可以echo $?
int main(int argv, char* argc[])
{
	int i;
	for(i=0; i<argv; i++){
		printf("argc[%d] is %s\n", i, argc[i]);
	}
}
typedef可以给数据类型起别名
typedef int tni；
typedef int *p；//int *的别名是p
typedef在预处理时不会被替换
typedef的作用域和变量的作用范围一样，定义在局部只能在局部使用。

typedef结合结构体可以简化结构体的使用；
typedef struct student
{
char name[20];
}stu_t;

两种定义结构体变量xxx的方法：
stu_t xxx;
struct student xxx;
定义结构体指针yyy
struct student *yyy;
（*yyy）.name=stu_name;//等价于yyy->name;
struct student stus[2];
yyy = &stus;
yyy++//这yyy自加之后指向stus[1]
structure stu{
int a;
char b;
int c;
}
结构体的大小为12不是9；因为c变量相对于结构体首地址偏移了5，不是自身大小4的整数倍，所以要在b与c之间填充3个字节使得c相对于首地址的偏移量是自身大小的整数倍。填充之后结构体大小为12，结构体中最长的数据类型为4个字节，12也是4的整数倍，不用在c变量后填充了。

共用体指的是共同使用一块内存的结构
union data{
int a;
char b;
int c;
}
在一个时间中只能存储一个数值。
链表可以动态存储数据

================
===gdb调试工具===
================
1，gcc -g main.c -o main.out来产生可以调试的文件
2，gdb ./main.out开始进入编译文件中
3，l(或者list)可以显示编译文件
4，start开始调试
5，n（或者next）可以显示下一行运行结果，s则是进入一个函数。
6，bt（stack）可以显示堆栈以及对应的编号
7，f加堆栈的编号可以在不同的堆栈之间切换
8，p加变量名可以显示变量的地址
9，q推出调试
x/3d 0x7fffffffde1c
从0x7fffffffde1c开始打印3个整形数据(四个字节一组)
x/c 0x7fffffffde1c
从0x7fffffffde1c开始打印3个字符数据（一个字节一组）
x/3cd 0x7fffffffde1c
从0x7fffffffde1c开始一个字节一组提取数据，并且按照整形数据的格式来打印
变量名只是一个代号
变量的本质就是内存
指针就是保存内存地址的变量
32位系统地址有32位，8个字节，需要8位16进制数来表示
64位系统地址有64位，16个字节，需要16个16进制数字来表示
===================
==管道与重定向的区别==
===================
>>追加输出重定向
>或者1>位普通重定向
2>位错误重定向
<错误重定向
重定向的意义在于将本来要由键盘输入或者屏幕输出的内容由其他文件输入
管道连接两个命令
ls /etc/ | grep ab
管道将第一个命令得到的结果作为输入输入到第二个命令中。

vim编辑器
命令行模式下输入：set nu可以设置行号
输入:sp FileName.c可以另外打开一个编辑器
crtl+w+上下箭头可以在两个编辑器之间切换
i在光标前方插入，I在整行前边插入
a在光标后方插入，A在整行后边插入
按dd之后再按p可以实现剪切粘贴
====================================

使用scanf保存字符串的时候编译器给两个相邻的指针分配的地址之间相差仅仅为8bytes，如果后输入的字符长度大于8则就会将前者输入的毁掉。	
#include<stdio.h>
#include<stdlib.h>
int main()
{
        int c, i = 0;
        printf("pless input the string cotain all \n");
        char* str_all;
        scanf("%s",str_all);
        printf("pless input the string should delet \n");
        char* str_del;
        printf("str_all bef scanf is:%s\n",str_all);
        scanf("%s",str_del);
        printf("str_all after scanf is:%s\n str_delet is :%s\n",str_all,str_del);
        printf("str_all address is:%d\nstr_delet address is:%d",str_all, str_del);
}
==========================
pless input the string cotain all 
abcd
pless input the string should delet 
1234567890!@#
str_all after scanf is:90!@#
str_delet is :1234567890!@#
str_all address is:-1080614996
str_delet address is:-1080615004
=================================
========指针=====================
=================================

int i=5;
printf("%d\n",&i);
int j=10;
printf("%d\n",&j);	//两个相邻数值的地址相差4
===================
=关于地址的一些血泪史=
=======================================================
=1.指针的定义问题==
=================
错误定义：
========
char* str, str_mv = "hello world \n";
str = str_mv;
在函数‘main’中:
警告：初始化将指针赋给整数，未作类型转换 [-Wint-conversion]
  char* str, str_mv = "hello world \n";
                    ^~~~~~~~~~~~~~~~
警告：赋值时将整数赋给指针，未作类型转换 [-Wint-conversion]
  str = str_mv;
      ^
==============
正确定义=
========
char* str_mv = "hello world \n";
        char* str;
        str = str_mv;

=======
=解析：=
=======
int* 和 int  * （注意空格的位置）是不同的：
对于单变量声明，即一条语句中只声明一个指针变量的情况，没有区别；原因是C语言允许形式的自由性。即以下两种声明方式效果相同：
int  *a;
int*  a;
对于一条语句中声明多个指针变量的情况，有很大区别。
int*  a, b,c;
对于这条语句，常会造成我们的误解：认为声明了三个整型指针。但是正解是：只有a是整型指针，b和c都是普通的整数。 
这里的星号“*”号只是声明变量a的一部分，以上的变量声明相当于：
int *a;
int b;
int c;
如果希望在一条语句中声明多个整型指针，正确的方式应该是：
int *a, *b, *c;

=====================================================
#include<stdio.h>
int main()
{
        int c;
        int chose = 1;
        char* str_mv;
        char*  str_const;
        if(chose = 1)
        {
                c =getchar();
                *str_const = c;
                str_const++;
                c =getchar();
                *str_const = c;
        }
        putchar(*str_const);
        str_const--;
        putchar(*str_const);
        printf("\n%d\n",str_const);
}/*以上输入如果为ab则输出ba符合预期 
若将*号处的chose = 1改为chose ==1则输出以下错误
Segmentation fault (核心已转储)
===============================
#include<stdio.h>
int c = 0, i = 0;
char a;
char* hex_str = &a;
int main()
{
        while((c = getchar()) != EOF)
        {
                *hex_str = c;
                putchar(*hex_str);
                hex_str++;
                i++;
        }
        hex_str--;
        while(i >= 0)
        {
                putchar(*hex_str);
                hex_str--;
                i--;
        }
}
本代码可以实现输入与输出任意长度字符串。但是注意字符指针要定义在main（）函数之外。
如果定义在main（）函数内部则前者的修改不会保存在字符指针所指向的存储空间中
===============================================================================================
#include<stdio.h>
int main()
{
        int c;
        char* str_const = NULL;
        for(int i = 0; i < 1; i++)
        {
                c =getchar();
                *str_const = c;
                putchar(*str_const);
        }
}

=====================================================
*的两个用途：声明变量与取数据
1.int* iPtr = &i;//星号和类型在一起就是声明指针,说明该指针所表示变量的地址，也可以写为int *iptr iPtr中的数值是&i；但是所有变量的地址都是int型的，为了知道某个地址中存入的数据类型故有此定义。
2.声明指针也可以取数据
printf("%d",*iptr);既然如此那么也可以用*iptr=100;来更改变变量i的数值，过程是把100存入iPtr所指向的变量。

上述两者的区别是前者前面有类型。
int i=10;
char str[100];	//这个地方不能用char * str;因为没有初始化。数组在定义的时候就已经分配了内存空间。也可以char* str="rupeng";来定义字符串。
itoa(i,str,10);	//整数转换为ascii，变量分别表示：要转到、转入的、十进制；因为同一个字符ascii中不同进制下的表示不一样（详见ascii转换）；int i=atoi(strs1),整字符串转换为整数（为何没有进制？）

定义在#include<stdlib.h>
itoa()函数的原型为： char *itoa( int value, char *string,int radix);
itoa()函数有3个参数：第一个参数是要转换的数字，第二个参数是要写入转换结果的目标字符串，第三个参数是转换数字时所用的基数。在例中，转换基数为10。10：十进制；2：二进制...

printf("%s",str);//对于%s只需要给出字符串的首地址就可以打印了
=============

====================
=字符串的初始化方法=
====================

char str1[]={"I am happy"};
char str2[]="I am happy";
char str3[]={'I',' ','a','m',' ','h','a','p','p','y','\0'};
char str3[]={'Iamok',' ','a','m',' ','h','a','p','p','y','\0'};	//则第一个字符取最后一个k
另外还有一种初始化的方法
char strNULL[]={0};
的C语言规定在定义字符串时0表示\0，因此strNULL里边什么也没有，当然也没有0。如果想要表达里边有一个0怎么办呢？char str0[]={'0','\0'};或者char str0[]={'0',0};在某些编译器中NULL是0的宏定义。
那么指针是否有类似的写法？在cgimysql.c时出现的写法。sprintf(str,"");函数好像必须要str字符串变量初始化
上述三个字符串的长度均为11
char str4[10]={ 'I',' ','a','m',' ',‘h’,'a','p','p','y'};
str4的长度为10，如果将定义时的字符串长度10去掉则这种初始化方法是不对的，因为既没有结束符也没有字符串长度，电脑不知道最大多少个字符。即使指定了字符串的长度为10，同样不对，因为正确的字符串长度应该为11。这两种情况下都会出现“烫烫烫”。这个地方定义的字符串长度要大于等于{}里边的字符数（即一定要给\0留一个位置）。

============
字符串的赋值
============
char str5[10];
str={ 'I',' ','a','m',' ',‘h’,'a','p','p','y'}；
这种方法有效性存疑！！
=======================
字符指针变量初始化方法=
=======================

char* str6="I love China";

或者  char* str7;
      str7="I love China";
	str7="我很好";

因此如果要将一个获得的字符串存入字符串变量中最简便的方法还是指针。	

字符串数组或者字符指针变量的输出都用
printf("%s\n",str);
%s 表示输出一个字符串，给出字符指针变量名str（对于第一种表示方法，字符数组名即是字符数组的首地址，与第二种中的指针意义是一致的），则系统先输出它所指向的一个字符数据，然后自动使str地址自动加1，使之指向下一个字符...，如此，直到遇到字符串结束标识符" \0 "。
char str[]={0};	//0代表\0
char str[]={'a'};
ASCII表示字符的整数表示

关于ASCII码
int a=65;
char b='A';
printf("%d,%c",b,a);	//输出结果是65,A

int a='A';
char b=65;
printf("%c,%d",b,a);	//输出结果是A，65

char str[]={65,66,67,68,0};
printf("%s",str);	//输出结果是ABCD,如果没有最后的0，则显示烫烫烫。

	char b='65';
	printf("%c",b);	//输出结果为最后一位5
==========================================================================================================

1、数组在内存中是连续的内存区域： printf("%d,%d,%d\n",&(num[0]),&(num[1]),&(num[2])); 打印每个元素的内存地址

2、int *ptr = &(num[0]);//获取第0个元素的内存地址

int *ptr = num;//效果同上，一般这样用，数组元素的名字就是“第0个元素的内存地址”一般用数组名代表“首元素的地址”

3、字符串也就是“以\0结尾的char内存区域”，所以一样的：

char  str1[] = "hello";

char *str2 = str1;
==================
=字符串与指针的区别=
==================
#include <stdio.h>
int main(){
        char str1[] = "hello";
        char *str2 = "word";
        char str3[10];
        printf("input value\n");
        scanf("%s",str2);
        printf("str1 is %s\nstr2 is %s\nstr3 is %s", str1, str2,str3);
}
如果scanf函数的存储地址为str1或者str2则存储键盘输入的数据是没有问题的，如果是str2则出现问题如下：
input value
alskdfj
Segmentation fault (核心已转储)
=========
(gdb) p &str1
$1 = (char (*)[6]) 0xbfffefc6
(gdb) p &str2
$2 = (char **)     0xbfffefcc
(gdb) p &str3
$3 = (char (*)[10])0xbfffefbc
解释：变量str1与str2与str3的地址均较大，以bffffe开头，都是存储在栈中的

(gdb) p str1
$4 = "hello"
(gdb) p &str1
$5 = (char (*)[6]) 0xbfffefc6
(gdb) x/6c 0xbfffefc6
0xbfffefc6:	104 'h'	101 'e'	108 'l'	108 'l'	111 'o'	0 '\000'

(gdb) p &str2
$6 = (char **) 0xbfffefcc
(gdb) x/5c 0xbfffefcc
0xbfffefcc:	-108 '\224'	-123 '\205'	4 '\004'	8 '\b'	-68 '\274'
(gdb) p str2
$7 = 0x8048594 "word"
(gdb) x/5c 0x8048594
0x8048594:	119 'w'	111 'o'	114 'r'	100 'd'	0 '\000'
字符串变量中的内容是字符串地址之后的一段栈空间中，所以可以从字符串变量开始连续打印其中的数值
p &str2打印的是指针的地址，由于地址较大所以str2变量的地址也是在栈空间，但是指针所指向的内容的地址却是在低地址段中（代码段）中的，所以不能将输入的地址存储在代码段中

        char *str1;
        char *str2 = "hello";
        char str3[10];
        char str4[10] = {0};
(gdb) p str1
$1 = 0xbffff07c "g\362\377\277r\362\377\277\207\362\377\277\230\362\377\277\260\362\377\277\317\362\377\277\351\362\377\277\372\362\377\277\021\363\377\277%\363\377\277\065\363\377\277C\363\377\277[\363\377\277r\363\377\277\223\363\377\277\237\363\377\277:\372\377\277c\372\377\277s\372\377\277\301\372\377\277\314\372\377\277\342\372\377\277\376\372\377\277^\373\377\277m\373\377\277\221\373\377\277\243\373\377\277\333\373\377\277\364\373\377\277\b\374\377\277\031\374\377\277.\374\377\277\252\374\377\277\305\374\377\277\324\374\377\277\335\374\377\277\356\374\377\277\005\375\377\277\r\375\377\277\037\375\377\277.\375\377\277Z\375\377\277i\375\377\277\203\375\377\277\271\375\377\277\066\376\377\277U\376\377\277x\376\377\277\217\376\377\277\232\376\377\277"...
(gdb) p str2
$2 = 0x8048564 "hello"

(gdb) p str3
$3 = "\004\b\001\000\000\000t\360\377\277"
(gdb) p &str3
$4 = (char (*)[10]) 0xbfffefae
(gdb) p str4
$5 = "\000\000\000\000\000\000\000\000\000"
(gdb) p &str4
$6 = (char (*)[10]) 0xbfffefa4

说明没有初始化的指针存放在栈中，初始化的指针存放在数据段中，
数组无论是否初始化均存储在栈中
        scanf("%s", str1);
        printf("%s", str1);
可以用str1，str3，str4但不能用str2。如果用str3和str4如果输入大于10个字符就会报错
用str1则不会,但如果数据太长大于0xbfffefae-0xbffff07c则可能仍然会出现覆盖原先数据的情况
如果真的想实现输入、存储、输出任意长度的数据可能需要结构体。
========================================================================================================
=指针的移动=
============
int num[] = {33,55,88,99,22};
int *p = num;		//C语言规定数组的名字就是第0个元素的地址。若printf("%s",p)则打印字符串，打印字符用%c
printf("%d\n",*p);
p++;			//因为p是int类型的指针、所以向后移动1*4个字节
			//不应该减，只能加。
printf("%d\n",*p);
p=p+2;			//移动2*4个字节
			//等价于p[2]。但是不能写成num=num+2.本质上数组名永远指向同一个地址，是指针常量。而指针可以
指向不同的地方，所以可以理解为指针变量。变量可以运算，常量不可以。
printf("%d\n",*p);
p--;
printf("%d\n",*p);

1、每次加一就是指针向前移动指针类型对应的字节数。减法则是相反的。ptr--;
可以用中括号来代替地址的计算

应用实例：
    char s1 [] = "hello rupeng.com";  
    char *p = s1;
    p=p+2;		//char是一个字节，向后移动2*1 个字节
    printf("%s\n",s1);
    printf("%s\n",p);

2、指针相减代表计算两个指针的距离，得到相距数据类型的长度（不是字节数，而是“字节数/数据类型的字节长度”）。
    int nums[] = {11,22,33,44,55};
    int iP1 = nums;//说明此时不加*也是可以的。
    int iP2 = nums;
    iP2=iP2+3;
    printf("%d\n",iP2-iP1);
    //同类型指针相减，得出的是相距的数据类型的长度
======================================
                c =getchar();
                *str_const = c;
                str_const += 2;
                c =getchar();
                *str_const = c;
                str_const -= 2;

=================================================================================================================

对于计算机而言，存储的都是“字节数据”，帮你存、帮你取，从哪儿读（指针）、读多少（数据类型）、怎么解读这些数据你随意。

int i1=88888888;	//88888888、二进制表示：00000101，01001100，01010110，00111000
    short *p2 = &i1;	//(short *)&i1;本来i1是int类型的数据，四个字节，但是这个地方用short类型，有两个字节。
    printf("%d\n",*p2);	//得到22072；其二进制是：		    01010110，00111000
    p2++;		//这个地方指针向前移动1个字节
    printf("%d\n",*p2);	//得到1356；其二进制是：00000101，01001100
			//指针从个位开始。
    char* ch = &i1;
    printf("%d,%d,%d,%d\n",ch[0],ch[1],ch[2],ch[3]);		//写法很妙
    printf("%d,%d,%d,%d\n",*(ch+0),*(ch+1),*(ch+2),*(ch+3));	//同样很妙，//ch[n]→*(ch+n)
    
=================================================================================================================

1、如果在作用域内，变量以数组形式声明，则可以使用sizeof求数组大小：
在同一个函数内
{
	int num[]={5,89,33,25,99};

	int len = sizeof(num)/sizeof(int); //可以计算出num的长度为5；
}
但是在调用函数
void printEach(int *nums)
中使用
int len = sizeof(num)/sizeof(int)
来计算nums的长度是得不到传入数组的长度的，因为在编译时定义函数没有接收到main函数传入的数组，在编译时计算机只知道nums是一个int类型的指针，因此len永远都是1；

2、sizeof是编译器在编译的时候计算的，无法动态计算。因此对于int *或者将数组传递给函数，那么就无法使用sizeof获取大小了。即使函数声明中写着int[]也不行（为了避免误解，不要在参数中声明数组类型）。因此一般给函数传递数组/字符串的时候都要求额外传递“长度”参数，因为函数内部也不知道“有多长”。memcpy(void * restrict, const void * restrict, size_t)

char hex_username[200]={0};
char hex_password[200]={0};
mysql_hex_string(hex_username,username_get,strlen(username_get));	//这个地方只能用strlen不能用sizeof因为前者表面变量实际用掉的内存，而后者表面定义的内存大小，如果定义的内存过大容易造成程序崩溃									
mysql_hex_string(hex_password,password_get,strlen(password_get));
sprintf(html_to_mysql,"insert into t_employees(name,number) value(0x%s,0x%s)",hex_username,hex_password);	//0x，零xyz的x
====================================================================================================================
char arr[10] = "What?";
int len_one = strlen(arr);
int len_two = sizeof(arr); 
len_one和len_two的结果分别为5 and 10
点评：sizeof返回定义arr数组时，编译器为其分配的数组空间大小，不关心里面存了多少数据。strlen只关心ｓ实际存储的数据内容，不关心空间的大小和类型。
==================
数组的复制       =
==================
int main(int argc, char *argv[])
{
    char str1[] = "hello rupeng";
    char str2[30];
    //strncpy(str2,str1,sizeof(str1)/sizeof(char));
    strcpy(str2,str1);
    //strncat
    printf("%d\n",strlen(str1));
    //strlen(str1);
    printf("%s",str2);
    return 0;
}
数组的复制可以用strncpy和strpy，前者必须传入被传入数组的长度，后者不用它们通过strlen来判断结尾。同理：strcpy→strncpy；strcat→strncat
不是可以通过strlen获取字符串长度吗？安全性问题，strlen根据'\0'判断字符串结束，恶意攻击者会构造一个不包含'\0'的字符串，然后让数据写入数组之外的程序内存空间，从而进行破坏。
在这种情况下爱strncpy 和strpy都不能正常使用。

==============================================================================================================
==主函数与被调用函数之间的关系===
=================================
#include <stdio.h>

int clc(int num, int *ge, int *shi, int *bai)
{
	if(num<0||num>2999)
	{
		printf("您输入的三位数不正确\n");
	}
	*ge=num%10;
	*shi=(num/10)%10;
	*bai=(num/100);
	return 1;
}

int main(int argc, char *argv[])
{
	int main_ge,main_shi,main_bai;
	int number = 356;
	if(clc(number, &main_ge,&main_shi,&main_bai))
	{
		printf("三位数的各个十百位分别是：%d,%d,%d",main_ge,main_shi,main_bai);
	}
	return 0;
}
======================
===上述例子简化一下===
======================
#include <stdio.h>
#include <string.h>


int func_int(int *s) 
{
	*s = 10;
	return 0;
}
int main(int argc, char *argv[])
{
	int t_main;
	func_int(&t_main);
	printf("%d",t_main);
	getchar();
	return 0;
}
====================================传整数======
int func_char(char* s_char)
{
	*s_char='a';
	return 0;
}
int main(int argc, char *argv[])
{
	char t_main;
	func_char(&t_main);
	printf("%c",t_main);
	getchar();
	return 0;
}
===================================传字符======
int func_string(char* s_string)
{
	char* string="ab";
	strcpy(s_string,string);
	return 0;
}
int main(int argc, char *argv[])
{
	char t_main[10]={0};
	//char* t_main;		//不知道怎么初始化
	func_string(t_main);	//当数据需要从main函数传到被调函数时可以func_string("input_string");
	printf("%s",t_main);
	getchar();
	return 0;
}
===============================传字符串===========



从上述例子可以看出以下几点：
1.	主函数与被调用函数中变量不能互相使用，均需各自定义，变量名可以不同，但是函数名必须相同。
2.	主函数与被调用函数之间传递的变量类型必须相同，自己所定义的函数只能传回一个数值。可以通过指针变量传递数值。

1、栈空间：出了函数范围，内存空间自动释放。定义的局部变量int、局部数组等都在栈空间中。
栈空间的尺寸有最大限制，不适合分配大空间使用；栈空间出了函数范围就释放，不适合要给其他地方使用的内存。好处：不用手动释放内存。

2、注意：一般不要把局部变量的指针做为返回值返回；因为局部变量在出了函数之后要释放。

    int * nums=(int*)malloc(sizeof(int)*3);	//malloc返回值是oid*
    nums[0]=9;
    nums[1]=8;
    nums[2]=7;


while(*str!='\0')
    {
        str++;
        strlen++;
    }
    int substrlen=0;

指针是要自加的。

=============================================
内存的初始化
=============================================
1、创建一个内存区域的时候，内存中的数据是乱七八糟的（可能是其他代码用过后遗留的数据）：

char ch1[20];

printf("%s\n",ch1);

这个代码打印出来的可能就是乱码，因为printf的%s是“打印一直遇到'\0'”。

2、解决问题的两种方法：

1）使用memset函数
memset(void *,要填充的数据,要填充的字节个数)

char ch1[20]; 
memset(ch1,0,sizeof(ch1));
printf("%s\n",ch1);
输出的字符串是空，就是没有任何字符。因为0表示字符串的结尾，
思考：如果把memset(ch1,0,sizeof(ch1)); 改成memset(ch1,1,sizeof(ch1)); 为什么会多输出点东西？因为没有输入终止符号'\0'或者0所以priintf函数不知道什么时候结束，所以最后就输出乱码了。感觉没用呀，为何不在定义的时候就初始化，还再来个这个函数。
2)char ch1[20] ={0}; 对于长度为20字节的这段内容全部填充为0。原理：int num[10]={3,5};//前两个分别填充3和5，之后都填充为0。因此char ch1[20] ={0}; 等价于char ch1[20] ={}; 

===============
====结构体======
===============
1、结构体就是一大块内存，对他进行格式化的存储、读取。
=====结构体定义====
struct student	//这两个要跟在一起
{
int age;
char* name;
double height;
};

======================
定义结构体变量xxx的方法：
stu_t xxx;
struct student xxx;
=======
typedef可以给数据类型起别名
typedef int tni；
typedef int *p；//int *的别名是p
typedef在预处理时不会被替换
typedef的作用域和变量的作用范围一样，定义在局部只能在局部使用。

typedef结合结构体可以简化结构体的使用；
typedef struct student
{
char name[20];
}stu_t;
==============================================================================================
每次使用结构体的时候都要struct student ，比如struct student p1={0};sizeof(struct student); 太麻烦。
typedef int zhengshu;可以给int取一个别名zhengshu。因此也可以用typedef struct student stu;把stu定义为struct student的别名
当然最常用的简化写法：
typedef struct student
{
int age;
char* name;
}stu;
===结构体引用===========
xxx.name = "全智贤";
xxx.age=30;
printf("%s的年龄是%d\n",xxx.name,xxx.age);

通过结构体指针yyy来引用
struct student *yyy;
（*yyy）.name = "全智贤";	//等价于yyy->name;
===结构体数组===
struct student stus[2];
yyy = &stus;
yyy++				//yyy自加之后指向stus[1]
=====结构体大小=========================
eg1
printf("%d\n",sizeof(Person));
为什么是16？
对于32位系统来说int类型的数据为4个字节，所有的指针变量都是int类型。double为8个字节。用字符指针的好处是可以不用管输入字符的长度。对于int、short等放到结构体中保存是占用对应的字节，但是对于char*等，则只是保存它的指针（地址）。

eg2
structure stu{
int a;
char b;
int c;
}
结构体的大小为12不是9；因为c变量相对于结构体首地址偏移了5，不是自身大小4的整数倍，所以要在b与c之间填充3个字节使得c相对于首地址的偏移量是自身大小的整数倍。填充之后结构体大小为12，结构体中最长的数据类型为4个字节，12也是4的整数倍，不用在c变量后填充了。
================
4、结构体的赋值=
================

Person p1={0};
p1.name = "全智贤";
p1.age=30;
Person p2 = p1;
printf("%d,%d\n",&p1,&p2);
printf("%s的年龄是%d\n",p1.name,p1.age);

p1和p2的地址不一样，结构体赋值是“复制一份”，复制完成之后如果更改了p1结构体中的数据那么p2中的数据不会再改了。如果要节省内存，或者让一个数据真正做到同步那么就要用到结构体的指针。

Person* p3 = &p1;
printf("%s,%d\n",p3->name,p3->age);//对于结构体指针取成员的值要使用->
===================
链表

=================
void类型指针=====
=================

1、void *表示一个“不知道类型”的指针，也就不知道从这个指针地址开始多少字节为一个数据。所有的指针变量都是int类型，和用int表示指针异曲同工，只是更明确是“指针”。因此void*只能表示一个地址，不能用来&取值，也不能++--移动指针，因此不知道多少字节是一个数据单位
2、void *就是一个不能动的“地址”，在进行&、移动指针之前必须转型为类型指针
3、void *的用途：在只知道内存，但是不知道是什么类型的时候。

void zeromem(void *data,int byteSize)
{
char * ptr = (char *)data;	//char就是一个字节，而计算机中是以字节为单位存储数据的，所以可以将任何类型的数据转换为char类型进行初始化
int i;
for(i=0;i<byteSize;i++)
{
*ptr=0;
ptr++;				//因为ptr是char*，所以逐字节移动
}
}
 
int main(int argc, char *argv[])
{
    int nums[20];
    memset(nums,0,sizeof(nums));	//用memset函数进行初始化
    
    Person p1;
    zeromem(&p1,sizeof(Person));	//用void指针调用函数进行初始化
     
    return 0;
}


==================
void类型的函数====
==================
￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥
#include <stdio.h>

typedef void (*intFunc) (int i);

void foreachnum(int *number, int length,intFunc funct)	//这个地方intFunc funct表示传入一个符合intFunc类型的函数，而foreachnum则用这个传入的函数处理数据
{
	int i=0;
	for(i=0;i<length;i++)
	{
		funct(number[i]);
	}
}

void print(int j)
{
	printf("%d\n",j);
}

int main(int argc, char *argv[])
{
	int nums[]={111,222,333,444,555,};
	foreachnum(nums, sizeof(nums)/sizeof(int),print);	//这个地方传入prin函数很重要，且这个print函数要符合intFunc的类型。
	return 0;
}
$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
===================================================================================================

==================================
==================================
GTK

MessageBox(NULL,TEXT("世界你好")//正文//,TEXT("问好")//标题//,MB_OK);
TEXT是一个宏，字符串需要用""包起来
#define	MB_OK	0x00000000L
#define	MB_OKCANCEL	0x00000001L
#define	MB_ABORTRETRYIGNORE	0x00000002L
#define	MB_YESNOCANCEL	0x00000003L
#define	MB_YESNO	0x00000004L
#define	MB_RETRYCANCEL	0x00000005L

用逻辑求“或”可以将按钮与图标结合起来
MessageBox(NULL,TEXT("世界你好"),TEXT("问好"),MB_RETRYCANCEL|MB_ICONEXCLAMATION);

#define MB_ICONHAND	0x00000010L
#define MB_ICONQUESTION	0x00000010L
#define MB_ICONEXCLAMATION	0x00000010L
#define MB_ICONASTERISK	0x00000010L

通过MessageBox的返回值来判断用户点击了什么
int retu2=MessageBox(NULL,TEXT("你是成年人了"),TEXT("结果"),MB_RETRYCANCEL|MB_ICONHAND);

#define IDOK	1
#define IDCANCEL	2
#define IDABORT	3
#define IDRETRY	4
#define IDIGNORE	5
#define IDYES	6


if(IDYES==retu1){
		int retu2=MessageBox(NULL,TEXT("你是成年人了"),TEXT("结果"),MB_RETRYCANCEL|MB_ICONHAND);
		if(IDRETRY==retu2){
			goto lable1;
		}
	}

if中写IDYES==retu1是高级写法，不能给宏把被判断值写在左边，把变量写在右边。

如果一个函数不知道怎么用，则选中它右键点击go to reference of“”

在Microsoft Visual C++中的工具条中选择Build，在窗口中可以选择调试的选项为Debug版本还是Release版本。
====================================================================================
void gtk_widget_show(widget)：显示。int整数

void gtk_widget_hide(widget)：隐藏

void gtk_widget_set_sensitive(widget, sensitive)：禁用。boolean（布尔类型）只有true（真/是）、false（假/否）两个值。

void gtk_widget_set_size_request(int widget,int width, int height) 设置“建议的”大小

void gtk_widget_destroy(widget)销毁

gtk_window_set_title(window,"红尘若梦");

gtk_window_set_resizable(window, resizable);设置是否可以缩放。

gtk_window_set_position(window, GTK_WIN_POS_CENTER_ALWAYS)显示在屏幕正中间

void gtk_window_maximize(window)最大化

void gtk_container_add(container, widget)：从container继承。比如可以将子控件widget添加到window中，window中只能有一个子控件。
====================================================================================
GTK命名规则
一般以gtk开头
gtk_widget_代表这个函数可以应用于GtkWidget及所有子类。gtk_container_代表这个函数可以应用于Gtk_container_代表这个函数可以应用于GtkContainer及所有子类。
========
=window=
========
	gtk_init(NULL,NULL);
	GtkWindow* windownew = gtk_window_new(GTK_WINDOW_TOPLEVEL);
	gtk_widget_show(windownew);
	g_signal_connect(windownew,"destroy",gtk_main_quit,0);
	
	gtk_main();
	gtk_window_set_title(window,"红尘若梦");显示的必须是字符串如果要显示数字要转换
	char time[20]={0};
	itoa(i,time,2);
	gtk_window_set_title(windownew,time);

================================
添加文本框/输入框（GtkEntry）===
================================
GtkEntry *××× = gtk_entry_new();//不能忘了show
gtk_widget_show(×××);
gtk_container_add(window,×××);//不能忘了添加到容器中
void gtk_entry_set_max_length(entry, int max)设置输入最大宽度
void gtk_entry_set_text(entry, text)设置输入框的文本
const char * gtk_entry_get_text(int entry)获得输入框的文本值
void gtk_editable_set_editable(int editable,boolean is_editable) 设置是否可编辑，实现“只能看不能改”的效果，和Label的区别是可以选择、复制。
void gtk_entry_set_visibility(int entry,boolean visible)当把visible设置为FALSE的时候为密码框风格

=======================
添加按钮（GtkButton）==
=======================

GtkButton *××× = gtk_button_new();
gtk_button_set_label(×××,"点我呀");
gtk_widget_show(×××);
gtk_container_add(window,×××);
g_signal_connect(btn,"clicked",function,NULL);触发点击

添加标签（GtkLabel）
GtkLabel *××× = gtk_label_new("×××");
使用void gtk_label_set_text(label, str)修改Label的文本内容
gtk_widget_show(×××);
gtk_container_add(window,×××);

添加下拉框（ComboBoxText）
1、GtkComboBoxText从GtkComboBox继承
int gtk_combo_box_text_new()创建一个ComboBoxText；
2、void gtk_combo_box_text_append(combo_box,char * id, char * text) 附加一个文本，并且给它设定一个自定义id（字符串类型）。
char* gtk_combo_box_get_active_id(combo_box)得到选中行的自定义id
gtk_combo_box_set_active_id(combo_box,char * active_id)设定自定义id等于active_id的项被选中
void gtk_combo_box_text_remove_all(int combo_box)清除所有内容
3、当选中一行的时候会发出“changed”信号

添加图片控件（image）
1、int gtk_image_new()创建一个图片控件支持gif、jpg、png，不支持bmp。
2、void gtk_image_set_from_file(GtkImage* image, char * filename)从文件全路径为filename的文件中加载图片。注意文件路径的转义问题，“\\”因为“\”有特殊含义。
3、gtk_image_set_from_stock(GtkImage* image char * stockid, int size)，从GTK内置的图库加载图片，stockid可选值为GTK_STOCK_*；内置图片有不同尺寸的多套，size用来指定用哪个尺寸的，可选值GTK_ICON_SIZE_*
===============================
两种布局方式

盒子布局（GtkBox）可以放很多控件
int gtk_box_new(int orientation, int spacing)创建一个盒子布局。orientation方向：另外还有GTK_ORIENTATION_HORIZONTAL、GTK_ORIENTATION_VERTICAL两个可选值；spacing：单元格之间的间隙 ORIENTATION：方向
void gtk_box_pack_start(int box//着一个盒子//, int child//哪个属性//,boolean expand, boolean fill, int padding)：往盒子开头添加元素。child被添加的控件。后三个参数不讲，一般FALSE,FALSE,0即可。

网格布局
GtkGrid *grid1 = gtk_grid_new();
void gtk_grid_attach(grid, child, int left,int top, int width, int height);
=====================================================================================================
窗口、盒子的新建方式与其他方式不同：括号里边要有参数(GTK_WINDOW_TOPLEVEL)、(GTK_ORIENTATION_HORIZONTAL,0)
文本框、按钮、标签、盒子添加到窗口的方法都是gtk_container_add(window,×××);
文本框、按钮、标签添加到盒子的方法都是gtk_box_pack_start(box,child,FALSE,FALSE,0);
文本框、按钮、标签添加到网格的方法都是gtk_grid_attach(grid, child, int left,int top, int width, int height);

GtkAbc *×××=gtk_abc_new，	创建一个abc控件并且返回它的标志。
gtk_AAA_get_BBB，从AAA类型的控件的标识获得控件BBB属性的值。
gtk_AAA_set_BBB，设置指定标识的类型为AAA的控件的BBB属性。

=======================================================================================================
==GTK深入==
===========

如果一个变量要在不同的函数内进行使用则必须声明为全局变量，即在include之下就声明变量。
而如果一个函数要在main函数之后定义，并且main函数要用这个函数，则也要在main之前声明函数，声明方法就是将定义的大括号去掉，并且在语句之后加上分号。
在include下声明的函数(int a)使用时就直接赋值就行了,不要再int a = 1;直接a=1;
editPluss F2可以输入已输入的变量等。
一个变量即使只是在次级函数使用，但要显示在主函数，还是要全局声明的。unit4_time.c
gtk_widget_set_sensitive(btnInstall,checked);
===================================================================================

1、 guint g_timeout_add (guint interval, GSourceFunc function, gpointer data); 增加定时器，每隔interval毫秒执行一次function指向的函数。 GSourceFunc 函数指针的定义： gboolean (*onTimer)(gpointer user_data);如果return TRUE则下次再执行，返回FALSE则停止定时器。

2、获得当前时间小时、分钟、秒的函数：
	time_t sysTime;
	time(&sysTime);
	struct tm* bjtime = localtime(&sysTime);
	int hour = bjtime->tm_hour;
	int min = bjtime->tm_min;
	int sec = bjtime->tm_sec;
====================================
LED时钟
====================================
	char file_sege[60]={0};	//char* timenow 是不行的。
	sprintf(file_sege,"LED_image\\%d.png",sec%10);//如果工程和文件在一个目录可以用这样的写法写路径
							sprintf函数的使用
	gtk_image_set_from_file(secge,file_sege);	//文件名的引用

============================================

1、int gtk_toolbar_new()创建工具栏容器

2、void gtk_toolbar_insert (GtkToolbar *toolbar, GtkToolItem *item, gint pos)将工具栏项添加到工具栏，item：工具栏项，pos插入的位置

工具栏项有按钮、下拉菜单按钮、分隔栏、开关等复杂内容，这里不介绍，只介绍简单常用的GtkToolButton。

GtkToolItem *gtk_tool_button_new (GtkWidget *icon_widget, const gchar *label);创建GtkToolButton。icon_widget为显示的控件id，可以在按钮上显示其他控件，一般传0；label为标题。

void gtk_tool_button_set_stock_id (GtkToolButton *button, const gchar *stock_id);设置按钮上显示的图片。

响应点击只要连接"clicked"信号即可。


    GtkToolItem* btn1 = gtk_tool_button_new(NULL,"保存");
    gtk_tool_button_set_stock_id(btn1,GTK_STOCK_SAVE);
    gtk_toolbar_insert(toolbar,btn1,0);
    gtk_widget_show(btn1);

======================================================================================
数据库
======================================================================================

数据库、表、列
书放在书架上，碗放在橱柜中，不同类型的资料放到不同的“格子”中，将这种区域叫做"表（table）"
一张表中有列/字段

localhost、表的命名T_students	表名列名不要有空格。

主键有两种选用策略：业务主键和逻辑主键。业务主键是使用有业务意义的字段做主键，比如身份证号、银行账号等；逻辑主键是使用没有任何业务意义的字段做主键，完全给程序看的，业务人员不会看的数据。因为很难保证业务主键不会重复（身份证号重复）、不会变化（帐号升位），因此推荐用逻辑主键。

文本类型；

CHAR(*)：最多255个字节的定长字符串，它的长度必须在创建时指定
VARCHAR(*)：最多255个字节的可变长度字符串，它的长度必须在创建时指定
TEXT：最大长度为64K字符的变长文本	64×1024个字符
TINYTEXT：最大长度为255字符的变长文本
MEDUIMTEXT：最大长度为16K字符的变长文本
LONGTEXT：最大长度为4GB字符的变长文本

整数：(考虑数据取值后选择尽可能小的类型)

tinyint：1字节。有符号值：-128 到127；无符号值：0到255	有无符号可以在见表时选
smallint：2字节。有符号值：-32768 到32767；无符号值：0到65535
mediumint：3字节。
int：4字节
bigint：8字节

小数(需要指定长度和小数点，也就是显示宽度和小数位数)：

decimal：精确存储的小数，在内部用字符串存储，适合金额等要求精确的类型。别名：NUMERIC
float：4字节，单精度。会近似存储(*)，效率比decimal高。
double：8字节，双精度。会近似存储(*)，效率比decimal高。

日期时间：

DATE：4字节。范围：1000-01-01——9999-12-31	只保存日期
TIME：3字节。范围：-838:59:59——838:59:59	只保存时间
DATETIME：8字节。范围：1000-01-01 00:00:00——9999-12-31 23:59:59	日期和时间

二进制大数据：图片/扫描件，网站只保存文件路径不保存文件

TITYBLOB：最大长度为255字节
BLOB：最大长度为64KB
MEDIUMBLOB：最大长度为16MB
LONGBLOB：最大长度为4GB

1、SQL语句中字符串一般用单引号。

2、SQL语句是大小写不敏感的。

3、NavCat中找到执行SQL语句的地方“查询”→“新建查询”，编写SQL后点击【运行】执行SQL语句。

补充：如果只想执行NavCat中编写的一部分代码，而不是执行全部代码，只要选中要执行的代码，点击鼠标右键，选择“运行已经选择的”即可。

4、最简单的SQL：查看一个表的全部数据：select * from T_Persons

5、简单的插入数据的SQL语句：INSERT INTO T_Persons(Id,Name,Age,Gender) VALUES(5,'Jim',20,1)	//Insert语句可以省略表名后的列名，但是强烈不推荐//不用引号必须保证接受类型为整型

6、如果插入的行中有些字段的值不确定，那么Insert的时候不指定那些列即可。不“允许为空”的列在插入时不能省略。
Navicat有一个bug：某些情况下没有给字段设定值，最后的结果竟然不是null，咱们不用管这个bug。

7、自动递增/自增(Auto Increment)：字段自增可以避免并发等问题，不要程序员代码控制自增。用自增字段在Insert的时候不用指定值。

修改表结构的方法：选中要改掉表--【设计表】--更改
里边有数据之后再修改数据类型时要保证旧的数据类型和新选的数据类型兼容
选择主键《设计表》中自动递增，再次增加时就可以保证增加了。

=============
更新表=======
=============
1、更新一个列：UPDATE T_Persons Set Age=30	UPDATE T_Persons Set Age=30 where ID=2
2、更新多个列： UPDATE T_Persons Set Age=30,Name='tom'
3、表达式：UPDATE T_Persons Set Age=Age+1
4、更新一部分数据： UPDATE T_Persons Set Age=30 where Name='tom'，用where语句表示只更新Name是'tom'的行，注意SQL中等于判断用单个=，而不是==。
5、Where中还可以使用复杂的逻辑判断UPDATE T_Persons Set Age=30 where Name='tom' or Age<25，or相当于Java中的||	where (Age>20 and Age<30) or(Age=80)
6、Where中可以使用的其他逻辑运算符：or、and、not、<、>、>=、<=、!=（或<>）等
7、补充：正常情况下char类型赋值字符串，并且用''括起来；int类型赋值数字，用不用''括起来都可以。容错性下：char类型的字段可以赋值数字，可不用括起来，括起来int类型的赋值字符串，需用括号括起来，但是赋值不成功，永远显示0
总结：数字可以不括起来，字符串必须括起来，char类型可以接收字符串和数字，int要接收数字，如果接收字符串不会报错不会显示。
====================
=删除表中全部数据：=
====================

DELETE FROM T_Persons。删除数据时一定要有where
Delete 也可以带where子句来删除一部分数据：DELETE FROM T_Persons WHERE Age > 20
Delete只是删除数据，表还在。
(*)Drop table T_Persons 删除表。

select语法
1、简单的数据检索 ：SELECT * FROM T_Employees
2、只检索需要的列 ：SELECT Number FROM T_Employees 、SELECT Name,Age FROM T_Employees
3、列别名：SELECT Number AS 编号,Name AS 姓名,Age AS Age111 FROM T_Employees  写不写AS都行
4、计算列：SELECT Number 编号,Name 姓名,Age Age111,Age+10 十年后的年龄,1+1,now() FROM T_Employees
5、使用where检索符合条件的数据：SELECT Name FROM T_Employees WHERE Salary<5000。故事：新员工的数据检索噩梦
6、还可以检索不与任何表关联的数据：select 1+1;select now();

1、SQL聚合函数：MAX（最大值）、MIN（最小值）、AVG （平均值）、SUM （和）、COUNT（数量）
2、大于25岁的员工的最高工资 ：SELECT MAX(Salary) FROM T_Employees WHERE Age>25 
3、最低工资和最高工资：SELECT MIN(Salary),MAX(Salary) FROM  T_Employees
4、大于25岁的员工人数：SELECT COUNT(*) FROM  T_Employees  WHERE Age>25 
5、全体员工的工资总和平均工资：SELECT SUM(Salary),AVG (Salary) FROM  T_Employees

1、ORDER BY子句位于SELECT语句的末尾，它允许指定按照一个列或者多个列进行排序，还可以指定排序方式是升序(从小到大排列，ASC)还是降序(从大到小排列，DESC) 
2、按照年龄升序排序所有员工信息的列表：SELECT * FROM  T_Employees ORDER BY Age ASC 
3、按照年龄从大到小排序，如果年龄相同则按照工资从大到小排序 ：SELECT * FROM  T_Employees ORDER BY Age DESC,Salary DESC
4、ORDER BY子句要放到WHERE子句之后 ：SELECT * FROM T_Employees WHERE Age>23 ORDER BY Age DESC,Salary DESC 

通配符过滤使用LIKE 。
1、单字符匹配的通配符为半角下划线“_”，它匹配单个出现的字符。以任意字符开头，剩余部分为“erry” ：SELECT * FROM T_Employees WHERE Name LIKE '_erry' 
2、多字符匹配的通配符为半角百分号“%”，它匹配任意次数（零或多个）出现的任意字符。 “k%”匹配以“k”开头、任意长度的字符串。检索姓名中包含字母“n”的员工信息 ：SELECT * FROM T_Employees WHERE Name LIKE '%n%' 
3、Like性能较差，很容易造成全表扫描，谨慎使用。后面会讲数据库优化(索引等)，项目中做搜索用全文检索。

=========
NULL=====
=========
1、数据库中，一个列如果没有指定值，那么值就为null，数据库中的null表示“不知道”，而不是表示没有。因此select null+1结果是null，因为“不知道”加1的结果还是“不知道”。
2、SELECT * FROM T_Employees WHERE NAME=null ； SELECT * FROM T_Employees WHERE NAME!=null ；
都没有任何返回结果，因为数据库也“不知道”。
3、提问：如果T_Employees表中Name列的值是null的话，那么下面查询结果是什么？Select Name+"a" FROM T_Employees 。
答案：还是null，因为null和任何的东西做任何的运算都是null，null+"a"还是null。
4、SQL中使用is null、is not null来进行空值判断： 
SELECT * FROM T_Employees WHERE NAME is null ； 
SELECT * FROM T_Employees WHERE NAME is not null ；

select 2=null	运行时得到null


1、LIMIT关键字用来限制返回的结果集， LIMIT放在SELECT语句的最后位置，语法为“LIMIT  首行行号，要返回的结果集的最大数目” 。比如下面的SQL语句将返回Name不为空的、按照工资降序排列的从第二行开始（行号从0开始）的最多五条记录：
SELECT * FROM T_Employees
where Name is not null 
ORDER BY Salary DESC  

LIMIT 2,5
2、limit一定要放到所有的语句的最后。


==========
GROUP BY==
==========
1、数据分组用来将数据分为多个逻辑组，从而可以对每个组进行聚合运算。SQL语句中使用GROUP BY子句进行分组，使用方式为“GROUP BY  分组字段”。分组语句一般和聚合函数一起使用，GROUP BY子句负责将数据分成逻辑组，而聚合函数则对每一个组进行统计计算。
2、查看公司员工有哪些年龄段的：
SELECT Age FROM T_Employees GROUP BY Age 
将Age相同的数据行放到一组，分组后的数据可以看作一个临时的结果集，而SELECT  Age语句则取出每组的Age字段的值，这样我们就得到上表的员工年龄段表了。
3、如果SELECT语句有WHERE子句，则GROUP BY子句必须放到WHERE语句的之后。
4、GROUP  BY子句将检索结果划分为多个组，每个组是所有记录的一个子集。上面的SQL例子在执行的时候数据库系统将数据分成了下面的分组：
5、
分组后就可以对组内的数据采用聚合函数进行统计了：
1）计算每个分组中的员工平均工资
SELECT Age,AVG(Salary) FROM T_Employees
GROUP BY Age
2）查看每个年龄段的员工的人数：
SELECT Age,COUNT(*)  FROM T_Employees
GROUP BY Age

外键最好命名为××ID.

==================================

==================================
1、查询每张订单的订单号、价格、对应的客户姓名以及客户年龄
方法1：
select name,id from t_students	//若不加逗号则只是查询第二个参数id的数值
扩展方法1
select name 员工姓名,id 员工id from t_students

方法2：
select t_students.name,t_students.id
FROM t_students

方法3：
select qq.name,qq.id
FROM t_students qq

方法4：
select qq.name,qq.id FROM t_students qq

SELECT o.Number,o.Price,c.Name,c.Age
FROM T_Orders o
LEFT JOIN T_Customers c
ON o.CustomerId=c.Id

select from t_order.number,from t_order.price,left join t_customers.name,left join t_customers.age
on from t_orders.customerid=left join t_customers.id


2、添加where语句(显示价格>=150元的订单)

SELECT o.Number,o.Price,o.CustomerId,

c.Name,c.Age  FROM T_Orders o

LEFT JOIN T_Customers c  ON o.CustomerId=c.Id

WHERE o.Price>=150

3、可以join多张表：
===============================================
select qq.name,qq.id,	//qq.political_status,qq.gender两个数值不用填。
ps.political_status,	//需要把别的表想要显示的值写出来
gd.gender		//第三个表
FROM t_students qq	//以下是从什么表中取，怎么取，从主表中直接from
left join t_students_political_status_id ps	//从外键取，left join
on qq.political_status=ps.political_status_id	//从外键取出值的条件，要和Left join写在一起。
left join t_students_gender_id gd
on qq.gender=gd.gender_id

select qq.name,qq.id,ps.political_status,gd.gender FROM t_students qq left join t_students_political_status_id ps on qq.political_status=ps.political_status_id left join t_students_gender_id gd on qq.gender=gd.gender_id
================================================
SELECT o.Number 订单号,o.Price 价格, c.Name 客户姓名,c.Age 客户年龄,t.Name 订单类型
FROM T_Orders o
LEFT JOIN T_Customers c  ON o.CustomerId=c.Id
LEFT JOIN T_OrderTypes t ON o.TypeId=t.Id
======================
外键约束
=====================
1、如果删除/更新T_Customers一行记录，那么就可能会导致T_Orders中存在CustomerId为非法值的数据，使得程序逻辑错误。一般不会更新主键Id的值，所以谈外键约束的时候只谈“删除T_Customers时”。
2、外键约束：当删除T_Customers中一条数据的时候，如何处理T_Orders等存在指向T_Customers外键的行。外键约束建立在外键字段***Id的表上(t_orders)。
3、建外键约束的方法：新建或者修改表的时候(右击表格选择设计表格)“外键”→“添加外键”。名：自动命名即可；栏位名：CustomerId；参考表：t_customers；外栏位名：Id；删除时、更新时：一般默认RESTRICT(CASCADE:删除T_Customers一行时把它的订单也删除了；SET NULL:删除T_Customers一行时把它的订单CustomerId设置为NULL；NO ACTION/RESTRICT：拒绝删除)。
4、有的公司不习惯建外键，而是通过程序进行数据合法性控制，对于初学者先不建议这样，都把外键加上。利用外键约束防止数据出现逻辑混乱.

=========================================
静态网页内容一直不会变
动态网站，用户可以在上面留言，时时更新网页。
web服务器提供底层的socket程序
开发服务器底端程序CGI、 Java 、asp.net

嵌入式、路由器可以用CGI开发

http:80端口（socket）一个端口只能被一个程序占用

======================
apache服务器配置======
======================
1、把压缩包解压到硬盘（最好不要有中文、空格、特殊符号），选择一个文件夹作为“网站根目录”，然后把apache的conf/httpd.conf中的DocumentRoot修改为“网站根目录”（注意：路径分割一定要用正斜线/，不要用\；要用英文的引号，不要用中文的引号；“网站根目录”最好不要有中文，否则httpd.conf要以Utf-8编码保存）。
2、双击bin下的httpd.exe，启动服务器，如果屏幕一闪而过，则可能是出错了。启动命令行再运行httpd.exe，看报错信息。最好在dos命令行之下进入bin目录，然后运行httpd.exe，

可能的问题：“(OS 10048)通常每个套接字地址(协议/网络地址/端口)只允许使用一次”→有其他的Web服务器，把电脑上安装的其他的IIS、Apache、Tomcat等Web服务器卸载掉或者修改端口号。 http默认占用80端口，如鹏绿色版配置默认占用8080端口，麻烦地方在于每次网址都要带端口号。通过httpd.conf修改端口号。
在“网站根目录（用记事本更改的httpd.conf中的根目录）”下放一个1.html，写点内容，浏览器访问http://127.0.0.1:80/1.html，如果能显示就没问题了。
关闭命令行窗口apache服务器就关闭了。默认配置简单但是不安全，适用于开发，不适用于正式运行的网站。(*)正式运行以服务方式启动apche，但是开发时不要用“Windows 服务”方式启动，主要是权限导致的访问受限、调试等的麻烦。

浏览器运行出错的一般形式
ipcmg
如果瞬间出现internal server error,一般说明是第一行出错
如果是加载一会儿才出现internal server error，一般是程序有问题，被0除指针错误
浏览器向apache服务器请求打开exe文件，然后apache服务器将运行的结果返回给浏览器，浏览器将结果显示出来。
#include<windows.h>
MessageBoxA(0,"ok","ok",0);下断点用

system("pause");//按任意键继续
如果替换cgi时替换不了说明cgi程序apache正在运行着cgi

获取用户请求参数
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


VS中变量声明只能放在代码块前边
JavaScript的代码是写在浏览器中的，而CGI是写在服务器中的，不同的语言有不同的用处。

===================================================
请求报文头request headers
=========================
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

===========================
响应报文头response headers
==========================
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


响应报文体response				//之前要有两行空行
=======================================
HTTP/1.1 404 Not Found				//你需要的文件没有找到
Date: Tue, 12 Jul 2016 12:52:18 GMT		//当前时间是什么
Server: Apache/2.2.21 (Win32)			//我是一个什么服务器
Content-Length: 204
Keep-Alive: timeout=5, max=100
Connection: Keep-Alive
Content-Type: text/html; charset=iso-8859-1

Windows中写CGI程序一般都用"Content-Type:text/html;charset=gbk\r\n\r\n"
服务器通过Content-Type告诉客户端相应的数据的类型，这样浏览器就根据返回数据的类型来进行不同的处理，如果是图片、文本类型就直接显示内容，如果用html类型就用浏览器显示内容，常用的Content-Type有：text/html.image/gif.image/jpeg.text/;lain.text/plain.text/javascript
其中由于在Windows中的VS中编码故常用gbk编码故一般写"charset=gbk"


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
================================================================================
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

在解决方案下可以新建一个项目，并且点击右键设置“设定为启动项目”

设置一个断点、下一个、打一个、插入
因为CGI程序是apache运行起来的，运行完结束，来不及VS的调试器附加到CGI程序Web服务器不能以windows服务的形式运行
#include<windows.h>
MessageBoxA(0,"ok","ok",0);当执行到这个代码时对话框关闭才能继续向下执行
这时可以点击调试下拉列表，在列表中选择附加到进程然后在程序左侧灰色地带点击添加断点，这些工作做好之后点击OK清除弹出来的对话框，然后程序就在断点处停下来了，这是将鼠标放在变量上就可以看到变量的值了，从而起到调试的目的。

================================================================
CGI库
不同的开发语言都有不同的开发框架	框架 Framework
Java: Structs servlet JSP
.Net: WebForm ASP.Net MVC
PHP
asp.net MVC
CGIC只是简化开发而已，
No Magic!没有魔法
离了它，不用它，我也能自己造一个类似的东西来。

==================================
使用开发框架
==================================
打开vs新建win32控制台项目，新建.c项目，在解决方案下的项目上点击右键选择属性，对于其中的保存位置以及保持扩展名更改一下。
.\   表示项目文件所在目录之下的目录。
..\ 表示项目文件所在目录向上一级目录下的目录。
..\..\表示项目文件所在目录向上二级目录之下的目录。
..\..可以保存到和文件目录同一级别的目录中.也就是说本来该工程都包括在abc文件夹内的，但是此时调试出来的cgi文件和abc文件夹放在了一起。
再右键--在文件资源管理器中打开文件夹，将cgi.c 与cgi.h两个文件夹复制到打开的文件夹中。
再右键--添加--现有项目--选择这两个文件添加，

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
===========================================================
浏览器和服务器的交互
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
===================================
案例：通过表单和服务器进行交互=====
================================================================================================================================
html中的代码：

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

#include "cgic.h"

name=enter+username&password=123&textareaword=textarea+word&minzu=han&checkbox1=apple&checkbox2=pear&checkbox3=banana&sex=nan&submit=login

判断用户是否点击了name="submitting",value="="的按钮的方法
方法1：	if(cgiFormString("submitting",submit,sizeof(submit))!=cgiFormSuccess)
方法2：		cgiFormString("submitting",submit,sizeof(submit));
		if(strcmp(submit,"=")!=0)
方法3：	if(cgiFormSubmitClicked("submitting")==cigFormSuccess)	//点击时提交的报文头就有"submitting="，浏览器就可以识别了，因此必须有name
方法3潜在的bug,当提交的表单中有另外一个叫submitting的按钮，或者文本框时即使没有点击submitting，仍然有submitting
被提交，所以在给表单命名时要注意，名字的唯一

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
============================================================================================================================================
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
