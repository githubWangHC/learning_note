#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <Winsock2.h>
#include <process.h>
#include <time.h>
#pragma comment(lib,"Ws2_32")
#define M 512000							 //每块传送文件的大小500k
#define portNum 5102						 //使用的端口号1024~60000

void LongToChar(long x,char a[]);			//将long型整数转换成字符串

long StrToLong(char str[]);					//将字符串转换成long型整数

void getW_name(char waddr[],char wname[]);   //获取文件名函数

void sendFile(SOCKET ss);					 //服务端发送文件函数

int receiveFile(SOCKET sc);					//客户端接收文件函数

int client();								 //客户端主程序

void server();								 //服务端主程序

//定义主函数	
void main()
{	
	system("cls");
	system("@color 0a");
	puts("\n");
	puts("	  *********************************************************");
	puts("    	  *							  * ");
	puts("    	  *   欢·迎·使·用·局·域·网·文·件·发·送·工·具  *");
	puts("    	  *			     			          * ");
	puts("    	  *							  * ");
	puts("    	  *							  * ");
	puts("    	  *	 	       1.   客  户  端  	    	  * ");
	puts("    	  *							  * ");
	puts("    	  *		       2.   服  务  端 	    	          * ");
	puts("    	  *							  * ");
	puts("    	  *		       3.   结      束   		  * ");
	puts("    	  *							  * ");
	puts("    	  *							  * ");
	puts("    	  *							  * ");
	puts("    	  *			  2017-6-20 			  * ");
	puts("    	  *		      长江大学计算机网络	  	  * ");
	puts("    	  *********************************************************\n");
	printf("请输入你要选择的端代号(1-3):");

	char ch;
	char Num[10];
	gets(Num);
	ch=Num[0];
	while(ch >='4' || ch <='0')						//检测用户选择的代号是否符合要求
	{
		printf("\n你的输入有误!请重新输入：");
		gets(Num);
		ch=Num[0];
	}
	
	if('1'==ch)
	{
		//输入1选择客户端
		client();
	}
	else if('2'==ch)
	{
		//输入2选择服务端
		server();
	}
	else if('3'==ch)
	{
		exit(0);
	}
}




