package academyClean.ProjetoJavaClean.service;

import academyClean.ProjetoJavaClean.dominio.CommentsReader;
import academyClean.ProjetoJavaClean.dominio.DirectoryReader;

import java.io.IOException;
import java.nio.file.Paths;

public class JavaClean {

    public static void main(String[] args) throws IOException {
        
        DirectoryReader directoryReader = new DirectoryReader(
                Paths.get("caminhoOrigem"),
                Paths.get("caminhoDestino")
        );

        if (!directoryReader.checkOrigem()) {
            System.out.println("Não foi possível encontrar o diretório "+directoryReader.getOrigem().getFileName());
            return;
        }

        directoryReader.createDirectory();

        CommentsReader commentsReader = new CommentsReader(
                directoryReader.getOrigem(),
                directoryReader.getDestino()
        );
        commentsReader.process();

        System.out.println("Os arquivos Java do diretório "+directoryReader.getOrigem().getFileName()+ " foram limpos e agora encontram-se no diretório " + directoryReader.getDestino().getFileName()+ ".");
    }
}
