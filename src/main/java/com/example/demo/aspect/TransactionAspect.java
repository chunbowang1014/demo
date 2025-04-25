package com.example.demo.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

@Aspect
@Component
public class TransactionAspect {

    @Autowired
    private PlatformTransactionManager transactionManager;

    private static final Logger logger = LoggerFactory.getLogger(TransactionAspect.class);

    @Around("@annotation(org.springframework.transaction.annotation.Transactional)")
    public Object manageTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        try {
            Object result = joinPoint.proceed();
            long duration = System.currentTimeMillis() - startTime;

            logger.info("Transaction completed in {} ms for method: {}",
                    duration, joinPoint.getSignature().getName());

            return result;
        } catch (Exception ex) {
            long duration = System.currentTimeMillis() - startTime;

            logger.error("Transaction failed after {} ms for method: {} with exception: {}",
                    duration, joinPoint.getSignature().getName(), ex.getMessage());

            throw ex;
        }

    }
}