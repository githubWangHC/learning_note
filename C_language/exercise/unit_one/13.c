/*编写一个程序，打印输入中单词的直方图
 *
 */
#include<stdio.h>
int main()
{
	int nChar = 0, c, innerWorld = 0;
	while((c = getchar()) != EOF)
	{
		if((c >= 'a' & c <= 'z')|(c >= 'A' & c <= 'Z'))
		{
			++nChar;
			innerWorld = 1;
		}else if(1 == innerWorld)
		{
			putchar('\n');
			for(int i = 0; i< nChar; i++)
			{
				putchar('*');
			}
			nChar = 0;
			innerWorld = 0;
		}
	}
}
