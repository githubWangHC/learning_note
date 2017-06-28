#include<stdio.h>
#include<stdlib.h>
int main()
{
	int c, i = 0;
	char a,b;
	char* str_all = &a;
	char* str_del = &b;
	printf("pless input the string cotain all \n");
/*
	scanf("%s",str_all);
	scanf("%s",str_del);
*/
	while((*str_all++ = getchar()) != '\n')
	;
	*--str_all = '\0';
	while((*str_del++ = getchar()) != '\n')
	;
	*--str_del = '\0';

	printf("str_all address is:%d\nstr_delet address is:%d",str_all, str_del);
	printf("str_all after scanf is:%s\n str_delet is :%s\n",str_all,str_del);
}
