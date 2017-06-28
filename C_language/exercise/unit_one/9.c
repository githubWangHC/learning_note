/*将输入的单词输出，如果有多个空格则合并。
 *
 */
#include<stdio.h>
int main()
{
	int c, bef_is_space = 0;
	while((c = getchar())!=EOF)
	{
		if(' '== c)
		{
			bef_is_space = 1;
		}else
		{
			if(0 == bef_is_space)
			{
				putchar(c);
			}else if(1 == bef_is_space)
			{
				putchar(' ');
				putchar(c);
			}
			bef_is_space = 0;
		}
	}
}
