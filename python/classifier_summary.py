'''
看看别人是怎么写代码的.
https://necromuralist.github.io/data_science/posts/predicting-cancer/
https://stackoverflow.com/questions/7571635/fastest-way-to-check-if-a-value-exist-in-a-list
'''
from sklearn.datasets import load_iris
from sklearn.model_selection import train_test_split

from sklearn.neighbors import KNeighborsClassifier
from sklearn.svm import SVC
from sklearn.naive_bayes import MultinomialNB
from sklearn.linear_model import SGDClassifier
from sklearn.tree import DecisionTreeClassifier, export_graphviz
from sklearn.ensemble import RandomForestClassifier

from sklearn.metrics import confusion_matrix, classification_report
import xgboost as xgb
import numpy as np
import math

def evaluate_predict(y_true, y_predict, model):
    
    print("=============" + model + "==============")
    # use confusion_matrix evaluate the classifier
    cm_res = confusion_matrix(y_true, y_predict)
    print("confusion_matrix\n", cm_res)
    
    # get classification report of the knn classification
    report = classification_report(y_true, y_predict)
    print("classification_report\n", report)

def forest_classifier(x_train, y_train, x_test, y_test):
    forest = RandomForestClassifier()
    forest.fit(x_train, y_train)
    y_predict = forest.predict(x_test)
    
    print("forest score is \n", forest.score(x_test, y_test))
    print("feature importance \n", forest.feature_importances_)
    evaluate_predict(y_test, y_predict, model = 'forest')

def tree_clf(x_train, y_train, x_test, y_test):
    clf = DecisionTreeClassifier()
    clf.fit(x_train, y_train)
    y_predict = clf.predict(x_test)
    
    print("tree clf is \n", clf.score(x_test, y_test))
    evaluate_predict(y_test, y_predict, model = 'tree clf')
    
    export_graphviz(clf, out_file = './data/clf.dot', feature_names = ['a', 'b', 'c', 'd'])

def svc_model(x_train, y_train, x_test, y_test):
    svc = SVC(kernel = 'linear')
    svc.fit(x_train, y_train)
    y_predict = svc.predict(x_test)
    
    evaluate_predict(y_test, y_predict, model = 'svc_model')

def knn_model(x_train, y_train, x_test, y_test):
    classifier = KNeighborsClassifier(n_neighbors = 3)
    classifier.fit(x_train, y_train)
    y_predict = classifier.predict(x_test)
    
    evaluate_predict(y_test, y_predict, model = 'knn_model')

def naive_bayes(x_train, y_train, x_test, y_test):
    bayes = MultinomialNB()
    bayes.fit(x_train, y_train)
    y_predict = bayes.predict(x_test)
    
    print("naive_bayes score is ", bayes.score(x_test, y_test))
    #your classifier is not predicting positives at all on the test data
    #Logistic Regression: UndefinedMetricWarning: Precision and F-score are ill-defined and being set to 0.0 in labels with no predicted samples
    evaluate_predict(y_test, y_predict, model = 'naive_bayes')

def SGDClassifier_model(x_train, y_train, x_test, y_test):
    sgd = SGDClassifier(penalty = "l2")
    sgd.fit(x_train, y_train)
    y_predict = sgd.predict(x_test)
    evaluate_predict(y_test, y_predict, model = 'SGDClassifier')

def load_iris_data():
    iris = load_iris()
    x = iris.data[:100]
    y = iris.target[:100]
    # x ndarry type, four features: sepal length, sepal width, petal length, petal width:
    # y ndarry type
    #print(iris.DESCR, x.shape, type(x), y.shape, type(y))
    # 分隔数据
    x_train, x_test, y_train, y_test = train_test_split(x, y, test_size = 0.25, random_state = 1, shuffle = True)
    return x_train, x_test, y_train, y_test

def log_reg(y_hat, y):
    p = 1.0 / (1.0 + np.exp(-y_hat))
    g = p - y.get_label()
    h = p * (1.0-p)
    return g, h

def err_rate(y_hat, y):
    return 'error', float(sum(y.get_label() != (y_hat > 0.5))) / len(y_hat)

