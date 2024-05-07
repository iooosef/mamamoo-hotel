package ph.edu.tip.mamamoo.Utilities;

import java.io.File;

public class PathDebug {
    public static void getAllFiles(File curDir) {
        File[] filesList = curDir.listFiles();
        for(File f : filesList){
            if(f.isDirectory())
                System.out.println(f.getName());
            if(f.isFile()){
                System.out.println(f.getName());
            }
        }

    }
}
