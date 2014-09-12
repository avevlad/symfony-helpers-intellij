package me.vld.SymfonyHelpers;

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
//        System.out.println(new java.util.Date());
//        System.out.println(route);
//        System.out.println(type);
//        System.out.println(project);
//        if (type.equals("controller")) {
//            System.out.println("controller controller");
//        }
//        if (type.equals("view")) {
//            System.out.println("view view");
//        }
        VirtualFile file = baseDir.findFileByRelativePath("/rrr/1.txt");
        System.out.println(file + " file");
        System.out.println("-----------------");
        openFile(file);
    }

    public static void openFile(VirtualFile file) {
        FileEditorManager fileEditorManager = FileEditorManager.getInstance(project);
        fileEditorManager.openFile(file, true);
//        Application application = ApplicationManager.getApplication();
//        application.runWriteAction(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("application.runWriteAction");
//            }
//        });
    }

    // тоже выдает исключение
    public static class RunnableCreateAndOpenFile implements Runnable {

        VirtualFile file;
        Project project;

        RunnableCreateAndOpenFile(Project project, VirtualFile file) {
            this.project = project;
            this.file = file;
            run();
        }

        @Override
        public void run() {
            System.out.println("runrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");
            FileEditorManager fileEditorManager = FileEditorManager.getInstance(project);
            fileEditorManager.openFile(file, true, true);
        }
    }
}