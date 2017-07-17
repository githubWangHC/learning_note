### 方法
方法是类的功能，类的功能只能通过方法来实现；  
方法必须属于某一个类，即方法必须定义在一个类中；  
普通方法的调用是对象+.+方法名+括号；  
普通方法可以操作调用它的对象的属性；  
形参就是函数定义时在参数列表中的参数，实参是对象在调用方法时给形式参数的值，可以是直接的数值也可以是变量；  
方法签名仅由方法名和形参决定，与方法其他属性无关。  
方法名相同方法签名不同的方法之间关系为**重载**。  
重载时参数类型的匹配原则是大于等于实参精度中挑一个精度最小的。  
byte,int, long, double,float, double  
实参为int则依次查询是否有long, double,float, double的形参。  
当形参为一个类时形参与实参之间的参数传递是引用之间的赋值。  
在一个类中方法外的变量为实例变量，方法内的变量为局部变量。二者重名时遵循先局部后实例的原则，要打破这种规则需要用this关键字。打破规则的意义是局部变量很难传递，而实例变量是类的属性很容易在方法结束后别的地方获得。  
构造方法指的是与**类**同名，无返回值(不能用void，方法体内可以有return语句)的方法。一个类至少要有一个构造方法，如果自己添加了一个带形参的构造方法则java不会再自动添加那个无形参的构造方法，如果new创建类的时候如果括号内没有参数则会报错。  
一个构造方法可以条用另外一个构造方法或者普通方法；但是普通方法不能调用构造方法。  
### java中的package
package的全路径名从源代码的根目录开始，专有名词为包的全限定名。  
当需要在某一个类中引入**不在一个包中**的其他类时需要用package+其他类的全限定名将其他类所在的包引入，并且在创建这个类的对象时也要用全限定名。  
如果想在不属于某一个类所在的文件夹内javac一个类也要加上这个类的路径。  
### import
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
### 命名习惯
1. 局部变量首字母小写myCar;  
2. 成员变量mMyCar;mColor;  
3. 类名首字母大写，Car;  
4. 方法名，第一个单词为动词，首字母小写；  
5. 参数名，p开头的局部变量形式；  
## 注释
```
//


/*        */


/**
*
*
*/
```
## 获得数组长度*length*
		int[] arr = new int[9];
		final int len = arr.length;
final关键字修饰的变量必须在创建时赋值，且赋值之后不允许改变。
**多维数组的长度**
		int[][] arr = new int[2][10];
		arr.length为2；
		arr[1].length为10；
## clone
		int[] arr1 = new int[10];
		int[] arr2 = arr1.clone();
		int[][] arr3 = new int[2][10];
		int[] arr4 = arr3[1].clone();
一维数组调用clone后获得的是原一维数组的数值，改变arr1[1]中的数据不会对arr2[1]产生影响，同样改变arr3[1][1]中的数据不会对arr4[1]中的数据产生影响；
		int[][] arr3 = new int[2][10];
		int[][] arr4 = arr3.clone();
二维或更高维数组获得指向原数组对象的引用，改变arr4[1][1]的数值会对arr3[1][1]数值产生影响。

## String
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

## StringBuffer


		StringBuffer strbuf = new StringBuffer();
		strbuf.append(true);	//strbuf内容为"true";
		strbuf.append("test");	//strbuf内容为"truetest";
		strbuf.append('\t');	//strbuf内容为"truetest	";
		strbuf.append(1);	//strbuf内容为"truetest	1";
		strbuf.insert(1,"插入字符");	//strbuf内容为"t插入字符ruetest	1";
		strbuf.delete(1,3);	//strbuf内容为"t符ruetest	1";
		strbuf.replace(1, 2, "替换字符");	//strbuf内容为"t替换字符uetest	1";
		strbuf.reverse();	//strbuf内容为"1	tseteu符字换替t"
		String str = strBuffer.toString();	//将StringBuffer创建的字符串固定化
		str = strbuf.toString();

## 通过命令行可以为main方法传递参数

## static
**static修饰变量**
*. static修饰的变量称为类变量（属于类的变量，不是属于对象的变量），如果创建的时候没有赋值则自动赋值为0，类变量可以用类名直接访问，不必先创建引用，再创建对象，再用引用指向对象，再用引用访问。
**static修饰方法**
*. static修饰的方法位为类方法（属于类的方法，不是属于对象的方法）；类方法不能用this访问变量和普通方法，但可以先创建一个指向对象的引用，然后用引用访问；类方法可以使用其他类方法（使用属于同一个类中的类方法可以省略类名）和类变量；普通方法可以调用类方法。
详细例子参考《JAV入门123》P234.

