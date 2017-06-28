
#include<stdio.h>
#define MAX = 30
/*
 * 这个脚本用来自动折断过宽的行
char a;
char* str_input = & a;

int c, line_length = 0;
int n = MAX;
*/
char a;
char* str_input = & a;

int c, line_length = 0, n = 10;

int outputStr(char* str, int line_length);
int printfStr(char* str, int str_length);

int main()
{
	while((c = getchar()) != EOF)
	{
		if ('\n' != c)
		{
			*str_input = c;
			str_input++;
			line_length++;
		}else
		{
			*str_input = '\0';
			outputStr(str_input, line_length);
			line_length = 0;
		}
	}
}

int outputStr(char* str, int line_length)
{
	int length_back = 0;
	if (line_length <= n)
	{
		printf("%s",str - line_length);
	}else
	{
		while((*str != ' ')&(*str != '\t'))
		{
			str--;
			length_back++;
		}
		printfStr(str - line_length + length_back, line_length - length_back - 1);
		putchar('\n');
		printfStr(str+1, length_back);
	}
}

int printfStr(char* str, int str_length)
{
	while(str_length >= 0)
	{
		putchar(*str);
		str++;
		str_length--;
	} 
}
