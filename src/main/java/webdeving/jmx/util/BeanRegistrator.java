package webdeving.jmx.util;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.management.*;
import java.lang.management.ManagementFactory;

@ManagedBean(name = "beanRegistrator")
@ApplicationScoped
public class BeanRegistrator {

    public BeanRegistrator() {}

    public void register(String nameOfObject, Object object) {
        try {
            ObjectName beanName = new ObjectName(nameOfObject);
            ManagementFactory.getPlatformMBeanServer().registerMBean(object, beanName);
        } catch (NotCompliantMBeanException | InstanceAlreadyExistsException | MBeanRegistrationException |
                 MalformedObjectNameException e) {
            throw new RuntimeException(e);
        }
    }

    public void unregister(String nameOfObject) {
        try {
            ObjectName beanName = new ObjectName(nameOfObject);
            ManagementFactory.getPlatformMBeanServer().unregisterMBean(beanName);
        } catch (MBeanRegistrationException | MalformedObjectNameException | InstanceNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
