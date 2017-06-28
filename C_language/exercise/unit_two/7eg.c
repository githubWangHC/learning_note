
#include<stdio.h>
char a;
char* str_input = &a;
int atoi(char* str);
int main()
{
	int c, i = 0;
	while((c = getchar()) != '\n')
	{
		*str_input = c;
		str_input++;
		i++;
	}
	*str_input = '\0';
	str_input -= i;
	printf("thr result is %d \n",atoi(str_input));
}
int atoi(char* str)
{
	int result = 0;
	while((*str) != '\0')
	{
		putchar('\n');
		putchar(*str);
		result = result * 10 + (*str - '0');	
		str++;
	}
	return result;
}

