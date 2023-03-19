package gitlet;
import java.nio.file.Path;
import java.io.File;
import static gitlet.Utils.*;
import java.io.IOException;
public class Initrepo {
    public static final File CWD = new File(System.getProperty("user.dir"));
    /** The .gitlet directory. */
    public static final File GITLET_DIR = join(CWD, ".gitlet");
    /** The .gitlet/objects directory. */
    public static final File OBJECTS_DIR = join(GITLET_DIR, "objects");
    /** The .gitlet/refs directory. */
    public static final File REFS_DIR = join(GITLET_DIR, "refs");
    /** The .gitlet/refs/heads directory. */
    public static final File HEADS_DIR = join(REFS_DIR, "heads");

    /** The .gitlet/index file. */

    /** The .gitlet/HEAD file. */
    public static final File HEAD_FILE = join(GITLET_DIR, "HEAD");

    public static void initRepository() throws IOException{
        prepareRepository();
        Commit initCommit = new Commit();
        String hashValue = Repository.writeObjectInDir(initCommit);
        Repository.updateHeadAfterCommit(hashValue);
    }
    private static void prepareRepository() throws IOException {

        // create necessary directories
        GITLET_DIR.mkdirs();
        OBJECTS_DIR.mkdirs();
        REFS_DIR.mkdirs();
        HEADS_DIR.mkdirs();
        HEAD_FILE.createNewFile();
        // create master head file if it doesn't exist
        File masterHeadFile = join(HEADS_DIR, "master");

        // create HEAD file and write the path to the master head file
        writeContents(HEAD_FILE, Repository.relativeSimplePath(GITLET_DIR, masterHeadFile).toString());

        // empty staging area
        Repository.resetStage();
    }


}
