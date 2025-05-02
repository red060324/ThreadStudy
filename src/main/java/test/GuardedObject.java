package test;

//设计模式 ：保护性暂停
public class GuardedObject {
    private Object response;

    //获取结果
    public Object get() {
        synchronized (this){
            while (response == null){
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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
