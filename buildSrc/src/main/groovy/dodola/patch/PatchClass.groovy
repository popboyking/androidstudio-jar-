package dodola.patch;

import javassist.ClassPool
import javassist.CtClass
import javassist.CtConstructor
import javassist.CtMethod
import javassist.CtNewConstructor
import javassist.CtNewMethod

public class PatchClass {
    /**
     * 植入代码
     * @param buildDir 是项目的build class目录,就是我们需要注入的class所在地
     * @param lib 这个是hackdex的目录,就是CombomBox类的class文件所在地
     */
    public static void process(String buildDir, String lib) {

        println(lib)
        ClassPool classes = ClassPool.getDefault()
        classes.appendClassPath(buildDir)
        classes.appendClassPath(lib)

        //下面的操作比较容易理解,在将需要关联的类的构造方法中插入引用代码

        CtClass c = classes.getCtClass("dex.zzcm.com.dexupdate.core.User")
        if (c.isFrozen()) {
            c.defrost()
        }
        println("====添加构造方法====")
        def constructor = c.getConstructors()[0];
        constructor.insertBefore("System.out.println(dex.zzcm.com.codefix.CombomBox.class);")
//        constructor.insertBefore("new dex.zzcm.com.codefix.CombomBox().play();")
        c.writeFile(buildDir)

        CtClass c1 = classes.getCtClass("dex.zzcm.com.dexupdate.SourceSystemManager")
        if (c1.isFrozen()) {
            c1.defrost()
        }
        println("====添加构造方法====")
        def constructor1 = c1.getConstructors()[0];
        constructor1.insertBefore("");
        constructor1.insertBefore("System.out.println(dex.zzcm.com.codefix.CombomBox.class);")
        c1.writeFile(buildDir)




        CtClass c2 = classes.getCtClass("dex.zzcm.com.dexupdate.MainActivity")
        if (c2.isFrozen()) {
            c2.defrost()
        }
        println("====添加构造方法====")
        def constructor2 = c2.getConstructors()[0];
        constructor2.insertBefore("");
        constructor2.insertBefore("System.out.println(dex.zzcm.com.codefix.CombomBox.class);")
        c2.writeFile(buildDir)

    }

    static void growl(String title, String message) {
        def proc = ["osascript", "-e", "display notification \"${message}\" with title \"${title}\""].execute()
        if (proc.waitFor() != 0) {
            println "[WARNING] ${proc.err.text.trim()}"
        }
    }
}
