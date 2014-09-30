package me.vld.SymfonyHelpers.route;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RouteHelper {
    public static Project project;
    public static VirtualFile baseDir;
    public static String fileContent = "";
    public static String routeName;
    public static Map routesMap;

    public RouteHelper(Project project, String routeName) throws IOException {
        RouteHelper.project = project;
        RouteHelper.baseDir = project.getBaseDir();
        RouteHelper.routeName = routeName;
        RouteHelper.routesMap = routesMap();
    }

    public static Map routesMap() throws IOException {
        System.out.println(routeName + " routeName -> routesMap run");
        VirtualFile file = baseDir.findFileByRelativePath("app/cache/dev/appDevUrlGenerator.php");
        if (file != null) {
            fileContent = VfsUtil.loadText(file);
        }
        Map<String, String> routesMap = new HashMap<String, String>();
        Matcher matcher = Pattern.compile("'((?:[^'\\\\]|\\\\.)*)' => [^\\n]+'_controller' => '((?:[^'\\\\]|\\\\.)*)'[^\\n]+\n").matcher(fileContent);
        while (matcher.find()) {
            if (!matcher.group(2).matches("(.*)::(.*)")) {
                continue;
            }
            routesMap.put(matcher.group(1), matcher.group(2));
        }

        return routesMap;
    }

    public String getFileAction() throws IOException {
        String path = String.valueOf(routesMap.get(routeName));
        String[] splitPath = path.split("::");
        String controller = "src/" + splitPath[0].replace("\\", "/") + ".php";
        String action = splitPath[1];
        VirtualFile file = baseDir.findFileByRelativePath(controller);
        if (file != null) {
            System.out.println(VfsUtil.loadText(file));
        }

        return controller;
    }

    public String getFileView() {
        System.out.println(routesMap);
        System.out.println(routesMap.get(routeName));
        return "";
    }

    private String getStrokeNumber() {
        return "";
    }
}