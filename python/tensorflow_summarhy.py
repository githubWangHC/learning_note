operation, op 专门运算的操作节点，使用tf的API定义的函数都是op
tensorflow指代数据
graph: 整个程序的结构
图默认已经注册，一组表示 tf.Operation计算单位的对象和tf.Tensor表示操作之间流动的数据单元的对象

tensorflow, session, graph

==tensorflow==
a = tf.constant(1.0)
b = tf.constant(2.0)  
Tensor("Const_1:0", shape=(), dtype=float32)
sum1 = tf.add(a, b)
Tensor("Add:0", shape=(), dtype=float32)

==graph==
gf = tf.get_default_graph()
print(gf)
tensorflow.python.framework.ops.Graph object at 0x0000000005619CF8
print(a.graph)      same above

用新图替换默认的图
g = tf.Graph()      address
print(g)
with g.as_default():
    d = tf.constant(8)
    print(d.graph)  address same above
等价于下面
with tf.get_default_graph(graph = g):
    d = tf.constant(8)
    print(d.graph)  address same above

==session==
with tf.Session() as sess:
    print(sess.run(sum1)) print 3.0 

sess = tf.Session()
with sess.as_default():   
    assert sess is tf.get_default_session()
    assert t.eval() == sess.run(t)
But we can fetch the values of many tensors in the same step.
Session.run() VS. Tensor.eval()?

==Assertions==
Assertions 可检查表达式返回值，True则继续，False则抛出异常

==with==
完整的sess API还包括sess.run(...) sess.close()但是在with上下文管理器可以在代码执行完毕之后自动close会话，不需要自己再参与。
more details about the with statement python
http://effbot.org/zone/python-with-statement.htm


=========
===misc===
=========
config=tf.ConfigProto(log_device_placement=True)
可以打印协议消息（Protocol Message）参与计算的设备

warming level
os.environ['TF_CPP_MIN_LOG_LEVEL']='2'

sess.run([a, b, sum1])，传入的参数要用[]成为数组。

类似于 numpy.ndarry ，张量封装了ndarry，
张量的属性：
         op的类型，张良的形状， 类型
Tensor("Const_1:0", shape=(), dtype=float32)
Tensor("Add:0", shape=(), dtype=float32)
分别对应于a.name，a.shape，a.dtype
a.op 是tensor 的计算节点
a.graph可以看到该tensor在哪张图上
a.eval()可以看到该tensor的默认值（如果有的话）eval要在session中才能使用，

梯度爆炸问题：权重的值变得很大，以至溢出。
重新设计网络；调整学习率；使用梯度截断；使用激活函数。从经验上看，0.1的学习率比较大了，太大导致Nan

32位的张量是最常用的
============
==张量的形状===
============
0维：()                         1维：(5)
2维：(5, 6)                   3维：(2, 3, 4)

==形状的改变==
动态形状与静态形状：区别是改变的过程是否有生成一个新的张量（动态）。

tf支持重载
a = tf.constant(1)
b = 2
sum = a + b
由于算式中有一个是tf类型，所以不会报错。


===静态改变形状===

plt = tf.placeholder(tf.float32, [None, 2])
plt.set_shape([3, 2]) 将plt静态地固定为3×2
plt.set_shape([4, 2]) 错误，一旦张量形状固定就不能再静态地重设；无返回值，plt的形状直接变。

动态转变可创建一个新的装量，但转换前后是元素个数应相同相同。
reshape = tf.reshape(plt, [4, 5])
reshape = tf.reshape(plt, [2, 2, 5])  元素数量要匹配,要用变量接受新的返回值

====创建特殊张量===
zeros = tf.zeros(shape, dtype = tf.float32, name = None)
返回具有dtype类型，shape形状的，所有元素均为0的张量。
ones= tf.zeros(shape, dtype = tf.float32, name = None)
创建随机张量
tf.random_normal(shape, mean=0.0, stddev=1.0, dtype=tf.float32, seed=None, name=None)

==张量类型的转换==
n1 = tf.constant(["1234","6789"])
n2 = tf.string_to_number(n1,out_type=tf.types.float32)

x = tf.constant([1.8, 2.2], dtype=tf.float32)
tf.cast(x, tf.int32)
tf.cast支持数值类型的转换，数值之外不支持。

===张量合并===
tf.concat(concat_dim, values, name='concat')

==变量==
tf.Variable(value, name = "w", trainable = True)
变量可以在训练过程重新被赋值
init_op = tf.global_variables_initializer()

==========================
===create an event file===
==========================
fw = tf.summary.FileWriter("./tmp/", graph = sess.graph)

===summary tensor===
tf.summary.scalar(name='',loss) 收集损失函数、准确率等单值变量，name为变量的名字，tensor为值
tf.summary.histogram(name=‘’,w) 收集高维度的变量参数
tf.summary.image(name=‘’,tensor) 收集输入的图片张量

===merge & add ===
merged = tf.summary.merge_all()
summary = sess.run(merged)，             
运行merge op，每次迭代都产生新结果，故都需运行
fw.add_summary(summary,i) 
添加到事件，i表示第几次的值

