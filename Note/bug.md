=====================================================
#include<stdio.h>
int main()
{
        int c;
        int chose = 1;
        char* str_mv;
        char*  str_const;
        if(chose = 1)
        {
                c =getchar();
                *str_const = c;
                str_const++;
                c =getchar();
                *str_const = c;
        }
        putchar(*str_const);
        str_const--;
        putchar(*str_const);
        printf("\n%d\n",str_const);
}/*以上输入如果为ab则输出ba符合预期 
若将*号处的chose = 1改为chose ==1则输出以下错误
Segmentation fault (核心已转储)
===============================

