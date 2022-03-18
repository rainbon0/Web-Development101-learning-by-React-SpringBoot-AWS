package com.example.demo.persistence;

import com.example.demo.model.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, String> {
                                                    // <테이블에 매핑될 엔티티 클래스, 엔티티의 primary key 타입(id)>


    // ?1은 매소드의 매개변수의 순서 위치
    @Query(value = "select t from Todo t where t.userId = ?1")
    List<TodoEntity> findByUserId(String userId);



}
