## �ܵ����ض��������
**�ض���**
```
>>	׷������ض���
>	����1>λ��ͨ�ض���
2>	λ�����ض���
<	�����ض���
```
�ض�����������ڽ�����Ҫ�ɼ������������Ļ����������������ļ�����
**�ܵ�**
�ܵ�������������
```
ls /etc/ | grep ab
```
�ܵ�����һ������õ��Ľ����Ϊ�������뵽�ڶ��������С�

## vim�༭��
������ģʽ�����룺set nu���������к�  
����:sp FileName.c���������һ���ļ�  
crtl+w+���¼�ͷ�����������༭��֮���л�  
i�ڹ��ǰ�����룬I������ǰ�߲���  
a�ڹ��󷽲��룬A�����к�߲���  
��dd֮���ٰ�p����ʵ�ּ���ճ��  
## &&
��ͬʱִ���������������ʹ��&&����ʾ���ǰһ��ָ��ִ�гɹ������ִ��ls����
```
./main.out && ls
```
��Ҫ�鿴��һ������ķ���ֵ����echo $?

## ϵͳ��Ϣ
```
arch		��ʾ�����Ĵ������ܹ�(1)
uname -m	��ʾ�����Ĵ������ܹ�(2)
uname -r	��ʾ����ʹ�õ��ں˰汾
dmidecode -q	��ʾӲ��ϵͳ����-(SMBIOS/DMI)
hdparm -i/dev/hda	����һ�����̵ļܹ�����
hdparm -tT/dev/sda	�ڴ�����ִ�в����Զ�ȡ����
cat /proc/cpuinfo	��ʾCPUinfo����Ϣ
cat /proc/interrupts	��ʾ�ж�
cat /proc/meminfo	У���ڴ�ʹ��
cat /proc/swaps	��ʾ��Щswap��ʹ��
cat /proc/version	��ʾ�ں˵İ汾
cat /proc/net/dev	��ʾ������������ͳ��
cat /proc/mounts	��ʾ�Ѽ��ص��ļ�ϵͳ
lspci -tv		����PCI�豸
lsusb -tv		��ʾUSB�豸

date			��ʾϵͳ����
echo $LANG		��ʾĿǰ��֧�ֵ�����
LANG=en_US		�޸���ϵ��ΪӢ����ϵ�����ε�¼��Ч��
cal 10 2007		��ʾ2007��10�µ�������
date 041217002007.00	�������ں�ʱ��-����ʱ����.��
date +%Y/%m/%d		����ǰʱ�����ó������յ���ʽ
date +%H:%M		17:04
bc			+�ӡ�-����*�ˡ�/����^ָ����%����
			quit��scale=number��number��С����λ��
sync			������ͬ��д��Ӳ���еĒ��
clock-w		��ʱ���޸ı��浽BIOS
```
## �ػ�(ϵͳ�Ĺػ��������Լ��ǳ�)
```
shutdown -h now	�ر�ϵͳ(1)
init 0			�ر�ϵͳ(2)
?			runlevel3�����ı�ģʽ
?			runlevel5������ͼ�νӿ�ģʽ
?			runlevel6����������
telinit 0		�ر�ϵͳ(3)
shutdown -h hours:minutes	��Ԥ��ʱ��ر�ϵͳ
shutdown -c		ȡ����Ԥ��ʱ��ر�ϵͳ
shutdown -r now		����(1)
reboot��halt,powerof	����(2)
logout			ע��
```
## �ļ���Ŀ¼
```
cd		�����Լ��ļ�Ŀ¼ChangeDirectory
cd /home	����'/home'Ŀ¼'
cd ..		������һ��Ŀ¼
cd ../..	����������Ŀ¼
cd ~user1	����user1����Ŀ¼
cd -		�����ϴ����ڵ�Ŀ¼
pwd		��ʾ����·����PrintWorkingDirectory��
pwd -P		д��Ŀǰ�Ĺ���Ŀ¼������ʾ���ӵ�

ls		�鿴Ŀ¼�е��ļ�����Ŀ¼
ls -d		������ʾĿ¼���֣�������ʾĿ¼�µ������б���ʾ�������ӱ���������ʾ����ָ���Ŀ¼�б�
ls -F		�鿴Ŀ¼�е��ļ�
ls -l		��ʾ�ļ���Ŀ¼����ϸ����
ls -a		��ʾ�����ļ�
ls *[0-9]*	��ʾ�������ֵ��ļ�����Ŀ¼��
ls -h		��ʾȨ��
tree		��ʾ�ļ���Ŀ¼�ɸ�Ŀ¼��ʼ�����νṹ

mkdir dir1		�ڵ�ǰĿ¼�´���һ������'./dir1'��Ŀ¼'���ҵ�ǰĿ¼��û����dir1��������������ĵ�
mkdir dir1 dir2		ͬʱ��������Ŀ¼
mkdir -p /tmp/dir1/dir2	����һ��Ŀ¼��
mkdir -p tmp/dir1/dir2	�Ե�ǰ�ļ���Ϊ��㴴��һ��Ŀ¼��

rmdir dir1		ɾ��һ������'dir1'�Ŀ�Ŀ¼'
rmdir /tmp/dir1/dir2	ɾ��dir2�Ŀ�Ŀ¼
rmdir -p /tmp/dir1/dir2	ͬʱɾ����Ŀ¼dir1��dir2�������/tmp��ִ�л���ʾ/tmp busy���Բ���ɾ�����������/tmp����ʾȨ�޲���
mkdir -m 711 test2	����һ��Ȩ��Ϊ711��test2Ŀ¼

touch file1	�ڵ�ǰĿ¼�´���һ����Ϊfile1���ĵ�
```
## �ĵ�����������Щ��
```
rm abc		ɾ����ǰ�ļ�����һ������abc���ļ�����Ŀ¼��ϵͳ��ѯ��ȷ���Ƿ�ɾ����
rm -f abc	ɾ����ǰ�ļ�����һ������abc���ļ�����Ŀ¼��ϵͳ�������ˡ�
rm -r dir1	ɾ��dir1Ŀ¼�µ���Ŀ¼�Լ��������ļ�

mv dir1 new_dir	������/�ƶ�һ��Ŀ¼

cp /tmp/dir .		���Ƶ���ǰ�ļ��е��ĵ������˱�Ϊ����ִ����
cp -a /tmp/dir1 .	���ĵ�ԭ�����Ը��ƹ�����-p���һ��
cp -l file2 lnk_h	���Ƴ�Ϊ����Ӳʽ����(hard link)��
cp -s file1 lnk_s	���Ƴ�Ϊ���������ļ�(symbolic link)����ɿ�ݷ�ʽ����
   -d			��Դ�ļ�Ϊ�����ļ�������(link file)�����������ļ����Զ��ǵ�������
```
**����hard link��symbolic link��˵��**
hard link���ŵ㣬ԭ�ĵ�ɾ���󻹿���ͨ��hard link�ҵ�ԭ�ĵ����ݣ�ȱ�㣬ռ�ڴ棬�������ӵ�Ŀ¼���������Ӳ�ͬfilesystem���͵��ĵ���  
symbolic linkָ���ǿ�ݷ�ʽ���ŵ㣬�������ӵ�Ŀ¼����ռ�ڴ棻ȱ�㣬ԭ�ĵ�ɾ����soft link��ʧЧ�ˡ����ǽ�����ĵ���(file,link_h)ָ��ͬһ��inode���룬ʹ��ls -i�۲�ԭ�ĵ���ԭ�ĵ���Ӳ���ӿ��Կ������߰���inood�������еĶ�������ͬ��

