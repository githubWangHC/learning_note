## 管道与重定向的区别
**重定向**
```
>>	追加输出重定向
>	或者1>位普通重定向
2>	位错误重定向
<	错误重定向
```
重定向的意义在于将本来要由键盘输入或者屏幕输出的内容由其他文件输入
**管道**
管道连接两个命令
```
ls /etc/ | grep ab
```
管道将第一个命令得到的结果作为输入输入到第二个命令中。

## vim编辑器
命令行模式下输入：set nu可以设置行号  
输入:sp FileName.c可以另外打开一个文件  
crtl+w+上下箭头可以在两个编辑器之间切换  
i在光标前方插入，I在整行前边插入  
a在光标后方插入，A在整行后边插入  
按dd之后再按p可以实现剪切粘贴  
## &&
想同时执行两条命令则可以使用&&，表示如果前一条指令执行成功则继续执行ls命令
```
./main.out && ls
```
想要查看上一条命令的返回值可以echo $?

## 系统信息
```
arch		显示机器的处理器架构(1)
uname -m	显示机器的处理器架构(2)
uname -r	显示正在使用的内核版本
dmidecode -q	显示硬件系统部件-(SMBIOS/DMI)
hdparm -i/dev/hda	罗列一个磁盘的架构特性
hdparm -tT/dev/sda	在磁盘上执行测试性读取操作
cat /proc/cpuinfo	显示CPUinfo的信息
cat /proc/interrupts	显示中断
cat /proc/meminfo	校验内存使用
cat /proc/swaps	显示哪些swap被使用
cat /proc/version	显示内核的版本
cat /proc/net/dev	显示网络适配器及统计
cat /proc/mounts	显示已加载的文件系统
lspci -tv		罗列PCI设备
lsusb -tv		显示USB设备

date			显示系统日期
echo $LANG		显示目前所支持的语言
LANG=en_US		修改语系成为英文语系（本次登录有效）
cal 10 2007		显示2007年10月的日历表
date 041217002007.00	设置日期和时间-月日时分年.秒
date +%Y/%m/%d		将当前时间设置成年月日的形式
date +%H:%M		17:04
bc			+加、-减、*乘、/除、^指数、%余数
			quit、scale=number，number是小数点位数
sync			将数据同步写入硬盘中的挃令：
clock-w		将时间修改保存到BIOS
```
## 关机(系统的关机、重启以及登出)
```
shutdown -h now	关闭系统(1)
init 0			关闭系统(2)
?			runlevel3：纯文本模式
?			runlevel5：含有图形接口模式
?			runlevel6：重新启改
telinit 0		关闭系统(3)
shutdown -h hours:minutes	按预定时间关闭系统
shutdown -c		取消按预定时间关闭系统
shutdown -r now		重启(1)
reboot，halt,powerof	重启(2)
logout			注销
```
## 文件和目录
```
cd		进入自己的家目录ChangeDirectory
cd /home	进入'/home'目录'
cd ..		返回上一级目录
cd ../..	返回上两级目录
cd ~user1	进入user1的主目录
cd -		返回上次所在的目录
pwd		显示工作路径（PrintWorkingDirectory）
pwd -P		写出目前的工作目录，不显示链接档

ls		查看目录中的文件或者目录
ls -d		仅仅显示目录名字，而不显示目录下的内容列表，显示符号链接本身，而不显示其所指向的目录列表
ls -F		查看目录中的文件
ls -l		显示文件和目录的详细资料
ls -a		显示隐藏文件
ls *[0-9]*	显示包含数字的文件名和目录名
ls -h		显示权限
tree		显示文件和目录由根目录开始的树形结构

mkdir dir1		在当前目录下创建一个叫做'./dir1'的目录'，且当前目录中没有以dir1这个名字命名的文档
mkdir dir1 dir2		同时创建两个目录
mkdir -p /tmp/dir1/dir2	创建一个目录树
mkdir -p tmp/dir1/dir2	以当前文件夹为结点创建一个目录树

rmdir dir1		删除一个叫做'dir1'的空目录'
rmdir /tmp/dir1/dir2	删除dir2的空目录
rmdir -p /tmp/dir1/dir2	同时删除空目录dir1与dir2，如果在/tmp中执行会显示/tmp busy所以不能删除，如果不再/tmp则显示权限不够
mkdir -m 711 test2	创建一个权限为711的test2目录

touch file1	在当前目录下创建一个名为file1的文档
```
## 文档的类型有哪些：
```
rm abc		删除当前文件夹下一个叫做abc的文件或者目录，系统会询问确认是否删除。
rm -f abc	删除当前文件夹下一个叫做abc的文件或者目录，系统不再问了。
rm -r dir1	删除dir1目录下的子目录以及包含的文件

mv dir1 new_dir	重命名/移动一个目录

cp /tmp/dir .		复制到当前文件夹的文档所有人变为命令执行者
cp -a /tmp/dir1 .	将文档原有属性复制过来和-p结果一样
cp -l file2 lnk_h	复制成为进行硬式连结(hard link)档
cp -s file1 lnk_s	复制成为符号链接文件(symbolic link)，亦成快捷方式档案
   -d			若源文件为链接文件的属性(link file)，则复制链接文件属性而非档案本身；
```
**关于hard link与symbolic link的说明**
hard link：优点，原文档删除后还可以通过hard link找到原文档内容；缺点，占内存，不能连接到目录，不能连接不同filesystem类型的文档。  
symbolic link指的是快捷方式，优点，可以链接到目录，不占内存；缺点，原文档删除后soft link就失效了。就是将多个文档名(file,link_h)指向同一个inode号码，使用ls -i观察原文档和原文档的硬链接可以看到二者包括inood在内所有的东西都相同。

