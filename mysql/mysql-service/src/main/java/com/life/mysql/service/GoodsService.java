package com.life.mysql.service;

import com.life.mysql.dao.GoodsDao;
import com.life.mysql.entity.Goods;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * (Goods)表服务实现类
 * 设计并测试 数据库的 悲观锁、乐观锁
 * @author tengyun
 * @since 2021-02-22 11:29:58
 */
@Service
public class GoodsService {

    @Resource
    private GoodsDao goodsDao;

    @Resource
    private PlatformTransactionManager platformTransactionManager;

    /**
     * 使用悲观锁解决
     * 读的时候就加上排它锁
     * @author tengyun
     * @date 14:38 2021/2/22
     **/
    public void test() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 10, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        Runnable runnable = () -> {
            // 注意，使用 select for update 加排它锁的时候，必须是在事务内才行
            // 由于线程池里的线程spring是无法管理的，所以事务是无法传递进来的
            // 所以，在这个线程池里面，我加上了显示的事务管理
            DefaultTransactionDefinition def = new DefaultTransactionDefinition();
            def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
            TransactionStatus status = platformTransactionManager.getTransaction(def);
            try {
                // 加了排它锁。事务结束才会释放，不允许其他线程读写
                Goods goods = goodsDao.queryById((long) 1);
                Integer stock = goods.getStock();
                System.out.println("++++++++++" + stock);
                if (stock > 0) {
                    goods.setStock(stock - 10);
                    goodsDao.update(goods);
                }
                // 提交事务
                platformTransactionManager.commit(status);
            } catch (Exception e) {
                // 回滚事务
                platformTransactionManager.rollback(status);
            }
        };
        for (int i = 0; i < 10; i++) {
            threadPoolExecutor.execute(runnable);
        }
    }

    /**
     * 乐观锁
     * 设计一个version字段，先读出stock和version，version相同才更新。cas操作
     * @author tengyun
     * @date 14:57 2021/2/22
     **/
    public void optimistic() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 10, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        Runnable runnable = () -> {
            // 这里读出来的都是一样的，因为只加了共享锁
            Goods goods = goodsDao.queryByIdOptimistic(1);
            Integer stock = goods.getStock();
            while (goods.getStock() > 0) {
                System.out.println(Thread.currentThread().getName() + "++++" + goods.getStock() + "  " + goods.getVersion());
                goods.setStock(stock - 10);
                int i = goodsDao.updateOptimistic(goods);
                if (i == 1) {
                    System.out.println("购买成功");
                    break;
                }
                goods = goodsDao.queryByIdOptimistic(1);
            }
            if (goods.getStock() == 0) {
                System.out.println("购买失败");
            }
            System.out.println();
        };
        for (int i = 0; i < 6; i++) {
            threadPoolExecutor.execute(runnable);
        }
    }

    public void test3() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 10, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        Runnable runnable = (() -> {
            int update = goodsDao.inventoryReduction();
            if(update == 1) {
                System.out.println("购买成功");
            } else {
                System.out.println("购买失败");
            }
        });
        for (int i = 0; i < 100; i++) {
            threadPoolExecutor.execute(runnable);
        }
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    public Goods queryById(Long id) {
        return this.goodsDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    public List<Goods> queryAllByLimit(int offset, int limit) {
        return this.goodsDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param goods 实例对象
     * @return 实例对象
     */
    public Goods insert(Goods goods) {
        this.goodsDao.insert(goods);
        return goods;
    }

    /**
     * 修改数据
     *
     * @param goods 实例对象
     * @return 实例对象
     */
    public Goods update(Goods goods) {
        this.goodsDao.update(goods);
        return this.queryById(goods.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    public int deleteById(Long id) {
        return this.goodsDao.deleteById(id);
    }

}