package jmx;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

public class BeanRegister {

    private MBeanServer mbs = null;

    public BeanRegister() {

        //  Get the platform MBeanServer
        mbs = ManagementFactory.getPlatformMBeanServer();

        //  Unique identification of MBeans

        Observer observer = new Observer();
        ObjectName beanName = null;

        try {
            //  Uniquely identify the MBeans and register them with the platform MBeanServer
            beanName = new ObjectName("BeanRegister:name=observe");
            mbs.registerMBean(observer, beanName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //  Utility method: so that the application continues to run
    private static void waitForEnterPressed() {
        try {
            System.out.println("Press <enter> to continue...");
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        BeanRegister beanRegister = new BeanRegister();
        System.out.println("Bean is working...");
        BeanRegister.waitForEnterPressed();
    }

}
