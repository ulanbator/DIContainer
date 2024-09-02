package org.geek.dicontainer;

import jakarta.inject.Provider;

import java.util.HashMap;
import java.util.Map;

public class Context {

    private Map<Class<?>, Provider<?>> providerMap = new HashMap<>();

    public <ComponentType> void bind(Class<ComponentType> type, ComponentType instance) {
        providerMap.put(type, (Provider<ComponentType>) () -> instance);
    }

    public <ComponentType, ComponentTypeImplementation extends ComponentType> void bind(Class<ComponentType> typeClass,
                                                                                        Class<ComponentTypeImplementation> implementationClass) {
        providerMap.put(implementationClass, (Provider<ComponentType>) () -> {
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
