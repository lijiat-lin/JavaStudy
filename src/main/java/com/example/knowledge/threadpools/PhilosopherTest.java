package com.example.knowledge.threadpools;

/**
 * @program: knowledge
 * @description: 哲学家进餐问题
 * @author: zhangjialin
 * @create: 2020-08-05 17:02
 */
class Philosopher extends Thread{

    private Fork fork;

    public Philosopher(String name,Fork fork){
        this.setName(name);
        this.fork = fork;
    }

    @Override
    public void run() {
        while (true){
            thinking();
            fork.takeFork();
            eating();
            fork.putFork();
        }
    }

    public void eating(){
        System.out.println("哲学家【"+this.getName()+"】吃饭中");
        try{
            //模拟吃饭
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void thinking(){
        System.out.println("哲学家【"+this.getName()+"】---思考中---");
        try{
            //模拟思考
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
class Fork{

    private boolean[] used = {false,false,false,false,false};

    /**
     * 只有当左右手的筷子都未被使用时，才允许获取筷子，且必须同时获取左右手筷子
     *
     */
    public synchronized void takeFork(){
        String name = Thread.currentThread().getName();
        int i = Integer.parseInt(name);
        while (used[i]||used[(i+1)%5]){
            try{
                //左右手只有一个的时候，等待
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        used[i] = true;
        used[(i+1)%5] = true;
    }

    /**
     * 必须同时释放左右手的筷子
     */
    public synchronized  void putFork(){
        String name = Thread.currentThread().getName();
        int i = Integer.parseInt(name);

        used[i] =false;
        used[(i+1)%5] = false;
        //唤醒其他线程
        notifyAll();
    }
}

/**
 * 哲学家进餐问题：五个哲学家围成一圈，五只叉子同样一圈在哲学家们的左右，哲学家只有拿起左右两个叉子才能进餐
 */
public class PhilosopherTest{
    public static void main(String[] args) {
        Fork fork = new Fork();
        new Philosopher("0",fork).start();
        new Philosopher("1",fork).start();
        new Philosopher("2",fork).start();
        new Philosopher("3",fork).start();
        new Philosopher("4",fork).start();
    }
}
