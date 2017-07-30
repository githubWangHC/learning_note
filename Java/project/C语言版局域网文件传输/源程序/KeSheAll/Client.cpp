int client()
{
	system("color 0a");				//修改DOS窗口颜色，是其成0A。

	WORD wVersion=MAKEWORD(1,1); 
	WSADATA wsData; 
	int nResult= WSAStartup(wVersion,&wsData);  //启动WINSOCKET	
	if(nResult !=0) 
	{ 
	printf("启动Winsock失败!\n"); 
	} 

	SOCKET sc=socket(AF_INET,SOCK_STREAM,IPPROTO_IP);  //创建套接字
	if(sc==INVALID_SOCKET) 
	{ 
	printf("创建套接字失败!\n"); 
	} 

	SOCKADDR_IN addrSc;

	//family指明协议族
	addrSc.sin_family=AF_INET; //ipv4
	addrSc.sin_port=htons(portNum); //保证字节顺序 
	char IP[20];
again:
	printf("请输入服务器的IP地址：");
	gets(IP);	

	//循环检测IP地址是否合法
	if( -1==inet_addr(IP) )  
	{
		printf("IP地址错误!\n");
		goto again;
	}
	addrSc.sin_addr.S_un.S_addr=inet_addr(IP); 


	int b=0;
	while(b<5)               //检测5次，如果服务器在此时间内启动，则进行连接
	{
		nResult=connect(sc,(SOCKADDR*)&addrSc,sizeof(SOCKADDR)); //套接字连接
		Sleep((DWORD)100);				//延时1秒
		if(nResult==SOCKET_ERROR) 
		{ 
		printf("  %d 次连接失败!\n",b+1);
		}
		else
			break;
		b++;
	}
	if(nResult==SOCKET_ERROR)
	{
	printf("登陆超时，请重新登陆!\n");
	goto again;
	}

	char *buf="连接成功!";                   //向服务端验证连接成功
	nResult=send(sc,buf,strlen(buf)+1,0); 
	if(nResult==SOCKET_ERROR)
	{ 
	printf("5.套接字发送数据失败!\n");
	return 0;
	}
	char mess[M];
	nResult =recv(sc,mess,strlen(mess),0);     //接受服务端的连接验证信息
	if(nResult == -1 )                         //判断服务端是否关闭
	{
		printf("\n服务端已断开\n");
		system("pause");
		exit(0);
	}
	printf("%s\n",mess);

///////////////////////////////////////////////////////////////////////////
////                       功能菜单及操作                              ////
///////////////////////////////////////////////////////////////////////////

tianle:	
	system("cls");             //清屏
	puts("\n\n");
	puts("            *********************************************************");
	puts("            *                                                       *");
	puts("            *         欢 迎 使 用 局 域 网 文 件 发 送 工 具        *");
    puts("            *                      (客  户  端)                     *");
	puts("            *                                                       *");
	puts("            *                                                       *");
	puts("            *                                                       *");
	puts("            *                                                       *");
	puts("            *                 请等待服务端的相应操作                *");
	puts("            *                                                       *");
	puts("            *                                                       *");
	puts("            *                                                       *");
	puts("            *                                                       *");
	puts("            *                     计算机网络课程设计                *");
	puts("            *                         2017-06-19                    *");
	puts("            *                                                       *");
	puts("            *********************************************************\n");
	puts("全屏(退出全屏)操作请按：ALT+ENTER\n");

		char rMess[100];
		nResult=recv(sc,rMess,strlen(rMess),0);         //接收服务端发来的操作请求
		if(nResult==-1)                              //判断服务端是否关闭
		{
		printf("\n服务端已断开\n");
		system("pause");
		goto tianle;
		}
		printf("%s\n",rMess);

		char ch;
		char str[100];
		gets(str);
		ch=str[0];
		while( ch!='Y' && ch!='y' && ch!='N' && ch!='n' ) //处理客户端的错误输入
		{
			printf("输入有误,请重新输入(Y/N)：");
			gets(str);
			ch=str[0];
		}
		
		send(sc,&ch,sizeof(char),0); //向服务端反馈选择，并执行相应操作
		if(strcmp(rMess,"服务端向你传送文件,是否接受(Y/N):")==0 && (ch=='Y' || ch=='y') )
		{
			receiveFile(sc);
		}
		if(strcmp(rMess,"服务端向你发起聊天,是否接受(Y/N):")==0 && (ch=='Y' || ch=='y') )
		{
			chatting_client(sc);
		}
		if(ch=='N' || ch=='n')
		{
			printf("你拒绝了!\n");
			system("pause");
		}			
	system("cls");
	goto tianle;

	nResult=closesocket(sc);  //关闭套接字
	if(nResult==SOCKET_ERROR) 
	{ 
	printf("8.关闭套接字失败!\n");
	return 0;
	} 
}
