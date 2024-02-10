package org.Bookstore;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
class Category {
    private String name;

    public Category(String name) {
        this.name = name;
    }

}