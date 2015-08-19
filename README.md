Pacman search

Neste jogo o Pacman está posicionado em um grid, ele deve encontrar um caminho para a comida utilizando alguma estratégia de busca a partir do seguinte formato de input:

    P=(3,9)
    C=(5,1)
    7x20
    ████████████████████
    █··············█···█  
    █·██·██·██·██·██·█·█  
    █········☺·······█·█  
    ██████████████████·█  
    █ϖ·················█  
    ████████████████████  

* A primeira linha identifica a coordenada do Pacman na grid no formato linha/coluna.
* A segunda linha identifica a coordenada da comida na grid no formato linha/coluna.
* A terceira linha determina o tamanho da grid indicando o número de linhas e colunas. O tamanho máximo da grid deve ser 30x30.
* O símbolo █ representa as paredes enquanto que o símbolo · representa o espaços por onde o Pacman pode percorrer.

A partir do input informado devolva como output um possível caminho para o Pacman chegar até a comida, o output deve obedecer o seguinte formato:



    P=(3,9)
    C=(5,1)
    7x20
    ████████████████████
    █··············█***█  
    █·██·██·██·██·██*█*█  
    █········☺*******█*█  
    ██████████████████*█  
    █ϖ*****************█  
    ████████████████████  

O Output obedece o mesmo formato do input inicial com a adição do caminho a ser percorrido pelo Pacman para chegar até a comida, este representado pelo o caractere "*".

Para resolver este problema você pode utilizar qualquer linguagem de sua preferência bem como qualquer algoritmo. Além da resolução do problema é esperado que o código possua uma boa semântica e cobertura de testes.

Para submeter uma solução realize um fork deste projeto e envie um pull request.
