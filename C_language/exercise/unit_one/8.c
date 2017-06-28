/*统计输入制表符、空格、行的个数
 *
 */
#include<stdio.h>
int main()
{
	int nTable = 0, nSpace = 0, nNewLine = 0,nOther = 0,c;
	while((c = getchar())!=EOF)
	{
		if('	'== c)
		nTable++;
		else if(' ' == c)
		nSpace++;
		else if('\n' == c)
		nNewLine++;
		else
		nOther++;
	}
	printf("\nTable个数是%d;空格个数是%d;换行个数是%d;其他是%d\n",nTable,nSpace,nNewLine,nOther);
}
