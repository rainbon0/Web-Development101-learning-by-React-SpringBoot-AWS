package com.example.demo.dto;

//  서비스가 Model을 그대로 반환하지 않고 DTO로 변환해서 반환하는 이유
//  1. 비즈니스 로직 캡슐화
//    Model은 DB table 구조와 매우 흡사하다. 대부분이 외부인이 자사의 DB Scheme를 아는 것을 원치 않으므로 DTO로 반환하여 내부 로직, DB 구조를 숨긴다
//  2. 클라이언트가 필요한 정보를 모델이 전부 포함하지 않는 경우 다수 발생
//    에러 메시지와 같은 서비스 로직과 연관된 정보를 담기 위해 DTO를 사용한다.


import com.example.demo.model.TodoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TodoDTO {
    private String id, title;
    private boolean done;
    
    public TodoDTO(final TodoEntity entity){
        this.id=entity.getId();
        this.title = entity.getTitle();
        this.done = entity.isDone();
    }

}





