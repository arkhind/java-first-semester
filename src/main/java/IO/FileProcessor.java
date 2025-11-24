package IO;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class FileProcessor {

    public List<Path> splitFile(String sourcePath, String outputDir, int partSize) throws IOException {
        List<Path> parts = new ArrayList<>();
        Path source = Paths.get(sourcePath);
        String fileName = source.getFileName().toString();

        try (FileChannel sourceChannel = FileChannel.open(source, StandardOpenOption.READ)) {
            ByteBuffer buffer = ByteBuffer.allocate(partSize);
            int partNumber = 1;

            while (sourceChannel.read(buffer) > 0) {
                buffer.flip();

                String partName = fileName + ".part" + partNumber;
                Path partPath = Paths.get(outputDir, partName);

                try (FileChannel partChannel = FileChannel.open(partPath,
                        StandardOpenOption.CREATE, StandardOpenOption.WRITE)) {
                    partChannel.write(buffer);
                }

                parts.add(partPath);
                partNumber++;
                buffer.clear();
            }
        }

        return parts;
    }

    public void mergeFiles(List<Path> partPaths, String outputPath) throws IOException {
        try (FileChannel outputChannel = FileChannel.open(Paths.get(outputPath),
                StandardOpenOption.CREATE, StandardOpenOption.WRITE)) {

            for (Path partPath : partPaths) {
                try (FileChannel partChannel = FileChannel.open(partPath, StandardOpenOption.READ)) {
                    ByteBuffer buffer = ByteBuffer.allocate(8192);

                    while (partChannel.read(buffer) > 0) {
                        buffer.flip();
                        outputChannel.write(buffer);
                        buffer.clear();
                    }
                }
            }
        }
    }
}