link_h的block中存放的是目录的block
link_s的block中存放的是链接档的block

/和/boot和/home的inode都是2，因为三者都是挂载点，三者安装在不同的硬盘上，不同硬盘的格式可能就不一样，所以三者之间的文件不能挂载。
每个档案都会占用一个inode，档案内容由inode的记录来指出;想要读取该档案,必项要经过目录的文件名来指向向到正确的inode号码才能读取。
新建一个目录时Linux做了什么？
linux会分配一个inode与一个block，inode记录这个目录的权限以及block等信息，block存储该目录下的每一个文档名字以及各文档对应的inode号码，是的，目录下的文档名字。
那么当读取一个文档时，必须先找到文档所在目录的inode，再由目录的inode找到其所对应的目录的block中该目录下要读取的文档所对应的inode，再由要读取文档的inode找到文档的block。
eg.先由/的inode号码2读取/对应的block，从中读取etc/的的indoe，由etc/的inode找到etc/所对应的block，从这个block中读取passwd文档对应的inode号码，由passwd文档对应的block读取passwd的内容。读取inode的过程中要先看看操作者是否有读取这个inode所对应的block的权限。==》如果想要读取某个文件，就需要对这个文件所在的目录有读取的权限。
touch -t 0712250000 file1修改一个文件或目录的时间戳-(YYMMDDhhmm)
iconv -l		列出已知的编码

## 文件搜索##
```
find / -user user1	搜索属于用户'user1'的文件和目录
find /home/user1 -name \*.bin	在目录'/home/user1'中搜索带有'.bin'结尾的文件
find /usr/bin -typef -atime +100	搜索在过去100天内未被访问的文件-n指n天以内，+n指n天以前
find /usr/bin -typef -mtime -10	搜索在10天内被创建或者修改过的文件
find / -name \*.rpm -exe cchmod 755	搜索以'.rpm'结尾的文件并定义其权限
find / -xdev -name \*.rpm		搜索以'.rpm'结尾的文件，忽略光驱、捷盘等可移动设备
locate \*.ps				寻找以'.ps'结尾的文件-先运行'updatedb'命令
whereis halt				显示一个二进制文件、源码或man的位置
which halt				显示一个二进制文件或可执行文件的完整路径
```
## Linux文件与目录的三种时间状态##
modification time(mtime):  
当修改文件的内容数据的时候，就会更新这个时间，而更改权限或者属性，mtime不会改变，这就是和ctime的区别
status time(ctime)
当修改文件的权限或者属性的时候，就会更新这个时间，ctime并不是create time，给我的感觉更像是change time，但这么说也不完全对，因为只有当更新文件的属性或者权限的时候才会更新这个时间，更改内容的话是不会更新这个时间的
access time(atime)
当读取这个文件的时候就会更新这个时间
	对于文件:
当修改mtime时,ctime必须随着改变.因为文件大小等都是属性；
有人说atime也一定会改变，要想修改文件必须先读取；其实是不对的，不读取问文件就能修改其内容：
如：echo “This is a test!”>>/etc/issue,issue文件内容会变，但并没有读取文件，所以atime并没有改变
vi编辑器或者cat打开一个文档，atime会改变；无论vi时是否写入文字，退出时如果：w则三个时间都会变。
	对于目录：
访问一个目录其atime改变，mtime，ctime不变；
修改一个目录：在一个目录下touch一个文件，mtime与ctime会改变，atime不一定会变
查看文档的时间
ls -l /etc/man.config	默认查看的是mtime
ls -l --time=atime /etc/man.config
ls -l --time=ctime /etc/man.config
## 修改文档的时间##
touch[-acdmt]档案
选项不参数：
-a：仅修订access time；
-c：仅修改档案的时间，若该档案不存在则不建立新档案；
-d：后面可以接欲修订的日期而不用目前的日期，也可以使用--date="日期或时间"
-m：仅修改mtime；
-t：后面可以接欲修订的时间而不用目前的时间，格式为[YYMMDDhhmm]

挂载一个文件系统
mount /dev/hda2 /mnt/hda2	挂载一个叫做hda2的盘-确定目录'/mnt/hda2'已经存在
umount /dev/hda2		卸载一个叫做hda2的盘-先从挂载点'/mnt/hda2'退出
fuser -km /mnt/hda2		当设备繁忙时强制卸载
umount -n/mnt/hda2		运行卸载操作而不写入/etc/mtab文件-当文件为只读或当磁盘写满时非常有用
mount /dev/fd0 /mnt/floppy	挂载一个软盘
mount /dev/cdrom /mnt/cdrom	挂载一个cdrom或dvdrom
mount /dev/hdc /mnt/cdrecorder	挂载一个cdrw或dvdrom
mount /dev/hdb /mnt/cdrecorder	挂载一个cdrw或dvdrom
mount -o loopfile.iso /mnt/cdrom挂载一个文件或ISO镜像文件
mount-tvfat /dev/hda5 /mnt/hda5挂载一个WindowsFAT32文件系统
mount /dev/sda1 /mnt/usbdisk	挂载一个usb捷盘或闪存设备
mount-tsmbfs-ousername=user,password=pass//WinClient/share/mnt/share挂载一个windows网络共享

