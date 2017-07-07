#include<stdio.h>
#include "sort.h"

int main(){
	int number[N] = {0}, i = 0;
	printf("input N numubes\n");
	for(i = 0; i < N; i++){
	scanf("%d,", &number[i]);
}
//bubble(N, number);
//insertion(N, number);
//shell(N, number);
//recursion(N, number);
QuickSort(N, number);
	printf("after bubble sort the result is\n");
	for(i = 0; i < N; i++){
		printf("%d,", number[i]);
	}
}
