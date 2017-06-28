
#include<stdio.h>
int itoaa(int i);
int main()
{
	int i = -12345;
	itoaa(i);
}
int itoaa(int i)
{
	if(i<0)
{
	putchar('-');
	i = -i;
}
	if(i/10)
		itoaa(i/10);
	putchar('0'+i%10);/*这里不能使用else选择语句
			打印完1之后将i=12带入才能打印2
			将i=123带入才能打印3～～～*/
}