int client()
{
	system("color 0a");				//修改DOS窗口颜色，是其成0A。
	//初始化套接字
	WORD wVersion=MAKEWORD(1,1);	//Winsock版本，调用1.1版，支持TCP/IP协议
	WSADATA wsData;					//返回Windows Sockets数据
	int nResult= WSAStartup(wVersion,&wsData);  //启动WINSOCKET	
	if(nResult !=0) 
	{ 
	printf("启动Winsock失败!\n"); 
	} 

	SOCKET sc=socket(AF_INET,SOCK_STREAM,IPPROTO_IP);  //创建套接字
	/*
	第一个参数指使用的协议族，协议族决定了socket的地址类型，在通信中必须采用对应的地址，
	AF_INET决定了要用ipv4地址（32位的）与端口号（16位的）的组合
	第二个参数指明socket类型，SOCK_STREAM -- TCP类型，保证数据顺序及可靠性
	第三个参数传输协议
	*/
	if(sc==INVALID_SOCKET) 
	{ 
	printf("创建套接字失败!\n"); 
	} 

	SOCKADDR_IN addrSc; //sockaddr 是一个通用地址结构，这是为了统一地址结构的表示方法，统一接口函数，
	//使不同的地址结构可以被bind() , connect() 等函数调用；struct sockaddr_in中的in 表示internet，就是网络地址
	addrSc.sin_family=AF_INET;   //AF_INET地址族
	addrSc.sin_port=htons(portNum); //保证字节顺序 
	char IP[20];
again:
	printf("请输入服务器的IP地址：");
	gets(IP);	
	if( -1==inet_addr(IP) )   //循环检测IP地址是否合法
	{
		printf("IP地址错误!\n");
		goto again;
	}
	addrSc.sin_addr.S_un.S_addr=inet_addr(IP); //若合法，则填充服务端IP地址


	int b=0;
	while(b<5)               //检测5次，如果服务器在此时间内启动，则进行连接
	{
		nResult=connect(sc,(SOCKADDR*)&addrSc,sizeof(SOCKADDR)); //套接字连接 connect()
		Sleep((DWORD)100);				//延时1秒
/*		if(nResult==SOCKET_ERROR) 
		{ 
		printf("  %d 次连接失败!\n",b+1);
		}
		
		else
			break;
*/
		if (nResult!=SOCKET_ERROR)
			break;
		b++;
	}
	
	if(nResult==SOCKET_ERROR)
	{
	printf("连接超时，请重新登陆!\n");
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
	system("@color 0a");
	puts("\n\n");
	puts("	  *********************************************************");
	puts("    	  *							  * ");
	puts("    	  *   欢·迎·使·用·局·域·网·文·件·发·送·工·具  *");
	puts("    	  *			(客   户   端)			  * ");
	puts("    	  *							  * ");
	puts("    	  *							  * ");
	puts("    	  *		   请等待服务端的相应操作		  * ");
	puts("    	  *							  * ");
	puts("    	  *							  * ");
	puts("    	  *		      	  	                          * ");
	puts("    	  *			  2017-6-20 			  * ");
	puts("    	  *		      	  	                          * ");
	puts("    	  *********************************************************\n");

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
		if(ch=='N' || ch=='n')
		{
			printf("你拒绝了!\n");
			system("pause");
		}			
	system("cls");
	goto tianle;

	nResult=closesocket(sc);  //关闭套接字，断开连接
	if(nResult==SOCKET_ERROR) 
	{ 
	printf("8.关闭套接字失败!\n");
	return 0;
	} 
}

void server()
{
	system("color 0a");						//修改DOS窗口颜色为0A
	//初始化套接字
	WORD wVersion=MAKEWORD(1,1); 
	WSADATA wsData; 
	int nResult= WSAStartup(wVersion,&wsData); //1、启动Winsock
	if(nResult !=0) 
	{ 
	printf("启动Winsock失败!\n"); 
	} 

	SOCKET s=socket(AF_INET,SOCK_STREAM,IPPROTO_IP); //2.创建套接字
	if(s==INVALID_SOCKET) 
	{ 
	printf("创建套接字失败!\n"); 
	} 

	SOCKADDR_IN addr; 
	addr.sin_family=AF_INET; 
	addr.sin_port=htons(portNum); //保证字节顺序 
	addr.sin_addr.S_un.S_addr=htonl(INADDR_ANY);
	nResult=bind(s,(sockaddr*)&addr,sizeof(sockaddr)); //3、bind()套接字绑定到指定的IP地址和端口上
	if(nResult==SOCKET_ERROR) 
	{ 
	printf("套接字的绑定失败!\n");
	} 

	nResult=listen(s,5); //最多5个连接， 套接字的监听listen()
	if(nResult==SOCKET_ERROR) 
	{ 
	printf("套接字的监听(服务器端)失败!\n"); 
	} 


	SOCKADDR_IN addrClient;
	int len=sizeof(SOCKADDR);
	printf("\n等待客户端的连接");
	
	int a=0;
	while(a<10)										//开始的一个小动态图形
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
	SOCKET s_d=accept(s,(sockaddr*)&addrClient,&len); //5、套接字等待连接:：（服务器端） accept()
	if(s_d==INVALID_SOCKET) 
	{ 
	printf("套接字等待连接(服务器端)失败!\n");
	}	
	char *success="成功登陆服务器!";
	send(s_d,success,strlen(success)+1,0);            //向客户端发送验证信息

	char mess[M]; 
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
	puts("	  *********************************************************");
	puts("    	  *							  * ");
	puts("    	  *   欢·迎·使·用·局·域·网·文·件·发·送·工·具  *");
	puts("    	  *			(服   务   端)			  * ");
	puts("    	  *							  * ");
	puts("    	  *							  * ");
	puts("    	  *	 	  1.向 客 户 端 传 送 文 件 		  * ");
	puts("    	  *							  * ");
	puts("    	  *							  * ");
	puts("    	  *		  2.结   束   服   务    端		  * ");
	puts("    	  *							  * ");
	puts("    	  *							  * ");
	puts("    	  *		      	  	                          * ");
	puts("    	  *			  2017-6-20 			  * ");
	puts("    	  *		      	  	                          * ");
	puts("    	  *********************************************************\n");
	printf("请输入你要选择的模块的代号(1-2):");
	char ch;
	char Num[10];
	gets(Num);
	ch=Num[0];
	while(ch >='3' || ch <='0')						//检测用户选择的代码是否符合要求
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
			sendFile(s_d);						//Y 开始发送文件
			goto again;
		}
		if(ch=='N'|| ch=='n')
		{
			printf("对方拒绝接受!\n");
			system("pause");
			goto again;
		}
	}

	if('2'==ch)
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

	if( (fp=fopen(fileAddr,"rb")) == NULL)   //打开文件 fopen
	{
		printf("文件地址输入错误!\n");
		goto dmm;
	}
	getW_name(fileAddr,fileName);	
	send(ss,fileName,strlen(fileName)+1,0);  //发送文件名

	fseek(fp,0L,2);
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
	while( k <= i )					//当正在传送的模块号大于块数，表示传输完成
	{	
		Sleep((DWORD)800);			//此非常重要，降低了传输时间，保证了两边的同步 800毫秒
		readLength=fread(mess,sizeof(char),M,fp); //fread(void *buffer, size_t size, size_t count, FILE * stream);
												  //fread()函数每次从stream中最多读取count个单元，每个单元大小为size个字节，
												  //将读取的数据放到buffer；文件流的位置指针后移 size * count 字节。
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

void getW_name(char waddr[],char wname[])
{	//定义获取文件名函数
	int i,k=0,j,w_len=strlen(waddr);
	for(i=w_len-1;i>=0;i--)
	{
		if(waddr[i]=='\\')
			break;
		wname[k++]=waddr[i];
	}
	wname[k++]='\0';
	i=strlen(wname);
	for(j=0,k=i-1;j<i/2;j++,k--)
	{
		char temp=wname[j];wname[j]=wname[k];wname[k]=temp;
	}
}

int receiveFile(SOCKET sc)
{	//客户端接收文件函数
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


	FILE *fp;							//打开文件
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


void LongToChar(long x,char a[])
{	//将long型整数转换成字符串
	int count=0;
	if(x==0)
		return;
	while(x)
	{
		a[count++]=x%10+'0';
		x=x/10;
	}
	a[count]='\0';
	for(int i=0,j=count-1;i<count/2;i++,j--)
	{ char temp;
	  temp=a[i];
	  a[i]=a[j];
	  a[j]=temp;}
}

long StrToLong(char str[])
{	//将字符串转换成long型整数
	long result;
	bool bEnd;
	bEnd = true;
	result = 0;
	for ( int i = 0; bEnd; i++ )
	{
		if (str[i] != '\0' && str[i] >= '0' && str[i] <= '9')
		{
			result = result * 10 + str[i] - '0';
		}
		if (str[i] == '\0')
		{
			bEnd = false;
		}
	}
	return result;
}
