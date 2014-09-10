package me.vld.SymfonyHelpers.settings;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * @author AveVlad
 */
public class SymfonyHelpersSettingsForm implements Configurable {
    private Project project;
    private JPanel panel;
    private JCheckBox enableOpenViewsCheckBox;
    private SymfonyHelpersSettings mySettings;

    public SymfonyHelpersSettingsForm(SymfonyHelpersSettings settings) {
        mySettings = settings;
    }

    public SymfonyHelpersSettingsForm() {
        System.out.println("SymfonyHelpersSettingsForm");
    }

    @Nls
    @Override
    public String getDisplayName() {    
        return "Symfony Helpers";
    }

    @Nullable
    @Override
    public String getHelpTopic() {
        return null;
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        return panel;
    }

    @Override
    public boolean isModified() {
        System.out.println("isModified");
        System.out.println(enableOpenViewsCheckBox);
        System.out.println("===============");
        return false;
    }

    @Override
    public void apply() throws ConfigurationException {
        System.out.println("apply");

    }

    @Override
    public void reset() {

    }

    @Override
    public void disposeUIResources() {

    }

    private SymfonyHelpersSettings getSettings() {
        return SymfonyHelpersSettings.getInstance(project);
    }
}
