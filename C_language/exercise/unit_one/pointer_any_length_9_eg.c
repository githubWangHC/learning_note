
#include<stdio.h>
int main()
{
	int c, nChar = 0, maxLeth = 0;
	char a;
	char* maxString;
	char* str_mv;
	maxString = &a; 
	str_mv = &a;
	while((c = getchar()) != EOF)
	{
		if('\n' != c)
		{
			*str_mv = c;
			str_mv++;
			nChar++;
		}else if(nChar > maxLeth)
			{
				maxString = str_mv; 
				maxLeth = nChar;
				nChar = 0;
			}
	}
/*
		maxString -= (maxLeth - 1);
*/
		maxString--;
		putchar(*maxString);
		maxString += 2;
		putchar(*maxString);
	/*for(int i = 0; i < maxLeth; i++)
	{
		putchar(*maxString);
		maxString++;
	}*/
/*
	printf("\n the max length string is %d, the max string is %s",maxLeth,maxString);
*/
}
