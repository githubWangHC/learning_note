#include"sort.h"

int MinHeapFixup(int i, int number[]){
	int tmp = number[i], j = (i - 1)/2;
	while((j > 0) && (number[j] > number[i])){
		number[i] = number[j];
		i = j;
		j = (i - 1)/2;
	}
	number[j] = tmp;
	return 0;
}

int MinHeapAdd(int num_add, int num, int number[]){
	number[num] = num_add;
	MinHeapFixup(num + 1, number);
	return 0;
}

int MinHeapSort(int num, int start, int number[]){
	int father = start, son = 2 * father + 1, tmp = number[start];
	while((son < num)){
		if((son + 1 <= num) && (number[son] >= number[son +1])){
//if(number[father] > number[son + 1])这一行代码不能要，因为即使父节点不大于子节点的最小值，在以后也应该从子节点的最小值处开始判断，因为这个节点需要互换的概率比另外一个大。
			son++;
			//else
			//	break; 合并到下一行就好了
			}
		if(number[father] < number[son])
			break;
		number[father] = number[son];
		father = son;
		son = 2 * father + 1;	
	}
		number[son] = tmp;
}

int MinHeapDel(int num, int number[]){
	number[0] = number[num - 1];
	MinHeapSort(num - 1, 0, number);
}

int MakeMinHeap(int num, int number[]){
	int i = num/2;
	for(i = num/2; i > 0; i--){
		MinHeapSort(num, i, number);
	}
}
