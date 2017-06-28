
#include<stdio.h>
char a;
char* str_input = &a;
int del_notes(char* str_input);
int main()
{
	int c, i = 0;
	while((c = getchar()) != EOF)
	{
		*str_input = c;
		str_input++;
		i++;
	}
	*str_input = '\0';
	str_input -= i;
	del_notes(str_input);
}
int del_notes(char str[])
{
	int i = -1, notes_beg = 0, notes_end = 1;
	while(str[++i] != '\0')
	{
		if(('/' == str[i])&&('*' == str[i+1]))
		{
			notes_beg = 1;
			notes_end = 0;
		}else if(('*' == str[i])&&('/' == str[i+1]))
		{
			notes_beg = 0;
			notes_end = 1;
			i++;
		}else if ((0 == notes_beg)&&(1 == notes_end))
		{
			putchar(str[i]);
		}
	}
}
