# Sobre este projeto 
Projeto pessoal desenvolvido para automatizar o processo de remoção de comentários em arquivos Java antes de disponibiliza-los no GitHub, ootimizando tempo e mantendo o código limpo. 
A ideia é poder estudar e adicionar comentários ao código para melhorar a aprendizagem sem se preocupar em ter que apagar tudo manualmente antes de subir os códigos no github.

# Funcionalidades
Lê o diretório de origem e cria um diretório destino para receber os subdiretórios e arquivos do diretório de origem.

Percorre o diretório de origem de forma recursiva e cria uma cópia de todos os subdiretórios e arquivos no destino. 

Lê linha por linha dos arquivos Java e remove os comentários. 

Sobrescreve a cópia criada no destino mantendo a estrutura original sem alterar os arquivos da origem.


# Tecnologias utilizadas
Linguagem Java; Bibliotecas: java.io e java.nio; Orientação a objetos.

# Como utilizar o projeto
Clone o repositório:
git clone https://github.com/JuliaAzvedo/ProjetoJavaClean.git

Em seguida, compile e execute o programa.

Abra o programa em sua IDEA de preferencia, acesse o arquivo JavaClean na pasta service. 

Nos parametros passados na instancia da classe DirectoryReader, substitua "caminhoOrigem" pelo caminho do diretório que contenha os arquivos Java. Substitua "caminhoDestino" pelo caminho do diretório a ser criado para receber os arquivos da origem e sobrescreve-los sem comentários. 
        
Compile o código e seu novo diretório com arquivos Java livre de comentários será gerado no caminho que foi informado para o destino. 






