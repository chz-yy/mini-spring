package context.event;

import context.ApplicationContext;

public class ContextClosedEvent extends ApplicationContextEvent{
    public ContextClosedEvent(ApplicationContext source) {
        super(source);
    }
}
