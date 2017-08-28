package chao.plugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;

@Mojo(name = "count-plugin",defaultPhase = LifecyclePhase.PACKAGE)
public class CountJavaFile extends AbstractMojo {

    /**
     * 项目目录，可以传参，也可以配置参数
     */
    @Parameter(property = "project.basedir")
    private String projectDir;

    /**
     * 统计的文件类型，可以配置（默认类型是java）
     */
    @Parameter(defaultValue = ".java")
    private String fileType;

    private int filesCount;

    public void execute() throws MojoExecutionException, MojoFailureException {
        System.out.println("projectDir:" + projectDir + ",fileType:" + fileType);
        File rootFile = new File(projectDir);
        statJavaFiles(rootFile);
        System.out.println("files count:" + filesCount);
    }

    private void statJavaFiles(File file) {
        if (file.isDirectory()) {
            File[] lists = file.listFiles();
            if (lists != null) {
                for (int i = 0; i < lists.length; i++) {
                    statJavaFiles(lists[i]);
                }
            }
        } else {
            if (file.getName().endsWith(fileType))
                filesCount++;
        }
    }
}
