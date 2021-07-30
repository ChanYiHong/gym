package hcy.gym.converter;

import hcy.gym.domain.Category;

import javax.persistence.AttributeConverter;

public class CategoryConverter implements AttributeConverter<Category, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Category attribute) {
        if (attribute.equals(Category.QUESTION)) return 1;
        else if (attribute.equals(Category.FREE)) return 2;
        else if (attribute.equals(Category.HELLO)) return 3;
        else return null;
    }

    @Override
    public Category convertToEntityAttribute(Integer dbData) {
        if (dbData == 1) return Category.QUESTION;
        else if (dbData == 2) return Category.FREE;
        else if (dbData == 3) return Category.HELLO;
        else return null;
    }
}
