#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <Winsock2.h>
#include <process.h>
#include <time.h>
#pragma comment(lib,"Ws2_32")
#define M 512000							 //ÿ�鴫���ļ��Ĵ�С500k
#define portNum 5102						 //ʹ�õĶ˿ں�1024~60000

void LongToChar(long x,char a[]);			//��long������ת�����ַ���

long StrToLong(char str[]);					//���ַ���ת����long������

void getW_name(char waddr[],char wname[]);   //��ȡ�ļ�������

void sendFile(SOCKET ss);					 //����˷����ļ�����

int receiveFile(SOCKET sc);					//�ͻ��˽����ļ�����

int client();								 //�ͻ���������

void server();								 //�����������

//����������	
void main()
{	
	system("cls");
	system("@color 0a");
	puts("\n");
	puts("	  *********************************************************");
	puts("    	  *							  * ");
	puts("    	  *   ����ӭ��ʹ���á��֡��������ġ����������͡�������  *");
	puts("    	  *			     			          * ");
	puts("    	  *							  * ");
	puts("    	  *							  * ");
	puts("    	  *	 	       1.   ��  ��  ��  	    	  * ");
	puts("    	  *							  * ");
	puts("    	  *		       2.   ��  ��  �� 	    	          * ");
	puts("    	  *							  * ");
	puts("    	  *		       3.   ��      ��   		  * ");
	puts("    	  *							  * ");
	puts("    	  *							  * ");
	puts("    	  *							  * ");
	puts("    	  *			  2017-6-20 			  * ");
	puts("    	  *		      ������ѧ���������	  	  * ");
	puts("    	  *********************************************************\n");
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
		//����1ѡ��ͻ���
		client();
	}
	else if('2'==ch)
	{
		//����2ѡ������
		server();
	}
	else if('3'==ch)
	{
		exit(0);
	}
}




