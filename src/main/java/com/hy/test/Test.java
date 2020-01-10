package com.hy.test;

import redis.clients.jedis.Jedis;

public class Test {
    public static void main(String[] args) throws Exception {
        /*InputStream stream = Resources.getResourceAsStream("application.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(stream);
        SqlSession session = sqlSessionFactory.openSession();
        CompanyMapper companyMapper = session.getMapper(CompanyMapper.class);
        EmpBean empBean=new EmpBean();
        empBean.setEmpname("周末");
        empBean.setSex(1);
        empBean.setDid(1);
        companyMapper.insert(empBean);*/

        //Redis
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.auth("root");
        //jedis.flushDB();清空数据



        String randomkey=jedis.randomKey();
        System.out.println(randomkey);
    }
}