link_h��block�д�ŵ���Ŀ¼��block
link_s��block�д�ŵ������ӵ���block

/��/boot��/home��inode����2����Ϊ���߶��ǹ��ص㣬���߰�װ�ڲ�ͬ��Ӳ���ϣ���ͬӲ�̵ĸ�ʽ���ܾͲ�һ������������֮����ļ����ܹ��ء�
ÿ����������ռ��һ��inode������������inode�ļ�¼��ָ��;��Ҫ��ȡ�õ���,����Ҫ����Ŀ¼���ļ�����ָ������ȷ��inode������ܶ�ȡ��
�½�һ��Ŀ¼ʱLinux����ʲô��
linux�����һ��inode��һ��block��inode��¼���Ŀ¼��Ȩ���Լ�block����Ϣ��block�洢��Ŀ¼�µ�ÿһ���ĵ������Լ����ĵ���Ӧ��inode���룬�ǵģ�Ŀ¼�µ��ĵ����֡�
��ô����ȡһ���ĵ�ʱ���������ҵ��ĵ�����Ŀ¼��inode������Ŀ¼��inode�ҵ�������Ӧ��Ŀ¼��block�и�Ŀ¼��Ҫ��ȡ���ĵ�����Ӧ��inode������Ҫ��ȡ�ĵ���inode�ҵ��ĵ���block��
eg.����/��inode����2��ȡ/��Ӧ��block�����ж�ȡetc/�ĵ�indoe����etc/��inode�ҵ�etc/����Ӧ��block�������block�ж�ȡpasswd�ĵ���Ӧ��inode���룬��passwd�ĵ���Ӧ��block��ȡpasswd�����ݡ���ȡinode�Ĺ�����Ҫ�ȿ����������Ƿ��ж�ȡ���inode����Ӧ��block��Ȩ�ޡ�==�������Ҫ��ȡĳ���ļ�������Ҫ������ļ����ڵ�Ŀ¼�ж�ȡ��Ȩ�ޡ�
touch -t 0712250000 file1�޸�һ���ļ���Ŀ¼��ʱ���-(YYMMDDhhmm)
iconv -l		�г���֪�ı���

## �ļ�����##
```
find / -user user1	���������û�'user1'���ļ���Ŀ¼
find /home/user1 -name \*.bin	��Ŀ¼'/home/user1'����������'.bin'��β���ļ�
find /usr/bin -typef -atime +100	�����ڹ�ȥ100����δ�����ʵ��ļ�-nָn�����ڣ�+nָn����ǰ
find /usr/bin -typef -mtime -10	������10���ڱ����������޸Ĺ����ļ�
find / -name \*.rpm -exe cchmod 755	������'.rpm'��β���ļ���������Ȩ��
find / -xdev -name \*.rpm		������'.rpm'��β���ļ������Թ��������̵ȿ��ƶ��豸
locate \*.ps				Ѱ����'.ps'��β���ļ�-������'updatedb'����
whereis halt				��ʾһ���������ļ���Դ���man��λ��
which halt				��ʾһ���������ļ����ִ���ļ�������·��
```
## Linux�ļ���Ŀ¼������ʱ��״̬##
modification time(mtime):  
���޸��ļ����������ݵ�ʱ�򣬾ͻ�������ʱ�䣬������Ȩ�޻������ԣ�mtime����ı䣬����Ǻ�ctime������
status time(ctime)
���޸��ļ���Ȩ�޻������Ե�ʱ�򣬾ͻ�������ʱ�䣬ctime������create time�����ҵĸо�������change time������ô˵Ҳ����ȫ�ԣ���Ϊֻ�е������ļ������Ի���Ȩ�޵�ʱ��Ż�������ʱ�䣬�������ݵĻ��ǲ���������ʱ���
access time(atime)
����ȡ����ļ���ʱ��ͻ�������ʱ��
	�����ļ�:
���޸�mtimeʱ,ctime�������Ÿı�.��Ϊ�ļ���С�ȶ������ԣ�
����˵atimeҲһ����ı䣬Ҫ���޸��ļ������ȶ�ȡ����ʵ�ǲ��Եģ�����ȡ���ļ������޸������ݣ�
�磺echo ��This is a test!��>>/etc/issue,issue�ļ����ݻ�䣬����û�ж�ȡ�ļ�������atime��û�иı�
vi�༭������cat��һ���ĵ���atime��ı䣻����viʱ�Ƿ�д�����֣��˳�ʱ�����w������ʱ�䶼��䡣
	����Ŀ¼��
����һ��Ŀ¼��atime�ı䣬mtime��ctime���䣻
�޸�һ��Ŀ¼����һ��Ŀ¼��touchһ���ļ���mtime��ctime��ı䣬atime��һ�����
�鿴�ĵ���ʱ��
ls -l /etc/man.config	Ĭ�ϲ鿴����mtime
ls -l --time=atime /etc/man.config
ls -l --time=ctime /etc/man.config
## �޸��ĵ���ʱ��##
touch[-acdmt]����
ѡ�������
-a�����޶�access time��
-c�����޸ĵ�����ʱ�䣬���õ����������򲻽����µ�����
-d��������Խ����޶������ڶ�����Ŀǰ�����ڣ�Ҳ����ʹ��--date="���ڻ�ʱ��"
-m�����޸�mtime��
-t��������Խ����޶���ʱ�������Ŀǰ��ʱ�䣬��ʽΪ[YYMMDDhhmm]

