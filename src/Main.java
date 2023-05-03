import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

    public static void main(String[] args) {
        GameProgress save1 = new GameProgress(100, 10, 15, 20);
        GameProgress save2 = new GameProgress(80, 4, 20, 50);
        GameProgress save3 = new GameProgress(30, 15, 2, 40);

        saveGame("/Users/stkatze/Workspace/Games/savegames/save1.dat", save1);
        saveGame("/Users/stkatze/Workspace/Games/savegames/save2.dat", save2);
        saveGame("/Users/stkatze/Workspace/Games/savegames/save3.dat", save3);

        ArrayList<String> saves = new ArrayList<>();
        saves.add("/Users/stkatze/Workspace/Games/savegames/save1.dat");
        saves.add("/Users/stkatze/Workspace/Games/savegames/save2.dat");
        saves.add("/Users/stkatze/Workspace/Games/savegames/save3.dat");

        zipFiles("/Users/stkatze/Workspace/Games/savegames/save.zip", saves);

        File save1Dat = new File("/Users/stkatze/Workspace/Games/savegames/save1.dat");
        File save2Dat = new File("/Users/stkatze/Workspace/Games/savegames/save2.dat");
        File save3Dat = new File("/Users/stkatze/Workspace/Games/savegames/save3.dat");

        if (save1Dat.delete()) System.out.println("Файл удален");
        if (save2Dat.delete()) System.out.println("Файл удален");
        if (save3Dat.delete()) System.out.println("Файл удален");
    }

    private static void saveGame(String dirPath, GameProgress save) {
        try (FileOutputStream fos = new FileOutputStream(dirPath);
             ObjectOutputStream saving = new ObjectOutputStream(fos)) {
            saving.writeObject(saving);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void zipFiles(String dirPath, List<String> saves) {
        try (ZipOutputStream zip = new ZipOutputStream(new FileOutputStream(dirPath))) {
            for (String i : saves) {
                try (FileInputStream streamSave = new FileInputStream(i)) {
                    ZipEntry entry = new ZipEntry(i);
                    zip.putNextEntry(entry);
                    while (streamSave.available() > 0) {
                        zip.write(streamSave.read());
                    }
                    zip.closeEntry();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}