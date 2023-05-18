package utils.logging;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.restaurant.waiter.Spring.ApplicationContextProvider;
import net.logstash.logback.marker.ObjectAppendingMarker;
import utils.request.RequestBean;

import static utils.logging.CustomRequestLoggingFilter.NOT_DEF;
import static utils.logging.CustomRequestLoggingFilter.REQUEST_ID;

public class RequestIdMessageConverter extends ClassicConverter {

    ApplicationContextProvider appContext = new ApplicationContextProvider();
    
    @Override
    @AspectLogger
    public String convert(ILoggingEvent event) {
        try {
            RequestBean request = appContext.getApplicationContext().getBean("requestScopedBean", RequestBean.class);
            if (request != null) {
                return ("" + request.getRequestId());
            }
        } catch (Exception e) {
            if (event != null && event.getArgumentArray() != null) {
                ObjectAppendingMarker tmp;
                for (Object bean : event.getArgumentArray()) {
                    if (bean instanceof ObjectAppendingMarker) {
                        tmp = (ObjectAppendingMarker) bean;
                        if (REQUEST_ID.equals(tmp.getFieldName())) {
                            return "" + tmp.getFieldValue();
                        }
                    }
                }
            }
        }
        return NOT_DEF;
    }
}