磁盘空间
dumpe2fs [-bh] 装置文件名	可以查询到superblock 内容，每个 block group的信息
	 -b ：列出保留为坏轨的部分(一般用不到吧！？)
	 -h ：仅列出superblock的数据，不会列出其他的区段内容！
df -h 目录或者文件名	显示文件系统磁盘使用量，单位：GBytes、MBytes、KBytes
   -i 目录或者文件名				单位：inode数量
du		显示当前目录下每一个目录的容量
du /*		显示根目录下每个目录所占有的容量
du -a		显示当前目录下每一个目录下的每一个档案的容量
显示的单位
   -h 以G/M的形式显示，-k以KBytes为单位，-m以MBytes为单位
是否包含子目录的大小
   -s 不显示次目录的名字，但是当前目录包含了其次目录的大小
   -S 不显示次目录的名字，不包含其次目录的大小
rpm-q-a--qf'%10{SIZE}t%{NAME}n'|sort-k1,1n以大小为依据依次显示已安装的rpm包所使用的空间(fedora,redhat类系统)
dpkg-query-W-f='${Installed-Size;10}t${Package}n'|sort-k1,1n以大小为依据显示已安装的deb包所使用的空间(ubuntu,debian类系统)

用户和群组
groupadd group_name		创建一个新用户组
groupdel group_name		删除一个用户组
groupmod -n new_group_name old_group_name	重命名一个用户组
useradd -c "NameSurname" -g admin -d /home/user 1-s /bin/bashuser1创建一个属于"admin"用户组的用户
useradd user1			创建一个新用户
userdel -r user1		删除一个用户('-r'排除主目录)
usermod -c "UserFTP" -g system -d /ftp/user1 -s /bin/nologinuser1修改用户属性
passwd				修改口令
passwd user1			修改一个用户的口令(只允许root执行)
chage -E 2005 -12 -31 user1	设置用户口令的失效期限
pwck				检查'/etc/passwd'的文件格式和语法修正以及存在的用户
grpck				检查'/etc/passwd'的文件格式和语法修正以及存在的群组
newgrp group_name		登陆进一个新的群组以改变新创建文件的预设群组

文件的权限
r(read)			4；可读取此一档案，执行cp（前提：用户对于档案所在目录有x权限）
w(write)		2；可新增、修改但不能删除该档案（前提：用户对档案有r权限，对档案所在目录有x权限）
x(execute)		1；可以被系统执行的权限
目录的权限
r(read)			用ls等查询该目录下的文件名
w(write)		建立新的档案、目录
			删除已经存在的档案、目录(无论该档案的权限为何)
			将已存在的档案或目录进行更名
			搬移该目录内的档案、目录的位置
x(execute)		可以cd进该目录成为工作目录

重要举例：
使用者可以建立一个档案的基本权限为何？
目录所需权限：用户在该目录要具有 w,x的权限，重点在 w 啦
用户进入某目录执行该目录下的某个指令的基本权限为何？
目录所需权限：用户在该目录至少要有x的权限；
档案所需权限：使用者在该档案至少需要有x的权限

u;g;o
预设情况下：
建立文档权限默认为：666
建立目录权限默认为：777
umask是指在上述默认值的情况下还要去掉的权限
如果umask为003
档案：(-rw-rw-rw-)-(--------wx)=-rw-rw-r--=666-003=664切记
目录：(drwxrwxrwx)-(--------wx)=drwxrwxr--=777-003=663

	SetUID，SUID权限指的是s出现在档案拥有者（u）的x的位置，-rwsr-xr-x,生效条件：
1.SUID权限仅对二进制程序(binaryprogram)有效；==>>/usr/bin/passwd是二进制文件；
2.执行者对于该程序需要具有x的可执行权限；==>>vbird对/usr/bin/passwd这个程序来说是具有x权限的，vbird能执行passwd；
					==>>passwd的拥有者是root这个账号；
3.本权限仅在执行该程序的过程中有效(run-time)；
	满足以上三个条件执行者将具有该程序拥有者(owner)的权限。==>>vbird执行passwd的过程中，会暂时获得root的权限

	s在群组(g)的x时则称为SetGID,SGID,-rwx--s--x

	StickyBit,SBIT目前叧针对目录有效，对于档案已经没有效果了。
用户对于此目录具有w,x权限，亦具有写入的权限时；
用户在该目录下建立档案或目录时，仅有自己不root才有权力删除该档案

SUID/SGID/SBIT权限设定
4为SUID
2为SGID
1为SBIT
chmod 4755 test	==>>-rwsr-xr-x<<====>>chmodu+s
chmod 6755 test	==>>-rwsr-sr-x<<====>>chmodg+s
chmod 1755 test	==>>-rwxr-xr-t<<====>>chmodo+t
chmod 7666 test	==>>-rwSrwSrwT,档案拥有者本身就没有X权限，大写表示为空，没有权限给其他人。
鸟哥，228

扇区(Sector)为最小的物理储存单位，每个扇区为 512 bytes；
将扇区组成一个囿，那就是磁柱(Cylinder)，磁柱是分割槽(partition)的最小单位；
第一个扇区最重要，里面有：(1)主要开机区(Master boot record, MBR)及分割表(partition table)，其中MBR占有446 bytes，而partition table则占有64 bytes。

主要分割与延伸分割最多可以有四笔(硬盘的限制)，延伸分割最多只能有一个(操作系统的限制)，逻辑分割是由延伸分割持续切割出来的分割槽；
能够被格式化后，作为数据存储的分割槽为主要分割与逻辑分割。延伸分割无法格式化；
逻辑分割的数量依操作系统而不同，在 Linux 系统中，IDE 硬盘最多有 59 个逻辑分割(5号到63号)， SATA 硬盘则有 11 个逻辑分割(5 号到 15 号)。

superblock：记彔此 filesystem 的整体信息，包括inode/block的总量、使用量、剩余量，以及文件系统的格式不相关信息等；
inode：记彔档案的属性，一个档案占用一个inode，同时记彔此档案的数据所在的block号码；
block：实际记彔档案的内容，若档案太大时，会占用多个 block 。
索引式文件系统(indexed allocation)
FAT 格式每个 block 号码都记彔在前一个 block 当中

使用"+"设置权限，使用"-"用于取消
chmod ugo +rwx directory1	设置目录的所有人(u)、群组(g)以及其他人(o)rwx权限
chmod go -rwx directory1	删除群组(g)与其他人(o)对目录的读写执行权限
chown user1 file1		改变一个文件的所有人属性
chown -R user1 directory1	改变一个目录的所有人属性并同时改变改目录下所有文件的属性
chgrp group1 file1		改变文件的群组
chown user1:group1 file1	改变一个文件的所有人和群组属性
find /-perm -u+s		罗列一个系统中所有使用了SUID控制的文件
chmodu +s /bin/file1		设置一个二进制文件的SUID位-运行该文件的用户也被赋予和所有者同样的权限
chmodu -s /bin/file1		禁用一个二进制文件的SUID位
chmodg +s /home/public		设置一个目录的SGID位-类似SUID，不过这是针对目录的
chmodg -s /home/public		禁用一个目录的SGID位
chmodo +t /home/public		设置一个文件的STIKY位-只允许合法所有人删除文件
chmodo -t /home/public		禁用一个目录的STIKY位

文件的特殊属性使用"+"设置权限，使用"-"用于取消
chattr +a file1	只允许以追加方式读写文件
chattr +c file1	允许这个文件能被内核自动压缩/解压
chattr +d file1	在进行文件系统备份时，dump程序将忽略这个文件
chattr +i file1	设置成不可变的文件，不能被删除、修改、重命名或者链接
chattr +s file1	允许一个文件被安全地删除
chattr +S file1	一旦应用程序对这个文件执行了写操作，使系统立刻把修改的结果写到磁盘
chattr +u file1	若文件被删除，系统会允许你在以后恢复这个被删除的文件
lsattr			显示特殊的属性

压缩文件
*.gz	gzip程序压缩癿档案
gzip [-cdtv#] 档案名
	-d	解压参数，等同于gunzip file1.gz，##为何没有压缩的参数
	-c	将压缩癿数据输出到屏幕上，可透过数据流重导向杢处理；
	-t	可以用杢检验一个压缩文件癿一致性～看看档案有无错诨；
	-v	可以显示出原档案/压缩文件案癿压缩比等信息；
	-#	压缩等级，-1 最忚，但是压缩比最差、 -9 最慢，但是压缩比最好！预讴是-6
zcat 档案名.gz	不解压但读取文本格式中的内容

*.bz2	bzip2 程序压缩癿档案
bzip2 [-cdkzv#] 檔名
	-z	压缩#为何出现了压缩的参数，加不加z有区别吗
	-d	解压，等同于bunzip2 file1.bz2
	-k	保留源文件，而丌会删除原始癿档案喔！
	-c	将压缩癿过程产生癿数据输出到屏幕上！
	-v	可以显示出原档案/压缩文件案癿压缩比等信息；
	-#	与gzip 同样癿，都是在计算压缩比癿参数， -9 最佳， -1 最忚！
bzcat 檔名.bz2	不解压但读取文本格式中的内容

zip file1.zip file1		创建一个zip格式的压缩包
zip -r file1.zip file1 file2 dir1将几个文件和目录同时压缩成一个zip格式的压缩包
unzip file1.zip		解压一个zip格式压缩包


打包文件
rar a file1.rar test_file	创建一个叫做'file1.rar'的包
rar a file1.rar file1 file2 dir1	同时压缩'file1','file2'以及目录'dir1'
rar x file1.rar		解压rar包
unrar x file1.rar		解压rar包

打包文件
-c为打包选项（没有压缩）；-v选项可以查看打包过程发生了什么事情；
tar命令没有自动建立档案名字，-f选项后要紧跟档案的名字；-x为释放解压。
-C和-x选项一起使用，用来选定解压到的目录

tar -cv -f pack.tar file1	创建一个非压缩的打包文件(即tarfile)
tar -cv -f pack.tar file1 file2 dir1 创建一个包含了'file1','file2'以及'dir1'的
tar -t -f pack.tar		显示一个包中的内容
tar -xv farchive.tar		释放一个包

tar -jcv -f filename.tar.bz2	用bzip2压缩工具将打包的文件压缩（即tarball）
tar -jxv -f filename.tar.bz2 -C /tmp	将bz2格式的压缩包释放到/tmp目录下
    -zxv                                  gz
tar -zcv -f filename.tar.gz	用gzip压缩工具将打包的文件压缩

RPM包-（Fedora,Redhat及类似系统）
rpm -ivh package.rpm		安装一个rpm包
rpm -ivh --nodeepspackage.rpm	安装一个rpm包而忽略依赖关系警告
rpm -U package.rpm		更新一个rpm包但不改变其配置文件
rpm -F package.rpm		更新一个确定已经安装的rpm包
rpm -e package_name.rpm	删除一个rpm包
rpm -qa			显示系统中所有已经安装的rpm包
rpm-qa|grephttpd		显示所有名称中包含"httpd"字样的rpm包
rpm-qipackage_name		获取一个已安装包的特殊信息
rpm-qg"SystemEnvironment/Daemons"显示一个组件的rpm包
rpm-qlpackage_name		显示一个已经安装的rpm包提供的文件列表
rpm-qcpackage_name		显示一个已经安装的rpm包提供的配置文件列表
rpm-qpackage_name--whatrequires显示与一个rpm包存在依赖关系的列表
rpm-qpackage_name--whatprovides显示一个rpm包所占的体积
rpm-qpackage_name--scripts	显示在安装/删除期间所执行的脚本l
rpm-qpackage_name--changelog	显示一个rpm包的修改历史
rpm-qf/etc/httpd/conf/httpd.conf	确认所给的文件由哪个rpm包所提供
rpm-qppackage.rpm-l		显示由一个尚未安装的rpm包提供的文件列表
rpm--import/media/cdrom/RPM-GPG-KEY	导入公钥数字证书
rpm--checksigpackage.rpm	确认一个rpm包的完整性
rpm-qagpg-pubkey		确认已安装的所有rpm包的完整性
rpm-Vpackage_name		检查文件尺寸、许可、类型、所有者、群组、MD5检查以及最后修改时间
rpm-Va			检查系统中所有已安装的rpm包-小心使用
rpm-Vppackage.rpm		确认一个rpm包还未安装
rpm2cpiopackage.rpm|cpio--extract--make-directories*bin*从一个rpm包运行可执行文件
rpm-ivh/usr/src/redhat/RPMS/`arch`/package.rpm从一个rpm源码安装一个构建好的包
rpmbuild--rebuildpackage_name.src.rpm	从一个rpm源码构建一个rpm包

YUM软件包升级器-（Fedora,RedHat及类似系统）
yuminstallpackage_name	下载并安装一个rpm包
yumlocalinstallpackage_name.rpm	将安装一个rpm包，使用你自己的软件仓库为你解决所有依赖关系
yumupdatepackage_name.rpm	更新当前系统中所有安装的rpm包
yumupdatepackage_name	更新一个rpm包
yumremovepackage_name	删除一个rpm包
yumlist			列出当前系统中安装的所有包
yumsearchpackage_name	在rpm仓库中搜寻软件包
yumcleanpackages		清理rpm缓存删除下载的包
yumcleanheaders		删除所有头文件
yumcleanall			删除所有缓存的包和头文件

DEB包(Debian,Ubuntu以及类似系统)
dpkg-ipackage.deb	安装/更新一个deb包
dpkg-rpackage_name	从系统删除一个deb包
dpkg-l		显示系统中所有已经安装的deb包
dpkg-l|grephttpd	显示所有名称中包含"httpd"字样的deb包
dpkg-spackage_name	获得已经安装在系统中一个特殊包的信息
dpkg-Lpackage_name	显示系统中已经安装的一个deb包所提供的文件列表
dpkg--contentspackage.deb显示尚未安装的一个包所提供的文件列表
dpkg-S/bin/ping	确认所给的文件由哪个deb包提供

APT软件工具(Debian,Ubuntu以及类似系统)
apt-getinstallpackage_name	安装/更新一个deb包
apt-cdrominstallpackage_name	从光盘安装/更新一个deb包
apt-getupdate			升级列表中的软件包
apt-getupgrade		升级所有已安装的软件
apt-getremovepackage_name	从系统删除一个deb包
apt-getcheck			确认依赖的软件仓库正确
apt-getclean			从下载的软件包中清理缓存
apt-cachesearchsearched-package返回包含所要搜索字符串的软件包名称

查看文件内容
file file1	查看file1的文档类型是属于ASCII、data档案、binary、有没有使用到动态函式库(sharelibrary)等等信息
which ifconfig	根据PATH这个环境变量所规范的路径，去搜寻『执行档』的档名，cd是bash内建的指令用which找不到
whereis[-bmsu]档案或目弽名
	-b:叧找binary格式的档案
	-m:叧找在说明文件manual路径下的档案
	-s:叧找source杢源档案
	-u:搜寻不在上述三个项目弼中的其他特殊档
locate[-ir]keyword
	选项不参数：
	-i：応略大小写的差异；
	-r：后面可接正觃表示法的显示方式
which、locate寻找的数据是由『已建立的数据库/var/lib/mlocate/』里面的数据所搜寻到的，所以不用直接在去硬盘弼中存取数据，呵呵！弼然是徆忚速啰
?
updatedb：根据/etc/updatedb.conf的讴定去搜寻系统硬盘内的文件名，幵更新/var/lib/mlocate内的数据库档案；
locate：依据/var/lib/mlocate内的数据库记载，找出用户输入的关键词文件名。

find指令
find[PATH][option][action]
选项不参数：
	1.与时间有关的选项：有-atime、-ctime、-mtime，以-mtime说明
-mtimen：n为数字，意义为在n天之前的『一天之内』被更改过内容的档案；
-mtime+n：列出在n天之前(不含n天本身)被更改过内容的档案档名；
-mtime-n：列出在n天之内(含n天本身)被更改过内容的档案档名。
-newerfile：file为一个存在的档案，列出比file还要新的档案档名
	2.与使用者或组名有关的参数：
-uidn：n为数字，这个数字是用户的账号ID，亦UID，这个UID是记弽在/etc/passwd里面不账号名称对应的数字。
-gidn：n为数字，这个数字是组名的ID，亦卲GID，这个GID记弽在/etc/group
-username：name为使用者账号名称喔！例如dmtsai
-groupname：name为组名喔，例如users；
-nouser：寻找档案的拥有者不存在/etc/passwd的人！
-nogroup：寻找档案的拥有群组不存在亍/etc/group的档案！
	3.与档案权限及名称有关的参数：
-name filename：搜寻文件名为filename的档案；
-size[+-]SIZE：搜寻比SIZE还要大(+)或小(-)的档案。这个SIZE的规格有：c:代表byte，k:代表1024bytes。要找比50KB还要大的档案，就是-size +50k
-type TYPE：搜寻档案的类型为TYPE的，类型主要有：一般正规档案(f),装置档案(b,c),目录(d),连结档(l),socket(s),及FIFO(p)等属性。
-perm mode：搜寻档案权限『刚好等于』mode的档案，-rwsr-xr-x的属性为4755
-perm -mode：搜寻档案权限『必须要全部囊括mode的权限』的档案，我们要搜寻-rwxr--r--，亦0744的档案，使用-perm-0744。档案权限为-rwsr-xr-x，亦4755时也会被列出，因为-rwsr-xr-x的属性已经囊括了-rwxr--r--的属性了。
-perm +mode：搜寻档案权限『包含任一mode的权限』的档案，搜寻-rwxr-xr-x ，亦-perm +755 时，属性为 -rw-------也会被列出，因为他有 -rw属性存在
	4.find额外可以进行的动作
	find / -perm +7000 -exec ls -l {} \;
{} 代表的是『由 find 找到的内容』，如上图所示，find的结果会被放置到{}位置中；
-exec 一直到 \; 是关键词，代表 find 额外动作荡开始 (-exec) 到结束 (\;)
因为『;』在 bash 环境下是有特殊意义的，因此利用反斜杠来跳脱。
	find /etc -name '*httpd*'
你想要找出 /etc 底下档案名包含httpd的档案

catfile1	从第一个字节开始正向查看文件的内容
tacfile1	从最后一行开始反向查看一个文件的内容
nl			添加行号进行显示
more			一页页显示，enter向下一行，空格向下一页，b回翻一页，q退出
less			除了more控制命令外还可以使用pageup和pagedown
head-12		查看文档的前12行
tail-14		查看文档的后14行
head-n20fail|tail-n10	查看文档的10到20行
tail-f/var/log/messages	实时查看被添加到一个文件中的内容
od-tTYPE		TYPE的选项可以有
			a：利用默认字符输出；
			c：使用ASCII字符输出
			d[size]：利用十进制(decimal)输出数据，每个整数占用sizebytes；
			f[size]：利用浮点数(floating)输出数据，每个数占用sizebytes；
			o[size]：利用八进制(octal)输出数据，每个整数占用sizebytes；
			x[size]：利用十六进制(hexadecimal)输出数据，每个整数占用sizebytes；
od-toCc/etc/issue	将/etc/issue这个档案的内容以8进位列出储存值不ASCII的对照表
文本处理
catfile1file2...|command<>file1_in.txt_or_file1_out.txt
						generalsyntaxfortextmanipulationusingPIPE,STDINandSTDOUT
catfile1|command(sed,grep,awk,grep,etc...)>result.txt合并一个文件的详细说明文本，并将简介写入一个新文件中
catfile1|command(sed,grep,awk,grep,etc...)>>result.txt合并一个文件的详细说明文本，并将简介写入一个已有的文件中


grepAug/var/log/messages	在文件'/var/log/messages'中查找关键词"Aug"
grep^Aug/var/log/messages	在文件'/var/log/messages'中查找以"Aug"开始的词汇
grep[0-9]/var/log/messages	选择'/var/log/messages'文件中所有包含数字的行
grepAug-R/var/log/*		在目录'/var/log'及随后的目录中搜索字符串"Aug"
sed's/stringa1/stringa2/g'example.txt	将example.txt文件中的"string1"替换成"string2"
sed'/^$/d'example.txt	从example.txt文件中删除所有空白行
sed'/*#/d;/^$/d'example.txt从example.txt文件中删除所有注释和空白行
echo'esempio'|tr'[:lower:]''[:upper:]'	合并上下单元格内容
echo$PATH			查看到底有哪些目录被定义出
PATH="$PATH":/root		添加一个目录
sed-e'1d'result.txt		从文件example.txt中排除第一行
sed-n'/stringa1/p'		查看只包含词汇"string1"的行
sed-e's/*$//'example.txt	删除每一行最后的空白字符
sed-e's/stringa1//g'example.txt		从文档中只删除词汇"string1"并保留剩余全部
sed-n'1,5p;5q'example.txt	查看从第一行到第5行内容
sed-n'5p;5q'example.txt	查看第5行
sed-e's/00*/0/g'example.txt	用单个零替换多个零
cat-nfile1			标示文件的行数
catexample.txt|awk'NR%2==1'删除example.txt文件中的所有偶数行
echoabc|awk'{print$1}'	查看一行第一栏
echoabc|awk'{print$1,$3}'	查看一行的第一和第三栏
pastefile1file2		合并两个文件或两栏的内容
paste-d'+'file1file2	合并两个文件或两栏的内容，中间用"+"区分
sortfile1file2		排序两个文件的内容
sortfile1file2|uniq	取出两个文件的并集(重复的行只保留一份)
sortfile1file2|uniq-u	删除交集，留下其他的行
sortfile1file2|uniq-d	取出两个文件的交集(只留下同时存在于两个文件中的文件)
comm-1file1file2		比较两个文件的内容只删除'file1'所包含的内容
comm-2file1file2		比较两个文件的内容只删除'file2'所包含的内容
comm-3file1file2		比较两个文件的内容只删除两个文件共有的部分

