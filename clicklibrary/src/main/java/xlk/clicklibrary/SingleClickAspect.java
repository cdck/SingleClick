package xlk.clicklibrary;

import android.view.View;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * @author xlk
 * @date 2020/8/31
 */
@Aspect
public class SingleClickAspect {
    private static long mLastClickTime;

    private static final String POINTCUT_METHOD =
            "execution(* onClick(..))";
    private static final String POINTCUT_ANNOTATION =
            "execution(@xlk.clicklibrary.SingleClick * *(..))";
    private static final String POINTCUT_BUTTER_KNIFE =
            "execution(@butterknife.OnClick * *(..))";

    @Pointcut(POINTCUT_METHOD)
    public void methodPointcut() {

    }

    @Pointcut(POINTCUT_ANNOTATION)
    public void annotationPointcut() {

    }

    @Pointcut(POINTCUT_BUTTER_KNIFE)
    public void butterKnifePointcut() {

    }

    @Around("methodPointcut() || annotationPointcut() || butterKnifePointcut()")
    public void aroundJoinPoint(final ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            //检查方法是否有注解
            boolean hasAnnotationPresent = method != null && method.isAnnotationPresent(SingleClick.class);
            //计算点击间隔，没有注解默认500，有注解按注解参数来
            int interval = SingleClickManager.clickInterval;
            if (hasAnnotationPresent) {
                SingleClick annotation = method.getAnnotation(SingleClick.class);
                interval = annotation.value();
            } else if (!SingleClickManager.isGlobalClick) {
                //没有注解且不是全局配置的不进行拦截
                joinPoint.proceed();
                return;
            }
            //获取被点击的view对象
            Object[] args = joinPoint.getArgs();
            View view = findViewInMethodArgs(args);
            if (view != null) {
                int clickId = view.getId();
                if (hasAnnotationPresent) {
                    SingleClick annotation = method.getAnnotation(SingleClick.class);
                    //指明了哪些需要限制
                    int[] specify = annotation.specify();
                    for (int id : specify) {
                        if (id == clickId) {
                            if (canClick(interval)) {
                                mLastClickTime = System.currentTimeMillis();
                                joinPoint.proceed();
                            }
                            return;
                        }
                    }
                    //指明了哪些不需要限制
                    int[] except = annotation.except();
                    for (int id : except) {
                        if (id == clickId) {
                            joinPoint.proceed();
                            return;
                        }
                    }
                    //没有指定限制按钮
                    if (specify.length == 0) {
                        if (canClick(interval)) {
                            mLastClickTime = System.currentTimeMillis();
                            joinPoint.proceed();
                        }
                        return;
                    } else {//不在指定限制按钮集合中
                        joinPoint.proceed();
                        return;
                    }
                } else {
                    //全局限制了点击事件： SingleClickManager.isGlobalClick为true时
                    if (canClick(interval)) {
                        mLastClickTime = System.currentTimeMillis();
                        joinPoint.proceed();
                    }
                    return;
                }
            }
            //View为null就不拦截
            joinPoint.proceed();
        } catch (Exception e) {
            //出现异常不拦截点击事件
            joinPoint.proceed();
        }
    }

    private View findViewInMethodArgs(Object[] args) {
        if (args == null || args.length == 0) {
            return null;
        }
        for (Object arg : args) {
            if (arg instanceof View) {
                View view = (View) arg;
                if (view.getId() != View.NO_ID) {
                    return view;
                }
            }
        }
        return null;
    }

    private boolean canClick(int interval) {
        long l = System.currentTimeMillis() - mLastClickTime;
        if (l > interval) {
            mLastClickTime = System.currentTimeMillis();
            return true;
        }
        return false;
    }
}