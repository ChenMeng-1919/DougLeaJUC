package lesson20;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/*
 * @author: cm
 * @date: Created in 2021/11/4 15:40
 * @description:
 */
@Slf4j
public class Demo2 {
    static class GoodsModel {
        //商品名称
        String name;
        //购物开始时间
        long startime;
        //送到的时间
        long endtime;

        public GoodsModel(String name, long startime, long endtime) {
            this.name = name;
            this.startime = startime;
            this.endtime = endtime;
        }

        @Override
        public String toString() {
            return name + "，下单时间[" + this.startime + "," + endtime + "]，耗时:" + (this.endtime - this.startime);
        }
    }

    /**
     * 将商品搬上楼
     *
     * @param goodsModel
     * @throws InterruptedException
     */
    static void moveUp(GoodsModel goodsModel) throws InterruptedException {
        //休眠5秒，模拟搬上楼耗时
        TimeUnit.SECONDS.sleep(5);
        log.info("将商品搬上楼，商品信息:" + goodsModel);
    }

    /**
     * 模拟下单
     *
     * @param name     商品名称
     * @param costTime 耗时
     * @return
     */
    static Callable<GoodsModel> buyGoods(String name, long costTime) {
        return () -> {
            long startTime = System.currentTimeMillis();
            log.info(startTime + "购买" + name + "下单!");
            //模拟送货耗时
            try {
                TimeUnit.SECONDS.sleep(costTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            long endTime = System.currentTimeMillis();
            log.info(endTime + name + "送到了!");
            return new GoodsModel(name, startTime, endTime);
        };
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        long st = System.currentTimeMillis();
        log.info(st + "开始购物!");
        //创建一个线程池，用来异步下单
        ExecutorService executor = Executors.newFixedThreadPool(5);
        //异步下单购买冰箱
        Future<GoodsModel> bxFuture = executor.submit(buyGoods("冰箱", 5));
        //异步下单购买洗衣机
        Future<GoodsModel> xyjFuture = executor.submit(buyGoods("洗衣机", 2));
        //关闭线程池
        executor.shutdown();
        //等待洗衣机送到
        GoodsModel xyjGooldModel = xyjFuture.get();
        //将洗衣机搬上楼
        moveUp(xyjGooldModel);
        //等待冰箱送到
        GoodsModel bxGoodModel = bxFuture.get();
        //将冰箱搬上楼
        moveUp(bxGoodModel);
        long et = System.currentTimeMillis();
        log.info(et + "货物已送到家里咯，哈哈哈！");
        log.info("总耗时:" + (et - st));
    }
}
