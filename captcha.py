import tensorflow as tf

def read_img_lab():
    '''
    1.创建阅读队列
    '''
    fi_queue = tf.train.string_input_producer(["./data/tfrecoders/captcha.tfrecords"])
    #fi_queue = tf.data.TFRecordDataset(["./data/tfrecoders"])
    reader = tf.TFRecordReader()
    key, value = reader.read(fi_queue)
    
    feature_dict = {"image": tf.FixedLenFeature([], tf.string), "label": tf.FixedLenFeature([], tf.string)}
    features = tf.parse_single_example(value, features = feature_dict)
    image = tf.decode_raw(features['image'], tf.uint8)
    label = tf.decode_raw(features['label'], tf.uint8)
    
    image_reshape = tf.reshape(image, [20, 80, 4])
    label_reshape = tf.reshape(label, [4])
    image_batch, label_batch = tf.train.batch([image_reshape, label_reshape], batch_size = 100, num_threads = 5, capacity = 100)
    '''
    image = [100, 20, 80, 3]
    '''
    print (image, label)
    
    return image_batch, label_batch
    
def train_nn(image):
    
    img_reshpae = tf.reshape(image, [-1, 20 * 80 * 4])
    waitht = tf.Variable(tf.random_normal(shape = [20 * 80 * 4, 4 * 26], mean = 0.0, stddev = 1.0))
    bise = tf.Variable(tf.constant(0.0, shape = [4 * 26]))
    
    y_predict = tf.matmul(tf.cast(img_reshpae, tf.float32), waitht) + bise
    print("train_nn y_predict")
    print(y_predict)
    return y_predict

def one_hot_label(label):

    label_one_hot = tf.one_hot(label, depth = 26, on_value = 1.0, axis = 2)
    return label_one_hot

def captcha_recognize():
    
    image, label = read_img_lab()
    y_predict = train_nn(image)
    
    label_true = one_hot_label(label)
    
    #矩阵的相乘，所以要将label_true转换为2D的。
    cross_entropy = tf.nn.softmax_cross_entropy_with_logits(labels = tf.reshape(label_true, [100, 4 * 26]), logits = y_predict)
    loss = tf.reduce_mean(cross_entropy)
    train_op = tf.train.GradientDescentOptimizer(0.01).minimize(loss)
    
    #y_predict为何要改变 y_predict转换之前shape=(100, 104)，要一个字母一个字母比较，所以要单拎出来。
    equal_list = tf.equal(tf.argmax(tf.reshape(y_predict, shape = [-1, 4, 26]), 2), tf.argmax(label_true, 2))
    
    acc = tf.reduce_mean(tf.cast(equal_list, tf.float32))
    
    init_op = tf.global_variables_initializer()
    with tf.Session() as sess:
        sess.run(init_op)
        coord = tf.train.Coordinator()
        threads = tf.train.start_queue_runners(sess, coord = coord)

        for i in range(500):
            print("line 65")
            sess.run(train_op)
            print("i = %d, acc = %f" %(i, sess.run(acc)))
        
        coord.request_stop()
        coord.join(threads)
    return None

if __name__ == "__main__":

    captcha_recognize()