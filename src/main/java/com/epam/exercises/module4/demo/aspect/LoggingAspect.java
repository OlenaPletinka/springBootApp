package com.epam.exercises.module4.demo.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Aspect
@Component
public class LoggingAspect {
  private final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

  @Pointcut("within(com.epam.exercises.module4.demo.service.impl.GameProcessorImpl*)")
  public void applicationPointCut() {
  }

  @Around("applicationPointCut()")
  public void aroundExecution(ProceedingJoinPoint pjp) throws Throwable {
    long start = System.nanoTime();
    pjp.proceed();
    long end = System.nanoTime();
    logInfo(pjp, start, end);
  }

  private void logInfo(ProceedingJoinPoint pjp, long start, long end) {
    Object[] signatureArgs = pjp.getArgs();
    Signature signature = pjp.getSignature();
    String methodName = signature.getName();
    Class returnType = ((MethodSignature) signature).getReturnType();
    logger.info("*********************");
    logger.info("Execution of " + methodName + " took " +
              TimeUnit.NANOSECONDS.toMillis(end - start) + " ms");
    logger.info(" Arguments of the method " + methodName + " are:");
    if (signatureArgs.length != 0) {
      for (Object signatureArg : signatureArgs) {
        logger.info(signatureArg.toString());
      }
    } else {
      logger.info("There is no arguments.");
    }
    logger.info("Return value is " + returnType);
    logger.info("*********************");
  }
}
