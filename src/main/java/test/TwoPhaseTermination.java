package test;

//设计模式--两阶段终止
//监控线程
public class TwoPhaseTermination {
    private Thread monitor;

    public void start(){
        monitor = new Thread(){
            @Override
            public void run() {
                while (true){
                    Thread current = Thread.currentThread();
                    if (current.isInterrupted()){
                        System.out.println("料理后事");
                        break;
                    }
                    try {
                        Thread.sleep(1000);
                        System.out.println("执行监控");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        current.interrupt();
                    }

                }
            }
        };

        monitor.start();

    }

    public void stop(){
        monitor.interrupt();
    }
}
