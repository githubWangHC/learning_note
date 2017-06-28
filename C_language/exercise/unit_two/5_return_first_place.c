/*
 *this scrip is to return the first place in str1 which match with str2
 */
#include<stdio.h>
int str_delet_length = 0;
int firstPlace(char* str_all, char* str_dele_input);
int main()
{
	int c;
	char* str_find = "xyz";
	char* str_all = "abcdxeyfxg";/*这上下两行不能写在一起，至今仍然没有找到更好的方法来输入str_del*/
	/*printf("pless input the string cotain all \n");
	scanf("%s",str_all);*/
	while(*str_all++ != '\0')
	{
		str_delet_length++;
	}
	str_all -= (str_delet_length +1);
	firstPlace(str_all, str_find);
}
int firstPlace(char* str_all, char* str_find)
{
	char* str_temp = str_all;
	int no_match = 1;
	while(*str_find++ != '\0')
	{
		for(int i = 0; *str_all++ != '\0'; i++)
		{
			if(*(str_all - 1) == *(str_find-1))
			{
				printf("first place of '%c' is %d\n", *(str_find-1), i+1);
				str_all = str_temp;
				no_match = 0;
				break;
			}
			no_match = 1;
		}
		if(no_match)
		{
			printf("first place of '%c' is -1\n", *(str_find - 1));
			str_all = str_temp;
		}
	}
}
