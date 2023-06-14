package com.dbwp031.dylc.controller;

import com.dbwp031.dylc.auth.domain.MemberPrincipal;
import com.dbwp031.dylc.domain.Member;
import com.dbwp031.dylc.domain.Project;
import com.dbwp031.dylc.domain.Todo;
import com.dbwp031.dylc.domain.TodoPostDto;
import com.dbwp031.dylc.repository.MemberRepository;
import com.dbwp031.dylc.repository.ProjectRepository;
import com.dbwp031.dylc.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
@RequestMapping("/todo")
public class TodoController {

    private final MemberRepository memberRepository;
    private final TodoRepository todoRepository;
    private final ProjectRepository projectRepository;

    @GetMapping("")
    public String todoList(Model model, Principal principal) {
        String memberName = principal.getName();
//        System.out.println(memberPrincipal.toString());
//        authentication.
//        Optional<Member> member = memberRepository.findMemberByLoginId(memberPrincipal.getAttribute("login"));
        Optional<Member> member = memberRepository.findMemberByLoginId(memberName);
        if (member.isPresent()) {
            List<Todo> todosRaw = member.get().getTodos();
            List<TodoPostDto> todos = todosRaw.stream().map(todo -> new TodoPostDto(todo.getId(), todo.getContent(), todo.isDone())).toList();
            model.addAttribute("todos", todos);
        }

//        if (member.isPresent()) {
//            List<Todo> todos =  member.get().getTodos();
//            if (todos.size() != 0) {
//                model.addAttribute("todos", todos);
//            } else {
//                model.addAttribute("todos", null);
//            }
//        } else {
//            System.out.println("멤버 없음");
//        }
        return "template/todo/main";
    }


    @GetMapping("/create")
    public String visitTodoCreate(Model model, Principal principal) {
        String memberName = principal.getName();
        Optional<Member> member = memberRepository.findMemberByLoginId(memberName);
        List<Project> containedProjects = member.get().getProjects();
        model.addAttribute("containedProjects", containedProjects);

        return "template/todo/create";
    }

    @PostMapping("/create")
    public String createTodo(@RequestParam String todoContext, @RequestParam Long selectedProjectId, Principal principal) {
        String memberName = principal.getName();
        Optional<Member> member = memberRepository.findMemberByLoginId(memberName);
        Project project = projectRepository.findById(selectedProjectId).get();
        if (member.isPresent()) {
            Todo todo = Todo.builder()
                    .content(todoContext)
                    .member(member.get())
                    .done(false)
                    .project(project)
                    .build();
            todoRepository.save(todo);
        } else {
            System.out.println("멤버: null");
        }

        return "redirect:/todo";
    }

    @GetMapping("/{id}/detail")
    public String detail(@PathVariable Long id, Model model) {
        Optional<Todo> todo = todoRepository.findTodoById(id);

        if (todo.isPresent()) {
            model.addAttribute("content", todo.get().getContent());
            Project project = todo.get().getProject();
            if (project != null) {
                model.addAttribute("project", project.getTitle());
            } else {
                model.addAttribute("project", null);
            }
        }
        return "template/todo/detail";
    }

    @GetMapping("/{id}/update")
    public String update(@PathVariable Long id, Model model, Principal principal) {
        String memberName = principal.getName();
        Optional<Member> member = memberRepository.findMemberByLoginId(memberName);
        Optional<Todo> todo = todoRepository.findTodoById(id);
        List<Project> containedProjects = member.get().getProjects();
        if (todo.isPresent()) {
            model.addAttribute("content", todo.get().getContent());
            Project project = todo.get().getProject();
            model.addAttribute("project", todo.get().getProject());
            model.addAttribute("containedProjects", containedProjects);
        }
        return "template/todo/update";
    }
    @PostMapping("/{id}/update")
    public String update(@PathVariable Long id, @RequestParam String content, @RequestParam Long selectedProjectId  ) {
        Optional<Todo> todoOptional = todoRepository.findById(id);
        Optional<Project> projectOptional = projectRepository.findById(selectedProjectId);
        Project project = projectOptional.get();
        if (todoOptional.isPresent()) {
            Todo todo = todoOptional.get();
            todoRepository.updateContent(id, content);
        todoRepository.updateContentAndProject(id, content, project);
        }
        return "redirect:/todo/{id}/detail";
    }
}
