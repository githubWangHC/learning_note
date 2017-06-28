/*该脚本用来实现itoa()函数；
 */
#include<stdio.h>
int main()
{
	int a = 0, int_input = 0;
	char* str_store = &a;
	printf("please input the integer should transfer\n");
	scanf("%d", &int_input);
	itoa(int_input, str_store);
}
int itoa(int int_input, char* str_store)
{
	int i_length = 0, int_get = int_input;
	char a;
	while(int_get /= 10)
	{
		i_length++;
	}
	do
	{
		*str_store++ = (char)(int_input%10);
		i++;
	}while(int_input/=10)

}