字符设置和文件格式转换
dos2unixfiledos.txtfileunix.txt	将一个文本文件的格式从MSDOS转换成UNIX
unix2dosfileunix.txtfiledos.txt	将一个文本文件的格式从UNIX转换成MSDOS
recode..HTML<page.txt>page.html	将一个文本文件转换成html
recode-l|more			显示所有允许的转换格式

文件系统分析
badblocks-v/dev/hda1		检查磁盘hda1上的坏磁块
fsck/dev/hda1			修复/检查hda1磁盘上linux文件系统的完整性
fsck.ext2/dev/hda1		修复/检查hda1磁盘上ext2文件系统的完整性
e2fsck/dev/hda1		修复/检查hda1磁盘上ext2文件系统的完整性
e2fsck-j/dev/hda1		修复/检查hda1磁盘上ext3文件系统的完整性
fsck.ext3/dev/hda1		修复/检查hda1磁盘上ext3文件系统的完整性
fsck.vfat/dev/hda1		修复/检查hda1磁盘上fat文件系统的完整性
fsck.msdos/dev/hda1		修复/检查hda1磁盘上dos文件系统的完整性
dosfsck/dev/hda1		修复/检查hda1磁盘上dos文件系统的完整性

初始化一个文件系统
mkfs/dev/hda1		在hda1分区创建一个文件系统
mke2fs/dev/hda1	在hda1分区创建一个linuxext2的文件系统
mke2fs-j/dev/hda1	在hda1分区创建一个linuxext3(日志型)的文件系统
mkfs-tvfat32-F/dev/hda1	创建一个FAT32文件系统
fdformat-n/dev/fd0	格式化一个软盘
mkswap/dev/hda3	创建一个swap文件系统

