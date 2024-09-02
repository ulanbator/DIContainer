package org.geek.dicontainer;

import org.junit.Assert;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * 容器单元测试
 */
public class ContainerTest {

    public interface Component {
    }

    public static class ComponentWithDefault implements Component {
        public ComponentWithDefault() {
        }
    }

    /**
     * 组件构造
     */
    @Nested
    public class ComponentConstruction {

        /**
         * 无需构造的组件
         */
        @Test
        public void test_component_construction_without_dependency() throws NoSuchMethodException {
            Component component = new Component() {
            };
            Context context = new Context();
            context.bind(Component.class, component);
            Component contextComponent = context.get(Component.class);
            Assert.assertSame(component, contextComponent);
        }

        /**
         * 无依赖组件通过默认构造函数实现
         */
        @Test
        public void test_component_construction_with_default_construction_function() throws NoSuchMethodException {
            Context context = new Context();
            context.bind(Component.class, ComponentWithDefault.class);
            Component contextComponent = context.get(ComponentWithDefault.class);
            Assert.assertNotNull(contextComponent);
        }

        // 构造失败的组件-抽象类、接口
        // 构造函数注入
        @Nested
        public class ConstructorInjection {

            // TODO 有依赖组件，通过Inject注解构造函数实现
            // TODO 所依赖的组件中也存在依赖，需要先实现依赖
            // TODO 依赖的组件和当前组件存在循环依赖，抛出异常
            // TODO 多个Inject注解构造函数，抛出异常
            // TODO 存在的依赖组件不存在，抛出异常
        }

        @Nested
        public class FieldInjection {
            // 属性注入
            // TODO 单属性注入
            // TODO 多属性注入
            // TODO 属性不存在，抛出异常
            // TODO 属性之间存在循环依赖，抛出异常
            // TODO 依赖属性通过Inject注解实现
            // TODO 依赖属性和当前对象存在循环依赖，抛出异常
            // TODO 字段为final，抛出异常
        }

        public class FunctionInjection {
            // 方法注入
            //TODO Inject标注方法，其参数为依赖对象
            //TODO Inject标注的无参方法，会被调用
            //TODO Inject的组件不存在，抛出异常
            //TODO Inject的组件和当前组件存在循环依赖，抛出异常
            //TODO 子类重载父类的Inject注解方法
            //TODO 方法定义类型参数，抛出异常
        }

    }

    /**
     * 依赖选择
     */
    @Nested
    public class DependencySelectionTest {
        // provider类型依赖
        // TODO 属性依赖注入可声明为provider
        // TODO 方法依赖注入可声明为provider
        // TODO 方法依赖注入声明为provider，抛出异常
        // 自定义qualifier依赖
        // TODO 注册对象可指定qualifier
        // TODO 注册对象从类对象上提取qualifier
        // TODO 依赖注入指定qualifier，需要找到对应的注册对象
        // TODO 支持默认qualifier-Named
    }

    /**
     * 生命周期
     */
    @Nested
    public class LifecycleManagementTest {
        // singleton 生命周期
        // TODO 实例注册的时候指定scope生命周期
        // TODO 默认从类对象上提取singleton标注
        // TODO 指定为singleton对象后，确保容器范围内的唯一实例
        // TODO 容器组件默认不是single生命周期
        // 自定义scope标注
        // TODO 可向容器注册自定义scope标注的回调
    }
}
