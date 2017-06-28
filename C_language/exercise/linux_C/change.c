#include <stdio.h>

void change (int * a, int * b){
	int tmp = *a;
	*a = *b;
	*b = tmp;
}

int main (){
	int a =1;
	int b =2;
	change (&a, &b);
	printf("a=%d,b=%d",a, b);
}
