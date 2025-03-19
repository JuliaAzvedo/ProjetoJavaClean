package academyClean.ProjetoJavaClean.dominio;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class CommentsReader extends SimpleFileVisitor<Path> {

    private Path origemDir;
    private Path destinoDir;

    public CommentsReader(Path origemDir, Path destinoDir) {
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

        
        if (file.toString().endsWith(".java")) {
            
            removeComments(file, targetFile);
        } else {
            
            Files.copy(file, targetFile, StandardCopyOption.REPLACE_EXISTING);
        }

        return FileVisitResult.CONTINUE;
    }

    private void removeComments(Path sourceFile, Path targetFile) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(sourceFile);
             BufferedWriter writer = Files.newBufferedWriter(targetFile, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {

            
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }

            String code = content.toString();

            
            code = removeBlockComments(code);

            
            code = removeLineComments(code);

            
            writer.write(code);
            writer.flush();
        }
    }

    private String removeBlockComments(String code) {
        StringBuilder result = new StringBuilder();
        boolean inComment = false;
        boolean inString = false;
        char prevChar = '\0';

        for (int i = 0; i < code.length(); i++) {
            char c = code.charAt(i);
            char nextChar = (i < code.length() - 1) ? code.charAt(i + 1) : '\0';

            
            if (c == '"' && prevChar != '\\' && !inComment) {
                inString = !inString;
                result.append(c);
            }
            
            else if (c == '/' && nextChar == '*' && !inString && !inComment) {
                inComment = true;
                i++; 
            }
            
            else if (c == '*' && nextChar == '/' && inComment) {
                inComment = false;
                i++; 
            }
            
            else if (!inComment) {
                result.append(c);
            }

            prevChar = c;
        }

        return result.toString();
    }

    private String removeLineComments(String code) {
        StringBuilder result = new StringBuilder();
        String[] lines = code.split("\n");

        for (String line : lines) {
            boolean inString = false;
            int commentStart = -1;

            for (int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);
                char nextChar = (i < line.length() - 1) ? line.charAt(i + 1) : '\0';

                
                if (c == '"' && (i == 0 || line.charAt(i - 1) != '\\')) {
                    inString = !inString;
                }

                
                if (c == '/' && nextChar == '/' && !inString) {
                    commentStart = i;
                    break;
                }
            }

            
            if (commentStart != -1) {
                result.append(line.substring(0, commentStart)).append("\n");
            } else {
                result.append(line).append("\n");
            }
        }

        return result.toString();
    }

    public void process() throws IOException {
        if (Files.exists(origemDir)) {
            Files.walkFileTree(origemDir, this);
        } else {
            throw new IOException("Diretório de origem não existe: " + origemDir);
        }
    }
}
