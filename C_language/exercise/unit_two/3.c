/*
 *本代码用来将十六进制数字转化为十进制数字
 */
#include<stdio.h>
char a;
char* hex_str = &a;/*想用指针来保存输入的数据，使得出了while函数还能使用，则必须定义成全剧变量*/
int htoi(char hex_str_input[]);
int main()
{
	int c, i = 0, deci_result = 0;
	while((c = getchar()) != '\n')
	{
		*hex_str = c;
		hex_str++;
		i++;
	}
	*hex_str = '\0';
	hex_str = hex_str - i;
	deci_result = htoi(hex_str);
	if (-1 == deci_result)
		printf("the input is unlegel\n");
	else
		printf("the hexadecimal is %d", deci_result);
}
int htoi(char hex_str_input[])
{
	int i = 1, result = 0;
	if(('0' == hex_str_input[0]) && (('x' == hex_str_input[1]) || ('X' == hex_str_input[1])))
	{
		while(hex_str_input[++i] != '\0')
		{
			if((hex_str_input[i] >= '0') && (hex_str_input[i] <= '9'))
			{
				result = result * 16 + (hex_str_input[i] - '0');
			}else if((hex_str_input[i] >= 'a') && (hex_str_input[i] <= 'f'))
			{
				result = result * 16 + (hex_str_input[i] - 'a' +10);
			}else if((hex_str_input[i] >= 'A') && (hex_str_input[i] <= 'F'))
			{
				result = result * 16 + (hex_str_input[i] - 'A' +10);
			}else
			{
				return -1;
				break;
			}
		}
	}else
	{
		return -1;
	}
	return result;
}
