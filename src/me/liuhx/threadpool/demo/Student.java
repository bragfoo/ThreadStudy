package me.liuhx.threadpool.demo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program thread
 * @description: 学生实体类
 * @author: liuhx
 * @create: 2018/11/28 10:20
 */
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class Student {
    private int num; //学号
    private int scoreOne;//第一科分数
    private int scoreTwo;//第二科分数
    private int timeOne;//第一科使用时间
    private int timeTwo;//第二科使用时间
}
