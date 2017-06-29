在Linux上安装Git
首先，你可以试着输入git，看看系统有没有安装Git。$sudo apt-get install git就可以直接完成Git的安装，非常简单。
安装完成后，还需要最后一步设置，在命令行输入：
$ git config --global user.name "Your Name"
$ git config --global user.email "email@example.com"
因为Git是分布式版本控制系统，所以，每个机器都必须自报家门：你的名字和Email地址。git config命令的--global参数，用了这个参数，表示你这台机器上所有的Git仓库都会使用这个配置，当然也可以对某个仓库指定不同的用户名和Email地址。

初始化一个Git仓库，使用git init命令。
添加文件到Git仓库，分两步：
第一步，使用命令git add <file>，注意，可反复多次使用，添加多个文件；
第二步，使用命令git commit -m "描述语言"，完成。

要随时掌握工作区的状态，使用git status命令。
如果git status告诉你有文件被修改过，用git diff可以查看修改内容。

git管理的是修改不是管理文件
第一次修改 -> git add -> 第二次修改 -> git commit
第一次修改后用git add命令，在工作区的第一次修改被放入暂存区，准备提交，但是，在工作区的第二次修改并没有放入暂存区，所以，git commit只负责把暂存区的修改提交了，也就是第一次的修改被提交了，第二次的修改不会被提交。
在bra1分支新建文件file1但是没有add与commit，新建bra2之后对file1进行了add与commit;其效果与在bra2上新建file1并进行add与commit效果一样。也就是说git中的各个分支没有权限的差别，新建file1与在一个已有的文件进行修改在git看来都是修改。

撤销操作：
场景1：当你改乱了工作区某个文件的内容，想直接丢弃工作区的修改时，用命令git checkout -- file。
场景2：当你不但改乱了工作区某个文件的内容，还添加到了暂存区时，想丢弃修改，分两步，第一步用命令git reset HEAD file，就回到了场景1，第二步按场景1操作。
场景3：已经提交了不合适的修改到版本库时，想要撤销本次提交，不过前提是没有推送到远程库。
用HEAD表示当前版本，上一个版本就是HEAD^，上上一个版本就是HEAD^^，当然往上100个版本写写成HEAD~100
$ git reset --hard HEAD^
Git同样允许使用commit id来实现版本的历史之间穿梭，使用命令
git reset --hard commit_id
其中的commit id可以用git reflog查看命令历史来获得。

删除文件
在工作区删除文件后没有add或者commit
此时如果确实想删除这个文件则可用如下命令删除
$ git rm filename 
并且
$ git commit -m "删除了一个文件"
此时如果后悔了则可以哟个下面的命令来重新在工作区得到这个文件

分支。每次提交，Git都把它们串成一条时间线，这条时间线就是一个分支。截止到目前，只有一条时间线，在Git里，这个分支叫主分支，即master分支。HEAD严格来说不是指向提交，而是指向master，master才是指向提交的，所以，HEAD指向的就是当前分支。一开始的时候，master分支是一条线，Git用master指向最新的提交，再用HEAD指向master，就能确定当前分支，以及当前分支的提交点：
查看分支：git branch
创建分支：git branch <name>
切换分支：git checkout <name>
创建+切换分支：git checkout -b <name>
合并某分支到当前分支：git merge <name>
删除分支：git branch -d <name>	//不能删除自己所在的分支，就是先checkout再删除
如果要删除一个没有被合并过的分支，可以通过git branch -D <name>强行删除。

