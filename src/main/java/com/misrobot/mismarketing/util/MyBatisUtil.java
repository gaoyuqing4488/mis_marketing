package com.misrobot.mismarketing.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by CHJ on 2017/8/10.
 */
public class MyBatisUtil {
    private static ThreadLocal<SqlSession> threadLocal = new ThreadLocal<SqlSession>();
    private static SqlSessionFactory sqlSessionFactory;

    static {
//        try {
//            Reader reader = Resources.getResourceAsReader("application-context-mybatis.xml");
//            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
    }

    /**
     * 禁止外界通过new方法创建
     */
    private MyBatisUtil() {
    }

    /**
     * 获取SqlSession
     */
    public static SqlSession getSqlSession() {
        SqlSession sqlSession = threadLocal.get();
        if (sqlSession == null) {
            sqlSession = sqlSessionFactory.openSession();
            threadLocal.set(sqlSession);
        }
        return sqlSession;
    }

    //关闭SqlSession
    public static void closeSqlSession() {
        SqlSession sqlSession = threadLocal.get();
        if (sqlSession != null) {
            sqlSession.close();
            threadLocal.remove();
        }
    }

    public static void main(String[] args) {
//        Connection conn = MyBatisUtil.getSqlSession().getConnection();
//        System.out.println(conn != null ? "连接成功" : "连接失败");
//        int count = 1000;
//        int index = 0;
//        List<Integer> list = new ArrayList<Integer>();
//        for (int i = 0; i < count; i++) {
//            list.add(i+1);
//        }
//        for (int i = 0; i < 20; i++) {
//            List<Integer> temp = list.subList(index, index+count/20);
//            index += count/20;
//            System.out.println("the data of list " + (i + 1) + "===========>");
//            for (int j = 0; j < temp.size(); j++) {
//                System.out.println(temp.get(j));
//            }
//        }
//        List<Integer> dataList = new ArrayList<Integer>();
//      for(int i=0;i<12888;i++)
//       dataList.add(i);
//
//   //分批处理
//        if(null!=dataList&&dataList.size()>0){
//        int pointsDataLimit = 1000;//限制条数
//        Integer size = dataList.size();
//        //判断是否有必要分批
//            if(pointsDataLimit<size){
//            int part = size/pointsDataLimit;//分批数
//            System.out.println("共有 ： "+size+"条，！"+" 分为 ："+part+"批");
//            for (int i = 0; i < part; i++) {
//            //1000条
//            List<Integer> listPage = dataList.subList(0, pointsDataLimit);
//            System.out.println(listPage);
//            //剔除
//            dataList.subList(0, pointsDataLimit).clear();
//        }
//
//            if(!dataList.isEmpty()){
//                System.out.println(dataList);//表示最后剩下的数据
//            }
//        }else{
//            System.out.println(dataList);
//        }
//    }else{
//    System.out.println("没有数据!!!");
//    }


        List<Integer> list=new ArrayList<Integer>();
        for(int i=0;i<121;i++)
            list.add(i);
        int count=list.size()/20;
        int pre=list.size()%20;
        int total=20;
        if(pre>0){
            total+=1;
        }
        for(int i=0;i<total;i++){
            List<Integer> subList=new ArrayList<>();
            if(pre>0){
                if(i==total-1){
//                    subList=list.subList(count*i,(count*i)+pre);
                    subList=list.subList(count*i,(count*i)+pre);
                    System.out.println(subList);
                }else {
                    subList= list.subList(i*count,count*(i+1));
                    System.out.println(subList);
                }
            }else {
                 subList= list.subList(i*count,count*(i+1));
                System.out.println(subList);
            }
        }

//        int per = 6;//批量插入每次最大数量
//        List<Integer> list=new ArrayList<Integer>();
//        for(int i=0;i<121;i++)
//            list.add(i);
//        int total = list.size();
//        if(total > per){
//            int count = total/per;
//            if(total%per >0){
//                count+=1;
//            }
//            for(int i=0;i<count;i++){
//                List<Integer> listanbak = new ArrayList<Integer>();
//                if((i+1)*per > total){
//                    listanbak = list.subList(i*per,total);
//                }else{
//                    listanbak = list.subList(i*per,(i+1)*per);
//                }
//               System.out.println(listanbak);
//            }
//        }else{
//            System.out.println(list);
//        }
    }
}
