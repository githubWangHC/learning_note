#include <stdio.h>

int main(){
	char str1[] = "hello";
	char *str2 = "word";
	char str3[10];
	printf("input value\n");
	scanf("%s",str2);
	printf("str1 is %s\nstr2 is %s\nstr3 is %s", str1, str2,str3);
}
