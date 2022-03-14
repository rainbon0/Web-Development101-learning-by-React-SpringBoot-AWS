package com.example.demo.service;

import com.example.demo.model.TodoEntity;
import com.example.demo.persistence.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TodoService {

    @Autowired
    private TodoRepository repository;
    // ? - TodoRepository는 인터페이스인데 이를 구현하는 클래스 없이 메소드가 어떻게 동작하는가 -> spring의 MethodInterceptor AOP interface 이용

    
    public String testService(){
        // TodoEntity create
        TodoEntity entity = TodoEntity.builder().title("My first todo item").build();
        // TodoEntity save
        repository.save(entity);
        // TodoEntity search
        TodoEntity savedEntity = repository.findById(entity.getId()).get();

        return savedEntity.getTitle();
    }

    public List<TodoEntity> create(final TodoEntity entity){
        // Validation
        validate(entity);
        
        repository.save(entity);

        log.info("Entity ID : {} is saved", entity.getId());

        return repository.findByUserId(entity.getUserId());
    }

    public List<TodoEntity> retrieve(final String userId){
        return repository.findByUserId(userId);
    }

    public List<TodoEntity> update(final TodoEntity entity){
        // update할 entity가 유효한지 검증
        validate(entity);

        // id를 통해 original entity를 가져온다
        final Optional<TodoEntity> original = repository.findById(entity.getId());

        original.ifPresent(todo -> {
            // 반환된 TodoEntity가 존재하면 값을 새 entity 값으로 덮어씌운다
            todo.setTitle(entity.getTitle());
            todo.setDone(entity.isDone());

            repository.save(todo);

        });
        // update 후에는 해당 사용지의 모든 todo 리스트를 리턴한다
        return retrieve(entity.getUserId());
    }

    public List<TodoEntity> delete(final TodoEntity entity){
        validate(entity);

        try{
            repository.delete(entity);

        }catch (Exception e){
            log.error("error deleting entity", entity.getId(), e);
            throw new RuntimeException("error deleting entity" + entity.getId());
        }
        return retrieve(entity.getUserId());
    }

    // Validation
    private void validate(final TodoEntity entity){
        if(entity==null){
            log.warn("Entity cannot be null");
            throw new RuntimeException("Entity cannot be null");
        }

        if(entity.getUserId()==null){
            log.warn("Unknown error");
            throw new RuntimeException("Unknown user.");
        }
    }
}
