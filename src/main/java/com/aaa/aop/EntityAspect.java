package com.aaa.aop;

import com.aaa.entity.User;
import com.aaa.util.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author ：xiaoliu
 * @date ：Created in 2020/6/23 15:16
 * @description：补充修改添加信息
 * @modified By：
 * @version:
 */

@Aspect
@Component
public class EntityAspect {

    @Pointcut("@annotation(com.aaa.aop.SaveOrUpdateEntityAnn)")
    public void pointCutSaveOrUpdate(){}

    @Around("pointCutSaveOrUpdate()")
    public Object aroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        //首先获取所有参数
        Object[] args = joinPoint.getArgs();
        //获取方法签名，方法名
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        String methodName = method.getName();

        System.out.println(methodName);


        //获取注解，注解的值，拿到方法上的注解 getAnnotation() 参数要获取那个注解就是那个注解的.class
        SaveOrUpdateEntityAnn annotation = method.getAnnotation(SaveOrUpdateEntityAnn.class);
        //拿到注解的值
        Class<?> entityClass = annotation.entityClass();
        //遍历所有参数，然后复制
        for (int i = 0;i<args.length;i++){
            //原始对象
            Object fromObject = args[i];
            //通过jackson工具转换对象,创建一个objectMapper对象
            ObjectMapper objectMapper = new ObjectMapper();
            //将原始对象，通过jakson工具转换成具体的实体对象
            Object toObject = objectMapper.convertValue(fromObject, entityClass);
            //获取当前用户名
            User user = (User) SecurityUtils.getSubject().getPrincipal();
            //当前用户名
            String loginName = user.getLoginName();
            //判断当前方法是否含有常量值中的save 或者 update
            if (methodName.contains(Constants.SAVE_OPERATION)){
                //如果包含,用反射的方式赋值
//                toObject
                Method setCreateTime = entityClass.getMethod("setCreateTime", Date.class);
                setCreateTime.invoke(toObject, new Date());
                Method setCreateBy = entityClass.getMethod("setCreateBy", String.class);
                setCreateBy.invoke(toObject, loginName);
            }
            if (methodName.contains(Constants.UPDATE_OPERATION)){
                Method setUpdateTime = entityClass.getMethod("setUpdateTime", Date.class);
                setUpdateTime.invoke(toObject, new Date());
                Method setUpdateBy = entityClass.getMethod("setUpdateBy", String.class);
                setUpdateBy.invoke(toObject, loginName);
            }
            //将修改过后的参数重新设置回去
            args[i]=toObject;
        }
        //继续执行
        Object proceed = joinPoint.proceed(args);
        return proceed;
    }
}
