package validator;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.hibernate.validator.HibernateValidator;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Leighton
 * @create 2018-12-13.
 */
public class ValidationTest {



    @Test
    public void test01(){
        PayIT t=new PayIT();
        try{
            validate(t);
            t.setPayTime("hehe");
        }catch (ConstraintViolationException e){
            List<String> list =extractPropertyAndMessageAsList(e.getConstraintViolations(),":");
            System.out.println(list);
            Map<String,String> map=extractPropertyAndMessage(e.getConstraintViolations());
            System.out.println(map);
        }

    }

    /**
     *  启用HibernateValidator的注解进行验证
     *  failFast为false返回所有的的错误信息
     */
   private  Validator validator= Validation.byProvider(HibernateValidator.class).configure().failFast(false).buildValidatorFactory().getValidator();

    public  <T> void validate(T obj) {
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(obj);
        // 抛出检验异常
        if (!constraintViolations.isEmpty()) {
           throw new ConstraintViolationException(constraintViolations);
        }
    }

    public  List<String> extractPropertyAndMessageAsList(Set<? extends ConstraintViolation> constraintViolations,
                                                               String separator) {
        List<String> errorMessages = Lists.newArrayList();
        constraintViolations.forEach(violation-> {
            errorMessages.add(violation.getPropertyPath() + separator + violation.getMessage());
        });

        return errorMessages;
    }

    public  Map<String,String> extractPropertyAndMessage(Set<? extends ConstraintViolation> constraintViolations){
        Map<String,String> errorMessages= Maps.newHashMap();
        for (ConstraintViolation violation:constraintViolations){
            errorMessages.put(violation.getPropertyPath().toString(),violation.getMessage());
        }

        return errorMessages;
    }
}
