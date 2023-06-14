package com.dbwp031.dylc.repository;

import com.dbwp031.dylc.domain.Project;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    public Optional<Project> findProjectById(Long id);
    @Transactional
    @Modifying
    @Query("update Project project set project.title=?2, project.description=?3 where project.id=?1")
    public void updateProjectFieldTitleAndDescriptionById(Long id, String title, String description);
}
