
=================
void类型指针=====
=================

1、void *表示一个“不知道类型”的指针，也就不知道从这个指针地址开始多少字节为一个数据。所有的指针变量都是int类型，和用int表示指针异曲同工，只是更明确是“指针”。因此void*只能表示一个地址，不能用来&取值，也不能++--移动指针，因此不知道多少字节是一个数据单位
2、void *就是一个不能动的“地址”，在进行&、移动指针之前必须转型为类型指针
3、void *的用途：在只知道内存，但是不知道是什么类型的时候。

void zeromem(void *data,int byteSize)
{
char * ptr = (char *)data;	//char就是一个字节，而计算机中是以字节为单位存储数据的，所以可以将任何类型的数据转换为char类型进行初始化
int i;
for(i=0;i<byteSize;i++)
{
*ptr=0;
ptr++;				//因为ptr是char*，所以逐字节移动
}
}
 
int main(int argc, char *argv[])
{
    int nums[20];
    memset(nums,0,sizeof(nums));	//用memset函数进行初始化
    
    Person p1;
    zeromem(&p1,sizeof(Person));	//用void指针调用函数进行初始化
     
    return 0;
}


==================
void类型的函数====
==================
￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥
#include <stdio.h>

typedef void (*intFunc) (int i);

void foreachnum(int *number, int length,intFunc funct)	//这个地方intFunc funct表示传入一个符合intFunc类型的函数，而foreachnum则用这个传入的函数处理数据
{
	int i=0;
	for(i=0;i<length;i++)
	{
		funct(number[i]);
	}
}

void print(int j)
{
	printf("%d\n",j);
}

int main(int argc, char *argv[])
{
	int nums[]={111,222,333,444,555,};
	foreachnum(nums, sizeof(nums)/sizeof(int),print);	//这个地方传入prin函数很重要，且这个print函数要符合intFunc的类型。
	return 0;
}
