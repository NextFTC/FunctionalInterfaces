# Functional Interfaces

A library that provides two functional interfaces: `Action` and `Configurator`.

Dokka: <https://javadoc.io/doc/dev.nextftc/functional-interfaces>

## Installation

You will most likely not need to install this library, unless you are a library developer. If you need to install it, do this:

```kotlin
implementation("dev.nextftc:functional-interfaces:1.0.1")

// or if it's in a library, you most likely want an api dependency instead. make sure you have the java-library plugin installed first!
api("dev.nextftc:functional-interfaces:1.0.1")
```

## `Action`

`Action` is defined as follows:

```java
@FunctionalInterface
public interface Action {
  void execute();
}
```

You should use `Action` instead of `Runnable`, as `Runnable` is meant for threading and using `Action` is more semantic.

## `Configurator`

`Configurator` is defined as follows:

```java
@FunctionalInterface
public interface Configurator<T> {
  void configure(T t);
}
```

However, this library also has the Kotlin SAM with receiver plugin, so Kotlin code can pass a receiver function instead! The reason it must be defined in Java instead of Kotlin is so that it has a return type of `void` instead of `Unit`.

## License

This project is licensed under the [GNU General Public License v3.0](https://www.gnu.org/licenses/gpl-3.0.html)

You are free to use, modify, and distribute this software under the terms of the GPLv3. Any derivative work must also be
distributed under the same license.

See the [LICENSE](LICENSE) for more information.
