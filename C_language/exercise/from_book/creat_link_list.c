//该文件演示了如何动态地存储数据
#include<stdio.h>
#include<stdlib.h>
typedef struct student
{
	int stu_number;
	struct student* stu_pointer;
}stu;
stu* creat_link(int total)
{
	stu *link_header, *link_bef, *link_next;
	int n = 0;
	do
	{
		n++;
		if(1 == n)
		{
			link_bef = (stu*) malloc (sizeof (stu));
			scanf("%d", & link_bef -> stu_number);
			link_header = link_bef;
		}else
		{
			link_next = (stu*) malloc (sizeof (stu));
			scanf("%d", & link_next -> stu_number);
			(*link_bef).stu_pointer = link_next;
			link_bef = link_next;
		}
	}while(n < total);
	return(link_header);
}
int main()
{
	int i = 0, total_size = 5;
	stu* data = creat_link(total_size);
	while(i < total_size)
	{
		printf("%d", (*data).stu_number);
		data = (*data).stu_pointer;
		i++;
	}
}