===open tensor===
tensorboard --logdir="./tmp/"    不要有空格

=================
===checkpoint===
=================
1，定义个保存模型的实例
sv = tf.train.Saver(var_list=None,max_to_keep=5)
    var_list:要保存/还原的变量。可用dict或list传递.
 max_to_keep：要保留的最近检查点文件的最大数

2. 在return之前保存会话
sv.save(sess, '/tmp/ckpt/test/model')
3. 在run之前用保存的参数重新初始化
os.path.exists("./tmp/ckpt/checkpoint")
sv.restore(sess, '/tmp/ckpt/test/model')    

===============
==命令行参数===
==============
tf.app.flags.DEFINE_integer("max_step", 100, "模型训练的步数")
tf.app.flags.DEFINE_string("model_dir", " ", "模型文件的加载的路径")

FG= tf.app.flags.FLAGS
FG.max_step
python tf01.py --max_step=500

=====read file======
fi_queue = tf.train.string_input_producer(filelist, shuffle = True)
reader = tf.TextLineReader()
key, value = reader.read(fi_queue)
s1, f1=tf.decode_csv(value, record_defaults = rd)

fi_queue = tf.train.string_input_producer(filelist)
reader = tf.WholeFileReader()
key, value = reader.read(fi_queue)
img_de = tf.image.decode_jpeg(value)

fi_queue = tf.train.string_input_producer(filelist)
reader = tf.FixedLengthRecordReader(32*32*3 + 1)
key, value = reader.read(fi_queue)
img_label = tf.decode_raw(value, tf.uint8)

x = tf.placeholder(tf.float32, [None, 32 * 32 * 3])
w_conv1 = tf.Variable(w_init([4, 4, 3, 96]))
w = tf.random_normial([,,,], mean = 0, stddev = 1, name = 'w')
feed_dict = {x_train: img, y_true: label}


===csv===
fi_queue = tf.train.string_input_producer([fi_list], shuffle = True)
创建CSV阅读器按行读取队列数据，混合读取
reader = tf.TextLineReader()
key, value = reader.read(fi_queue)
           key:    ./data/cc.csv:1',
           value: c1,1'
rd = [["Nan"], ["Nan"]]
s1, f1=tf.decode_csv(value, record_defaults = rd)
      s1: 'c1'      f1:  '1'

===image===
fi_queue = tf.train.string_input_producer(filelist)
reader = tf.WholeFileReader()

Tensor("ReaderReadV2:0", shape=(), dtype=string)
key, value = reader.read(fi_queue)

Tensor("DecodeJpeg:0", shape=(?, ?, ?), dtype=uint8)
img_de = tf.image.decode_jpeg(value)

http://www.cs.toronto.edu/~kriz/cifar.html
http://yann.lecun.com/exdb/mnist/

===mnist===
from tensorflow.examples.tutorials.mnist import input_data
mnist = input_data.read_data_sets("./mnist/", one_hot = True)
mnist_x, mnist_y = mnist.train.next_batch(50)

===bin===
fi_queue = tf.train.string_input_producer(filelist)
reader = tf.FixedLengthRecordReader(32*32*3 + 1)
key, value = reader.read(fi_queue)
img_label = tf.decode_raw(value, tf.uint8)
Tensor("DecodeRaw:0", shape=(?,), dtype=uint8)

label = tf.cast(tf.slice(img_label, [0], [1]), tf.uint8)
image = tf.slice(img_label, [1], [32*32*3])

Tensor("Slice:0", shape=(1,), dtype=uint8)
Tensor("Slice_1:0", shape=(3072,), dtype=uint8)

img_reshape = tf.reshape(image, [32, 32, 3])
Tensor("Reshape:0", shape=(32, 32, 3), dtype=uint8)

label, img = tf.train.batch([label, img_reshape], num_threads = 3, batch_size = 2, capacity = 3)
Tensor("batch:0", shape=(10, 1), dtype=uint8)
Tensor("batch:1", shape=(10, 32, 32, 3), dtype=uint8)
虽然是批处理，但是不会全部读取，只是读取一部分。

==def one calss===
class CifarRead(object):
    def __init__(self, filelist):
        self.filist = filelist

    def byteread(self):
        fi_queue = tf.train.string_input_producer(self.filist)

===TFRecord===

===构造tfrecord阅读器===
fi_queue = tf.train.string_input_producer(["./cifar.tfrecords"])
路径不对读不到文件。
===文件阅读器===
reader = tf.TFRecordReader()
key, value = reader.read(fi_queue)

===parse example===
第一个参数是数据形状一般不指定，feat_dit，features及存储example中的key要一致。
feat_dit = {"image": tf.FixedLenFeature([], 
tf.string), "label": tf.FixedLenFeature([], tf.int64)}

features = tf.parse_single_example(value, features = feat_dit)

image = tf.decode_raw(features["image"], tf.uint8)
img_reshape = tf.reshape(image, [self.w, self.h, self.channel])

label = tf.cast(features["label"], tf.uint8)

label, image = tf.train.batch([label, img_reshape], ...)

