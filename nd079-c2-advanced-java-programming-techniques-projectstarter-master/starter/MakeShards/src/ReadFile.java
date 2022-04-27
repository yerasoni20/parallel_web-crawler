import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class ReadFile {

    public void read() throws IOException {
        BufferedReader reader =
                Files.newBufferedReader(Path.of("unsorted.txt"), StandardCharsets.UTF_8);
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        reader.close();
    }
}
