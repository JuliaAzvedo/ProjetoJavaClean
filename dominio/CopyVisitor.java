package academyClean.ProjetoJavaClean.dominio;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class CopyVisitor extends SimpleFileVisitor<Path> {

    private Path origemDir;
    private Path destinoDir;

    public CopyVisitor(Path origemDir, Path destinoDir) {
        this.origemDir = origemDir;
        this.destinoDir = destinoDir;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        
        Path targetDir = destinoDir.resolve(origemDir.relativize(dir));
        Files.createDirectories(targetDir);
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        
        Path relativePath = origemDir.relativize(file);
        Path targetFile = destinoDir.resolve(relativePath);

        try {
            
            Files.copy(file, targetFile, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.err.println("Erro ao copiar o arquivo: " + file + " - " + e.getMessage());
        }

        return FileVisitResult.CONTINUE;
    }

    public void processArq() throws IOException {
        if (Files.exists(origemDir)) {
            Files.walkFileTree(origemDir, this);
        } else {
            throw new IOException("Diretório de origem não existe: " + origemDir);
        }
    }
}
