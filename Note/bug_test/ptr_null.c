
#include<stdio.h>
int c = 0, i = 0;
char a;
char* hex_str = &a;
int main()
{
        while((c = getchar()) != EOF)
        {
                *hex_str = c;
                putchar(*hex_str);
                hex_str++;
                i++;
        }
        hex_str--;
        while(i >= 0)
        {
                putchar(*hex_str);
                hex_str--;
                i--;
        }
}
