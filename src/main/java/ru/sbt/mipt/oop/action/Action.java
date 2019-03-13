package ru.sbt.mipt.oop.action;

public interface Action<T> {
    void execute(T object);
}
