package me.vld.SymfonyHelpers.settings;

/**
 * @author AveVlad
 */
import com.intellij.openapi.components.*;
import com.intellij.openapi.project.Project;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.Nullable;

@State(
        name = "SymfonyHelpersSettings",
        storages = {
                @Storage(id = "default", file = StoragePathMacros.PROJECT_FILE),
                @Storage(id = "dir", file = StoragePathMacros.PROJECT_CONFIG_DIR + "/symfony2.xml", scheme = StorageScheme.DIRECTORY_BASED)
        }
)
public class SymfonyHelpersSettings implements PersistentStateComponent<SymfonyHelpersSettings> {
    public static String DEFAULT_APP_DIRECTORY = "app";
    public boolean pluginEnabled = false;

    protected Project project;

    public static SymfonyHelpersSettings getInstance(Project project) {
        SymfonyHelpersSettings settings = ServiceManager.getService(project, SymfonyHelpersSettings.class);

        settings.project = project;

        return settings;
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
}