SWAP文件系统
mkswap/dev/hda3创建一个swap文件系统
swapon/dev/hda3启用一个新的swap文件系统
swapon/dev/hda2/dev/hdb3启用两个swap分区

备份
dump-0aj-f/tmp/home0.bak/home	制作一个'/home'目录的完整备份
dump-1aj-f/tmp/home0.bak/home	制作一个'/home'目录的交互式备份
restore-if/tmp/home0.bak		还原一个交互式备份
rsync-rogpav--delete/home/tmp	同步两边的目录
rsync-rogpav-essh--delete/homeip_address:/tmp		通过SSH通道rsync
rsync-az-essh--deleteip_addr:/home/public/home/local	通过ssh和压缩将一个远程目录同步到本地目录
rsync-az-essh--delete/home/localip_addr:/home/public	通过ssh和压缩将本地目录同步到远程目录
ddbs=1Mif=/dev/hda|gzip|sshuser@ip_addr'ddof=hda.gz'	通过ssh在远程主机上执行一次备份本地磁盘的操作
ddif=/dev/sdaof=/tmp/file1		备份磁盘内容到一个文件
tar-Pufbackup.tar/home/user		执行一次对'/home/user'目录的交互式备份操作
(cd/tmp/local/&&tarc.)|ssh-Cuser@ip_addr'cd/home/share/&&tarx-p'	通过ssh在远程目录中复制一个目录内容
(tarc/home)|ssh-Cuser@ip_addr'cd/home/backup-home&&tarx-p'		通过ssh在远程目录中复制一个本地目录
tarcf-.|(cd/tmp/backup;tarxf-)			本地将一个目录复制到另一个地方，保留原有权限及链接
find/home/user1-name'*.txt'|xargscp-av--target-directory=/home/backup/--parents
					从一个目录查找并复制所有以'.txt'结尾的文件到另一个目录
