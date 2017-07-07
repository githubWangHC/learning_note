#include"sort.h"

int shell(int n, int number[]){
	int gap = n, i = 0, j = 0;
	for(gap = n/2; gap > 0; gap/=2){
		for(i = gap; i < n; i++){
			int tmp = number[i];
			for(j = i-gap; (j >= 0) && (number[j] > tmp); j -= gap){
				number[j + gap] = number[j];
			}
			number[j + gap] = tmp;
		}
	}
}
