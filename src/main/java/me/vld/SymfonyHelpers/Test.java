package me.vld.SymfonyHelpers;

public class Test {
    public static void main(String[] args) {
        String test = "<?php\n" +
                "\n" +
                "namespace Acme\\DemoBundle\\Controller;\n" +
                "\n" +
                "use Symfony\\Bundle\\FrameworkBundle\\Controller\\Controller;\n" +
                "use Symfony\\Component\\HttpFoundation\\RedirectResponse;\n" +
                "use Symfony\\Component\\HttpFoundation\\Request;\n" +
                "use Sensio\\Bundle\\FrameworkExtraBundle\\Configuration\\Route;\n" +
                "use Sensio\\Bundle\\FrameworkExtraBundle\\Configuration\\Method;\n" +
                "use Sensio\\Bundle\\FrameworkExtraBundle\\Configuration\\Template;\n" +
                "\n" +
                "class TestController extends Controller\n" +
                "{\n" +
                "    /**\n" +
                "     * @Route(\"/\", name=\"test_index\")\n" +
                "     * @Template()\n" +
                "     */\n" +
                "    public function indexAction()\n" +
                "    {\n" +
                "        return array();\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * @Route(\"/contact\", name=\"test_contact\")\n" +
                "     * @Method(\"GET\")\n" +
                "     * @Template()\n" +
                "     */\n" +
                "    public function contactAction()\n" +
                "    {\n" +
                "        return array();\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * @Route(\"/contact\", name=\"test_contact_post\")\n" +
                "     * @Method(\"POST\")\n" +
                "     * @Template()\n" +
                "     */\n" +
                "    public function contactPostAction()\n" +
                "    {\n" +
                "        return array();\n" +
                "    }\n" +
                "}\n";

        String[] arr = test.split("\n");
        int lineNumberIter = 0;
        int lineNumber = 0;
        int lineIndexOf = 0;
        for (String s : arr) {
            lineNumberIter++;
            if (s.matches("(.*)contactAction(.*)")) {
                lineIndexOf = s.indexOf("contactAction") + 1;
                lineNumber = lineNumberIter;
            }
        }
        System.out.println(lineNumber);
        System.out.println(lineIndexOf);
    }
}