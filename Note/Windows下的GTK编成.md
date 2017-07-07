GTK
====

MessageBox(NULL,TEXT("世界你好")//正文//,TEXT("问好")//标题//,MB_OK);
TEXT是一个宏，字符串需要用""包起来
```
#define	MB_OK	0x00000000L
#define	MB_OKCANCEL	0x00000001L
#define	MB_ABORTRETRYIGNORE	0x00000002L
#define	MB_YESNOCANCEL	0x00000003L
#define	MB_YESNO	0x00000004L
#define	MB_RETRYCANCEL	0x00000005L
```
用逻辑求“或”可以将按钮与图标结合起来  
MessageBox(NULL,TEXT("世界你好"),TEXT("问好"),MB_RETRYCANCEL|MB_ICONEXCLAMATION);  
```
#define MB_ICONHAND	0x00000010L
#define MB_ICONQUESTION	0x00000010L
#define MB_ICONEXCLAMATION	0x00000010L
#define MB_ICONASTERISK	0x00000010L
```
通过MessageBox的返回值来判断用户点击了什么  
int retu2=MessageBox(NULL,TEXT("你是成年人了"),TEXT("结果"),MB_RETRYCANCEL|MB_ICONHAND);  
```
#define IDOK	1
#define IDCANCEL	2
#define IDABORT	3
#define IDRETRY	4
#define IDIGNORE	5
#define IDYES	6

if(IDYES==retu1){
		int retu2=MessageBox(NULL,TEXT("你是成年人了"),TEXT("结果"),MB_RETRYCANCEL|MB_ICONHAND);
		if(IDRETRY==retu2){
			goto lable1;
		}
	}
```
if中写IDYES==retu1是高级写法，不能给宏把被判断值写在左边，把变量写在右边。  
如果一个函数不知道怎么用，则选中它右键点击go to reference of“”  
在Microsoft Visual C++中的工具条中选择Build，在窗口中可以选择调试的选项为Debug版本还是Release版本。  
```
void gtk_widget_show(widget)：显示。int整数
void gtk_widget_hide(widget)：隐藏
void gtk_widget_set_sensitive(widget, sensitive)：禁用。boolean（布尔类型）只有true（真/是）、false（假/否）两个值。
void gtk_widget_set_size_request(int widget,int width, int height) 设置“建议的”大小
void gtk_widget_destroy(widget)销毁
gtk_window_set_title(window,"红尘若梦");
gtk_window_set_resizable(window, resizable);设置是否可以缩放。
gtk_window_set_position(window, GTK_WIN_POS_CENTER_ALWAYS)显示在屏幕正中间
void gtk_window_maximize(window)最大化
void gtk_container_add(container, widget)：从container继承。比如可以将子控件widget添加到window中，window中只能有一个子控件。
```
# GTK命名规则
一般以gtk开头  
gtk_widget_代表这个函数可以应用于GtkWidget及所有子类。gtk_container_代表这个函数可以应用于Gtk_container_代表这个函数可以应用于GtkContainer及所有子类。  
**window**
	gtk_init(NULL,NULL);
	GtkWindow* windownew = gtk_window_new(GTK_WINDOW_TOPLEVEL);
	gtk_widget_show(windownew);
	g_signal_connect(windownew,"destroy",gtk_main_quit,0);

	gtk_main();
	gtk_window_set_title(window,"红尘若梦");显示的必须是字符串如果要显示数字要转换
	char time[20]={0};
	itoa(i,time,2);
	gtk_window_set_title(windownew,time);

**添加文本框/输入框（GtkEntry）**
```
GtkEntry *××× = gtk_entry_new();//不能忘了show
gtk_widget_show(×××);
gtk_container_add(window,×××);//不能忘了添加到容器中
void gtk_entry_set_max_length(entry, int max)设置输入最大宽度
void gtk_entry_set_text(entry, text)设置输入框的文本
const char * gtk_entry_get_text(int entry)获得输入框的文本值
void gtk_editable_set_editable(int editable,boolean is_editable) 设置是否可编辑，实现“只能看不能改”的效果，和Label的区别是可以选择、复制。
void gtk_entry_set_visibility(int entry,boolean visible)当把visible设置为FALSE的时候为密码框风格
```
**添加按钮（GtkButton）**
```
GtkButton *××× = gtk_button_new();
gtk_button_set_label(×××,"点我呀");
gtk_widget_show(×××);
gtk_container_add(window,×××);
g_signal_connect(btn,"clicked",function,NULL);触发点击
```
**添加标签（GtkLabel）**
```
GtkLabel *××× = gtk_label_new("×××");
使用void gtk_label_set_text(label, str)修改Label的文本内容
gtk_widget_show(×××);
gtk_container_add(window,×××);
```
**添加下拉框（ComboBoxText）**
```
1、GtkComboBoxText从GtkComboBox继承
int gtk_combo_box_text_new()创建一个ComboBoxText；
2、void gtk_combo_box_text_append(combo_box,char * id, char * text) 附加一个文本，并且给它设定一个自定义id（字符串类型）。
char* gtk_combo_box_get_active_id(combo_box)得到选中行的自定义id
gtk_combo_box_set_active_id(combo_box,char * active_id)设定自定义id等于active_id的项被选中
void gtk_combo_box_text_remove_all(int combo_box)清除所有内容
3、当选中一行的时候会发出“changed”信号
```
**添加图片控件（image）**
```
1、int gtk_image_new()创建一个图片控件支持gif、jpg、png，不支持bmp。
2、void gtk_image_set_from_file(GtkImage* image, char * filename)从文件全路径为filename的文件中加载图片。注意文件路径的转义问题，“\\”因为“\”有特殊含义。
3、gtk_image_set_from_stock(GtkImage* image char * stockid, int size)，从GTK内置的图库加载图片，stockid可选值为GTK_STOCK_*；内置图片有不同尺寸的多套，size用来指定用哪个尺寸的，可选值GTK_ICON_SIZE_*
```
**两种布局方式**

*盒子布局（GtkBox）*  
可以放很多控件  
```
int gtk_box_new(int orientation, int spacing)创建一个盒子布局。orientation方向：另外还有GTK_ORIENTATION_HORIZONTAL、GTK_ORIENTATION_VERTICAL两个可选值；spacing：单元格之间的间隙 ORIENTATION：方向
void gtk_box_pack_start(int box//着一个盒子//, int child//哪个属性//,boolean expand, boolean fill, int padding)：往盒子开头添加元素。child被添加的控件。后三个参数不讲，一般FALSE,FALSE,0即可。
```
*网格布局*
```
GtkGrid *grid1 = gtk_grid_new();
void gtk_grid_attach(grid, child, int left,int top, int width, int height);
```
*窗口、盒子的新建方式与其他方式不同:*  
括号里边要有参数(GTK_WINDOW_TOPLEVEL)、(GTK_ORIENTATION_HORIZONTAL,0)  
文本框、按钮、标签、盒子添加到窗口的方法都是gtk_container_add(window,×××);  
文本框、按钮、标签添加到盒子的方法都是gtk_box_pack_star(box,child,FALSE,FALSE,0);  
文本框、按钮、标签添加到网格的方法都是gtk_grid_attach(grid, child, int left,int top, int width, int height);  

GtkAbc *×××=gtk_abc_new，	创建一个abc控件并且返回它的标志。  
gtk_AAA_get_BBB，从AAA类型的控件的标识获得控件BBB属性的值。  
gtk_AAA_set_BBB，设置指定标识的类型为AAA的控件的BBB属性。  
# GTK深入
如果一个变量要在不同的函数内进行使用则必须声明为全局变量，即在include之下就声明变量。  
而如果一个函数要在main函数之后定义，并且main函数要用这个函数，则也要在main之前声明函数，声明方法就是将定义的大括号去掉，并且在语句之后加上分号。  
在include下声明的函数(int a)使用时就直接赋值就行了,不要再int a = 1;直接a=1;  
editPluss F2可以输入已输入的变量等。  
一个变量即使只是在次级函数使用，但要显示在主函数，还是要全局声明的。unit4_time.c
gtk_widget_set_sensitive(btnInstall,checked);  
```
1、 guint g_timeout_add (guint interval, GSourceFunc function, gpointer data);
```
增加定时器，每隔interval毫秒执行一次function指向的函数。 GSourceFunc 函数指针的定义： gboolean (*onTimer)(gpointer user_data);如果return TRUE则下次再执行，返回FALSE则停止定时器。  
2、获得当前时间小时、分钟、秒的函数：  
		time_t sysTime;
		time(&sysTime);
		struct tm* bjtime = localtime(&sysTime);
		int hour = bjtime->tm_hour;
		int min = bjtime->tm_min;
		int sec = bjtime->tm_sec;
**LED时钟**
		char file_sege[60]={0};	//char* timenow 是不行的。
		sprintf(file_sege,"LED_image\\%d.png",sec%10);//如果工程和文件在一个目录可以用这样的写法写路径
							sprintf函数的使用
		gtk_image_set_from_file(secge,file_sege);	//文件名的引用
		1、int gtk_toolbar_new()创建工具栏容器

		2、void gtk_toolbar_insert (GtkToolbar *toolbar, GtkToolItem *item, gint pos)将工具栏项添加到工具栏，item：工具栏项，pos插入的位置

工具栏项有按钮、下拉菜单按钮、分隔栏、开关等复杂内容，这里不介绍，只介绍简单常用的GtkToolButton。  

		GtkToolItem *gtk_tool_button_new (GtkWidget *icon_widget, const gchar *label);
创建GtkToolButton。icon_widget为显示的控件id，可以在按钮上显示其他控件，一般传0；label为标题。  

		void gtk_tool_button_set_stock_id (GtkToolButton *button, const gchar *stock_id);设置按钮上显示的图片。  
响应点击只要连接"clicked"信号即可。  
		GtkToolItem* btn1 = gtk_tool_button_new(NULL,"保存");
		gtk_tool_button_set_stock_id(btn1,GTK_STOCK_SAVE);
		gtk_toolbar_insert(toolbar,btn1,0);
		gtk_widget_show(btn1);
