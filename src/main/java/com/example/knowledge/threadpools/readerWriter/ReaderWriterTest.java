package com.example.knowledge.threadpools.readerWriter;

/**
 * @program: knowledge
 * @description:
 * @author: zhangjialin
 * @create: 2020-08-06 10:37
 */
public class ReaderWriterTest {
    public static void main(String[] args) {
        Pool pool = new Pool();

        for (int i = 0; i < 5 ; i++) {
            Reader reader = new Reader(""+i,pool);
            reader.start();
        }

        for (int i = 0; i < 5 ; i++) {
            Writer writer = new Writer(""+i,pool);
            writer.start();
        }
    }
}
    class Pool{

        private int readerNumber = 0;
        private int writerNumber = 0;
        private boolean waitingWrite;

        public int getReaderNumber() {
            return readerNumber;
        }

        public void setReaderNumber(int readerNumber) {
            this.readerNumber = readerNumber;
        }

        public int getWriterNumber() {
            return writerNumber;
        }

        public void setWriterNumber(int writerNumber) {
            this.writerNumber = writerNumber;
        }

        public boolean isWaitingWrite() {
            return waitingWrite;
        }

        public void setWaitingWrite(boolean waitingWrite) {
            this.waitingWrite = waitingWrite;
        }
    }

    class Reader extends Thread {
        private Pool pool;
        public Reader(String name,Pool pool){
            this.setName(name);
            this.pool = pool;
        }
        @Override
        public void run() {
            while (!this.isInterrupted()){
                synchronized (pool){
                    //如果写者的数量大于0  或者正在被写  则等待
                    while (pool.getWriterNumber()>0|| pool.isWaitingWrite()){
                        try{
                            wait();
                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }
                    }{
                        pool.setReaderNumber(pool.getReaderNumber()+1);
                    }
                }
            }
            System.out.println(this.getName()+"-正在读书-");
            try{
                Thread.sleep(2000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

            synchronized (pool){
                pool.setReaderNumber(pool.getReaderNumber()-1);
                System.out.println(this.getName()+"--读书完毕--");
                if(pool.getReaderNumber() ==0){
                    notifyAll();
                }
            }
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

        }
    }

    class Writer extends Thread {
        private Pool pool;
        public Writer(String name,Pool pool){
            this.setName(name);
            this.pool = pool;
        }
        @Override
        public void run() {
            while (!this.isInterrupted()){
                synchronized (pool){
                    //如果线程正在被读  或者写线程等待
                    while (pool.getWriterNumber()>0|| pool.getReaderNumber()>0){
                        try{
                            wait();
                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }
                    }{
                        pool.setWriterNumber(pool.getWriterNumber()+1);
                    }
                }
            }
            pool.setWaitingWrite(false);
            System.out.println(this.getName()+"---正在写书---");
            try{
                Thread.sleep(2000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

            synchronized (pool){
                pool.setWriterNumber(pool.getWriterNumber()-1);
                System.out.println(this.getName()+"----写书完毕----");
                notifyAll();
            }
        }
    }


