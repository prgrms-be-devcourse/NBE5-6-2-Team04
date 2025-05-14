package com.grepp.nbe562team04.app.controller.api.todo;

import com.grepp.nbe562team04.model.todo.dto.TodoRequestDto;
import com.grepp.nbe562team04.model.todo.dto.TodoResponseDto;
import com.grepp.nbe562team04.model.todo.repository.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/todos")
public class TodoApiController {

    private final TodoService todoService;

    //  투두 생성
    @PostMapping
    public ResponseEntity<String> createTodo(@RequestBody TodoRequestDto dto) {
        todoService.create(dto);
        return ResponseEntity.ok("투두 생성 완료!");
    }

    //  특정 목표에 속한 투두 목록 조회
    @GetMapping("/goal/{goalId}")
    public ResponseEntity<List<TodoResponseDto>> getTodosByGoal(@PathVariable Long goalId) {
        List<TodoResponseDto> todoList = todoService.getByGoal(goalId);
        return ResponseEntity.ok(todoList);
    }

    //  투두 수정
    @PutMapping("/{todoId}")
    public ResponseEntity<String> updateTodo(@PathVariable Long todoId, @RequestBody TodoRequestDto dto) {
        todoService.update(todoId, dto);
        return ResponseEntity.ok("투두 수정 완료!");
    }

    //  투두 삭제
    @DeleteMapping("/{todoId}")
    public ResponseEntity<String> deleteTodo(@PathVariable Long todoId) {
        todoService.delete(todoId);
        return ResponseEntity.ok("투두 삭제 완료!");
    }


}