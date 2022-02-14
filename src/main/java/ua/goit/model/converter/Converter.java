package ua.goit.model.converter;

public interface Converter<T, E> {

    E from(T type);
    T to(E type);

}
