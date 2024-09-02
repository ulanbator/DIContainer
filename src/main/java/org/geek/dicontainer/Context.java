package org.geek.dicontainer;

import jakarta.inject.Provider;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class Context {

    private Map<Class<?>, Supplier<?>> providerMap = new HashMap<>();

    public <ComponentType> void bind(Class<ComponentType> type, ComponentType instance) {
        providerMap.put(type, (Supplier<ComponentType>) () -> instance);
    }

    public <ComponentType, ComponentTypeImplementation extends ComponentType> void bind(Class<ComponentType> typeClass,
                                                                                        Class<ComponentTypeImplementation> implementationClass) {
        providerMap.put(implementationClass, (Supplier<ComponentType>) () -> {
            try {
                return (ComponentType) implementationClass.getConstructor().newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    public <ComponentType> ComponentType get(Class<ComponentType> componentTypeClass) {
        return (ComponentType) providerMap.get(componentTypeClass).get();
    }
}
