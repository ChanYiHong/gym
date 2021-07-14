package hcy.gym.converter;

import hcy.gym.domain.PaymentPlan;

import javax.persistence.AttributeConverter;

public class PaymentPlanConverter implements AttributeConverter<PaymentPlan, Integer> {

    @Override
    public Integer convertToDatabaseColumn(PaymentPlan attribute) {
        if (attribute.equals(PaymentPlan.TEMPORARY)) {
            return 1;
        } else if (attribute.equals(PaymentPlan.TWO_M)) {
            return 2;
        } else if (attribute.equals(PaymentPlan.THREE_M)) {
            return 3;
        } else {
            return null;
        }
    }

    @Override
    public PaymentPlan convertToEntityAttribute(Integer dbData) {
        if (dbData == 1) {
            return PaymentPlan.TEMPORARY;
        } else if (dbData == 2) {
            return PaymentPlan.TWO_M;
        } else if (dbData == 3) {
            return PaymentPlan.THREE_M;
        } else {
            return null;
        }
    }
}
