package me.vld.SymfonyHelpers;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.scope.PsiScopeProcessor;

public class SymfonyHelpersOpen implements PsiScopeProcessor.Event {
    public static Project project;
    public static VirtualFile baseDir;

    public SymfonyHelpersOpen(Project project) {
        SymfonyHelpersOpen.project = project;
        SymfonyHelpersOpen.baseDir = project.getBaseDir();
    }

    /**
     * @param route route
     */
    public static void open(String route, String type) {
//        if (type.equals("controller")) {
//            System.out.println("controller controller");
//        }
//        if (type.equals("view")) {
//            System.out.println("view view");
//        }
        VirtualFile file = baseDir.findFileByRelativePath("/rrr/1.txt");
        openFile(file);
    }

    public static void openFile(VirtualFile file) {
        ApplicationManager.getApplication().invokeLater(new Runnable() {
            @Override
            public void run() {
                if (file != null && file.isValid()) {
                    FileEditorManager fileEditorManager = FileEditorManager.getInstance(project);
                    fileEditorManager.openFile(file, true);
                }
            }
        });
    }
}