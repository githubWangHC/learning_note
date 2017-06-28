
#include<stdio.h>
int reverse(char* str);
int main()
{
	char* str_input = "abcdefg";
	reverse(str_input);
}
int reverse(char* str)
{
	if(*str != '\0')
	{
		putchar(*str);
		reverse(++str);
	}
	putchar(*--str);
}
