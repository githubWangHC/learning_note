###方法#
方法是类的功能，类的功能只能通过方法来实现；
方法必须属于某一个类，即方法必须定义在一个类中；
普通方法的调用是对象+.+方法名+括号；
普通方法可以操作调用它的对象的属性；
形参就是函数定义时在参数列表中的参数，实参是对象在调用方法时给形式参数的值，可以是直接的数值也可以是变量；
方法签名仅由方法名和形参决定，与方法其他属性无关。
方法名相同方法签名不同的方法之间关系为重载。
重载时参数类型的匹配原则是大于等于实参精度中挑一个精度最小的。
byte,int, long, double,float, double
实参为int则依次查询是否有long, double,float, double的形参。
当形参为一个类时形参与实参之间的参数传递是引用之间的赋值。
在一个类中方法外的变量为实例变量，方法内的变量为局部变量。二者重名时遵循先局部后实例的原则，要打破这种规则需要用this关键字。打破规则的意义是局部变量很难传递，而实例变量是类的属性很容易在方法结束后别的地方获得。
构造方法指的是与**类**同名，无返回值的方法。一个类至少要有一个构造方法，如果自己添加了一个带形参的构造方法则java不会再自动添加那个无形参的构造方法，如果new创建类的时候如果括号内没有参数则会报错。
一个构造方法可以条用另外一个构造方法或者普通方法；但是普通方法不能调用构造方法。
###java中的package###
package的全路径名从源代码的根目录开始，专有名词为包的全限定名。
当需要在某一个类中引入**不在一个包中**的其他类时需要用package+其他类的全限定名将其他类所在的包引入，并且在创建这个类的对象时也要用全限定名。
如果想在不属于某一个类所在的文件夹内javac一个类也要加上这个类的路径。
###import#
类的全限定名为类所在包的全限定名+.+类名。
为了避免在创建不属于同一个包中的类的对象时必须要加上类的权限定名的限制，可以使用import来预先引入这个类。
**import的两种用法**
1. import+ +类的全限定名+;
2. import+ +包的全限定名+.+*+;
第一种方法是精确引用，如果用第一种方法引入两个同名的类则java是不允许的；
第二种是粗略引入，引入两个同名的类java是允许的，但是在创建同名类时需要加上类的全限定名来确定创建的是哪个类；
同时用第一种与第二种引入从而造成了类同名，在默认的情况下创建的类是由第一种方法引入的那个类，要打破默认使用第二种方法的类需要用全限定名。
第一种方法引入的类与创建这个类处于同一个包中的类冲突仍然优先使用第一种情况下引入的类。
String类所在的包中有很多类，java会默认地帮我们引入: import java.lang.*;
###命名习惯###
1. 局部变量首字母小写myCar;
2. 成员变量mMyCar;mColor;
3. 类名首字母大写，Car;
4. 方法名，第一个单词为动词，首字母小写；
5. 参数名，p开头的局部变量形式；
##注释#
//

/*        */

/**
*
*
*/
##获得数组长度*length*#
		int[] arr = new int[9];
		final int len = arr.length;
final关键字修饰的变量必须在创建时赋值，且赋值之后不允许改变。
**多维数组的长度**
		int[][] arr = new int[2][10];
		arr.length为2；
		arr[1].length为10；
##clone##
		int[] arr1 = new int[10];
		int[] arr2 = arr1.clone();
		int[][] arr3 = new int[2][10];
		int[] arr4 = arr3[1].clone();
一维数组调用clone后获得的是原一维数组的数值，改变arr1[1]中的数据不会对arr2[1]产生影响，同样改变arr3[1][1]中的数据不会对arr4[1]中的数据产生影响；
		int[][] arr3 = new int[2][10];
		int[][] arr4 = arr3.clone();
二维或更高维数组获得指向原数组对象的引用，改变arr4[1][1]的数值会对arr3[1][1]数值产生影响。

##String##
**获得字符串长度**
		String str = "ABC";
		int len = str.length();		//字符串的length与数组的length方法不太一样？
**获得字符串中第几个字符**
		String str = "ABC";
		char ch = str.charAt(0);	//获取str字符串的第一个字符
**判断字符是否相等**
		char a = 'a';
		char b = 'a';
		boolean isEqual = (a == b);
**获取字符串的一部分**
		String str = "ABCDEFG";
		String subStr1 = str.subString(1,3);	//得到BC
		String subStr2 = str.subString(1);	//得到BCDEFG
**判断字符串是否相等**
		String str1 = "ABC";
		String str2 = "ABC";
		boolean isEque12 = str1.equals(str2);
**引用的相等**
		String str1 = "ABC";
		String str2 = "ABC";
		String str3 = str1
		boolean isEque12 = (str1 == str2);	//false
		boolean isEque13 = (str1 == str3);	//true
**判断字符串的开头和结尾**
		String str1 = "ABCDEFGH";
		String str2 = "ABC";
		String str3 = "GH";
		boolean started1 = str1.startsWith(str2);	//true
		boolean end2 = str1.endsWith(str3);	//true
**分割字符串**
		String str = "ABCD*EFGH*IJK";
		String[] pieces = str.split("*");
		int length = pieces.length();
		for(i = 0; i< length; i++){
			System.out.println(pieces[i]);
		}
**查找字符串**
		String str = "ABCD";
		int index1 = str.indexOf('C');		//结果为2
		int index2 = str.indexOf('F');		//结果为-1
		int index3 = str.indexOf(“CD”);		//结果为2
		int index2 = str.indexOf(”FG“);		//结果为-1
**替换字符串中的内容**
		String str = "ABCDEFGH";
		String str1 = str.replace('C', 'a');
		String str2 = str.replace("CD", "EF");
*上述操作过程中原始的str内容都没有改变，String又重新创建了一个字符串*

