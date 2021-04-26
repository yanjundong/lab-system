package com.coder.labsystem.aspectj;

import com.coder.labsystem.model.http.ErrorCode;
import com.coder.labsystem.model.http.ResponseBody;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

/**
 * @author : JQK
 * @date : 2021-04-26 15:57
 * @description :
 */
@Component
@Aspect
public class ControllerValidatorAspect implements Ordered {
    /**
     * 检查 Controller 方法的参数是否合法
     * 环绕通知：目标方法执行前后分别执行一些代码，发生异常的时候执行另外一些代码
     * execution 表达式分为5个部分：
     * 返回类型，* 表示所有的类型
     * 包名，表示需要拦截的包名
     * 类名，*号表示所有的类。
     * 方法名，*(..) 表示所有方法， .. 表示任何参数
     * @return
     * @throws Throwable
     */
    @Around(value = "execution(* com.coder.labsystem.core.*.controller.*Controller.*(..)) && args(.., bindingResult)")
    public Object around(ProceedingJoinPoint joinPoint, BindingResult bindingResult) throws Throwable {
        if (bindingResult != null && bindingResult.hasErrors()) {
            //检查是否存在校验错误
            if (bindingResult.hasErrors()) {
                //获取字段参数不合法的错误集合
                List<FieldError> errors = bindingResult.getFieldErrors();
                String errorInfo = errors.isEmpty() ? "" : errors.get(0).getDefaultMessage();
                //返回异常错误
                return ResponseBody.getInstance(ErrorCode.CLIENT_ERROR, "参数校验失败", errorInfo);
            }
        }
        //执行目标方法
        return joinPoint.proceed();
    }

    @Override
    public int getOrder() {
        return 10;
    }
}
