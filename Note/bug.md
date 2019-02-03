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

import tensorflow as tf
import os

def weight_init(shape):
    w = tf.Variable(tf.random_normal(shape, mean = 0, stddev = 1, name = 'weight'))
    return w
    
def bias_init(sp):
    b = tf.Variable(tf.constant(0.0, shape = sp))
    return b

def get_datasets():
    dir = "./cifar-10-batches-bin/"
    fis = os.listdir(dir)
    fi_list = [os.path.join(dir, fi) for fi in fis if fi[-3: ] == 'bin']
    
    #print("fis is \n", fis, fi_list)
    fi_queue = tf.train.string_input_producer(fi_list)
    reader = tf.FixedLengthRecordReader(32 * 32 * 3 + 1)
    key, value = reader.read(fi_queue)
    img_laber = tf.decode_raw(value, tf.uint8)
    
    label = tf.slice(img_laber, [0], [1])
    label_one_hot = tf.one_hot(label, depth = 10)
    label_one_hot_reshape = tf.reshape(label_one_hot, shape = [10])
    image = tf.slice(img_laber, [1], [32 * 32 * 3])
    label_batch, img_batch = tf.train.batch([label_one_hot_reshape, image], num_threads = 2, batch_size = 100, capacity = 100)
    
    #print("before run\n", img_batch, label_batch)
    return img_batch, label_batch

def cnn_model():
    #1.定义用于接收 img 原始数据的变量
    x_train = tf.placeholder(tf.float32, shape = [None, 32 * 32 * 3])
    x_reshpe = tf.reshape(x_train, shape = [-1, 32, 32, 3])
    
    #3. 第一层卷积层,input [-1, 32, 32, 3]；8个filter，一个为[4, 4, 3, 8 * 3]；b = [8 * 3] strides = [1, 1, 1, 1], padding = "SAME"，输出为[-1,32,32,8*3]
    w_conv1 = weight_init([4, 4, 3, 8 * 3])
    b_conv1 = bias_init([8 * 3])
    x_relu1 = tf.nn.relu(tf.nn.conv2d(x_reshpe, w_conv1, strides = [1, 1, 1, 1], padding = "SAME")) + b_conv1
    print("\n x_relu1 \n", x_relu1)
    #4. 第一层迟化层,input [-1, 32, 32, 8 * 3]；filter为[1, 2, 2, 1], stride = [1, 2, 2, 1], padding = "SAME"，输出为[-1, 16, 16, 8 * 3]
    x_poo1_1 = tf.nn.max_pool(x_relu1, ksize = [1, 2, 2, 1], strides = [1, 2, 2, 1], padding = "SAME")
    print("\n x_poo1_1 \n", x_poo1_1)
    
    #5. 第二层卷积层,input [-1, 16, 16, 8 * 3]；16个filter，一个为[4, 4, 8 * 3, 8 * 3 * 16]； stride = [1, 1, 1, 1], padding = "SAME"，输出为[-1, 16, 16, 8 * 3 × 16]
    w_conv2 = weight_init([4, 4, 8 * 3, 8 * 3 * 16])
    b_conv2 = bias_init([8 * 3 * 16])
    x_relu2 = tf.nn.relu(tf.nn.conv2d(x_poo1_1, w_conv2, strides = [1, 1, 1, 1], padding = "SAME")) + b_conv2
    print("\n x_relu2 \n", x_relu2)
    #6. 第二个迟化层,input [-1, 16, 16, 8 * 3 × 16]；filter为[1, 4, 4, 1], stride = [1, 4, 4, 1], padding = "SAME"，输出为[-1, 4,4, 8 * 3 × 16]
    x_poo1_2 = tf.nn.max_pool(x_relu2, ksize = [1, 4, 4, 1], strides = [1, 4, 4, 1], padding = "SAME")
    print("\n x_poo1_2 \n", x_poo1_2)
    
    #7. 全连接层,input [-1, 4, 4, 8 * 3 × 16]; 转换为 [-1, 2 * 2 * 8 * 3 * 16], w 为 [4 * 4 * 32 * 3 * 32, 10], b = [10]
    fc_input = tf.reshape(x_poo1_2, shape = [-1, 4 * 4 * 8 * 3 * 16])
    w_fc = weight_init([4 * 4 * 8 * 3 * 16, 10])
    b_fc = bias_init([10])
    y_predict = tf.matmul(fc_input, w_fc) + b_fc
    print("x_train, y_predict\n", x_train, y_predict)
    return x_train, y_predict, x_poo1_2


