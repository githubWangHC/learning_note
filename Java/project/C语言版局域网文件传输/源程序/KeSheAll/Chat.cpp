//����ͻ��˳���
void chatting_client(SOCKET sc)
{
	system("cls");
	system("color 0a");
	int receiveLength; 
	char SMess[1000];			//������Ϣ�Ļ���
	char RMess[1000];			//������Ϣ�Ļ���
	char SName[200];			//������û�������
	char CName[200];			//�ͻ����û�������
	int nResult;

	nResult=recv(sc,RMess,strlen(RMess),0);     //ȷ���ļ�����ģ�����ӳɹ�
	if( nResult== -1 )           //�жϷ�����Ƿ�ر�
	{
		printf("\n������ѶϿ�\n");
		system("pause");
		return ;
	}
	printf("%s\n",RMess);

	printf("������������֣�");      //�ͻ����û�������
	gets(CName);

	while(strlen(CName)==0)
	{
		printf("�û�������Ϊ��! ������������֣�\n");
		gets(CName);
	}

	send(sc,CName,strlen(CName)+1,0);   //�����˷�������
	receiveLength=recv(sc,SName,200,0); //���շ�����û�������
	if(receiveLength==-1)                //�жϷ�����Ƿ�ر�
	{
		printf("\n������ѶϿ�\n");
		system("pause");
		return ;
	}
	printf("���Ѻ�%sȡ�����ӡ���\n",SName);
	printf("��ʼ����(����exit�˳��������)����\n");

	while(1)
	{
		struct tm *local; 
		time_t t;						//����ʱ�亯������ʾ�û��Ĳ���ʱ��
		t=time(NULL); 
		local=localtime(&t); 
		nResult=recv(sc,RMess,M,0);		 //���շ���˷��͹�������Ϣ
		if(nResult == -1)                //�жϷ�����Ƿ�ر�
		{
			printf("\n������ѶϿ�\n");
			system("pause");
			return ;
		}

		if(strcmp(RMess,"exit")==0)
		{
			printf("��������˳��������,������Ҳ�����˳�!\n");
			system("pause");
			break;
		}
		printf("%s\t%d:%d:%d  \n    %s\n",SName,local->tm_hour,local->tm_min,local->tm_sec,RMess);	

		printf("\n��������Ϣ��");      //�ͻ����û�������Ϣ
		gets(SMess);
		if(strcmp(SMess,"exit")==0)
		{
		send(sc,SMess,strlen(SMess)+1,0);
		printf("�����˳��������!\n");
		system("pause");
		break;
		}
		//�������ʾ�Լ�����Ϣ
		t=time(NULL); 
		local=localtime(&t); 
		printf("\r%s\t%d:%d:%d  \n    %s\n",CName,local->tm_hour,local->tm_min,local->tm_sec,SMess);  //SMess������Ϣ�Ļ���
		send(sc,SMess,strlen(SMess)+1,0);
	}
}

//�������˳���
void chatting_server(SOCKET s_d)
{
	system("color 0a");
	char *str1="��ͬ���ˡ���";
	send(s_d,str1,strlen(str1)+1,0);

	int receiveLength;			//������Ϣ�ĳ���
	char SMess[1000];			//������Ϣ�Ļ���
	char RMess[1000];			//������Ϣ�Ļ���
	char SName[200];			//������û�������
	char CName[200];			//�ͻ����û�������


	receiveLength=recv(s_d,CName,200,0);
	if(receiveLength==-1)		 //�жϿͻ����Ƿ�ر�
	{
		printf("�ͻ����ѶϿ�\n");
		system("pause");
		return ;
	}
	 printf("���Ѻ�%sȡ�����ӡ���\n",CName);

	printf("������������֣�");
	gets(SName);
	while(strlen(SName)==0)		//ѭ������û����Ƿ�Ϸ�
	{
		printf("�û�������Ϊ��!  ������������֣�\n");
		getchar();
		gets(SName);
	}

	send(s_d,SName,strlen(SName)+1,0);	//��ͻ��˷�������
	printf("\n��ʼ����(����exit�˳��������)����\n");
	while(1)
	{
		struct tm *local; 
		time_t t; 
		t=time(NULL); 
		local=localtime(&t); 
		printf("\n\n��������Ϣ��");
		gets(SMess);
		if(strcmp(SMess,"exit")==0)   //�û����롮exit���˳�����
		{
		send(s_d,SMess,strlen(SMess)+1,0);
		printf("�����˳��������!\n");
		system("pause");
		break;
		}
		//�ڷ������ʾ�Լ�����Ϣ
		printf("\r%s\t%d:%d:%d\n    %s\n",SName,local->tm_hour,local->tm_min,local->tm_sec,SMess);
		send(s_d,SMess,strlen(SMess)+1,0);
		
		//�ڷ������ʾ�ͻ�����Ϣ
		t=time(NULL); 
		local=localtime(&t); 
		receiveLength=recv(s_d,RMess,M,0);
		if(receiveLength==-1)		 //�жϿͻ����Ƿ�ر�
		{
			printf("�ͻ����ѶϿ�\n");
			system("pause");
			return ;
		}
		if(strcmp(RMess,"exit")==0)
		{
			printf("\n�ͻ������˳��������,������Ҳ�����˳�!\n");
			system("pause");
			break;
		}
		printf("\n%s\t%d:%d:%d\n    %s\n",CName,local->tm_hour,local->tm_min,local->tm_sec,RMess);
	}

}