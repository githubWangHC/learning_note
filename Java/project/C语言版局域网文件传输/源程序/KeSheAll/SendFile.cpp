//发送文件
void sendFile(SOCKET ss)
{
	char *str1="....确认成功....";
	send(ss,str1,strlen(str1)+1,0);			//向客户端发送验证信息

	FILE *fp;
	long i;									//文件分的的块数

	char fileAddr[300];
	char fileName[100];
dmm:
	printf("请输入要传送的文件绝对地址(含文件名)：");

	gets(fileAddr);

	//以读、写方式打开一个二进制文件，只允许读/写数据
	if( (fp=fopen(fileAddr,"rb")) == NULL)   //打开文件
	{
		printf("文件地址输入错误!\n");
		goto dmm;
	}
	getW_name(fileAddr,fileName);	
	send(ss,fileName,strlen(fileName)+1,0);  //发送文件名

	//用来移动文件流的读写位置，调用成功返回0
	fseek(fp,0L,2);

	//ftell()获取文件读写指针的当前位置，成功返回当前位置
	long fileLength=ftell(fp);				//取得文件的长度
	fseek(fp,0L,0);

	char Length[100];
	LongToChar(fileLength,Length);
	send(ss,Length,strlen(Length)+1,0);		//发送文件大小到客户端


	if( 0 == fileLength%M )					//对文件进行分块
	{
		i=fileLength/M;
	}
	else
	{
		i=fileLength/M+1;
	}
	
	char mess[M];
	long readLength;						//从文件读取的长度
	long sendLength;						//发送文件的长度
	int k=1;								//正在传送的模块号

	char *temp="开始接收文件";				//向客户端开始传送，确认同步开始
	int nResult;
	nResult=recv(ss,mess,M,0);
	if(nResult == -1 )                   //判断客户端是否关闭
	{
		printf("\n客户端已断开\n");
		system("pause");
		return ;
	}
	send(ss,temp,strlen(temp)+1,0);
	printf("%s,共 %ld 模块：\n",mess,i);
	long total=0;
	while( k <= i )
	{	
		Sleep((DWORD)800);			//此非常重要，降低了传输时间，保证了两边的同步
		readLength=fread(mess,sizeof(char),M,fp);
		sendLength=send(ss,mess,readLength,0);
		total+=sendLength;
		printf("\r第 %d 模块   读取：%ldB  发送：%ldB",k,readLength,sendLength);
		printf("  进度: %2.2f%c  模块：%ld / %ld",100*(total*1.0/fileLength),37,k,i);
		if(sendLength==SOCKET_ERROR)   
		{ 
		printf("失败!");
		}	
		k++;
	}
	fclose(fp);
	printf("\n文件发送完成!\n");
	system("pause");
}


//接收文件
int receiveFile(SOCKET sc)
{
	system("color 0a");
	int nResult;
	char mess[M];
	nResult=recv(sc,mess,strlen(mess),0);    //确认文件传送模块连接成功
	if(nResult == -1 )                       //判断服务端是否关闭
	{
		printf("\n服务端已断开\n");
		system("pause");
		return 0;
	}
	printf("%s\n",mess);


	char fileName[50];
	nResult=recv(sc,fileName,50,0);       //接收文件名
	if(nResult == -1 )                    //判断服务端是否关闭
	{
		printf("\n服务端已断开\n");
		system("pause");
		return 0;
	}
	nResult=recv(sc,mess,M,0);             //接受文件的大小
	if(nResult == -1 )                    //判断服务端是否关闭
	{
		printf("\n服务端已断开\n");
		system("pause");
		return 0;
	}

	long fileLength=StrToLong(mess);      //取的待传送文件的长度
	long i;	                              //存放文件块数

	if( 0 == fileLength%M )				  //对文件按N单位进行分块
	{
		i=fileLength/M;
	}
	else
	{
		i=fileLength/M+1;
	}


	FILE *fp;							//打开 文件
	int k=1;					    	//当前正在接收文件的块数
	long receiveLength;					//接收文件的长度
	long writeLength;					//已写到文件的字符长度
	fp=fopen(fileName,"wb");		
	char *temp="开始传送文件";		
	send(sc,temp,strlen(temp)+1,0);		//向客户端开始传送，确认同步开始
	nResult=recv(sc,mess,M,0);			//接收服务端的确认消息
	if(nResult == -1 )                  //判断服务端是否关闭
	{
		printf("\n服务端已断开\n");
		system("pause");
		return 0;
	}
	printf("%s,共 %ld 模块：\n",mess,i);

	long total=0;						//已接收文件的长度
	while(k<=i)                         //循环的接收文件
	{
		receiveLength=recv(sc,mess,M,0);
		if( receiveLength== -1 )        //判断服务端是否关闭
		{
		printf("\n服务端已断开\n");
		system("pause");
		return 0;
		}
		
		writeLength=fwrite(mess,sizeof(char),receiveLength,fp);
		printf("\r第 %d 模块   接收：%ldB  写入：%ldB",k,receiveLength,writeLength);
		total+=writeLength;
		printf("  进度: %2.2f%c  模块：%ld / %ld",100*(total*1.0/fileLength),37,k,i);
		k++;
	}


	fclose(fp);
	printf("\n文件接收完成!\n");
	system("pause");
	return 0;
}