def xgboost(x_train, y_train, x_test, y_test):
    data_train = xgb.DMatrix(x_train, label = y_train)
    data_test = xgb.DMatrix(x_test, label = y_test) #y_train[y_train == 3] = 0 标签从0开始。
    # 设置参数
    watchlist = [(data_test, 'eval'), (data_train, 'train')]
    n_round = 5
    param = {'max_depth': 2, 'eta': 1, 'silent': 1, 'objective': 'multi:softmax', 'num_class': 3} #
    bst = xgb.train(param, data_train, num_boost_round=n_round, evals=watchlist)
    #param = {'max_depth': 4, 'eta': 1, 'silent': 1, 'objective': 'reg:logistic'} #二分类可以自己定义函数
    #bst = xgb.train(param, data_train, num_boost_round=n_round, evals=watchlist, obj = log_reg, feval = err_rate)
    '''
    'eta' learning_rate: Step size shrinkage used in update to prevents overfitting.
    'objective' reg or classification function，建立一颗树时需要指定一阶导与二阶导，与xgb.train中的obj有何不同
    'reg:logistic' 输出0到1，logitraw输出负无穷到正无穷
    multi:softmax', 多分类问题用softmax ，分为三类
    '''
    # 计算错误率
    y_hat = bst.predict(data_test)
    print(y_hat, '\n\n',y_test)
    error = float(np.sum(y_test.reshape(1, -1) != y_test))/len(y_hat)
    #error = 1- float(np.sum((y_hat > 0.5) == y_test))/len(y_hat)
    print("错误率是 ", error)

def BGDClf_model(x_train, y_train, x_test, y_test):
    '''
    批量梯度下降算法实现
    '''
    alpha = 0.001
    lamda = 0.0001        #1e-4 > lambda
    is_detailed = True
    train_times = 100
    M, N = x_train.shape  #M samples N features
    theta = np.zeros(N).reshape(-1, 1) #N * 1
    for t in range(train_times):
        loss = np.dot(x_train, theta) -y_train #M * 1 = M * 1 - M * 1，计算每个样本的损失
        theta -= alpha * np.dot(x_train.T, loss) + lamda * theta # np.dot(N * M, M * 1) = N * 1，每个样本的损失乘以每一个theta的系数加和(矩阵乘法过程中有加和)后更新theta
        if((t%10 == 0) and (is_detailed)):
            print("t is: ", t , "mean loss is: ", np.mean(loss, axis = 0), "updated theta: ", theta.tolist())
    return theta

def GDClf_model(x_train, y_train, x_test, y_test):
    '''
    随机梯度下降
    '''
    alpha = 0.001
    lamda = 0.0001        #1e-4 > lambda
    is_detailed = True
    train_times = 100
    M, N = x_train.shape  #M samples N features
    theta = np.zeros(N)
    for t in range(train_times):
        for j in range(M):  #use one sample to update all theta 随机梯度下降
            loss = np.dot(x_train[j, :],theta) - y_train[j]
            theta -= alpha * loss * x_train[j, :] + lamda * theta
        if((t%10 == 0) and (is_detailed)):
            print("times = %d  loss = %f" %(t, loss), "updated theta is", theta)
    return theta

def sigmoid(x, theta):
    '''
    计算 x * theat，带入sigmoid函数
    '''
    exp = np.exp(-np.dot(x, theta))
    pow = np.array(list(map(lambda x : x ** (-1), exp)))
    return pow

def lrGD_model(x_train, y_train, x_test, y_test):
    '''
    load_iris_data()中数据分三类，前两类线性可分，所以可以拿来实验。
    '''
    alpha = 0.001
    lamda = 0.0001        #1e-4 > lambda
    is_detailed = True
    train_times = 1000
    M, N = x_train.shape  #M samples N features
    theta = np.zeros(N).reshape(-1, 1) #N * 1
    for t in range(train_times):
        y_hat = sigmoid(x_train, theta).reshape(-1, 1)
        loss = y_hat -y_train.reshape(-1, 1) #M * 1 = M * 1 - M * 1，计算每个样本的损失
        error = float(np.sum((y_hat > 0.5) != y_train.reshape(-1, 1))) / M #
        theta -= alpha * np.dot(x_train.T, loss) + lamda * theta # np.dot(N * M, M * 1) = N * 1，每个样本的损失乘以每一个theta的系数加和(矩阵乘法过程中有加和)后更新theta
        if((t%10 == 0) and (is_detailed)):
            print("t is: ", t , "mean loss is: ", error, "updated theta: ", theta.tolist())
    return theta

if __name__ == "__main__":
    x_train, x_test, y_train, y_test = load_iris_data()
    '''
    knn_model(x_train, y_train, x_test, y_test)
    svc_model(x_train, y_train, x_test, y_test)
    naive_bayes(x_train, y_train, x_test, y_test)
    SGDClassifier_model(x_train, y_train, x_test, y_test)
    tree_clf(x_train, y_train, x_test, y_test)
    forest_classifier(x_train, y_train, x_test, y_test)
    '''
    lrGD_model(x_train, y_train, x_test, y_test)

    #xgboost(x_train, y_train, x_test, y_test)
    