def net_model():

    x = tf.placeholder(tf.float32, [None, 32 * 32 * 3])
    y_true = tf.placeholder(tf.float32, [None, 10])
    img_batch, label_batch = get_datasets()

    w = tf.Variable(tf.random_normal([32 * 32 * 3, 10], mean = 0, stddev = 1, name = "weight"))
    b = tf.Variable(tf.constant(0.0, shape = [10,]), name = 'bias')
    
    init_op = tf.global_variables_initializer()

    y_predict = tf.matmul(x, w) + b
    loss = tf.reduce_mean(tf.nn.softmax_cross_entropy_with_logits(labels = y_true, logits = y_predict))
    train_op = tf.train.GradientDescentOptimizer(0.01).minimize(loss)
    
    equal_list = tf.equal(tf.argmax(y_predict, 1), tf.argmax(y_true, 1))
    acc = tf.reduce_mean(tf.cast(equal_list, tf.float32))
    
    with tf.Session() as sess:
        sess.run(init_op)
        cd = tf.train.Coordinator()
        th = tf.train.start_queue_runners(sess, coord = cd)
        img, label = sess.run([img_batch, label_batch])
        #print('\nimg_batch\n', img_batch, '\nimg_batch\n', label_batch)
        
        for i in range(6000):
            sess.run(train_op, feed_dict = {x: img, y_true:label})
            print("i = %d, acc = %f" %(i, sess.run(acc, feed_dict = {x: img, y_true:label})))
        print("\n \nweight is \n", w.eval(), "\n \n bias is \n", b.eval())
        cd.request_stop()
        cd.join(th)

    return None

def train_conv():
    
    #1. 获取数据，[-1, 32*32*3]
    img_batch, label_batch = get_datasets()
    #2. 转换形状，[-1, 32, 32, 3]
    #img_reshape = tf.reshape(img_batch, shape = [-1, 32, 32, 3])
    img_train = tf.cast(img_batch, tf.float32)
    
    x_train, y_predict, why = cnn_model()
    print("x_train, y_predict\n", x_train, y_predict)
    y_true = tf.placeholder(tf.float32, shape = [None, 10])
    
    loss = tf.nn.softmax_cross_entropy_with_logits(labels = y_true, logits = y_predict)
    train_op = tf.train.GradientDescentOptimizer(0.01).minimize(loss)
    
    equal_list = tf.equal(tf.argmax(y_predict, 1), tf.argmax(y_true, 1))
    equal_list_float32 = tf.cast(equal_list, tf.float32)
    acc = tf.reduce_mean(equal_list_float32)
    init_op = tf.global_variables_initializer()
    with tf.Session() as sess:
        cd = tf.train.Coordinator()
        th = tf.train.start_queue_runners(sess, coord = cd)
        sess.run(init_op)
        img, label = sess.run([img_train, label_batch])
        get_why = sess.run(why, feed_dict = {x_train: img})
        print("x_train, y_predict, why=======================\n", get_why.shape)
        for i in range(100):
            sess.run(train_op, feed_dict = {x_train: img, y_true: label})
            print("i = %d, acc = %f" %(i, sess.run(acc, feed_dict = {x_train: img, y_true: label})))
        cd.request_stop()
        cd.join(th)

if __name__ == "__main__":
     #net_model()
     train_conv()
