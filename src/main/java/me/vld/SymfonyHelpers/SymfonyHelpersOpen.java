package me.vld.SymfonyHelpers;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import me.vld.SymfonyHelpers.route.RouteHelper;

import java.io.IOException;

public class SymfonyHelpersOpen {
    public static Project project;
    public static VirtualFile baseDir;

    public SymfonyHelpersOpen(Project project) {
        SymfonyHelpersOpen.project = project;
        SymfonyHelpersOpen.baseDir = project.getBaseDir();
    }

    /**
     * @param route     - route name
     * @param routeType - route type
     * @throws IOException
     */
    public static void findFile(String route, String routeType) throws IOException {
        System.out.println(route + " - route findFile");
        RouteHelper routeHelper = new RouteHelper(project, route);
        String filename = null;
        if (routeType.equals("controller")) {
            filename = routeHelper.getFileAction();
        }
        if (routeType.equals("view")) {
            filename = routeHelper.getFileView();
        }
        VirtualFile file;
        if (filename != null) {
            file = baseDir.findFileByRelativePath(filename);
            System.out.println(file + " findFileByRelativePath");
            openFile(file);
        }
    }

    /**
     * @param file - VirtualFile
     */
    public static void openFile(VirtualFile file) {
        ApplicationManager.getApplication().invokeLater(new Runnable() {
            @Override
            public void run() {
                if (file != null && file.isValid()) {
                    FileEditorManager fileEditorManager = FileEditorManager.getInstance(project);
                    fileEditorManager.openFile(file, true, true);
                }
            }
        });
    }
}