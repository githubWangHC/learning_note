#include <stdio.h>
#include <stdlib.h>
#include <string.h>
//#include <Winsock2.h>
#include <process.h>
#include <time.h>
#pragma comment(lib,"Ws2_32")
#define M 512000							 //每块传送文件的大小500k
#define portNum 4567						 //使用的端口号


//函数声明
#include "fun_statement.cpp";

//引入相应函数文件

#include "Client.cpp";            //客户端文件
#include "Server.cpp";            //服务端文件
#include "SendFile.cpp";          //文件处理模块
#include "Chat.cpp";              //聊天模块
#include "tool.cpp";              //工具模块
//#include "menu.cpp";              //主菜单文件
	
void main()
{	
	system("cls");
	system("@color 0b");
	puts("\n");
	puts("            *********************************************************");
	puts("            *                                                       *");
	puts("            *         欢 迎 使 用 局 域 网 文 件 发 送 工 具        *");
	puts("            *                                                       *");
	puts("            *                                                       *");
	puts("            *                                                       *");
	puts("            *                      1.   客  户  端                  *");
	puts("            *                                                       *");
	puts("            *                      2.   服  务  端                  *");
	puts("            *                                                       *");
	puts("            *                      3.   结      束                  *");
	puts("            *                                                       *");
	puts("            *                                                       *");
	puts("            *                     计算机网络课程设计                *");
	puts("            *                         2017-06-19                    *");
	puts("            *                                                       *");
	puts("            *********************************************************\n");
	puts("全屏(退出全屏)操作请按：ALT+ENTER\n");
	printf("请输入你要选择的端代号(1-3):");

	char ch;
	char Num[10];
	gets(Num);
	ch=Num[0];
	while(ch >='4' || ch <='0')						//检测用户选择的代码是否符合要求
	{
		printf("\n你的输入有误!请重新输入：");
		gets(Num);
		ch=Num[0];
	}

	if('1'==ch)
	{
		client();
	}
	else if('2'==ch)
	{
		server();
	}
	else if('3'==ch)
	{
		exit(0);
	}
}






