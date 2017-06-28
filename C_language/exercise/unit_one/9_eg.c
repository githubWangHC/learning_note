
#include<stdio.h>
int main()
{
	int c, nChar = 0, maxLeth = 0;
	char* maxString;
	char str[1024] = {'\0'};
	while((c = getchar()) != EOF)
	{
		if('\n' != c)
		{
			str[nChar] = c;
			nChar++;
		}else if(nChar > maxLeth)
			{
				maxString = str; 
				maxLeth = nChar;
				nChar = 0;
			}
	}
	printf("\n the max length string is %d, the max string is %s",maxLeth,maxString);
}