### 继承和多态
```
public class ParentClass{
	public void pMethod(){
}
	public void sameMethod(){
}

}
public class ChildClass extends ParentClass{
	ChildClass CClass = new ChildClass();
	CClass.pMethod();	//*1*之所以子类对象的引用能够利用父类对象中的方法（或者属性）是因为在创建子类对象前首先创建了父类对象。这个父类对象隐藏在子类对象中，当通过子类对象访问父类的方法或者属性时可以理解为访问这个内嵌的父类对象的属性。
	public void cMethod(){
}
	CClass.cMethod();	//*2*子类对象的引用能够利用子类对象中的方法
	ParentClass PClass = CClass	//*3*子类引用可以用来给父类引用赋值，或者说父类引用可以无条件指向子类对象。
#java平台执行的规则是：在编译程序过程中可以调用哪些方法，访问哪些属性，时引用类型决定的；在程序运行时，具体访问哪个属性，执行哪个方法是由对象类型决定的#
	PClass.pMethod();	//*4*引用的类型决定了引用可以调用哪些方法，父类引用能够调用父类方法
	PClass.cMethod();	//*5***错误**，同4，虽然父类的引用同样指向了子类对象，但是在编译过程中java认为父类引用只能调用父类的方法。
//如何解决这个问题呢？最简单的方法当然就是用指向子类对象的子类引用CClass去执行了就好了，但是如果CClass没有我们需要的属性，而只有PClass有我们需要的属性怎么办呢？
	ChildClass converted = (ChildClass)PClass;	//创建一个子类的引用，让最终的编译通过;并且用强制类型转换将**已经指向子类引用**的父类引用强制转化为子类引用，这样就可以用converted去执行cMethod。

	public void sameMethod(){
}				//*6*子类中的sameMethod与父类中的sameMethod构成覆盖。覆盖的条件：1）子类继承父类；2）子类的方法访问控制符与父类相同或者更宽泛；3）子类的返回值能够赋值给父类方法的返回值，也就是子类返回值类型精度要比父类低。
	PClass.sameMethod();	//*7***正确**，javac编译过程中发现PClass属于父类，而且父类中有可以被PClass调用的sameMethod方法，通过编译；java执行过程中发现PClass指向的对象为CClass，而CClass对象中有sameMethod，所以就执行这个方法。如果没有呢?那就去执行父类中的那个方法。
}
```
**继承与构造方法**
子类唯一没有从父类继承的下来的就是父类的构造方法，可以在子类的构造方法的第一行用super调用父类的构造方法，java会根据super中有无参数及参数类型在父类中选择合适的构造方法来进行初始化。  
父类中往往要留一个无参数的构造方法，因为如果子类没有显式地调用父类中的某个构造方法时，编译器会帮我们调用父类的无参数的构造方法。  
## 多态
同一个东西在不同的环境下有不同的表现。覆盖就是一种多态形式，它让程序只有在执行的时候才能断定调用哪个方法。多态还有一种形式就是重载，当用不同的实参调用方法名相同的方法时执行的方法会有不同。  
当继承被引入到重载的参数时，决定函数重载的哪个方法被调用的是实参。这里的实参指的是引用的类型，而不是引用指向对象的类型。实参与形参匹配的原则是实参要能够赋值给形参，与形参类型相同或者是其子类。
## Class类
Class类无法用new来创建需要用getClass()方法来得到Class的对象 
		CarBass bass = new sportsCar();
		Class clazz = bass.getClass();
		System.out.println("base类引用指向的对象所属的类是：" + clazz.getName());	//getName()方法返回Class类所表示的类的全限定名。为com.javaeasy.**.sportsCar


		if(base instanceof Bus){	//判断一个引用指向对象所属的类是不是某个类或者某个类的子类。

		}

## 修饰符


**类的修饰符**
*类可见意味着什么*
1. 创建类的引用
2. 创建类的对象
3. 通过类名调用静态方法
4. 继承这个类
# 类的可见性


