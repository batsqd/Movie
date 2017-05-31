package com.kmeans;

/**
 * 
 * @author Yuanbo She
 * 
 */
public class Kmeans_result {
    public int attempts; // 退出迭代时的尝试次数
    public double criteriaBreakCondition; // 退出迭代时的最大距离（小于阈值）
    public int k; // 聚类数
    public int[] centerCounts;
    public int[] labels;//labels[i]代表data[i]这个元素所在的聚类在第labels[i]个聚类中
}
