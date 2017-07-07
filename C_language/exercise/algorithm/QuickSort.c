#include"sort.h"

int QuickSort(int n, int number[]){
	int i = 0, j = n-1, pivot = number[0];
	if(n >1){
		while(i < j){//之所以要设置一个循环而不是条件判断是要保证所有数组中的数据都要阿被分为大于pivot以及小于pivot两份，如果设置条件则仅仅从两侧判断一对。
			while((pivot < number[j]) && (j > i)){//之所以要写小于是为了保证一旦有大于的就跳出循环。不能将j--写道wile条件中，是因为有可能pivot > number[n-1]。
				j--;
			}
			if(i < j){
				number[i++] = number[j];//之所以要写i++是为了保证number[i]比过一次之后就不再比了。
			}
			while((pivot > number[i]) && (i < j)){
				i++;
			}
			if(i < j){
				number[j--] = number[i];
			}
		}
		if(i == j){
			number[i] = pivot;
			QuickSort(i, number);
			QuickSort(n - i - 1, &number[i+1]);
			return 0;
		}else{
			return 1;
		}
	}
}
