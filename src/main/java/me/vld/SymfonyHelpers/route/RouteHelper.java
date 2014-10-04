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

    public Object[] getFileAction() throws IOException {
        String path = String.valueOf(routesMap.get(routeName));
        String[] splitPath = path.split("::");
        String controller = "src/" + splitPath[0].replace("\\", "/") + ".php";
        String action = splitPath[1];
        VirtualFile file = baseDir.findFileByRelativePath(controller);
        int[] lineInfo = new int[0];
        if (file != null) {
            lineInfo = getLineInfo(VfsUtil.loadText(file), action);
            System.out.println(controller + " controller");
            System.out.println(lineInfo[1] + " lineInfo");
            System.out.println(lineInfo[0] + " lineInfo");
        }

        return new Object[]{controller, lineInfo[0], lineInfo[1]};
    }

    public String getFileView() {
        System.out.println(routesMap);
        System.out.println(routesMap.get(routeName));
        return "";
    }

    private int[] getLineInfo(String text, String actionName) {
        String[] arr = text.split("\n");
        int lineNumberIter = 0;
        int lineNumber = 0;
        int columnNumber = 0;
        for (String s : arr) {
            if (s.matches("(.*)" + actionName + "(.*)")) {
                columnNumber = s.indexOf(actionName) - 1;
                lineNumber = lineNumberIter;
            }
            lineNumberIter++;
        }

        return new int[]{lineNumber, columnNumber};
    }
}