#include<stdio.h>
int main()
{
	int c;
	char a;
	char* str_const = &a;
	for(int i = 0; i < 6; i++)
	{
		c =getchar();
		*str_const = c;
		str_const++;
	}
	putchar(*str_const);
	str_const--;
	putchar(*str_const);
}
