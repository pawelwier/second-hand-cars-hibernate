package pl.akademiakodu.commons;

import org.springframework.stereotype.Component;

@Component
public interface Mapper <F, T> {

    T map(F from);
    F reverse(T to);

}
