package test;

//设计模式 ：保护性暂停
public class GuardedObject {
    private Object response;

    //获取结果
    //timeOut 表示要等多久
    public Object get(long timeout) {
        synchronized (this){
            //开始时间
            long begin = System.currentTimeMillis();
            //经历的时间
            long passedTime = 0;
            while (response == null){
                //这一轮循环应该等待的时间
                long waitTime = timeout - passedTime;
                if (waitTime <= 0){
                    break;
                }
                try {
                    this.wait(waitTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //求得经历时间
                passedTime = System.currentTimeMillis() - begin;
            }
            return response;
        }
    }

    //产生结果
    public void complete(Object response){
        synchronized (this){
            //给结果成员变量赋值
            this.response = response;
            this.notifyAll();
        }
    }
}