类的访问控制符|对同一源文件中的类可见|对同一个包中的类可见|对不同包中的类可见
-|-|-|-
public	|	是		|	是	|	是
默认	|	是		|	是	|	否


```
public final class finalClass{	//final所修饰的类不能被继承

}
```
import是将一个类引入，如果这个类被修饰为不可见则不能引入。

## 方法的访问控制符
**方法的可见性受限于类的可见性：如果一个类使用默认修饰符那么无论这个类中的方法使用什么修饰符，这个类中的方法对于并不在同一个包中的类来说都是不可见的**


访问控制符|对所在类可见|对同一个包中的类可见|对子类可见|对不在同一个包中的类可见
-|-|-|-|-	
public	|	是 |	是	|	是	|	是
protected|	是|	是	|	是	|若非继承则否
默认	|	是|	是	|若非同一个包则否|	否
private|	是|	否	|	否	|	否


* .类的可见是类可以被继承的前提，类的可继承是子类的方法能够覆盖父类方法的前提，子类的方法可以覆盖哪些父类的方法是由父类方法的访问控制符决定的。如果子类和父类处于同一个包中则只要父类中的方法修饰符不是private就可被覆盖；如果处于不同的包中则父类的修饰符只要不是默认或者private都可以。
* .方法没有被覆盖，即使子类与父类的方法签名相同也不会被覆盖，这时使用指向父类对象的引用一定访问的是父类中的方法；使用指向子类对象的引用同样访问的时父类中的方法，因为父类对象可以内嵌在子类中。 
* .方法被覆盖时具体调用子类还是父类的方法是由引用所指向的对象决定的：用指向父类对象的引用则访问父类中已经被覆盖的方法；用指向子类的引用则访问子类中的方法。

```
public final void finalMethod(){	//当用final修饰方法时表示方法不能被覆盖

}
```
**用static修饰的变量、方法分别为静态变量与静态方法**

类型|所属关系|作用范围
-|-|-
非静态变量|成员变量|实例变量
静态变量|静态成员变量|类变量
非静态方法|成员方法|实例方法
静态方法|静态成员方法|类方法

**静态方法与非静态方法区别**
1. 非静态方法通过指向对象的引用调用，所以可以访问调用对象中的实例变量；静态方法通过类名直接调用，无法直接操作类中某一个对象的实例变量只能访问静态方法和静态变量。
2. 静态变量不是通过引用或者this调用，类方法内可以调用其他类方法和使用类变量；类方法可以被普通方法调用。
```
public class className{
	final static staticMethod(){
}
className.staticMethod();
}
```
**静态方法何为静态**
1. 对于普通方法，在存在继承覆盖的情况下java会根据引用所指向对象的类型来决定执行哪个方法，引用指向对象的类型是动态可变的所以就给java带来了多态；
2. 对于静态方法，java会根据类名或者引用所属的类（不推荐）来决定调用哪个静态方法；类名固然是不可随便改变的，同样，引用的类型在引用被创建出来之后就不可改变了，所以静态方法不存在多态。

## 变量的访问控制符



访问控制符|对所在类可见|对同一个包中的类可见|对子类可见|对不在同一个包中的类可见
public	|	是 |	是	|	是	|	是
protected|	是|	是	|	是	|	否
默认	|	是|	是	|	否	|	否
private|	是|	否	|	否	|	否



* .为了避免类外部直接操作成员变量的数值，在没有外部需求的前提下应该尽可能使用private访问控制符修饰成员变量。
* .对于private修饰的变量程序提供setXXX、getXXX的方法，在方法内通过检查权限以及修改数值的合法性进行相应操作。
**变量的覆盖**
* .如果子类从父类中继承了一个变量，而同时子类本身中也定义了一个具有相同变量名相同类型的变量，那么子类的变量将覆盖父类的变量；
* .如果父类的变量不希望被覆盖则使用static修饰；
* .对于变量的覆盖，最终得到哪个变量的值是由引用的类型，而不是引用指向对象的类型。

**final与static修饰成员变量**
```
public final int a = 9;		//final关键字修饰的变量必须在创建时赋值，且赋值之后不允许改变。  
public static int a = 9;	//static修饰的变量为静态变量，可以通过类名直接操作，可以在静态方法中使用，由于静态变量的唯一性可以让这个变量在不同的对象之间共享，只要一个类是可见的而且这个静态变量也是可见的那么就可以使用这个类中的静态变量，不要错误地认为一个类的静态变量只能在本类或者本类中的对象中使用。  
```
**局部变量的修饰符**
局部变量只能在方法内使用，对外部一定是不可见的，所以修饰符只有final；  
final修饰的局部变量和final修饰的成员变量性质一样。  

