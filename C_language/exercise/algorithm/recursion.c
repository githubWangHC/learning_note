#include"sort.h"
int* divide_conquer(int n1, int num1[], int n2, int num2[]);
int num_sorted[N] = {0};
int recursion(int n, int number[]){
	if(n > 1){
		int n1 = n/2, n2 = n - n/2, j = 0, l = 0, n = 0;
		int num1[N] = {0}, num2[N] = {0};
		while(j < n1){
		num1[j] = number[n];
		n++;
		j++;
		}
		while(l < n2){
		num2[l] = number[n];
		n++;
		l++;
		}
		recursion(n1, num1);
		recursion(n2, num2);
		int i = 0, k = 0, m = 0;
		while((n1 != i) && (n2 != k)){
			if(num1[i] <= num2[k]){
				number[m] = num1[i];
				i++;
				m++;
			}else if(num1[i] >= num2[k]){
				number[m] = num2[k];
				k++;
				m++;
			}
		}
		if((n1 == i) || (n2 == k)){
			int j = 0;
			int *num_left = ((n1 == i) ? &num2[k] : &num1[i]);
			while(m <= n1 + n2){
				number[m] = *num_left;
				m++;
				num_left++;
			}
		}
	}
}
