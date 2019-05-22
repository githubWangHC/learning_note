from sklearn.feature_extraction import DictVectorizer
from sklearn.feature_extraction.text import CountVectorizer, TfidfVectorizer
from sklearn.preprocessing import MinMaxScaler, StandardScaler, Imputer
from sklearn.feature_selection import VarianceThreshold
from sklearn.decomposition import PCA
import jieba
import numpy as np

# 特征抽取
#
# 导入包
# from sklearn.feature_extraction.text import CountVectorizer
#
# # 实例化CountVectorizer
#
# vector = CountVectorizer()
#
# # 调用fit_transform输入并转换数据
#
# res = vector.fit_transform(["life is short,i like python","life is too long,i dislike python"])
#
# # 打印结果
# print(vector.get_feature_names())
#
# print(res.toarray())


def dictvec():
    """
    字典数据抽取
    :return: None
    """
    # 实例化 sparse 格式，找下标，节约内存方便处理；若为false则转换为 ndarray 数组
    dict = DictVectorizer(sparse=True)
    # 调用fit_transform
    data = dict.fit_transform([{'city': '北京','temperature': 100}, {'city': '上海','temperature':60}, {'city': '深圳','temperature': 30}])
    print(dict.get_feature_names())	#特征值的名字，也就是每一列的名字
    print(dict.inverse_transform(data))	#
    print(data)
    return None

'''
特征抽取，将数据中有类别的数据分别转换为 one-hot编码
数值型的不做转换
'''

def countvec():
    """
    对文本进行特征值化，文本分类，情感分析，单个字母或者单个字不计入
    :return: None
    """
    cv = CountVectorizer()
    data = cv.fit_transform(["人生 苦短，我 喜欢 python python", "人生漫长，不用 python", "this is a test"])#用空格或者逗号帮助其分词
    print(cv.get_feature_names())	#单词/词语，不计重复
    print(data)
    print(data.toarray())	#feature 中的每个词在每篇文章（data每一行）中所出现的次数
    return None

#分词
def cutword():

   #生成器 https://foofish.net/what-is-python-generator.html
    con1 = jieba.cut("今天很残酷，明天更残酷，后天很美好，但绝对大部分是死在明天晚上，所以每个人不要放弃今天。")
    con2 = jieba.cut("我们看到的从很远星系来的光是在几百万年之前发出的，这样当我们看到宇宙时，我们是在看它的过去。")
    con3 = jieba.cut("如果只用一种方式了解某样事物，你就不会真正了解它。了解事物真正含义的秘密取决于如何将其与我们所了解的事物相联系。")

    # 转换成列表,有的地方显示可以直接转换成str，不用先转换成list http://www.cnblogs.com/baiboy/p/jieba3.html
    #https://github.com/fxsjy/jieba
    content1 = list(con1)
    content2 = list(con2)
    content3 = list(con3)

    # 吧列表转换成字符串
    c1 = ' '.join(content1)
    c2 = ' '.join(content2)
    c3 = ' '.join(content3)
    print("type con1:" + str(type(con1)) + ", type content1:" + str(type(content1)) + "type c1" + str(type(c1)))

    return c1, c2, c3

def hanzivec():
    """
    中文特征值化
    :return: None
    """
    c1, c2, c3 = cutword()
    print(c1, c2, c3)
    cv = CountVectorizer()
    data = cv.fit_transform([c1, c2, c3])
    print(cv.get_feature_names())
    print(data.toarray())		#如果两篇文章属于同一类，则某些词的频率相似
    return None

#避免只看重词频，还要看重要程度；idf:逆文档频率 inverse document frequency 
#如果一个词语在某些文档出现的频率较高，另外一些文档中出现的频率较低则认为这些词能够用来区分文档log(总文档数/该词出现的文档数)
def tfidfvec():
    """
    中文特征值化
    :return: None
    """
    c1, c2, c3 = cutword()
    print(c1, c2, c3)
    tf = TfidfVectorizer()
    data = tf.fit_transform([c1, c2, c3])
    print(tf.get_feature_names())
    print(data.toarray())
    return None

def mm():
    """
    归一化处理
    :return: NOne
    """
    mm = MinMaxScaler(feature_range=(2, 3))

    data = mm.fit_transform([[90,2,10,40],[60,4,15,45],[75,3,13,46]])

    print(data)

    return None


def stand():
    """
    标准化缩放
    :return:
    """
    std = StandardScaler()

    data = std.fit_transform([[ 1., -1., 3.],[ 2., 4., 2.],[ 4., 6., -1.]])

    print(data)

    return None


def im():
    """
    缺失值处理
    :return:NOne
    """
    # NaN, nan
    im = Imputer(missing_values='NaN', strategy='mean', axis=0)

    data = im.fit_transform([[1, 2], [np.nan, 3], [7, 6]])

    print(data)

    return None


def var():
    """
    特征选择-删除低方差的特征
    :return: None
    """
    var = VarianceThreshold(threshold=1.0)
    data = var.fit_transform([[0, 2, 0, 3], [0, 1, 4, 3], [0, 1, 1, 3]])
    print(data)
    return None

def pca():
    """
    主成分分析进行特征降维
    :return: None
    """
    pca = PCA(n_components=0.9)
    data = pca.fit_transform([[2,8,4,5],[6,3,0,8],[5,4,9,1]])
    print(data)
    return None


if __name__ == "__main__":
    tfidfvec()