====写入TFRecorder===
writer = tf.python_io.TFRecordWriter("./cifar.tfrecords")
要指定到文件

==创建并写入example实例==
批处理得到的数据第一个维度是样本索引
img_val = img_bat[i].eval().tostring()#要存储为byte
lab_val = lab_bat[i].eval()[0]

img = tf.train.Feature(bytes_list = tf.train.BytesList(value = [img_val]))
label = tf.train.Feature(int64_list = tf.train.Int64List(value = [label_val]))
feature_dict ={"image": img, "label": label}

example = tf.train.Example(features = tf.train.Features(feature = feature_dict))
两个feature两个features

writer.write(example.SerializeToString())
将图片转换为二进制序列
writer.close()


神经网络(neural networks)的基本组成包括输入层、隐藏层、输出层。
而卷积神经网络的特点在于隐藏层分为卷积层和池化层(pooling layer，又叫下采样层)。
卷积层：通过在原始图像上平移来提取特征，每一个特征就是一个特征映射
池化层：通过特征后稀疏参数来减少学习的参数，降低网络的复杂度，（最大池化和平均池化）

卷积层：
tf.nn.conv2d(input, filter, strides=, padding=, name=None)
    input：样本特征, placeholder, shape [batch,heigth,width,channel] tpye float32,64
    filter：线性化variable 权重, 有n个,[filter_height, filter_width, in_channels, n * in_channels]
    strides：步长 = [1, stride, stride, 1]
    padding：“SAME”, “VALID”，填充算法的类型，VALID 表示不越界取样，剩余取样面积小于filter时停止取样
          SAME 表示填充使得变化后height,width一样大

激活函数：增加网络的非线性分割。
tf.nn.relu(features, name=None) features:卷积后加上偏置的结果
sigmoid函数相较于Relu计算量大，而且容易造成梯度消失（还是爆炸）（求不出权重和偏置）

池化层
Pooling 特征提取，过滤 Feature Map中不重要的样本，减少参数数量。Pooling的方法很多，最常用的是Max Pooling。
tf.nn.max_pool(value, ksize=, strides=, padding=,name=None)
    value:激活后的tensor shape = [batch, height, width, channels]
    ksize:池化窗口大小，[1, ksize, ksize, 1]
    strides:步长大小，[1,strides,strides,1]
    padding:“SAME”, “VALID”，填充算法的类型

VALID填充方法所导致的tensor形状的改变
input：[batch1, H1, W1, C1]
filter数量K，filter大小F，步长S，零填充大小P
filter：[F, F, C1, K*C1]

return batch1 * H2 * W2 * C2
    H2 = (H1 -F + 2P)/S + 1
    W2 = (H1 -F + 2P)/S + 1
    C2 = K * C2

===queue===

==进队出队操作==
q = tf.FIFOQueue(3, dtypes = tf.float32)
eq_many = q.enqueue_many([[0.1, 0.2, 0.3], ])
sess.run(eq_many)              初始化q

d_q = q.dequeue()                    定义一个出队列op
sess.run(d_q)                             运行出队列

e_q = q.enqueue(val)                定义一个进队列op
sess.run(e_q)                       运行进队列
q_r = tf.train.QueueRunner(q, enqueue_ops = [e_q] * 2)

用协调器保证主线程退出后子线程被回收
cd = tf.train.Coordinator()
thread = q_r.create_threads(sess, coord = cd, start = True)
cd.request_stop()
cd.join(thread)

主线程结束，session结束，子线程还在操作。


参数服务器paramenter server ps保存参数
工作服务器worker，去计算

同步更新，三个服务同时更新完之后平均得到w的变化方向。
异步更新。w1更新的参数是wa，w2服务器更新的参数是wb，则w是wa和wb的平均

有n个输入数据，通过权重与各数据之间的计算和，比较激活函数结果，得出输出
应用：很容易解决与、或、非问题

Mnist数据集神经网络分析

算法                   策略                          优化
线性回归       均方误差             梯度下降
逻辑回归       对数似然损失       梯度下降二分类
神经网络       交叉熵损失           反向传播算法

softMax回归，每个类别的概率为exp(yi)对所有类别的归一化。

交叉熵损失，J= -yc*logpc
当类别为one-hot编码时只有样本真实类别所对应的概率会体现，为了将损失降低则只有尽可能使得pc接近1
https://zhuanlan.zhihu.com/p/38241764
https://zhuanlan.zhihu.com/p/35709485

反向传播，从损失开始梯度下降更新权重
正向传播，输出经过一层层的计算得出输出

anaconda exe 清华镜像
https://mirrors.tuna.tsinghua.edu.cn/anaconda/archive/?C=M&O=A

Installing TensorFlow on Windows
https://stackabuse.com/installing-tensorflow-on-windows/

conda 操作
https://conda.io/docs/user-guide/tasks/manage-environments.html

conda create --name my_env python  # latest available python version
conda create --name my_env python=3.7  # specific python version

conda create --prefix /tmp/test-env python=2.7

conda env list
conda create --n tf
conda activate tf

conda install tensorflow
conda env remove -n tensorflow