**final修饰引用**
```
public class cl1{
	public final finalValue = 9;
	public int commonValue = 10;
}

public class useCl1{
	public static void main(String[] args){
	final cl1 finalRef = new cl1();	//final修饰引用
	finalRef = new cl1();		//错误，既然引用被final修饰则引用不可以再次赋值
	finalRef.commonValue = 1;	//正确，可以修改被final修饰的引用所指向对象的属性
}
}
```

## 接口（interface）
**定义一个接口**
public abstract interface InterfaceA{	//java规定一个类型（接口或者类）包含抽象方法则这个类型就是抽象的，必须用abstract修饰。
	public static final int i = 0;	//接口中的变量只能用public static final同时修饰
	public abstract int method1();	//抽象方法只能有方法签名和返回值，必须用public以及abstract修饰
}
接口中可以没有抽象方法，此时成为空接口，主要用于为为类增加新的数据类型，必要的时候对类进行区分。  
由于接口中的abstract以及方法中的public abstract以及变量中的public static final都是必须要写的，所以可以都省略。  
接口可以继承，继承的语法与类相同：  
接口只能继承接口，不能继承类；  
接口之能但继承；  
继承后子接口也可以拥有父接口的抽象方法和变量；  
*接口的继承是可以避免的，因为一个类可以实现多个接口*  
抽象的意思就是一个元素存在没有完成的部分，如果一个类中包含抽象方法则这个类也是抽象的，不能用new来创建，但是可以定义抽象类型的引用：  

InterfaceA interfaceA = null;

public class ExtendsInterface implements InterfaceA{	//如果要实现两个接口则public class ExtendsInterface implements InterfaceA, InterfaceB
	public int method1(){	//可以认为接口实现抽象方法就是子类方法覆盖父类方法的过程。方法签名要与抽象方法完全相同，如果抽象方法的返回值为基本数据类型则此处的返回值应该和抽象方法保持一致，如果抽象方法的返回值为自定义类型则类中的返回值类型的引用必须能够不经过强制类型转换直接赋值给抽象方法返回值类型的引用。
	}
	public int method2(){
	}
}

public class MainClass{
	public static void main(){
		InterfaceA interfaceA = null;
		ExtendsInterface eF = new ExtendsInterface();
		interfaceA = eF;	//可以让接口的引用指向实现了接口的类的对象
		interfaceA.method1();	//并调用接口中规定的方法。
		//interfaceA.method2();	//错误，接口中没有这个方法。和之前的继承覆盖很像：类中的method1覆盖了接口中的抽象方法，编译时接口中由方法通过，执行时类中有方法执行引用所指对象中的方法；但接口中没有method2，所以编译不能通过。
	}
}
public class ParentClass{
	public parentMethod(){

	}
}

public class ChildClass extends ParentClass{

}

public class MainClass{
	ParentClass pClass = new ParentClass;
	pClass.parentMethod();	//ParentClass类当然可以使用parentMethod方法
	ChildClass cClass = new ChildClass;
	cClass.parentMethod();	//ChildClass类继承了ParentClass类也可以使用parentMethod方法
}

现在有一个NormalClass，也想用ParentMethod方法，但是却不想继承ParentClass，就需要使用接口。接口定义为：

public abstract interface InterfaceAB{
	public abstract shareMethod();
}
```
然后在ParentClass以及NormalClass中实现这个接口：
```
public class ParentClass implements InterfaceAB{
	public shareMethod(){

	}
}