����һ���ļ�ϵͳ
mount /dev/hda2 /mnt/hda2	����һ������hda2����-ȷ��Ŀ¼'/mnt/hda2'�Ѿ�����
umount /dev/hda2		ж��һ������hda2����-�ȴӹ��ص�'/mnt/hda2'�˳�
fuser -km /mnt/hda2		���豸��æʱǿ��ж��
umount -n/mnt/hda2		����ж�ز�������д��/etc/mtab�ļ�-���ļ�Ϊֻ���򵱴���д��ʱ�ǳ�����
mount /dev/fd0 /mnt/floppy	����һ������
mount /dev/cdrom /mnt/cdrom	����һ��cdrom��dvdrom
mount /dev/hdc /mnt/cdrecorder	����һ��cdrw��dvdrom
mount /dev/hdb /mnt/cdrecorder	����һ��cdrw��dvdrom
mount -o loopfile.iso /mnt/cdrom����һ���ļ���ISO�����ļ�
mount-tvfat /dev/hda5 /mnt/hda5����һ��WindowsFAT32�ļ�ϵͳ
mount /dev/sda1 /mnt/usbdisk	����һ��usb���̻������豸
mount-tsmbfs-ousername=user,password=pass//WinClient/share/mnt/share����һ��windows���繲��

���̿ռ�
dumpe2fs [-bh] װ���ļ���	���Բ�ѯ��superblock ���ݣ�ÿ�� block group����Ϣ
	 -b ���г�����Ϊ����Ĳ���(һ���ò����ɣ���)
	 -h �����г�superblock�����ݣ������г��������������ݣ�
df -h Ŀ¼�����ļ���	��ʾ�ļ�ϵͳ����ʹ��������λ��GBytes��MBytes��KBytes
   -i Ŀ¼�����ļ���				��λ��inode����
du		��ʾ��ǰĿ¼��ÿһ��Ŀ¼������
du /*		��ʾ��Ŀ¼��ÿ��Ŀ¼��ռ�е�����
du -a		��ʾ��ǰĿ¼��ÿһ��Ŀ¼�µ�ÿһ������������
��ʾ�ĵ�λ
   -h ��G/M����ʽ��ʾ��-k��KBytesΪ��λ��-m��MBytesΪ��λ
�Ƿ������Ŀ¼�Ĵ�С
   -s ����ʾ��Ŀ¼�����֣����ǵ�ǰĿ¼���������Ŀ¼�Ĵ�С
   -S ����ʾ��Ŀ¼�����֣����������Ŀ¼�Ĵ�С
rpm-q-a--qf'%10{SIZE}t%{NAME}n'|sort-k1,1n�Դ�СΪ����������ʾ�Ѱ�װ��rpm����ʹ�õĿռ�(fedora,redhat��ϵͳ)
dpkg-query-W-f='${Installed-Size;10}t${Package}n'|sort-k1,1n�Դ�СΪ������ʾ�Ѱ�װ��deb����ʹ�õĿռ�(ubuntu,debian��ϵͳ)

�û���Ⱥ��
groupadd group_name		����һ�����û���
groupdel group_name		ɾ��һ���û���
groupmod -n new_group_name old_group_name	������һ���û���
useradd -c "NameSurname" -g admin -d /home/user 1-s /bin/bashuser1����һ������"admin"�û�����û�
useradd user1			����һ�����û�
userdel -r user1		ɾ��һ���û�('-r'�ų���Ŀ¼)
usermod -c "UserFTP" -g system -d /ftp/user1 -s /bin/nologinuser1�޸��û�����
passwd				�޸Ŀ���
passwd user1			�޸�һ���û��Ŀ���(ֻ����rootִ��)
chage -E 2005 -12 -31 user1	�����û������ʧЧ����
pwck				���'/etc/passwd'���ļ���ʽ���﷨�����Լ����ڵ��û�
grpck				���'/etc/passwd'���ļ���ʽ���﷨�����Լ����ڵ�Ⱥ��
newgrp group_name		��½��һ���µ�Ⱥ���Ըı��´����ļ���Ԥ��Ⱥ��

�ļ���Ȩ��
r(read)			4���ɶ�ȡ��һ������ִ��cp��ǰ�᣺�û����ڵ�������Ŀ¼��xȨ�ޣ�
w(write)		2�����������޸ĵ�����ɾ���õ�����ǰ�᣺�û��Ե�����rȨ�ޣ��Ե�������Ŀ¼��xȨ�ޣ�
x(execute)		1�����Ա�ϵͳִ�е�Ȩ��
Ŀ¼��Ȩ��
r(read)			��ls�Ȳ�ѯ��Ŀ¼�µ��ļ���
w(write)		�����µĵ�����Ŀ¼
			ɾ���Ѿ����ڵĵ�����Ŀ¼(���۸õ�����Ȩ��Ϊ��)
			���Ѵ��ڵĵ�����Ŀ¼���и���
			���Ƹ�Ŀ¼�ڵĵ�����Ŀ¼��λ��
x(execute)		����cd����Ŀ¼��Ϊ����Ŀ¼

��Ҫ������
ʹ���߿��Խ���һ�������Ļ���Ȩ��Ϊ�Σ�
Ŀ¼����Ȩ�ޣ��û��ڸ�Ŀ¼Ҫ���� w,x��Ȩ�ޣ��ص��� w ��
�û�����ĳĿ¼ִ�и�Ŀ¼�µ�ĳ��ָ��Ļ���Ȩ��Ϊ�Σ�
Ŀ¼����Ȩ�ޣ��û��ڸ�Ŀ¼����Ҫ��x��Ȩ�ޣ�
��������Ȩ�ޣ�ʹ�����ڸõ���������Ҫ��x��Ȩ��

u;g;o
Ԥ������£�
�����ĵ�Ȩ��Ĭ��Ϊ��666
����Ŀ¼Ȩ��Ĭ��Ϊ��777
umask��ָ������Ĭ��ֵ������»�Ҫȥ����Ȩ��
���umaskΪ003
������(-rw-rw-rw-)-(--------wx)=-rw-rw-r--=666-003=664�м�
Ŀ¼��(drwxrwxrwx)-(--------wx)=drwxrwxr--=777-003=663

	SetUID��SUIDȨ��ָ����s�����ڵ���ӵ���ߣ�u����x��λ�ã�-rwsr-xr-x,��Ч������
1.SUIDȨ�޽��Զ����Ƴ���(binaryprogram)��Ч��==>>/usr/bin/passwd�Ƕ������ļ���
2.ִ���߶��ڸó�����Ҫ����x�Ŀ�ִ��Ȩ�ޣ�==>>vbird��/usr/bin/passwd���������˵�Ǿ���xȨ�޵ģ�vbird��ִ��passwd��
					==>>passwd��ӵ������root����˺ţ�
3.��Ȩ�޽���ִ�иó���Ĺ�������Ч(run-time)��
	����������������ִ���߽����иó���ӵ����(owner)��Ȩ�ޡ�==>>vbirdִ��passwd�Ĺ����У�����ʱ���root��Ȩ��

	s��Ⱥ��(g)��xʱ���ΪSetGID,SGID,-rwx--s--x

	StickyBit,SBITĿǰ�����Ŀ¼��Ч�����ڵ����Ѿ�û��Ч���ˡ�
�û����ڴ�Ŀ¼����w,xȨ�ޣ������д���Ȩ��ʱ��
�û��ڸ�Ŀ¼�½���������Ŀ¼ʱ�������Լ���root����Ȩ��ɾ���õ���

SUID/SGID/SBITȨ���趨
4ΪSUID
2ΪSGID
1ΪSBIT
chmod 4755 test	==>>-rwsr-xr-x<<====>>chmodu+s
chmod 6755 test	==>>-rwsr-sr-x<<====>>chmodg+s
chmod 1755 test	==>>-rwxr-xr-t<<====>>chmodo+t
chmod 7666 test	==>>-rwSrwSrwT,����ӵ���߱����û��XȨ�ޣ���д��ʾΪ�գ�û��Ȩ�޸������ˡ�
��磬228

����(Sector)Ϊ��С�������浥λ��ÿ������Ϊ 512 bytes��
���������һ�����Ǿ��Ǵ���(Cylinder)�������Ƿָ��(partition)����С��λ��
��һ����������Ҫ�������У�(1)��Ҫ������(Master boot record, MBR)���ָ��(partition table)������MBRռ��446 bytes����partition table��ռ��64 bytes��

��Ҫ�ָ�������ָ����������ı�(Ӳ�̵�����)������ָ����ֻ����һ��(����ϵͳ������)���߼��ָ���������ָ�����и�����ķָ�ۣ�
�ܹ�����ʽ������Ϊ���ݴ洢�ķָ��Ϊ��Ҫ�ָ����߼��ָ����ָ��޷���ʽ����
�߼��ָ������������ϵͳ����ͬ���� Linux ϵͳ�У�IDE Ӳ������� 59 ���߼��ָ�(5�ŵ�63��)�� SATA Ӳ������ 11 ���߼��ָ�(5 �ŵ� 15 ��)��

superblock���Ǐ��� filesystem ��������Ϣ������inode/block��������ʹ������ʣ�������Լ��ļ�ϵͳ�ĸ�ʽ�������Ϣ�ȣ�
inode���Ǐ����������ԣ�һ������ռ��һ��inode��ͬʱ�Ǐ��˵������������ڵ�block���룻
block��ʵ�ʼǏ����������ݣ�������̫��ʱ����ռ�ö�� block ��
����ʽ�ļ�ϵͳ(indexed allocation)
FAT ��ʽÿ�� block ���붼�Ǐ���ǰһ�� block ����

ʹ��"+"����Ȩ�ޣ�ʹ��"-"����ȡ��
chmod ugo +rwx directory1	����Ŀ¼��������(u)��Ⱥ��(g)�Լ�������(o)rwxȨ��
chmod go -rwx directory1	ɾ��Ⱥ��(g)��������(o)��Ŀ¼�Ķ�дִ��Ȩ��
chown user1 file1		�ı�һ���ļ�������������
chown -R user1 directory1	�ı�һ��Ŀ¼�����������Բ�ͬʱ�ı��Ŀ¼�������ļ�������
chgrp group1 file1		�ı��ļ���Ⱥ��
chown user1:group1 file1	�ı�һ���ļ��������˺�Ⱥ������
find /-perm -u+s		����һ��ϵͳ������ʹ����SUID���Ƶ��ļ�
chmodu +s /bin/file1		����һ���������ļ���SUIDλ-���и��ļ����û�Ҳ�������������ͬ����Ȩ��
chmodu -s /bin/file1		����һ���������ļ���SUIDλ
chmodg +s /home/public		����һ��Ŀ¼��SGIDλ-����SUID�������������Ŀ¼��
chmodg -s /home/public		����һ��Ŀ¼��SGIDλ
chmodo +t /home/public		����һ���ļ���STIKYλ-ֻ����Ϸ�������ɾ���ļ�
chmodo -t /home/public		����һ��Ŀ¼��STIKYλ

�ļ�����������ʹ��"+"����Ȩ�ޣ�ʹ��"-"����ȡ��
chattr +a file1	ֻ������׷�ӷ�ʽ��д�ļ�
chattr +c file1	��������ļ��ܱ��ں��Զ�ѹ��/��ѹ
chattr +d file1	�ڽ����ļ�ϵͳ����ʱ��dump���򽫺�������ļ�
chattr +i file1	���óɲ��ɱ���ļ������ܱ�ɾ�����޸ġ���������������
chattr +s file1	����һ���ļ�����ȫ��ɾ��
chattr +S file1	һ��Ӧ�ó��������ļ�ִ����д������ʹϵͳ���̰��޸ĵĽ��д������
chattr +u file1	���ļ���ɾ����ϵͳ�����������Ժ�ָ������ɾ�����ļ�
lsattr			��ʾ���������

ѹ���ļ�
*.gz	gzip����ѹ���m����
gzip [-cdtv#] ������
	-d	��ѹ��������ͬ��gunzip file1.gz��##Ϊ��û��ѹ���Ĳ���
	-c	��ѹ���m�����������Ļ�ϣ���͸���������ص���t����
	-t	�����Öt����һ��ѹ���ļ��mһ���ԡ������������޴�ڻ��
	-v	������ʾ��ԭ����/ѹ���ļ����mѹ���ȵ���Ϣ��
	-#	ѹ���ȼ���-1 ��裬����ѹ������ -9 ����������ѹ������ã�Ԥک��-6
zcat ������.gz	����ѹ����ȡ�ı���ʽ�е�����

*.bz2	bzip2 ����ѹ���m����
bzip2 [-cdkzv#] �n��
	-z	ѹ��#Ϊ�γ�����ѹ���Ĳ������Ӳ���z��������
	-d	��ѹ����ͬ��bunzip2 file1.bz2
	-k	����Դ�ļ�����آ��ɾ��ԭʼ�m����ร�
	-c	��ѹ���m���̲����m�����������Ļ�ϣ�
	-v	������ʾ��ԭ����/ѹ���ļ����mѹ���ȵ���Ϣ��
	-#	��gzip ͬ���m�������ڼ���ѹ���Ȱm������ -9 ��ѣ� -1 ��裡
bzcat �n��.bz2	����ѹ����ȡ�ı���ʽ�е�����

zip file1.zip file1		����һ��zip��ʽ��ѹ����
zip -r file1.zip file1 file2 dir1�������ļ���Ŀ¼ͬʱѹ����һ��zip��ʽ��ѹ����
unzip file1.zip		��ѹһ��zip��ʽѹ����


����ļ�
rar a file1.rar test_file	����һ������'file1.rar'�İ�
rar a file1.rar file1 file2 dir1	ͬʱѹ��'file1','file2'�Լ�Ŀ¼'dir1'
rar x file1.rar		��ѹrar��
unrar x file1.rar		��ѹrar��

����ļ�
-cΪ���ѡ�û��ѹ������-vѡ����Բ鿴������̷�����ʲô���飻
tar����û���Զ������������֣�-fѡ���Ҫ�������������֣�-xΪ�ͷŽ�ѹ��
-C��-xѡ��һ��ʹ�ã�����ѡ����ѹ����Ŀ¼

tar -cv -f pack.tar file1	����һ����ѹ���Ĵ���ļ�(��tarfile)
tar -cv -f pack.tar file1 file2 dir1 ����һ��������'file1','file2'�Լ�'dir1'��
tar -t -f pack.tar		��ʾһ�����е�����
tar -xv farchive.tar		�ͷ�һ����

tar -jcv -f filename.tar.bz2	��bzip2ѹ�����߽�������ļ�ѹ������tarball��
tar -jxv -f filename.tar.bz2 -C /tmp	��bz2��ʽ��ѹ�����ͷŵ�/tmpĿ¼��
    -zxv                                  gz
tar -zcv -f filename.tar.gz	��gzipѹ�����߽�������ļ�ѹ��

RPM��-��Fedora,Redhat������ϵͳ��
rpm -ivh package.rpm		��װһ��rpm��
rpm -ivh --nodeepspackage.rpm	��װһ��rpm��������������ϵ����
rpm -U package.rpm		����һ��rpm�������ı��������ļ�
rpm -F package.rpm		����һ��ȷ���Ѿ���װ��rpm��
rpm -e package_name.rpm	ɾ��һ��rpm��
rpm -qa			��ʾϵͳ�������Ѿ���װ��rpm��
rpm-qa|grephttpd		��ʾ���������а���"httpd"������rpm��
rpm-qipackage_name		��ȡһ���Ѱ�װ����������Ϣ
rpm-qg"SystemEnvironment/Daemons"��ʾһ�������rpm��
rpm-qlpackage_name		��ʾһ���Ѿ���װ��rpm���ṩ���ļ��б�
rpm-qcpackage_name		��ʾһ���Ѿ���װ��rpm���ṩ�������ļ��б�
rpm-qpackage_name--whatrequires��ʾ��һ��rpm������������ϵ���б�
rpm-qpackage_name--whatprovides��ʾһ��rpm����ռ�����
rpm-qpackage_name--scripts	��ʾ�ڰ�װ/ɾ���ڼ���ִ�еĽű�l
rpm-qpackage_name--changelog	��ʾһ��rpm�����޸���ʷ
rpm-qf/etc/httpd/conf/httpd.conf	ȷ���������ļ����ĸ�rpm�����ṩ
rpm-qppackage.rpm-l		��ʾ��һ����δ��װ��rpm���ṩ���ļ��б�
rpm--import/media/cdrom/RPM-GPG-KEY	���빫Կ����֤��
rpm--checksigpackage.rpm	ȷ��һ��rpm����������
rpm-qagpg-pubkey		ȷ���Ѱ�װ������rpm����������
rpm-Vpackage_name		����ļ��ߴ硢��ɡ����͡������ߡ�Ⱥ�顢MD5����Լ�����޸�ʱ��
rpm-Va			���ϵͳ�������Ѱ�װ��rpm��-С��ʹ��
rpm-Vppackage.rpm		ȷ��һ��rpm����δ��װ
rpm2cpiopackage.rpm|cpio--extract--make-directories*bin*��һ��rpm�����п�ִ���ļ�
rpm-ivh/usr/src/redhat/RPMS/`arch`/package.rpm��һ��rpmԴ�밲װһ�������õİ�
rpmbuild--rebuildpackage_name.src.rpm	��һ��rpmԴ�빹��һ��rpm��

YUM�����������-��Fedora,RedHat������ϵͳ��
yuminstallpackage_name	���ز���װһ��rpm��
yumlocalinstallpackage_name.rpm	����װһ��rpm����ʹ�����Լ�������ֿ�Ϊ��������������ϵ
yumupdatepackage_name.rpm	���µ�ǰϵͳ�����а�װ��rpm��
yumupdatepackage_name	����һ��rpm��
yumremovepackage_name	ɾ��һ��rpm��
yumlist			�г���ǰϵͳ�а�װ�����а�
yumsearchpackage_name	��rpm�ֿ�����Ѱ�����
yumcleanpackages		����rpm����ɾ�����صİ�
yumcleanheaders		ɾ������ͷ�ļ�
yumcleanall			ɾ�����л���İ���ͷ�ļ�

DEB��(Debian,Ubuntu�Լ�����ϵͳ)
dpkg-ipackage.deb	��װ/����һ��deb��
dpkg-rpackage_name	��ϵͳɾ��һ��deb��
dpkg-l		��ʾϵͳ�������Ѿ���װ��deb��
dpkg-l|grephttpd	��ʾ���������а���"httpd"������deb��
dpkg-spackage_name	����Ѿ���װ��ϵͳ��һ�����������Ϣ
dpkg-Lpackage_name	��ʾϵͳ���Ѿ���װ��һ��deb�����ṩ���ļ��б�
dpkg--contentspackage.deb��ʾ��δ��װ��һ�������ṩ���ļ��б�
dpkg-S/bin/ping	ȷ���������ļ����ĸ�deb���ṩ

APT�������(Debian,Ubuntu�Լ�����ϵͳ)
apt-getinstallpackage_name	��װ/����һ��deb��
apt-cdrominstallpackage_name	�ӹ��̰�װ/����һ��deb��
apt-getupdate			�����б��е������
apt-getupgrade		���������Ѱ�װ�����
apt-getremovepackage_name	��ϵͳɾ��һ��deb��
apt-getcheck			ȷ������������ֿ���ȷ
apt-getclean			�����ص��������������
apt-cachesearchsearched-package���ذ�����Ҫ�����ַ��������������

�鿴�ļ�����
file file1	�鿴file1���ĵ�����������ASCII��data������binary����û��ʹ�õ���̬��ʽ��(sharelibrary)�ȵ���Ϣ
which ifconfig	����PATH��������������淶��·����ȥ��Ѱ��ִ�е����ĵ�����cd��bash�ڽ���ָ����which�Ҳ���
whereis[-bmsu]������Ŀ����
	-b:����binary��ʽ�ĵ���
	-m:������˵���ļ�manual·���µĵ���
	-s:����source�tԴ����
	-u:��Ѱ��������������Ŀ���е��������⵵
locate[-ir]keyword
	ѡ�������
	-i�����Դ�Сд�Ĳ��죻
	-r������ɽ����_��ʾ������ʾ��ʽ
which��locateѰ�ҵ��������ɡ��ѽ��������ݿ�/var/lib/mlocate/���������������Ѱ���ģ����Բ���ֱ����ȥӲ�����д�ȡ���ݣ��Ǻǣ���Ȼ�Ǐ����ن�
?
updatedb������/etc/updatedb.conf��ک��ȥ��ѰϵͳӲ���ڵ��ļ������Ը���/var/lib/mlocate�ڵ����ݿ⵵����
locate������/var/lib/mlocate�ڵ����ݿ���أ��ҳ��û�����Ĺؼ����ļ�����

findָ��
find[PATH][option][action]
ѡ�������
	1.��ʱ���йص�ѡ���-atime��-ctime��-mtime����-mtime˵��
-mtimen��nΪ���֣�����Ϊ��n��֮ǰ�ġ�һ��֮�ڡ������Ĺ����ݵĵ�����
-mtime+n���г���n��֮ǰ(����n�챾��)�����Ĺ����ݵĵ���������
-mtime-n���г���n��֮��(��n�챾��)�����Ĺ����ݵĵ���������
-newerfile��fileΪһ�����ڵĵ������г���file��Ҫ�µĵ�������
	2.��ʹ���߻������йصĲ�����
-uidn��nΪ���֣�����������û����˺�ID����UID�����UID�ǼǏ���/etc/passwd���治�˺����ƶ�Ӧ�����֡�
-gidn��nΪ���֣����������������ID�����pGID�����GID�Ǐ���/etc/group
-username��nameΪʹ�����˺�����ร�����dmtsai
-groupname��nameΪ����ร�����users��
-nouser��Ѱ�ҵ�����ӵ���߲�����/etc/passwd���ˣ�
-nogroup��Ѱ�ҵ�����ӵ��Ⱥ�鲻����ء/etc/group�ĵ�����
	3.�뵵��Ȩ�޼������йصĲ�����
-name filename����Ѱ�ļ���Ϊfilename�ĵ�����
-size[+-]SIZE����Ѱ��SIZE��Ҫ��(+)��С(-)�ĵ��������SIZE�Ĺ���У�c:����byte��k:����1024bytes��Ҫ�ұ�50KB��Ҫ��ĵ���������-size +50k
-type TYPE����Ѱ����������ΪTYPE�ģ�������Ҫ�У�һ�����浵��(f),װ�õ���(b,c),Ŀ¼(d),���ᵵ(l),socket(s),��FIFO(p)�����ԡ�
-perm mode����Ѱ����Ȩ�ޡ��պõ��ڡ�mode�ĵ�����-rwsr-xr-x������Ϊ4755
-perm -mode����Ѱ����Ȩ�ޡ�����Ҫȫ������mode��Ȩ�ޡ��ĵ���������Ҫ��Ѱ-rwxr--r--����0744�ĵ�����ʹ��-perm-0744������Ȩ��Ϊ-rwsr-xr-x����4755ʱҲ�ᱻ�г�����Ϊ-rwsr-xr-x�������Ѿ�������-rwxr--r--�������ˡ�
-perm +mode����Ѱ����Ȩ�ޡ�������һmode��Ȩ�ޡ��ĵ�������Ѱ-rwxr-xr-x ����-perm +755 ʱ������Ϊ -rw-------Ҳ�ᱻ�г�����Ϊ���� -rw���Դ���
	4.find������Խ��еĶ���
	find / -perm +7000 -exec ls -l {} \;
{} ������ǡ��� find �ҵ������ݡ�������ͼ��ʾ��find�Ľ���ᱻ���õ�{}λ���У�
-exec һֱ�� \; �ǹؼ��ʣ����� find ���⶯������ʼ (-exec) ������ (\;)
��Ϊ��;���� bash ������������������ģ�������÷�б�������ѡ�
	find /etc -name '*httpd*'
����Ҫ�ҳ� /etc ���µ���������httpd�ĵ���

catfile1	�ӵ�һ���ֽڿ�ʼ����鿴�ļ�������
tacfile1	�����һ�п�ʼ����鿴һ���ļ�������
nl			����кŽ�����ʾ
more			һҳҳ��ʾ��enter����һ�У��ո�����һҳ��b�ط�һҳ��q�˳�
less			����more���������⻹����ʹ��pageup��pagedown
head-12		�鿴�ĵ���ǰ12��
tail-14		�鿴�ĵ��ĺ�14��
head-n20fail|tail-n10	�鿴�ĵ���10��20��
tail-f/var/log/messages	ʵʱ�鿴����ӵ�һ���ļ��е�����
od-tTYPE		TYPE��ѡ�������
			a������Ĭ���ַ������
			c��ʹ��ASCII�ַ����
			d[size]������ʮ����(decimal)������ݣ�ÿ������ռ��sizebytes��
			f[size]�����ø�����(floating)������ݣ�ÿ����ռ��sizebytes��
			o[size]�����ð˽���(octal)������ݣ�ÿ������ռ��sizebytes��
			x[size]������ʮ������(hexadecimal)������ݣ�ÿ������ռ��sizebytes��
od-toCc/etc/issue	��/etc/issue���������������8��λ�г�����ֵ��ASCII�Ķ��ձ�
�ı�����
catfile1file2...|command<>file1_in.txt_or_file1_out.txt
						generalsyntaxfortextmanipulationusingPIPE,STDINandSTDOUT
catfile1|command(sed,grep,awk,grep,etc...)>result.txt�ϲ�һ���ļ�����ϸ˵���ı����������д��һ�����ļ���
catfile1|command(sed,grep,awk,grep,etc...)>>result.txt�ϲ�һ���ļ�����ϸ˵���ı����������д��һ�����е��ļ���


grepAug/var/log/messages	���ļ�'/var/log/messages'�в��ҹؼ���"Aug"
grep^Aug/var/log/messages	���ļ�'/var/log/messages'�в�����"Aug"��ʼ�Ĵʻ�
grep[0-9]/var/log/messages	ѡ��'/var/log/messages'�ļ������а������ֵ���
grepAug-R/var/log/*		��Ŀ¼'/var/log'������Ŀ¼�������ַ���"Aug"
sed's/stringa1/stringa2/g'example.txt	��example.txt�ļ��е�"string1"�滻��"string2"
sed'/^$/d'example.txt	��example.txt�ļ���ɾ�����пհ���
sed'/*#/d;/^$/d'example.txt��example.txt�ļ���ɾ������ע�ͺͿհ���
echo'esempio'|tr'[:lower:]''[:upper:]'	�ϲ����µ�Ԫ������
echo$PATH			�鿴��������ЩĿ¼�������
PATH="$PATH":/root		���һ��Ŀ¼
sed-e'1d'result.txt		���ļ�example.txt���ų���һ��
sed-n'/stringa1/p'		�鿴ֻ�����ʻ�"string1"����
sed-e's/*$//'example.txt	ɾ��ÿһ�����Ŀհ��ַ�
sed-e's/stringa1//g'example.txt		���ĵ���ֻɾ���ʻ�"string1"������ʣ��ȫ��
sed-n'1,5p;5q'example.txt	�鿴�ӵ�һ�е���5������
sed-n'5p;5q'example.txt	�鿴��5��
sed-e's/00*/0/g'example.txt	�õ������滻�����
cat-nfile1			��ʾ�ļ�������
catexample.txt|awk'NR%2==1'ɾ��example.txt�ļ��е�����ż����
echoabc|awk'{print$1}'	�鿴һ�е�һ��
echoabc|awk'{print$1,$3}'	�鿴һ�еĵ�һ�͵�����
pastefile1file2		�ϲ������ļ�������������
paste-d'+'file1file2	�ϲ������ļ������������ݣ��м���"+"����
sortfile1file2		���������ļ�������
sortfile1file2|uniq	ȡ�������ļ��Ĳ���(�ظ�����ֻ����һ��)
sortfile1file2|uniq-u	ɾ��������������������
sortfile1file2|uniq-d	ȡ�������ļ��Ľ���(ֻ����ͬʱ�����������ļ��е��ļ�)
comm-1file1file2		�Ƚ������ļ�������ֻɾ��'file1'������������
comm-2file1file2		�Ƚ������ļ�������ֻɾ��'file2'������������
comm-3file1file2		�Ƚ������ļ�������ֻɾ�������ļ����еĲ���

�ַ����ú��ļ���ʽת��
dos2unixfiledos.txtfileunix.txt	��һ���ı��ļ��ĸ�ʽ��MSDOSת����UNIX
unix2dosfileunix.txtfiledos.txt	��һ���ı��ļ��ĸ�ʽ��UNIXת����MSDOS
recode..HTML<page.txt>page.html	��һ���ı��ļ�ת����html
recode-l|more			��ʾ���������ת����ʽ

�ļ�ϵͳ����
badblocks-v/dev/hda1		������hda1�ϵĻ��ſ�
fsck/dev/hda1			�޸�/���hda1������linux�ļ�ϵͳ��������
fsck.ext2/dev/hda1		�޸�/���hda1������ext2�ļ�ϵͳ��������
e2fsck/dev/hda1		�޸�/���hda1������ext2�ļ�ϵͳ��������
e2fsck-j/dev/hda1		�޸�/���hda1������ext3�ļ�ϵͳ��������
fsck.ext3/dev/hda1		�޸�/���hda1������ext3�ļ�ϵͳ��������
fsck.vfat/dev/hda1		�޸�/���hda1������fat�ļ�ϵͳ��������
fsck.msdos/dev/hda1		�޸�/���hda1������dos�ļ�ϵͳ��������
dosfsck/dev/hda1		�޸�/���hda1������dos�ļ�ϵͳ��������

��ʼ��һ���ļ�ϵͳ
mkfs/dev/hda1		��hda1��������һ���ļ�ϵͳ
mke2fs/dev/hda1	��hda1��������һ��linuxext2���ļ�ϵͳ
mke2fs-j/dev/hda1	��hda1��������һ��linuxext3(��־��)���ļ�ϵͳ
mkfs-tvfat32-F/dev/hda1	����һ��FAT32�ļ�ϵͳ
fdformat-n/dev/fd0	��ʽ��һ������
mkswap/dev/hda3	����һ��swap�ļ�ϵͳ

SWAP�ļ�ϵͳ
mkswap/dev/hda3����һ��swap�ļ�ϵͳ
swapon/dev/hda3����һ���µ�swap�ļ�ϵͳ
swapon/dev/hda2/dev/hdb3��������swap����

����
dump-0aj-f/tmp/home0.bak/home	����һ��'/home'Ŀ¼����������
dump-1aj-f/tmp/home0.bak/home	����һ��'/home'Ŀ¼�Ľ���ʽ����
restore-if/tmp/home0.bak		��ԭһ������ʽ����
rsync-rogpav--delete/home/tmp	ͬ�����ߵ�Ŀ¼
rsync-rogpav-essh--delete/homeip_address:/tmp		ͨ��SSHͨ��rsync
rsync-az-essh--deleteip_addr:/home/public/home/local	ͨ��ssh��ѹ����һ��Զ��Ŀ¼ͬ��������Ŀ¼
rsync-az-essh--delete/home/localip_addr:/home/public	ͨ��ssh��ѹ��������Ŀ¼ͬ����Զ��Ŀ¼
ddbs=1Mif=/dev/hda|gzip|sshuser@ip_addr'ddof=hda.gz'	ͨ��ssh��Զ��������ִ��һ�α��ݱ��ش��̵Ĳ���
ddif=/dev/sdaof=/tmp/file1		���ݴ������ݵ�һ���ļ�
tar-Pufbackup.tar/home/user		ִ��һ�ζ�'/home/user'Ŀ¼�Ľ���ʽ���ݲ���
(cd/tmp/local/&&tarc.)|ssh-Cuser@ip_addr'cd/home/share/&&tarx-p'	ͨ��ssh��Զ��Ŀ¼�и���һ��Ŀ¼����
(tarc/home)|ssh-Cuser@ip_addr'cd/home/backup-home&&tarx-p'		ͨ��ssh��Զ��Ŀ¼�и���һ������Ŀ¼
tarcf-.|(cd/tmp/backup;tarxf-)			���ؽ�һ��Ŀ¼���Ƶ���һ���ط�������ԭ��Ȩ�޼�����
find/home/user1-name'*.txt'|xargscp-av--target-directory=/home/backup/--parents
					��һ��Ŀ¼���Ҳ�����������'.txt'��β���ļ�����һ��Ŀ¼
