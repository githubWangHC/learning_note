void server()
{
	system("color 0a");						//�޸�DOS������ɫΪ0A
	WORD wVersion=MAKEWORD(1,1); 
	WSADATA wsData; 
	int nResult= WSAStartup(wVersion,&wsData); //1������Winsock
	if(nResult !=0) 
	{ 
	printf("����Winsockʧ��!\n"); 
	} 


	/*
		AF_INETָʹ�õ�Э����
		�ڶ���������ָ��socket���ͣ�	SOCK_STREAM--tcp���ͣ���֤����˳�򼰿ɿ���
		������������ͨ����ֵΪ0����ϵͳ�Զ�ѡ��
	*/
	SOCKET s=socket(AF_INET,SOCK_STREAM,IPPROTO_IP); //2.�����׽���
	if(s==INVALID_SOCKET) 
	{ 
	printf("�����׽���ʧ��!\n"); 
	} 

	//��IP�Ͷ˿�
	SOCKADDR_IN addr; 
	addr.sin_family=AF_INET; 
	addr.sin_port=htons(portNum); //��֤�ֽ�˳�� 
	addr.sin_addr.S_un.S_addr=htonl(INADDR_ANY);

	//3��bind() �׽��ֵİ�
	nResult=bind(s,(sockaddr*)&addr,sizeof(sockaddr)); 
	if(nResult==SOCKET_ERROR) 
	{ 
	printf("�׽��ֵİ�ʧ��!\n");
	} 

	//���5�����ӣ� �׽��ֵļ�������ʼ����
	nResult=listen(s,5); 
	if(nResult==SOCKET_ERROR) 
	{ 
	printf("�׽��ֵļ���(��������)ʧ��!\n"); 
	} 


	SOCKADDR_IN addrClient;
	int len=sizeof(SOCKADDR);
	printf("\n�ȴ��ͻ��˵�����");
	
	int a=0;
	while(a<5)										//��ʼ��һ��С��̬ͼ��
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
	SOCKET s_d=accept(s,(sockaddr*)&addrClient,&len); //5���׽��ֵȴ�����:�����������ˣ� 
	if(s_d==INVALID_SOCKET) 
	{ 
	printf("�׽��ֵȴ�����(��������)ʧ��!\n");
	}	
	char *success="�ɹ���½������!";


	/*
		��һ�����������Ͷ��׽���������
		�ڶ������������������ݵĻ�����
		���������������������ݵ��ֽڳ���
	*/
	send(s_d,success,strlen(success)+1,0);            //��ͻ��˷�����֤��Ϣ

	char mess[M]; 

	//��send�������Ӧ
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
	puts("            *********************************************************");
	puts("            *                                                       *");
	puts("            *         �� ӭ ʹ �� �� �� �� �� �� �� �� �� ��        *");
    puts("            *                     (��  ��  ��)                      *");
	puts("            *                                                       *");
	puts("            *                                                       *");
	puts("            *              1.   �� �� �� �� �� �� �� ��             *");
	puts("            *                                                       *");
	puts("            *              2.   �� �� �� �� �� �� �� ��             *");
	puts("            *                                                       *");
	puts("            *              3.         ��      ��                    *");
	puts("            *                                                       *");
	puts("            *                                                       *");
	puts("            *                     ���������γ����                *");
	puts("            *                         2017-06-19                    *");
	puts("            *                                                       *");
	puts("            *********************************************************\n");
	puts("ȫ��(�˳�ȫ��)�����밴��ALT+ENTER\n");
	printf("��������Ҫѡ���ģ��Ĵ���(1-3):");
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
			sendFile(s_d);
			goto again;
		}
		if(ch=='N'|| ch=='n')
		{
			printf("�Է��ܾ�����!\n");
			system("pause");
			goto again;
		}
	}

	if('2'==ch)									//��������ģ��
	{
		char ch;
		int flag=0;
		char *str="��������㷢������,�Ƿ����(Y/N):";
		send(s_d,str,strlen(str)+1,0);

		printf("�ȴ��ͻ��˻�Ӧ����\n");
	
		nResult=recv(s_d,&ch,sizeof(char),0);       //�жϿͻ���ͬ���ͬ��
		if(nResult == -1 )                         //�жϿͻ����Ƿ�ر�
		{
			printf("\n�ͻ����ѶϿ�\n");
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
			printf("�Է��ܾ�����!\n");
			system("pause");
			goto again;
		}
	}
	if('3'==ch)
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