public class NormalClass implements InterfaceAB{
	public shareMethod(){

	}
}
```
ChildClass同样继承ParentClass
```
public class ChildClass extends ParentClass{

}
```
这样在三个类中就可以无差别地使用shareMethod了。
```
public class MainClass{
	ParentClass pClass = new ParentClass;
	pClass.shareMethod();
	ChildClass cClass = new ChildClass;
	cClass.shareMethod();
	NormalClass nClass = new NormalClass;
	nClass.shareMethod();
}
```
### 抽象类与内部类
**Abstract Class**
```
public abstract class abstractClass{		//抽象类不能用new来创建其对象
	public abstract void abstractMethod();	//当一个类中有了抽象方法那么其所在的类就是抽象类，类需要用abstract修饰。但是抽象类中可以没有抽象方法。抽象方法可以使用除privit之外的任何一种访问控制符
}
```
抽象类的作用是让子类强制实现父类中的抽象方法，否则就子类需要将自身定义为抽象类。
**Inner Class**
public class OutClass{
	public int pubValue = 0;
	private int value5 = 0;
	private static int value4 = 0;
	public class InnerClass{	//成员内部类与类中的其它成员处于同一级别
		int innerValue = 0;
		public int innerClassMethod(){
		}
		return pubValue + innerValue;	//内部类可以访问外部类中的所有属性，无论属性的访问控制符是什么。为什么可以访问pubValue呢，是因为java将其变成了OutClass.this.pubValue。当内部类中的变量与外部类中的变量相同时就不能再省略隐含的引用了。
	}
	public static class StaticInnerClass{	//静态内部类
	
	}
	public method(){
		InnerClass inner = new InnerClass();	//在方法中可以创建内部类的引用
		inner. innerClassMethod();		//可以通过内部类的引用访问内部类中的方法
		final int value3 = 0;
		class LocalInnerClass{	//定义在方法中的局部内部类局部内部类只能在方法中使用，先定义后使用，不能使用访问控制符来修饰，不能用static来修饰，可以用final来修饰。
			public int value2 = 0;
			public void innerMethod(){		//在局部内部类中定义方法
				int value1 = 0;
				System.out.println(value1);	//使用局部内部类LocalInnerClass中innerMethod的局部变量value1
				System.out.println(value2);	//使用局部内部类LocalInnerClass中的成员变量val2
				System.out.println(value3）；	//与局部内部内类不同，使用外部类中method方法的变量value3（可以直接使用方法中被final修饰的，定义在方法之前的变量）
				System.out.println(OutClass.value4）；	//使用外部类中的静态变量value4
				System.out.println(OutClass.this.value5）；	//使用外部类中的变量成员value5**如果是局部内部类
			}
		}
	}
}
* 内部类可以使用的访问控制符同样有四种：public 默认 protected private。希望哪个内部类只能在类中使用则使用private.
* satatic：使用static修饰的内部类成为静态内部类，静态方法中是不能使用非静态内部类的。
* abstract：内部类也可以抽象。
* final内部类也可标记为不能被继承
import com.*.OutClass
import com.*.OutClass.InnerClass	//在使用内部类之前需要将内部类引入
import com.*.OutClass.StaticInnerClass	//同样引入静态内部类
public class UseInnerClass{
	public static void main(String[] args){
		OutClass out = new OutClass();
		InnerClass inner = out.new InnerClass();	//创建内部类的应用要用到外部类的引用
		StaticInnerClass sInner = new StaticInnerClass();	//可以直接创建内部静态类
	}
}
**静态成员内部类**
* 静态方法和非静态方法都是通过类名访问静态变量的；
* 非静态方法通过this访问非静态变量，非静态方法不能使用非静态变量；
* 静态方法不能访问非静态方法，能够通过类名访问另外的静态方法；
* 非静态方法通过类名访问静态方法，通过this访问其他非静态方法。

方法\变量|静态变量

### 匿名类（anonymous inner class）
* 匿名类通常表示能够实现某一个接口或者某一个抽象类的没有名字的一个类
```
public interface AnInterface{	//定义一个接口
	void InterfaceMethod();
}

public abstract class AnAbstractClass{		//定义一个抽象类
	public AnAbstractClass(int i){

	}
	public AnAbstractClass(){
	}
	public abstract void ClassMethod();	//抽象方法
	
}

public class UseAnonymous{
	AnInterface intf = new AnInterface(){	//创建了一个实现了接口的匿名类的对象，并让接口的引用指向这个对象
		public void InterfaceMethod(){	//实现接口中的抽象方法
		}
	}；	//最后的分号表明大括号中表示一个类，这个类实现了接口。
	
	AnAbstractClass absCl = new AnAbstractClass(){	//创建了一个实现了抽象类的匿名类的对象，并让抽象类的引用指向这个对象
		public void Classmethod(){
		}
	}
}

