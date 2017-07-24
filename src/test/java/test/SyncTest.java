package test;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by 文江 on 2017/7/10.
 */
public class SyncTest {
    public static void main(String[] args) {

        /*SynchronousQueue<Pokers> synchronousQueue = new SynchronousQueue();
        new Thread(new Product(synchronousQueue)).start();
        new Thread(new Customer(synchronousQueue)).start();*/

            Depot depot = new Depot(150);
            Producer mPro = new Producer(depot);
            Customers mCus = new Customers(depot);
            mPro.produce(60);
            mPro.produce(20);
            mCus.consume(90);

            mCus.consume(150);
            mPro.produce(110);

        }

        public static class Product implements Runnable {
            SynchronousQueue<Pokers> synchronousQueue;

            public Product(SynchronousQueue<Pokers> synchronousQueue) {
                this.synchronousQueue = synchronousQueue;
            }

            @Override
            public void run() {
                Pokers poker = null;
                while (true) {
                    int random = new Random().nextInt(16);
                    System.out.println("生成中");
                    try {
                        poker = new Pokers();
                        poker.setNumber(random);
                        poker.setColor(random);
                        synchronousQueue.offer(poker, 1, TimeUnit.MINUTES);
                        System.out.println("等3秒");
                        //  TimeUnit.SECONDS.sleep(3);
                        TimeUnit.SECONDS.sleep(3);
                        System.out.println("發送完成    ");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        public static class Customer implements Runnable {
            SynchronousQueue<Pokers> synchronousQueue;

            public Customer(SynchronousQueue<Pokers> synchronousQueue) {
                this.synchronousQueue = synchronousQueue;
            }

            @Override
            public void run() {
                while (true) {
                    try {
                        TimeUnit.SECONDS.sleep(3);
                        Pokers take = synchronousQueue.poll(1, TimeUnit.SECONDS);
                        System.out.println("消費了" + take.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        //ArrayBlockingQueue是数组实现的线程安全的有界的阻塞队列。
        public static void arayblockingQueuetest() {
            ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(5);
            arrayBlockingQueue.add(new Object());
            ReentrantLock lock = new ReentrantLock(true);//公平锁


        }

        //deom reentrantLock   （经典生产者和消费者模型）
        public static class Depot {
            private Integer capactiy;//数量
            private Integer size;
            private Lock lock;
            private Condition fullcondition;
            private Condition emptycondition;

            //工程模型：为一个临界资源    工长大小为一个临界资源，多线程在操作这个资源的时候为了保证数据的正确性
            // 必须的加上锁机制，这就是典型的生产者和消费者模型
            public Depot(int capactiy) {
                this.capactiy = capactiy;//容量
                this.size = 0;
                this.lock = new ReentrantLock();
                this.emptycondition = this.lock.newCondition();
                this.emptycondition = this.lock.newCondition();
            }

            //
            public void produce(int val) {
                lock.lock();
                try {
                    int left = val;//要保存的数据
                    while (left > 0) {
                        while (size >= capactiy) {
                            fullcondition.await();
                            int inc = (size + left) > capactiy ? left - size : left;//实际容量
                            size += inc;
                            left -= inc;
                            System.out.printf("%s produce(%d) --> size=%d\n", Thread.currentThread().getName(), inc, size);
                            emptycondition.signal();
                        }
                    }
                } catch (Exception e) {
                } finally {
                    lock.unlock();
                }
            }

            public void consume(int val) {
                lock.lock();
                try {
                    int left = val;
                    while (left > 0) {
                        while (size <= 0) {
                            emptycondition.await();
                            int dec = size < left ? size : left;
                            size -= dec;
                            left -= dec;
                            System.out.printf("%s consume(%d) --> size=%d\n", Thread.currentThread().getName(), val, size);
                            fullcondition.signal();
                        }
                    }
                } catch (Exception e) {

                } finally {
                    lock.unlock();
                }
            }
        }

        //生产者
        public static class Producer {
            private Depot depot;

            public Producer(Depot depot) {
                this.depot = depot;
            }

            public void produce(final int val) {
                new Thread() {
                    @Override
                    public void run() {
                        depot.produce(val);
                    }
                }.start();
            }
        }

        //消费者模型
        public static class Customers {
            private Depot depot;

            public Customers(Depot depot) {
                this.depot = depot;
            }

            public void consume(final int val) {
                new Thread() {
                    @Override
                    public void run() {
                        depot.consume(val);
                    }
                }.start();
            }
        }
    }
