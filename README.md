# Projeto MapFood

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/3534ed9497e345cf9a44c7b81320a996)](https://app.codacy.com/app/aceleradev/aceleradev-mapfood?utm_source=github.com&utm_medium=referral&utm_content=imerljak/aceleradev-mapfood&utm_campaign=Badge_Grade_Dashboard)


# Equipe: Mighty Ducks
![](https://s3951.pcdn.co/wp-content/uploads/2015/09/Ducks1-532x480.gif)


## Sobre a Solução

Esta implementação utiliza `PostgreSQL` e `MongoDB` como solução de base de dados. Com o objetivo de utilizar o melhor dos dois mundos. A base de dados relacional para informações bem estruturadas que precisam de velocidade na atualização/inserção de dados. E a base de dados *não* relacional para informações com dados que exigem maior flexibilidade e velocidade na consulta/relatórios.

É feita uma pesquisa nas localizações dos motoboys na base de dados e retorna-se o mais próximo para atender utilizando a função de Haversine nas distancias para calcular a menor. A API Google directions então calcula e otimiza as melhores rotas para fazer o trajeto.

Para efetuar o calculo de distância entre os recursos (motoboys, clientes, estabelecimentos) é utilizado uma implementação da [Formula de Haversine](https://pt.wikipedia.org/wiki/F%C3%B3rmula_de_Haversine) que fornece a distância entre dois pontos em uma esfera (Esfera ~= Terra).


## Configurando o Ambiente.

Para o funcionamento pleno deste projeto em seu ambiente é necessário ter instalados:
* PostgreSQL
* MongoDB

Para o calculo correto da distância dos motoboys na consulta do motoboy mais proximo é usado uma função que implementa a [Formula de Haversine](https://pt.wikipedia.org/wiki/F%C3%B3rmula_de_Haversine). O Postgres não disponibiliza essa função nativamente, então é necessário executar o script `haversine.sql` que está dentro da pasta `/resouces/` na base de dados para registrar a função e ser capaz de utilizá-la.

---

## Descrição

Para utilizar a metodologia CBL - Challenge Based Learning, os participantes do programa executarão um projeto real baseado nos aprendizados que recebem dos tutores, mentores e equipes da Code:Nation e da Movile. Para isso, os selecionados serão divididos em squads para execução do projeto MapFood.

O objetivo do squad é fornecer autonomia para que cada equipe tome decisões e que cada integrante descubra seu papel dentro do grupo, tornando-os aptos a resolver problemas reais por conta própria.

## Objetivo:

O objetivo do projeto é criar uma API para organizar os pedidos e gerar os melhores trajetos de solicitacaoEntrega do iFood.

## Contextualização

Utilizando um conjunto de dados da plataforma iFood, contendo informações como estabelecimentos conveniados, suas localizações e produtos, os participantes deverão construir uma API para gerenciar essas informações, além de uma base com os motoristas que farão as entregas e respectivas listas de pedidos (contando com clientes e localização). A API deverá apresentar o melhor trajeto e também poderá trazer outras informações (a critério das squads), como gasto de cada estabelecimento com a quilometragem rodada, tempo de solicitacaoEntrega, etc.

Ao final do programa, além de apresentar a API criada, cada squad deverá expor quais são os pontos de melhoria e quais seriam os próximos passos no projeto, caso fossem seguir adiante. 


## Requisitos técnicos obrigatórios

Para o projeto MapFood, é necessário que as squads se utilizem dos aprendizados repassados pelo programa AceleraDev, portanto a solução deve ser construída de acordo com os seguintes requisitos:

- Banco de dados;
- Desenvolvimento do backend e APIs com Java Spring Boot;
- Testes unitários são um bônus importante

## Definições do sistema MapFood

- Um motoboy pode levar no máximo 5 pedidos por entrega independente do número de itens do pedido;
- O tempo de preparação de cada pedido é 10 minutos;
- Consumo motocicleta: 42km/L
- Entende-se por pedido como uma solicitação de entrega feita por um usuário, sendo que o mesmo pode conter diversos itens (cachorro quente, batata frita, refrigerante, etc);
- Para o tempo de entrega, é importante considerar os seguintes parâmetros:
	- Deslocamento do motoboy até o estabelecimento;
	- Deslocamento do motoboy até a entrega do pedido ao cliente.

## Casos práticos do sistema

Para facilitar o entendimento da API que deverá ser construída, segue abaixo alguns casos práticos de possíveis interações que o sistema pode conter:

**Dado que** um consumidor selecionou um cachorro quente no estabelecimento X
**Quando** o pedido for realizado
**Então** deve-se identificar no sistema qual motoboy deve ser selecionado, qual o respectivo trajeto e informações adicionais do trajeto (ex: quilometragem, tempo de entrega…);

**Dado que** vários pedidos em localizações próximas foram solicitados em um mesmo estabelecimento e em horários próximos
**Quando** o sistema for selecionar o motoboy e respectiva rota
**Então** o mesmo pode levar em consideração a proximidade desses pedidos para utilizar o mesmo motoboy em tais entregas.

**Dado que** um restaurante deseje saber o tempo/quilometragem das entregas do seu estabelecimento
**Quando** o restaurante solicitar tais dados de determinada data
**Então** o sistema poderá gerar um relatório para o mesmo.

## Informações adicionais

É papel do squad definir quais atributos serão levados em consideração para definir o melhor trajeto. Alguns exemplos são: quilometragem, tempo de entrega, capacidade de buscar e entregar múltiplos pedidos em uma única rota, etc.

Com exceção dos requisitos técnicos obrigatórios, as funcionalidades e seus atributos podem ser alterados livremente por cada squad, desde que as alterações sejam justificáveis. Exemplos: em tempo de desenvolvimento, nova ideia que facilite o uso pelo usuário, melhorias na geração das rotas de entrega, etc.

É necessário que apenas uma pessoa do squad faça a submissão dos códigos para que os mentores possam fazer a avaliação.