find/var/log-name'*.log'|tarcv--files-from=-|bzip2>log.tar.bz2����������'.log'��β���ļ�������һ��bzip��
ddif=/dev/hdaof=/dev/fd0bs=512count=1	��һ����MBR(MasterBootRecord)���ݸ��Ƶ����̵Ķ���
ddif=/dev/fd0of=/dev/hdabs=512count=1	���Ѿ����浽���̵ı����лָ�MBR����

����
cdrecord-vgracetime=2dev=/dev/cdrom-ejectblank=fast-force	���һ���ɸ�д�Ĺ�������
mkisofs/dev/cdrom>cd.iso			�ڴ����ϴ���һ�����̵�iso�����ļ�
mkisofs/dev/cdrom|gzip>cd_iso.gz		�ڴ����ϴ���һ��ѹ���˵Ĺ���iso�����ļ�
mkisofs-J-allow-leading-dots-R-V"LabelCD"-iso-level4-o./cd.isodata_cd����һ��Ŀ¼��iso�����ļ�
cdrecord-vdev=/dev/cdromcd.iso		��¼һ��ISO�����ļ�
gzip-dccd_iso.gz|cdrecorddev=/dev/cdrom-	��¼һ��ѹ���˵�ISO�����ļ�
mount-oloopcd.iso/mnt/iso			����һ��ISO�����ļ�
cd-paranoia-B					��һ��CD����ת¼���쵽wav�ļ���
cd-paranoia--"-3"				��һ��CD����ת¼���쵽wav�ļ��У�����-3��
cdrecord--scanbus				ɨ��������ʶ��scsiͨ��
ddif=/dev/hdc|md5sum			У��һ���豸��md5sum���룬����һ��CD

