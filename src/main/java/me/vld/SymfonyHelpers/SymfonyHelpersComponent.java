package me.vld.SymfonyHelpers;

import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.project.Project;
import me.vld.SymfonyHelpers.server.SymfonyHelpersServer;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * @author AveVlad
 */
public class SymfonyHelpersComponent implements ProjectComponent {
    private Project project;

    @SuppressWarnings("UnusedParameters")
    public SymfonyHelpersComponent(Project project) {
        this.project = project;
    }

    @NotNull
    public String getComponentName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public void projectOpened() {
        SymfonyHelpersOpen open = new SymfonyHelpersOpen(project);
        SymfonyHelpersServer server = new SymfonyHelpersServer(8814);
        try {
            server.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void projectClosed() {
    }


    @Override
    public void initComponent() {
    }

    @Override
    public void disposeComponent() {
    }
}