find/var/log-name'*.log'|tarcv--files-from=-|bzip2>log.tar.bz2查找所有以'.log'结尾的文件并做成一个bzip包
ddif=/dev/hdaof=/dev/fd0bs=512count=1	做一个将MBR(MasterBootRecord)内容复制到软盘的动作
ddif=/dev/fd0of=/dev/hdabs=512count=1	从已经保存到软盘的备份中恢复MBR内容

光盘
cdrecord-vgracetime=2dev=/dev/cdrom-ejectblank=fast-force	清空一个可复写的光盘内容
mkisofs/dev/cdrom>cd.iso			在磁盘上创建一个光盘的iso镜像文件
mkisofs/dev/cdrom|gzip>cd_iso.gz		在磁盘上创建一个压缩了的光盘iso镜像文件
mkisofs-J-allow-leading-dots-R-V"LabelCD"-iso-level4-o./cd.isodata_cd创建一个目录的iso镜像文件
cdrecord-vdev=/dev/cdromcd.iso		刻录一个ISO镜像文件
gzip-dccd_iso.gz|cdrecorddev=/dev/cdrom-	刻录一个压缩了的ISO镜像文件
mount-oloopcd.iso/mnt/iso			挂载一个ISO镜像文件
cd-paranoia-B					从一个CD光盘转录音轨到wav文件中
cd-paranoia--"-3"				从一个CD光盘转录音轨到wav文件中（参数-3）
cdrecord--scanbus				扫描总线以识别scsi通道
ddif=/dev/hdc|md5sum			校验一个设备的md5sum编码，例如一张CD