����-����̫����WIFI���ߣ�
ifconfigeth0		��ʾһ����̫����������
ifupeth0		����һ��'eth0'�����豸
ifdowneth0		����һ��'eth0'�����豸
ifconfigeth0192.168.1.1netmask255.255.255.0����IP��ַ
ifconfigeth0promisc	����'eth0'�ɻ���ģʽ����̽���ݰ�(sniffing)
dhclienteth0		��dhcpģʽ����'eth0'
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


fork()������һ�ε��ã����η���
http://www.cnblogs.com/nosadness/p/4051220.html
http://blog.csdn.net/songxueyu/article/details/9115393
��ǰһֱ�Ի�ʲô��һ�ε��ã����η��ء�ͨ����������������֪��ԭ�ɡ��ֽ��Լ�������¼�ڴˡ�
׼��֪ʶ��
�ڴ��еĽ��̰����������֣���ִ���ļ��������򣩣�������ݣ������������ڴ�ռ䣬�������ȣ��������Ļ������������Ϊ���Ķ��������Ķ�ȥ��������֪��������CPU��Դ���ޣ����˾�ֻ��һ�������Ҳ�������޶ࡣ����ǰ���еĳ���������Ƕ���CPU�����ģ����Ӧ���ǿ������ͨ�ģ�û���ĸ������̻������ô�������˷�CPU��������ڲ���ϵͳ�ĵ���֮�£�һ������һ�㲻���ͷִ�е�β������ϣ�ϵͳ�ᰴ�ճ���ִ�е�˳�����ȼ������ȷʵ���ĸ�����ռ�õ�ǰCPU��������ϵ���Щ�������Ҫ������ʱ�̵�״̬�������̵��������ֶ�Ҫ��¼���������Ա��ٴ�ִ���ܹ���ȫ�ָ��������ǰ�������ϵ�ʱ���㹻�̣�Ӧ�ø��˺������ĸо�����Ҳ����Ϊʲô�������ܹ�һ�������֣�һ�߿���ƪ���µ�Ե�ʡ��Ҳ£���Ҳ��Ϊʲô�������ڴ��еĳ���ᱻ��Ϊ���̵�Ե�ʡ�
���������ǾͿ��Կ�ʼ����fork�����ˡ�����һ����������һ�����p=fork()��
���ǳƵ�ǰ����p=fork()�Ľ���Ϊ�����̣�������pid�ſ�����getpid()��ȡ��fork()����һ��ֵ������p����ʱp���������Ӧ����һ������������ʾfork()�²������ӽ��̵�pid�š�
fork()�������Ǹ��ӽ��̺͸�������ȫ��ͬ�������ڸ����̵���fork()��һ˲������ͬ�ģ�������Ҳ����ȫ�����ģ���ִ�е��Ⱥ�˳����ȫ�ɲ���ϵͳ���ȣ��Ҹ����̲�һ�����ӽ�����ִ���꣩��fork()��������ͬ��һ��ĺ����������̲������fork�����������ӽ�����ȫִ������ִ��pid=fork()����Ĵ��롣���ߣ��������Ϊfork()�����þ��ǲ���һ���ӽ��̣������ӽ����Ƿ����С������������û��ϵ��
�������ӽ��̵�ִ�С�����֪������Ȼfork()�Ǹ��Ƹ����̣���ôp=fork()���֮ǰ�����Ӧ������ȫһ�¡��ڸ�������p�����õ������ӽ��̵�pid�ţ��������ӽ�����ͬ��������һ��p����������ֵ�ǲ���Ҳ���ӽ��̵�pid���أ����ǹؼ�����! ��ʵ�ϣ��ӽ����е�p�����õ���ֵ�����ӽ��̵�pid�ţ�����0���ӽ��̵�pid����ȫ������getpid()���ӽ����еõ�����Ҳ��Ϊʲô��fork()һ�ε��ã����η��أ������Ľ���͵ȼ��ڣ�һ�����򱻵��������γ��������̣���p=fork()֮ǰ������������ȫһ��������һ��ʱ��һ��������p����ֵΪ��һ�����̵�pid�ţ�����һ��������p����ֵΪ0������֮���������̷ֵ����������κιϸ𡣣�ע�⣬�������̿�����ͬһ���������𡣣�


#################################
## ����������Ա�����ʽ���
mount | column -t		//��tab��Ϊ�ָ���������ɱ��
mount | column -ts:		//��������ţ��˴�Ϊð�ţ�Ϊ�ָ�

## �ظ�ִ��һ������ֱ�����ɹ�
while true
do ping -c 1 google.com > /dev/null 2>&1 && break	//����׼�������׼���붼���뵽/dev/null����ִ̨�У�break
done ;




