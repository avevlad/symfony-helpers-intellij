package me.vld.SymfonyHelpers;

import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;

public class SymfonyHelpersOpen {
    public static Project project;
    public static VirtualFile baseDir;

    public SymfonyHelpersOpen(Project project) {
        System.out.println(project);
        SymfonyHelpersOpen.project = project;
        SymfonyHelpersOpen.baseDir = project.getBaseDir();
    }

    /**
     * @param route route
     */
    public static void open(String route, String type) {
        System.out.println(new java.util.Date());
        System.out.println(route);
        System.out.println(type);
        System.out.println(project);
        if (type.equals("controller")) {
            System.out.println("controller controller");
        }
        if (type.equals("view")) {
            System.out.println("view view");
        }
        VirtualFile file = baseDir.findFileByRelativePath("/rrr/1.txt");
        openFile(file);
        System.out.println(file + " file");
        System.out.println("-----------------");
    }

    public static void openFile(VirtualFile file) {
        FileEditorManager fileEditorManager = FileEditorManager.getInstance(project);
        fileEditorManager.openFile(file, true, true);
    }
}