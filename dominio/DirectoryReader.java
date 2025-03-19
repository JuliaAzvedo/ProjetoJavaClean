package academyClean.ProjetoJavaClean.dominio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DirectoryReader {

    private Path origem;
    private Path destino;

    public DirectoryReader(Path origem, Path destino) {
        this.origem = origem;
        this.destino = destino;
    }

    public Path getOrigem() {
        return this.origem;
    }

    public Path getDestino() {
        return this.destino;
    }

    public void createDirectory() {
        try {
            
            if (Files.exists(destino)) {
                System.out.println("O diretório já existe.");
            } else {
                
                Files.createDirectories(destino);
                System.out.println("Diretório criado.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public boolean checkOrigem() {
        if (!Files.exists(origem)) {
            System.err.println("O diretório de origem não existe: " + origem);
            return false;
        }
        return true;
    }
}
