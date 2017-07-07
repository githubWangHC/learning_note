# 程序编译的一般过程
.c文件====>.i文件==>.s文件==>.o文件==>可执行文件
     预处理     编译      汇编    链接

这一过程用gcc命令的实现

-c表示只编译(compile)源文件但不链接，会把.c或.cc的c源程序编译成目标文件，一般是.o文件。

-o用于指定输出(out)文件名。不用-o的话，一般会在当前文件夹下生成默认的a.out文件作为可执行程序。

例如

gcc -c test.c		将生成test.o的目标文件

gcc -o app test.c	将生成可执行程序app，只用在-o选项后加目标文件即可，app与

test的顺序无要求

gcc -c a.c -o a.o	表示把源文件a.c编译成指定文件名a.o的中间目标文件(其实在这里，你把-o a.o省掉，效果是一样的，因为中间文件默认与源文件同名，只是后缀变化)。

# make与Makefile

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

#this is a makefile

main.out:max.o min.o main.c

        gcc -g max.o min.o main.c -o main.out

max.o:max.c

        gcc -c -g max.c -o max.o

min.o:min.c

        gcc -c -g min.c -o min.o

第一行以#开头，是注释

第二行意思是说如果要得到main.out文件则需要哪些文件，有三个

第三行是要得到main.out文件要用什么命令

以下内容类似，但需要将最终得到的main.out文件放在最前面，类似于第递归。

gcc的-c表示编译的意思，-o表示目标文件的意思，-g表示用gdb工具来调试，如果某一个文件不在makefile的文件夹中则可以直接写路径即可。./main/main.c。


# Gdb调试工具

1，gcc -g main.c -o main.out来产生可以调试的文件

如果是在makefile中编写则需要注意在编译.o文件时也要加上-g，否则能够进入main函数，但是不能进入子函数。

#this is a makefile

main.out:max.o min.o main.c

	gcc -g max.o min.o main.c -o main.out

max.o:max.c

	gcc -g -c max.c -o max.o

min.o:min.c

	gcc -g -c min.c -o min.o

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
