package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.context.annotation.Primary;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Builder    //  생성자 매개변수 순서와 무관하게 오브젝트 생성 가능
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name="Todo")
@Table(name="Todo")
public class TodoEntity {

    @Id
    @GenericGenerator(name="system-uuid", strategy = "uuid")    // UUID를 사용하는 system-uuid라는 generator를 생성하고
    @GeneratedValue(generator = "system-uuid")  //  system-uuid custom generator를 참조해 사용한다.
    private String id;

    private String title;
    private String userId;
    private boolean done; // true - todo를 완료한 경우(checked)
}
