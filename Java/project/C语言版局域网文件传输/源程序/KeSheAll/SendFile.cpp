//�����ļ�
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

	//�Զ���д��ʽ��һ���������ļ���ֻ�����/д����
	if( (fp=fopen(fileAddr,"rb")) == NULL)   //���ļ�
	{
		printf("�ļ���ַ�������!\n");
		goto dmm;
	}
	getW_name(fileAddr,fileName);	
	send(ss,fileName,strlen(fileName)+1,0);  //�����ļ���

	//�����ƶ��ļ����Ķ�дλ�ã����óɹ�����0
	fseek(fp,0L,2);

	//ftell()��ȡ�ļ���дָ��ĵ�ǰλ�ã��ɹ����ص�ǰλ��
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
	while( k <= i )
	{	
		Sleep((DWORD)800);			//�˷ǳ���Ҫ�������˴���ʱ�䣬��֤�����ߵ�ͬ��
		readLength=fread(mess,sizeof(char),M,fp);
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


//�����ļ�
int receiveFile(SOCKET sc)
{
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


	FILE *fp;							//�� �ļ�
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