### Java异常
**异常是什么**
* 语法错误是由编译器发现的，异常是程序在执行过程中发生的错误；
* 程序发生了一个异常通常称为“程序抛出了一个异常”
* 每一种异常都是用一个类来表示，每一个抛出的异常都是某一个异常类所产生的实例。
* java.lang.Throwable是所有异常类的父类；
* 仅仅有两个类直接继承了Throwable，分别是java.lang.Error和java.lang.Exception；
* 只有一个类继承了Throwable，才会被当作异常，才可以被抛出

**一个使用异常的例子**
public class TB extends Exception {	//一个类只有继承了Throwable才能被抛出
	public TB(String msg){		//msg是想要显示的错误信息
		super(msg);	//Exception类中最重要的构造方法是Exception(String msg)，其中的形参就是要显示出来的信息
	}
}

public class TS extends Exception{
	public TS(String msg){
		super(msg);
	}
}

public class Cup{
	private static int cupCapacity;
	public static void main(String[] args) throws TB,TS{	//告诉程序这一个方法可能要抛出这些类型的异常，
		int capacity = Integer.valueOf(args[0]);
		if(capacity > 1000){
			TB big = new TB("太大了");
			throw big;	//throw后必须要跟一个异常类的实例，也就是必须是Throwable类或者是其实例。
		}else if(capacity < 0){
			TS small = new TS("太小了");
			throw small;
		}
		Cup.cupCapacity = capacity;
	}				//一旦发生异常则下面的程序就不会再执行了
}
*程序运行的结果为*
java Cup 5000
Exception in thread "main" TB: 太大了	//可见抛出的由类的名字以及错误信息。
	at Cup.main(Cup.java:6)
**try catch语句**
throw可以把异常抛出，但是也可以用try-catch处理  
public class Cup{
	private static int cupCapacity;
	public static void main(String[] args) throws TS{//由于try catch处理了TB类的异常，所以不用抛出TB了，当然也可以写着TB异常类，这时同样是先匹配catch再尝试抛出异常。
		int capacity = Integer.valueOf(args[0]);
		try{
			if(capacity > 1000){
				TB big = new TB("太大了");
				throw big;
			}else if(capacity < 0){
				TS small = new TS("太小了");
				throw small;
			}
		} catch (TB bigg){
			System.out.println("由try catch捕捉到的异常信息为：" + bigg.getMessage());
		}
		Cup.cupCapacity = capacity;
	}
}

$ java Cup 1000000
由try catch捕捉到的异常信息为：太大了

try {
	//try语句告诉程序本代码段可能会抛出异常，抛出前先去catch语句中查看是否有要抛出异常所对应的类型（是所对应异常本身或者是其子类型）
} catch (){	//catch语句用来填写异常的类型，如果填写Exception则catch语句会处理所有的异常。

}

**try catch finally语句**
try {
	
} catch (){	

}finally{

}
* finally语句并不处理异常。一个异常没有匹配到catch语句中的任意类型时是要抛出的（throws 后要加上异常的类型），当然如果匹配到类型就执行catch语句，不用抛出。无论异常是否匹配了catch语句，亦或catch语句中有return返回语句，代码都是要尝试执行finally语句的。

**try finally**
try{

}finally{

}
* try finally语句不是用来处理异常的，通常是用来保证程序无论在什么条件下一定会执行finally语句中的内容的。
**异常的类型**
* Throwable的意思是可以被抛出，是Exception和Error类的直接父类
* RunntimeException的直接父类是Exception
* Exception必须处理
* Error不处理
* RunningtimeException灵活应对

