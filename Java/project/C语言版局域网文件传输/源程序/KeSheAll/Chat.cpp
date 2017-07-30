//聊天客户端程序
void chatting_client(SOCKET sc)
{
	system("cls");
	system("color 0a");
	int receiveLength; 
	char SMess[1000];			//发送消息的缓存
	char RMess[1000];			//接收消息的缓存
	char SName[200];			//服务端用户的姓名
	char CName[200];			//客户端用户的姓名
	int nResult;

	nResult=recv(sc,RMess,strlen(RMess),0);     //确认文件传送模块连接成功
	if( nResult== -1 )           //判断服务端是否关闭
	{
		printf("\n服务端已断开\n");
		system("pause");
		return ;
	}
	printf("%s\n",RMess);

	printf("请输入你的名字：");      //客户端用户的姓名
	gets(CName);

	while(strlen(CName)==0)
	{
		printf("用户名不能为空! 请输入你的名字：\n");
		gets(CName);
	}

	send(sc,CName,strlen(CName)+1,0);   //向服务端发送姓名
	receiveLength=recv(sc,SName,200,0); //接收服务端用户的姓名
	if(receiveLength==-1)                //判断服务端是否关闭
	{
		printf("\n服务端已断开\n");
		system("pause");
		return ;
	}
	printf("你已和%s取的连接……\n",SName);
	printf("开始聊天(输入exit退出聊天程序)……\n");

	while(1)
	{
		struct tm *local; 
		time_t t;						//调用时间函数，显示用户的操作时间
		t=time(NULL); 
		local=localtime(&t); 
		nResult=recv(sc,RMess,M,0);		 //接收服务端发送过来的消息
		if(nResult == -1)                //判断服务端是否关闭
		{
			printf("\n服务端已断开\n");
			system("pause");
			return ;
		}

		if(strcmp(RMess,"exit")==0)
		{
			printf("服务端已退出聊天程序,本程序也即将退出!\n");
			system("pause");
			break;
		}
		printf("%s\t%d:%d:%d  \n    %s\n",SName,local->tm_hour,local->tm_min,local->tm_sec,RMess);	

		printf("\n请输入信息：");      //客户端用户输入消息
		gets(SMess);
		if(strcmp(SMess,"exit")==0)
		{
		send(sc,SMess,strlen(SMess)+1,0);
		printf("你已退出聊天程序!\n");
		system("pause");
		break;
		}
		//服务端显示自己的消息
		t=time(NULL); 
		local=localtime(&t); 
		printf("\r%s\t%d:%d:%d  \n    %s\n",CName,local->tm_hour,local->tm_min,local->tm_sec,SMess);  //SMess发送消息的缓存
		send(sc,SMess,strlen(SMess)+1,0);
	}
}

//聊天服务端程序
void chatting_server(SOCKET s_d)
{
	system("color 0a");
	char *str1="你同意了……";
	send(s_d,str1,strlen(str1)+1,0);

	int receiveLength;			//接收消息的长度
	char SMess[1000];			//发送消息的缓存
	char RMess[1000];			//接收消息的缓存
	char SName[200];			//服务端用户的姓名
	char CName[200];			//客户端用户的姓名


	receiveLength=recv(s_d,CName,200,0);
	if(receiveLength==-1)		 //判断客户端是否关闭
	{
		printf("客户端已断开\n");
		system("pause");
		return ;
	}
	 printf("你已和%s取的连接……\n",CName);

	printf("请输入你的名字：");
	gets(SName);
	while(strlen(SName)==0)		//循环检测用户名是否合法
	{
		printf("用户名不能为空!  请输入你的名字：\n");
		getchar();
		gets(SName);
	}

	send(s_d,SName,strlen(SName)+1,0);	//向客户端发送姓名
	printf("\n开始聊天(输入exit退出聊天程序)……\n");
	while(1)
	{
		struct tm *local; 
		time_t t; 
		t=time(NULL); 
		local=localtime(&t); 
		printf("\n\n请输入信息：");
		gets(SMess);
		if(strcmp(SMess,"exit")==0)   //用户输入‘exit’退出程序
		{
		send(s_d,SMess,strlen(SMess)+1,0);
		printf("你已退出聊天程序!\n");
		system("pause");
		break;
		}
		//在服务端显示自己的消息
		printf("\r%s\t%d:%d:%d\n    %s\n",SName,local->tm_hour,local->tm_min,local->tm_sec,SMess);
		send(s_d,SMess,strlen(SMess)+1,0);
		
		//在服务端显示客户端消息
		t=time(NULL); 
		local=localtime(&t); 
		receiveLength=recv(s_d,RMess,M,0);
		if(receiveLength==-1)		 //判断客户端是否关闭
		{
			printf("客户端已断开\n");
			system("pause");
			return ;
		}
		if(strcmp(RMess,"exit")==0)
		{
			printf("\n客户端已退出聊天程序,本程序也即将退出!\n");
			system("pause");
			break;
		}
		printf("\n%s\t%d:%d:%d\n    %s\n",CName,local->tm_hour,local->tm_min,local->tm_sec,RMess);
	}

}