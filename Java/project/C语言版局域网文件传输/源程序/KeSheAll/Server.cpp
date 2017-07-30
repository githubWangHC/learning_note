void server()
{
	system("color 0a");						//修改DOS窗口颜色为0A
	WORD wVersion=MAKEWORD(1,1); 
	WSADATA wsData; 
	int nResult= WSAStartup(wVersion,&wsData); //1、启动Winsock
	if(nResult !=0) 
	{ 
	printf("启动Winsock失败!\n"); 
	} 


	/*
		AF_INET指使用的协议族
		第二个参数：指明socket类型，	SOCK_STREAM--tcp类型，保证数据顺序及可靠性
		第三个参数：通常赋值为0，由系统自动选择
	*/
	SOCKET s=socket(AF_INET,SOCK_STREAM,IPPROTO_IP); //2.创建套接字
	if(s==INVALID_SOCKET) 
	{ 
	printf("创建套接字失败!\n"); 
	} 

	//绑定IP和端口
	SOCKADDR_IN addr; 
	addr.sin_family=AF_INET; 
	addr.sin_port=htons(portNum); //保证字节顺序 
	addr.sin_addr.S_un.S_addr=htonl(INADDR_ANY);

	//3、bind() 套接字的绑定
	nResult=bind(s,(sockaddr*)&addr,sizeof(sockaddr)); 
	if(nResult==SOCKET_ERROR) 
	{ 
	printf("套接字的绑定失败!\n");
	} 

	//最多5个连接， 套接字的监听，开始监听
	nResult=listen(s,5); 
	if(nResult==SOCKET_ERROR) 
	{ 
	printf("套接字的监听(服务器端)失败!\n"); 
	} 


	SOCKADDR_IN addrClient;
	int len=sizeof(SOCKADDR);
	printf("\n等待客户端的连接");
	
	int a=0;
	while(a<5)										//开始的一个小动态图形
	{
		for(int b=0;b<8;b++)
		{
			Sleep(100);
			printf(" . ");
		}
		Sleep(100);
		system("cls");
		printf("\n\r等待客户端的连接");
		a++;
	}
while(1)
{
	SOCKET s_d=accept(s,(sockaddr*)&addrClient,&len); //5、套接字等待连接:：（服务器端） 
	if(s_d==INVALID_SOCKET) 
	{ 
	printf("套接字等待连接(服务器端)失败!\n");
	}	
	char *success="成功登陆服务器!";


	/*
		第一个参数：发送端套接字描述符
		第二个参数：待发送数据的缓冲区
		第三个参数：待发送数据的字节长度
	*/
	send(s_d,success,strlen(success)+1,0);            //向客户端发送验证信息

	char mess[M]; 

	//与send函数相对应
	nResult=recv(s_d,mess,strlen(mess),0); 
	if(nResult == -1 )                               //判断服务端是否关闭
	{
		printf("\n客户端已断开\n");
		system("pause");
		exit(0);
	}
	printf("\n%s",mess);

///////////////////////////////////////////////////////////////////////////
////                       功能菜单及操作                              ////
///////////////////////////////////////////////////////////////////////////
again:
	system("@color 0a");
	system("cls");

	puts("\n");
	puts("            *********************************************************");
	puts("            *                                                       *");
	puts("            *         欢 迎 使 用 局 域 网 文 件 发 送 工 具        *");
    puts("            *                     (服  务  端)                      *");
	puts("            *                                                       *");
	puts("            *                                                       *");
	puts("            *              1.   向 客 户 端 传 送 文 件             *");
	puts("            *                                                       *");
	puts("            *              2.   向 客 户 端 发 起 聊 天             *");
	puts("            *                                                       *");
	puts("            *              3.         结      束                    *");
	puts("            *                                                       *");
	puts("            *                                                       *");
	puts("            *                     计算机网络课程设计                *");
	puts("            *                         2017-06-19                    *");
	puts("            *                                                       *");
	puts("            *********************************************************\n");
	puts("全屏(退出全屏)操作请按：ALT+ENTER\n");
	printf("请输入你要选择的模块的代号(1-3):");
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
 
	if('1'==ch)								    	//发送文件模块的处理
	{
		char ch;
		char *str="服务端向你传送文件,是否接受(Y/N):";
		send(s_d,str,strlen(str)+1,0);
		printf("等待客户端回应……\n");
		nResult=recv(s_d,&ch,sizeof(char),0);
		if(nResult == -1 )                         //判断客户端是否关闭
		{
			printf("\n客户端已断开\n");
			system("pause");
			goto again;
		}
		if( ch=='Y' || ch=='y')
		{	
			sendFile(s_d);
			goto again;
		}
		if(ch=='N'|| ch=='n')
		{
			printf("对方拒绝接受!\n");
			system("pause");
			goto again;
		}
	}

	if('2'==ch)									//处理聊天模块
	{
		char ch;
		int flag=0;
		char *str="服务端向你发起聊天,是否接受(Y/N):";
		send(s_d,str,strlen(str)+1,0);

		printf("等待客户端回应……\n");
	
		nResult=recv(s_d,&ch,sizeof(char),0);       //判断客户端同意或不同意
		if(nResult == -1 )                         //判断客户端是否关闭
		{
			printf("\n客户端已断开\n");
			system("pause");
			goto again;
		}
		if( ch=='Y' || ch=='y' )
		{	
			chatting_server(s_d);
			goto again;
		}
		if(ch=='N'|| ch=='n')
		{
			printf("对方拒绝接受!\n");
			system("pause");
			goto again;
		}
	}
	if('3'==ch)
	{
		exit(0);          //结束程序
	}

}
	nResult=closesocket(s); //关闭套接字。
	if(nResult==SOCKET_ERROR) 
	{ 
	printf("8.关闭套接字(服务器端)失败!\n");
	} 
}