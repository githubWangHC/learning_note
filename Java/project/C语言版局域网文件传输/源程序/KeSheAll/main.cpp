#include <stdio.h>
#include <stdlib.h>
#include <string.h>
//#include <Winsock2.h>
#include <process.h>
#include <time.h>
#pragma comment(lib,"Ws2_32")
#define M 512000							 //ÿ�鴫���ļ��Ĵ�С500k
#define portNum 4567						 //ʹ�õĶ˿ں�


//��������
#include "fun_statement.cpp";

//������Ӧ�����ļ�

#include "Client.cpp";            //�ͻ����ļ�
#include "Server.cpp";            //������ļ�
#include "SendFile.cpp";          //�ļ�����ģ��
#include "Chat.cpp";              //����ģ��
#include "tool.cpp";              //����ģ��
//#include "menu.cpp";              //���˵��ļ�
	
void main()
{	
	system("cls");
	system("@color 0b");
	puts("\n");
	puts("            *********************************************************");
	puts("            *                                                       *");
	puts("            *         �� ӭ ʹ �� �� �� �� �� �� �� �� �� ��        *");
	puts("            *                                                       *");
	puts("            *                                                       *");
	puts("            *                                                       *");
	puts("            *                      1.   ��  ��  ��                  *");
	puts("            *                                                       *");
	puts("            *                      2.   ��  ��  ��                  *");
	puts("            *                                                       *");
	puts("            *                      3.   ��      ��                  *");
	puts("            *                                                       *");
	puts("            *                                                       *");
	puts("            *                     ���������γ����                *");
	puts("            *                         2017-06-19                    *");
	puts("            *                                                       *");
	puts("            *********************************************************\n");
	puts("ȫ��(�˳�ȫ��)�����밴��ALT+ENTER\n");
	printf("��������Ҫѡ��Ķ˴���(1-3):");

	char ch;
	char Num[10];
	gets(Num);
	ch=Num[0];
	while(ch >='4' || ch <='0')						//����û�ѡ��Ĵ����Ƿ����Ҫ��
	{
		printf("\n�����������!���������룺");
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






