package org.geek.dicontainer;

import java.util.HashMap;
import java.util.Map;

public class Context {

    private Map<Class<?>, Object> container = new HashMap<>();

    public <ComponentType> void bind(Class<ComponentType> type, ComponentType instance) {
        container.put(type, instance);
    }

    public <ComponentType> ComponentType get(Class<ComponentType> typeClass) {
        return (ComponentType) container.get(typeClass);
    }
}
