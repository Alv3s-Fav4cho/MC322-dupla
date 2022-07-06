# MC322

# Alunos
* Nome: Daniel Alves da Rocha RA:247024
* Nome: Leonardo Luca Favacho RA:247226

# Curso
* 34 - Engenharia da Computação

# Lab05 - Mundo de Wumpus

[Arquivos Java](https://github.com/Alv3s-Fav4cho/MC322-dupla/tree/main/Lab05/src/pt/c40task/l05wumpus)

[Destaques](https://github.com/Alv3s-Fav4cho/MC322-dupla/blob/main/Lab05/Destaques.pdf)

# Projeto Lost in the Darkness

# Descrição Resumida do Projeto/Jogo

Lost in The Darkness é um jogo que se passa em um mapa quadriculado escuro de tamanho fixo 5x5
representando um mundo sombrio. Há uma pessoa perdida e monstros espalhados pelo mapa, gerados aleatoriamente. No entanto,
o protagonista do jogo pode cumprir a missão de salvar a pessoa, tendo consigo uma arma para enfrentar tais monstros.
Ademais, ele conta com munição e kit médico também espalhados aleatoriamente pelo mapa.
Assim, o protagonista ganha o jogo se conseguir capturar a pessoa perdida e encontrar a porta para sair do mundo sombrio. Os turnos para
cumprir tal objetivo são contados e, quanto menos turnos para ganhar o jogo, melhor.
Porém, perde o jogo caso ele morra ou a pessoa perdida morra após sofrer sangramento.

# Equipe
* Nome: Daniel Alves da Rocha - RA:247024
* Nome: Leonardo Luca Favacho - RA:247226

# Arquivo Executável do Jogo

[lost in the darkness](https://github.com/Alv3s-Fav4cho/MC322-dupla/blob/main/executavel/trabalho_com_interface.jar)

# Slides do Projeto

## Slides da Prévia
[apresentação 1](https://docs.google.com/presentation/d/1ebuWa0mRhdeRJlyFdBaJB6J8-5YdEax1ck0ExOhTA0o/edit#slide=id.g12eab453924_0_0)

## Slides da Apresentação Final
[apresentação final](https://docs.google.com/presentation/d/1_Il_i4ERdoqOP_OMNCpQH4tbsKAtW0nK6Z0qSo7RvOk/edit#slide=id.p)
# Diagramas
## diagrama de classes
![alt text](https://github.com/Alv3s-Fav4cho/MC322-dupla/blob/main/Diagramas/diagrama%20de%20classes.drawio.png)

## Diagrama Geral da Arquitetura do Jogo
![alt text](https://github.com/Alv3s-Fav4cho/MC322-dupla/blob/main/Diagramas/Arquitetura_lost-in-the-darkness.drawio.png)

*diagrama geral com divisão MVC por meio de cores
![alt text](https://github.com/Alv3s-Fav4cho/MC322-dupla/blob/main/Diagramas/Arquitetura_lost-in-the-darkness%20com%20divis%C3%A3o%20MVC.drawio.png)

## diagrama de exceções
![alt text](https://github.com/Alv3s-Fav4cho/MC322-dupla/blob/main/Diagramas/diagrama%20de%20excecoes.PNG)

**Detalhamento do plano de exeções**
Classe | Descrição
----- | -----
ComandoInvalido| usuário usa um comando que não é esperado
MovimentoParaBorda | personagem está na borda do mapa e o usuário tenta se mover para esse lado
MovimentoSemSanidade | personagem sem sanidade e usuário tenta se mover
tiroSemMunicao | personagem sem munição e usuário tenta usar o comando de atirar
pegarMunicao_SemMunicao | usuario usa o comando de recolher munição sem ter munição na posição do personagem
pegarKit_SemKit | usuario usa o comando de recolher kit médico sem ter kit médico na posição do personagem
usarKitSemTer | personagem sem kit e usuario tenta usar o comando de usar kit médico

# destaques de código
## polimorfismo
~~~java
public class Sala {
     …
     public Sala(int i, int j) {
          …
          Ator monstro = AtorFactory.getAtor("monstro", i, j, null);
          …
          Ator kit = AtorFactory.getAtor("kit", i, j, null);
          …
          Ator municao = AtorFactory.getAtor("municao", i, j, null);
     }
}   
~~~
~~~java
public class Montador {
     …
     public void montar() {
          …
          prot = AtorFactory.getAtor("prot", linha, coluna, neth);
          …
          pessoap = AtorFactory.getAtor("pessoap", linha, coluna, neth);
          …
          porta = AtorFactory.getAtor("porta", linha, coluna, neth);
     }
}
     
~~~

## Sobrecarga de método
~~~java
public class ControleJogo {
     …
     private void protTomaDano() {
          …
     }
     private void protTomaDano(char c) {
          …
     }
     …
}
~~~

## Geração aleatória 1
> utilizada para instanciar protagonista, pessoa perdida e porta na matriz de salas
~~~java
public class Montador {
     …
     public void montar() {
          …
          x = rand.nextInt(25) + 1;
          if (x % n == 0) {
               linha = x / n - 1;
               coluna = n - 1;
          } else if (x / n == 0) {
               linha = x / n;
               coluna = x % n - 1;
          }else{
               linha = x / n;
               coluna = x % n - 1;
          }
          prot = AtorFactory.getAtor("prot", linha, coluna, neth);
          …
     }
     …
}

~~~

## Geração aleatória 2
> utilizada para instanciar monstro, kit médico e munição na matriz de salas
~~~java
public class Sala {
     …
     public Sala(int i, int j) {
          …
          x = rand.nextInt(100)+1;
          if(x > 70){ // 30% de chance de criar um monstro junto com a sala
               Ator monstro = AtorFactory.getAtor("monstro", i, j, null);
               atores.add(monstro);
          }
          …
     }
     …
}     
~~~


# Design Pattern
>instanciação com factory
~~~java
Ator monstro = AtorFactory.getAtor("monstro", i, j, null);
~~~
>codigo da classe ActorFactory
~~~java
public class AtorFactory {

     public static Ator getAtor(String type, int linha, int coluna, Nether neth) {
     …
     if ("monstro".equalsIgnoreCase(type)){
          return new Monstro(type, linha, coluna);
     }     
     …
     return null;
     }
}
~~~
> construtor do ator monstro

~~~java
public class Monstro extends Ator{
     public Monstro(String type, int linha, int coluna){
          setType(type);
          setLinha(linha);
          setColuna(coluna);
     }
}
~~~


