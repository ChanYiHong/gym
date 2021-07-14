package hcy.gym.converter;

import hcy.gym.domain.PaymentWay;

import javax.persistence.AttributeConverter;

public class PaymentWayConverter implements AttributeConverter<PaymentWay, Integer> {

    @Override
    public Integer convertToDatabaseColumn(PaymentWay attribute) {
        if (attribute.equals(PaymentWay.CARD)) {
            return 1;
        } else if (attribute.equals(PaymentWay.CASH)) {
            return 2;
        } else {
            return null;
        }
    }

    @Override
    public PaymentWay convertToEntityAttribute(Integer dbData) {
        if (dbData == 1) {
            return PaymentWay.CARD;
        } else if (dbData == 2) {
            return PaymentWay.CASH;
        } else {
            return null;
        }
    }
}
