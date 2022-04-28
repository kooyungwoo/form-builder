package com.formbuilder.cmmn;

import java.util.HashMap;

import javax.persistence.PersistenceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@ControllerAdvice 은 @InitBinder, @ModelAttribute, @ExceptionHandler 관련 어노테이션을 여러 컨트롤러에 걸쳐 공통으로 설정 할 수 있게 해줍니다.
//@ControllerAdvice(assignableTypes={TestController.class, Test2Controller.class}) 옵션을 이용해서 영향이 미치는 범위를 지정 할 수 있습니다.(class, 패키지 등)
//assignableTypes을 많이 추가 하는경우 성능에 안좋은 영향을 미칠 수 있습니다.(https://docs.spring.io/spring-framework/docs/5.3.18/javadoc-api/org/springframework/web/bind/annotation/ControllerAdvice.html)
//@ControllerAdvice+@ResponseBody 를 한꺼번에 사용할 수 있게 해주는(에러 처리 후 json으로 데이터를 응답 할때 사용하기 좋음) 어노테이션
@RestControllerAdvice
public class ControllerExceptionHandler {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@ResponseStatus(value = HttpStatus.OK)//응답 코드를 정의 합니다. OK=200
	@ExceptionHandler(value = NullPointerException.class)//처리할 Exception을 정의 합니다.
    public HashMap<String, Object> nullPointerException(Exception e, Model model) {
		
		logger.error(e.getLocalizedMessage());
		
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("result", false);
		returnMap.put("message", "비정상적인 정보(null) 입니다.");
		
        return returnMap;
    }
    
    @ResponseStatus(value = HttpStatus.OK)
	@ExceptionHandler(value = ClassCastException.class)
    public HashMap<String, Object> classCastException(Exception e, Model model) {
		
		logger.error(e.getLocalizedMessage());
		
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("result", false);
		returnMap.put("message", "프로그램이 비정상 구동 되었습니다. 시스템 확인이 필요 합니다.(classCastException)");
		
        return returnMap;
    }
    
    @ResponseStatus(value = HttpStatus.OK)
   	@ExceptionHandler(value = NoClassDefFoundError.class)
       public HashMap<String, Object> noClassDefFoundError(Exception e, Model model) {
   		
   		logger.error(e.getLocalizedMessage());
   		
   		HashMap<String, Object> returnMap = new HashMap<String, Object>();
   		returnMap.put("result", false);
   		returnMap.put("message", "프로그램이 비정상 구동 되었습니다. 시스템 확인이 필요 합니다.(noClassDefFoundError)");
   		
           return returnMap;
       }
	
	@ResponseStatus(value = HttpStatus.OK)
	@ExceptionHandler(value = ArrayIndexOutOfBoundsException.class)
    public HashMap<String, Object> arrayIndexOutOfBoundsException(Exception e, Model model) {
		
		logger.error(e.getLocalizedMessage());
		
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("result", false);
		returnMap.put("message", "비정상적인 배열 정보 입니다.(ArrayIndexOutOfBoundsException)");
		
        return returnMap;
    }
	
	@ResponseStatus(value = HttpStatus.OK)
	@ExceptionHandler(value = IndexOutOfBoundsException.class)
    public HashMap<String, Object> indexOutOfBoundsException(Exception e, Model model) {
		
		logger.error(e.getLocalizedMessage());
		
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("result", false);
		returnMap.put("message", "비정상적인 배열 정보를 확인 하고 있습니다.(IndexOutOfBoundsException)");
		
        return returnMap;
    }
	
	@ResponseStatus(value = HttpStatus.OK)
	@ExceptionHandler(value = ClassNotFoundException.class)
    public HashMap<String, Object> classNotFoundException(Exception e, Model model) {
		
		logger.error(e.getLocalizedMessage());
		
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("result", false);
		returnMap.put("message", "프로그램이 비정상 구동 되었습니다. 시스템 확인이 필요 합니다.(ClassNotFoundException)");
		
        return returnMap;
    }
	
	//모든 data-jpa 관련 exception은 PersistenceException 을 상속받는다고 알고 있어 해당 exception을 기준으로 발생되는 예외는
	//데이터 관련 작업으로 처리
	@ResponseStatus(value = HttpStatus.OK)
	@ExceptionHandler(value = PersistenceException.class)
    public HashMap<String, Object> persistenceException(Exception e, Model model) {
		
		logger.error(e.getLocalizedMessage());
		
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("result", false);
		returnMap.put("message", "데이터 작업 중 문제가 발생 되었습니다.");
		
        return returnMap;
    }
	
}
