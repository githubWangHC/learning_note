#include<stdio.h>
#include"sort.h"
//直接插入排序(Insertion Sort)的基本思想是：每次将一个待排序的值按其大小插入到前面已经排好序的子序列中的适当位置，直到全部记录插入完成为止。
//设数组为a[0…n-1]。
//1. 初始时，a[0]自成1个有序区，无序区为a[1..n-1]。令i=1
//2. 将a[i]并入当前的有序区a[0…i-1]中形成a[0…i]的有序区间。
//3. i++并重复第二步直到i==n-1。排序完成。
// 下面给出严格按照定义书写的代码（由小到大排序）：
//
int swap(int num[], int j, int i);
int insertion_1(int n, int number[]){
	int i = 0, j = 0;
	for(i = 1; i < n; i++){
		int k = i;
		for(j = i - 1; j >= 0; j--){
			if((number[i] - number[j]) < 0){
				k = j;
			}//此段代码也可改为>0然后break;
		}
		swap(number, k, i);	
	}
}

int swap (int num[], int j, int i){
	int k = 0, tmp_1 = 0, tmp_i = num[i];
	for(k = i -1; k >= j; k--){
		num[k+1] = num[k]; 
	}
	num[j] = tmp_i;
}
//经过简化得到的结果如下
int insertion(int n, int number[]){
	int i = 0, j = 0, tmp = 0;
	for(i = 1; i < n; i++){
		tmp = number[i];
		for(j = i - 1; (j >= 0) && (number[j] > tmp); j--){
			number[j+1] = number[j];
			}
		number[j + 1] = tmp;
	}
}
