#include<stdio.h>
#include<stdlib.h>
/*
char temp1, temp2;这种方式定义的字符指针地址是一前一后，
先给str_all赋值abcdefg，再给str_delet赋值efgh，则后者会把前者覆盖掉导致前者成为fgh；
char *str_all = &temp1, *str_delet = &temp2;
*/
/*char *str_delet; 竟然不不能用这种方式进行初始化指针操作
str_delet = & temp2;
*/
/*char *str_delet; 竟然不不能用这种方式进行初始化指针操作
str_delet = & temp2;
*/
char temp1;
char* str_delet = &temp1;
int str_delet_length = 0;
int squeeze(char* str_all, char first_char);
int str_compare(char* str_part);
int main()
{
	int c, i = 0;
/*
char* str_all = (char *)malloc(sizeof(char)*100);
char* str_delet = (char *)malloc(sizeof(char)*100);
*/
	printf("pless input the string cotain all \n");
	char* str_all;
	scanf("%s",str_all);
	char* str_const = str_all;
	printf("pless input the string should delet \n");
	char* str_del;
	printf("str_all bef scanf is %s\n",str_all);
	scanf("%s",str_del);
	printf("str_all after scanf is %s\n",str_all);
	str_delet = str_del;
	printf("str_all address is:%d\nstr_delet address is:%d",str_all, str_delet);
}