int client()
{
	system("color 0a");				//�޸�DOS������ɫ�������0A��
	//��ʼ���׽���
	WORD wVersion=MAKEWORD(1,1);	//Winsock�汾������1.1�棬֧��TCP/IPЭ��
	WSADATA wsData;					//����Windows Sockets����
	int nResult= WSAStartup(wVersion,&wsData);  //����WINSOCKET	
	if(nResult !=0) 
	{ 
	printf("����Winsockʧ��!\n"); 
	} 

	SOCKET sc=socket(AF_INET,SOCK_STREAM,IPPROTO_IP);  //�����׽���
	/*
	��һ������ָʹ�õ�Э���壬Э���������socket�ĵ�ַ���ͣ���ͨ���б�����ö�Ӧ�ĵ�ַ��
	AF_INET������Ҫ��ipv4��ַ��32λ�ģ���˿ںţ�16λ�ģ������
	�ڶ�������ָ��socket���ͣ�SOCK_STREAM -- TCP���ͣ���֤����˳�򼰿ɿ���
	��������������Э��
	*/
	if(sc==INVALID_SOCKET) 
	{ 
	printf("�����׽���ʧ��!\n"); 
	} 

	SOCKADDR_IN addrSc; //sockaddr ��һ��ͨ�õ�ַ�ṹ������Ϊ��ͳһ��ַ�ṹ�ı�ʾ������ͳһ�ӿں�����
	//ʹ��ͬ�ĵ�ַ�ṹ���Ա�bind() , connect() �Ⱥ������ã�struct sockaddr_in�е�in ��ʾinternet�����������ַ
	addrSc.sin_family=AF_INET;   //AF_INET��ַ��
	addrSc.sin_port=htons(portNum); //��֤�ֽ�˳�� 
	char IP[20];
again:
	printf("�������������IP��ַ��");
	gets(IP);	
	if( -1==inet_addr(IP) )   //ѭ�����IP��ַ�Ƿ�Ϸ�
	{
		printf("IP��ַ����!\n");
		goto again;
	}
	addrSc.sin_addr.S_un.S_addr=inet_addr(IP); //���Ϸ������������IP��ַ


	int b=0;
	while(b<5)               //���5�Σ�����������ڴ�ʱ�������������������
	{
		nResult=connect(sc,(SOCKADDR*)&addrSc,sizeof(SOCKADDR)); //�׽������� connect()
		Sleep((DWORD)100);				//��ʱ1��
/*		if(nResult==SOCKET_ERROR) 
		{ 
		printf("  %d ������ʧ��!\n",b+1);
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
	printf("���ӳ�ʱ�������µ�½!\n");
	goto again;
	}

	char *buf="���ӳɹ�!";                   //��������֤���ӳɹ�
	nResult=send(sc,buf,strlen(buf)+1,0); 
	if(nResult==SOCKET_ERROR)
	{ 
	printf("5.�׽��ַ�������ʧ��!\n");
	return 0;
	}
	char mess[M];
	nResult =recv(sc,mess,strlen(mess),0);     //���ܷ���˵�������֤��Ϣ
	if(nResult == -1 )                         //�жϷ�����Ƿ�ر�
	{
		printf("\n������ѶϿ�\n");
		system("pause");
		exit(0);
	}
	printf("%s\n",mess);

///////////////////////////////////////////////////////////////////////////
////                       ���ܲ˵�������                              ////
///////////////////////////////////////////////////////////////////////////

tianle:	
	system("cls");             //����
	system("@color 0a");
	puts("\n\n");
	puts("	  *********************************************************");
	puts("    	  *							  * ");
	puts("    	  *   ����ӭ��ʹ���á��֡��������ġ����������͡�������  *");
	puts("    	  *			(��   ��   ��)			  * ");
	puts("    	  *							  * ");
	puts("    	  *							  * ");
	puts("    	  *		   ��ȴ�����˵���Ӧ����		  * ");
	puts("    	  *							  * ");
	puts("    	  *							  * ");
	puts("    	  *		      	  	                          * ");
	puts("    	  *			  2017-6-20 			  * ");
	puts("    	  *		      	  	                          * ");
	puts("    	  *********************************************************\n");

		char rMess[100];
		nResult=recv(sc,rMess,strlen(rMess),0);         //���շ���˷����Ĳ�������
		if(nResult==-1)                              //�жϷ�����Ƿ�ر�
		{
		printf("\n������ѶϿ�\n");
		system("pause");
		goto tianle;
		}
		printf("%s\n",rMess);

		char ch;
		char str[100];
		gets(str);
		ch=str[0];
		while( ch!='Y' && ch!='y' && ch!='N' && ch!='n' ) //����ͻ��˵Ĵ�������
		{
			printf("��������,����������(Y/N)��");
			gets(str);
			ch=str[0];
		}
		
		send(sc,&ch,sizeof(char),0); //�����˷���ѡ�񣬲�ִ����Ӧ����
		if(strcmp(rMess,"��������㴫���ļ�,�Ƿ����(Y/N):")==0 && (ch=='Y' || ch=='y') )
		{
			receiveFile(sc);
		}
		if(ch=='N' || ch=='n')
		{
			printf("��ܾ���!\n");
			system("pause");
		}			
	system("cls");
	goto tianle;

	nResult=closesocket(sc);  //�ر��׽��֣��Ͽ�����
	if(nResult==SOCKET_ERROR) 
	{ 
	printf("8.�ر��׽���ʧ��!\n");
	return 0;
	} 
}

void server()
{
	system("color 0a");						//�޸�DOS������ɫΪ0A
	//��ʼ���׽���
	WORD wVersion=MAKEWORD(1,1); 
	WSADATA wsData; 
	int nResult= WSAStartup(wVersion,&wsData); //1������Winsock
	if(nResult !=0) 
	{ 
	printf("����Winsockʧ��!\n"); 
	} 

	SOCKET s=socket(AF_INET,SOCK_STREAM,IPPROTO_IP); //2.�����׽���
	if(s==INVALID_SOCKET) 
	{ 
	printf("�����׽���ʧ��!\n"); 
	} 

	SOCKADDR_IN addr; 
	addr.sin_family=AF_INET; 
	addr.sin_port=htons(portNum); //��֤�ֽ�˳�� 
	addr.sin_addr.S_un.S_addr=htonl(INADDR_ANY);
	nResult=bind(s,(sockaddr*)&addr,sizeof(sockaddr)); //3��bind()�׽��ְ󶨵�ָ����IP��ַ�Ͷ˿���
	if(nResult==SOCKET_ERROR) 
	{ 
	printf("�׽��ֵİ�ʧ��!\n");
	} 

	nResult=listen(s,5); //���5�����ӣ� �׽��ֵļ���listen()
	if(nResult==SOCKET_ERROR) 
	{ 
	printf("�׽��ֵļ���(��������)ʧ��!\n"); 
	} 


	SOCKADDR_IN addrClient;
	int len=sizeof(SOCKADDR);
	printf("\n�ȴ��ͻ��˵�����");
	
	int a=0;
	while(a<10)										//��ʼ��һ��С��̬ͼ��
	{
		for(int b=0;b<8;b++)
		{
			Sleep(100);
			printf(" . ");
		}
		Sleep(100);
		system("cls");
		printf("\n\r�ȴ��ͻ��˵�����");
		a++;
	}
while(1)
{
	SOCKET s_d=accept(s,(sockaddr*)&addrClient,&len); //5���׽��ֵȴ�����:�����������ˣ� accept()
	if(s_d==INVALID_SOCKET) 
	{ 
	printf("�׽��ֵȴ�����(��������)ʧ��!\n");
	}	
	char *success="�ɹ���½������!";
	send(s_d,success,strlen(success)+1,0);            //��ͻ��˷�����֤��Ϣ

	char mess[M]; 
	nResult=recv(s_d,mess,strlen(mess),0); 
	if(nResult == -1 )                               //�жϷ�����Ƿ�ر�
	{
		printf("\n�ͻ����ѶϿ�\n");
		system("pause");
		exit(0);
	}
	printf("\n%s",mess);

///////////////////////////////////////////////////////////////////////////
////                       ���ܲ˵�������                              ////
///////////////////////////////////////////////////////////////////////////
again:
	system("@color 0a");
	system("cls");
	puts("\n");
	puts("	  *********************************************************");
	puts("    	  *							  * ");
	puts("    	  *   ����ӭ��ʹ���á��֡��������ġ����������͡�������  *");
	puts("    	  *			(��   ��   ��)			  * ");
	puts("    	  *							  * ");
	puts("    	  *							  * ");
	puts("    	  *	 	  1.�� �� �� �� �� �� �� �� 		  * ");
	puts("    	  *							  * ");
	puts("    	  *							  * ");
	puts("    	  *		  2.��   ��   ��   ��    ��		  * ");
	puts("    	  *							  * ");
	puts("    	  *							  * ");
	puts("    	  *		      	  	                          * ");
	puts("    	  *			  2017-6-20 			  * ");
	puts("    	  *		      	  	                          * ");
	puts("    	  *********************************************************\n");
	printf("��������Ҫѡ���ģ��Ĵ���(1-2):");
	char ch;
	char Num[10];
	gets(Num);
	ch=Num[0];
	while(ch >='3' || ch <='0')						//����û�ѡ��Ĵ����Ƿ����Ҫ��
	{
		printf("\n�����������!���������룺");
		gets(Num);
		ch=Num[0];
	}
 
	if('1'==ch)								    	//�����ļ�ģ��Ĵ���
	{
		char ch;
		char *str="��������㴫���ļ�,�Ƿ����(Y/N):";
		send(s_d,str,strlen(str)+1,0);
		printf("�ȴ��ͻ��˻�Ӧ����\n");
		nResult=recv(s_d,&ch,sizeof(char),0);
		if(nResult == -1 )                         //�жϿͻ����Ƿ�ر�
		{
			printf("\n�ͻ����ѶϿ�\n");
			system("pause");
			goto again;
		}
		if( ch=='Y' || ch=='y')
		{	
			sendFile(s_d);						//Y ��ʼ�����ļ�
			goto again;
		}
		if(ch=='N'|| ch=='n')
		{
			printf("�Է��ܾ�����!\n");
			system("pause");
			goto again;
		}
	}

	if('2'==ch)
	{
		exit(0);          //��������
	}

}
	nResult=closesocket(s); //�ر��׽��֡�
	if(nResult==SOCKET_ERROR) 
	{ 
	printf("8.�ر��׽���(��������)ʧ��!\n");
	} 
}

void sendFile(SOCKET ss)
{
	char *str1="....ȷ�ϳɹ�....";
	send(ss,str1,strlen(str1)+1,0);			//��ͻ��˷�����֤��Ϣ

	FILE *fp;
	long i;									//�ļ��ֵĵĿ���

	char fileAddr[300];
	char fileName[100];
dmm:
	printf("������Ҫ���͵��ļ����Ե�ַ(���ļ���)��");

	gets(fileAddr);

	if( (fp=fopen(fileAddr,"rb")) == NULL)   //���ļ� fopen
	{
		printf("�ļ���ַ�������!\n");
		goto dmm;
	}
	getW_name(fileAddr,fileName);	
	send(ss,fileName,strlen(fileName)+1,0);  //�����ļ���

	fseek(fp,0L,2);
	long fileLength=ftell(fp);				//ȡ���ļ��ĳ���
	fseek(fp,0L,0);

	char Length[100];
	LongToChar(fileLength,Length);
	send(ss,Length,strlen(Length)+1,0);		//�����ļ���С���ͻ���


	if( 0 == fileLength%M )					//���ļ����зֿ�
	{
		i=fileLength/M;
	}
	else
	{
		i=fileLength/M+1;
	}
	
	char mess[M];
	long readLength;						//���ļ���ȡ�ĳ���
	long sendLength;						//�����ļ��ĳ���
	int k=1;								//���ڴ��͵�ģ���

	char *temp="��ʼ�����ļ�";				//��ͻ��˿�ʼ���ͣ�ȷ��ͬ����ʼ
	int nResult;
	nResult=recv(ss,mess,M,0);
	if(nResult == -1 )                   //�жϿͻ����Ƿ�ر�
	{
		printf("\n�ͻ����ѶϿ�\n");
		system("pause");
		return ;
	}
	send(ss,temp,strlen(temp)+1,0);
	printf("%s,�� %ld ģ�飺\n",mess,i);
	long total=0;
	while( k <= i )					//�����ڴ��͵�ģ��Ŵ��ڿ�������ʾ�������
	{	
		Sleep((DWORD)800);			//�˷ǳ���Ҫ�������˴���ʱ�䣬��֤�����ߵ�ͬ�� 800����
		readLength=fread(mess,sizeof(char),M,fp); //fread(void *buffer, size_t size, size_t count, FILE * stream);
												  //fread()����ÿ�δ�stream������ȡcount����Ԫ��ÿ����Ԫ��СΪsize���ֽڣ�
												  //����ȡ�����ݷŵ�buffer���ļ�����λ��ָ����� size * count �ֽڡ�
		sendLength=send(ss,mess,readLength,0);
		total+=sendLength;
		printf("\r�� %d ģ��   ��ȡ��%ldB  ���ͣ�%ldB",k,readLength,sendLength);
		printf("  ����: %2.2f%c  ģ�飺%ld / %ld",100*(total*1.0/fileLength),37,k,i);
		if(sendLength==SOCKET_ERROR)   
		{ 
		printf("ʧ��!");
		}	
		k++;
	}
	fclose(fp);
	printf("\n�ļ��������!\n");
	system("pause");
}

void getW_name(char waddr[],char wname[])
{	//�����ȡ�ļ�������
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
{	//�ͻ��˽����ļ�����
	system("color 0a");
	int nResult;
	char mess[M];
	nResult=recv(sc,mess,strlen(mess),0);    //ȷ���ļ�����ģ�����ӳɹ�
	if(nResult == -1 )                       //�жϷ�����Ƿ�ر�
	{
		printf("\n������ѶϿ�\n");
		system("pause");
		return 0;
	}
	printf("%s\n",mess);


	char fileName[50];
	nResult=recv(sc,fileName,50,0);       //�����ļ���
	if(nResult == -1 )                    //�жϷ�����Ƿ�ر�
	{
		printf("\n������ѶϿ�\n");
		system("pause");
		return 0;
	}
	nResult=recv(sc,mess,M,0);             //�����ļ��Ĵ�С
	if(nResult == -1 )                    //�жϷ�����Ƿ�ر�
	{
		printf("\n������ѶϿ�\n");
		system("pause");
		return 0;
	}

	long fileLength=StrToLong(mess);      //ȡ�Ĵ������ļ��ĳ���
	long i;	                              //����ļ�����

	if( 0 == fileLength%M )				  //���ļ���N��λ���зֿ�
	{
		i=fileLength/M;
	}
	else
	{
		i=fileLength/M+1;
	}


	FILE *fp;							//���ļ�
	int k=1;					    	//��ǰ���ڽ����ļ��Ŀ���
	long receiveLength;					//�����ļ��ĳ���
	long writeLength;					//��д���ļ����ַ�����
	fp=fopen(fileName,"wb");		
	char *temp="��ʼ�����ļ�";		
	send(sc,temp,strlen(temp)+1,0);		//��ͻ��˿�ʼ���ͣ�ȷ��ͬ����ʼ
	nResult=recv(sc,mess,M,0);			//���շ���˵�ȷ����Ϣ
	if(nResult == -1 )                  //�жϷ�����Ƿ�ر�
	{
		printf("\n������ѶϿ�\n");
		system("pause");
		return 0;
	}
	printf("%s,�� %ld ģ�飺\n",mess,i);

	long total=0;						//�ѽ����ļ��ĳ���
	while(k<=i)                         //ѭ���Ľ����ļ�
	{
		receiveLength=recv(sc,mess,M,0);
		if( receiveLength== -1 )        //�жϷ�����Ƿ�ر�
		{
		printf("\n������ѶϿ�\n");
		system("pause");
		return 0;
		}
		
		writeLength=fwrite(mess,sizeof(char),receiveLength,fp);
		printf("\r�� %d ģ��   ���գ�%ldB  д�룺%ldB",k,receiveLength,writeLength);
		total+=writeLength;
		printf("  ����: %2.2f%c  ģ�飺%ld / %ld",100*(total*1.0/fileLength),37,k,i);
		k++;
	}


	fclose(fp);
	printf("\n�ļ��������!\n");
	system("pause");
	return 0;
}


void LongToChar(long x,char a[])
{	//��long������ת�����ַ���
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
{	//���ַ���ת����long������
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