网络-（以太网和WIFI无线）
ifconfigeth0		显示一个以太网卡的配置
ifupeth0		启用一个'eth0'网络设备
ifdowneth0		禁用一个'eth0'网络设备
ifconfigeth0192.168.1.1netmask255.255.255.0控制IP地址
ifconfigeth0promisc	设置'eth0'成混杂模式以嗅探数据包(sniffing)
dhclienteth0		以dhcp模式启用'eth0'
route-nshowroutingtable
routeadd-net0/0gw	IP_Gatewayconfiguradefaultgateway
routeadd-net192.168.0.0netmask255.255.0.0gw192.168.1.1configurestaticroutetoreachnetwork'192.168.0.0/16'
routedel0/0gwIP_gatewayremovestaticroute
echo"1">/proc/sys/net/ipv4/ip_forward	activateiprouting
hostnameshowhostnameofsystem
hostwww.example.com			lookuphostnametoresolvenametoipaddressandviceversa(1)
nslookupwww.example.com		lookuphostnametoresolvenametoipaddressandviceversa(2)
iplinkshowshowlinkstatusofallinterfaces
mii-tooleth0showlinkstatusof'eth0'
ethtooleth0showstatisticsofnetworkcard'eth0'
netstat-tupshowallactivenetworkconnectionsandtheirPID
netstat-tuplshowallnetworkserviceslisteningonthesystemandtheirPID
tcpdumptcpport80showallHTTPtraffic
iwlistscanshowwirelessnetworks
iwconfigeth1showconfigurationofawirelessnetworkcard
hostnameshowhostname
hostwww.example.comlookuphostnametoresolvenametoipaddressandviceversa
nslookupwww.example.comlookuphostnametoresolvenametoipaddressandviceversa
whoiswww.example.comlookuponWhoisdatabase

