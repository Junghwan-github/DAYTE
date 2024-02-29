package com.example.dayte.notice.controller.advice;

import com.example.dayte.notice.dto.ResponseDTO;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Component
@Aspect // 해당 클래스가 관점(Aspect)임을 나타내는 역할 - 얘를 거쳐감
        // 횡단 관심사를 구현하는 모듈로, 여러 객체나 메서드에 걸쳐 발생하는 코드를 중앙에서 제어할 수 있도록 한다.
        // 횡단 관심사 : 핵심 관심(비즈니스 로직)에 영향을 주는 프로그램 영역을 뜻함. (시스템에서 사용하는 공통 영역)
public class ValidationCheckAdvice {
    // Around : advice의 종류
    // AOP에서 advice는 어떤 특정한 시점에 실행되어야 하는 코드를 뜻함
    @Around("execution(* com.example.dayte.notice.controller.*Controller.*(..))")
          // 해당 이름을 가진 것들은 아래의 메서드를 가짐
    public Object validationCheck(ProceedingJoinPoint jp) throws  Throwable{
        //ProceedingJoinPoint jp => 현재 실행 중인 조인 포인트(ex:메서드)에 대한 정보를 제공

        //args가 원본 메서드에서 전달된 인자(매개변수)들을 배열로 가져옴
        Object[] args = jp.getArgs();

        //만약 PostController의 insertPost()가 대상 메서드라면
        // getArgs -> PostDTO, BindingResult, HttpSession => Object[] args에 저장

        for(Object arg : args){
            if(arg instanceof BindingResult bindingResult){
                // arg가 BindingResult 타입으로 변환이 된다면, 매개변수로 BindingResult가 있으면
                if(bindingResult.hasErrors()){
                    // 유효성 검사 시 에러가 하나라도 있다면 에러 메시지를 Map(에러가 여러개일 수도 있으니)에 등록
                    Map<String, String> errMap = new HashMap<>();
                    for(FieldError err : bindingResult.getFieldErrors()){
                        errMap.put(err.getField(), err.getDefaultMessage());
                        //getField() : 유효성 검사에 실패한 필드 이름을 반환
                        //getDefaultMessage() : 해당 필드에 대한 기본 에러 메시지를 반환
                        // ex) username에 null값이 들어온 경우 -> errMap에 {username : "Username을 작성하세요"} 저장
                    }
                    return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), errMap);
                }
            }
        }
        return jp.proceed();  // 해당 advice를 사용하는 대상이 될 메서드를 실행하는 역할
        // 중간에 에러 뜨면 위에 있는 return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), errMap); 에 걸림
    }
}
