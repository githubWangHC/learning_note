# sizeof
**sizeof类型名**
* 1、如果在作用域内，变量以数组形式声明，则可以使用sizeof求数组大小：  
在同一个函数内  
```
{
	int num[]={5,89,33,25,99};
	int len = sizeof(num)/sizeof(int); //可以计算出num的长度为5；
}
```
但是在调用函数
`void printEach(int *nums)`
中使用
`int len = sizeof(num)/sizeof(int)`
来计算nums的长度是得不到传入数组的长度的，因为在编译时定义函数没有接收到main函数传入的数组，在编译时计算机只知道nums是一个int类型的指针，因此len永远都是1；
**sizeof表达式**
“sizeof 表达式”中的子表达式并不求值，而只是根据类型转换规则求得子表达式的类型，然后把这种类型所占的字节数作为整个表达式的值。有些人喜欢写成“sizeof(表达式)”的形式也可以，这里的括号和return(1);的括号一样，不起任何作用。但另外一种形式“sizeof(类型名)”的括号则是必须写的，整个表达式的值也是这种类型所占的字节数。  
比如用sizeof运算符求一个数组的长度：  
```
int a[12];
printf("%d\n", sizeof a/sizeof a[0]);
```
在上面这个例子中，由于sizeof 表达式中的子表达式不需要求值，所以不需要到运行时才计算，事实上在编译时就知道sizeof a的值是48，sizeof a[0]的值是4，所以在编译时就已经把sizeof a/sizeof a[0]替换成常量12了，这是一个常量表达式。


* 2、sizeof是编译器在编译的时候计算的，无法动态计算。因此对于int *或者将数组传递给函数，那么就无法使用sizeof获取大小了。即使函数声明中写着int[]也不行（为了避免误解，不要在参数中声明数组类型）。因此一般给函数传递数组/字符串的时候都要求额外传递“长度”参数，因为函数内部也不知道“有多长”。
# strlen
```
memcpy(void * restrict, const void * restrict, size_t);
char hex_username[200]={0};
char hex_password[200]={0};
mysql_hex_string(hex_username,username_get,strlen(username_get));	//这个地方只能用strlen不能用sizeof因为前者表面变量实际用掉的内存，而后者表面定义的内存大小，如果定义的内存过大容易造成程序崩溃
mysql_hex_string(hex_password,password_get,strlen(password_get));
sprintf(html_to_mysql,"insert into t_employees(name,number) value(0x%s,0x%s)",hex_username,hex_password);	//0x，零xyz的x
```
*sizeof 与 strlen的区别*
``
char arr[10] = "What?";
int len_one = strlen(arr);
int len_two = sizeof(arr); 
len_one和len_two的结果分别为5 and 10
```
点评：sizeof返回定义arr数组时，编译器为其分配的数组空间大小，不关心里面存了多少数据。strlen只关心ｓ实际存储的数据内容，不关心空间的大小和类型。
# memset
使用memset函数初始化数据  
memset(void *,要填充的数据,要填充的字节个数)  
```
char ch1[20]; 
memset(ch1,0,sizeof(ch1));
printf("%s\n",ch1);
```
输出的字符串是空，就是没有任何字符。因为0表示字符串的结尾，
思考：如果把memset(ch1,0,sizeof(ch1)); 改成memset(ch1,1,sizeof(ch1)); 为什么会多输出点东西？因为没有输入终止符号'\0'或者0所以priintf函数不知道什么时候结束，所以最后就输出乱码了。感觉没用呀，为何不在定义的时候就初始化，还再来个这个函数。  
填充一部分数据剩下的让编译器填写
2)char ch1[20] ={0}; 对于长度为20字节的这段内容全部填充为0。原理：int num[10]={3,5};//前两个分别填充3和5，之后都填充为0。因此char ch1[20] ={0}; 等价于char ch1[20] ={}; 
