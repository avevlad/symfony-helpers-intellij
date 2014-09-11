package me.vld.SymfonyHelpers;

import com.intellij.openapi.components.ProjectComponent;
import org.jetbrains.annotations.NotNull;

/**
 * @author AveVlad
 */
public class SymfonyHelpersComponent implements ProjectComponent {
    @NotNull
    public String getComponentName() {
        return "symfony.helpers.component";
    }

    @Override
    public void projectOpened() {
        System.out.println("projectOpened");
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
