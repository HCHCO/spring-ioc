package factory.know;

import factory.know.Aware;

public interface BeanClassLoaderAware extends Aware {
    void setBeanClassLoader(ClassLoader classLoader);
}
