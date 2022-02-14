package ua.goit.model.converter;

public interface Converter<T, E> {

    E convert(T type);
}
