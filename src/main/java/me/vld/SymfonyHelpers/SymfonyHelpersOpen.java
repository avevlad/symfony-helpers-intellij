package me.vld.SymfonyHelpers;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.fileEditor.OpenFileDescriptor;
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
        Object[] fileParams = null;
        if (routeType.equals("controller")) {
            fileParams = routeHelper.getFileAction();
        }
        if (routeType.equals("view")) {
            String filename = routeHelper.getFileView();
        }
        VirtualFile file = null;
        System.out.println("fileParams start");
        if (fileParams != null) {
            System.out.println("fileParams end");
            file = baseDir.findFileByRelativePath(String.valueOf(fileParams[0]));
            int line = (Integer) fileParams[1];
            int column = (Integer) fileParams[2];
            openFile(file, line, column);
        }
    }

    /**
     * @param file   - VirtualFile
     * @param line   - file line
     * @param column - file column
     */
    public static void openFile(VirtualFile file, int line, int column) {
        ApplicationManager.getApplication().invokeLater(new Runnable() {
            @Override
            public void run() {
                if (file != null && file.isValid()) {
                    new OpenFileDescriptor(project, file, line, column).navigate(true);
                }
            }
        });
    }
}