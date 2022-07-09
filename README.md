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

> Lost in The Darkness é um jogo que se passa em um mapa quadriculado escuro de tamanho fixo 5x5
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

# Relatório de Evolução
> Durante o planejamento do projeto, havíamos definido que a arquitetura do jogo seria MVC (Model-View-Controller) e, com base nisso, já tínhamos ideia do seu funcionamento geral e dos possíveis problemas que surgiriam. Um desses problemas foi o uso frequente de “casts”, que foi resolvido colocando os métodos da subclasse na superclasse, implementando nas subclasses somente os métodos pertinentes a ela. No entanto, por não entendermos a efetividade e relevância das interfaces na arquitetura do projeto, não construímos um plano de interfaces adequado ao jogo. Além disso, o uso tardio da interface gráfica, após o término e testes do jogo no terminal, e alguns erros entre a IDE e o framework (LibGDX) foram empecilhos que nos impediram de explorar mais os recursos da interface gráfica, embora todas as ações e fluxo do jogo estejam ocorrendo normalmente pela interface, faltando somente duas telas para vitória e derrota que finalizam o jogo.

# Destaques de Código
## Polimorfismo
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


# Destaques de Pattern

## Diagrama do Pattern Factory

![alt text](https://github.com/Alv3s-Fav4cho/MC322-dupla/blob/main/Diagramas/Diagrama%20de%20pattern.drawio.png)

## Código do pattern Factory

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

> o Pattern Factory foi adotado para padronizar a criação de uma instância para determinado objeto, precisando para isso, passar como parâmetro uma String com tipo do objeto que se deseja instanciar e outros parâmetros referentes ao construtor do objeto alvo da instanciação, como mostra o diagrama de pattern mostrado acima.

# Conclusões e Trabalhos Futuros
> Ao final do projeto, podemos concluir que a sua proposta foi alcançada, como: utilizar os conceitos de polimorfismo, herança, classe abstrata e sobrecarga de métodos, plano de exceções e interface gráfica que, embora não tenhamos explorado melhor seu potencial, tornou o jogo mais interativo.
O desenvolvimento de um plano de interfaces, apesar de não termos julgado tão relevante para a elaboração do projeto, talvez pudesse ter contribuído para sua organização. Ademais, a falta de tempo dificultou a expansão do jogo, que poderia ter mais níveis de dificuldade, alterando a proporção entre a geração aleatória de monstros e kits médicos pelo mapa, assim como mapas maiores e de formatos diferentes. Com isso, concluímos que o planejamento da arquitetura e gerenciamento de tempo melhores, aliados à exploração de boas práticas da orientação a objetos, como já havia sido feita até então no projeto, permitiriam facilmente a sua expansão futuramente.

# Diagramas
## Diagrama Geral da Arquitetura do Jogo
![alt text](https://github.com/Alv3s-Fav4cho/MC322-dupla/blob/main/Diagramas/diagrama%20de%20classes.drawio.png)
> Para o desenvolvimento do jogo, usamos o padrão Model-View-Controller, em que temos um componente (Controller) que interage com o Model, controlando todo o fluxo do jogo e ações dos atores, e outro componente (View) que apresenta todas as informações do Model ao usuário.

## Diagrama de Classes
![alt text](https://github.com/Alv3s-Fav4cho/MC322-dupla/blob/main/Diagramas/Arquitetura_lost-in-the-darkness.drawio.png)

* Diagrama de Classes com divisão MVC por meio de cores
![alt text](https://github.com/Alv3s-Fav4cho/MC322-dupla/blob/main/Diagramas/Arquitetura_lost-in-the-darkness%20com%20divis%C3%A3o%20MVC.drawio.png)

## Diagrama Geral de Componentes
![alt text](https://github.com/Alv3s-Fav4cho/MC322-dupla/blob/main/Diagramas/Componentes.drawio.png)
> O objeto controle solicita acesso aos métodos que pertencem aos atores

## Componente `<Ator>`

> Este componente é responsável por receber requisições do controle e repassá-las ao nether, pois este que efetivamente verificará se a ação recebida pelo controle é válida.

![Componente](https://github.com/Alv3s-Fav4cho/MC322-dupla/blob/main/Diagramas/componente%20ator.drawio.png)

**Ficha Técnica**
item | detalhamento
----- | -----
Classe | `<com.mygdx.game>`
Autores | `<Daniel e Leonardo>`
Interfaces | `<IProtPessoap>`

### Interfaces

Interfaces associadas a esse componente:

![Diagrama Interfaces](https://github.com/Alv3s-Fav4cho/MC322-dupla/blob/main/Diagramas/interface_IProtPessoap.drawio.png)

## Detalhamento de interface

### Interface `<IProtPessoap>`

`<Define métodos comuns as classes do protagonista e da pessoa perdida>`

~~~java
public interface IProtPessoap {
	public String getType();
	public void setType(String type);
	public int getLinha();
	public void setLinha(int linha);
	public int getColuna();
	public void setColuna(int coluna);
	public void conectaNether(Nether neth);
	public boolean atorSeMove(int linha_atual, int coluna_atual, int nova_linha,int nova_coluna, String tipo);
}
~~~

Classe | Descrição
----- | -----
getType| Retorna uma String com o tipo de ator
setType | Define o tipo de ator
getLinha | Retorna a linha atual do ator
setLinha | Define a linha atual do ator
getColuna | Retorna a coluna atual do ator
setColuna | Define a coluna atual do ator
conectaNether | Realiza a conexão entre o ator e o nether
atorSeMove | Verifica se o movimento do ator é válido


## Diagrama de exceções
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