### Thread
* 线程就是执行一个方法，执行完之后线程就结束了
* Thread是线程的代理，当操作Thread时，Thread类就会操作真正的线程，当创建一个Thread类的实例时，在java平台内部，一个真正的线程同时被创建爱你出来了。
**使用继承和覆盖的方法创建线程**
* Thread();是Thread类的一个构造方法；
* void start();方法是线程类的核心方法，当调用这个方法时就会执行Thread类中的run();方法。
* void run(); 是Thread类的一个普通方法，特之处在于statr()方法会将其作为线程的起点。
*用继承覆盖的方法实现多线程*
```
public class MyThread extends Thread{	//创建一个类，并且继承Thread
	public void run(){		//Thread类内部也有一个run方法，将发生覆盖
		System.out.println("这是在另外一个线程");
	}
}
```
***
```
public class UseMyThread{
	public static void main(String[] args){
		MyThread td = new MyThread();	//创建一个对象
		td.start();			//虽然MyThread类中没有start方法，但是其继承了Thread类，所以就执行Thread类中的方法，但是Thread类中的方法已经被覆盖，所以就执行我们创造的那个方法
	}
}
```
*用接口实现多线程*
简单来讲继承的作用就是将我们需要执行的方法覆盖Thread中原来的方法。然后调用start方法。现在Thread类中的run方法提供了这样一种可能  
public void run(){
	if(target != null){
		target.run();	//只用将target的值改为MyThread类的引用就好了，这样引用不为空，就可以执行引用中的run方法了怎么改呢？
	}
}
利用Thread类中的另外一个构造方法来改：
Thread(Runnable target){	//只用把引用传给这个构造方法就好了，但是这个引用不仅能像MyThread类的引用一样包含run方法，而且要 能够是Runnable类型的。
	*
	*
}
怎么获得Runnbale类型的引用呢？Runnable是一个接口，实现了这个接口就好了

public class MyRunnable implements Runnable{	//MyRunnable类实现Runnable接口
	public void run(){
		System.out.println("在另外一个线程中了");
	}
}

public class UseMyRunnable{
	public static void main(String[] args){
		MyRunnable myRun = new MyRunnable();
		Thread useTh = new Thread(myRun);	//由于myRun是实现了Runnable接口的类的对象，所以可以作为参数传递给Thread类；并且MyRunnable类中包含run方法。
		useTh.start();
	}
}
*使用匿名内部类实现多线程*
public class AnonymousRunnable{
	public static void main(String[] args){
		Thread thread = new Thread(new Runnable() {	//创建了一个实现了Runnable接口的匿名类的对象，并且把这个对象作为参数传递给Thread构造方法，而得到的thread引用就是一个target标签不是空，而且又是Thread类型的引用
			public void run(){
				System.out.println("在另外进程中了");
			}
		});
		thread.start();
	}
}
**显示线程的名字**
```
public class ShowNameClass extends Thread{
	public ShowNameClass(){
		super();			//调用Thread类中无参的构造方法来初始化
	}
	public ShowNameClass(String p_name){
		super(p_name);			//调用由参的构造方法来初始化
	}
	public void showName(){
		System.out.println("线程的名字是" + this.getName());	//由于ShowNameClass内部没有定义getName方法，所以调用父类的构造方法。
	}
}
***
public class MainShowName{
	public static void main(String[] args){
		ShowNameClass defaultName = new ShowNameClass();
		ShowNameClass name = new ShowNameClass("你的名字");
		defaultName.showName();
		name.showName();
	}
}
```
**利用run方法以及start方法**
```
public class ShowNameClass extends Thread{
	public ShowNameClass(){
		super();			//调用Thread类中无参的构造方法来初始化
	}
	public ShowNameClass(String p_name){
		super(p_name);			//调用由参的构造方法来初始化，将线程的名字设置为p_name
	}
	public void run(){
		System.out.println("线程的名字是" + this.getName());	//由于ShowNameClass内部没有定义getName方法，所以调用父类的构造方法。
	}
}
```
***
```
public class MainShowName{
	public static void main(String[] args){
		ShowNameClass defaultName = new ShowNameClass();
		ShowNameClass name = new ShowNameClass("你的名字");
		defaultName.start();
		name.start();
	}
}
```
程序执行的结果为
线程的名字是Thread-0
线程的名字是你的名字
**获得当前线程的名字，并改变名字**

public class PrintCurrentThreatName{
	public static void main(String[] args){
		Thread currentThreadMsg = Thread.currentThread();	//用Thread.currentThread()获取当前的线程对象，并让currentThreadMsg线程引用指向这个对象
		System.out.println("当前线程的名字是："+ currentThreadMsg.getName());
		currentThreadMsg.setName("改变线程名字");			//调用setName()方法设置线程的名字
		System.out.println("当前线程的名字是："+ currentThreadMsg.getName());
	}
}
***
Thread类中的静态方法sleep可以让当前进程沉睡5秒钟，由于这个方法会抛出错误，所以要用try catch语句处理一下  
try{
	Thread.sleep(5000);
}catch (Exception e){
	
}
