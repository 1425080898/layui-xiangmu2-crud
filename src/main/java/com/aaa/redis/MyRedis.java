package com.aaa.redis;


/**
 * @author ：xiaoliu
 * @date ：Created in 2020/6/29 17:09
 * @description：操作redis
 * @modified By：
 * @version: 1.0
 */
public class MyRedis {
    public static void main(String[] args) {
//        String status = null;
//        // 初始化jedis连接
//        Jedis jedis = new Jedis("192.168.88.100",6379);
//        // 测试redis服务的连通性
//        String pong = jedis.ping();
//        // pong 是成功
//        System.out.println(pong);

//        String b = jedis.get("b");
//        System.out.println(b);
        // 给b赋值
//        jedis.set("b", "aaa");
        // 查询hash
//        List<String> hmget = jedis.hmget("qy111", "student","teacher","id");
//        for (String qyInfo : hmget) {
//            System.out.println(qyInfo);
//        }
//        Map map = new HashMap<>();
//        map.put("name", "xiaoliu");
//        map.put("age", "21");
//        jedis.hmset("myInfo", map);

        // 查询list
//        List<String> mylist = jedis.lrange("mylist", 0, -1);
//        for (String s : mylist) {
//            System.out.println(s);
//        }
        // 右插入
//        jedis.rpush("myList03", "xiaoliu");
//        jedis.rpush("myList03", "xiaozhang");
        // 左插入
//        jedis.lpush("myList02", "xiaoliu");
//        jedis.lpush("myList02", "xiaozhang");

//        Map map = new HashMap();



//        status = jedis.set("qq", "马化腾");
//        System.out.println("状态"+"-----"+status);
//        String qq = jedis.get("qq");
//        System.out.println(qq);
        // 添加hash
//        Map map = new HashMap();
//        map.put("name", "小寇");
//        map.put("sex","男");
//        jedis.hmset("xiaoKouInfo", map);
//        List<String> xiaoKouInfo = jedis.hmget("xiaoKouInfo", "name", "sex");
//        for (String info : xiaoKouInfo) {
//            System.out.println(info);
//        }
        // 添加list
//        jedis.rpush("myList01", "111", "222");
//        jedis.lpush("myList01", "333","444");
//        List<String> myList01 = jedis.lrange("myList01", 0, -1);
//        for (String s : myList01) {
//            System.out.println(s);
//        }
        // 添加set
//        Long mySet01 = jedis.sadd("mySet01", "111", "222", "111");
//        System.out.println(mySet01);
//        Set<String> mySet011 = jedis.smembers("mySet01");
//        for (String s : mySet011) {
//            System.out.println(s);
//        }
        // 添加zset
//        jedis.zadd("myZadd01",100,"a");
//        jedis.zadd("myZadd01",50 ,"b");
//        jedis.zadd("myZadd01",80 ,"c");
//
//        Set<String> myZadd01 = jedis.zrange("myZadd01", 0, -1);
//        for (String s : myZadd01) {
//            System.out.println(s);
//        }
//        Map map = new HashMap();
//        map.put("a", 100.0);
//        map.put("b", 50.0);
//        map.put("c", 80.0);
//        jedis.zadd("myZadd02", map);
//        Set<String> myZadd02 = jedis.zrange("myZadd02", 0, -1);
//        for (String s : myZadd02) {
//            System.out.println(s);
//        }
    }
}
