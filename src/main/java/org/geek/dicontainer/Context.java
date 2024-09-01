package org.geek.dicontainer;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class Context {

    private Map<Class<?>, Object> container = new HashMap<>();

    private Map<Class<?>, Class<?>> implementationContainer = new HashMap<>();

    public <ComponentType> void bind(Class<ComponentType> type, ComponentType instance) {
        container.put(type, instance);
    }

    public <ComponentType, ComponentTypeImplementation extends ComponentType> void bind(Class<ComponentType> typeClass,
                                                                                        Class<ComponentTypeImplementation> implementationClass) throws NoSuchMethodException {
        implementationContainer.put(typeClass, implementationClass);
    }

    public <ComponentType, ComponentTypeImplementation extends ComponentType> ComponentType get(Class<ComponentType> componentTypeClass) throws NoSuchMethodException {
        if (null != container.get(componentTypeClass)) {
            return (ComponentType) container.get(componentTypeClass);
        }
        Constructor<?> constructor = implementationContainer.get(componentTypeClass).getConstructor();
        Object object = null;
        try {
            object = constructor.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return (ComponentType) object;
    }


}
