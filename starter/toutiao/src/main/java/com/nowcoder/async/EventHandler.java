package com.nowcoder.async;
import java.util.List;
/**
 * Created by wuzhaojun on 2017/5/15.
 */
public interface EventHandler {
    void doHandle(EventModel model);
    List<EventType> getSupportEventTypes();
}
