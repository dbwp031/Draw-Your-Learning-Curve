package com.dbwp031.dylc.controller;

import com.dbwp031.dylc.domain.Member;
import com.dbwp031.dylc.domain.Project;
import com.dbwp031.dylc.domain.Todo;
import com.dbwp031.dylc.domain.TodoPostDto;
import com.dbwp031.dylc.repository.MemberRepository;
import com.dbwp031.dylc.repository.ProjectRepository;
import com.dbwp031.dylc.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
@RequestMapping("/project")
public class ProjectController {
    private final TodoRepository todoRepository;
    private final ProjectRepository projectRepository;
    private final MemberRepository memberRepository;

    @GetMapping("")
    public String projectPage(Model model, Principal principal) {
        String memberName = principal.getName();
//        System.out.println(memberPrincipal.toString());
//        authentication.
//        Optional<Member> member = memberRepository.findMemberByLoginId(memberPrincipal.getAttribute("login"));
        Optional<Member> member = memberRepository.findMemberByLoginId(memberName);
        if (member.isPresent()) {
            List<Project> projectRaw = member.get().getProjects();
//            List<TodoPostDto> todos = todosRaw.stream().map(todo -> new TodoPostDto(todo.getId(), todo.getContent(),todo.isDone())).toList();
            model.addAttribute("projects", projectRaw);
        }
        return "/template/project/main";
    }

    @GetMapping("/create")
    public String visitTodoCreate() {
        return "template/project/create";
    }

    @PostMapping("/create")
    public String createTodo(@RequestParam String projectName, @RequestParam String description, Principal principal) {
        String memberName = principal.getName();
        Optional<Member> member = memberRepository.findMemberByLoginId(memberName);
        if (member.isPresent()) {
            Project project = Project.builder()
                    .title(projectName)
                    .member(member.get())
                    .description(description)
                    .build();
            projectRepository.save(project);

        } else {
            System.out.println("프로젝트: null");
        }

        return "redirect:/project";
    }

    @GetMapping("/{id}/detail")
    public String detail(@PathVariable Long id, Model model) {
        Optional<Project> project = projectRepository.findProjectById(id);
        System.out.println(project.toString());
        if (project.isPresent()) {
            List<Todo> todos = todoRepository.findAllByProject(project.get());
            System.out.println(project.get().getTitle());
            model.addAttribute("title", project.get().getTitle());
            model.addAttribute("description", project.get().getDescription());
            model.addAttribute("createdDate", project.get().getCreatedDate());
            model.addAttribute("id", project.get().getId());
            model.addAttribute("todos", todos);
        }
        return "template/project/detail";
    }

    @GetMapping("/{id}/update")
    public String update(@PathVariable Long id, Model model) {
        Optional<Project> project = projectRepository.findProjectById(id);
        System.out.println(project.toString());
        if (project.isPresent()) {
            System.out.println(project.get().getTitle());
            model.addAttribute("title", project.get().getTitle());
            model.addAttribute("description", project.get().getDescription());
            model.addAttribute("createdDate", project.get().getCreatedDate());
            model.addAttribute("id", project.get().getId());
        }
        return "template/project/update";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable Long id,
                         @RequestParam(required = false) String title,
                         @RequestParam(required = false) LocalDateTime createdDate,
                         @RequestParam(required = false) String description) {
        projectRepository.updateProjectFieldTitleAndDescriptionById(id, title, description);
        return "redirect:/project";
    }
}
