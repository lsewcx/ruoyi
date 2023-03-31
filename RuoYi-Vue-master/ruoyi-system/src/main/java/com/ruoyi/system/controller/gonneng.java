package com.ruoyi.system.controller;

import com.hankcs.hanlp.HanLP;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class gonneng {
    /**
     * 使用余弦相似度算法计算文本相似性
     *
     * @param sentence1
     * @param sentence2
     * @return
     */
    public static String getSimilarity(String sentence1, String sentence2) {

        List<String> sent1Words = getSplitWords(sentence1);

        List<String> sent2Words = getSplitWords(sentence2);


        List<String> allWords = mergeList(sent1Words, sent2Words);


        int[] statistic1 = statistic(allWords, sent1Words);
        int[] statistic2 = statistic(allWords, sent2Words);

        // 向量A与向量B的点乘
        double dividend = 0;
        // 向量A所有维度值的平方相加
        double divisor1 = 0;
        // 向量B所有维度值的平方相加
        double divisor2 = 0;
        // 余弦相似度 算法
        for (int i = 0; i < statistic1.length; i++) {
            dividend += statistic1[i] * statistic2[i];
            divisor1 += Math.pow(statistic1[i], 2);
            divisor2 += Math.pow(statistic2[i], 2);
        }


        // 向量A与向量B的点乘 / （向量A所有维度值的平方相加后开方 * 向量B所有维度值的平方相加后开方）
        double A=dividend / (Math.sqrt(divisor1) * Math.sqrt(divisor2))*100;
        A= Math.round(A);
        String B= String.valueOf(A);
        B+="%";
        return B;
    }

    // 3. 计算词频
    private static int[] statistic(List<String> allWords, List<String> sentWords) {
        int[] result = new int[allWords.size()];
        for (int i = 0; i < allWords.size(); i++) {
            // 返回指定集合中指定对象出现的次数
            result[i] = Collections.frequency(sentWords, allWords.get(i));
        }
        return result;
    }

    // 2. 取并集
    private static List<String> mergeList(List<String> list1, List<String> list2) {
        List<String> result = new ArrayList<>();
        result.addAll(list1);
        result.addAll(list2);
        return result.stream().distinct().collect(Collectors.toList());
    }

    // 1. 分词
    private static List<String> getSplitWords(String sentence) {
        // 标点符号会被单独分为一个Term，去除之
        return HanLP.segment(sentence).stream().map(a -> a.word).filter(s -> !"`~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？ ".contains(s)).collect(Collectors.toList());
    }
}