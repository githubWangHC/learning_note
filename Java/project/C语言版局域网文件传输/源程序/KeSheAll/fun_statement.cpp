//��������
void LongToChar(long x,char a[]);			//��long������ת�����ַ���

long StrToLong(char str[]);					//���ַ���ת����long������

void getW_name(char waddr[],char wname[]);   //��ȡ�ļ�������

void sendFile(SOCKET ss);					 //����˷����ļ�����

int receiveFile(SOCKET sc);					//�ͻ��˽����ļ�����

void chatting_client(SOCKET sc);				 //�ͻ������캯��

void chatting_server(SOCKET s_d);			 //��������캯��

int client();								 //�ͻ���������

void server();								 //	�����������