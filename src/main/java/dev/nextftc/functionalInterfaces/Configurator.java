package dev.nextftc.functionalInterfaces;

@FunctionalInterface
@ConfiguratorMarker
public interface Configurator<T> {
    void configure(T t);
}
