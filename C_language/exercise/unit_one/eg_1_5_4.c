/*统计输入单词、行的个数
 *
 */
#include<stdio.h>
int main()
{
	int c, nWorld = 0, nLine = 0, nChar =0, inner_world = 0;
	while((c=getchar())!=EOF)
	{
		nChar++;
		if('\n'==c)
		{
			++nLine;
			if(inner_world = 1)
			{
				++nWorld;
			}
			inner_world = 0;
		}else if((' '==c)|('\t'==c))
		{
			if(inner_world = 1)
			{
				++nWorld;
			}
			inner_world = 0;
		}else
		{
			inner_world =1;
		}
	}
	printf("\n word num is %d;line num is %d; char num is %d",nWorld,nLine,nChar);
}