MicrosoftWindowsnetworks(SAMBA)
nbtscanip_addrnetbiosnameresolution
nmblookup-Aip_addrnetbiosnameresolution
smbclient-Lip_addr/hostnameshowremotesharesofawindowshost
smbget-Rrsmb://ip_addr/sharelikewgetcandownloadfilesfromahostwindowsviasmb
mount-tsmbfs-ousername=user,password=pass//WinClient/share/mnt/sharemountawindowsnetworkshare


fork()函数，一次调用，两次返回
http://www.cnblogs.com/nosadness/p/4051220.html
http://blog.csdn.net/songxueyu/article/details/9115393
以前一直迷惑，什么叫一次调用，两次返回。通过上网搜索，终于知其原由。现将自己的理解记录于此。
准备知识：
内存中的进程包括三个部分：可执行文件（即程序），相关数据（包括变量，内存空间，缓冲区等），上下文环境（个人理解为从哪儿来，到哪儿去）。我们知道，电脑CPU资源有限，单核就只有一个，多核也不是无限多。而当前运行的程序个数总是多于CPU个数的（这个应该是可以想得通的，没有哪个制造商或个人那么阔气而浪费CPU）。因此在操作系统的调度之下，一个程序一般不会从头执行到尾而不间断，系统会按照程序执行的顺序、优先级别等来确实由哪个程序占用当前CPU。而被间断的那些程序就需要保存间断时刻的状态（即进程的三个部分都要记录下来），以便再次执行能够完全恢复到间断以前，如果间断的时间足够短，应该给人很流畅的感觉，这也就是为什么，我们能够一边听音乐，一边看这篇文章的缘故。我猜，这也是为什么运行在内存中的程序会被称为进程的缘故。
这样，我们就可以开始讨论fork函数了。假设一个进程中有一句代码p=fork()。
我们称当前调用p=fork()的进程为父进程，父进程pid号可以用getpid()获取。fork()返回一个值给变量p，此时p正常情况下应该是一个正整数，表示fork()新产生的子进程的pid号。
fork()产生的那个子进程和父进程完全相同（至少在父进程调用fork()那一瞬间是相同的），而且也是完全独立的（即执行的先后顺序完全由操作系统调度，且父进程不一定比子进程先执行完）。fork()函数并不同于一般的函数，父进程并不会等fork函数产生的子进程完全执行完再执行pid=fork()后面的代码。或者，可以理解为fork()的作用就是产生一个子进程，至于子进程是否运行、如何运行与它没关系。
现讨论子进程的执行。我们知道，既然fork()是复制父进程，那么p=fork()语句之前的情况应该是完全一致。在父进程中p变量得到的是子进程的pid号，但是在子进程中同样有这样一个p变量，它的值是不是也是子进程的pid号呢？这是关键所在! 事实上，子进程中的p变量得到的值不是子进程的pid号，而是0。子进程的pid号完全可以用getpid()在子进程中得到。这也是为什么称fork()一次调用，两次返回，即最后的结果就等价于：一个程序被调用两次形成两个进程，在p=fork()之前，两个进程完全一样，到这一句时，一个进程中p变量值为另一个进程的pid号，而另一个进程中p变量值为0，在这之后，两个进程分道扬镳，再无任何瓜葛。（注意，两个进程可以由同一个程序引起。）


#################################
## 将输出内容以表格的形式输出
mount | column -t		//以tab键为分割将输出结果作成表格
mount | column -ts:		//以特殊符号（此处为冒号）为分割

## 重复执行一个命令直到它成功
while true
do ping -c 1 google.com > /dev/null 2>&1 && break	//将标准错误与标准输入都输入到/dev/null，后台执行，break
done ;




