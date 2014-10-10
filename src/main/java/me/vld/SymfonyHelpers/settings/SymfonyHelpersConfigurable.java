package me.vld.SymfonyHelpers.settings;

import com.intellij.openapi.options.BaseConfigurable;
import com.intellij.openapi.options.ConfigurationException;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
* @author AveVlad
*/
public class SymfonyHelpersConfigurable extends BaseConfigurable {
    private JPanel panel;
    private JCheckBox enableOpenViewsCheckBox;

    public SymfonyHelpersConfigurable() {
        System.out.println("SymfonyHelpersSettingsForm");
        SymfonyHelpersSettings settings = getSettings();
        enableOpenViewsCheckBox.setSelected(settings.isEnableOpenViews());
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
        SymfonyHelpersSettings settings = getSettings();
        return enableOpenViewsCheckBox.isSelected() != settings.isEnableOpenViews();
    }

    @Override
    public void apply() throws ConfigurationException {
        SymfonyHelpersSettings settings = getSettings();
        settings.setEnableOpenViews(enableOpenViewsCheckBox.isSelected());
    }

    @Override
    public void reset() {

    }

    @Override
    public void disposeUIResources() {
        enableOpenViewsCheckBox = null;
    }

    private SymfonyHelpersSettings getSettings() {
        return SymfonyHelpersSettings.getInstance();
    }
}
