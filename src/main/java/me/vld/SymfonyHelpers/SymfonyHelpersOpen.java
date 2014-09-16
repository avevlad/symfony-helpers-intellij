package me.vld.SymfonyHelpers;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;

public class SymfonyHelpersOpen {
    public static Project project;
    public static VirtualFile baseDir;

    public SymfonyHelpersOpen(Project project) {
        SymfonyHelpersOpen.project = project;
        SymfonyHelpersOpen.baseDir = project.getBaseDir();
    }

    /**
     * @param route route
     */
    public static void findFile(String route, String type) {
        System.out.println(route + " route");
        if (type.equals("controller")) {
            System.out.println("controller controller");
        }
        if (type.equals("view")) {
            System.out.println("view view");
        }
        VirtualFile file = baseDir.findFileByRelativePath(route);
        System.out.println(file + " file");
        openFile(file);
    }

    public static void openFile(VirtualFile file) {
        ApplicationManager.getApplication().invokeLater(new Runnable() {
            @Override
            public void run() {
                if (file != null && file.isValid()) {
                    System.out.println("open FileEditorManager");
                    FileEditorManager fileEditorManager = FileEditorManager.getInstance(project);
                    fileEditorManager.openFile(file, true, true);
                    System.out.println("open FileEditorManager end");
                    System.out.println("-----------------------------------------------");
                }
            }
        });
    }
}