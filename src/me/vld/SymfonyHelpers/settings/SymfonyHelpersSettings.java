package me.vld.SymfonyHelpers.settings;

/**
 * @author AveVlad
 */

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.*;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@State(
        name = "SymfonyHelpersSettings",
        storages = {
                @Storage(file = StoragePathMacros.APP_CONFIG + "/symfony.helpers.settings.xml")
        }
)
public class SymfonyHelpersSettings implements PersistentStateComponent<SymfonyHelpersSettings>, ApplicationComponent {
    private boolean enableOpenViews = true;

    public static SymfonyHelpersSettings getInstance() {
        return ApplicationManager.getApplication().getComponent(SymfonyHelpersSettings.class);
    }

    @Nullable
    @Override
    public SymfonyHelpersSettings getState() {
        return this;
    }

    @Override
    public void loadState(SymfonyHelpersSettings settings) {
        XmlSerializerUtil.copyBean(settings, this);
    }

    @Override
    public void initComponent() {
    }

    @Override
    public void disposeComponent() {
    }

    @NotNull
    @Override
    public String getComponentName() {
        return getClass().getName();
    }

    public boolean isEnableOpenViews() {
        return enableOpenViews;
    }

    public void setEnableOpenViews(boolean enableOpenViews) {
        this.enableOpenViews = enableOpenViews;
    }
}