package me.vld.SymfonyHelpers;

import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
* @author AveVlad
*/
public class SymfonyHelpersComponent implements ProjectComponent {

    Project project;
    VirtualFile baseDir;

    @SuppressWarnings("UnusedParameters")
    public SymfonyHelpersComponent(Project project) {
        System.out.println("SymfonyHelpersComponent");
        this.project = project;
        this.baseDir = project.getBaseDir();
    }

    @NotNull
    public String getComponentName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public void projectOpened() {
        System.out.println("projectOpened");
        System.out.println(new java.util.Date());
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
