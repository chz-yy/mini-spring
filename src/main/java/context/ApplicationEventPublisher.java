package context;

/**
 * 事件发布接口
 */
public interface ApplicationEventPublisher {
    /**
     * 事件发布
     * @param event
     */
    void publishEvent(ApplicationEvent event);
}
