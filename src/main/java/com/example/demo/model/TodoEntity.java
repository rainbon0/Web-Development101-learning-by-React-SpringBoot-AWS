package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder    //  생성자 매개변수 순서와 무관하게 오브젝트 생성 가능
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TodoEntity {
    private String id;
    private String title;
    private String userId;
    private boolean done; // true - todo를 완료한 경우(checked)
}
