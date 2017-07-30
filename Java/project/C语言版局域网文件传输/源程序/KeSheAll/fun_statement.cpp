//函数声明
void LongToChar(long x,char a[]);			//将long型整数转换成字符串

long StrToLong(char str[]);					//将字符串转换成long型整数

void getW_name(char waddr[],char wname[]);   //获取文件名函数

void sendFile(SOCKET ss);					 //服务端发送文件函数

int receiveFile(SOCKET sc);					//客户端接收文件函数

void chatting_client(SOCKET sc);				 //客户端聊天函数

void chatting_server(SOCKET s_d);			 //服务端聊天函数

int client();								 //客户端主程序

void server();								 //	服务端主程序