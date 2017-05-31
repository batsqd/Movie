package com.kmeans;

import java.util.Arrays;


import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * 
 * @author Yuanbo She
 * 
 */
public class Kmeans {

    /**
     * double[][] 元素全置0
     * 
     * @param matrix
     *            double[][]
     * @param highDim
     *            int
     * @param lowDim
     *            int <br/>
     *            double[highDim][lowDim]
     */
    private static void setDouble2Zero(double[][] matrix, int highDim, int lowDim) {
        for (int i = 0; i < highDim; i++) {
            for (int j = 0; j < lowDim; j++) {
                matrix[i][j] = 0;
            }
        }
    }

    /**
     * 拷贝源二维矩阵元素到目标二维矩阵。 foreach (dests[highDim][lowDim] = sources[highDim][lowDim]);
     * 
     * @param dests
     *            double[][]
     * @param sources
     *            double[][]
     * @param highDim
     *            int
     * @param lowDim
     *            int
     */
    private static void copyCenters(double[][] dests, double[][] sources, int highDim, int lowDim) {
        for (int i = 0; i < highDim; i++) {
            for (int j = 0; j < lowDim; j++) {
                dests[i][j] = sources[i][j];
            }
        }
    }

    /**
     * 更新聚类中心坐标
     * 
     * @param k
     *            int 分类个数
     * @param data
     *            kmeans_data
     */
    private static void updateCenters(int k, Kmeans_data data) {
        double[][] centers = data.centers;
        //yzy-将聚类中心数组清零，
        setDouble2Zero(centers, k, data.dim);
        int[] labels = data.labels;  
        int[] centerCounts = data.centerCounts;
        for (int i = 0; i < data.dim; i++) {
            for (int j = 0; j < data.length; j++) {
            	//yzy-labels[j]---data[j]元素在第labels[j]聚类
            	//下面是将在同一个聚类中心的所有元素对应分量相加，
            	//比如（1，1）和（2，2）属于同一个聚类，为（3，3）
                centers[labels[j]][i] += data.data[j][i];
            }
        }
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < data.dim; j++) {
            	// (2)是属于同一个聚类中的所有元素对应分量的累加后得到的，
            	// (1)是第i个聚类的质心
            	//   (1)               (2)       
                centers[i][j] = centers[i][j] / centerCounts[i];
            }
        }
    }

    /**
     * 计算两点欧氏距离
     * 
     * @param pa
     *            double[]
     * @param pb
     *            double[]
     * @param dim
     *            int 维数
     * @return double 距离
     */
    /*
     * 
     public static double dist(double[] pa, double[] pb, int dim) {
        double rv = 0;
        for (int i = 0; i < dim; i++) {
            double temp = pa[i] - pb[i];
            temp = temp * temp;
            rv += temp;
        }
        return Math.sqrt(rv);
    }
    */
    /*2016/12/30
    public static double getVectorLength(double vector[]){
		double result=0;
		for(int i=0;i<vector.length;i++){
			result+=Math.pow(vector[i], 2);
		}
		return Math.sqrt(result);
	}
    //改为余弦相似性
    public static double dist(double[] pa, double[] pb, int dim) {
        
        double numerator=0.0;//分子
        double denominator=0.0;//分母
        for (int i = 0; i < dim; i++) {
        	numerator+=(pa[i]*pb[i]);
        }
        denominator=getVectorLength(pa)*getVectorLength(pb)+1;//加一为了避免分母为0
        return Math.sqrt(numerator/denominator);//好像错了--曹亮说
    }*/
    public static double getVectorLength(double vector[]){
		double result=0;
		for(int i=0;i<vector.length;i++){
			result+=Math.pow(vector[i], 2);
		}
		return Math.sqrt(result);
	}
    //改为余弦相似性
    public static double dist(double[] pa, double[] pb, int dim) {
        
        double numerator=0.0;//分子
        double denominator=0.0;//分母
        for (int i = 0; i < dim; i++) {
        	numerator+=(pa[i]*pb[i]);
        }
        denominator=getVectorLength(pa)*getVectorLength(pb)+1;//加一为了避免分母为0
        return numerator/denominator;//好像错了--曹亮说
    }
    

    /**
     * 做Kmeans运算
     * 
     * @param k
     *            int 聚类个数
     * @param data
     *            kmeans_data kmeans数据类
     * @param param
     *            kmeans_param kmeans参数类
     * @return kmeans_result kmeans运行信息类
     */
    public static Kmeans_result doKmeans(int k, Kmeans_data data, Kmeans_param param) {
        // 预处理
        double[][] centers = new double[k][data.dim]; // 聚类中心点集
        data.centers = centers;
        int[] centerCounts = new int[k]; // 各聚类的包含点个数
        data.centerCounts = centerCounts;
        Arrays.fill(centerCounts, 0);
        int[] labels = new int[data.length]; // 各个点所属聚类标号
        data.labels = labels;
        double[][] oldCenters = new double[k][data.dim]; // 临时缓存旧的聚类中心坐标

        // 初始化聚类中心（随机或者依序选择data内的k个不重复点）
        if (param.initCenterMehtod == Kmeans_param.CENTER_RANDOM) { // 随机选取k个初始聚类中心
            Random rn = new Random();
            List<Integer> seeds = new LinkedList<Integer>();
            while (seeds.size() < k) {
                int randomInt = rn.nextInt(data.length);
                if (!seeds.contains(randomInt)) {
                    seeds.add(randomInt);
                }
            }
            Collections.sort(seeds);
            for (int i = 0; i < k; i++) {
                int m = seeds.remove(0);
                for (int j = 0; j < data.dim; j++) {
                    centers[i][j] = data.data[m][j];
                }
            }
            
        } else { // 选取前k个点位初始聚类中心
        	
            for (int i = 0; i < k; i++) {
                for (int j = 0; j < data.dim; j++) {
                    centers[i][j] = data.data[i][j];
                }
            }
        }

        // 第一轮迭代
        for (int i = 0; i < data.length; i++) {
            double maxDist = dist(data.data[i], centers[0], data.dim);
            int label = 0;
            for (int j = 1; j < k; j++) {
                double tempDist = dist(data.data[i], centers[j], data.dim);
                if (tempDist > maxDist) {
                    maxDist = tempDist;
                    label = j;//j指的是第几个聚类中心
                }
            }
            labels[i] = label;//第i个点属于第labels[i]个聚类中心
            centerCounts[label]++;//找到了第i个元素属于的聚类，（i属于labels),将聚类中的总个数加1
        }
        updateCenters(k, data);
        copyCenters(oldCenters, centers, k, data.dim);

        // 迭代预处理
        int maxAttempts = param.attempts > 0 ? param.attempts : Kmeans_param.MAX_ATTEMPTS;
        int attempts = 1;
        double criteria = param.criteria > 0 ? param.criteria : Kmeans_param.MIN_CRITERIA;
        double criteriaBreakCondition = 0;//yzy---迭代结束后所有聚类中心在最后一次迭代中中心移动的最大的那个中心移动的距离
        boolean[] flags = new boolean[k]; // 标记哪些中心被修改过

        // 迭代
        iterate: while (attempts < maxAttempts) { // 迭代次数不超过最大值，最大中心改变量不超过阈值
            for (int i = 0; i < k; i++) { // 初始化中心点“是否被修改过”标记
                flags[i] = false;
            }
            for (int i = 0; i < data.length; i++) { // 遍历data内所有点
                double maxDist = dist(data.data[i], centers[0], data.dim);
                int label = 0;
                for (int j = 1; j < k; j++) {
                    double tempDist = dist(data.data[i], centers[j], data.dim);
                    if (tempDist > maxDist) {
                        maxDist = tempDist;
                        label = j;
                    }
                }
                if (label != labels[i]) { // 如果当前点被聚类到新的类别则做更新
                    int oldLabel = labels[i];
                    labels[i] = label;
                    centerCounts[oldLabel]--;
                    centerCounts[label]++;
                    flags[oldLabel] = true;//yzy代表oldLabel聚类中的元素有变化，意味着聚类中心可能变化
                    flags[label] = true;
                }
            }
            updateCenters(k, data);
            attempts++;
//////////////////////////////////////////////////////////////
            // 计算被修改过的中心点最大修改量是否超过阈值
            //yzy--minDist(前后两个聚类中心越相似，余弦距离越大1，找最不相似的（余弦值小）)，
            //使其最不相似的前后两次得到的聚类中心的余弦距离大于某个阈值，从而使得所有的前后
            //两次迭代的聚类中心都非常相似
            double minDist = 1;
            
            for (int i = 0; i < k; i++) {
                if (flags[i]) {
                    double tempDist = dist(centers[i], oldCenters[i], data.dim);
                    if (minDist > tempDist) {
                        minDist = tempDist;
                    }
                    for (int j = 0; j < data.dim; j++) { // 更新oldCenter
                        oldCenters[i][j] = centers[i][j];
                    }
                }
            }
            //两次迭代的聚类中心余弦距离最小（相似度低）的都大于阈值，所有的前后两次迭代的聚类中心都大于阈值
            //若阈值设置的很大，那么说明迭代后与迭代前的聚类中心很相似，可以停止迭代
            if (minDist > criteria) {
                criteriaBreakCondition = minDist;
                break iterate;
            }
        }

        // 输出信息
        Kmeans_result rvInfo = new Kmeans_result();
        rvInfo.attempts = attempts;
        rvInfo.criteriaBreakCondition = criteriaBreakCondition;
        rvInfo.centerCounts=centerCounts;//yzy+
        rvInfo.labels=labels;//yzy+
        rvInfo.k=k;
        if (param.isDisplay) {
            System.out.println("k=" + k);
            System.out.println("attempts=" + attempts);
            System.out.println("criteriaBreakCondition=" + criteriaBreakCondition);
            System.out.println("The number of each classes are: ");
            for (int i = 0; i < k; i++) {
                System.out.print(centerCounts[i] + " ");
            }
            System.out.print("\n\n");
        }
        return rvInfo;
    }
}
