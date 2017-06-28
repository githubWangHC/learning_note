/*
 *this scrip is to delet all the string in str1 which match with str2
 */
#include<stdio.h>
int str_delet_length = 0;
int squeeze(char* str_all, char* str_dele_input);
int str_compare(char* str_part, char* str_dele_input);
int main()
{
	int c;
	char* str_all;/*这上下两行不能写在一起，至今仍然没有找到更好的方法来输入str_del*/
	char* str_del = "xyz";
	printf("pless input the string cotain all \n");
	scanf("%s",str_all);
	while(*str_del++ != '\0')
	{
		str_delet_length++;
	}
	str_del -= (str_delet_length +1);
	squeeze(str_all, str_del);
	printf("\nthe result:%s", str_all);
}
int squeeze(char* str_input, char* str_dele_input)
{
	while(*str_input++ != '\0')
	{
		if(*str_input == *str_dele_input)
		{
			char* str_temp;
			str_temp = str_input;
			if(str_compare(str_input, str_dele_input))
			{
				while((*str_input++ = *(str_input -1 + str_delet_length)) != '\0')
				;
			}
			str_input = str_temp;
		}
	}
}
int str_compare(char* str_part, char* str_delet)
{
	int is_match = 1;
	while((*str_part++ != *str_delet++) && (*(str_delet-1) != '\0'))
	{
		is_match = 0;
		return is_match;
	}
	return is_match;
}