分支的冲突
新建分支bra1 => 在bra1内修改 => bra1 add => bra1 commit => 切换到分支bra2：
（1）在bra2分支内则看不到bra1之前的修改。
工作区（Working Directory）就是电脑里能看到的目录；工作区有一个隐藏目录.git，这个不算工作区，而是Git的版本库（Repository）；Git的版本库里存了很多东西，其中最重要的就是称为暂存区（stage或者叫index），还有Git为我们自动创建的第一个分支master，以及指向master的一个指针叫HEAD。git add命令实际上就是把要提交的所有修改放到暂存区（Stage），然后执行git commit就可以一次性把暂存区的所有修改提交到分支。个人理解是在commit之前文件在工作区，哪个分支都可以看到，修改后在某一个分支进行了add则修改被保存在stage，最后commit则把修改保存到某一个分支，这时别的分支就看不到了。看不到是什么意思？一个大活人就这样无缘无故消失了吗？确实消失了，不但ls看不到，而且在两个分支之间进行切换时视图中的文件一会儿出现，一会儿消失，可有意思了。。[^=^]
（2）如果在bra1进行commit之后又进行了修改（这里所讨论的修改限于对已经commit的文件内容进行了更改，新建一个新的文件不算，为啥？不知道），则不能切换到bra2。
（3）在bra2分支没有紧接着合并而是继续对做修改，再合并时就会出现冲突。
在发生冲突之后无论对文件作任何修改，master再次提交后均认为这是最终版本了，不会再次提醒冲突了。

分支的管理策略
通常，合并分支时，如果可能，Git会用Fast forward模式，但这种模式下，删除分支后，会丢掉分支信息。如果要强制禁用Fast forward模式，Git就会在merge时生成一个新的commit，这样，从分支历史上就可以看出分支信息。
在branch1上建立file1，只有在add，commit之后才可能和其他分支合并
将branch1与branch2合并首先要切换到branch2中。在禁用Fast forward的模式下进行合并则
$ git merge --no-ff -m "merge with no-ff" branch1
因为本次合并要创建一个新的commit，所以加上-m参数，把commit描述写进去。

用git log看看分支历史
$ git log --graph --pretty=oneline --abbrev-commit
最后一列的提示和-m""中的信息对应；垂直线应该表示没有被修改，斜线表示要合并了。只有所有的分支都被合并到master之后才能更新log图

*   cfdea17 br1 merge to master
|\  
| *   be53377 br2 merge to br1
| |\  
| | *   29d9411 br3 merge to br2
| | |\  
| | | * 09d3578 br3 commit file3
| | |/  
| | * 5b1a86d br2 commit file2
| |/  
| * 6044552 br1 commit file1
|/  
*   699e2ef br1 merge br3

链接远程仓库GitHub，在本地创建ssh key:
$ ssh-keygen -t rsa -C "whc15@outlook.com"
之后显示如下两行提示表示将要创建一个rsa key，以及确定key的保存地址，可以更换地址，也可以enter。之后提示是否要为key设置一个密码，enter就不设置。如果一切顺利的话，可以在用户主目录里找到.ssh目录，里面有id_rsa和id_rsa.pub两个文件，这两个就是SSH Key的秘钥对，id_rsa是私钥，不能泄露出去，id_rsa.pub是公钥，可以放心地告诉任何人。用记事本打开公钥，复制里边的文字，粘贴到在线账户（setting => SSH and GPG key => New SSH key）
是否连接成功呢，可以如下测试：	
$ ssh -T git@github.com
有如下提示：
Hi githubWangHC! You've successfully authenticated, but GitHub does not provide shell access.
则说明已经链接好了

将本地库与远程库关联
点击左上角的加号，选择New repository来创建一个仓库learning
将本地仓库与GitHub仓库进行关联
git remote add origin git@github.com:githubWangHC/learning.git
其中账户名和仓库名不要填写错误。origin是远程库的称号。GitHub给出的地址不止一个，还可以用https://github.com/michaelliao/gitskills.git这样的地址。实际上，Git支持多种协议，默认的git://使用ssh，但也可以使用https等其他协议。使用https除了速度慢以外，还有个最大的麻烦是每次推送都必须输入口令，但是在某些只开放http端口的公司内部就无法使用ssh协议而只能用https。

将本地库内容推送到远程库
$ git push -u origin master
第一次推送的时候加上参数-u不但会把本地的master分支内容推送的远程新的master分支，还会把本地的master分支和远程的master分支关联起来，在以后的推送或者拉取时就可以简化命令为
$ git push origin master
实际上推送过程就是Git自动把本地的master分支和远程的master分支对应起来了，并且远程仓库的默认名称是origin。但是推送也不一定非要用master，其他分支也可以根据情况选择是否推送。

将远程库内容复制到本地
$ git clone git@github.com:githubWangHC/learning.git


http://www.liaoxuefeng.com/wiki/0013739516305929606dd18361248578c67b8067c8c017b000
