int client()
{
	system("color 0a");				//�޸�DOS������ɫ�������0A��

	WORD wVersion=MAKEWORD(1,1); 
	WSADATA wsData; 
	int nResult= WSAStartup(wVersion,&wsData);  //����WINSOCKET	
	if(nResult !=0) 
	{ 
	printf("����Winsockʧ��!\n"); 
	} 

	SOCKET sc=socket(AF_INET,SOCK_STREAM,IPPROTO_IP);  //�����׽���
	if(sc==INVALID_SOCKET) 
	{ 
	printf("�����׽���ʧ��!\n"); 
	} 

	SOCKADDR_IN addrSc;

	//familyָ��Э����
	addrSc.sin_family=AF_INET; //ipv4
	addrSc.sin_port=htons(portNum); //��֤�ֽ�˳�� 
	char IP[20];
again:
	printf("�������������IP��ַ��");
	gets(IP);	

	//ѭ�����IP��ַ�Ƿ�Ϸ�
	if( -1==inet_addr(IP) )  
	{
		printf("IP��ַ����!\n");
		goto again;
	}
	addrSc.sin_addr.S_un.S_addr=inet_addr(IP); 


	int b=0;
	while(b<5)               //���5�Σ�����������ڴ�ʱ�������������������
	{
		nResult=connect(sc,(SOCKADDR*)&addrSc,sizeof(SOCKADDR)); //�׽�������
		Sleep((DWORD)100);				//��ʱ1��
		if(nResult==SOCKET_ERROR) 
		{ 
		printf("  %d ������ʧ��!\n",b+1);
		}
		else
			break;
		b++;
	}
	if(nResult==SOCKET_ERROR)
	{
	printf("��½��ʱ�������µ�½!\n");
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
	puts("\n\n");
	puts("            *********************************************************");
	puts("            *                                                       *");
	puts("            *         �� ӭ ʹ �� �� �� �� �� �� �� �� �� ��        *");
    puts("            *                      (��  ��  ��)                     *");
	puts("            *                                                       *");
	puts("            *                                                       *");
	puts("            *                                                       *");
	puts("            *                                                       *");
	puts("            *                 ��ȴ�����˵���Ӧ����                *");
	puts("            *                                                       *");
	puts("            *                                                       *");
	puts("            *                                                       *");
	puts("            *                                                       *");
	puts("            *                     ���������γ����                *");
	puts("            *                         2017-06-19                    *");
	puts("            *                                                       *");
	puts("            *********************************************************\n");
	puts("ȫ��(�˳�ȫ��)�����밴��ALT+ENTER\n");

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
		if(strcmp(rMess,"��������㷢������,�Ƿ����(Y/N):")==0 && (ch=='Y' || ch=='y') )
		{
			chatting_client(sc);
		}
		if(ch=='N' || ch=='n')
		{
			printf("��ܾ���!\n");
			system("pause");
		}			
	system("cls");
	goto tianle;

	nResult=closesocket(sc);  //�ر��׽���
	if(nResult==SOCKET_ERROR) 
	{ 
	printf("8.�ر��׽���ʧ��!\n");
	return 0;
	} 
}
